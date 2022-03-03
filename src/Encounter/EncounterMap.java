package Encounter;

import java.io.Serializable;
import java.util.HashMap;

public class EncounterMap implements Serializable {
    private HashMap<String, Encounter> encounterHashMap;
    public EncounterMap(){
        encounterHashMap = new HashMap<String, Encounter>();
    }

    public void setEncounterHashMap(HashMap<String,Encounter> encounterHashMap){
        this.encounterHashMap = encounterHashMap;
    }

    public HashMap<String,Encounter> getEncounterHashMap(){
        return encounterHashMap;
    }
}
