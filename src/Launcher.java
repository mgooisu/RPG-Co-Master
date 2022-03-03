import GUI.Home.HomeFrame;

/**
 * The main launcher that starts the home frame
 */
public class Launcher {
    public static void main(String[] args){
        HomeFrame homeFrame = new HomeFrame();
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
        System.out.println("wa");
    }
}
