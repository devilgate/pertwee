package software.tinlion.pertwee.feed;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import software.tinlion.pertwee.Attachment;
import software.tinlion.pertwee.check.GetIfPresent;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class AnAttachment implements Attachment {
    
    private final JsonObject attachment;
    private final GetIfPresent attachmentGetThing;
    
    public static List<Attachment> parseAttachmentsFromJson(JsonArray attachments) {
        
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
        attachmentGetThing = new GetIfPresent(attachment);
    }


    @Override
    public String url() throws RequiredElementNotPresentException {
        
        return attachmentGetThing.getString("url", true);
    }

    @Override
    public String mimeType() throws RequiredElementNotPresentException {
        
        return attachmentGetThing.getString("mime_type", true);
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
