package GUI.Creatures;

import Creature.Actions.Actions;
import Creature.Actions.Attack;
import Creature.Actions.Range;
import Creature.Features;
import Creature.Helpers.Enums.Damage;
import GUI.Elements.DeletableList.DeletableList;
import GUI.Elements.Fields.*;
import Helpers.DiceObject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This panel is for creatures that have combat capability. It extends the base creature creator with Combat Features,
 * Actions, and Legendary Actions. Legendary Actions are a extension of actions with a special condition string
 */
public class CombatCreatureGenPanel extends JPanel implements ActionListener {
    JPanel combatFeaturePanel, actionPanel, legendaryActionPanel;

    //Data
    ArrayList<Actions> actions,legendaryActions;
    ArrayList<Features> features;
    JButton newFeatureButton, newActionButton, newLegendaryActionButton;
    DeletableList legendaryActionsList, actionsList,featuresList;

    public CombatCreatureGenPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Data initialization
        actions = new ArrayList<>();
        features = new ArrayList<>();
        legendaryActions = new ArrayList<>();
        newActionButton = new JButton("Create New"); newLegendaryActionButton = new JButton("Create New"); newFeatureButton = new JButton("Create New");

        newFeatureButton.addActionListener(this);
        newLegendaryActionButton.addActionListener(this);
        newActionButton.addActionListener(this);




        //Temporary data filling todo - remove
        actions.add(new Actions("Multiattack","The Eagle makes two attacks, one with its beak, and one with its claws", Actions.ActionType.OTHER));
        features.add(new Features("Keen Sight", "The eagle has advantage on wisdom(perception) checks that rely on sight"));
        actions.add(new Attack("Beak","", Actions.ActionType.MELEE_ATTACK,new DiceObject(6,1,3),new Range(5),1,5, Damage.PIERCING));
        actions.add(new Attack("Talons","", Actions.ActionType.MELEE_ATTACK,new DiceObject(6,2,3),new Range(5),1,5,Damage.SLASHING));
        actions.add(new Attack("Spit","gross", Actions.ActionType.RANGED_ATTACK,new DiceObject(6,2,3),new Range(5,10),1,5,Damage.PSYCHIC));
        legendaryActions.add(new Actions("Beeg Speen","The bird speens", Actions.ActionType.LEGENDARY));

        //Features Panel
        combatFeaturePanel = new JPanel();
        combatFeaturePanel.setName("combatFeaturePanel");
        combatFeaturePanel.setBorder(new TitledBorder("Combat Features"));
        combatFeaturePanel.setLayout(new BorderLayout());




        featuresList = new DeletableList("Features",features,new Features("",""));
        combatFeaturePanel.add(newFeatureButton,BorderLayout.PAGE_START);

        combatFeaturePanel.add(featuresList,BorderLayout.CENTER);

        //Actions Panel
        actionPanel = new JPanel();
        actionPanel.setName("actionPanel");
        actionPanel.setLayout(new BorderLayout());
        actionPanel.setBorder(new TitledBorder("Actions"));
        actionsList = new DeletableList("Actions", actions, new Actions("","", Actions.ActionType.OTHER));
        actionPanel.add(newActionButton,BorderLayout.PAGE_START);


        actionPanel.add(actionsList,BorderLayout.CENTER);



        //Legendary Actions panel
        legendaryActionPanel = new JPanel();
        legendaryActionPanel.setName("legendaryActionPanel");
        legendaryActionPanel.setBorder(new TitledBorder("Legendary Actions"));
        legendaryActionPanel.setLayout(new BorderLayout());
        legendaryActionsList = new DeletableList("Legendary Actions", legendaryActions,
                new Actions("","", Actions.ActionType.LEGENDARY));

        legendaryActionPanel.add(newLegendaryActionButton,BorderLayout.PAGE_START);
        legendaryActionPanel.add(legendaryActionsList,BorderLayout.CENTER);







        add(combatFeaturePanel);
        add(actionPanel);
        add(legendaryActionPanel);



    }
    /**
     *
     * @return an array containing all the legendary or normal actions
     */
    public Actions[] getActionArray(){
        Actions[] actionArray = new Actions[actions.size()];
        int i = 0;
        for(Actions action: actions){
            actionArray[i] = action;
            i++;
        }
        return actionArray;
    }

    /**
     *
     * @return an array containing all the features of the monster
     */
    public Features[] getFeaturesArray(){
        Features[] featuresArray = new Features[features.size()];
        int i = 0;
        for(Features features: features){
            featuresArray[i] = features;
            i++;
        }
        return featuresArray;
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        JFrame combatEditorFrame = new JFrame();
        CombatEditorPanel newFeaturePanel = null;
        if(sourceButton == newFeatureButton){
            combatEditorFrame.setTitle("Create new Creature Feature");
            newFeaturePanel = new CombatEditorPanel(combatEditorFrame, featuresList,this);
        }
        if(sourceButton == newActionButton){
            combatEditorFrame.setTitle("Create new Creature Action");
            newFeaturePanel = new CombatEditorPanel(combatEditorFrame, actionsList,this);
        }
        if(sourceButton == newLegendaryActionButton){
            combatEditorFrame.setTitle("Create new Legendary Creature Action");
            newFeaturePanel = new CombatEditorPanel(combatEditorFrame,legendaryActionsList,this);
                    }
        if(newFeaturePanel == null){
            return;
        }
        combatEditorFrame.add(newFeaturePanel);

        combatEditorFrame.pack();
        combatEditorFrame.setLocationRelativeTo(this);
        combatEditorFrame.setVisible(true);
        combatEditorFrame.setAlwaysOnTop(true);
        combatEditorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


}

