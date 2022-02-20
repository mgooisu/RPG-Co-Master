package GUI.Creatures.Generation;

import Creature.BaseCreature;
import Creature.Creature;
import Creature.Monster;
import Creature.CreatureListHandler;
import Exceptions.CreatureException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class CreatureGenFrame extends JFrame implements ActionListener, ChangeListener {
    CreatureListHandler creatureListHandler = new CreatureListHandler();
    //The proper OO way to have submit buttons on both of these would be to create an parent object that they inherit
    //Or I could make it scuffed and create panels for both of them.... :)
    BaseCreatureGenPanel baseCreatureGenPanel;
    CombatCreatureGenPanel combatCreatureGenPanel;
    JPanel baseCreatureTopPanel, combatCreatureTopPanel;
    JTabbedPane creatureTabbedPane;
    JButton submit;

    public CreatureGenFrame() throws CreatureException, IOException, ClassNotFoundException {
        baseCreatureGenPanel = new BaseCreatureGenPanel();
        combatCreatureGenPanel = new CombatCreatureGenPanel();
        baseCreatureTopPanel = new JPanel(new BorderLayout());
        combatCreatureTopPanel = new JPanel(new BorderLayout());
        baseCreatureTopPanel.add(baseCreatureGenPanel,BorderLayout.CENTER);
        combatCreatureTopPanel.add(combatCreatureGenPanel,BorderLayout.CENTER);
        creatureTabbedPane = new JTabbedPane();
        creatureTabbedPane.addTab("Base Creature",baseCreatureTopPanel);
        creatureTabbedPane.addTab("Combat Creature",combatCreatureTopPanel);
        creatureTabbedPane.setSelectedIndex(0);
        creatureTabbedPane.addChangeListener(this);

        submit = new JButton("Add Creature");
        submit.addActionListener(this);

        baseCreatureTopPanel.add(submit,BorderLayout.PAGE_END);
        add(creatureTabbedPane);
    }




    Creature getCreature() throws CreatureException {
        Creature creature;
        if(baseCreatureGenPanel.getCombatCheck()){
             creature =new Monster(
                    null, baseCreatureGenPanel.getAlignment(), baseCreatureGenPanel.getDescription(),
                    baseCreatureGenPanel.getCreatureClass(), baseCreatureGenPanel.getIntegerHealth(),
                    baseCreatureGenPanel.getHpDice(),baseCreatureGenPanel.getAC(),baseCreatureGenPanel.getSpeed(),
                    baseCreatureGenPanel.getCreatureSize(), baseCreatureGenPanel.getSpecies(), baseCreatureGenPanel.getStats(),
                    baseCreatureGenPanel.getConditionImmunities(),baseCreatureGenPanel.getConditionResists(),
                    baseCreatureGenPanel.getDamageImmunities(),baseCreatureGenPanel.getDamageResistances(),
                    baseCreatureGenPanel.getDamageVulnerabilities()
            );

            ((Monster) creature).setActions(combatCreatureGenPanel.getActionArray());
            ((Monster)creature).setFeatures(combatCreatureGenPanel.getFeaturesArray());




        }
        else {
            creature = new BaseCreature(
                    null, baseCreatureGenPanel.getAlignment(), baseCreatureGenPanel.getDescription(),
                    baseCreatureGenPanel.getCreatureClass(), baseCreatureGenPanel.getIntegerHealth(),
                    baseCreatureGenPanel.getHpDice(), baseCreatureGenPanel.getAC(), baseCreatureGenPanel.getSpeed(),
                    baseCreatureGenPanel.getCreatureSize(), baseCreatureGenPanel.getSpecies(), baseCreatureGenPanel.getStats(),
                    baseCreatureGenPanel.getConditionImmunities(), baseCreatureGenPanel.getConditionResists(),
                    baseCreatureGenPanel.getDamageImmunities(), baseCreatureGenPanel.getDamageResistances(),
                    baseCreatureGenPanel.getDamageVulnerabilities()

            );
        }

        //shared elements
        creature.setLanguages(baseCreatureGenPanel.getLanguages());
        String[] oldSenses = baseCreatureGenPanel.getSenses();

        String[] newSenses = new String[oldSenses.length+1];
        newSenses[0] = "Passive Perception " + (creature.getStats().getWisdomMod()+10);
        System.arraycopy(oldSenses, 0, newSenses, 1, oldSenses.length);

        creature.setSenses(newSenses);


        return creature;



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass() == JButton.class){
            JButton button = (JButton) e.getSource();
            if(button == submit){
                Creature creature = null;
                try {
                    creature = getCreature();
                } catch (CreatureException creatureException) {
                    creatureException.printStackTrace();
                }
                if (creature != null) {
                    try {
                        creatureListHandler.addCreature(creature);
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }


            }
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JPanel selectedPanel =(JPanel) ((JTabbedPane) e.getSource()).getSelectedComponent();
        selectedPanel.add(submit,BorderLayout.PAGE_END);
    }
}
