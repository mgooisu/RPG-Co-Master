package Creature.Actions;

import Creature.Creature;
import Helpers.DiceObject;

/**
 * Defines any physical attack in 5e. For now, intended as a reference object. Actual application of
 * damage and effects are still to be undertaken by the DM using the gui.
 */
//todo - add two integer datatype for close and long range ranged attacks
public class Attack extends Actions {
    private DiceObject diceObject;
    private int reach, target, addToHit, saveThreshold;
    private String saveType;


    public Attack(String name, String description,ActionType type,
            DiceObject diceObject, int reach, int target, String saveType, int saveThreshold, int addToHit) {
        super(name, description,type);
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
