package GUI.Elements;

import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import GUI.Elements.Buttons.DeleteButton;

import javax.swing.*;
import java.util.ArrayList;

public class DeletableList extends JPanel {
    String id;
    public DeletableList(String id, ArrayList<Damage> damageList, Damage dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.id = id;
        for(Damage damage: damageList){
            JLabel damageLabel = new JLabel(damage.name());
            DeleteButton deleteThis = new DeleteButton();
            JPanel damageTile = new JPanel();
            damageTile.setLayout(new BoxLayout(damageTile,BoxLayout.X_AXIS));
            damageTile.add(damageLabel);
            damageTile.add(deleteThis);
            add(damageTile);
        }

    }
    public DeletableList(String id, ArrayList<String> stringList, String dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.id = id;
        for(String string: stringList){
            JLabel stringLabel = new JLabel(string);
            DeleteButton deleteThis = new DeleteButton();
            JPanel stringTile = new JPanel();
            stringTile.setLayout(new BoxLayout(stringTile,BoxLayout.X_AXIS));
            stringTile.add(stringLabel);
            stringTile.add(deleteThis);
            add(stringTile);
        }

    }
    public DeletableList(String id, ArrayList<Condition> conditionList, Condition dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.id = id;
        for(Condition condition: conditionList){
            JLabel stringLabel = new JLabel(condition.name());
            DeleteButton deleteThis = new DeleteButton();
            JPanel conditionTile = new JPanel();
            conditionTile.setLayout(new BoxLayout(conditionTile,BoxLayout.X_AXIS));
            conditionTile.add(stringLabel);
            conditionTile.add(deleteThis);
            add(conditionTile);
        }

    }
}
