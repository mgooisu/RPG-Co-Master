package GUI.Elements.DeletableList;

import Creature.Actions.Actions;
import Creature.Features;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Exceptions.CreatureException;

import java.util.ArrayList;

// disgusting datatype generifier - Allows for the storage of the arraylists used in DeletableList
public class CreatureElArrayList {
    //Private Data - One for each case
    private ArrayList<Damage> damageArrayList;
    private ArrayList<Condition> conditionArrayList;
    private ArrayList<Features> featuresArrayList;
    private ArrayList<Actions> actionsArrayList;
         //For use in generic cases
    private ArrayList<String> stringArrayList;

    // Informs the type of data that can be added and restricts invalid adding
    private final Object dummy;


    //Constructors
    public CreatureElArrayList(ArrayList<Damage> damageArrayList, Damage dummy){
        this.damageArrayList = damageArrayList;
        this.dummy = dummy;
    }
    public CreatureElArrayList(ArrayList<Condition> conditionArrayList, Condition dummy){
        this.conditionArrayList = conditionArrayList;
        this.dummy = dummy;

    }
    public CreatureElArrayList(ArrayList<Features> featuresArrayList, Features dummy){
        this.featuresArrayList = featuresArrayList;
        this.dummy = dummy;

    }
    public CreatureElArrayList(ArrayList<String> stringArrayList, String dummy){
        this.stringArrayList = stringArrayList;
        this.dummy = dummy;
    }
    public CreatureElArrayList(ArrayList<Actions> actionsArrayList, Actions dummy){
        this.actionsArrayList = actionsArrayList;
        this.dummy = dummy;
    }


    //Getters
    public ArrayList<Damage> getDamageArrayList(){ return damageArrayList;}
    public ArrayList<Condition> getConditionArrayList(){return conditionArrayList;}
    public ArrayList<Features> getFeaturesArrayList() {return featuresArrayList;
    }
    public ArrayList<String> getStringArrayList() { return stringArrayList;
    }

    //Adder
    public void addElement(Object input){
        //Damage Case
        if(input.getClass() == Damage.class && dummy.getClass() == Damage.class){
            damageArrayList.add((Damage) input);
            return;
        }
        //Condition Case
        if(input.getClass() == Condition.class && dummy.getClass() == Condition.class){
            conditionArrayList.add((Condition) input);
            return;
        }
        //Features Case
        if(input.getClass() == Features.class && dummy.getClass() == Features.class){
            featuresArrayList.add((Features) input);
            return;
        }
        //String Case
        if(input.getClass() == String.class && dummy.getClass() == String.class){
            stringArrayList.add((String) input);
            return;
        }

        try {
            throw new CreatureException("Tried to add an object of a different type to arraylist");
        } catch (CreatureException e) {
            e.printStackTrace();
        }
    }
}
