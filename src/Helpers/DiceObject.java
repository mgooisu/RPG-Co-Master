package Helpers;

import java.io.Serializable;

/**
 * Expresses the combination of dice and additions to their result as an object - eg. (3d4 +2)
 */
public class DiceObject implements Serializable {
    private int type,amount,modifier;

    public DiceObject(int type, int amount, int modifier) {
        this.type = type;
        this.amount = amount;
        this.modifier = modifier;
    }

    public int getModifier() {
        return modifier;
    }

    public int getAmount() {
        return amount;
    }

    public int getType() {
        return type;
    }
}
