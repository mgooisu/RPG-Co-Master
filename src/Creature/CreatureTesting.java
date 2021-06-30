package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

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
    private static final int maxHP = 13, aC = 13, speed = 30;
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

        skeleton = new BaseCreature(null, alignment,description,creatureClass, maxHP,aC,speed,size,species,stats);

    }
    @Test
    void testStatTracking(){
        //initial Case
        Stats myStats = skeleton.getStats();
        int[] myStatsArray = {myStats.getStrength(), myStats.getDexterity(),myStats.getConstitution(),
                myStats.getIntelligence(),myStats.getWisdom(),myStats.getCharisma()};
        int[] expectedArray = {10,14,15,6,8,5};
        Assertions.assertArrayEquals(expectedArray,myStatsArray);
        //altered case - for level-ups, buffs/de-buffs ect
        expectedArray =new int[] {18,16,14,11,13,5};
        skeleton.setStats(new Stats(
                expectedArray[0],expectedArray[1],expectedArray[2],expectedArray[3],expectedArray[4],expectedArray[5]));
        myStats = skeleton.getStats();
        myStatsArray = new int[]{myStats.getStrength(), myStats.getDexterity(),myStats.getConstitution(),
                myStats.getIntelligence(),myStats.getWisdom(),myStats.getCharisma()};
        Assertions.assertArrayEquals(expectedArray,myStatsArray);

    }
    @Test
    void checkHP(){
        Assertions.assertEquals(skeleton.getHealth(),13);
    }
    @Test
    void damageAndHeal(){
        // Healing under max case
        skeleton.healthDamage(5);
        Assertions.assertEquals(8,skeleton.getHealth());
        skeleton.healthHealing(4);
        Assertions.assertEquals(12,skeleton.getHealth());
        // Excessive Healing case
        skeleton.healthHealing(100);
        Assertions.assertEquals(skeleton.getMaxHP(),skeleton.getHealth());
    }

    @Test
    void temporaryHP(){
        skeleton.setTempHP(10);
        // tempHP>damage case
        skeleton.healthDamage(5);
        Assertions.assertEquals(5,skeleton.getTempHP());
        Assertions.assertEquals(skeleton.getMaxHP(),skeleton.getHealth());

        //tempHP<damage case
        skeleton.setTempHP(5);
        skeleton.healthDamage(8);
        Assertions.assertEquals(0,skeleton.getTempHP());
        Assertions.assertEquals(10,skeleton.getHealth());

        //tempHP = damage case
        skeleton.setTempHP(5);
        skeleton.healthDamage(5);
        Assertions.assertEquals(0,skeleton.getTempHP());
        Assertions.assertEquals(10,skeleton.getHealth());
    }
    @Test
    void alignmentTesting(){
        //base Lawful Evil
        Alignment skelAlign = skeleton.getAlignment();
        Assertions.assertEquals(Alignment.Ethic.LAWFUL,skelAlign.getEthic());
        Assertions.assertEquals(Alignment.Moral.EVIL,skelAlign.getMoral());
        //Lawful good Heel-turn
        skeleton.setAlignment(Alignment.CombinedAlignment.Lawful_Good);
        skelAlign = skeleton.getAlignment();
        Assertions.assertEquals(Alignment.Ethic.LAWFUL,skelAlign.getEthic());
        Assertions.assertEquals(Alignment.Moral.GOOD,skelAlign.getMoral());
    }

    @Test
    void armorTesting(){
        int newAC = 15;
        skeleton.setAC(newAC);
        Assertions.assertEquals(newAC,skeleton.getAC());
    }

    @Test
    void speedTesting(){
        int newSpeed = 60;
        skeleton.setSpeed(newSpeed);
        Assertions.assertEquals(newSpeed,skeleton.getSpeed());
    }

    @Test
    void addCondition(){

    }





}
