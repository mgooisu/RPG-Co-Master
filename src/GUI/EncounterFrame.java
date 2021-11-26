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
import java.text.NumberFormat;
import java.util.HashMap;

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
            CreatureGenPanel creatureGenPanel = null;
            try {
                creatureGenPanel = new CreatureGenPanel();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (creatureGenPanel != null) {
                creatureGenPanel.setLocationRelativeTo(this);
                creatureGenPanel.setVisible(true);
            }


        }
    }


}
