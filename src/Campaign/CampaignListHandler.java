package Campaign;
import Encounter.Encounter;
import Helpers.FileReadWrite;
import Helpers.SerializedObjectHandler;

import java.io.IOException;
import java.util.HashMap;

/**
 * Handles reading and writing campaigns to file, which in turn contain encounters, which in turn contain
 * creatures
 */
public class CampaignListHandler implements SerializedObjectHandler {
    private final String path = "./CampaignListFile.dnd";
    CampaignMap localCampaigns, storedCampaigns;

    public CampaignListHandler() throws IOException, ClassNotFoundException {
        if(fileExists()){
            storedCampaigns = readObject();
            localCampaigns = storedCampaigns;
        }
        else{
            localCampaigns = new CampaignMap();
            writeObject();
            storedCampaigns = readObject();
        }
    }

    /**
     * Grabs the current version of the campaign list and adds the new campaign to it
     * @param campaign the campaign to be added
     */
    public void addCampaign(Campaign campaign) throws IOException, ClassNotFoundException {
        localCampaigns = new CampaignMap();
        if(!fileExists()){
            localCampaigns = new CampaignMap();
            writeObject();
        }
        else{localCampaigns = readObject();}

        HashMap<String, Campaign> tempMap = localCampaigns.getCampaignHashMap();
        if(tempMap.containsKey(campaign.getCampaignName())){
            throw new IOException("Campaign file already exists");
        }
        tempMap.put(campaign.getCampaignName(),campaign);
        localCampaigns.setCampaignHashMap(tempMap);
        writeObject();
    }

    /**
     * Replaces the existing campaign entry with the input one
     * @param campaign The campaign to be updated
     */
    public void updateCampaign(Campaign campaign) throws IOException, ClassNotFoundException {
        if(!fileExists()){
            throw new IOException("Campaign list file does not exist");
        }
        else{localCampaigns = readObject();}

        HashMap<String, Campaign> tempMap = localCampaigns.getCampaignHashMap();
        if(!tempMap.containsKey(campaign.getCampaignName())){
            throw new IOException("Campaign file does not exist");
        }else{
            tempMap.remove(campaign.getCampaignName());
            tempMap.put(campaign.getCampaignName(),campaign);
        }
        localCampaigns.setCampaignHashMap(tempMap);
        writeObject();
    }

    /**
     * Grabs the current version of the campaign list and removes a campaign from it
     * @param campaign the campaign to be removed
     */
    public void removeCampaign(Campaign campaign) throws IOException, ClassNotFoundException {
        if(!fileExists()){
            throw new IOException("Nothing to delete from! No campaign list file");
        }

        localCampaigns = readObject();
        HashMap<String, Campaign> tempMap = localCampaigns.getCampaignHashMap();
        if(!tempMap.containsKey(campaign.getCampaignName())){
            throw new IOException("Campaign file does not exist!");
        }
        tempMap.remove(campaign.getCampaignName());
        localCampaigns.setCampaignHashMap(tempMap);
        writeObject();

    }
    @Override
    public boolean fileExists() {
        return FileReadWrite.FileExists(path);
    }

    @Override
    public CampaignMap readObject() throws IOException, ClassNotFoundException {
        return(CampaignMap) FileReadWrite.readObjectFromFile(path);
    }

    @Override
    public void writeObject() throws IOException {
        FileReadWrite.writeObjectToFile(path,localCampaigns);
        try {
            storedCampaigns = readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * returns whether or not the data is consistent across the local and stored files
     * @return true if the data is the same | false if the data is inconsistent
     */
    @Override
    public boolean compareData() throws IOException, ClassNotFoundException {
        storedCampaigns = readObject();
        HashMap<String ,Campaign> localHash = localCampaigns.getCampaignHashMap(),
                storedHash = storedCampaigns.getCampaignHashMap();
        if(localHash.keySet().size()!= storedHash.keySet().size()){
            return false;
        }
        for(String key: localHash.keySet()){
            Campaign localCampaign = localHash.get(key), storedCampaign = storedHash.get(key);

            if(
                //checks each of the data structures for inconsistencies
                    !(localCampaign.getCampaignName().equals(storedCampaign.getCampaignName()))
            ){
                return false;
            }
        }
        return true;
    }
}
