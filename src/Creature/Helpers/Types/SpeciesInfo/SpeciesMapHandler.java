package Creature.Helpers.Types.SpeciesInfo;

import Helpers.FileReadWrite;
import Helpers.SerializedObjectHandler;

import java.io.IOException;
import java.util.HashMap;

public class SpeciesMapHandler implements SerializedObjectHandler {
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
        FileReadWrite.writeObjectBytes(sListDirectory, speciesMap);
        speciesMap = getMapFile();
    }

    public void addSpecies(String name, String description, Species.Role role) throws IOException, ClassNotFoundException {
        speciesMap.addSpecies(name,description,role);
        editListFile();
    }

    public void deleteSpecies(String name) throws IOException, ClassNotFoundException {
        speciesMap.deleteSpecies(name);
        editListFile();
    }

    @Override
    public boolean compareData() throws IOException, ClassNotFoundException {
        HashMap<String,Species> storedSpeciesHashMap = getMapFile().getSpeciesHashMap();
        HashMap<String,Species> localSpeciesHashMap = speciesMap.getSpeciesHashMap();
        for(String key: storedSpeciesHashMap.keySet()){
            Species storedMapEntry = storedSpeciesHashMap.get(key), localMapEntry = localSpeciesHashMap.get(key);
            if(
                    !(localMapEntry.getRole().equals(storedMapEntry.getRole()))||
                    !(localMapEntry.getName().equals(storedMapEntry.getName()))||
                    !(localMapEntry.getDescription().equals(storedMapEntry.getDescription()))
            ){
                return false;
            }

        }
        return true;
    }
}
