package Creature;

import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMap;
import Exceptions.CreatureException;
import Helpers.FileReadWrite;
import Helpers.SerializedObjectHandler;

import java.io.IOException;
import java.util.HashMap;

public class CreatureListHandler implements SerializedObjectHandler {
    private final String path = "./CreatureListFile.dnd";

    CreatureMap localCreatures,storedCreatures;

    public CreatureListHandler() throws IOException, ClassNotFoundException {
        if(fileExists()){
            storedCreatures = readObject();
            localCreatures = storedCreatures;
        }else{
            localCreatures = new CreatureMap();
            writeObject();
            storedCreatures = readObject();
        }
    }

    /**
     * Grabs the current version of the creature list and adds a new creature to it.
     * @param creature The creature to be added
     */
    public void addCreature(Creature creature) throws IOException, ClassNotFoundException {
        // creates the file if it doesn't yet exist. This should never run under normal circumstances
        if(!fileExists()){
            localCreatures = new CreatureMap();
            writeObject();
        }
        else{
            localCreatures = readObject();
        }
        HashMap<String, Creature> tempMap = localCreatures.getCreatureHashMap();
        if(tempMap.containsKey(creature.getCreatureClass())){
            throw new IOException("Creature File already exists");
        }
        tempMap.put(creature.getCreatureClass(),creature);
        localCreatures.setCreatureHashMap(tempMap);
        writeObject();
    }

    /**
     * Grabs the current version of the creature list and removes a creature from it
     * @param creature the creature to be removed
     */
    public void removeCreature(Creature creature) throws  IOException, ClassNotFoundException{
        if(!fileExists()){
            throw new IOException("Nothing to Delete from!");
        }
        localCreatures = readObject();
        HashMap<String, Creature> tempMap = localCreatures.getCreatureHashMap();
        if(!tempMap.containsKey(creature.getCreatureClass())){
            throw new IOException("Creature file does not Exist!");
        }
        tempMap.remove(creature.getCreatureClass());
        localCreatures.setCreatureHashMap(tempMap);
        writeObject();
    }

    @Override
    public boolean fileExists() {
        return FileReadWrite.FileExists(path);
    }

    @Override
    public CreatureMap readObject() throws IOException, ClassNotFoundException {
        return  (CreatureMap) FileReadWrite.readObjectFromFile(path);
    }

    @Override
    public void writeObject() throws IOException {
        FileReadWrite.writeObjectToFile(path,localCreatures);
        try {
            storedCreatures = readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean compareData() throws IOException, ClassNotFoundException {
        storedCreatures = readObject();
        HashMap<String,Creature> localHash = localCreatures.getCreatureHashMap(),
                storedHash = storedCreatures.getCreatureHashMap();
        if(localHash.keySet().size()!=storedHash.keySet().size()){
            return false;
    }
        for(String key : localHash.keySet()){
            Creature localMappedCreatures = localHash.get(key), storedMappedCreatures = storedHash.get(key);

            if(
                //checks each of the data structures for inconsistencies
                    !(localMappedCreatures.getName().equals(storedMappedCreatures.getName()))
                            ||!(localMappedCreatures.getDescription().equals(storedMappedCreatures.getDescription()))
            ){
                return false;
            }

        }
        return true;
    }
}
