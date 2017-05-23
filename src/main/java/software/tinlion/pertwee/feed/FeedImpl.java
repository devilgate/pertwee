package software.tinlion.pertwee.feed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.Feed;
import software.tinlion.pertwee.Hub;
import software.tinlion.pertwee.Item;

public class FeedImpl implements Feed {
    
    private final String url;
    
    public static Feed load(final String url) throws IOException {
        
        return new FeedImpl(url);
    }
    
    private FeedImpl(final String url) throws IOException {
        
        this.url = url;
        
        URL feedUrl = new URL(url);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(feedUrl.openStream()));
        
        // Now we get the JSON
        JsonReader jsonReader = Json.createReader(reader);
    }

    @Override
    public Item nextItem() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String version() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String title() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String homePageUrl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String feedUrl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String description() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String userComment() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String nextUrl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Feed nextFeed() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String icon() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String favicon() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Author author() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Item> items() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Hub> hubs() {
        // TODO Auto-generated method stub
        return null;
    }

}
