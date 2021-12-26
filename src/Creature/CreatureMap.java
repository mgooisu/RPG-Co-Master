package Creature;

import java.io.Serializable;
import java.util.HashMap;

public class CreatureMap implements Serializable {
    private HashMap<String, Creature> creatureHashMap;

    public CreatureMap(){
        creatureHashMap = new HashMap<>();
    }

    public HashMap<String, Creature> getCreatureHashMap() {
        return creatureHashMap;
    }

    public void setCreatureHashMap(HashMap<String, Creature> creatureHashMap) {
        this.creatureHashMap = creatureHashMap;
    }


}
