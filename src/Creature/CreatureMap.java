package Creature;

import java.io.Serializable;
import java.util.HashMap;

public class CreatureMap implements Serializable {
    private HashMap<String, BaseCreature> creatureHashMap;
    public HashMap<String, BaseCreature> getCreatureHashMap() {
        return creatureHashMap;
    }

    public void setCreatureHashMap(HashMap<String, BaseCreature> creatureHashMap) {
        this.creatureHashMap = creatureHashMap;
    }


}
