package Creature;

import Creature.Helpers.Attack;
import Helpers.DiceObject;

/**
 * Information pertaining to a given monster action such as attack type\damage, effects, duration, range ect.
 */
public class MonsterAction {
    //TODO change string definitions to enums
    private String name, type, description;
    private Attack attack;

    /**
     * Constructor for minimum definition of an action
     * @param name the name of the action
     * @param description What the action does
     * @param type the type of the action - e.g meleeAttack, rangedAttack, Magic, etc.
     */
    public MonsterAction(String name, String type, String description){
        this.name = name;
        this.description = description;
        this.type = type;
    }

    /**
     * Constructor for an attack action
     * @param name name of attack
     * @param type type of attack
     * @param description description of attack - also covers any other miscellaneous parameters of the attack
     * @param saveType required save to resist effects
     * @param reach range of attack in feet
     * @param addToHit modifier to hit attack
     * @param target how many targets the attack can hit in one instance
     * @param diceAmount how many dice to roll for damage
     * @param diceType what type of dice for damage
     * @param diceMod constant to add to roll for damage
     */
    public MonsterAction(String name, String type, String description,
                         String saveType, int saveThreshold,
                         int reach, int addToHit, int target,
                         int diceAmount, int diceType, int diceMod
    ){
        this.name = name;
        this.description = description;
        this.type = type;
        this.attack = new Attack(new DiceObject(diceType,diceAmount,diceMod), reach, target, saveType,saveThreshold, addToHit);
    }
}
