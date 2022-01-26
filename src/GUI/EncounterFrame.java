package GUI;

import Creature.Creature;
import Creature.CreatureListHandler;
import Creature.CreatureMap;
import Exceptions.CreatureException;
import GUI.Creatures.BaseCreatureGenPanel;
import GUI.Creatures.CreatureGenFrame;
import GUI.Creatures.CreaturePanel;
import GUI.Elements.Panels.CreatureListPanel;
import GUI.Encounter.EncounterCreatureList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Lowest scope panel for creating creatures and managing them in an encounter
 */
public class EncounterFrame extends JFrame implements ActionListener {
    JMenuBar encounterMenuBar;

    JMenuItem createCreature, addCreature;

    JPanel topPanel;

    EncounterCreatureList creatureList;

    JScrollPane creatureScrollPane;

    JButton creatureInitRollsButton;


    //todo remove test button
    JButton testButton = new JButton("Print info");

    EncounterFrame(){

        // Menu Bar
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

        // Creature Scrollbar


        //Todo - Adding Creatures
        // Todo - Removing Creatures
        //Todo - Inititative Implies sort
        creatureList = new EncounterCreatureList();
        creatureList.setLayout(new BoxLayout(creatureList,BoxLayout.Y_AXIS));


        creatureScrollPane = new JScrollPane(creatureList);
        creatureScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Top pane - Operations that impact all creatures in the encounter
        topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(200,50));
        creatureInitRollsButton = new JButton("Roll Initiatives");
        creatureInitRollsButton.addActionListener(this);

        topPanel.add(creatureInitRollsButton,BorderLayout.PAGE_START);

        creatureList.add(topPanel);

        //TODO test structure - to be deleted when adding creatures implemented
        try {
            CreatureListHandler creatureListHandler = new CreatureListHandler();
            HashMap<String,Creature> creatureHashMap = creatureListHandler.readObject().getCreatureHashMap();
            creatureList.initOrderLocked = true;
            for(Creature creature: creatureHashMap.values()){
                creatureList.addToCreatureList(new CreatureListPanel(creature));
            }
            creatureList.initOrderLocked = false;
            creatureList.initArrange();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



        //Element adding

        add(creatureScrollPane,BorderLayout.LINE_START);

        setMinimumSize(new Dimension(800,500));
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Component source = (Component) actionEvent.getSource();

        if(source.equals(addCreature)){
            System.out.println("wa");
        }
        if(source.equals(createCreature)){
            CreatureGenFrame creatureGenFrame = null;
            try {
                creatureGenFrame = new CreatureGenFrame();
            } catch (IOException | ClassNotFoundException | CreatureException e) {
                e.printStackTrace();
            }
            if (creatureGenFrame != null) {
                creatureGenFrame.setVisible(true);
                creatureGenFrame.pack();
                creatureGenFrame.setLocationRelativeTo(this);
            }


        }
        if(source.equals(creatureInitRollsButton)){
            creatureList.rollInitiatives();

        }
        if(source.equals(testButton)){
           printCreaturesInDatabase();
        }
    }




    //Debugging Programs

    void printCreaturesInDatabase(){
        CreatureListHandler creatureListHandler;
        HashMap<String, Creature> creatureHashMap;

        System.out.println("Debug Program : \n Saved Creatures: ");
        try {
            creatureListHandler = new CreatureListHandler();
            creatureHashMap = creatureListHandler.readObject().getCreatureHashMap();
            if(creatureHashMap.keySet().isEmpty()){
                System.out.println("No Saved Creatures :(");
            }
            for(Creature creature : creatureHashMap.values()){
                System.out.println(creature.getCreatureClass());
                System.out.println(creature.getDescription());
                System.out.println(creature.getSpecies().getName());
            }




        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



        System.out.println("------------------------------");
    }


}
