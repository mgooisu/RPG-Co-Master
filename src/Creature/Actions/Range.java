package Creature.Actions;

/**
 * Data structure to store the range of attacks with one or two ranges. e.g longswords have a single range of 5ft,
 * and shortbows have a double range of 80/320, whereby they have disadvantage on distances between 80ft and 320 ft
 */
public class Range {
    int close, far;

    /**
     * Constructor for attacks with only one range, usually melee
     * @param close the range
     */
    public Range(int close){
        this.close = close;
    }

    /**
     * Constructor of attacks with a long-range and close-range option
     * @param close the close range option
     * @param far the long range option
     */
    public Range(int close, int far){
        this.close = close;
        this.far = far;
    }

    public int getClose() {
        return close;
    }

    public int getFar() {
        return far;
    }
}
