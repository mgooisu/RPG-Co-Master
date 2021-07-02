package Creature;

import Creature.Actions.MonsterAction;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;



public class Monster extends BaseCreature{

    private MonsterAction[] actions;

    public Monster(String name, Alignment.CombinedAlignment alignment, String description,
                   String creatureClass, int health, int aC, int speed, Size size,
                   Species species, Stats stats, Condition[] conditionImmunities, Condition[] conditionResists,
                   Damage[] immunities,Damage[] resistances,Damage[] vulnerabilities) {
        super(name, alignment, description, creatureClass, health, aC, speed,size,
                species,stats,conditionImmunities,conditionResists,immunities,resistances,vulnerabilities);
    }



    //Monster/enemy specific methods

    public MonsterAction[] getActions() {
        return actions;
    }

    public void setActions(MonsterAction[] actions) {
        this.actions = actions;
    }




}
