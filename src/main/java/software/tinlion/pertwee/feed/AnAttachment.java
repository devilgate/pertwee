package software.tinlion.pertwee.feed;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import software.tinlion.pertwee.Attachment;

public class AnAttachment implements Attachment {
    
    private final JsonObject attachment;
    
    public static List<Attachment> parseHubsFromJson(JsonArray attachments) {
        
        List<Attachment> attachmentsList = new ArrayList<>();
        if (attachments != null && !attachments.isEmpty()) {
            
            attachments.forEach( a -> attachmentsList.add(new AnAttachment(a)) );
        }
        return attachmentsList;
    }
    
    private AnAttachment(final JsonValue value) {
        
        if (!(value instanceof JsonObject)) {
            
            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }
        
        attachment = (JsonObject)value;
    }


    @Override
    public String url() {
        
        if (attachment.containsKey("url")) {
            
            return attachment.getString("url");
        }
        return "";
    }

    @Override
    public String mimeType() {
        
        if (attachment.containsKey("mime_type")) {
            
            return attachment.getString("mime_type");
        }
        return "";
    }

    @Override
    public String title() {
        
        if (attachment.containsKey("title")) {
            
            return attachment.getString("title");
        }
        return "";
    }

    @Override
    public long sizeInBytes() {
        
        if (attachment.containsKey("size_in_bytes")) {
            
            return attachment.getInt("size_in_bytes");
        }
        return 0;
    }

    @Override
    public long durationInSeconds() {
        
        if (attachment.containsKey("duration_in_seconds")) {
            
            return attachment.getInt("duration_in_seconds");
        }
        return 0;
    }

}
