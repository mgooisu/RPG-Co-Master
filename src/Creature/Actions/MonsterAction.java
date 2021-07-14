package Creature.Actions;

import Creature.Actions.Actions;
import Creature.Actions.Attack;
import Creature.Creature;
import Helpers.DiceObject;

/**
 * Information pertaining to a given monster action such as attack type\damage, effects, duration, range ect.
 */
public class MonsterAction extends Actions {
    private Attack attack;

    /**
     * Constructor for minimum definition of an action
     * @param name the name of the action
     * @param description What the action does
     * @param type the type of the action - e.g meleeAttack, rangedAttack, Magic, etc.
     */
    public MonsterAction(String name, ActionType type, String description){
        super(name,description,type);
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
    public MonsterAction(String name, ActionType type, String description,
                         String saveType, int saveThreshold,
                         Range reach, int addToHit, int target,
                         int diceAmount, int diceType, int diceMod
    ){
        super(name,description,type);
        DiceObject attackDice = new DiceObject(diceType,diceAmount,diceMod);
        this.attack = new Attack(name, description,type,attackDice,reach,target,saveType,saveThreshold,addToHit);
    }

    public Attack getAttack() {
        return attack;
    }
}
