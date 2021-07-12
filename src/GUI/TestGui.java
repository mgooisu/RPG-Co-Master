package GUI;

import Creature.BaseCreature;
import Creature.Helpers.Alignment;
import Creature.Helpers.Enums.Condition;
import Creature.Helpers.Enums.Damage;
import Creature.Helpers.Enums.Size;
import Creature.Helpers.Stats;
import Creature.Helpers.Types.SpeciesInfo.Species;
import Creature.Helpers.Types.SpeciesInfo.SpeciesMapObjectHandler;
import Creature.Monster;
import GUI.Helpers.ComponentHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.jar.JarEntry;

/**
 * Transistional GUI for testing the base functionality of different elements of the application
 * The first tab will be dedicated to the construction of creature\monster entities and ensuring the correct
 * serialization of that information
 *
 * The second tab will allow for the construction of different encounters, which can be filled with instances of
 * creatures from the first tab and provide an interface for changing the state of those instanced creatures.
 * For example, there will be a healing interface and a damage interface, along with drop down menus for inflicting
 * and removing conditions.
 */
public class TestGui extends JFrame {
    SpeciesMapObjectHandler speciesMapObjectHandler;
    Species undead;
    Monster skeleton;
    Stats stats;

    /**
     * Test Main method for generating GUI, will be replaced when a proper build system is in place
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new TestGui();
    }

    public TestGui() throws IOException, ClassNotFoundException {
        speciesMapObjectHandler = new SpeciesMapObjectHandler();
        undead = speciesMapObjectHandler.readObject().getSpeciesHashMap().get("Undead");

        stats = new Stats(10,11,12,13,14,15);

        Condition[] conditionArray = new Condition[] {};

        Damage[] damageArray = new Damage[] {};

        skeleton = new Monster(null, Alignment.CombinedAlignment.Lawful_Evil,"A clinking, rattling" +
                "pile of ancient bones","skeleton",13,13,30, Size.MEDIUM,undead,stats,
                conditionArray,conditionArray,damageArray,damageArray,damageArray);

        add(new CreaturePanel(skeleton));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}


/**
 * Panel that contains information and controls pertaining to a single creature
 */
class CreaturePanel extends JPanel  {
BaseCreature creature;
JLabel healthStatus;
    public CreaturePanel(BaseCreature creature){
        this.creature = creature;
        setLayout(new BorderLayout());
        JPanel title = new JPanel();
        if(creature.getName() == null){
            title.add(new JLabel(creature.getCreatureClass()));
        }else {
            title.add(new JLabel("<html><h1 style='text-align:center'>"+creature.getName()+":<br>"+creature.getCreatureClass()+"</h1></html> "));
        }
        ComponentHelpers.centerLabelsInPanel(title);
        add(title, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(8,1,50,50));

        content.add(new InitiativePanel());



        healthStatus = new JLabel("Health: "+creature.getHealth()+"/"+creature.getMaxHP());
        content.add(healthStatus);
        content.add(new DamageHealingButtons());


        add(content,BorderLayout.CENTER);
    }



    /**
     * Test Buttons for health Display
     */
    class DamageHealingButtons extends JPanel implements ActionListener {
        JButton DamageButton, HealButton;
        JSpinner numberIn;
        DamageHealingButtons(){
            setLayout(new GridLayout(1,3));

            numberIn = new JSpinner(new SpinnerNumberModel(5,1,999,1));
            HealButton = new JButton("Heal");
            DamageButton = new JButton("Damage");

            HealButton.addActionListener(this);
            DamageButton.addActionListener(this);

            add(numberIn);
            add(DamageButton);
            add(HealButton);

        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            Component source = (Component) actionEvent.getSource();


            if(source.equals(HealButton)){
                creature.healthHealing((Integer) numberIn.getValue());
                healthStatus.setText("Health: "+creature.getHealth()+"/"+creature.getMaxHP());

            }

            if(source.equals(DamageButton)){
                creature.healthDamage((Integer) numberIn.getValue());
                int HP = creature.getHealth(), maxHP = creature.getMaxHP();
                String healthText;
                if(HP <= 0){
                    healthText ="Down";
                }else{
                    healthText = "Health: "+HP+"/"+maxHP;
                }
                System.out.println(creature.getHealth());
                healthStatus.setText(healthText);

            }

        }
    }

    class InitiativePanel extends JPanel implements ActionListener{
        JButton rollInitiative, setInitiative;
        JSpinner initiativeSpinner;
        JLabel initiativeValue;
        InitiativePanel(){

            setLayout(new BoxLayout(this,BoxLayout.X_AXIS));


            int initiative = creature.getInitiative();
            initiativeValue = new JLabel(("Initiative: "+initiative));

            rollInitiative = new JButton("Roll Initiative");
            rollInitiative.addActionListener(this);

            //Sub-panel for manually setting initiative
            int overallHeight = 50, overallWidth = 100;
            JPanel initiativeSetPanel = new JPanel();

            initiativeSetPanel.setLayout(new BoxLayout(initiativeSetPanel,BoxLayout.PAGE_AXIS));

            initiativeSpinner = new JSpinner(new SpinnerNumberModel(10,-4,30,1));
            initiativeSetPanel.add(initiativeSpinner);
            initiativeSetPanel.setAlignmentY(CENTER_ALIGNMENT);
            Dimension setSpinnerSize= new Dimension(overallWidth,overallHeight/2);
            initiativeSpinner.setMaximumSize(setSpinnerSize);
            initiativeSpinner.setSize(setSpinnerSize);

            setInitiative = new JButton("Set");
            setInitiative.addActionListener(this);
            Dimension setButtonDims = new Dimension(100,overallHeight/2);
            setInitiative.setMinimumSize(setButtonDims);


            Dimension initSetPanelSize = new Dimension(overallWidth,overallHeight);
            initiativeSetPanel.setSize(initSetPanelSize);
            initiativeSetPanel.setMaximumSize(initSetPanelSize);
            initiativeSetPanel.add(setInitiative);

           initiativeSetPanel.setBorder(new EtchedBorder());


            Dimension elementSpacing = new Dimension(10,overallHeight);

            add(initiativeValue);
            add(Box.createRigidArea(elementSpacing));
            add(initiativeSetPanel);
            add(Box.createRigidArea(elementSpacing));
            add(rollInitiative);



        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Component source = (Component) actionEvent.getSource();


            if(source.equals(rollInitiative)){
                creature.setInitiative(1+creature.getStats().getDexterityMod()+ (int)(Math.random()*20));
                initiativeValue.setText("Initiative: "+creature.getInitiative());
            }

            if(source.equals(setInitiative)){
                creature.setInitiative((int) initiativeSpinner.getValue());
                initiativeValue.setText("Initiative: "+creature.getInitiative());

            }

        }
    }

}


