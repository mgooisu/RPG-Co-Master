package GUI.Elements;

import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import GUI.Elements.Buttons.DeleteButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletableList extends JPanel implements ActionListener {
    public DeletableList(String id, ArrayList<Damage> damageList, Damage dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(id);
        for(Damage damage: damageList){
            JLabel damageLabel = new JLabel(damage.name());
            DeleteButton deleteThis = new DeleteButton();
            deleteThis.addActionListener(this);
            JPanel damageTile = new JPanel();
            damageTile.setName(damage.name());
            damageTile.setLayout(new BoxLayout(damageTile,BoxLayout.X_AXIS));
            damageTile.add(damageLabel);
            damageTile.add(deleteThis);
            add(damageTile);
        }

    }
    public DeletableList(String id, ArrayList<String> stringList, String dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(id);
        for(String string: stringList){
            JLabel stringLabel = new JLabel(string);
            DeleteButton deleteThis = new DeleteButton();
            deleteThis.addActionListener(this);
            JPanel stringTile = new JPanel();
            stringTile.setName(string);
            stringTile.setLayout(new BoxLayout(stringTile,BoxLayout.X_AXIS));
            stringTile.add(stringLabel);
            stringTile.add(deleteThis);
            add(stringTile);
        }

    }
    public DeletableList(String id, ArrayList<Condition> conditionList, Condition dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(id);
        for(Condition condition: conditionList){
            JLabel stringLabel = new JLabel(condition.name());
            DeleteButton deleteThis = new DeleteButton();
            deleteThis.addActionListener(this);
            JPanel conditionTile = new JPanel();
            conditionTile.setName(condition.name());
            conditionTile.setLayout(new BoxLayout(conditionTile,BoxLayout.X_AXIS));
            conditionTile.add(stringLabel);
            conditionTile.add(deleteThis);
            add(conditionTile);
        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel clickedPanel = (JPanel) ((JButton) e.getSource()).getParent();
        JPanel parentPanel = (JPanel) clickedPanel.getParent();

        if(e.getSource().getClass() == DeleteButton.class){
            parentPanel.remove(clickedPanel);

            parentPanel.revalidate();
            parentPanel.repaint();
        }




    }
}
