package Creature.Helpers.Types;

import Creature.Helpers.Enums.Size;
import Creature.Helpers.Types.SpeciesInfo.*;

import java.io.IOException;

/**
 * Association of creatures to size and species, as well as default stats, randomization factors and customization handlers
 */
public class CreatureType{
    private Size size;
    private Species species;
    public CreatureType(Size size, String speciesName) throws IOException, ClassNotFoundException {
        this.size = size;
        this.species = SpeciesMapHandler.getMapFile().getSpeciesHashMap().get(speciesName);
    }

    public Size getSize() {
        return size;
    }

    public Species getSpecies() {
        return species;
    }
}
