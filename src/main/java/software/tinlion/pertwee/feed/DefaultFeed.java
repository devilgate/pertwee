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
import software.tinlion.pertwee.Hub;
import software.tinlion.pertwee.Item;
import software.tinlion.pertwee.check.GetIfPresent;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class DefaultFeed implements Feed {
    
    private JsonObject feedObject;
    private GetIfPresent feedGet;
    private List<Item> itemsInFeed;
    private int itemsIndex;

    public static Feed fromString(final String jsonString) throws IOException {
        
        return new DefaultFeed(jsonString);
    }
    
    public static Feed fromUrl(URL url) throws IOException {
        
        return new DefaultFeed(url);
    }
    
    private DefaultFeed(final Reader reader) {
        
        JsonReader jReader = Json.createReader(reader);
        feedObject = jReader.readObject();
        jReader.close();
        feedGet = new GetIfPresent(feedObject);
    }
    
    private DefaultFeed(final URL url) throws IOException {
        
        this(new InputStreamReader(url.openStream()));
    }
    
    private DefaultFeed(final String jsonString) throws IOException {
        
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
    public String version() throws RequiredElementNotPresentException {

        return feedGet.getString("version", true);
    }

    @Override
    public String title() throws RequiredElementNotPresentException {
        
        return feedGet.getString("title", true);
    }

    @Override
    public String homePageUrl() {
        
        return feedGet.getString("home_page_url", false);
    }

    @Override
    public String feedUrl() {
        
        return feedGet.getString("feed_url", false);
    }

    @Override
    public String description() {

        return feedGet.getString("description", false);
    }

    @Override
    public String userComment() {
        
        return feedGet.getString("user_comment", false);
    }

    @Override
    public String nextUrl() {
        
        return feedGet.getString("next_url", false);
    }

    @Override
    public Feed nextFeed() throws IOException {
        
        return DefaultFeed.fromString(nextUrl());
    }

    @Override
    public String icon() {
        
        return feedGet.getString("icon", false);
    }

    @Override
    public String favicon() {
        
        return feedGet.getString("favicon", false);
    }

    @Override
    public Author author() throws RequiredElementNotPresentException {
        
        
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
    public List<Item> items() throws RequiredElementNotPresentException {
        
        if (!feedObject.containsKey("items")) {
            
            throw new RequiredElementNotPresentException("Element 'items' is"
                    + " required, but was not found in the feed.");
        }
        List<Item> items = new ArrayList<>();
        for (JsonValue val : feedObject.getJsonArray("items")) {
            
            items.add(DefaultItem.parseItem(val, author()));
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
                .append(i.contentHtml()).append("\n")
                .append(i.contentText()).append("\n");
            if (!i.tags().isEmpty()) {
                output.append("[");
                for (String tag : i.tags()) {
                    output.append(tag).append(", ");
                }
                output.setLength(output.length() - 1); // Remove last comma.
                output.append("]\n");
            }
        }
        
        // TODO: more 
        
        return output.toString();
    }

}
