package Creature;


import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Types.CreatureType;
import Creature.Helpers.Types.SpeciesInfo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CreatureTesting {
    int health = 13, aC = 13, speed = 30;
    String name = "Clink", description = "A shambling undead skeleton wearing scraps of old armour", creatureClass = "Skeleton";
    Alignment.CombinedAlignment alignment = Alignment.CombinedAlignment.Lawful_Evil;
    Monster monster;

    Size size = Size.MEDIUM;
    CreatureType creatureType = new CreatureType(size,"Undead");

    public CreatureTesting() throws IOException, ClassNotFoundException {
    }

    @BeforeEach
    public void createCreature(){
        monster = new Monster(name,alignment,description,creatureClass,health,aC,speed);
        System.out.println("Created a "+alignment+" " +creatureClass +" named "+name+" it is "+description);
        System.out.println("it has "+health+" health, "+speed+" feet-per-turn movement speed, and "+aC+" armor class");
        monster.setType(size,"Undead");
        System.out.println("This creature is a "+creatureType.getSize() + " "+ creatureType.getSpecies().getName());
    }
    @Test
    public void monsterCorrectInfo(){
        Alignment testAlign = new Alignment(alignment);
        Assertions.assertTrue(monster.getAC() == aC && monster.getSpeed() == speed && monster.getHealth() == health &&
                monster.getAlignment().getMoral().equals(testAlign.getMoral()) && monster.getAlignment().getEthic().equals(testAlign.getEthic())
                && monster.getName().equals(name) && monster.getDescription().equals(description)&&
                monster.getCreatureClass().equals(creatureClass));
    }

    @Test
    public void monsterCorrectType(){
        Assertions.assertEquals(creatureType.getSpecies().getName(), monster.getType().getSpecies().getName());
        Assertions.assertEquals(creatureType, monster.getType());
    }
}
