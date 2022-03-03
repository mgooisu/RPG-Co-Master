package GUI.Campaign;

import Campaign.CampaignListHandler;
import Campaign.Campaign;
import Encounter.Encounter;
import Exceptions.EncounterException;
import GUI.Elements.Buttons.DeleteButton;
import GUI.Elements.Panels.NagDialogue;
import GUI.Encounter.EncounterFrame;
import GUI.Home.HomeFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Frame that contains a list of encounters, methods to create or delete encounters, as well as a system to read/write
 * those encounters to a campaign file. The user should also be able to save and load the campaign file to any name.
 */
public class CampaignFrame extends JFrame {
    //Data
    Campaign campaign;
    CampaignListHandler campaignListHandler;

    //Gui
    JScrollPane encounterScrollPane;
    EncounterScrollPanePanel encounterScrollPanePanel;
    CampaignMenuBar campaignMenuBar;

    //Constructor
    public CampaignFrame(Campaign campaign, HomeFrame homeFrame){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            campaignListHandler = new CampaignListHandler();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.campaign = campaign;
        setLayout(new BorderLayout());
        setTitle(campaign.getCampaignName());

        encounterScrollPanePanel= new EncounterScrollPanePanel();

        encounterScrollPane = new JScrollPane(encounterScrollPanePanel);
        encounterScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Build frame
        add(encounterScrollPane,BorderLayout.WEST);

        //Add Menu
        campaignMenuBar = new CampaignMenuBar(homeFrame);
        setJMenuBar(campaignMenuBar);

        repaint();
        revalidate();


    }

    //Private Classes
    /**
     * Scrollable pane for the encounters
     */
    private class EncounterScrollPanePanel extends JPanel{
        AddEncounterButton addEncounterButton;
        JPanel topPanel = new JPanel();
        EncounterScrollPanePanel(){
            super();
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            addEncounterButton = new AddEncounterButton();
            topPanel.add(addEncounterButton);
            topPanel.setMaximumSize(new Dimension(400,40));
            add(topPanel);
            for(Encounter encounter: campaign.getEncounters()){
                addEncounter(encounter);
            }
        }

        void addEncounter(Encounter encounter){
            add(new EncounterPanel(encounter));
            repaint();
            revalidate();
        }

        void removeEncounter(Encounter encounter){
            for(Component component : getComponents()){
                if(component.getClass() == EncounterPanel.class){
                    EncounterPanel encounterPanel = ((EncounterPanel) component);
                    if(encounterPanel.encounter == encounter){
                        remove(component);
                    }
                }
            }
            repaint();
            revalidate();
        }

        private class AddEncounterButton extends JButton implements ActionListener{
            AddEncounterButton(){
                setText("Add Encounter");
                addActionListener(this);
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEncounterPopup addEncounterPopup = new AddEncounterPopup();
                addEncounterPopup.setLocationRelativeTo(this);
                addEncounterPopup.setVisible(true);
            }
        }
    }

    /**
     * individual panel for each encounter
     */
    private class EncounterPanel extends JPanel implements ActionListener {
        Encounter encounter;
        DeleteButton deleteButton = new DeleteButton();
        NagDialogue deleteDialogue;
        final int WIDTH = 500, HEIGHT = 60;
        EncounterPanel(Encounter encounter){
            deleteButton.addActionListener(this);
            Dimension dimension = new Dimension(WIDTH,HEIGHT);
            setSize(dimension);
            setMaximumSize(dimension);
            this.encounter = encounter;
            setLayout(new BorderLayout());
            setBorder(new TitledBorder(encounter.getEncounterName()));
            add(deleteButton, BorderLayout.EAST);
            add(new GoToEncounterButton(), BorderLayout.CENTER);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().getClass() == DeleteButton.class){
                deleteDialogue = new NagDialogue(this,
                        "Are you sure about deleting"+encounter.getEncounterName()+"?");
                deleteDialogue.setLocationRelativeTo(this);
                deleteDialogue.setVisible(true);
            }
            if(e.getSource().getClass() == NagDialogue.NoButton.class){
                deleteDialogue.setVisible(false);
            }
            if(e.getSource().getClass() == NagDialogue.YesButton.class){
                removeEncounter(encounter);
                deleteDialogue.setVisible(false);
            }

        }

        /**
         * Button specifically for opening a new Frame for the encounter
         */
        private class GoToEncounterButton extends JButton implements ActionListener {
            GoToEncounterButton(){
                setText("Open");
                setToolTipText("Opens a new window containing this encounter");
                addActionListener(this);
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                encounter.summonFrame();
            }
        }
    }


    private class AddEncounterPopup extends JDialog implements ActionListener {
        JTextField encounterNameField;
        JButton addButton, cancelButton;
        AddEncounterPopup(){
            setLayout(new BorderLayout());
            setTitle("Add Encounter to Campaign");
            encounterNameField = new JTextField();
            addButton = new JButton("Add");
            cancelButton = new JButton("Cancel");

            addButton.addActionListener(this);
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addButton);
            buttonPanel.add(cancelButton);

            add(encounterNameField,BorderLayout.CENTER);
            add(buttonPanel,BorderLayout.SOUTH);
            pack();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == addButton){
                boolean errorThrown = false;
                if(encounterNameField.getText() == null|| encounterNameField.getText().equals("")){
                    try {
                        errorThrown = true;
                        throw new EncounterException("Encounter requires a name");
                    } catch (EncounterException ex) {
                        ex.printStackTrace();
                    }
                }
                //Todo check encounter name is unique in this campaign

                if(errorThrown){
                    return;
                }

                Encounter encounter = new Encounter(encounterNameField.getText());
                addEncounter(encounter);

            }
            setVisible(false);
        }
    }

    private class CampaignMenuBar extends JMenuBar implements ActionListener{
        HomeFrame homeFrame;
        JMenuItem homeButton;
        CampaignMenuBar(HomeFrame homeFrame){
            this.homeFrame = homeFrame;

            homeButton= new JMenuItem("Go Home" );
            homeButton.addActionListener(this);

            JMenu fileMenu = new JMenu("File");

            fileMenu.add(homeButton);



            add(fileMenu);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == homeButton){
                homeFrame.summonFrame();
                banishFrame();
            }
        }
    }
    //Private Methods
    private void banishFrame(){
        setVisible(false);
    }

    //Public Methods

    /**
     * Calls the add method for the panel
     * @param encounter for the panel
     */
    public void addEncounter(Encounter encounter)  {
        encounterScrollPanePanel.addEncounter(encounter);
        campaign.addEncounter(encounter);
        try {
            campaignListHandler.updateCampaign(campaign);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Calls the deletion method for the panel
     * @param encounter for the panel
     */
    public void removeEncounter(Encounter encounter){
        encounterScrollPanePanel.removeEncounter(encounter);
        campaign.removeEncounter(encounter);
        try {
            campaignListHandler.updateCampaign(campaign);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void summonFrame(){
        pack();
        setLocationRelativeTo(null);
        setSize(400,400);
        setVisible(true);

        //Band-aid fix for the frame dropping to the background
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);
    }


}
