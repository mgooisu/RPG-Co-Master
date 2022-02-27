package GUI.Encounter;

import Creature.Creature;
import Creature.CreatureListHandler;
import Encounter.Encounter;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
}
