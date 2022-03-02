package GUI.Campaign;

import Campaign.Campaign;
import Encounter.Encounter;
import Exceptions.EncounterException;
import GUI.Elements.Buttons.DeleteButton;
import GUI.Encounter.EncounterFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Frame that contains a list of encounters, methods to create or delete encounters, as well as a system to read/write
 * those encounters to a campaign file. The user should also be able to save and load the campaign file to any name.
 */
public class CampaignFrame extends JFrame {
    //Data
    ArrayList<Encounter> encounters = new ArrayList<Encounter>();

    //Gui
    JScrollPane encounterScrollPane;
    EncounterScrollPanePanel encounterScrollPanePanel;

    //Constructor
    public CampaignFrame(Campaign campaign){
        setLayout(new BorderLayout());
        setTitle(campaign.getCampaignName());


        //Todo pull encounters from a file to populate the campaign
        encounters.add(new Encounter("Test Encounter"));
        encounterScrollPanePanel= new EncounterScrollPanePanel();

        encounterScrollPane = new JScrollPane(encounterScrollPanePanel);
        encounterScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        //Build frame
        add(encounterScrollPane,BorderLayout.WEST);

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
            for(Encounter encounter: encounters){
                addEncounter(encounter);
            }
        }

        void addEncounter(Encounter encounter){
            add(new EncounterPanel(encounter));
            repaint();
            revalidate();
        }

        void removeEncounter(Encounter encounter){
            encounters.remove(encounter);
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
                encounterScrollPanePanel.removeEncounter(encounter);
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

    //Public Methods
    public void addEncounter(Encounter encounter){
        encounters.add(encounter);
        encounterScrollPanePanel.addEncounter(encounter);
    }
    public void removeEncounter(Encounter encounter){
        encounters.remove(encounter);
        encounterScrollPanePanel.removeEncounter(encounter);
    }


}
