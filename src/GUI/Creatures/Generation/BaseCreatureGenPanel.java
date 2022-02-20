package GUI.Creatures.Generation;

import Creature.BaseCreature;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import Exceptions.CreatureException;
import GUI.Elements.DeletableList.DeletableList;
import GUI.Elements.Fields.FocusTextArea;
import GUI.Elements.Fields.FocusTextField;
import GUI.Helpers.ComponentHelpers;
import Helpers.DiceObject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseCreatureGenPanel extends JPanel implements ActionListener {
    //GUI elements
    JPanel namePanel, baseAlignPanel, speciesPanel,acPanel,healthPanel,diceHPValue,speedPanel,
            statParentPanel,statPanel,baseAlignPanelChild, sizePanel, descriptionPanel;
    JLabel labelName;
    FocusTextField textName;
    FocusTextArea description;
    JSpinner acSpinner,integerHP, diceNumHP, diceValHP, diceModHP, speedSpinner,
            strSpinner, dexSpinner,conSpinner,intSpinner,wisSpinner,chaSpinner,
            passivePerceptionSpinner;

    JComboBox<String> speciesBox;
    SpeciesMapObjectHandler speciesMapObjectHandler;
    JCheckBox alignedCheck, combatCheck;
    JComboBox<Size> sizeJComboBox;
    JComboBox<Alignment.Ethic> ethicJComboBox;
    JComboBox<Alignment.Moral> moralJComboBox;

    // Condition/ Damage parameter lists
    ArraySetGui languageGui, senseGui, damageImmunitiesGui,damageResistancesGui,damageVulnerabilitiesGui,
    conditionImmunitiesGui,conditionResistancesGui;

    //Data
    public ArrayList<String> languageArray, sensesArray;
    public ArrayList<Damage> damageImmunities,damageResistances, damageVulnerabilities;

    public ArrayList<Condition> conditionResistances, conditionImmunities;

    HashMap<String, Species> speciesHashMap;

    public BaseCreatureGenPanel() throws IOException, ClassNotFoundException, CreatureException {


//        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(800,1000));
        languageArray =new ArrayList<>(); sensesArray = new ArrayList<>();
        damageImmunities = new ArrayList<>();damageResistances = new ArrayList<>();damageVulnerabilities = new ArrayList<>();
        conditionImmunities = new ArrayList<>(); conditionResistances = new ArrayList<>();





        //Creature Name Panel
        //TODO - Prevent Duplicate Creature Names
        textName = new FocusTextField();

        textName.setBorder(new TitledBorder("Creature \"Class\" name"));
        textName.setColumns(20);

        add(textName);


        //Creature Size
        sizeJComboBox = new JComboBox<>(Size.values());
        sizeJComboBox.setSelectedItem(Size.MEDIUM);
        sizeJComboBox.setBorder(new TitledBorder("Creature Size"));
        add(sizeJComboBox);

        //Is an "Monster"
        JPanel combatCheckPanel = new JPanel();
        combatCheck = new JCheckBox();
        combatCheck.setSelected(true);
        combatCheck.addActionListener(this);
        combatCheckPanel.add(combatCheck);
        combatCheckPanel.setBorder(new TitledBorder("Combat"));
        combatCheckPanel.setPreferredSize(new Dimension(60,50));
        add(combatCheckPanel);

        //Creature Default Alignment

        baseAlignPanel = new JPanel(new GridLayout(1,3));
        alignedCheck = new JCheckBox();
        JPanel alignmentCheckPanel = new JPanel();
        alignmentCheckPanel.add(new JLabel("Unaligned"));
        alignedCheck.addActionListener(this);
        alignmentCheckPanel.add(alignedCheck);
        baseAlignPanel.add(alignmentCheckPanel);

        ethicJComboBox = new JComboBox<>(Alignment.Ethic.values());
        moralJComboBox = new JComboBox<>(Alignment.Moral.values());

        ethicJComboBox.setSelectedIndex(1);
        moralJComboBox.setSelectedIndex(1);

        baseAlignPanel.add(ethicJComboBox);
        baseAlignPanel.add(moralJComboBox);
        baseAlignPanel.setBorder(new TitledBorder("Creature Default Alignment"));
        add(baseAlignPanel);

        //Creature Species Panel

        /*Todo -  This is a terrible way to do data reading.
         * it grabs the information from the file for every element. It should just grab everything
         * at the start ONCE */
        speciesMapObjectHandler = new SpeciesMapObjectHandler();
        speciesHashMap= speciesMapObjectHandler.readObject().getSpeciesHashMap();
        String[] speciesNames = speciesHashMap.keySet().toArray(new String[0]);

        speciesBox = new JComboBox<>(speciesNames);
        speciesBox.setBorder(new TitledBorder("Species"));
        add(speciesBox);

        //AC
        acSpinner = new JSpinner(new SpinnerNumberModel(10,0,100,1));
        acSpinner.setBorder(new TitledBorder("AC"));
        ComponentHelpers.hideSpinnerArrow(acSpinner,60);
        add(acSpinner);

        //HP
        /*Sets the max HP and Dice HP. Either can be used to create instances, but not both*/
        healthPanel = new JPanel();
        healthPanel.setBorder(new TitledBorder("HP"));

        //IntegerHP HP variant for basic number
        JPanel intHPPanel = new JPanel(new GridLayout(2,1));
        integerHP = new JSpinner(new SpinnerNumberModel(30,1,99999,10));
        intHPPanel.add(new JLabel("Health"));
        intHPPanel.add(integerHP);
        healthPanel.add(intHPPanel);

        //Dice HP Variant
        diceHPValue = new JPanel(new GridLayout(2,3));
        diceNumHP = new JSpinner(new SpinnerNumberModel(3,1,100,1));
        diceValHP = new JSpinner(new SpinnerNumberModel(6,2,100,1));
        diceModHP = new JSpinner(new SpinnerNumberModel(0,0,500,1));
        diceHPValue.add(new JLabel("Amount"));
        diceHPValue.add(new JLabel("Value"));
        diceHPValue.add(new JLabel("Modifier"));
        diceHPValue.add(diceNumHP);
        diceHPValue.add(diceValHP);
        diceHPValue.add(diceModHP);
        diceHPValue.setBorder(new TitledBorder("Dice"));

        healthPanel.add(diceHPValue);

        add(healthPanel);

        //Speed Panel
        speedSpinner = new JSpinner(new SpinnerNumberModel(20,0,200,5));
        speedSpinner.setPreferredSize(new Dimension(85,40));
        speedSpinner.setBorder(new TitledBorder("Speed"));
        ComponentHelpers.hideSpinnerArrow(speedSpinner,70);
        add(speedSpinner);

        //Stats Panel

        //Each stat panel
        statPanel= new JPanel(new GridLayout(1,6));
        strSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        dexSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        conSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        intSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        wisSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));
        chaSpinner = new JSpinner(new SpinnerNumberModel(10,0,50,1));

        strSpinner.setBorder(new TitledBorder("STR"));
        dexSpinner.setBorder(new TitledBorder("DEX"));
        conSpinner.setBorder(new TitledBorder("CON"));
        intSpinner.setBorder(new TitledBorder("INT"));
        wisSpinner.setBorder(new TitledBorder("WIS"));
        chaSpinner.setBorder(new TitledBorder("CHA"));


        ComponentHelpers.hideSpinnerArrow(strSpinner,60);
        ComponentHelpers.hideSpinnerArrow(dexSpinner,60);
        ComponentHelpers.hideSpinnerArrow(conSpinner,60);
        ComponentHelpers.hideSpinnerArrow(intSpinner,60);
        ComponentHelpers.hideSpinnerArrow(wisSpinner,60);
        ComponentHelpers.hideSpinnerArrow(chaSpinner,60);




        statPanel.add(strSpinner);
        statPanel.add(dexSpinner);
        statPanel.add(conSpinner);
        statPanel.add(intSpinner);
        statPanel.add(wisSpinner);
        statPanel.add(chaSpinner);
        statPanel.setBorder(new TitledBorder("Default Stats"));

        wisSpinner.addChangeListener(e -> updatePassivePerception());

        add(statPanel);

        //Description

        description = new FocusTextArea();
        description.setPreferredSize(new Dimension(800,100));
        description.setBorder(new TitledBorder("Description"));
        add(description);




        //Damage parameters
        damageImmunitiesGui = new ArraySetGui(this, "Damage Immunities");
        damageResistancesGui = new ArraySetGui(this, "Damage Resistances");
        damageVulnerabilitiesGui = new ArraySetGui(this, "Damage Vulnerabilities");

        add(damageImmunitiesGui);
        add(damageResistancesGui);
        add(damageVulnerabilitiesGui);


        //Conditions Parameters
        conditionImmunitiesGui = new ArraySetGui(this,"Condition Immunities");
        conditionResistancesGui = new ArraySetGui(this,"Condition Resistances");

        add(conditionImmunitiesGui);
        add(conditionResistancesGui);


        //language & Senses
        languageGui = new ArraySetGui(this, "Languages");
        add(languageGui);

        senseGui = new ArraySetGui(this, "Senses");
        add(senseGui);

        //Passive Perception GUI
        passivePerceptionSpinner = new JSpinner(new SpinnerNumberModel(10,0,30,1));
        passivePerceptionSpinner.setBorder(new TitledBorder("Passive Perception"));
        add(passivePerceptionSpinner);

    }
    //Getters
    public Alignment getAlignment(){
        if(ethicJComboBox.isEnabled()){
            Alignment.Ethic ethic = (Alignment.Ethic) ethicJComboBox.getSelectedItem();
            Alignment.Moral moral = (Alignment.Moral) moralJComboBox.getSelectedItem();
            return new Alignment(ethic,moral);
        }
        else {
            return new Alignment(Alignment.CombinedAlignment.UNALIGNED);
        }
    }

    public String getDescription(){
        return description.getText();
    }

    public String getCreatureClass(){
        return textName.getText();
    }

    public int getIntegerHealth(){
        return (Integer) integerHP.getValue();
    }

    public DiceObject getHpDice(){
        return new DiceObject((Integer)diceValHP.getValue(),(Integer)diceNumHP.getValue(),(Integer)diceModHP.getValue());
    }

    public int getAC(){
        return (Integer) acSpinner.getValue();
    }

    public int getSpeed(){
        return (Integer) speedSpinner.getValue();
    }

    public Size getCreatureSize(){
        return (Size) sizeJComboBox.getSelectedItem();
    }

    public Species getSpecies(){
        return speciesHashMap.get((String) speciesBox.getSelectedItem());
    }

    public Stats getStats(){
        Integer strength, dexterity, constitution,intelligence,wisdom, charisma;
        strength = (Integer) strSpinner.getValue();
        dexterity = (Integer) dexSpinner.getValue();
        constitution = (Integer) conSpinner.getValue();
        intelligence = (Integer) intSpinner.getValue();
        wisdom = (Integer) wisSpinner.getValue();
        charisma = (Integer) chaSpinner.getValue();
        return new Stats(strength,dexterity,constitution,intelligence,wisdom,charisma);

    }

    public Condition[] getConditionImmunities(){
        Condition[] conditions = new Condition[conditionImmunities.size()];
        int i = 0;
        for(Condition condition: conditionImmunities){
            conditions[i] = condition;
            i++;
        }
        return conditions;
    }
    public Condition[] getConditionResists(){

        Condition[] conditions = new Condition[conditionResistances.size()];
        int i = 0;
        for(Condition condition: conditionResistances){
            conditions[i] = condition;
            i++;
        }
        return conditions;

    }

    public Damage[] getDamageImmunities(){
        Damage[] damages = new Damage[damageImmunities.size()];
        int i = 0;
        for(Damage damage: damageImmunities){
            damages[i] = damage;
            i++;
        }
        return damages;

    }
    public Damage[] getDamageResistances(){
        Damage[] damages = new Damage[damageResistances.size()];
        int i = 0;
        for(Damage damage: damageResistances){
            damages[i] = damage;
            i++;
        }
        return damages;

    }
    public Damage[] getDamageVulnerabilities(){
        Damage[] damages = new Damage[damageVulnerabilities.size()];
        int i = 0;
        for(Damage damage: damageVulnerabilities){
            damages[i] = damage;
            i++;
        }
        return damages;

    }

    public String[] getLanguages(){
        String[] languages = new String[languageArray.size()];
        int i = 0;
        for(String language: languageArray){
            languages[i] = language;
            i++;
        }
        return languages;

    }

    public String[] getSenses(){
        String[] strings = new String[sensesArray.size()+1];
        strings[0] = "Passive Perception " + passivePerceptionSpinner.getValue();
        int i = 1;
        for(String language: sensesArray){
            strings[i] = language;
            i++;
        }
        return strings;

    }

    public Boolean getCombatCheck(){
        return combatCheck.isSelected();
    }

    //Internal Functions

    /**
     * Calculates a suggested passive perception value from the creature's wisdom and perception skill
     */
    void updatePassivePerception(){
        int passivePerception = Stats.getMod((Integer) wisSpinner.getValue()) + 10;
        passivePerceptionSpinner.setValue(passivePerception);
    }






    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == alignedCheck){
            ethicJComboBox.setEnabled(!ethicJComboBox.isEnabled());
            moralJComboBox.setEnabled(!moralJComboBox.isEnabled());
        }
        //Enables\Disables the enemy creature pane
        if(event.getSource() == combatCheck){
            if(getParent().getClass() == JTabbedPane.class){
                JTabbedPane parentPane = (JTabbedPane) getParent();
                parentPane.setEnabledAt(1,combatCheck.isSelected());

            }
        }
    }
}

