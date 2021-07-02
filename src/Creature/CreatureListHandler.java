package Creature;

import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMap;
import Helpers.FileReadWrite;
import Helpers.SerializedObjectHandler;

import java.io.IOException;
import java.util.HashMap;

public class CreatureListHandler implements SerializedObjectHandler {
    private final String path = "src/Creature/CreatureListFile.dnd";

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
        HashMap<String,BaseCreature> localHash = localCreatures.getCreatureHashMap(),
                storedHash = storedCreatures.getCreatureHashMap();
        if(localHash.keySet().size()!=storedHash.keySet().size()){
            return false;
    }
        for(String key : localHash.keySet()){
            BaseCreature localMappedCreatures = localHash.get(key), storedMappedCreatures = storedHash.get(key);

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
