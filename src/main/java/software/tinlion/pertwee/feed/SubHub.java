package software.tinlion.pertwee.feed;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import software.tinlion.pertwee.Hub;

public class SubHub implements Hub {
    
    private final JsonObject hub;
    
    public static List<Hub> parseHubsFromJson(JsonArray hubs) {
        
        List<Hub> hubsList = new ArrayList<>();
        if (hubs != null && !hubs.isEmpty()) {
            
            hubs.forEach( h -> hubsList.add(new SubHub(h)) );
        }
        return hubsList;
    }
    
    SubHub(final JsonValue value) {
        
        if (!(value instanceof JsonObject)) {
            
            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }
        
        hub = (JsonObject)value;
    }

    @Override
    public String type() {
        
        if (hub.containsKey("type")){
            return hub.getString("type");
        }
        return null;
    }

    @Override
    public String url() {
        
        if (hub.containsKey("url")) {
            return hub.getString("url");
        }
        return null;
    }

}
