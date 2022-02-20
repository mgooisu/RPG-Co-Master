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
    JCheckBox[] enabled;
    JCheckBox enableAll;
    JButton saveButton;
    int[] skillArray;
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
        enabled = new JCheckBox[Skills.values().length];

        enableAll = new JCheckBox("Enable all");
        enableAll.addActionListener(this);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(enableAll, BorderLayout.EAST);
        skillsPanel.add(topPanel);
        topPanel.setPreferredSize(new Dimension(WIDTH,20));

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
            enabled[counter] = new JCheckBox("Enabled");
            enabled[counter].setName(skillName + " enabled");


            newPanel.add(statSpinners[counter],BorderLayout.WEST);
            newPanel.add(enabled[counter],BorderLayout.EAST);
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
        Skills[] allSkills = Skills.values();
        int[] allValues = new int[allSkills.length];
        int skillArrayLength = 0;
        //Iterates through all the spinners to get a list of values
        for(int i = 0; i< allValues.length; i++){
            int value;
            //Sets deselected spinner values to 0
            if(enabled[i].isSelected()){
                value = (int) statSpinners[i].getValue();
                //counts the non-zero values for the output array
                if(value != 0){
                    skillArrayLength++;
                }
            }else{
                value = 0;
                allSkills[i] = null;
            }
            allValues[i] = value;
        }
        // Iterates through the list of values from the spinners and populates an array with the non-zero values
        int[] outputArray = new int[skillArrayLength];
        int o  = 0;
        for (int allValue : allValues) {
            if (allValue != 0) {
                outputArray[o] = allValue;
                o++;
            }
        }
        skillArray = outputArray;


        //Iterates through the new skill array and creates a new one, omitting the null values
        o = 0;
        for (Skills allSkill : allSkills) {
            if (allSkill != null) {
                o++;
            }
        }
        skillEnumArray = new Skills[o];
        o = 0;
        for (Skills allSkill : allSkills) {
            if (allSkill != null) {
                skillEnumArray[o] = allSkill;
                o++;
            }
        }
        //output testing preview TODO remove
        for(int i = 0; i < skillArray.length; i++){
            System.out.println(skillEnumArray[i].name() + ": " + skillArray[i]);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enableAll){
            for(JCheckBox enable : enabled){
                enable.setSelected(enableAll.isSelected());
            }
        }

        if(e.getSource() == saveButton){
            applySkills();
        }
    }
}
