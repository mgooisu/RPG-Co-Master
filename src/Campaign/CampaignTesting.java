package Campaign;

import Encounter.*;
import Encounter.EncounterListHandler;
import Helpers.FileReadWrite;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class CampaignTesting {
    private static EncounterListHandler encounterListHandler;
    private static CampaignMap campaignMap;
    private static CampaignListHandler campaignListHandler;

    @BeforeAll
    static void setup() throws IOException, ClassNotFoundException {
        encounterListHandler = new EncounterListHandler();
        campaignMap = new CampaignMap();
        campaignListHandler = new CampaignListHandler();
    }

    @Test
    void addEncountersToEncunterListFile() throws IOException, ClassNotFoundException {
        Encounter caveBattle = new Encounter("Cave Battle");
        Encounter ghoulHorde = new Encounter("Ghoul Horde");




        encounterListHandler.addEncounter(caveBattle);
        encounterListHandler.addEncounter(ghoulHorde);

        HashMap<String, Encounter> testMap = encounterListHandler.readObject().getEncounterHashMap();
        int counter = 1;
        for(Encounter encounter: testMap.values()){
            System.out.println("Encounter "+counter+": "+encounter.getEncounterName());
        }

        Assertions.assertTrue(encounterListHandler.compareData());
    }
    @Test
    void addCampaignsToMap(){
        Campaign campaign1 = new Campaign("test Campaign 1"), campaign2 = new Campaign("Test Campaign 2");
        campaign1.addEncounter(new Encounter("Test Encounter 1-1"));
        campaign1.addEncounter(new Encounter("Test Encounter 1-2"));
        campaign2.addEncounter(new Encounter("Test Encounter 2-1"));

        HashMap<String, Campaign> tempMap = new HashMap<String, Campaign>();
        tempMap.put(campaign1.getCampaignName(),campaign1);
        tempMap.put(campaign2.getCampaignName(),campaign2);
        campaignMap.setCampaignHashMap(tempMap);
        Assertions.assertSame(tempMap, campaignMap.getCampaignHashMap());
    }

    @Test
    void addCampaignsToCampaignFile() throws IOException, ClassNotFoundException {
        Campaign campaign1 = new Campaign("test Campaign 1"), campaign2 = new Campaign("Test Campaign 2");
        campaign1.addEncounter(new Encounter("Test Encounter 1-1"));
        campaign1.addEncounter(new Encounter("Test Encounter 1-2"));
        campaign2.addEncounter(new Encounter("Test Encounter 2-1"));

        campaignListHandler.addCampaign(campaign1);
        campaignListHandler.addCampaign(campaign2);

        HashMap<String,Campaign> testMap = campaignListHandler.readObject().getCampaignHashMap();

        int counter = 1;
        for(Campaign campaign: testMap.values()){
            System.out.println("Campaign "+counter+": "+campaign.getCampaignName());
            counter++;
        }

        Assertions.assertTrue(campaignListHandler.compareData());
    }

    @AfterAll
    static void cleanup() throws IOException {
        FileReadWrite.deleteObjectFile("./EncounterListFile.dnd");
        FileReadWrite.deleteObjectFile("./CampaignListFile.dnd");

    }
}
