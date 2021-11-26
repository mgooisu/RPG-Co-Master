package GUI;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import Exceptions.CreatureException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class CreatureGenPanel extends JFrame implements ActionListener {
    //GUI elements
    JPanel namePanel, baseAlignPanel, speciesPanel,acPanel,healthPanel,diceHPValue,speedPanel,
            statParentPanel,statPanel,baseAlignPanelChild, sizePanel, descriptionPanel;
    JLabel labelName;
    JTextField textName;
    JTextArea description;
    JSpinner acSpinner,integerHP, diceNumHP, diceValHP, diceModHP, speedSpinner,
            strSpinner, dexSpinner,conSpinner,intSpinner,wisSpinner,chaSpinner;

    JComboBox<String> speciesBox;
    SpeciesMapObjectHandler speciesMapObjectHandler;
    JCheckBox alignedCheck;
    JComboBox<Size> sizeJComboBox;
    JComboBox<Alignment.Ethic> ethicJComboBox;
    JComboBox<Alignment.Moral> moralJComboBox;

    //Data
    public String[] languageArray = {"Common","Dwarfish","Mexican"},
    sensesArray = {"Darkvision 69 feet", "Passive Perception 4"};
    public Damage[] damageImmunities = {Damage.BLUDGEONING, Damage.ACID},
    damageResistances = {}, damageVulnerabilities = {Damage.COLD};

    public Condition[] conditionResistances = {Condition.POISONED},
            conditionImmunities = {Condition.BLINDED};

    CreatureGenPanel() throws IOException, ClassNotFoundException, CreatureException {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));



        //Creature Name Panel
        //TODO - Prevent Duplicate Creature Names
        namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(1,2));
        labelName = new JLabel("Creature \"Class\" name: ");
        textName = new JTextField();



        namePanel.add(labelName);
        namePanel.add(textName);
        namePanel.setPreferredSize(new Dimension(400,25));
        namePanel.setMaximumSize(new Dimension(400,25));
        add(namePanel);


        //Creature Size
        sizePanel = new JPanel(new GridLayout(1,2));
        sizePanel.add(new JLabel("Size: "));
        sizeJComboBox = new JComboBox<>(Size.values());
        sizePanel.add(sizeJComboBox);
        sizeJComboBox.setSelectedItem(Size.MEDIUM);
        add(sizePanel);



        //Creature Default Alignment
        baseAlignPanel = new JPanel();
        baseAlignPanel.setLayout(new GridLayout(1,2));
        baseAlignPanel.add(new JLabel("Base Alignment: "));

        baseAlignPanelChild = new JPanel(new GridLayout(1,3));
        alignedCheck = new JCheckBox();
        baseAlignPanelChild.add(alignedCheck);
        alignedCheck.addActionListener(this);

        ethicJComboBox = new JComboBox<>(Alignment.Ethic.values());
        moralJComboBox = new JComboBox<>(Alignment.Moral.values());
        baseAlignPanelChild.add(ethicJComboBox);
        baseAlignPanelChild.add(moralJComboBox);
        baseAlignPanel.add(baseAlignPanelChild);
        add(baseAlignPanel);

        //Creature Species Panel

        /*Todo -  This is a terrible way to do data reading.
         * it grabs the information from the file for every element. It should just grab everything
         * at the start ONCE */
        speciesMapObjectHandler = new SpeciesMapObjectHandler();
        HashMap<String, Species> speciesHashMap= speciesMapObjectHandler.readObject().getSpeciesHashMap();
        String[] speciesNames = speciesHashMap.keySet().toArray(new String[0]);

        speciesBox = new JComboBox<>(speciesNames);
        speciesPanel = new JPanel();
        speciesPanel.setLayout(new GridLayout(1,2));
        speciesPanel.add(new JLabel("Species:"));
        speciesPanel.add(speciesBox);
        add(speciesPanel);

        //AC
        acPanel = new JPanel(new GridLayout(1,2));
        acPanel.add(new JLabel("AC:"));
        acSpinner = new JSpinner(new SpinnerNumberModel(10,0,100,1));
        acPanel.add(acSpinner);
        add(acPanel);

        //HP
        /*Sets the max HP and Dice HP. Either can be used to create instances, but not both*/
        healthPanel = new JPanel(new GridLayout(1,3));
        healthPanel.add(new JLabel("HP:"));

        //IntegerHP HP variant for basic number
        integerHP = new JSpinner(new SpinnerNumberModel(30,1,99999,10));
        healthPanel.add(integerHP);

        //Dice HP Variant
        diceHPValue = new JPanel(new GridLayout(1,3));
        diceNumHP = new JSpinner(new SpinnerNumberModel(3,1,100,1));
        diceValHP = new JSpinner(new SpinnerNumberModel(6,2,100,1));
        diceModHP = new JSpinner(new SpinnerNumberModel(0,0,500,1));
        diceHPValue.add(diceNumHP);
        diceHPValue.add(diceValHP);
        diceHPValue.add(diceModHP);

        healthPanel.add(diceHPValue);

        add(healthPanel);

        //Speed Panel
        speedPanel = new JPanel(new GridLayout(1,2));
        speedSpinner = new JSpinner(new SpinnerNumberModel(20,0,200,5));
        speedPanel.add(new JLabel("Speed: "));
        speedPanel.add(speedSpinner);

        add(speedPanel);

        //Stats Panel
        statParentPanel = new JPanel(new GridLayout(1,2));
        statParentPanel.add(new JLabel("Stats:"));
        //Each stat panel
        statPanel= new JPanel(new GridLayout(2,6));
        strSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        dexSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        conSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        intSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        wisSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        chaSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        statPanel.add(new JLabel("STR"));
        statPanel.add(new JLabel("DEX"));
        statPanel.add(new JLabel("CON"));
        statPanel.add(new JLabel("INT"));
        statPanel.add(new JLabel("WIS"));
        statPanel.add(new JLabel("CHA"));

        statPanel.add(strSpinner);
        statPanel.add(dexSpinner);
        statPanel.add(conSpinner);
        statPanel.add(intSpinner);
        statPanel.add(wisSpinner);
        statPanel.add(chaSpinner);
        statParentPanel.add(statPanel);

        add(statParentPanel);

        //Description
        descriptionPanel = new JPanel(new GridLayout(1,2));
        descriptionPanel.add(new JLabel("Description:"));
        description = new JTextArea(3,50);
        descriptionPanel.add(description);
        add(descriptionPanel);

        //Damage parameters
        JPanel damageStuff = new JPanel(new GridLayout(1,3));
        ArraySetGui damageImmunities = new ArraySetGui(this, "Damage Immunities");
        ArraySetGui damageResistances = new ArraySetGui(this, "Damage Resistances");
        ArraySetGui damageVulnerabilities = new ArraySetGui(this, "Damage Vulnerabilities");

        damageStuff.add(damageImmunities);
        damageStuff.add(damageResistances);
        damageStuff.add(damageVulnerabilities);

        add(damageStuff);

        //Conditions Parameters
        JPanel conditionStuff = new JPanel(new GridLayout(1,2));
        ArraySetGui conditionImmunities = new ArraySetGui(this,"Condition Immunities");
        ArraySetGui conditionResistances = new ArraySetGui(this,"Condition Resistances");

        conditionStuff.add(conditionImmunities);
        conditionStuff.add(conditionResistances);


        add(conditionStuff);


        //language & Senses
        JPanel langSens = new JPanel(new GridLayout(1,2));
        ArraySetGui languageGui = new ArraySetGui(this, "Languages");
        langSens.add(languageGui);

        ArraySetGui senseGui = new ArraySetGui(this, "Senses");
        langSens.add(senseGui);

        add(langSens);

        // Creature
        pack();
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == alignedCheck){
            ethicJComboBox.setEnabled(!ethicJComboBox.isEnabled());
            moralJComboBox.setEnabled(!moralJComboBox.isEnabled());
        }
    }
}

