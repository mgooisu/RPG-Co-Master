package Creature;

import Creature.Helpers.Alignment;

public class Monster extends BaseCreature{

    MonsterAction[] actions;

    public Monster(String name, Alignment.CombinedAlignment alignment, String description, String creatureClass, int health, int aC, int speed) {
        super(name, alignment, description, creatureClass, health, aC, speed);
    }


    //Monster/enemy specific methods




}
