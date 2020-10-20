package software.tinlion.pertwee.feed;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import software.tinlion.pertwee.Attachment;
import software.tinlion.pertwee.check.GetIfPresent;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class AnAttachment implements Attachment {
    
    private final JSONObject attachment;
    private final GetIfPresent attachmentGetThing;
    
    public static List<Attachment> parseAttachmentsFromJson(JSONArray attachments) {
        
        List<Attachment> attachmentsList = new ArrayList<>();
        if (attachments != null && !attachments.isEmpty()) {
            
            attachments.forEach( a -> attachmentsList.add(new AnAttachment(a)) );
        }
        return attachmentsList;
    }
    
    private AnAttachment(final Object value) {
        
        if (!(value instanceof JSONObject)) {
            
            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }
        
        attachment = (JSONObject)value;
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
        
        if (!attachment.optString("title").equals("")) {
            
            return attachment.getString("title");
        }
        return "";
    }

    @Override
    public long sizeInBytes() {
        
        if (attachment.optInt("size_in_bytes") != 0) {
            
            return attachment.getInt("size_in_bytes");
        }
        return 0;
    }

    @Override
    public long durationInSeconds() {
        
        if (attachment.optInt("duration_in_seconds") != 0) {
            
            return attachment.getInt("duration_in_seconds");
        }
        return 0;
    }

}
