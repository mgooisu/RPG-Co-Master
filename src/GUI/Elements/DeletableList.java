package GUI.Elements;

import Creature.Actions.Actions;
import Creature.Actions.Attack;
import Creature.Features;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Exceptions.CreatureException;
import GUI.Elements.Buttons.DeleteButton;
import Helpers.DiceObject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletableList extends JPanel implements ActionListener {
    public DeletableList(String id, ArrayList<Damage> damageList, Damage dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(id);
        for(Damage damage: damageList){
            addElement(damage);
        }

    }
    public DeletableList(String id, ArrayList<String> stringList, String dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(id);
        for(String string: stringList){
            addElement(string);
        }

    }
    public DeletableList(String id, ArrayList<Condition> conditionList, Condition dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(id);
        for(Condition condition: conditionList){
            addElement(condition);
        }

    }

    public DeletableList(String id, ArrayList<Features> featuresList, Features dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(id);
        for(Features features: featuresList){
            addElement(features);
        }

    }
    public DeletableList(String id, ArrayList<Actions> actionsList, Actions dummy){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName(id);
        for(Actions actions: actionsList){
            addElement(actions);
        }

    }


    public void addElement(String input){
        if(checkElements(input)) {
            JLabel stringLabel = new JLabel(input);
            DeleteButton deleteThis = new DeleteButton();
            deleteThis.addActionListener(this);
            JPanel stringTile = new JPanel();
            stringTile.setName(input);
            stringTile.setLayout(new BoxLayout(stringTile, BoxLayout.X_AXIS));
            stringTile.add(stringLabel);
            stringTile.add(deleteThis);
            add(stringTile);
            revalidate();
            repaint();
        }

    }
    public void addElement(Damage input){
        if(checkElements(input.name())) {
            JLabel damageLabel = new JLabel(input.name());
            DeleteButton deleteThis = new DeleteButton();
            deleteThis.addActionListener(this);
            JPanel damageTile = new JPanel();
            damageTile.setName(input.name());
            damageTile.setLayout(new BoxLayout(damageTile, BoxLayout.X_AXIS));
            damageTile.add(damageLabel);
            damageTile.add(deleteThis);
            add(damageTile);
            revalidate();
            repaint();
        }

    }
    public void addElement(Condition input){
        if(checkElements(input.name())) {
            JLabel conditionLabel = new JLabel(input.name());
            DeleteButton deleteThis = new DeleteButton();
            deleteThis.addActionListener(this);
            JPanel conditionTile = new JPanel();
            conditionTile.setName(input.name());
            conditionTile.setLayout(new BoxLayout(conditionTile, BoxLayout.X_AXIS));
            conditionTile.add(conditionLabel);
            conditionTile.add(deleteThis);
            add(conditionTile);
            revalidate();
            repaint();
        }

    }

    public void addElement(Features input){
        if(checkElements(input.getName())){
            JTextField featuresDesc = new JTextField(input.getDescription());
            DeleteButton deleteThis = new DeleteButton();
            deleteThis.addActionListener(this);
            JPanel featuresTile = new JPanel();
            featuresTile.setName(input.getName());
            featuresTile.setLayout(new BoxLayout(featuresTile, BoxLayout.X_AXIS));

            featuresTile.setBorder(new TitledBorder(input.getName()));
            featuresTile.add(featuresDesc);
            featuresTile.add(deleteThis);
            featuresDesc.setEditable(false);
            add(featuresTile);
            revalidate();
            repaint();
        }
    }
    public void addElement(Actions input){
        if(checkElements(input.getName())){
            JTextField actionsDesc = new JTextField(input.getDescription());
            actionsDesc.setEditable(false);
            DeleteButton deleteThis = new DeleteButton();
            deleteThis.addActionListener(this);
            JPanel actionsTile = new JPanel();
            actionsTile.setName(input.getName());
            actionsTile.setLayout(new BoxLayout(actionsTile, BoxLayout.X_AXIS));
            actionsTile.setBorder(new TitledBorder(input.getName()));

            JPanel actionsWords = new JPanel();
            actionsWords.setLayout(new BoxLayout(actionsWords, BoxLayout.Y_AXIS));

            //Attacks
            if(input.getActionType() == Actions.ActionType.MELEE_ATTACK|| input.getActionType()== Actions.ActionType.RANGED_ATTACK){
                Attack attackInput = (Attack) input;
                String rangeString, diceString;
                DiceObject dice  = attackInput.getDiceObject();
                if(input.getActionType() == Actions.ActionType.RANGED_ATTACK){
                    rangeString = "range "+attackInput.getRange().getClose()+"/"+attackInput.getRange().getFar();
                }
                else{
                    rangeString = "reach "+ attackInput.getRange().getClose();
                }
                diceString = dice.getAmount()+"d"+dice.getType()+"+"+dice.getModifier();
                actionsDesc = new JTextField(
                        attackInput.getAddToHit() +" to hit, "+rangeString+" ft., "+attackInput.getTarget()+
                                " target."+"Hit: "+diceString+" "+attackInput.getDamage()+" damage.");


            }

            actionsWords.add(actionsDesc);
            actionsDesc.setEditable(false);
            actionsTile.add(actionsWords);
            actionsTile.add(deleteThis);
            add(actionsTile);


            revalidate();
            repaint();
        }
    }

    boolean checkElements(String newElement){
        if(newElement.length()<1){
            try {
                throw new CreatureException("Name too short");
            } catch (CreatureException e) {
                e.printStackTrace();
            }
            return false;
        }

        for(Component c: getComponents()){
            if(c.getName().equals(newElement)){
                try {
                    throw new CreatureException("Element already exists!");
                } catch (CreatureException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    public void addElement(Object input){
        if (input instanceof String) {
            addElement((String) input);
            return;
        }
        if(input instanceof Damage){
            addElement((Damage) input);
            return;
        }
        if(input instanceof Condition){
            addElement((Condition) input);
            return;
        }
        if(input instanceof Features){
            addElement((Features) input);
            return;
        }
        if(input instanceof Actions){
            addElement((Actions) input);
            return;
        }
        try {
            throw new CreatureException("Invalid type");
        } catch (CreatureException e) {
            e.printStackTrace();
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
