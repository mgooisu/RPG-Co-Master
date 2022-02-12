package GUI.Elements.Panels;

import Creature.Creature;
import GUI.Encounter.EncounterCreatureList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An expandable information panel for creatures in an encounter. Each creature involved in an encounter has one,
 * including players.
 */
public class CreatureListPanel extends JPanel implements ActionListener, ChangeListener {
    //Data
    Creature creature;

    //GUI Elements
    JPanel mainPanel,centrePanel;
    String idName;
    JLabel acLabel, initiativeLabel;
    JButton deletionButton;
    JSpinner initiativeSpinner, acSpinner;
    JProgressBar healthBar;

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

        // Panel Assembly
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        centrePanel = new JPanel();
        centrePanel.add(initiativeLabel);
        centrePanel.add(initiativeSpinner);
        centrePanel.add(acLabel);
        centrePanel.add(acSpinner);

        mainPanel.add(healthBar,BorderLayout.NORTH);

        mainPanel.add(centrePanel,BorderLayout.CENTER);

        add(mainPanel,BorderLayout.CENTER);
        add(deletionButton,BorderLayout.EAST);


        //ActionListeners
        deletionButton.addActionListener(this);


        setBorder(new TitledBorder(idName));
        setMaximumSize(new Dimension(400,100));



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




    @Override
    public void actionPerformed(ActionEvent e) {
        Component sourceComp = (Component) e.getSource();
        if(sourceComp == deletionButton){
            EncounterCreatureList parentList = (EncounterCreatureList) getParent();
            parentList.removeFromCreatureList(this);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Component sourceComp = (Component) e.getSource();
        EncounterCreatureList parentList = (EncounterCreatureList) getParent();
        creature.setInitiative((Integer) initiativeSpinner.getValue());
        if(sourceComp == initiativeSpinner){
            creature.setInitiative((Integer) initiativeSpinner.getValue());
            if(!parentList.initOrderLocked){
                parentList.initArrange();
            }
        }
    }
}
