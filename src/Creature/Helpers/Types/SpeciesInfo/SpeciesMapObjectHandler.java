package Creature.Helpers.Types.SpeciesInfo;

import Helpers.FileReadWrite;
import Helpers.SerializedObjectHandler;
import Helpers.UTest_FileReadWrite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class SpeciesMapObjectHandler implements SerializedObjectHandler {
    private SpeciesMap  storedSpecies;
    public SpeciesMap localSpecies;
    private final String path = "src/Creature/Helpers/Types/SpeciesListFile.dnd";

    public SpeciesMapObjectHandler() throws IOException, ClassNotFoundException {
        if(fileExists()){
            storedSpecies = (SpeciesMap) readObject();
            localSpecies = storedSpecies;
        }
        else{
            localSpecies = new SpeciesMap();
            writeObject();
        }

    }

    //Species Map Specific Methods

    /**
     * Takes a new species object, updates the hashmap and writes it to file
     * @param species The species to be added
     * @throws IOException
     */
    public void addASpecies(Species species) throws IOException {
        localSpecies.addSpecies(species.getName(),species.getDescription(),species.getRole());
        writeObject();
    }

    public void deleteASpecies(String speciesName) throws IOException {
        localSpecies.deleteSpecies(speciesName);
        writeObject();
    }

    public void updateASpecies(String speciesName, String newDescription, Species.Role newRole) throws IOException {
        if(localSpecies.getSpeciesHashMap().containsKey(speciesName)){
            localSpecies.addSpecies(speciesName,newDescription,newRole);
            writeObject();
        }else{
            throw new FileNotFoundException("Species does not exist");
        }
    }
    //Inherited General Methods

    @Override
    public boolean fileExists() {
        return FileReadWrite.FileExists(path);
    }

    @Override
    public Object readObject() throws IOException, ClassNotFoundException {
        storedSpecies = (SpeciesMap) FileReadWrite.readObjectFromFile(path);
        return storedSpecies;
    }

    @Override
    public void writeObject() throws IOException {
        FileReadWrite.writeObjectToFile(path, localSpecies);
    }

    @Override
    public boolean compareData() throws IOException, ClassNotFoundException {
        readObject();
        HashMap<String, Species> localHash = localSpecies.getSpeciesHashMap(),
                                 storedHash = storedSpecies.getSpeciesHashMap();

        if(localHash.keySet().size()!=storedHash.keySet().size()){
            return false;
        }


        for(String key : localHash.keySet()){
            Species localMappedSpecies = localHash.get(key), storedMappedSpecies = storedHash.get(key);

            if(
                    //checks each of the data structures for inconsistencies
                    !(localMappedSpecies.getName().equals(storedMappedSpecies.getName()))
                    ||!(localMappedSpecies.getDescription().equals(storedMappedSpecies.getDescription()))
                    ||!(localMappedSpecies.getRole().equals(storedMappedSpecies.getRole()))
            ){
                return false;
            }

        }
        return true;
    }
}
