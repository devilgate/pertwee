package software.tinlion.pertwee.feed;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.Feed;
import software.tinlion.pertwee.Hub;
import software.tinlion.pertwee.Item;

public class SimpleFeed implements Feed {
    
    private JsonObject feedObject;

    public static Feed load(final String jsonString) throws IOException {
        
        return new SimpleFeed(jsonString);
    }
    
    public static Feed load(URL url) throws IOException {
        
        return new SimpleFeed(url);
    }
    
    private SimpleFeed(final Reader reader) {
        
        JsonReader jReader = Json.createReader(reader);
        feedObject = jReader.readObject();
//        System.out.printf("%nfeedObject is %s%n%n", feedObject);
        jReader.close();
    }
    
    private SimpleFeed(final URL url) throws IOException {
        
        this(new InputStreamReader(url.openStream()));
    }
    
    private SimpleFeed(final String jsonString) throws IOException {
        
        this(new StringReader(jsonString));
        
    }

    @Override
    public Item nextItem() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String version() {

        return feedObject.getJsonString("version").getString();
    }

    @Override
    public String title() {
        
        return feedObject.getJsonString("title").getString();
    }

    @Override
    public String homePageUrl() {
        
        return feedObject.getJsonString("home_page_url").getString();
    }

    @Override
    public String feedUrl() {
        
        return feedObject.getJsonString("feed_url").getString();
    }

    @Override
    public String description() {
        return feedObject.getJsonString("description").getString();
    }

    @Override
    public String userComment() {
        return feedObject.getJsonString("user_comment").getString();
    }

    @Override
    public String nextUrl() {
        return feedObject.getJsonString("next_url").getString();
    }

    @Override
    public Feed nextFeed() throws IOException {
        
        return SimpleFeed.load(nextUrl());
    }

    @Override
    public String icon() {
        return feedObject.getJsonString("icon").getString();
    }

    @Override
    public String favicon() {
        return feedObject.getJsonString("favicon").getString();
    }

    @Override
    public Author author() {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

}
