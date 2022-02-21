package GUI.Creatures;

import Creature.Helpers.Enums.Skills;
import GUI.Helpers.ComponentHelpers;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * Panel that allows the user to view or change each of the skill values for the creature.
 */
public class SkillFrame extends JFrame implements ActionListener {
    static final int WIDTH = 300;
    int HEIGHT = 950;
    JPanel skillsPanel;
    JSpinner[] statSpinners;
    JCheckBox enableAll;
    JButton saveButton;
    int[] skillValueArray;
    Skills[] skillEnumArray;
    //Constructors
    public SkillFrame(){

        generateSkillPanels();
        add(skillsPanel);
        pack();
        setVisible(true);

    }

    private void generateSkillPanels(){
        skillsPanel = new JPanel();
        statSpinners = new JSpinner[Skills.values().length];

        enableAll = new JCheckBox("Enable all");
        enableAll.addActionListener(this);

        int counter = 0;
        for(Skills skill : Skills.values()){
            JPanel newPanel = new JPanel(new BorderLayout());
            newPanel.setName(skill.name());
            String[] skillNameAr = skill.name().split("_");
            StringBuilder skillName = null;
            for(String subName : skillNameAr){
                String capital = subName.substring(0,1);
                subName = subName.toLowerCase();
                subName = capital + subName.substring(1);

                if(skillName == null){
                    skillName = new StringBuilder(subName);
                }else {
                    skillName.append(" ").append(subName);
                }

            }
            assert skillName != null;
            newPanel.setBorder(new TitledBorder(skillName + " (" +skill.getParentAbility().substring(0,3).toLowerCase()+")"));
            newPanel.setToolTipText(skill.getDescription());

            JSpinner jspinner = new JSpinner(new SpinnerNumberModel(0,-20,+20,1));
            ComponentHelpers.hideSpinnerArrow(jspinner,30);
            jspinner.setName(skillName +" spinner");
            statSpinners[counter] = jspinner;


            newPanel.add(statSpinners[counter],BorderLayout.WEST);
            newPanel.setPreferredSize(new Dimension(WIDTH,40));


            skillsPanel.add(newPanel);
            counter++;

            skillsPanel.setLayout(new BoxLayout(skillsPanel, BoxLayout.Y_AXIS));

        }
        JPanel savePanel = new JPanel();
        savePanel.setPreferredSize(new Dimension(WIDTH, 30));
        saveButton = new JButton("Save");
        saveButton.setMaximumSize(new Dimension(WIDTH/4, 30));
        saveButton.addActionListener(this);
        savePanel.add(saveButton);
        skillsPanel.add(savePanel);
    }

    private void applySkills(){
        // count the non zero value spinners
        int nonZeroCount =0;
        for (JSpinner statSpinner : statSpinners) {
            if ((int) statSpinner.getValue() != 0) {
                nonZeroCount++;
            }
        }
        // Initialize non-zero value sized arrays for output
        skillValueArray = new int[nonZeroCount];
        skillEnumArray = new Skills[nonZeroCount];

        //populate arrays with the values and their corresponding skills
        int j = 0;
        for(int i = 0; i < statSpinners.length;i++){
            if((int) statSpinners[i].getValue() != 0){
                skillValueArray[j] = (int) statSpinners[i].getValue();
                skillEnumArray[j] = Skills.values()[i];
                j++;
            }
        }

        //debugging output
        for(int i = 0 ; i < skillValueArray.length;i++){
            System.out.println("Skill " + skillEnumArray[i].name() + " = " + skillValueArray[i]);
        }



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            applySkills();
        }
    }
}
