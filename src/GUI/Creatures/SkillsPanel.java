package GUI.Creatures;

import Creature.Creature;
import Creature.Helpers.Enums.Skills;
import Creature.Helpers.Stats;
import GUI.Helpers.ComponentHelpers;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel that allows the user to view or change each of the skill values for the creature.
 */
public class SkillsPanel extends JPanel implements ActionListener {
    static final int WIDTH = 300;
    int HEIGHT = 950;
    JPanel skillsPanel;
    JSpinner[] statSpinners;
    JButton saveButton, genAllButton, clearAllButton;
    AutoGenButton[] generationButtons;
    int[] skillValueArray;
    Skills[] skillEnumArray;
    int creatureLevel;

    //Optional constructor data
    Stats stats;
    Creature creature;


    //Constructors
    private void SkillPanelConstruct(){

        generateSkillPanels();
        add(skillsPanel);





    }

    public SkillsPanel(Stats stats){
        creatureLevel = 0;
        this.stats = stats;
        SkillPanelConstruct();
    }
    public SkillsPanel(Stats stats,int creatureLevel){
        this.creatureLevel = creatureLevel;
        this.stats = stats;
        SkillPanelConstruct();
    }





    private void generateSkillPanels(){
        skillsPanel = new JPanel();
        statSpinners = new JSpinner[Skills.values().length];
        generationButtons = new AutoGenButton[Skills.values().length];

        //Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        clearAllButton = new JButton("Clear All");
        clearAllButton.setToolTipText("Resets all skills to 0");
        clearAllButton.addActionListener(this);

        genAllButton = new JButton("Generate all");
        genAllButton.setToolTipText("Sets all skills based on the creature's stats");
        genAllButton.addActionListener(this);

        topPanel.add(genAllButton,BorderLayout.EAST);
        topPanel.add(clearAllButton,BorderLayout.WEST);
        topPanel.setPreferredSize(new Dimension(WIDTH,20));

        skillsPanel.add(topPanel);


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

            //Automatic Generation panel
            JPanel autoPanel = new JPanel(new BorderLayout());
            if(creatureLevel >0 ) {
                ProficiencyCheck proficiencyCheck = new ProficiencyCheck(skill);
                autoPanel.add(proficiencyCheck, BorderLayout.WEST);
                generationButtons[counter] = new AutoGenButton(skill, stats, jspinner,proficiencyCheck);
            }
            else{
                generationButtons[counter] = new AutoGenButton(skill, stats, jspinner);

            }

            autoPanel.add(generationButtons[counter], BorderLayout.EAST);
            newPanel.add(autoPanel,BorderLayout.EAST);
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

    private void clearAllSkills(){
        for(JSpinner jSpinner: statSpinners){
            jSpinner.setValue(0);
        }
    }

    private void generateAllSkills(){
        for(AutoGenButton autoGenButton: generationButtons){
            autoGenButton.setSpinner();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            applySkills();
        }
        if(e.getSource()== clearAllButton){
            clearAllSkills();
        }
        if (e.getSource() == genAllButton){
            generateAllSkills();
        }
    }

    private class AutoGenButton extends JButton implements ActionListener {
        Skills skills;
        Stats stats;
        JSpinner jSpinner;
        ProficiencyCheck proficiencyCheck;

        AutoGenButton(Skills skills, Stats stats,JSpinner jSpinner, ProficiencyCheck proficiencyCheck){
            setText("Auto Gen");
            setToolTipText("Automatically generates the appropriate skill level based on the creature" +
                    "stats");
            addActionListener(this);
            this.skills = skills;
            this.stats = stats;
            this.jSpinner = jSpinner;
            this.proficiencyCheck = proficiencyCheck;
        }
        AutoGenButton(Skills skills, Stats stats,JSpinner jSpinner){
            setText("Auto Gen");
            setToolTipText("Automatically generates the appropriate skill level based on the creature" +
                    "stats");
            addActionListener(this);
            this.skills = skills;
            this.stats = stats;
            this.jSpinner = jSpinner;
            this.proficiencyCheck = null;
        }

        /**
         *
         * @return the modifier of the parent stat of the skill. i.e. the strength mod for athletics etc
         */
        int extractParentModifier() {
            if (skills == null || stats == null) return 0;

            switch (skills.getParentAbility()) {
                case "Strength" -> {
                    return stats.getStrengthMod();
                }
                case "Constitution" -> {
                    return stats.getConstitutionMod();
                }
                case "Dexterity" -> {
                    return stats.getDexterityMod();
                }
                case "Intelligence" -> {
                    return stats.getIntelligenceMod();
                }
                case "Wisdom" -> {
                    return stats.getWisdomMod();
                }
                case "Charisma" -> {
                    return stats.getCharismaMod();
                }

                default -> throw new IllegalStateException("Unexpected value: " + skills.getParentAbility());

            }
        }

        public void setSpinner(){
            // get the parent modifier from the stats
            int skillValue = extractParentModifier();
            if(proficiencyCheck != null) {
                if (proficiencyCheck.isSelected()) {
                    //Formula level - > proficiency mod
                    skillValue += (int) Math.ceil(1 + (creatureLevel / 4.0));

                }
            }
            jSpinner.setValue(skillValue);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            setSpinner();
        }
    }
    private static class ProficiencyCheck extends JCheckBox{
        ProficiencyCheck(Skills skills){
            setText("Proficiency");
            setToolTipText("Determines if the creature is proficient in "+skills.name()+", doubling the effect" +
                    "their stats have on the skill");
        }
    }
}
