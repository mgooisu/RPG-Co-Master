package GUI.Encounter;

import Creature.Creature;
import Creature.CreatureListHandler;
import Encounter.Encounter;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class for importing, creating and deleting creatures from the creature file
 */
public class EncounterCreatureGui extends JPanel implements ActionListener {
    //TODO species filtering and searchbar
    CreatureListHandler creatureListHandler;
    HashMap<String, Creature> rootCreatures;
    ArrayList<JPanel> rootCreaturePanels;
    JScrollPane scrollPane;
    JPanel scrollablePanel;
    Encounter encounter;
    public EncounterCreatureGui(Encounter encounter) throws IOException, ClassNotFoundException {
        setLayout(new BorderLayout());
        scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));


        //data
        rootCreaturePanels = new ArrayList<>();
        this.encounter = encounter;
        creatureListHandler = new CreatureListHandler();
        rootCreatures = creatureListHandler.readObject().getCreatureHashMap();
        if(rootCreatures.keySet().isEmpty()){
            add(new JLabel("The creature list is empty. Either import a .dnd file containing a valid creature" +
                    "list or add new creatures to the list via the creature creation tool under add->create creature"));
            return;
        }
        //Create a new root creature panel for every creature on file
        for(String key: rootCreatures.keySet()){
            JPanel rootCreaturePanel = new JPanel();
            rootCreaturePanel.setName(key);
            rootCreaturePanel.add(new JLabel(key));
            rootCreaturePanel.setBorder(new EtchedBorder());
            AddButton addButton = new AddButton("Add",rootCreatures.get(key));
            addButton.addActionListener(this);
            rootCreaturePanel.add(addButton);

            DeleteButton deleteButton = new DeleteButton("Delete", rootCreatures.get(key));
            rootCreaturePanel.add(deleteButton);

            rootCreaturePanels.add(rootCreaturePanel);
        }

        scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Add creature panels to this panel
        for(JPanel jPanel : rootCreaturePanels){
            scrollablePanel.add(jPanel);
        }
        add(scrollPane,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass() == AddButton.class){
            AddButton clickedButton = (AddButton) e.getSource();
            addCreatureToEncounter(clickedButton.getCreature());
        }
    }

    public void addCreatureToEncounter(Creature creature){
        encounter.addCreature(creature);
        encounter.getEncounterFrame().creatureList.addToCreatureList(new CreatureListPanel(creature));

    }

    private static class AddButton extends JButton{
        Creature creature;
        AddButton(String text, Creature creature){
            super(text);
            this.creature = creature;
        }

        public Creature getCreature(){
            return creature;
        }
    }
    private class DeleteButton extends JButton implements ActionListener{
        Creature creature;
        DeleteButton(String text, Creature creature){
            super(text);
            this.creature = creature;
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            DeleteDialogue deleteDialogue = new DeleteDialogue(creature);
            deleteDialogue.setLocationRelativeTo(this);
            deleteDialogue.setVisible(true);
        }

    }

    private class DeleteDialogue extends JDialog implements ActionListener{
        JButton yes, no;
        JLabel text;
        Creature creature;
        //TODO replace with generified NagDialogue
        DeleteDialogue(Creature creature){
            this.creature = creature;
            setAlwaysOnTop(true);
            text = new JLabel(("Are you sure you want to delete this creature? \n " +
                    "The creature will be removed from all encounters and files permanently"));
            text.setBackground(new Color(0,true));
            yes = new JButton("Yes");
            no = new JButton("No");
            yes.addActionListener(this);
            no.addActionListener(this);

            setLayout(new BorderLayout());
            add(text,BorderLayout.CENTER);

            JPanel ynPanel = new JPanel();
            ynPanel.add(yes);
            ynPanel.add(no);

            add(ynPanel, BorderLayout.SOUTH);
            pack();
        }
        public void removeCreatureFromFile() throws IOException, ClassNotFoundException {
            creatureListHandler.removeCreature(creature);

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == yes){
                try {
                    removeCreatureFromFile();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                //Remove instance from creature list
                rootCreaturePanels.removeIf(rootCreaturePanel -> Objects.equals(rootCreaturePanel.getName(), creature.getCreatureClass()));

                for(Component component : scrollablePanel.getComponents()){
                    if(component.getClass() == JPanel.class){
                        JPanel jPanel = (JPanel) component;
                        if(jPanel.getName().equals(creature.getCreatureClass())){
                            scrollablePanel.remove(jPanel);
                        }
                    }
                }
                scrollablePanel.repaint();
                scrollablePanel.revalidate();

            }
                setVisible(false);
            }
        }

}
