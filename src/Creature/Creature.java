package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Types.CreatureType;
import Creature.Helpers.Types.SpeciesInfo.Species;

import java.util.HashMap;

/**
 * Interface for the generic Creature object.
 */
public interface Creature {
    /**
     * @return the creature type
     * Todo- make enum
     */
    CreatureType getType();

    /**
     * Assigns a creature type to the creature
     */

    void setType(Size size, Species species);


    /**
     * Alignment is the measure of this creature's alignment towards good or evil and chaos or order
     * todo - make enum
     * @return Enum array of length 2: [MORAL:{good|evil}, ETHIC:{Chaos|Order}]
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
     * Gets the health of the Creature
     * @return health int
     */
    int getHealth();

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
    HashMap<String, Integer> getStats();

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
    String[] getCondition();


}
