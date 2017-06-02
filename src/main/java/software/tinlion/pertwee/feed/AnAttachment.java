package software.tinlion.pertwee.feed;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import software.tinlion.pertwee.Attachment;
import software.tinlion.pertwee.Hub;

public class AnAttachment implements Attachment {
    
    private final JsonObject attachment;
    
    public static List<Attachment> parseHubsFromJson(JsonArray attachments) {
        
        List<Hub> hubsList = new ArrayList<>();
        if (attachments != null && !attachments.isEmpty()) {
            
            attachments.forEach( h -> hubsList.add(new SubHub(h)) );
        }
        return hubsList;
    }
    
    private SubHub(final JsonValue value) {
        
        if (!(value instanceof JsonObject)) {
            
            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }
        
        hub = (JsonObject)value;
    }


    @Override
    public String url() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String mimeType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String title() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long sizeInBytes() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long durationInSeconds() {
        // TODO Auto-generated method stub
        return 0;
    }

}
