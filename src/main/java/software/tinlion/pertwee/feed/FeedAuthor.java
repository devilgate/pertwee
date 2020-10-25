package software.tinlion.pertwee.feed;

import org.json.JSONObject;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class FeedAuthor implements Author {

    private final JSONObject object;
    private static final Author NOBODY = new FeedAuthor();
    
    public static Author fromJson(JSONObject val) 
            throws RequiredElementNotPresentException {
        
        return new FeedAuthor(val);
    }
    
    public static Author nullAuthor() {
        
        return NOBODY;
    }
    
    private FeedAuthor() {
        // Just used for creating the null author singleton.
        // The line below is needed to make the compiler (and toString) happy.
        object = new JSONObject();
    }

    private FeedAuthor(final JSONObject value) throws RequiredElementNotPresentException {

        object = (JSONObject)value;
        if (!object.has("name") && !object.has("url")) {
            
            throw new RequiredElementNotPresentException(
                    "Author must contain at least one of 'name' and 'url'. "
                    + "received value was " + object);
        }
    }

    @Override
    public String name() {
        
        if (!object.optString("name").equals("")) {
            return object.getString("name");
        } else {
            return "";
        }
    }

    @Override
    public String url() {
   
        if (!object.optString("url").equals("")) {
            return object.getString("url");
        } else {
            return "";
        }
    }

    @Override
    public String avatar() {
        
        if (!object.optString("avatar").equals("")) {
            return object.getString("avatar");
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        
        String author = name() + " " + url();
        return author.trim().length() == 0 ? "No author details" : "Author: " + author;
    }
    
    

}
