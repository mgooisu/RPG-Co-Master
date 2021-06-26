package Creature;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CreatureTesting {
    int health = 10, aC = 8, speed = 25;
    String name = "blerg", description = "A disgusting red demon", creatureClass = "Kobald";
    String[] alignment = {"Chaotic","Evil"};
    Monster monster;
    @BeforeEach
    public void createCreature(){
        monster = new Monster(name,alignment,description,creatureClass,health,aC,speed);
        System.out.println("Created a "+Arrays.toString(alignment)+" " +creatureClass +" named "+name+" it is "+description);
        System.out.println("it has "+health+" health, "+speed+" feet-per-turn movement speed, and "+aC+" armor class");
    }
    @Test
    public void monsterCorrectInfo(){
        Assertions.assertTrue(monster.getAC() == aC && monster.getSpeed() == speed && monster.getHealth() == health &&
                Arrays.equals(monster.getAlignment(), alignment) && monster.getName().equals(name) && monster.getDescription().equals(description)&&
                monster.getCreatureClass().equals(creatureClass));
    }
}
