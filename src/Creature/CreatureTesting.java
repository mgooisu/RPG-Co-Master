package Creature;

import Creature.Actions.Actions;
import Creature.Actions.MonsterAction;
import Creature.Actions.Range;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import Exceptions.CreatureException;
import Helpers.DiceObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    static Monster skeleton;
    private static final int maxHP = 13, aC = 13, speed = 30;
    private static final DiceObject hpDice = new DiceObject(2,6,0);
    private static final Stats stats =
            new Stats(10,14,15,6,8,5);
    private final static String description ="A shambling, rattling warrior of bone",
            name = null, creatureClass ="Skeleton";


    private static final Alignment alignment = new Alignment(Alignment.Ethic.LAWFUL, Alignment.Moral.EVIL);
    private static final Size size = Size.MEDIUM;
    static String[]
            senses = {"Darkvision 60ft","Passive perception 9"},
            languages ={"Understands all languages it knew in life, but can't speak"};
    static Damage[]
            vulnerabilities ={Damage.BLUDGEONING},
            immunities ={Damage.POISON},
            resistances ={};
    static Condition[]
            conditionResists ={},
            conditionImmunities = {Condition.EXHAUSTION,Condition.POISONED};

    @BeforeAll
    static void setup(){
        ArrayList<String> conditions = new ArrayList<>();

        skeleton = new Monster(null, alignment,description,creatureClass,
                maxHP,hpDice,aC,speed,size,species,stats,conditionImmunities,conditionResists,
                immunities,resistances,vulnerabilities);

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
        //Unaligned
        skeleton.setAlignment(null);
        skelAlign = skeleton.getAlignment();
        Assertions.assertNull(skelAlign.getEthic());
        Assertions.assertNull(skelAlign.getMoral());

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
    void addConditionTesting() throws CreatureException {
        skeleton.addCondition(Condition.PRONE,3);
        skeleton.removeCondition(Condition.CHARMED);
        skeleton.removeCondition(Condition.EXHAUSTION);
        ArrayList<Condition> conditions = skeleton.getConditions();
        for(Condition entry: conditions){
            System.out.println(entry.name());
            for(String effect: entry.getEffects()){
                System.out.println(effect);
            }
        }

        //The skeleton creature is immune to poison, so an attempt to poison should throw a creature exception
        Assertions.assertThrows(Exception.class,()->skeleton.addCondition(Condition.POISONED,3));
    }

    @Test
    void ExhaustionTesting() throws CreatureException{
        skeleton.setConditionImmunities(new Condition[]{Condition.POISONED});

        //Accumulative exhaustionTesting
        Assertions.assertEquals(0,skeleton.getExhaustionLevel());
        skeleton.addCondition(Condition.EXHAUSTION,1);
        skeleton.addCondition(Condition.EXHAUSTION,1);
        Assertions.assertEquals(2,skeleton.getExhaustionLevel());
        skeleton.removeCondition(Condition.EXHAUSTION);
        Assertions.assertEquals(1,skeleton.getExhaustionLevel());
        Assertions.assertTrue(skeleton.getConditions().contains(Condition.EXHAUSTION));

    }

    @Test
    void testDamages(){
        Assertions.assertTrue(
                Arrays.equals(resistances, skeleton.getResistances())&&
                        Arrays.equals(immunities,skeleton.getImmunities())&&
                        Arrays.equals(vulnerabilities,skeleton.getVulnerabilities())
                                        );
    }


    @Test
    void addAttacks(){
        MonsterAction[] actions = new MonsterAction[]{new MonsterAction("Shortsword", Actions.ActionType.MELEE_ATTACK,"An attack with a shortsword",
                null,0,new Range(5),4,1,1,6,2),
                new MonsterAction("Shortbow", Actions.ActionType.RANGED_ATTACK,"An attack with a shortbow",
                        null,0,new Range(80,320),4,1,1,6,2)
        };
        skeleton.setActions(actions);
        for (MonsterAction action: actions){
            System.out.println("the "+skeleton.getCreatureClass()+" has the following actions:");
            System.out.println(action.getName()+": "+action.getDescription());        }
    }





}
