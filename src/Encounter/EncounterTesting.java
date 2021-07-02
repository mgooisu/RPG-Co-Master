package Encounter;

import Creature.BaseCreature;
import Creature.CreatureListHandler;
import Creature.CreatureMap;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMap;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import Creature.Monster;
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
    void addMonsters() throws IOException, ClassNotFoundException {
        Stats stats = new Stats(10,14,15,6,8,5);
        Monster skeleton = new Monster(null, Alignment.CombinedAlignment.Lawful_Evil,
                "A shambling pile of animate bones","Skeleton",13,13,30,
                Size.MEDIUM, speciesMap.getSpeciesHashMap().get("Undead"),stats,new Condition[]{Condition.POISONED,Condition.EXHAUSTION},
                new Condition[]{},new Damage[]{Damage.POISON},new Damage[]{},new Damage[]{});

        stats = new Stats(11,12,10,10,11,10);
        Monster cultist = new Monster("Carl", Alignment.CombinedAlignment.Neutral_Evil,"A cultist",
                "Cultist",9,12,30,Size.MEDIUM,speciesMap.getSpeciesHashMap().get("Humanoid"),
                stats,new Condition[]{},new Condition[]{}, new Damage[]{},new Damage[]{},new Damage[]{}
                );

        //temporary setting
        creatureMap.setCreatureHashMap(new HashMap<>());

        HashMap<String,BaseCreature> newMap = creatureMap.getCreatureHashMap();
        newMap.put("Skeleton 1",skeleton);
        newMap.put("Skeleton 2",skeleton);
        newMap.put("Cultist 1",cultist);
        creatureMap.setCreatureHashMap(newMap);
        for(String key: creatureMap.getCreatureHashMap().keySet()){
            BaseCreature creature = creatureMap.getCreatureHashMap().get(key);
            System.out.println(creature.getDescription());
        }
    }
}
