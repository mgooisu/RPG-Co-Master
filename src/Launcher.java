import GUI.Home.HomeFrame;

import java.io.IOException;

/**
 * The main launcher that starts the home frame
 */
public class Launcher {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HomeFrame homeFrame = new HomeFrame();
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
    }
}
