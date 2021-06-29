package Creature.Helpers.Types.SpeciesInfo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

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

    @AfterAll
    static void restoreOriginalFile() throws IOException {
        handler.localSpecies = (SpeciesMap) originalFile;
        handler.writeObject();
    }
}