/**
 * Private class for generating interface that populates arrays of information for the creature.
 * e.g a creature can speak 5 languages, this class helps the user specify that
 */
class ArraySetGui extends JPanel implements ActionListener{
    JButton addButton;
    JPanel editorPanel, listPanel;
    JComponent parameterEditor;

    String[] arrayEls;


    /**
     * Constructor for the class
     * @param arrayInfo - A string representation of the type of data e.g: "language" or "senses"
     */
    ArraySetGui(CreatureGenPanel creatureGenPanel, String arrayInfo) throws CreatureException {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addButton = new JButton("add");
        editorPanel = new JPanel();
        editorPanel.setLayout(new FlowLayout());

        switch (arrayInfo){
            case "Languages"->{
                parameterEditor = new JTextField(10);
                editorPanel.add(parameterEditor);
                editorPanel.add(addButton);
                add(editorPanel);
                arrayEls = creatureGenPanel.languageArray;
            }
            case "Senses"->{
                parameterEditor = new JTextField(10);
                editorPanel.add(parameterEditor);
                editorPanel.add(addButton);
                add(editorPanel);
                arrayEls = creatureGenPanel.sensesArray;
            }
            case "Damage Immunities"->{
                parameterEditor = new JComboBox<>(Damage.values());
                editorPanel.add(parameterEditor);
                editorPanel.add(addButton);
                add(editorPanel);
                String[] damageArrToStringArr =new String[creatureGenPanel.damageImmunities.length];
                for(int i = 0; i< damageArrToStringArr.length;i++) {
                    damageArrToStringArr[i] = creatureGenPanel.damageImmunities[i].toString();
                }
                arrayEls = damageArrToStringArr;
            }
            case "Damage Resistances"->{
                parameterEditor = new JComboBox<>(Damage.values());
                editorPanel.add(parameterEditor);
                editorPanel.add(addButton);
                add(editorPanel);
                String[] damageArrToStringArr =new String[creatureGenPanel.damageResistances.length];
                for(int i = 0; i< damageArrToStringArr.length;i++) {
                    damageArrToStringArr[i] = creatureGenPanel.damageResistances[i].toString();
                }
                arrayEls = damageArrToStringArr;
            }
            case "Damage Vulnerabilities"->{
                parameterEditor = new JComboBox<>(Damage.values());
                editorPanel.add(parameterEditor);
                editorPanel.add(addButton);
                add(editorPanel);
                String[] damageArrToStringArr =new String[creatureGenPanel.damageVulnerabilities.length];
                for(int i = 0; i< damageArrToStringArr.length;i++) {
                    damageArrToStringArr[i] = creatureGenPanel.damageVulnerabilities[i].toString();
                }
                arrayEls = damageArrToStringArr;
            }
            case "Condition Immunities" ->{
                parameterEditor = new JComboBox<>(Condition.values());
                editorPanel.add(parameterEditor);
                editorPanel.add(addButton);
                add(editorPanel);
                String[] conditionArrToStringArr =new String[creatureGenPanel.conditionImmunities.length];
                for(int i = 0; i< conditionArrToStringArr.length;i++) {
                    conditionArrToStringArr[i] = creatureGenPanel.conditionImmunities[i].toString();
                }
                arrayEls = conditionArrToStringArr;
            }
            case "Condition Resistances" ->{
                parameterEditor = new JComboBox<>(Condition.values());
                editorPanel.add(parameterEditor);
                editorPanel.add(addButton);
                add(editorPanel);
                String[] conditionArrToStringArr =new String[creatureGenPanel.conditionResistances.length];
                for(int i = 0; i< conditionArrToStringArr.length;i++) {
                    conditionArrToStringArr[i] = creatureGenPanel.conditionResistances[i].toString();
                }
                arrayEls = conditionArrToStringArr;
            }
            default -> throw new CreatureException("Not valid Creature info");
        }

        //Creates the Panel with the existing values for this info type

        for(String element : arrayEls){
            JPanel elementPanel = new JPanel();
            elementPanel.add(new JLabel(element));
            elementPanel.add(new Button("X"));
            add(elementPanel);
        }

        //styling
        setBorder(new TitledBorder(arrayInfo));





    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}