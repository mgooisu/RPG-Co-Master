package GUI.Helpers;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicSpinnerUI;
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

    /**
     * Yeets the ugly ass arrows from spinners
     * @param spinner the JSpinner object to have arrows removed
     */
    public static void hideSpinnerArrow(JSpinner spinner,int preferredWidth) {
            Dimension d = spinner.getPreferredSize();
            d.width =preferredWidth;
            spinner.setUI(new BasicSpinnerUI() {
                protected Component createNextButton() {
                    return null;
                }

                protected Component createPreviousButton() {
                    return null;
                }
            });
            spinner.setPreferredSize(d);

    }
}