/**
 * Panel that allows the user to add new features or actions to the creature
 */
class CombatEditorPanel extends JPanel implements ActionListener {
    //Elements

    JComboBox<Actions.ActionType> actionType;
    JComboBox<Damage> damageType;
    JPanel centrePanel, rangePanel,dicePanel;
    FocusTextField combatName;
    FocusTextArea description;
    JButton addButton;
    DeletableList targetList;
    JFrame parentFrame;
    JSpinner diceNumSpinner, diceValSpinner, diceModSpinner, closeRangeSpinner,
            longRangeSpinner, targetCountSpinner,toHitSpinner;

    CombatCreatureGenPanel combatCreatureGenPanel;

    /**
     *
     * @param targetList The DeletableList object containing the combat creature elements to be added to
     * @param parentFrame The JFrame the Panel is being rendered in
     */
    CombatEditorPanel(JFrame parentFrame,DeletableList targetList,CombatCreatureGenPanel combatCreatureGenPanel){
        this.parentFrame = parentFrame;
        this.targetList = targetList;
        this.combatCreatureGenPanel = combatCreatureGenPanel;

        setLayout(new BorderLayout());
        centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));

        combatName = new FocusTextField();
        combatName.setBorder(new TitledBorder("Title"));
        add(combatName,BorderLayout.PAGE_START);

        description = new FocusTextArea();
        description.setRows(3);
        description.setColumns(40);
        description.setBorder(new TitledBorder("Description"));
        description.setLineWrap(true);
        centrePanel.add(description);





        switch (targetList.getName()){
            case "Features"->{


            }
            case "Actions"->{

                //Attack Type and Range
                    //Attack type
                rangePanel = new JPanel();

                actionType = new JComboBox<>(new Actions.ActionType[]{Actions.ActionType.RANGED_ATTACK,
                        Actions.ActionType.MELEE_ATTACK, Actions.ActionType.OTHER});
                actionType.setBorder(new TitledBorder("Action Type"));

                actionType.addActionListener(this);

                centrePanel.add(actionType);


                    //Range
                rangePanel.setBorder(new TitledBorder("Targets"));

                if(actionType.getSelectedItem()== Actions.ActionType.RANGED_ATTACK){
                    closeRangeSpinner = new JSpinner(new SpinnerNumberModel(10,1,100,5));
                    longRangeSpinner = new JSpinner(new SpinnerNumberModel(30,20,1000,10));

                    closeRangeSpinner.setBorder(new TitledBorder("Close"));
                    longRangeSpinner.setBorder(new TitledBorder("Long"));
                    rangePanel.add(closeRangeSpinner);
                    rangePanel.add(longRangeSpinner);

                }
                if(actionType.getSelectedItem()== Actions.ActionType.MELEE_ATTACK){
                    rangePanel.setBorder(new TitledBorder("Reach"));
                    closeRangeSpinner = new JSpinner(new SpinnerNumberModel(10,1,100,5));
                    closeRangeSpinner.setBorder(new TitledBorder("Reach"));
                    rangePanel.add(closeRangeSpinner);

                }

                    //Targets
                targetCountSpinner = new JSpinner(new SpinnerNumberModel(1,0,10,1));
                targetCountSpinner.setBorder(new TitledBorder("# of."));
                targetCountSpinner.setPreferredSize(new Dimension(60,50));
                rangePanel.add(targetCountSpinner);

                    //To-Hit Modifier
                toHitSpinner = new JSpinner(new SpinnerNumberModel(0,-20,100,1));
                toHitSpinner.setBorder(new TitledBorder("+ To-hit"));
                toHitSpinner.setPreferredSize(new Dimension(70,50));
                rangePanel.add(toHitSpinner);


                centrePanel.add(rangePanel);


                //Dice
                dicePanel = new JPanel(new GridLayout(1,3));
                diceNumSpinner = new JSpinner(new SpinnerNumberModel(1,1,100,2));
                diceValSpinner = new JSpinner(new SpinnerNumberModel(1,1,100,2));
                diceModSpinner = new JSpinner(new SpinnerNumberModel(0,-100,100,2));

                dicePanel.setBorder(new TitledBorder("Damage Dice"));

                diceNumSpinner.setBorder(new TitledBorder("Amount"));
                diceValSpinner.setBorder(new TitledBorder("Value"));
                diceModSpinner.setBorder(new TitledBorder("Mod"));

                dicePanel.add(diceNumSpinner);
                dicePanel.add(diceValSpinner);
                dicePanel.add(diceModSpinner);

                centrePanel.add(dicePanel);


                damageType = new JComboBox<>(Damage.values());
                damageType.setBorder(new TitledBorder("Damage Type"));
                centrePanel.add(damageType);











            }
            case "Legendary Actions"->{

            }
            default -> throw new IllegalStateException("Unexpected value: " + targetList.getName());
        }
        add(centrePanel,BorderLayout.CENTER);
        addButton = new JButton("Add New "+targetList.getName().substring(0,targetList.getName().length()-1));


        addButton.addActionListener(this);
        add(addButton,BorderLayout.PAGE_END);




    }
    //end of constructor



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        //Action type spinner check

        if(source == actionType){
            rangePanel.removeAll();
            if(actionType.getSelectedItem()== Actions.ActionType.RANGED_ATTACK){
                dicePanel.setVisible(true);
                damageType.setVisible(true);
                rangePanel.setVisible(true);
                closeRangeSpinner.setBorder(new TitledBorder("Close"));
                longRangeSpinner.setBorder(new TitledBorder("Long"));
                rangePanel.add(closeRangeSpinner);
                rangePanel.add(longRangeSpinner);

            }
            if(actionType.getSelectedItem()== Actions.ActionType.MELEE_ATTACK){
                dicePanel.setVisible(true);
                damageType.setVisible(true);
                rangePanel.setVisible(true);
                closeRangeSpinner.setBorder(new TitledBorder("Reach"));
                rangePanel.add(closeRangeSpinner);
            }
            if(actionType.getSelectedItem() == Actions.ActionType.OTHER){
                dicePanel.setVisible(false);
                damageType.setVisible(false);
                rangePanel.setVisible(false);

            }
            rangePanel.add(targetCountSpinner);
            rangePanel.add(toHitSpinner);
            rangePanel.revalidate();
            rangePanel.repaint();
        }

        //Add button - adds the combat thingy and closes the window
        if(source == addButton){
            String combatNameText, descriptionText;
            combatNameText = combatName.getText(); descriptionText = description.getText();
            //Failure requirements
            if(combatNameText.length()< 1){
                throw new StringIndexOutOfBoundsException("Name too short");
            }
            if(descriptionText.length()< 1){
                throw new StringIndexOutOfBoundsException("Description too short");
            }




            switch (targetList.getName()){
                case "Features"->{
                    Features feature = new Features(combatNameText, descriptionText);
                    combatCreatureGenPanel.features.add(feature);
                    targetList.addElement(feature);
                }
                case "Legendary Actions"->{
                    Actions legendaryAction = new Actions(combatNameText,descriptionText, Actions.ActionType.LEGENDARY);
                    combatCreatureGenPanel.actions.add(legendaryAction);
                    targetList.addElement(legendaryAction);
                }
                case "Actions"->{
                    if(actionType.getSelectedItem() == Actions.ActionType.MELEE_ATTACK|| actionType.getSelectedItem() == Actions.ActionType.RANGED_ATTACK) {

                        DiceObject dice = new DiceObject((Integer) diceValSpinner.getValue(), (Integer) diceNumSpinner.getValue(), (Integer) diceModSpinner.getValue());
                        Range range;
                        if (actionType.getSelectedItem() == Actions.ActionType.RANGED_ATTACK) {
                            range = new Range((Integer) closeRangeSpinner.getValue(), (Integer) longRangeSpinner.getValue());
                        } else {
                            range = new Range((Integer) closeRangeSpinner.getValue());
                        }
                        Attack attack = new Attack(combatNameText, descriptionText, (Actions.ActionType) actionType.getSelectedItem(),
                                dice, range, (Integer) targetCountSpinner.getValue(), (Integer) toHitSpinner.getValue(),
                                (Damage) (damageType.getSelectedItem()));
                        targetList.addElement(attack);
                        combatCreatureGenPanel.actions.add(attack);
                    }
                    if(actionType.getSelectedItem() == Actions.ActionType.OTHER){
                        Actions actions = new Actions(combatNameText,descriptionText, Actions.ActionType.OTHER);
                        targetList.addElement(actions);
                        combatCreatureGenPanel.actions.add(actions);
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + targetList.getName());
            }

            parentFrame.setVisible(false);
            parentFrame.dispose();





        }

    }
}
