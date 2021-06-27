package Creature.Helpers;

import Creature.Creature;
import Helpers.DiceObject;

public class Attack {
    private DiceObject diceObject;
    private int reach, target, addToHit, saveThreshold;
    private String saveType;

    public Attack(DiceObject diceObject, int reach, int target, String saveType, int saveThreshold, int addToHit) {
        this.diceObject = diceObject;
        this.reach = reach;
        this.target = target;
        this.addToHit = addToHit;
        this.saveThreshold = saveThreshold;
        this.saveType = saveType;
    }


    public DiceObject getDiceObject() {
        return diceObject;
    }

    public int getTarget() {
        return target;
    }

    public int getReach() {
        return reach;
    }

    public int getAddToHit() {
        return addToHit;
    }

    public int getSaveThreshold() {
        return saveThreshold;
    }

    public String getSaveType() {
        return saveType;
    }
}
