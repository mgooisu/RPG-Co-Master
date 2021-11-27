package GUI.Creatures;

import Creature.Actions.Actions;
import Creature.Actions.Attack;
import Creature.Actions.Range;
import Creature.Features;
import Creature.Helpers.Enums.Damage;
import GUI.Elements.DeletableList;
import Helpers.DiceObject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * This panel is for creatures that have combat capability. It extends the base creature creator with Combat Features,
 * Actions, and Legendary Actions. Legendary Actions are a extension of actions with a special condition string
 */
public class CombatCreatureGenPanel extends JPanel {
    JPanel combatFeaturePanel, actionPanel, legendaryActionPanel;

    //Data
    ArrayList<Actions> actions,legendaryActions;
    ArrayList<Features> features;

    public CombatCreatureGenPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Data initialization
        actions = new ArrayList<>();
        features = new ArrayList<>();
        legendaryActions = new ArrayList<>();


        //Temporary data filling todo - remove
        actions.add(new Actions("Multiattack","The Eagle makes two attacks, one with its beak, and one with its claws", Actions.ActionType.OTHER));
        features.add(new Features("Keen Sight", "The eagle has advantage on wisdom(perception) checks that rely on sight"));
        actions.add(new Attack("Beak","", Actions.ActionType.MELEE_ATTACK,new DiceObject(6,1,3),new Range(5),1,5, Damage.PIERCING));
        actions.add(new Attack("Talons","", Actions.ActionType.MELEE_ATTACK,new DiceObject(6,2,3),new Range(5),1,5,Damage.SLASHING));
        actions.add(new Attack("Spit","gross", Actions.ActionType.RANGED_ATTACK,new DiceObject(6,2,3),new Range(5,10),1,5,Damage.PSYCHIC));
        legendaryActions.add(new Actions("Beeg Speen","The bird speens", Actions.ActionType.LEGENDARY));

        //Features Panel
        combatFeaturePanel = new JPanel();
        combatFeaturePanel.setBorder(new TitledBorder("Combat Features"));

        DeletableList featuresList = new DeletableList("Features",features,new Features("",""));
        combatFeaturePanel.add(featuresList);

        //Actions Panel
        actionPanel = new JPanel();
        actionPanel.setBorder(new TitledBorder("Actions"));
        DeletableList actionsList = new DeletableList("Actions", actions, new Actions("","", Actions.ActionType.OTHER));
        actionPanel.add(actionsList);


        //Legendary Actions panel
        legendaryActionPanel = new JPanel();
        legendaryActionPanel.setBorder(new TitledBorder("Legendary Actions"));
        DeletableList legendaryActionsList = new DeletableList("Legendary Actions", legendaryActions,
                new Actions("","", Actions.ActionType.LEGENDARY));
        legendaryActionPanel.add(legendaryActionsList);






        add(combatFeaturePanel);
        add(actionPanel);
        add(legendaryActionPanel);



    }
}
