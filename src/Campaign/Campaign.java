package Campaign;

import Encounter.Encounter;
import GUI.Campaign.CampaignFrame;

import java.util.ArrayList;

/**
 * Campaign object containing encounters. Also campaign is one of those words that no longer looks like a word
 * after a while
 */
public class Campaign {
    private String campaignName;
    private ArrayList<Encounter> encounters;
    private final CampaignFrame campaignFrame;

    public Campaign(String campaignName) {
        this.campaignName = campaignName;
        this.encounters = new ArrayList<Encounter>();
        this.campaignFrame = new CampaignFrame(this);
    }

    public String getCampaignName(){
        return campaignName;
    }
    public void setCampaignName(String campaignName){
        this.campaignName = campaignName;
    }
    public void setEncounters(ArrayList<Encounter> encounters){
        this.encounters = encounters;
    }
    public void addEncounter(Encounter encounter){
        encounters.add(encounter);
        campaignFrame.addEncounter(encounter);
    }
    public void removeEncounter(Encounter encounter){
        this.encounters.remove(encounter);
        campaignFrame.removeEncounter(encounter);
    }

    public CampaignFrame getCampaignFrame(){
        return campaignFrame;
    }

    public void summonFrame(){
        campaignFrame.pack();
        campaignFrame.setLocationRelativeTo(null);
        campaignFrame.setSize(400,400);
        campaignFrame.setVisible(true);
    }
}
