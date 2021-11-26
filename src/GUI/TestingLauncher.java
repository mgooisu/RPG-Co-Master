package GUI;

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
import Creature.Monster;
import Exceptions.CreatureException;

import javax.swing.*;
import java.io.IOException;

/**
 * Class for launching the gui classes
 */
public class TestingLauncher {
    static Stats stats;
    static SpeciesMapObjectHandler speciesMapObjectHandler;
    static Species undead;

    public static void main(String[] args) throws CreatureException, IOException, ClassNotFoundException {
        //Setting up a whole monster

        speciesMapObjectHandler = new SpeciesMapObjectHandler();
        undead = speciesMapObjectHandler.readObject().getSpeciesHashMap().get("Undead");

        stats = new Stats(10, 11, 12, 4, 14, 15);

        Condition[] conditionArray = new Condition[]{};

        Damage[] damageArray = new Damage[]{};
        Monster skeleton;
        skeleton = new Monster(null, Alignment.CombinedAlignment.Lawful_Evil, "A clinking, rattling" +
                "pile of ancient bones", "skeleton", 13, 13, 30, Size.MEDIUM, undead, stats,
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
        CreatureGenPanel creatureGenPanel = new CreatureGenPanel();
        creatureGenPanel.setLocationRelativeTo(null);
        creatureGenPanel.setVisible(true);
        creatureGenPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);







    }
}
