package software.tinlion.pertwee.feed;

import java.util.List;

import javax.json.JsonObject;
import javax.json.JsonValue;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.Item;

public class SimpleItem implements Item {
    
    private JsonObject itemObject;
    
    public static Item parseItem(JsonValue value) {
        
        return new SimpleItem(value);
    }
    
    private SimpleItem(JsonValue value) {
        
        if (!(value instanceof JsonObject)) {
            
            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }
        
        itemObject = (JsonObject)value;
    }

    @Override
    public String id() {
        return itemObject.getString("id");
    }

    @Override
    public String contentText() {
        return itemObject.getString("content_text");
    }

    @Override
    public String contentHtml() {
        return itemObject.getString("content_html");
    }

    @Override
    public String url() {
        return itemObject.getString("url");
    }

    @Override
    public String externalUrl() {
        return itemObject.getString("external_url");
    }

    @Override
    public String title() {
        return itemObject.getString("title");
    }

    @Override
    public String summary() {
        return itemObject.getString("summary");
    }

    @Override
    public String image() {
        return itemObject.getString("image");
    }

    @Override
    public String bannerImage() {
        return itemObject.getString("banner_image");
    }

    @Override
    public String datePublished() {
        return itemObject.getString("date_published");
    }

    @Override
    public String dateModified() {
        return itemObject.getString("date_modified");
    }

    @Override
    public Author author() {
        return FeedAuthor.fromJson(itemObject.getJsonObject("author"));
    }

    @Override
    public List<String> tags() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return itemObject.toString();
    }
}
