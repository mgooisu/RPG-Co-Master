package Creature;

import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;



public class Monster extends BaseCreature{

    MonsterAction[] actions;

    public Monster(String name, Alignment.CombinedAlignment alignment, String description,
                   String creatureClass, int health, int aC, int speed, Size size, Species species, Stats stats) {
        super(name, alignment, description, creatureClass, health, aC, speed,size, species,stats);
    }


    //Monster/enemy specific methods




}
