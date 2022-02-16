package Creature;

import Creature.Actions.Actions;
import Creature.Actions.MonsterAction;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Exceptions.CreatureException;
import Helpers.DiceObject;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The term "monster" refers to creatures that can be engaged in combat. There is no distinction between a human
 * bandit and an ogre in this regard
 */
public class Monster extends BaseCreature{
//todo - add skills like deception and acrobatics


    public Monster(String name, Alignment alignment, String description,
                   String creatureClass, int health, DiceObject hpDice, int aC, int speed, Size size,
                   Species species, Stats stats, Condition[] conditionImmunities, Condition[] conditionResists,
                   Damage[] immunities, Damage[] resistances, Damage[] vulnerabilities) throws CreatureException {
        super(name, alignment, description, creatureClass, health, hpDice,aC, speed,size,
                species,stats,conditionImmunities,conditionResists,immunities,resistances,vulnerabilities);
    }


    //Monster/enemy specific methods
    private Actions[] actions = new Actions[0];

    private Features[] features = new Features[0];

    //TODO experience points

    public Actions[] getActions() {
        return actions;
    }

    public void setActions(Actions[] actions) {
        this.actions = actions;
    }


    //Todo - migrate implementation of getActions to arraylist to allow for creature evolution
    public void setActions(ArrayList<MonsterAction> actions){
        MonsterAction[] actionArray = new MonsterAction[actions.size()];
        int i = 0;
        for(MonsterAction monsterAction: actions){
            actionArray[i] = monsterAction;
            i++;
        }
        this.actions = actionArray;
    }


    public Features[] getFeatures() {
        return features;
    }

    public void setFeatures(Features[] features) {
        this.features = features;
    }
}
