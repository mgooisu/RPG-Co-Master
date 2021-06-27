package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Types.CreatureType;
import Creature.Helpers.Types.SpeciesInfo.Species;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Parent class of all creatures to be used in the game. Contains base information like health and armor that all
 * creatures will have some representation of.
 */
public class BaseCreature implements Creature, Serializable {
    private int health, aC, speed;
    private HashMap<String, Integer> stats;
    private String description, name,creatureClass;
    private String[] senses, languages, vulnerabilities, immunities, resistances,
            conditionResists, conditionImmunities, conditions;
    private Alignment alignment;
    private CreatureType creatureType;






    public BaseCreature(String name, Alignment.CombinedAlignment combinedAlignment, String description, String creatureClass, int health, int aC, int speed){
        this. description = description;
        this.creatureClass = creatureClass;
        this.health = health;
        this.aC = aC;
        this.speed = speed;
        this.name = name;
        //todo - make alignment object
        this.alignment = new Alignment(combinedAlignment);
    }

    @Override
    public CreatureType getType() {
        return creatureType;
    }

    @Override
    public void setType(Size size, Species species){
        //todo - find creature type from typeList instead of generating a new one
        this.creatureType = new CreatureType(size,species);
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
    public HashMap<String, Integer> getStats() {
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
    public String[] getCondition() {
        return conditions;
    }
}
