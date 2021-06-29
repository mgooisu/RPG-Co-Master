package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CreatureTesting {
    SpeciesMapObjectHandler speciesMapObjectHandler;
    private static Species species;

    {
        try {
            speciesMapObjectHandler = new SpeciesMapObjectHandler();
            species = speciesMapObjectHandler.readObject().getSpeciesHashMap().get("Undead");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static BaseCreature skeleton;
    private static final int health = 13, aC = 13, speed = 30;
    private static final Stats stats =
            new Stats(10,14,15,6,8,5);
    private final static String description ="A shambling, rattling warrior of bone",
            name = null, creatureClass ="Skeleton";


    private static final Alignment.CombinedAlignment alignment = Alignment.CombinedAlignment.Lawful_Evil;
    private static final Size size = Size.MEDIUM;
    static String[]
            senses = {"Darkvision 60ft","Passive perception 9"},
            languages ={"Understands all languages it knew in life, but can't speak"},
            vulnerabilities ={"Bludgeoning"},
            immunities ={"poison"},
            resistances ={},
            conditionResists ={},
            conditionImmunities = {"Exhaustion","Poisoned"};

    @BeforeAll
    static void setup(){
        ArrayList<String> conditions = new ArrayList<>();

        skeleton = new BaseCreature(null, alignment,description,creatureClass,health,aC,speed,size,species,stats);

    }
    @Test
    void checkStats(){
        Stats myStats = skeleton.getStats();
        int[] myStatsArray = {myStats.getStrength(), myStats.getDexterity(),myStats.getConstitution(),
                myStats.getIntelligence(),myStats.getWisdom(),myStats.getCharisma()};
        int[] expectedArray = {10,14,15,6,8,5};
        Assertions.assertArrayEquals(expectedArray,myStatsArray);

    }

}
