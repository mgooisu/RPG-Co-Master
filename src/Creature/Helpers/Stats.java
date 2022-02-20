package Creature.Helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * This class describes an object that represents all the standard character statistics for
 * dungeons and dragons 5th edition and many other rpg systems
 */
public class Stats implements Serializable {
    private int strength, dexterity, constitution, intelligence, wisdom, charisma;

    public Stats(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }


    //Modifier Values
    public static int getMod(int inputStat){
        if(inputStat == 1){
            return -5;
        }
        inputStat -= inputStat%2;

       return inputStat/2-5;
    }

    //Modifier Handles for Each Ability score
    public int getStrengthMod(){
        return getMod(strength);
    }
    public int getDexterityMod(){
        return getMod(dexterity);
    }
    public int getConstitutionMod(){
        return getMod(constitution);
    }
    public int getIntelligenceMod(){
        return getMod(intelligence);
    }
    public int getWisdomMod(){
        return getMod(wisdom);
    }
    public int getCharismaMod(){
        return getMod(charisma);
    }

}

class StatsTesting{
    Stats stats;
    @BeforeEach
    void setup(){
        stats = new Stats(10,10,10,10,10,10);

    }

    @Test
    void modTesting(){
        int[] scoreArray = {-5,-4,-4,-3,-3,-2,-2,-1,-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10};
        for(int i = 1; i<=30;i++){

            stats.setStrength(i);
            Assertions.assertEquals(stats.getStrengthMod(),scoreArray[i-1]);

        }


    }
}
