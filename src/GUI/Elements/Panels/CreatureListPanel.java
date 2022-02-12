package GUI.Elements.Panels;

import Creature.Creature;
import GUI.Creatures.CreaturePanel;
import GUI.Encounter.EncounterCreatureList;
import GUI.Helpers.PopClickListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * An expandable information panel for creatures in an encounter. Each creature involved in an encounter has one,
 * including players.
 */
public class CreatureListPanel extends JPanel implements ActionListener, ChangeListener {
    //Data
    Creature creature;

    //GUI Elements
    JPanel mainPanel,centrePanel, damageHealPanel;
    InfoPanel infoPanel;
    String idName;
    JLabel acLabel, initiativeLabel;
    JButton deletionButton, damageHealButton;
    JSpinner initiativeSpinner, acSpinner, damageHealSpinner;
    public JProgressBar healthBar;
    JMenuItem fullPanel;
    JFrame expandedCreatureMenu;

    public CreatureListPanel(Creature creature){
        this.creature = creature;
        //todo - Counter for unnamed monsters . e.g: skeleton 1, skeleton 2, cultist 1, cultist 2
        //Title Label
        if(creature.getName() == null){
            idName = creature.getCreatureClass();
        }
        else {
            idName = creature.getName();
        }




        //initiative Counter
        //TODO - change order of panels
        initiativeLabel = new JLabel("Init: ");
        initiativeSpinner = new JSpinner(new SpinnerNumberModel(creature.getInitiative(),-10,30,1));
        initiativeSpinner.addChangeListener(this);

        //Armour Class
        acLabel = new JLabel("AC: ");
        acSpinner = new JSpinner(new SpinnerNumberModel(creature.getAC(),0,30,2));

        //Deletion Button
        deletionButton = new JButton("X");

        //Health Bar
        healthBar = new JProgressBar();
        healthBar.setMaximum(creature.getMaxHP());
        healthBar.setValue(creature.getHealth());
        healthBar.setStringPainted(true);
        healthBar.setString(creature.getHealth()+"/"+creature.getMaxHP());




        //Damage | Heal Interface
        damageHealPanel = new JPanel();
        damageHealSpinner = new JSpinner(new SpinnerNumberModel(0,-999,999,5));
        damageHealButton = new JButton("-");


        damageHealPanel.setLayout(new BorderLayout());
        damageHealPanel.add(damageHealSpinner,BorderLayout.WEST);
        damageHealPanel.add(damageHealButton,BorderLayout.CENTER);
        // Info Panel
        infoPanel = new InfoPanel();

        // Panel Assembly
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        centrePanel = new JPanel();
        centrePanel.add(initiativeLabel);
        centrePanel.add(initiativeSpinner);
        centrePanel.add(acLabel);
        centrePanel.add(acSpinner);
        centrePanel.add(damageHealPanel);

        mainPanel.add(healthBar,BorderLayout.NORTH);

        mainPanel.add(centrePanel,BorderLayout.CENTER);

        mainPanel.add(infoPanel,BorderLayout.SOUTH);

        add(mainPanel,BorderLayout.CENTER);
        add(deletionButton,BorderLayout.EAST);


        //ActionListeners
        deletionButton.addActionListener(this);
        damageHealSpinner.addChangeListener(this);
        damageHealButton.addActionListener(this);
        //Right click menu setup
        fullPanel = new JMenuItem("Show Full Creature Panel");
        fullPanel.addActionListener(this);
        JMenuItem[] jMenuItems = new JMenuItem[]{fullPanel,new JMenuItem("aw")};
        //Setup
        addMouseListener(new PopClickListener(jMenuItems));
        setBorder(new TitledBorder(idName));
        setMaximumSize(new Dimension(500,150));
        repaint();
        revalidate();



    }

    public void setCreature(Creature creature){
        if(creature.getName() == null){
            idName = creature.getCreatureClass();
        }
        else {
            idName = creature.getName();
        }
        this.creature = creature;
        initiativeSpinner.setValue(creature.getInitiative());
        setBorder(new TitledBorder(idName));
    }

    public Creature getCreature(){
        return creature;
    }

    public void rollInitiative(){
        creature.rollInitiative();
        initiativeSpinner.setValue(creature.getInitiative());
        repaint();
        revalidate();
    }

    public int getInitiative(){
        creature.setInitiative((Integer) initiativeSpinner.getValue());
        return (int) initiativeSpinner.getValue();

    }

    public void updateInitiative(){
        initiativeSpinner.setValue(creature.getInitiative());
        System.out.println(creature.getInitiative());
        initiativeSpinner.repaint();
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        Component sourceComp = (Component) e.getSource();

        if (sourceComp == fullPanel) {
            expandedCreatureMenu = new JFrame();
            expandedCreatureMenu.add(new CreaturePanel(this));
            expandedCreatureMenu.setVisible(true);
            expandedCreatureMenu.pack();
        }

            if (sourceComp == damageHealButton) {
                int damageVal = (int) damageHealSpinner.getValue();
                if (damageVal > 0) {
                    creature.healthDamage(damageVal);
                }
                if (damageVal < 0) {
                    creature.healthHealing(damageVal * -1);
                }
                healthBar.setValue(creature.getHealth());
                healthBar.setString(creature.getHealth() + "/" + creature.getMaxHP());
                healthBar.repaint();
                healthBar.revalidate();
            }

            if (sourceComp == deletionButton) {
                EncounterCreatureList parentList = (EncounterCreatureList) getParent();
                parentList.removeFromCreatureList(this);
            }
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        Component sourceComp = (Component) e.getSource();
        EncounterCreatureList parentList = (EncounterCreatureList) getParent();
        creature.setInitiative((Integer) initiativeSpinner.getValue());

        if(sourceComp == damageHealSpinner){
            //TODO replace +- symbols for damage heal symbols
            int damageVal = (int) damageHealSpinner.getValue();
            if(damageVal< 0 && damageHealButton.getText().equals("-")){
                damageHealButton.setText("+");
                damageHealButton.repaint();
            }
            if(damageVal> 0 && damageHealButton.getText().equals("+")){
                damageHealButton.setText("-");
                damageHealButton.repaint();
            }
        }



        if(sourceComp == initiativeSpinner){
            creature.setInitiative((Integer) initiativeSpinner.getValue());
            if(!parentList.initOrderLocked){
                parentList.initArrange();
            }
        }
    }
}

/**
 * Panel that concisely shows the conditions, weaknesses, and strength of the creature using icons
 */
class InfoPanel extends JPanel{
    public InfoPanel(){
       add(new JLabel("Todo - panel that shows the conditions, weaknesses, and strengths"));
    }
}
