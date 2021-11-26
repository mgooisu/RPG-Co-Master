package GUI;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class CreatureGenPanel extends JFrame implements ActionListener {
    JPanel namePanel, baseAlignPanel, speciesPanel,acPanel,healthPanel,diceHPValue,speedPanel,
            statParentPanel,statPanel,baseAlignPanelChild, sizePanel, descriptionPanel,
            languagePanel, sensesPanel, damImmunitiesPanel,damResPanel,damVulPanel,
            condResPanel,condImmunityPanel;
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

    CreatureGenPanel() throws IOException, ClassNotFoundException {
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

        //Senses






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


class arraySetGui extends JFrame implements ActionListener{
    JPanel mainPanel;
    JButton addButton;
    JList<String> arrayEls;

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}