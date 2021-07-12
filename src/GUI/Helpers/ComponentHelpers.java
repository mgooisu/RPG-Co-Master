package GUI.Helpers;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ComponentHelpers {

    /**
     * Centers the text of all Jlabels in the panel
     * @param panel - the JPanel object containing JLabels requiring centering
     */
    public static void centerLabelsInPanel(JPanel panel){
        for(Component component: panel.getComponents()){
            if(component.getClass().equals(JLabel.class)){
                JLabel label = (JLabel) component;
                label.setHorizontalAlignment(0);
            }
        }
    }
}
