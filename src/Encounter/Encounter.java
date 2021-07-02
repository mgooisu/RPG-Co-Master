package Encounter;

import Creature.BaseCreature;

import java.util.ArrayList;

/**
 * An encounter object will contain instances of creatures, which can be altered individually or as a group
 */
public class Encounter {
    private String encounterName;

    public Encounter(String encounterName){
        this.encounterName = encounterName;
    }

    public String getEncounterName() {
        return encounterName;
    }

    private ArrayList<BaseCreature> creatures;

    public ArrayList<BaseCreature> getCreatures() {
        return creatures;
    }

    public void setCreatures(ArrayList<BaseCreature> creatures) {
        this.creatures = creatures;
    }

    public void addCreature(BaseCreature creature){
        this.creatures.add(creature);
    }
}
