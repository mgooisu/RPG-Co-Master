package Creature.Helpers.Types.SpeciesInfo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class SpeciesListTesting {
    private static SpeciesMapHandler speciesMapHandler;
    static HashMap<String, Species> speciesHashMap;
    @BeforeAll
    static void setup() throws IOException, ClassNotFoundException {
        speciesMapHandler = new SpeciesMapHandler();
         speciesHashMap = speciesMapHandler.speciesMap.getSpeciesHashMap();

    }
    @Test
    void addASpecies() throws IOException, ClassNotFoundException {
        speciesMapHandler.addSpecies("Aasimar","A Humanoid bird player creature",Species.Role.HUMANOID);
        HashMap<String,Species> listFromFile = SpeciesMapHandler.getMapFile().getSpeciesHashMap();
        Assertions.assertTrue(listFromFile.containsKey("Aasimar"));
        sameSpeciesList();
    }
    @Test
    void deleteASpecies() throws IOException, ClassNotFoundException {
        String speciesToDelete = "Elf";
        speciesMapHandler.deleteSpecies(speciesToDelete);
        SpeciesMap missingSpeciesMapFile = SpeciesMapHandler.getMapFile();
        boolean containsSpecies;
        containsSpecies = missingSpeciesMapFile.getSpeciesHashMap().containsKey(speciesToDelete);
        Assertions.assertFalse(containsSpecies);
    }

    @Test
    void sameSpeciesList() throws IOException, ClassNotFoundException {
        Assertions.assertTrue(speciesMapHandler.compareData());


    }

}
