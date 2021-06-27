package Creature.Helpers.Types.SpeciesInfo;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SpeciesListTesting {
    private SpeciesList speciesList;
    ArrayList<Species> speciesArrayList;
    SpeciesListHandler speciesListHandler;
    @BeforeEach
    void setup() throws IOException, ClassNotFoundException {
        speciesListHandler = new SpeciesListHandler();
        speciesArrayList = speciesListHandler.speciesList.getSpeciesArrayList();
    }
    @Test
    void addASpecies() throws IOException, ClassNotFoundException {
        speciesListHandler.addSpecies("Aasimar","A Humanoid bird player creature",Species.Role.HUMANOID);
        ArrayList<Species> listFromFile = SpeciesListHandler.getListFile().getSpeciesArrayList();
        boolean containsNewSpecies = false;
        for(Species entry : listFromFile){
            if(entry.getName().equals("Aasimar")){
                containsNewSpecies = true;
            }
        }
        Assertions.assertTrue(containsNewSpecies);
        sameSpecies();
    }
    @Test
    void deleteASpecies(){

    }

    @Test
    void sameSpecies() throws IOException, ClassNotFoundException {
        ArrayList<Species> listFromFile = SpeciesListHandler.getListFile().getSpeciesArrayList();
        for(int i = 0; i< listFromFile.size();i++){
            Assertions.assertEquals(listFromFile.get(i).getName(),speciesArrayList.get(i).getName());
            Assertions.assertEquals(listFromFile.get(i).getRole(),speciesArrayList.get(i).getRole());
            Assertions.assertEquals(listFromFile.get(i).getDescription(),speciesArrayList.get(i).getDescription());
        }
    }

}
