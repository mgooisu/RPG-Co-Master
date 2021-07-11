package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

    /**
     * Test Main method for generating GUI, will be replaced when a proper build system is in place
     */

    public static void main(String[] args){
        new TestGui();
    }

    public TestGui(){
        add(new CreaturePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}


/**
 * Panel that contains information and controls pertaining to a single creature
 */
class CreaturePanel extends JPanel {

    public CreaturePanel(){
        setLayout(new GridLayout(3,1));
        JPanel title = new JPanel();
        title.add(new JLabel("This title should be centred"));
        add(title);

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(2,2));
        String text = "test";

        content.add(new JLabel(text),SwingConstants.CENTER);
        content.add(new JLabel(text));
        content.add(new JLabel(text));
        content.add(new JLabel(text));

        Border border = BorderFactory.createBevelBorder(0);
        for(Component component:content.getComponents()){
            JLabel label = (JLabel) component;
            label.setBorder(border);
            label.setHorizontalAlignment(0);
        }
        add(content);
    }
}
