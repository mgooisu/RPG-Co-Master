package GUI.Creatures;

import Creature.Helpers.Alignment;
import Creature.Monster;
import Exceptions.CreatureException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreatureGenFrame extends JFrame implements ActionListener, ChangeListener {
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



    Monster getMonster(){
        //new Monster()

        return null;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass() == JButton.class){
            JButton button = (JButton) e.getSource();
            if(button == submit){
                if(baseCreatureGenPanel.combatCheck.isSelected()){
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
