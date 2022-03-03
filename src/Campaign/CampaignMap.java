package Campaign;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Campaign Hash Map container file. Not to be confused with a map of the terrain
 */
public class CampaignMap implements Serializable {
    private HashMap<String, Campaign> campaignHashMap;
    CampaignMap(){
        campaignHashMap = new HashMap<String, Campaign>();
    }

    public void setCampaignHashMap(HashMap<String, Campaign> campaignHashMap){
        this.campaignHashMap = campaignHashMap;
    }

    public HashMap<String ,Campaign> getCampaignHashMap(){ return campaignHashMap;}

}
