package Creature;

import Helpers.DiceObject;

import java.util.HashMap;

/**
 * Information pertaining to a given monster action such as attack type\damage, effects, duration, range ect.
 */
public class MonsterAction {
    //TODO change all string definitions to enums
    private String name, type, description,saveType;
    private int reach, addToHit, target;
    HashMap<DiceObject, String> attack;
}
