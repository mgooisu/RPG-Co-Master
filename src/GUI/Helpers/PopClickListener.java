package GUI.Helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Right Click Context Menu shamelessly stolen from
 * https://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing
 */
public class PopClickListener extends MouseAdapter {
    JMenuItem[] jMenuItems;
    public PopClickListener(JMenuItem[] jMenuItems){
        super();
        this.jMenuItems = jMenuItems;
    }
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        for(JMenuItem jMenuItem: jMenuItems){
            popupMenu.add(jMenuItem);
        }

        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

}

