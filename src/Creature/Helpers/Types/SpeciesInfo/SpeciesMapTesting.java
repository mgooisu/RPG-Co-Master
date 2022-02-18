package Creature.Helpers.Types.SpeciesInfo;

import Helpers.FileReadWrite;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpeciesMapTesting {
    static SpeciesMapObjectHandler handler;
    static Object originalFile;
    @BeforeAll
    static void setup() throws IOException, ClassNotFoundException {
        handler = new SpeciesMapObjectHandler();
        originalFile = handler.readObject();
    }

    @Test
    void fileWritten(){
        Assertions.assertTrue(handler.fileExists());
    }

    @Test
    void localMatchesStored() throws IOException, ClassNotFoundException {

        Assertions.assertTrue(handler.compareData());

    }
    @Test
    void addASpecies() throws IOException, ClassNotFoundException {
        Species Aasimar = new Species("Aasimar","A bird-like race", Species.Role.HUMANOID);
        handler.addASpecies(Aasimar);
        Assertions.assertTrue(handler.localSpecies.getSpeciesHashMap().containsKey("Aasimar")&&
                handler.compareData());
    }

    @Test
    void deleteASpecies() throws IOException, ClassNotFoundException {
        addASpecies();
        handler.deleteASpecies("Aasimar");
        Assertions.assertTrue(!handler.localSpecies.getSpeciesHashMap().containsKey("Aasimar")&&
                handler.compareData());

    }
    @Test
    void updateASpecies() throws IOException, ClassNotFoundException{
        addASpecies();
        String speciesName = "Aasimar", newDescription = "A winged Menace";
        Species.Role newRole = Species.Role.MONSTER;
        handler.updateASpecies(speciesName,newDescription,newRole);
        Species updatedSpecies = handler.localSpecies.getSpeciesHashMap().get("Aasimar");
        Assertions.assertTrue(
                updatedSpecies.getName().equals(speciesName)&&
                        updatedSpecies.getDescription().equals(newDescription)&&
                        updatedSpecies.getRole().equals(newRole)
        );
    }

    @Test
    void readUndead(){
        Species undead = handler.localSpecies.getSpeciesHashMap().get("Undead");
        System.out.println(undead.getDescription());
    }

    @Test
    void updateANonExistentSpecies() throws IOException,ClassNotFoundException{
        String speciesName = "Aardvark", newDescription = "Some animal, I don't know";
        Species.Role newRole = Species.Role.MONSTER;
        Assertions.assertThrows(FileNotFoundException.class,()->
                handler.updateASpecies(speciesName,newDescription,newRole));
    }

    @Test
    void readSpecies() throws IOException, ClassNotFoundException {
        HashMap<String, Species> speciesHashMap = handler.readObject().getSpeciesHashMap();
        for (Map.Entry<String, Species> entry: speciesHashMap.entrySet()){
            Species species = entry.getValue();
            System.out.println(species.getName()+": "+species.getDescription());
        }
    }

    @Test
    void readMonsters() throws IOException, ClassNotFoundException {
        HashMap<String, Species> speciesHashMap = handler.readFilteredMap(Species.Role.MONSTER);
        for (Map.Entry<String, Species> entry: speciesHashMap.entrySet()){
            Species species = entry.getValue();
            System.out.println(species.getName()+": "+species.getDescription());
            Assertions.assertEquals(Species.Role.MONSTER, species.getRole());
        }
    }

    @AfterAll
    static void restoreOriginalFile() throws IOException, ClassNotFoundException {
        final String path = "src/Creature/Helpers/Types/SpeciesListFile.dnd";

        handler.localSpecies = (SpeciesMap) originalFile;
        handler.writeObject();

    }
}
