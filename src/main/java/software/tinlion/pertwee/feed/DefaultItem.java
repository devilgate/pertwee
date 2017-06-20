package software.tinlion.pertwee.feed;

import java.util.List;

import javax.json.JsonObject;
import javax.json.JsonValue;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.Item;
import software.tinlion.pertwee.check.GetIfPresent;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class DefaultItem implements Item {
    
    private JsonObject itemObject;
    private GetIfPresent feedGet;
    
    public static Item parseItem(JsonValue value) {
        
        return new DefaultItem(value);
    }
    
    private DefaultItem(JsonValue value) {
        
        if (!(value instanceof JsonObject)) {
            
            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }
        
        itemObject = (JsonObject)value;
        feedGet = new GetIfPresent(itemObject);
    }

    @Override
    public String id() throws RequiredElementNotPresentException {
        
        return feedGet.getString("id", true);
    }

    @Override
    public String contentText() {
        
        return feedGet.getString("content_text", false);
    }

    @Override
    public String contentHtml() {
        
        return feedGet.getString("content_html", false);
    }

    @Override
    public String url() {
        
        return feedGet.getString("url", false);
    }

    @Override
    public String externalUrl() {
        
        return feedGet.getString("external_url", false);
    }

    @Override
    public String title() {
        
        return feedGet.getString("title", false);
    }

    @Override
    public String summary() {
        
        return feedGet.getString("summary", false);
    }

    @Override
    public String image() {
        
        return feedGet.getString("image", false);
    }

    @Override
    public String bannerImage() {
        
        return feedGet.getString("banner_image", false);
    }

    @Override
    public String datePublished() {
        
        return feedGet.getString("date_published", false);
    }

    @Override
    public String dateModified() {
        
        return feedGet.getString("date_modified", false);
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
