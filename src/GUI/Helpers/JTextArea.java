package GUI.Helpers;

import javax.swing.*;
import java.awt.*;

/**
 * Override of Swing's JtextArea to disable user editing by default
 */
public class JTextArea extends javax.swing.JTextArea {
    public JTextArea(){
        super();
        super.setEditable(false);
        super.setOpaque(false);
    }
    public JTextArea(String content){
        super(content);
        super.setEditable(false);
        super.setOpaque(false);
    }
}
