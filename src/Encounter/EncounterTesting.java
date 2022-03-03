package Encounter;

import Creature.*;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMap;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import Exceptions.CreatureException;
import Helpers.DiceObject;
import Helpers.FileReadWrite;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class EncounterTesting {
    private static Encounter encounter;
    private static SpeciesMap speciesMap;
    private static CreatureMap creatureMap;
    @BeforeAll
    static void setup() throws IOException, ClassNotFoundException {
        encounter = new Encounter("Test Encounter");
        SpeciesMapObjectHandler speciesMapObjectHandler = new SpeciesMapObjectHandler();
        speciesMap = speciesMapObjectHandler.readObject();
        CreatureListHandler creatureListHandler = new CreatureListHandler();
        creatureMap = creatureListHandler.readObject();
    }

    @Test
    void addMonsters() throws  CreatureException {
        DiceObject testDice = new DiceObject(6,2,0);
        Stats stats = new Stats(10,14,15,6,8,5);
        Monster skeleton = new Monster(null, new Alignment(Alignment.Ethic.LAWFUL, Alignment.Moral.EVIL),
                "A shambling pile of animate bones","Skeleton",13,testDice,13,30,
                Size.MEDIUM, speciesMap.getSpeciesHashMap().get("Undead"),stats,new Condition[]{Condition.POISONED,Condition.EXHAUSTION},
                new Condition[]{},new Damage[]{Damage.POISON},new Damage[]{},new Damage[]{});

        stats = new Stats(11,12,10,10,11,10);
        Monster cultist = new Monster("Carl", new Alignment(Alignment.Ethic.NEUTRAL, Alignment.Moral.EVIL),"A cultist",
                "Cultist",9,testDice,12,30,Size.MEDIUM,speciesMap.getSpeciesHashMap().get("Humanoid"),
                stats,new Condition[]{},new Condition[]{}, new Damage[]{},new Damage[]{},new Damage[]{}
                );

        //temporary setting
        creatureMap.setCreatureHashMap(new HashMap<>());

        HashMap<String, Creature> newMap = creatureMap.getCreatureHashMap();
        newMap.put("Skeleton 1",skeleton);
        newMap.put("Skeleton 2",skeleton);
        newMap.put("Cultist 1",cultist);
        creatureMap.setCreatureHashMap(newMap);
        for(String key: creatureMap.getCreatureHashMap().keySet()){
            Creature creature = creatureMap.getCreatureHashMap().get(key);
            System.out.println(creature.getDescription());
        }
    }

    @AfterAll
    static void cleanup() throws IOException {
        FileReadWrite.deleteObjectFile("./CreatureListFile.dnd");
        FileReadWrite.deleteObjectFile("./SpeciesListFile.dnd");
    }
}
