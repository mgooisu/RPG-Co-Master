package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Stats;

import java.util.List;

/**
 * Interface for the generic Creature object.
 */
public interface Creature {

    /**
     * Alignment is the measure of this creature's alignment towards good or evil and chaos or order
     * @return Enum alignment object containing moral and ethic enums [MORAL:{good|evil}, ETHIC:{Chaos|Order}]
     */
    Alignment getAlignment();

    /**
     *
     * @return individual name or null if the creature is wild or otherwise nameless
     */
    String getName();

    /**
     *
     * @return the class of the unit. e.g skeleton archer, warrior, red kobold ect.
     */
    String getCreatureClass();

    /**
     * Gets the maximum health of the character
     * @return maximum HP
     */
    int getMaxHP();
    /**
     * Gets the health of the Creature
     * @return health int
     */
    int getHealth();

    /**
     * Gets the temporary HP of a character - a measure of how much damage the character can take before
     * their HP is affected. It is usually conferred by magic
     * note - it is not accumulative, it can only be replaced. It also cannot be negative
     * @return temporary HP integer
     */
    int getTempHP();

    /**
     * @return The armor class of the Creature
     */
    int getAC();

    /**
     *
     * @return the speed of the creature in feet-per-turn
     */
    int getSpeed();

    /**
     * gets the collection off RPG stats for the character. Initially will cater for 5e stats, but could be modified to
     * be general
     * @return a hashmap with key - string for stat type, value - int for value
     */
    Stats getStats();

    /**
     *
     * @return a physical description of this Creature.
     */
    String getDescription();

    /**
     * todo - make enum
     * @return a list of the special senses that the creature has e.g dark-vision, blind-sight
     */
    String[] getSenses();

    /**
     * todo - make enum
     * @return a list of languages that the creature can speak
     */
    String[] getLanguages();

    /**
     * todo - make enum
     * @return a list of the elemental\ attack type weaknesses that the creature has
     */
    String[] getVulnerabilities();

    /**
     * todo - make enum
     * @return a list of the elemental\ attack type immunities that the creature has
     */
    String[] getImmunity();

    /**
     * todo - make enum
     * @return a list of the elemental\ attack type that the creature is resistant to
     */
    String[] getResistances();

    /**
     * todo - make condition object with enums
     * @return a list of the condition immunities that the creature has
     */
    String[] getConditionImmunity();

    /**
     * todo - make condition object with enums
     * @return a list of the condition that the creature is resistant to
     */
    String[] getConditionResistance();

    /**
     * todo - make condition object with enums
     * @return a list of the conditions the creature is currently experiencing
     */
    List<String> getCondition();

    //Setters


    void setAlignment(Alignment.CombinedAlignment alignment);
    void setName(String name);
    void setCreatureClass(String creatureClass);
    void setMaxHP(int HP);
    void setTempHP(int tempHP);
    void setAC(int AC);
    void setSpeed(int speed);
    void setStats(Stats stats);
    void setDescription(String description);
    void setSenses(String[] senses);
    void setLanguages(String[] languages);
    void setImmunity(String[] immunities);
    void setResistances(String[] resistances);
    void setConditionImmunity(String[] conditionImmunity);
    void setConditionResistance(String[] conditionResistance);
    //Modifiers

    /**
     * Damages the creature for a certain number; subtracts an integer amount from health
     * @param dmgNumber The amount of damage the creature takes
     */
    void healthDamage(int dmgNumber);

    /**
     * heals the creature for a certain number; adds an integer amount to health
     * @param healingNumber The amount the creature heals by
     */
    void healthHealing(int healingNumber);

    //Todo - create condition object
    void addCondition(String condition, int durationTurns);

    void removeCondition(String condition);
}
