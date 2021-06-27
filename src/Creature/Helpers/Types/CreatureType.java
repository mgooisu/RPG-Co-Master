package Creature.Helpers.Types;

import Creature.Helpers.Enums.Size;
import Creature.Helpers.Types.SpeciesInfo.*;

/**
 * Combination of size and species, as well as default stats, randomization factors and customization handlers
 */
public class CreatureType{
    private Size size;
    private Species thisSpecies;
    public CreatureType(Size size, Species species){
        this.size = size;
        thisSpecies = species;
    }

    public Size getSize() {
        return size;
    }

    public Species getSpecies() {
        return thisSpecies;
    }
}
