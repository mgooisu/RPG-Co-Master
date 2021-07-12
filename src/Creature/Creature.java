package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Exceptions.CreatureException;

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
     * @return a list of the elemental\ attack type weaknesses that the creature has
     */
    Damage[] getVulnerabilities();

    /**
     * @return a list of the elemental\ attack type immunities that the creature has
     */
    Damage[] getImmunities();

    /**
     * @return a list of the elemental\ attack type that the creature is resistant to
     */
    Damage[] getResistances();

    /**
     *
     * @return a list of the condition immunities that the creature has
     */
    Condition[] getConditionImmunity();

    /**
     *
     * @return a list of the conditions that the creature is resistant to
     */
    Condition[] getConditionResistances();

    /**
     *
     * @return a list of the conditions the creature is currently experiencing
     */
    List<Condition> getConditions();

    /**
     *
     * @return the current exhaustion level of the creature
     */
    int getExhaustionLevel();

    /**
     *
     * @return the size of the creature
     */
    Size getSize();

    /**
     *
     * @return The species(type) of the creature
     */
    Species getSpecies();

    /**
     *
     * @return The current initiative for the creature
     */
    int getInitiative();

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
    void setImmunity(Damage[] immunities);
    void setResistances(Damage[] resistances);
    void setConditionImmunities(Condition[] conditionImmunity);
    void setConditionResistances(Condition[] conditionResistance);
    void setExhaustionLevel(int exhaustionLevel);
    void setSize(Size size);
    void setSpecies(Species species);
    void setInitiative(int initiative);
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

    /**
     * Inflicts the creature with a condition
     * @param condition the condition the creature has been inflicted with
     * @param durationTurns How long, in turns the creature should be inflicted with the condition(0 if undefined).
     */
    void addCondition(Condition condition, int durationTurns) throws CreatureException;

    void removeCondition(Condition condition);

    /**
     * Increases the level of exhaustion of the character, perhaps from starvation, overexertion or magic
     * @param exhaustionIncreaseAmount the integer amount by which the exhaustion increases, usually 1
     */
    void increaseExhaustion(int exhaustionIncreaseAmount);

    /**
     * Decreases the level of exhaustion of the character, perhaps from rest or magic
     * @param exhaustionDecreaseAmount the integer amount by which the exhaustion decreases, usually 1
     */
    void decreaseExhaustion(int exhaustionDecreaseAmount);
}
