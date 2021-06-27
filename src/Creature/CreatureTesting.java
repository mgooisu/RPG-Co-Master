package Creature;


import Helpers.Alignment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CreatureTesting {
    int health = 10, aC = 8, speed = 25;
    String name = "Blerg", description = "A disgusting red demon", creatureClass = "Kobald";
    Alignment.CombinedAlignment alignment = Alignment.CombinedAlignment.Chaotic_Evil;
    Monster monster;
    @BeforeEach
    public void createCreature(){
        monster = new Monster(name,alignment,description,creatureClass,health,aC,speed);
        System.out.println("Created a "+alignment+" " +creatureClass +" named "+name+" it is "+description);
        System.out.println("it has "+health+" health, "+speed+" feet-per-turn movement speed, and "+aC+" armor class");
    }
    @Test
    public void monsterCorrectInfo(){
        Alignment testAlign = new Alignment(alignment);
        Assertions.assertTrue(monster.getAC() == aC && monster.getSpeed() == speed && monster.getHealth() == health &&
                monster.getAlignment().getMoral().equals(testAlign.getMoral()) && monster.getAlignment().getEthic().equals(testAlign.getEthic())
                && monster.getName().equals(name) && monster.getDescription().equals(description)&&
                monster.getCreatureClass().equals(creatureClass));
    }
}
