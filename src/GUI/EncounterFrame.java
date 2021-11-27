package GUI;

import Exceptions.CreatureException;
import GUI.Creatures.BaseCreatureGenPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Lowest scope panel for creating creatures and managing them in an encounter
 */
public class EncounterFrame extends JFrame implements ActionListener {
    JMenuBar encounterMenuBar;

    JMenuItem createCreature, addCreature;

    EncounterFrame(){


        encounterMenuBar = new JMenuBar();
        JMenu encounterFileMenu = new JMenu("File");
        JMenu encounterAddMenu = new JMenu("Add");
        JMenu encounterOptionsMenu = new JMenu("Options");

        encounterMenuBar.add(encounterFileMenu);
        encounterMenuBar.add(encounterAddMenu);
        encounterMenuBar.add(encounterOptionsMenu);

        createCreature = new JMenuItem("Create Creature");
        createCreature.addActionListener(this);
        addCreature = new JMenuItem("Add Creature to Encounter");
        addCreature.addActionListener(this);





        encounterFileMenu.add(new JMenu("wa"));

        encounterAddMenu.add(createCreature);
        encounterAddMenu.add(addCreature);

        setJMenuBar(encounterMenuBar);

        add(new JButton("test"));

        setMinimumSize(new Dimension(400,400));
        pack();


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Component source = (Component) actionEvent.getSource();

        if(source.equals(addCreature)){
            System.out.println("wa");
        }
        if(source.equals(createCreature)){
            BaseCreatureGenPanel baseCreatureGenPanel = null;
            try {
                baseCreatureGenPanel = new BaseCreatureGenPanel();
            } catch (IOException | ClassNotFoundException | CreatureException e) {
                e.printStackTrace();
            }
            if (baseCreatureGenPanel != null) {
                baseCreatureGenPanel.setVisible(true);
            }


        }
    }


}
