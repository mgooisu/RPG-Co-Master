package Creature.Helpers.Types.SpeciesInfo;

import Helpers.FileReadWrite;

import java.io.IOException;

public class SpeciesListHandler {
    private static final String sListDirectory = "src/Creature/Helpers/Types/SpeciesInfo/SpeciesListFile.dnd";
    SpeciesList speciesList;
    public SpeciesListHandler() throws IOException, ClassNotFoundException {
        if(!FileReadWrite.checkFileExists(sListDirectory)){
            speciesList = new SpeciesList();
        }
        else
        {
            speciesList = getListFile();
        }
        editListFile();
    }

    public static SpeciesList getListFile() throws IOException, ClassNotFoundException {
        return (SpeciesList) FileReadWrite.readObject(sListDirectory);
    }

    private void editListFile() throws IOException, ClassNotFoundException {
        FileReadWrite.writeObject(sListDirectory, speciesList);
        speciesList = getListFile();
    }

    public void addSpecies(String name, String description, Species.Role role) throws IOException, ClassNotFoundException {
        speciesList.addSpecies(name,description,role);
        editListFile();
    }
}
