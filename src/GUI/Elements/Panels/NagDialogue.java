package GUI.Elements.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Generic Yes|No dialogue to check if the user is sure of a permanent operation like file deletion
 */
public class NagDialogue extends JDialog {
    YesButton yes;
    NoButton no;

    /**
     * Constructs the dialogue
     *
     * @param actionListener contained within the gui element that is to be deleted
     */
    public NagDialogue(ActionListener actionListener, String message) {
        setAlwaysOnTop(true);
        setLayout(new BorderLayout());
        JLabel text = new JLabel(message);
        yes = new YesButton("Yes");
        yes.addActionListener(actionListener);

        no = new NoButton("no");
        no.addActionListener(actionListener);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yes);
        buttonPanel.add(no);

        add(text, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public static class NoButton extends JButton {
        public NoButton(String message) {
            super(message);
        }
    }

    public static class YesButton extends JButton {
        public YesButton(String message) {
            super(message);
        }
    }
}
