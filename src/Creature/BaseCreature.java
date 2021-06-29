package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Parent class of all creatures to be used in the game. Contains base information like health and armor that all
 * creatures will have some representation of.
 */
public class BaseCreature implements Creature, Serializable {
    private int health, aC, speed;
    private Stats stats;
    private String description, name,creatureClass;
    //Todo - create creature information objects: Senses, languages, Damage, Conditions, resistance, immunity
    private String[] senses, languages, vulnerabilities, immunities, resistances,
            conditionResists, conditionImmunities;
    private ArrayList<String> conditions;
    private Alignment alignment;
    private Size size;
    private Species species;






    public BaseCreature(String name, Alignment.CombinedAlignment combinedAlignment, String description,
                        String creatureClass, int health, int aC, int speed, Size size, Species species,Stats stats){
        this. description = description;
        this.creatureClass = creatureClass;
        this.health = health;
        this.aC = aC;
        this.speed = speed;
        this.name = name;
        this.alignment = new Alignment(combinedAlignment);
        this.size = size;
        this.species = species;
        this.stats =stats;
    }



    @Override
    public Alignment getAlignment() {
        return alignment;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCreatureClass() {
        return creatureClass;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAC() {
        return aC;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String[] getSenses() {
        return senses;
    }

    @Override
    public String[] getLanguages() {
        return languages;
    }

    @Override
    public String[] getVulnerabilities() {
        return vulnerabilities;
    }

    @Override
    public String[] getImmunity() {
        return immunities;
    }

    @Override
    public String[] getResistances() {
        return conditionResists;
    }

    @Override
    public String[] getConditionImmunity() {
        return conditionImmunities;
    }

    @Override
    public String[] getConditionResistance() {
        return conditionResists;
    }

    @Override
    public List<String> getCondition() {
        return conditions;
    }
}
