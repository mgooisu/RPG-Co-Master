package Creature;

import java.util.HashMap;

public class Monster implements Creature{
    private int health;
    private int aC;
    private HashMap<String, Integer> stats;
    private String description;
    MonsterAction[] actions;


    //Monster/enemy specific methods



    @Override
    public String getType() {
        return null;
    }

    @Override
    public String[] getAlignment() {
        return new String[0];
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getCreatureClass() {
        return null;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public int getAC() {
        return 0;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public HashMap<String, Integer> getStats() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String[] getSenses() {
        return new String[0];
    }

    @Override
    public String[] getLanguages() {
        return new String[0];
    }

    @Override
    public String[] getVulnerabilities() {
        return new String[0];
    }

    @Override
    public String[] getImmunity() {
        return new String[0];
    }

    @Override
    public String[] getResistances() {
        return new String[0];
    }

    @Override
    public String[] getConditionImmunity() {
        return new String[0];
    }

    @Override
    public String[] getConditionResistance() {
        return new String[0];
    }
}
