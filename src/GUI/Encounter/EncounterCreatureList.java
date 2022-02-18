package GUI.Encounter;

import javax.swing.*;
import java.util.ArrayList;

public class EncounterCreatureList extends JPanel{
    //Data
    ArrayList<CreatureListPanel> creatureListPanels;
    // Ugly way of restricting reordering when all creature's initiatives are rolled
    public boolean initOrderLocked = false;

    //Does all the normal JPanel stuff and initializes the Data
    public EncounterCreatureList(){
        super();
        creatureListPanels = new ArrayList<>();
    }

    //Rolls Initiative for all panels
    public void rollInitiatives(){
        initOrderLocked = true;
        for(CreatureListPanel creatureListPanel: creatureListPanels){
            creatureListPanel.rollInitiative();
        }
        initOrderLocked = false;
        initArrange();
    }

    public ArrayList<CreatureListPanel> getCreatureListPanels() {
        return creatureListPanels;
    }
    public void addToCreatureList(CreatureListPanel creatureListPanel){
        creatureListPanels.add(creatureListPanel);
        add(creatureListPanel);
        initArrange();
    }
    public void removeFromCreatureList(CreatureListPanel creatureListPanelToRemove){
        /*Arraylists aren't fast for removing elements, but the user shouldn't be removing enemies from the list
        that often and generating IDs for hash maps is haaard.
         */
        creatureListPanels.remove(creatureListPanelToRemove);
        remove(creatureListPanelToRemove);
        revalidate();
        repaint();
    }

    /**
     * Rearranges the panels in descending initiative order
     */
    public void initArrange() {
        if(!initOrderLocked){
            int n = creatureListPanels.size();
            CreatureListPanel[] creatureListPanelsArray = new CreatureListPanel[n];
            int counter = 0;
            //populates array with original panel order
            for(CreatureListPanel creatureListPanel: creatureListPanels){
                creatureListPanelsArray[counter] = creatureListPanel;
                counter++;
            }

            //bubble sort
            CreatureListPanel temp;
            boolean swapped = true;
            while(swapped){
                swapped = false;
                for(int i = 1; i< n; i++){
                    if(creatureListPanelsArray[i-1].getInitiative()<creatureListPanelsArray[i].getInitiative()){
                        temp = creatureListPanelsArray[i];
                        creatureListPanelsArray[i] = creatureListPanelsArray[i-1];
                        creatureListPanelsArray[i-1] = temp;
                        swapped = true;
                    }
                }
            }

            //Rearrange panels
            for(CreatureListPanel creatureListPanel: getCreatureListPanels()){
                remove(creatureListPanel);
            }
            for(CreatureListPanel creatureListPanel : creatureListPanelsArray){
                add(creatureListPanel);

            }
            repaint();
            revalidate();

        }
    }
}
