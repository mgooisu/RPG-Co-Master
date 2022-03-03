package Encounter;

import Helpers.FileReadWrite;
import Helpers.SerializedObjectHandler;

import java.io.IOException;
import java.util.HashMap;

/**
 * Handles reading and writing encounters to file generally, without association to a campaign. Likely won't see
 * much use in application, but could allow for creating generic encounters to share across campaigns or users.
 */
public class EncounterListHandler implements SerializedObjectHandler {
    private final String path = "./EncounterListFile.dnd";
    EncounterMap localEncounters, storedEncounters;

    public EncounterListHandler() throws IOException, ClassNotFoundException {
        if(fileExists()){
            storedEncounters = readObject();
            localEncounters = storedEncounters;
        }
        else{
            localEncounters = new EncounterMap();
            writeObject();
            storedEncounters = readObject();
        }
    }

    /**
     * Grabs the current version of the encoutner list and adds the new encounter to it
     * @param encounter the encounter to be added
     */
    public void addEncounter(Encounter encounter) throws IOException, ClassNotFoundException {
            localEncounters = new EncounterMap();
            if(!fileExists()){
                localEncounters = new EncounterMap();
                writeObject();
            }
            else{localEncounters = readObject();}

            HashMap<String, Encounter> tempMap = localEncounters.getEncounterHashMap();
            if(tempMap.containsKey(encounter.getEncounterName())){
                throw new IOException("Encounter file already exists");
            }
            tempMap.put(encounter.getEncounterName(),encounter);
            localEncounters.setEncounterHashMap(tempMap);
            writeObject();
        }


    /**
     * Grabs the current version of the encounter list and removes an encounter from it
     * @param encounter the encounter to be removed
     */
    public void removeEncounter(Encounter encounter) throws IOException, ClassNotFoundException {
        if(!fileExists()){
            throw new IOException("Nothing to delete from! No encounter list file");
        }

        localEncounters = readObject();
        HashMap<String, Encounter> tempMap = localEncounters.getEncounterHashMap();
        if(!tempMap.containsKey(encounter.getEncounterName())){
            throw new IOException("Encounter file does not exist!");
        }
        tempMap.remove(encounter.getEncounterName());
        localEncounters.setEncounterHashMap(tempMap);
        writeObject();

    }
    @Override
    public boolean fileExists() {
        return FileReadWrite.FileExists(path);
    }

    @Override
    public EncounterMap readObject() throws IOException, ClassNotFoundException {
        return(EncounterMap) FileReadWrite.readObjectFromFile(path);
    }

    @Override
    public void writeObject() throws IOException {
        FileReadWrite.writeObjectToFile(path,localEncounters);
        try {
            storedEncounters = readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns whether or not the data is consistent across the local and stored files
     * @return true if the data is the same | false if the data is inconsistent
     */
    @Override
    public boolean compareData() throws IOException, ClassNotFoundException {
        storedEncounters = readObject();
        HashMap<String ,Encounter> localHash = localEncounters.getEncounterHashMap(),
                storedHash = storedEncounters.getEncounterHashMap();
        if(localHash.keySet().size()!= storedHash.keySet().size()){
            return false;
        }
        for(String key: localHash.keySet()){
            Encounter LocalEncounter = localHash.get(key), storedEncounter = storedHash.get(key);

            if(
                //checks each of the data structures for inconsistencies
                    !(LocalEncounter.getEncounterName().equals(storedEncounter.getEncounterName()))
                            ||!(LocalEncounter.getCreatures().equals(storedEncounter.getCreatures()))
            ){
                return false;
            }
        }
        return true;
    }
}
