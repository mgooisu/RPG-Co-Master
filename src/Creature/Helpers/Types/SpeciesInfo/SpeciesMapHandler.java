package Creature.Helpers.Types.SpeciesInfo;

import Helpers.FileReadWrite;

import java.io.IOException;

public class SpeciesMapHandler {
    private static final String sListDirectory = "src/Creature/Helpers/Types/SpeciesInfo/SpeciesListFile.dnd";
    SpeciesMap speciesMap;
    public SpeciesMapHandler() throws IOException, ClassNotFoundException {
        if(!FileReadWrite.checkFileExists(sListDirectory)){
            speciesMap = new SpeciesMap();
        }
        else
        {
            speciesMap = getMapFile();
        }
        editListFile();
    }

    public static SpeciesMap getMapFile() throws IOException, ClassNotFoundException {
        return (SpeciesMap) FileReadWrite.readObject(sListDirectory);
    }

    private void editListFile() throws IOException, ClassNotFoundException {
        FileReadWrite.writeObject(sListDirectory, speciesMap);
        speciesMap = getMapFile();
    }

    public void addSpecies(String name, String description, Species.Role role) throws IOException, ClassNotFoundException {
        speciesMap.addSpecies(name,description,role);
        editListFile();
    }
}
