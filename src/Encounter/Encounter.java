package Encounter;

import Creature.BaseCreature;
import Creature.Creature;
import GUI.Encounter.EncounterFrame;

import java.util.ArrayList;

/**
 * An encounter object will contain instances of creatures, which can be altered individually or as a group
 */
public class Encounter {
    private String encounterName;
    private ArrayList<Creature> creatures;
    private final EncounterFrame encounterFrame;


    public Encounter(String encounterName){

        this.encounterName = encounterName;
        this.creatures = new ArrayList<Creature>();
        this.encounterFrame = new EncounterFrame(this);
    }

    public String getEncounterName() {
        return encounterName;
    }

    public void setEncounterName(String encounterName){
        this.encounterName = encounterName;
    }


    public ArrayList<Creature> getCreatures() {return creatures;
    }

    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    public void addCreature(Creature creature){
        this.creatures.add(creature);
    }

    public void removeCreature(Creature creature){this.creatures.remove(creature);}

    public EncounterFrame getEncounterFrame(){
        return encounterFrame;
    }

    /**
     * Brings forth the frame for this encounter
     */
    public void summonFrame(){
        encounterFrame.pack();
        encounterFrame.setLocationRelativeTo(null);
        encounterFrame.setSize(400,400);
        encounterFrame.setVisible(true);
    }
}
