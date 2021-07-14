package Creature.Actions;

public class Actions {
    /**
     * Actions encompass all of the "things" creatures can do in combat. Casting spells, attacking,wielding weapons etc.
     * Anything that uses an action or a bonus action are contained in this class.
     */
    public enum ActionType{MELEE_ATTACK, RANGED_ATTACK}
    private final String name, description;
    private ActionType actionType;
    public Actions(String name, String description,ActionType actionType){
        this.name = name; this.description = description;
        this.actionType = actionType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
