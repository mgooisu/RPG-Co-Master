package GUI.Creatures;

import Creature.Actions.Actions;
import Creature.Actions.Attack;
import Creature.Actions.MonsterAction;
import Creature.Actions.Range;
import Creature.Creature;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import Creature.Monster;
import Exceptions.CreatureException;
import GUI.Elements.Panels.CreatureListPanel;
import GUI.Helpers.ComponentHelpers;
import Helpers.DiceObject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;

import GUI.Helpers.JTextArea;

/**
 * Panel that contains information and controls pertaining to a single creature
 */
public class CreaturePanel extends JPanel  {

CreatureListPanel creatureListPanel;
Creature creature;
    public CreaturePanel(CreatureListPanel creatureListPanel){
        this.creatureListPanel = creatureListPanel;
        this.creature = creatureListPanel.getCreature();
        setLayout(new BorderLayout());
        JPanel title = new JPanel();
        //TODO rename creature interface
        if(creature.getName() == null){
            title.add(new JLabel("<html><h1 style='text-align:center'>"+creature.getCreatureClass()+"</h1></html> "));
        }else {
            title.add(new JLabel("<html><h1 style='text-align:center'>"+creature.getName()+":<br>"+creature.getCreatureClass()+"</h1></html> "));
        }
        ComponentHelpers.centerLabelsInPanel(title);
        add(title, BorderLayout.PAGE_START);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));

        content.add(new InitiativePanel());

        content.add(new healthDisplay());

        content.add(new creatureInfoPanel());



        add(content,BorderLayout.CENTER);
        add(new JSeparator(),BorderLayout.SOUTH);
    }

    public Creature getCreature(){
        return creature;
    }

    class healthDisplay extends JPanel{
        JProgressBar healthBar;
        //Todo - health set context menu
        healthDisplay(){
            setBorder(new TitledBorder("Health"));
            setLayout(new BorderLayout());
            healthBar = new JProgressBar();
            healthBar.setMaximum(creature.getMaxHP());
            healthBar.setValue(creature.getHealth());
            healthBar.setStringPainted(true);
            healthBar.setString(creature.getHealth()+"/"+creature.getMaxHP());
            add(healthBar,BorderLayout.NORTH);
            add(new DamageHealingButtons(),BorderLayout.SOUTH);
        }


    /**
     * Test Buttons for health Display
     */
    class DamageHealingButtons extends JPanel implements ActionListener {
        JButton DamageButton, HealButton;
        JSpinner numberIn;
        DamageHealingButtons(){
            setLayout(new GridLayout(1,3));

            numberIn = new JSpinner(new SpinnerNumberModel(5,1,999,1));
            HealButton = new JButton("Heal");
            DamageButton = new JButton("Damage");

            HealButton.addActionListener(this);
            DamageButton.addActionListener(this);

            add(numberIn);
            add(DamageButton);
            add(HealButton);

        }


        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            Component source = (Component) actionEvent.getSource();

            if(source.equals(HealButton)||source.equals((DamageButton))){

                if (source.equals(HealButton)) {
                    creature.healthHealing((Integer) numberIn.getValue());

                }
                if (source.equals(DamageButton)) {
                    creature.healthDamage((Integer) numberIn.getValue());
                }
                int HP = creature.getHealth(), maxHP = creature.getMaxHP();
                healthBar.setValue(HP);
                healthBar.setString(HP+"/"+maxHP);
                creatureListPanel.healthBar.setValue(HP);
                creatureListPanel.healthBar.setString(HP+"/"+maxHP);
                creatureListPanel.healthBar.repaint();
            }

        }

        }
    }

    class InitiativePanel extends JPanel implements ActionListener{
        JButton rollInitiative, setInitiative;
        JSpinner initiativeSpinner;
        JLabel initiativeValue;
        InitiativePanel(){
            setBorder(new TitledBorder("Initiative"));
            setLayout(new BoxLayout(this,BoxLayout.X_AXIS));


            int initiative = creature.getInitiative();
            initiativeValue = new JLabel(String.valueOf(initiative));
            initiativeValue.setBorder(new StrokeBorder(new BasicStroke(BasicStroke.CAP_SQUARE)));

            rollInitiative = new JButton("Roll Initiative");
            rollInitiative.addActionListener(this);

            //Sub-panel for manually setting initiative
            int overallHeight = 30, overallWidth = 120;
            JPanel initiativeSetPanel = new JPanel();

            initiativeSetPanel.setLayout(new BoxLayout(initiativeSetPanel,BoxLayout.X_AXIS));

            initiativeSpinner = new JSpinner(new SpinnerNumberModel(10,-4,30,1));
            initiativeSetPanel.add(initiativeSpinner);
            initiativeSetPanel.setAlignmentY(CENTER_ALIGNMENT);
            Dimension setSpinnerSize= new Dimension(overallWidth,overallHeight/2);
            initiativeSpinner.setMaximumSize(setSpinnerSize);
            initiativeSpinner.setSize(setSpinnerSize);

            setInitiative = new JButton("Set");
            setInitiative.addActionListener(this);
            Dimension setButtonDims = new Dimension(overallWidth/2,overallHeight/2);
            setInitiative.setMinimumSize(setButtonDims);


            Dimension initSetPanelSize = new Dimension(overallWidth,overallHeight);
            initiativeSetPanel.setSize(initSetPanelSize);
            initiativeSetPanel.setMaximumSize(initSetPanelSize);
            initiativeSetPanel.add(setInitiative);

           initiativeSetPanel.setBorder(new EtchedBorder());


            Dimension elementSpacing = new Dimension(10,overallHeight);

            add(initiativeValue);
            add(Box.createRigidArea(elementSpacing));
            add(initiativeSetPanel);
            add(Box.createRigidArea(elementSpacing));
            add(rollInitiative);



        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Component source = (Component) actionEvent.getSource();


            if(source.equals(rollInitiative)){
                creature.setInitiative(1+creature.getStats().getDexterityMod()+ (int)(Math.random()*20));
                initiativeValue.setText(String.valueOf(creature.getInitiative()));
                creatureListPanel.updateInitiative();
            }

            if(source.equals(setInitiative)){
                creature.setInitiative((int) initiativeSpinner.getValue());
                initiativeValue.setText(String.valueOf(creature.getInitiative()));
                creatureListPanel.updateInitiative();

            }

        }
    }

    class creatureInfoPanel extends JPanel {
        JSpinner armourSpinner, speedSpinner;

        creatureInfoPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            //TODO - Rework creature panel Numbers and Conditions
            /*
            What the heck was I thinking here? Why are the datatype arranged into nested arrays?
            This needs to be simplified, as it stands its just begging for errors.
             */
            String[] damageInfoNames = {"Immunities", "Resistances", "Vulnerabilities"},
                    conditionInfoNames = {"Immunities", "Resistances"};


            // Number Based stats first, essentially speed and AC (Health has its own special interface)
            JPanel numberInfo = new JPanel();
            numberInfo.setBorder(new TitledBorder("Combat Stats"));
            armourSpinner = new JSpinner(new SpinnerNumberModel(creature.getAC(),0,99,1));
            armourSpinner.setBorder(new TitledBorder("AC"));

            speedSpinner = new JSpinner(new SpinnerNumberModel(creature.getSpeed(),0,200,5));
            speedSpinner.setBorder(new TitledBorder("Speed"));

            numberInfo.add(speedSpinner);
            numberInfo.add(armourSpinner);

            add(numberInfo);

            //Condition status
            //Todo - Seperate into subpanels for easier manipulation
            JPanel conditionInfoPanel = new JPanel();
            conditionInfoPanel.setBorder(new TitledBorder("Conditions"));
            conditionInfoPanel.setLayout(new GridLayout(3, 2));
            // The creature's current conditions
            conditionInfoPanel.add(new JLabel("Current"));
            JTextArea currentConditionPane = new JTextArea();

            conditionInfoPanel.add(currentConditionPane);
            //How the creature reacts to certain conditions - eg. resists
            for (int i = 0; i < conditionInfoNames.length; i++) {
                conditionInfoPanel.add(new JLabel(conditionInfoNames[i]));
                JTextArea conditionPane = new JTextArea();

                conditionPane.setBorder(new EtchedBorder());
                currentConditionPane.setBorder(new EtchedBorder());
                conditionInfoPanel.add(conditionPane);

            }
            add(conditionInfoPanel);

            //TODO display for conditions
            System.out.println("Conditions Resistances "+ Arrays.toString(creature.getConditionResistances()));
            System.out.println("Conditions Immunities " + Arrays.toString(creature.getConditionImmunity()));

            //Damage reaction panel
            JPanel damagePanel = new JPanel();
            damagePanel.setBorder(new TitledBorder("Damage"));
            damagePanel.setLayout(new BoxLayout(damagePanel, BoxLayout.Y_AXIS));

            JPanel[] damageSubPanels = new JPanel[3];
            for (int i = 0; i < damageSubPanels.length; i++) {
                damageSubPanels[i] = new JPanel();
                damageSubPanels[i].setLayout(new GridLayout(1, 2));
                damageSubPanels[i].add(new JLabel(damageInfoNames[i]));

                JTextArea damageText = new JTextArea();
                damageText.setBorder(new EtchedBorder());

                damageSubPanels[i].add(damageText);
            }
            for (JPanel subPanel : damageSubPanels) {
                damagePanel.add(subPanel);
            }
            add(damagePanel);
            //TODO display for damages
            System.out.println("Damage Vulnerabilities "+ Arrays.toString(creature.getVulnerabilities()));

            System.out.println("Damage Resistances "+ Arrays.toString(creature.getResistances()));
            System.out.println("Damage Immunities " + Arrays.toString(creature.getImmunities()));

            //Stats block
            //TODO make stats changeable - not all orcs are the same intelligence
            JPanel statPanel = new JPanel();
            statPanel.setBorder(new TitledBorder("Abilities"));
            statPanel.setLayout(new GridLayout(1,6));
            Stats creatureStats = creature.getStats();
            int[] creatureStatValues = {creatureStats.getStrength(),creatureStats.getDexterity(),
                creatureStats.getConstitution(),creatureStats.getIntelligence(),creatureStats.getWisdom(),
                creatureStats.getCharisma()};

            int[] creatureModValues = {creatureStats.getStrengthMod(),creatureStats.getDexterityMod(),
                    creatureStats.getConstitutionMod(),creatureStats.getIntelligenceMod(),creatureStats.getWisdomMod(),
                    creatureStats.getCharismaMod()};

            String[] statShorthands = {"Str","Dex","Con","Int","Wis","Cha"};
            for(int i = 0; i< statShorthands.length;i++){
                JPanel thisStat = new JPanel();
                thisStat.setLayout(new GridLayout(2,1));
                thisStat.setBorder(new TitledBorder(statShorthands[i]));

                thisStat.add(new JLabel(String.valueOf(creatureStatValues[i])));
                if(creatureModValues[i]>0){
                    thisStat.add(new JLabel("+"+creatureModValues[i]));
                }
                else
                    thisStat.add(new JLabel(String.valueOf(creatureModValues[i])));

                statPanel.add(thisStat);
            }

            add(statPanel);


            //Creature Actions - monsters only
            if(creature.getClass().equals(Monster.class)){
                JPanel monsterActionsPanel = new JPanel();
                monsterActionsPanel.setBorder(new TitledBorder("Actions"));

                for(Actions action: ((Monster)creature).getActions()){
                    System.out.println(action.getName());
                }

            }

            //Miscellaneous information - stuff like description, language, behaviour, loot, user notes

            JPanel misc = new JPanel();
            misc.setLayout(new BoxLayout(misc, BoxLayout.Y_AXIS));
            misc.setBorder(new TitledBorder("Information"));


            JTextArea classification = new JTextArea(creature.getAlignment().getEthic().name()+" "+
                    creature.getAlignment().getMoral().name()+" "+
                    creature.getSize()+" "+creature.getSpecies().getName());



            JTextArea description = new JTextArea(creature.getDescription());
            description.setBorder(new TitledBorder("Description"));


            // temp size Exhaustion
            //todo Proficiencies
            //todo Passive Perception automation as a distinct integer, rather than a string reference in senses
            JTextArea languages = new JTextArea();
            languages.setBorder(new TitledBorder("Languages"));
            languages.setLineWrap(true);
            languages.setWrapStyleWord(true);
            System.out.println(Arrays.toString(creature.getLanguages()));
            if(creature.getLanguages() != null) {



                String[] creatureLang = creature.getLanguages();
                for (int i = 0; i < creatureLang.length; i++) {
                    languages.append(creatureLang[i]);
                    if (i < creatureLang.length - 1)
                        languages.append(", ");
                }
            }

            //senses
            JTextArea senses = new JTextArea();
            senses.setBorder(new TitledBorder("Senses"));
            senses.setLineWrap(true);
            System.out.println("Senses:" + Arrays.toString(creature.getSenses()));
            if(creature.getSenses()!=null){
                String[] creatureSenses = creature.getSenses();
                for(int i = 0; i<creatureSenses.length;i++) {
                    senses.append(creatureSenses[i]);
                    if(i< creatureSenses.length-1)
                        senses.append(", ");
                }
            }

            //ChallengeRating
            JTextArea challenge = new JTextArea();
            challenge.setBorder(new TitledBorder("Challenge"));
            challenge.setLineWrap(true);
            challenge.setText("TODO - storage of the species CR and XP in the species object");


            //Loot
            JTextArea loot = new JTextArea();
            loot.setBorder(new TitledBorder("Loot"));
            loot.setLineWrap(true); loot.setWrapStyleWord(true);
            loot.setText("TODO - storage of possible loot as string arraylists in Monster classes");

            //Notes
            JTextArea notes = new JTextArea();
            notes.setBorder(new TitledBorder("DM notes"));
            notes.setLineWrap(true); notes.setWrapStyleWord(true);
            notes.setText("TODO - editable string stored in the specific monster object, to be saved on focus loss," +
                    "window closing and when pressing enter");
            notes.setEditable(true);
            notes.setOpaque(true);


            misc.add(classification);
            misc.add(new JSeparator());
            misc.add(description);
            misc.add(languages);
            misc.add(senses);
            misc.add(challenge);
            misc.add(loot);
            misc.add(notes);

            add(misc);
            validate();

            //Experience

        }










    }
}


