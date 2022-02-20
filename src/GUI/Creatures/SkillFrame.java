package GUI.Creatures;

import Creature.Helpers.Enums.Skills;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Panel that allows the user to view or change each of the skill values for the creature.
 */
public class SkillFrame extends JFrame{
    //TODO sizing to fit
    static final int WIDTH = 300;
    int HEIGHT = 600;
    JPanel skillsPanel;
    //Constructors
    public SkillFrame(){
        skillsPanel = new JPanel();
        generateSkillPanels();
        skillsPanel.setLayout(new BoxLayout(skillsPanel, BoxLayout.Y_AXIS));
        add(skillsPanel);


        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        pack();
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                setSize(new Dimension(WIDTH,getHeight()));
            }
        });

    }

    private void generateSkillPanels(){
        for(Skills skill : Skills.values()){
            JPanel newPanel = new JPanel();
            newPanel.setName(skill.name());
            newPanel.setBorder(new TitledBorder(skill.name()));
            newPanel.add(new JLabel(skill.name()));
            newPanel.setSize(new Dimension(WIDTH,HEIGHT/Skills.values().length-10 ));
            skillsPanel.add(newPanel);

        }
    }


}
