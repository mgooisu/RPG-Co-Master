package GUI.Creatures;

import Creature.Creature;
import Exceptions.CreatureException;
import GUI.Encounter.CreatureListPanel;
import GUI.Encounter.EncounterCreatureList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RenameFrame extends JFrame implements ActionListener {
    JTextField renameField;
    JButton submit;
    Creature creature;
    CreatureListPanel creatureListPanel;
    public RenameFrame(CreatureListPanel creatureListPanel){
        setLayout(new BorderLayout());
        this.creatureListPanel = creatureListPanel;
        this.creature = creatureListPanel.getCreature();
        if(creature.getName()!= null){
            setTitle("Rename "+creature.getName());
        }
        else{
            setTitle("Rename "+creature.getCreatureClass());
        }
        renameField = new JTextField();
        renameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                //If enter is pressed
                if(e.getKeyCode()  == 10){
                    updateCreatureList();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        add(renameField,BorderLayout.CENTER);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        add(submit,BorderLayout.SOUTH);
    }

    void updateCreatureList(){
        //Execution is escaping the for loop even after exception, so here's a nasty fix
        boolean errorFound = false;
        //Im so sorry
        EncounterCreatureList encounterCreatureList = (EncounterCreatureList) creatureListPanel.getParent();
        String text = renameField.getText();
        if(text.matches("[a-zA-Z]+")){
            for(Component component : encounterCreatureList.getComponents()){
                if(component.getClass() == CreatureListPanel.class){
                    CreatureListPanel panel = (CreatureListPanel) component;
                    String oldName = panel.getCreature().getName();
                    if(oldName == null){
                        oldName = panel.getCreature().getCreatureClass();
                    }
                    if(oldName.equals(text) && creatureListPanel != panel){
                        errorFound = true;
                        try {
                            throw new CreatureException("Name must be unique");
                        } catch (CreatureException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            }
            if(!errorFound) {
                text = text+ " - " + creature.getCreatureClass();
                creature.setName(renameField.getText());
                // refresh creature list panel
                creatureListPanel.updateState();
                //Update full creature panel
                updateCreaturePanel(text);
                this.setVisible(false);
            }
        }
        else{
            try {
                throw new CreatureException("Name must contain at least one letter");
            } catch (CreatureException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Checks to see if there are creature windows open and refreshes their title
     */
    void updateCreaturePanel(String text){
        JFrame creatureFrame = creatureListPanel.getExpandedCreatureMenuFrame();
        if(creatureFrame!= null){
            creatureFrame.setTitle(text);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == submit){
            updateCreatureList();


        }
    }
}
