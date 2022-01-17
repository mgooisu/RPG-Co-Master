package Creature.Actions;

import Creature.Creature;
import Creature.Helpers.Enums.Damage;
import Helpers.DiceObject;

/**
 * Defines any physical attack in 5e. For now, intended as a reference object. Actual application of
 * damage and effects are still to be undertaken by the DM using the gui.
 */
public class Attack extends Actions {
    private DiceObject diceObject;
    private int  target, addToHit, saveThreshold;
    private Range range;
    private String saveType;
    private Damage damage;


    public Attack(String name, String description,ActionType type,
            DiceObject diceObject, Range reach, int target, String saveType, int saveThreshold, int addToHit) {
        super(name, description,type);
        this.diceObject = diceObject;
        this.range = reach;
        this.target = target;
        this.addToHit = addToHit;
        this.saveThreshold = saveThreshold;
        this.saveType = saveType;

    }
    //No Saves version
    public Attack(String name, String description,ActionType type,
                  DiceObject diceObject, Range reach, int target, int addToHit, Damage damage) {
        super(name, description,type);
        this.diceObject = diceObject;
        this.range = reach;
        this.target = target;
        this.addToHit = addToHit;
        this.damage = damage;

    }


    public DiceObject getDiceObject() {
        return diceObject;
    }

    public int getTarget() {
        return target;
    }

    public Range getRange() {
        return range;
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

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }
}
