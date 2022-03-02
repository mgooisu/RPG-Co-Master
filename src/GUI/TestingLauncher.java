package GUI;

import Campaign.Campaign;
import Creature.Actions.Actions;
import Creature.Actions.MonsterAction;
import Creature.Actions.Range;
import Creature.Creature;
import Creature.CreatureListHandler;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import Creature.Monster;
import Encounter.Encounter;
import Exceptions.CreatureException;
import GUI.Encounter.EncounterFrame;
import GUI.Home.HomeFrame;
import Helpers.DiceObject;

import javax.swing.*;
import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class for launching the gui classes
 */
public class TestingLauncher {
    static Stats stats;
    static SpeciesMapObjectHandler speciesMapObjectHandler;
    static Species undead;
    static DiceObject hpDice;

    public static void main(String[] args) throws CreatureException, IOException, ClassNotFoundException {
        //Setting up a whole monster

        speciesMapObjectHandler = new SpeciesMapObjectHandler();
        undead = speciesMapObjectHandler.readObject().getSpeciesHashMap().get("Undead");
        hpDice = new DiceObject(6,2,0);
        stats = new Stats(10, 11, 12, 4, 14, 15);


        Condition[] conditionArray = new Condition[]{};

        Damage[] damageArray = new Damage[]{};
        Monster skeleton;
        skeleton = new Monster(null, new Alignment(Alignment.Ethic.LAWFUL, Alignment.Moral.EVIL), "A clinking, rattling" +
                "pile of ancient bones", "skeleton", 13, hpDice,13, 30, Size.MEDIUM, undead, stats,
                new Condition[]{Condition.EXHAUSTION, Condition.POISONED}, conditionArray,
                new Damage[]{Damage.POISON}, damageArray, new Damage[]{Damage.BLUDGEONING});
        skeleton.addCondition(Condition.BLINDED, 3);
        MonsterAction[] actions = new MonsterAction[]{new MonsterAction("Shortsword", Actions.ActionType.MELEE_ATTACK, "An attack with a shortsword",
                null, 0, new Range(5), 4, 1, 1, 6, 2),
                new MonsterAction("Shortbow", Actions.ActionType.RANGED_ATTACK, "An attack with a shortbow",
                        null, 0, new Range(80, 320), 4, 1, 1, 6, 2)
        };
        skeleton.setActions(actions);
        skeleton.setSenses(new String[]{"Darkvision 60ft", "Passive Perception 9"});
        skeleton.setLanguages(new String[]{"Understands all languages they knew in life, but cannot speak", "Common", "Dwarfish"});
        //Adding test monster to Encounter
//        CreatureListHandler creatureListHandler = new CreatureListHandler();
//        creatureListHandler.addCreature(skeleton);

        HomeFrame homeFrame = new HomeFrame();
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);

//        /**
//         * Periodic task operation  - Prints out all the creatures in the encounter every 5 seconds
//         * https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds/12908467#12908467
//         */
//        Runnable timeSecondsRunnable = new Runnable() {
//            @Override
//            public void run() {
//                StringBuilder creatureListString = new StringBuilder("Encounter creatures: ");
//                for(Creature creature : encounter.getCreatures()){
//                    creatureListString.append(creature.getCreatureClass());
//                }
//                System.out.println(creatureListString);
//            }
//        };
//
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        //executor.scheduleAtFixedRate(timeSecondsRunnable,1,5, TimeUnit.SECONDS);


//        //Creature Full Interface
//
//        JFrame creatureFrame = new JFrame();
//        CreaturePanel creaturePanel = new CreaturePanel(skeleton);
//        creatureFrame.add(creaturePanel);
//        creatureFrame.setLocationRelativeTo(encounterFrame);
//        creatureFrame.setVisible(true);
//        creatureFrame.pack();
//
//        //
//        JFrame cf = new JFrame();
//        CreatureListPanel cr = new CreatureListPanel(skeleton);
//        cf.add(cr);
//        cf.setLocationRelativeTo(null);
//        cf.setVisible(true);
//        cf.pack();




    }
}
