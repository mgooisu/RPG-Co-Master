package Creature.Helpers.Types.SpeciesInfo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SpeciesListTesting {
    private SpeciesMap speciesMap;
    HashMap<String, Species> speciesHashMap;
    SpeciesMapHandler speciesMapHandler;
    @BeforeEach
    void setup() throws IOException, ClassNotFoundException {
        speciesMapHandler = new SpeciesMapHandler();
         speciesHashMap = speciesMapHandler.speciesMap.getSpeciesHashMap();
    }
    @Test
    void addASpecies() throws IOException, ClassNotFoundException {
        speciesMapHandler.addSpecies("Aasimar","A Humanoid bird player creature",Species.Role.HUMANOID);
        HashMap<String,Species> listFromFile = SpeciesMapHandler.getMapFile().getSpeciesHashMap();
        Assertions.assertTrue(listFromFile.containsKey("Aasimar"));
        sameSpecies();
    }
    @Test
    void deleteASpecies(){

    }

    @Test
    void sameSpecies() throws IOException, ClassNotFoundException {
        HashMap<String, Species> listFromFile = SpeciesMapHandler.getMapFile().getSpeciesHashMap();
        Assertions.assertEquals(listFromFile.keySet(),speciesHashMap.keySet());
        for(String name : listFromFile.keySet()){
            Assertions.assertEquals(listFromFile.get(name).getName(),speciesHashMap.get(name).getName());
            Assertions.assertEquals(listFromFile.get(name).getRole(),speciesHashMap.get(name).getRole());
            Assertions.assertEquals(listFromFile.get(name).getDescription(),speciesHashMap.get(name).getDescription());

        }

    }

}
