package Campaign;

import Creature.Creature;
import Encounter.Encounter;
import GUI.Campaign.CampaignFrame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Campaign object containing encounters. Also campaign is one of those words that no longer looks like a word
 * after a while
 */
public class Campaign implements Serializable {
    private String campaignName, description;
    private ArrayList<Creature> characters;
    private ArrayList<Encounter> encounters;

    public Campaign(String campaignName) {
        this.campaignName = campaignName;
        this.encounters = new ArrayList<Encounter>();
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
    public ArrayList<Encounter> getEncounters(){return encounters;}
    public void addEncounter(Encounter encounter){
        encounters.add(encounter);

    }
    public void removeEncounter(Encounter encounter){
        this.encounters.remove(encounter);
    }





    public ArrayList<Creature> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Creature> characters) {
        this.characters = characters;
    }

    public void addToCharacters(Creature creature){
        characters.add(creature);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
