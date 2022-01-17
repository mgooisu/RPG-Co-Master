package GUI.Elements.Panels;

import Creature.Creature;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * An expandable information panel for creatures in an encounter. Each creature involved in an encounter has one,
 * including players.
 */
public class CreatureListPanel extends JPanel {
    //Data
    Creature creature;

    //GUI Elements
    String idName;
    JLabel acLabel, initiativeLabel;
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

        //Armour Class
        acLabel = new JLabel("AC: ");
        acSpinner = new JSpinner(new SpinnerNumberModel(creature.getAC(),0,30,2));




        // Panel Assembly
        add(initiativeLabel,BorderLayout.CENTER);
        add(initiativeSpinner,BorderLayout.CENTER);
        add(acLabel,BorderLayout.CENTER);
        add(acSpinner,BorderLayout.CENTER);


        setBorder(new TitledBorder(idName));
        setMaximumSize(new Dimension(400,100));



    }
}
