package Creature.Helpers.Enums;

/**
 * List of sizes in 5e
 */
public enum Size {
    TINY(2.5),
    SMALL(5),
    MEDIUM(5),
    LARGE(10),
    HUGE(15),
    GARGANTUAN(20);
    private double areaFeet;
    Size(double areaFeet){
        this.areaFeet = areaFeet;
    }
    public double getAreaFeet = areaFeet;
}
