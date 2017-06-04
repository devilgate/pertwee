package software.tinlion.pertwee.feed;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import software.tinlion.pertwee.Attachment;
import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.Feed;
import software.tinlion.pertwee.GetIfPresent;
import software.tinlion.pertwee.Hub;
import software.tinlion.pertwee.Item;

public class SimpleFeed implements Feed {
    
    private JsonObject feedObject;
    private GetIfPresent feedGet;
    private List<Item> itemsInFeed;
    private int itemsIndex;

    public static Feed fromString(final String jsonString) throws IOException {
        
        return new SimpleFeed(jsonString);
    }
    
    public static Feed fromUrl(URL url) throws IOException {
        
        return new SimpleFeed(url);
    }
    
    private SimpleFeed(final Reader reader) {
        
        JsonReader jReader = Json.createReader(reader);
        feedObject = jReader.readObject();
        jReader.close();
        feedGet = new GetIfPresent(feedObject);
    }
    
    private SimpleFeed(final URL url) throws IOException {
        
        this(new InputStreamReader(url.openStream()));
    }
    
    private SimpleFeed(final String jsonString) throws IOException {
        
        this(new StringReader(jsonString));
    }

    @Override
    public boolean hasNextItem() {
        
        // Initialise first time through
        if (itemsInFeed == null) {
            itemsInFeed = items();
            itemsIndex = 0;
            if (itemsInFeed == null) {
                
                // Not sure if this is possible, but maybe if there are no items
                return false;
            }
        }
            return itemsInFeed.size() > 0 && itemsIndex < itemsInFeed.size();
    }
    
    @Override
    public Item nextItem() {
        
        // Initialise first time through
        if (itemsInFeed == null) {
            itemsInFeed = items();
            itemsIndex = 0;
        }
        return itemsInFeed.get(itemsIndex++);
    }

    @Override
    public String version() {

        return feedGet.getString("version");
    }

    @Override
    public String title() {
        
        return feedGet.getString("title");
    }

    @Override
    public String homePageUrl() {
        
        return feedGet.getString("home_page_url");
    }

    @Override
    public String feedUrl() {
        
        return feedGet.getString("feed_url");
    }

    @Override
    public String description() {

        return feedGet.getString("description");
    }

    @Override
    public String userComment() {
        
        return feedGet.getString("user_comment");
    }

    @Override
    public String nextUrl() {
        
        return feedGet.getString("next_url");
    }

    @Override
    public Feed nextFeed() throws IOException {
        
        return SimpleFeed.fromString(nextUrl());
    }

    @Override
    public String icon() {
        
        return feedGet.getString("icon");
    }

    @Override
    public String favicon() {
        
        return feedGet.getString("favicon");
    }

    @Override
    public Author author() {
        
        if (feedObject.containsKey("author")) {
            return FeedAuthor.fromJson(feedObject.getJsonObject("author"));
        } else {
            return FeedAuthor.nullAuthor();
        }
    }

    @Override
    public boolean hasExpired() {
        
        return feedObject.getBoolean("expired");
    }

    @Override
    public List<Item> items() {
        
        List<Item> items = new ArrayList<>();
        for (JsonValue val : feedObject.getJsonArray("items")) {
            
            items.add(SimpleItem.parseItem(val));
        }
        return items;
    }

    @Override
    public List<Hub> hubs() {
        
        if (feedObject.containsKey("hubs")) {
            
            return SubHub.parseHubsFromJson(feedObject.getJsonArray("hubs"));
        }
        return null;
    }

    @Override
    public boolean hasExtensions() {
        
        Set<Entry<String, JsonValue>> entries = feedObject.entrySet();
        for (Entry<String, JsonValue> entry : entries) {
            
            if (entry.getKey().startsWith("_")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JsonObject getByName(String name) {
        
        if (feedObject.containsKey(name)) {
            return feedObject.getJsonObject(name);
        }
        return null;
    }

    @Override
    public boolean hasAttachments() {
        
        return feedObject.containsKey("attachments") 
                && feedObject.getJsonArray("attachements") != null
                && !feedObject.getJsonArray("attachments").isEmpty();
    }

    @Override
    public List<Attachment> attachments() {
        
        if (hasAttachments()) {
            
            return AnAttachment.parseHubsFromJson(feedObject.getJsonArray("attachments"));
        }
        return null;
    }
    
    public String print() {
        
        StringBuilder output = new StringBuilder();
        output
            .append(version()).append("\n")
            .append(title()).append("\n")
            .append(homePageUrl()).append("\n")
            .append(feedUrl()).append("\n")
            .append(description()).append("\n")
            .append(userComment()).append("\n")
            .append(nextUrl()).append("\n")
            .append(icon()).append("\n")
            .append(favicon()).append("\n")
            .append(author()).append("\n").append("\n")
            ;
        
        for (Item i : items()) {
            
            output.append(i.title()).append("\n")
                .append(i.id()).append("\n")
                .append(i.dateModified()).append("\n")
                .append(i.datePublished()).append("\n")
                .append(i.summary()).append("\n")
                .append(i.author()).append("\n")
                .append(i.url()).append("\n")
                .append(i.tags()).append("\n")
                .append(i.contentHtml()).append("\n")
                .append(i.contentText()).append("\n");
        }
        
        // TODO: more 
        
        return output.toString();
    }

}
