package software.tinlion.pertwee.feed;

import java.util.List;

import javax.json.JsonObject;
import javax.json.JsonValue;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.GetIfPresent;
import software.tinlion.pertwee.Item;

public class SimpleItem implements Item {
    
    private JsonObject itemObject;
    private GetIfPresent feedGet;
    
    public static Item parseItem(JsonValue value) {
        
        return new SimpleItem(value);
    }
    
    private SimpleItem(JsonValue value) {
        
        if (!(value instanceof JsonObject)) {
            
            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }
        
        itemObject = (JsonObject)value;
        feedGet = new GetIfPresent(itemObject);
    }

    @Override
    public String id() {
        
        return feedGet.getString("id");
    }

    @Override
    public String contentText() {
        
        return feedGet.getString("content_text");
    }

    @Override
    public String contentHtml() {
        
        return feedGet.getString("content_html");
    }

    @Override
    public String url() {
        
        return feedGet.getString("url");
    }

    @Override
    public String externalUrl() {
        
        return feedGet.getString("external_url");
    }

    @Override
    public String title() {
        
        return feedGet.getString("title");
    }

    @Override
    public String summary() {
        
        return feedGet.getString("summary");
    }

    @Override
    public String image() {
        
        return feedGet.getString("image");
    }

    @Override
    public String bannerImage() {
        
        return feedGet.getString("banner_image");
    }

    @Override
    public String datePublished() {
        
        return feedGet.getString("date_published");
    }

    @Override
    public String dateModified() {
        
        return feedGet.getString("date_modified");
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