/**
 * Private class for generating interface that populates arrays of information for the creature.
 * e.g a creature can speak 5 languages, this class helps the user specify that
 */
class ArraySetGui extends JPanel implements ActionListener{
    JButton addLang, addSense, addDamImmunity,addDamRes,addDamVul, addCondImmunity,addCondRes;
    JPanel editorPanel;
    JComponent parameterEditor;
    DeletableList languageList, sensesList,damageImmunitiesList,damageResistancesList,damageVulnerabilitiesList,
    conditionImmunitiesList,conditionResistancesList;

    String[] arrayEls;


    /**
     * Constructor for the class
     * @param arrayInfo - A string representation of the type of data e.g: "language" or "senses"
     */
    ArraySetGui(BaseCreatureGenPanel baseCreatureGenPanel, String arrayInfo) throws CreatureException {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        editorPanel = new JPanel();
        editorPanel.setLayout(new FlowLayout());
        this.setName(arrayInfo);

        switch (arrayInfo){
            case "Languages"->{
                parameterEditor = new JTextField(10);
                editorPanel.add(parameterEditor);
                addLang = new JButton("Add Language");
                addLang.addActionListener(this);
                editorPanel.add(addLang);
                add(editorPanel);
                // Create the list element
                languageList = new DeletableList("Language List", baseCreatureGenPanel.languageArray, "");
                add(languageList);
            }
            case "Senses"->{
                parameterEditor = new JTextField(10);
                editorPanel.add(parameterEditor);
                addSense = new JButton("Add Sense");
                addSense.addActionListener(this);
                editorPanel.add(addSense);
                add(editorPanel);
                // Create the list element
                sensesList = new DeletableList("Senses List", baseCreatureGenPanel.sensesArray, "");
                add(sensesList);

            }
            case "Damage Immunities"->{
                parameterEditor = new JComboBox<>(Damage.values());
                editorPanel.add(parameterEditor);
                addDamImmunity = new JButton("Add DMG Immunity");
                addDamImmunity.addActionListener(this);
                editorPanel.add(addDamImmunity);
                add(editorPanel);
                arrayEls = new String[baseCreatureGenPanel.damageImmunities.size()];
                int i = 0;
                for(Damage damage: baseCreatureGenPanel.damageImmunities){
                    arrayEls[i] = damage.name();
                    i++;
                }
                // Create the list element
                damageImmunitiesList = new DeletableList("Damage Immunities List", baseCreatureGenPanel.damageImmunities, Damage.ACID);
                add(damageImmunitiesList);

            }
            case "Damage Resistances"->{
                parameterEditor = new JComboBox<>(Damage.values());
                editorPanel.add(parameterEditor);
                addDamRes = new JButton("Add DMG Resistance");
                addDamRes.addActionListener(this);
                editorPanel.add(addDamRes);
                add(editorPanel);
                // Create the list element
                damageResistancesList = new DeletableList("Damage Resistances List", baseCreatureGenPanel.damageResistances, Damage.COLD);
                add(damageResistancesList);

            }
            case "Damage Vulnerabilities"->{
                parameterEditor = new JComboBox<>(Damage.values());
                editorPanel.add(parameterEditor);
                addDamVul = new JButton("Add DMG Vulnerability");
                addDamVul.addActionListener(this);
                editorPanel.add(addDamVul);
                add(editorPanel);
                // Create the list element
                damageVulnerabilitiesList = new DeletableList("Damage Vulnerabilities List", baseCreatureGenPanel.damageVulnerabilities, Damage.FIRE);
                add(damageVulnerabilitiesList);

            }
            case "Condition Immunities" ->{
                parameterEditor = new JComboBox<>(Condition.values());
                editorPanel.add(parameterEditor);
                addCondImmunity = new JButton("Add Condition Immunity");
                addCondImmunity.addActionListener(this);
                editorPanel.add(addCondImmunity);
                add(editorPanel);
                // Create the list element
                conditionImmunitiesList = new DeletableList("Condition Immunities List", baseCreatureGenPanel.conditionImmunities, Condition.CHARMED);
                add(conditionImmunitiesList);
            }
            case "Condition Resistances" ->{
                parameterEditor = new JComboBox<>(Condition.values());
                editorPanel.add(parameterEditor);
                addCondRes = new JButton("Add Condition Resistance");
                addCondRes.addActionListener(this);
                editorPanel.add(addCondRes);
                add(editorPanel);
                // Create the list element
                conditionResistancesList = new DeletableList("Condition Resistances List", baseCreatureGenPanel.conditionResistances, Condition.CHARMED);
                add(conditionResistancesList);

            }
            default -> throw new CreatureException("Not valid Creature info");
        }




        //styling
        setBorder(new TitledBorder(arrayInfo));





    }

    /** @noinspection rawtypes*/
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel parentPanel = (JPanel) (((JButton) e.getSource()).getParent().getParent());
        for(Component c: parentPanel.getComponents()){
            if(c instanceof DeletableList){
                if(editorPanel.getComponent(0) instanceof JTextField){
                    JTextField editor = (JTextField) editorPanel.getComponent(0);
                    ((DeletableList) c).addElement(editor.getText());
                }
                if(editorPanel.getComponent(0) instanceof JComboBox){
                    JComboBox editor = (JComboBox) editorPanel.getComponent(0);
                    ((DeletableList) c).addElement(editor.getSelectedItem());
                }

            }
        }

    }

}