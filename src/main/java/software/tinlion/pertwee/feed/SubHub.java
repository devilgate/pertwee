package software.tinlion.pertwee.feed;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import software.tinlion.pertwee.Hub;

public class SubHub implements Hub {
    
    private final JSONObject hub;
    
    public static List<Hub> parseHubsFromJson(JSONArray hubs) {
        
        List<Hub> hubsList = new ArrayList<>();
        if (hubs != null && !hubs.isEmpty()) {
            
            hubs.forEach( h -> hubsList.add(new SubHub(h)) );
        }
        return hubsList;
    }
    
    SubHub(final Object value) {
        
        if (!(value instanceof JSONObject)) {
            
            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }
        
        hub = (JSONObject)value;
    }

    @Override
    public String type() {
        
        if (!hub.optString("type").equals("")){
            return hub.getString("type");
        }
        return null;
    }

    @Override
    public String url() {
        
        if (!hub.optString("url").equals("")) {
            return hub.getString("url");
        }
        return null;
    }

}
