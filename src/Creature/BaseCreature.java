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
    private int health,maxHP,tempHP, AC, speed;
    private Stats stats;
    private String description, name,creatureClass;
    //Todo - create creature information objects: Senses, languages, Damage, Conditions, resistance, immunity
    private String[] senses, languages, vulnerabilities, immunities, resistances,
            conditionResists, conditionImmunities;
    private ArrayList<String> conditions;
    private Alignment alignment;
    private Size size;
    private final Species species;






    public BaseCreature(String name, Alignment.CombinedAlignment combinedAlignment, String description,
                        String creatureClass, int maxHP, int AC, int speed, Size size, Species species, Stats stats){
        this. description = description;
        this.creatureClass = creatureClass;
        this.maxHP= maxHP;
        this.health = maxHP;
        this.AC = AC;
        this.speed = speed;
        this.name = name;
        this.alignment = new Alignment(combinedAlignment);
        this.size = size;
        this.species = species;
        this.stats =stats;
    }



    //Getters
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
    public int getMaxHP(){
        return maxHP;
    }

    @Override
    public int getTempHP(){return tempHP;}

    @Override
    public int getAC() {
        return AC;
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

    @Override
    public void setAlignment(Alignment.CombinedAlignment combinedAlignment) {
        this.alignment = new Alignment(combinedAlignment);

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCreatureClass(String creatureClass) {
        this.creatureClass = creatureClass;

    }

    @Override
    public void setMaxHP(int HP) {

    }

    @Override
    public void setTempHP(int tempHP){
        this.tempHP = tempHP;
    }


    @Override
    public void setAC(int AC) {
        this.AC = AC;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void setStats(Stats stats) {
        this.stats = stats;

    }

    @Override
    public void setDescription(String description) {
        this.description = description;

    }

    @Override
    public void setSenses(String[] senses) {
        this.senses = senses;

    }

    @Override
    public void setLanguages(String[] languages) {
        this.languages = languages;

    }

    @Override
    public void setImmunity(String[] immunities) {
        this.immunities = immunities;

    }

    @Override
    public void setResistances(String[] resistances) {
        this.resistances = resistances;
    }

    @Override
    public void setConditionImmunity(String[] conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    @Override
    public void setConditionResistance(String[] conditionResistance) {
        this.conditionImmunities = conditionResistance;
    }


    //Modifiers
    @Override
    public void healthDamage(int dmgNumber){
        if(tempHP<=dmgNumber){
            dmgNumber -=tempHP;
            tempHP = 0;
        }
        else{
            tempHP-=dmgNumber;
            dmgNumber = 0;
        }
        health-=dmgNumber;
    }

    @Override
    public void healthHealing(int healingNumber) {
        if(health+healingNumber<maxHP)
        health+=healingNumber;
        else
            health = maxHP;

    }


    @Override
    public void addCondition(String condition, int durationTurns) {


    }

    @Override
    public void removeCondition(String condition) {

    }
}
