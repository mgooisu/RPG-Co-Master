package Creature.Actions;

import Creature.Creature;
import Helpers.DiceObject;

/**
 * Defines any physical attack in 5e. For now, intended as a reference object. Actual application of
 * damage and effects are still to be undertaken by the DM using the gui.
 */
public class Attack extends Actions {
    private DiceObject diceObject;
    private int reach, target, addToHit, saveThreshold;
    private String saveType;
    public enum Range{MELEE,RANGED};
    private Range range;

    public Attack(String name, String description,
            DiceObject diceObject, int reach, int target, String saveType, int saveThreshold, int addToHit, Range range) {
        super(name, description);
        this.diceObject = diceObject;
        this.range = range;
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
