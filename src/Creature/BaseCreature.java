package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Exceptions.CreatureException;
import Helpers.DiceObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Parent class of all creatures to be used in the game. Contains base information like health and armor that all
 * creatures will have some representation of.
 */
public class BaseCreature implements Creature, Serializable {
    private int health,maxHP,tempHP, AC, speed, exhaustionLevel, initiative;
    private Stats stats;
    private String description, name,creatureClass;
    private String[] senses, languages;
    private Damage[] immunities, resistances, vulnerabilities;
    private Condition[]  conditionResists, conditionImmunities;
    private ArrayList<Condition> conditions;
    private Alignment alignment;
    private Size size;
    private Species species;
    private DiceObject hpDice;







    public BaseCreature(String name, Alignment alignment, String description,
                        String creatureClass, int maxHP, DiceObject hpDice, int AC, int speed, Size size, Species species, Stats stats,
                        Condition[] conditionImmunities, Condition[] conditionResists,
                        Damage[] immunities, Damage[] resistances, Damage[] vulnerabilities
    ) throws CreatureException {
        this.description = description;
        if (creatureClass == null || creatureClass.equals("")|| creatureClass.length() < 1){
            throw new CreatureException("The creature type must be named");
        }
        this.creatureClass = creatureClass;
        this.maxHP= maxHP;
        this.health = maxHP;
        this.hpDice = hpDice;
        this.AC = AC;
        this.speed = speed;
        this.name = name;
        this.alignment = alignment;
        this.size = size;
        this.species = species;
        this.stats =stats;
        this.conditions = new ArrayList<>();
        this.exhaustionLevel = 0;
        this.conditionImmunities = conditionImmunities;
        this.conditionResists = conditionResists;
        this.immunities = immunities;
        this.resistances = resistances;
        this.vulnerabilities = vulnerabilities;

        //Generate an initiative value that can be altered or re-rolled from the interface
        this.initiative = rollInitiative();

    }

    public int rollInitiative(){
        this.initiative = 1+stats.getDexterityMod()+ (int)(Math.random()*20);
        return this.initiative;

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
    public Damage[] getVulnerabilities() {
        return vulnerabilities;
    }

    @Override
    public Damage[] getImmunities() {
        return immunities;
    }

    @Override
    public Damage[] getResistances() {
        return resistances;
    }

    @Override
    public Condition[] getConditionResistances() {
        return conditionResists;
    }

    @Override
    public Condition[] getConditionImmunity() {
        return conditionImmunities;
    }

    @Override
    public int getInitiative(){return initiative;}




    @Override
    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    @Override
    public int getExhaustionLevel(){ return exhaustionLevel;}

    @Override
    public Size getSize() {
        return size;
    }
    @Override
    public Species getSpecies() {
        return species;
    }
    //Setters

    @Override
    public void setAlignment(Alignment.CombinedAlignment combinedAlignment) {
        this.alignment = new Alignment(Objects.requireNonNullElse(combinedAlignment, Alignment.CombinedAlignment.UNALIGNED));
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
    public void setImmunity(Damage[] immunities) {
        this.immunities = immunities;

    }

    @Override
    public void setResistances(Damage[] resistances) {
        this.resistances = resistances;
    }

    @Override
    public void setConditionImmunities(Condition[] conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    @Override
    public void setConditionResistances(Condition[] conditionResistance) {
        this.conditionImmunities = conditionResistance;
    }

    @Override
    public void setExhaustionLevel(int exhaustionLevel){
        this.exhaustionLevel = exhaustionLevel;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public void setSpecies(Species species) {
        this.species = species;
    }

    @Override
    public void setInitiative(int initiative){
        this.initiative = initiative;
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
    public void addCondition(Condition condition, int durationTurns) throws CreatureException {
        //todo add method to remove condition after the certain amount of turns
        for(Condition conditionImmunity: conditionImmunities){
            if(conditionImmunity.name().equals(condition.name())){
                throw new CreatureException("Creature is immune to "+condition.name()+"!");
            }
        }
        if(condition.name().equals(Condition.EXHAUSTION.name())) {
            increaseExhaustion(1);
        }
        this.conditions.add(condition);



    }

    @Override
    public void removeCondition(Condition condition) {
        if(condition.name().equals(Condition.EXHAUSTION.name())) {
            decreaseExhaustion(1);
            if(getExhaustionLevel()==0)
                this.conditions.remove(condition);
            return;
        }
        this.conditions.remove(condition);

    }

    @Override
    public void increaseExhaustion(int exhaustionIncreaseAmount) {
        this.exhaustionLevel+=exhaustionIncreaseAmount;
    }

    @Override
    public void decreaseExhaustion(int exhaustionDecreaseAmount) {
        this.exhaustionLevel-=exhaustionDecreaseAmount;
    }

}
