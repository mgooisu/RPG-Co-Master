package GUI.Home;

import Campaign.*;
import Encounter.Encounter;
import Exceptions.CampaignException;
import Exceptions.EncounterException;
import GUI.Campaign.CampaignFrame;
import GUI.Elements.Buttons.DeleteButton;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Very similar interface to Campaign frame. Future versions could create a generified class for both of them
 */
public class HomeFrame extends JFrame {
    //Data
    ArrayList<Campaign> campaigns = new ArrayList<Campaign>();

    //Gui
    JScrollPane campaignScrollPane;
    CampaignScrollPanePanel campaignScrollPanePanel;
    final int WIDTH = 600, HEIGHT = 750;
    final Dimension dimension = new Dimension(WIDTH,HEIGHT);
    //Constructor
    public HomeFrame(){
        setSize(dimension);
        setLayout(new BorderLayout());
        setTitle("RPG Co-Master");

        //Todo pull campaigns from file
        campaigns.add(new Campaign("Test Campaign"));
        campaignScrollPanePanel = new CampaignScrollPanePanel();

        campaignScrollPane = new JScrollPane(campaignScrollPanePanel);
        campaignScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Build Frame
        add(campaignScrollPane,BorderLayout.WEST);
        repaint();
        revalidate();
    }

    private class CampaignScrollPanePanel extends JPanel{
        AddCampaignButton addCampaignButton;
        JPanel topPanel = new JPanel();
        CampaignScrollPanePanel(){
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            addCampaignButton = new AddCampaignButton();
            topPanel.add(addCampaignButton);
            topPanel.setMaximumSize(new Dimension(400,40));
            add(topPanel);
            for(Campaign campaign: campaigns){
                addCampaign(campaign);
            }


        }

        private void addCampaign(Campaign campaign) {
            add(new CampaignPanel(campaign));
            repaint();
            revalidate();
        }

        public void removeCampaign(Campaign campaign) {
            campaigns.remove(campaign);
            for(Component component : getComponents()){
                if(component.getClass() == CampaignPanel.class){
                    CampaignPanel campaignPanel = ((CampaignPanel) component);
                    if(campaignPanel.campaign == campaign){
                        remove(component);
                    }
                }
            }
            repaint();
            revalidate();
        }

        private class AddCampaignButton extends  JButton implements ActionListener {
            AddCampaignButton(){
                setText("Add Campaign");
                addActionListener(this);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                AddCampaignPopup addCampaignPopup = new AddCampaignPopup();
                addCampaignPopup.setLocationRelativeTo(this);
                addCampaignPopup.setVisible(true);
            }


        }
    }

    private class CampaignPanel extends JPanel implements ActionListener{
        Campaign  campaign;
        DeleteButton deleteButton = new DeleteButton();
        final int WIDTH = 500, HEIGHT = 60;

        CampaignPanel(Campaign campaign){
            deleteButton.addActionListener(this);
            Dimension dimension = new Dimension(WIDTH,HEIGHT);
            setSize(dimension); setMaximumSize(dimension);

            this.campaign = campaign;
            setLayout(new BorderLayout());
            setBorder(new TitledBorder(campaign.getCampaignName()));
            add(deleteButton,BorderLayout.EAST);
            add(new GoToCampaignButton(), BorderLayout.CENTER);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().getClass() == DeleteButton.class){
                campaignScrollPanePanel.removeCampaign(campaign);
            }
        }

        /**
         * For opening a new frame for the campaign
         */
        private class GoToCampaignButton extends JButton implements ActionListener {
            GoToCampaignButton(){
                setText("Open");
                setToolTipText("Opens a new window containing this campaign's encounters");
                addActionListener(this);
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                campaign.summonFrame();
            }
        }
    }

    private class AddCampaignPopup extends JDialog implements ActionListener{
        JTextField campaignNameTextField;
        JButton addButton, cancelButton;
        AddCampaignPopup(){
            setLayout(new BorderLayout());
            setTitle("Add Campaign");
            campaignNameTextField = new JTextField();
            addButton = new JButton("Add");
            cancelButton = new JButton("Cancel");

            addButton.addActionListener(this);
            cancelButton.addActionListener(this);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addButton);
            buttonPanel.add(cancelButton);

            add(campaignNameTextField,BorderLayout.CENTER);
            add(buttonPanel,BorderLayout.SOUTH);
            pack();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == addButton){
                boolean errorThrown = false;
                if(campaignNameTextField.getText() == null|| campaignNameTextField.getText().equals("")){
                    try {
                        errorThrown = true;
                        throw new CampaignException("Campaign requires a name");
                    } catch (CampaignException ex) {
                        ex.printStackTrace();
                    }
                }
                //Todo check encounter name is unique in this campaign

                if(errorThrown){
                    return;
                }

                Campaign campaign = new Campaign(campaignNameTextField.getText());
                addCampaign(campaign);
            }
            setVisible(false);
        }
    }

    //Public Methods
    public void addCampaign(Campaign campaign){
        campaigns.add(campaign);
        campaignScrollPanePanel.addCampaign(campaign);
    }

    public void removeCampaign(Campaign campaign){
        campaigns.remove(campaign);
        campaignScrollPanePanel.removeCampaign(campaign);
    }
}
