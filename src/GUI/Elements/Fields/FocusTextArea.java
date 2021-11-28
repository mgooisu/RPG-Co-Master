package GUI.Elements.Fields;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusTextArea extends JTextArea implements FocusListener {

    public FocusTextArea(String text){
        super(text);
        addFocusListener(this);
    }

    public FocusTextArea(){
        super();
        addFocusListener(this);
    }


    @Override
    public void focusGained(FocusEvent e) {
        selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {
        select(0,0);
    }
}
