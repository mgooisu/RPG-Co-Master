package GUI.Creatures;

import Exceptions.CreatureException;

import javax.swing.*;
import java.io.IOException;

public class CreatureGenFrame extends JFrame {
    BaseCreatureGenPanel baseCreatureGenPanel;
    CombatCreatureGenPanel combatCreatureGenPanel;
    JTabbedPane creatureTabbedPane;

    public CreatureGenFrame() throws CreatureException, IOException, ClassNotFoundException {
        baseCreatureGenPanel = new BaseCreatureGenPanel();
        combatCreatureGenPanel = new CombatCreatureGenPanel();
        creatureTabbedPane = new JTabbedPane();
        creatureTabbedPane.addTab("Base Creature",baseCreatureGenPanel);
        creatureTabbedPane.addTab("Combat Creature",combatCreatureGenPanel);
        creatureTabbedPane.setSelectedIndex(1);
        add(creatureTabbedPane);
    }
}
