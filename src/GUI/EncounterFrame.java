package GUI;

import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

/**
 * Lowest scope panel for creating creatures and managing them in an encounter
 */
public class EncounterFrame extends JFrame implements ActionListener {
    JMenuBar encounterMenuBar;

    JMenuItem createCreature, addCreature;
    CreatureGenPanel creatureGenPanel;
    EncounterFrame() throws IOException, ClassNotFoundException {


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

        creatureGenPanel = new CreatureGenPanel();
        creatureGenPanel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Component source = (Component) actionEvent.getSource();

        if(source.equals(addCreature)){
            System.out.println("wa");
        }
        if(source.equals(createCreature)){
            creatureGenPanel.setLocationRelativeTo(this);
            creatureGenPanel.setVisible(true);
        }
    }

    class CreatureGenPanel extends JFrame{
        JLabel labelName, labelSpecies;
        JTextField textName;
        JComboBox<String> speciesBox;
        SpeciesMapObjectHandler speciesMapObjectHandler;
        CreatureGenPanel() throws IOException, ClassNotFoundException {
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));



            //Creature Name Panel
            JPanel namePanel = new JPanel();
            namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.X_AXIS));
            namePanel.setLayout(new GridLayout(1,2));
            labelName = new JLabel("Creature Name: ");
            textName = new JTextField();


            namePanel.add(labelName);
            namePanel.add(textName);
            namePanel.setPreferredSize(new Dimension(400,25));
            namePanel.setMaximumSize(new Dimension(400,25));
            add(namePanel);


            //Creature Species Panel

            //Todo temporary species allocation - raise scope for other uses without execcive polling
            speciesMapObjectHandler = new SpeciesMapObjectHandler();
            HashMap<String,Species> speciesHashMap= speciesMapObjectHandler.readObject().getSpeciesHashMap();
            String[] speciesNames = speciesHashMap.keySet().toArray(new String[0]);

            speciesBox = new JComboBox<>(speciesNames);
            add(speciesBox);




            pack();
        }


    }
}
