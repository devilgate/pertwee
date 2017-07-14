package software.tinlion.pertwee.feed;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class FeedAuthor implements Author {

    private final JsonObject object;
    private static final Author NOBODY = new FeedAuthor();
    
    public static Author fromJson(JsonValue val) 
            throws RequiredElementNotPresentException {
        
        return new FeedAuthor(val);
    }
    
    public static Author nullAuthor() {
        
        return NOBODY;
    }
    
    private FeedAuthor() {
        // Just used for creating the null author singleton.
        // The line below is needed to make the compiler (and toString) happy.
        object = Json.createObjectBuilder().build();
    }

    private FeedAuthor(final JsonValue value) throws RequiredElementNotPresentException {

        object = (JsonObject)value;
        if (!object.containsKey("name") && !object.containsKey("url")) {
            
            throw new RequiredElementNotPresentException(
                    "Author must contain at least one of 'name' and 'url'. "
                    + "received value was " + object);
        }
    }

    @Override
    public String name() {
        
        if (object.containsKey("name")) {
            return object.getString("name");
        } else {
            return "";
        }
    }

    @Override
    public String url() {
   
        if (object.containsKey("url")) {
            return object.getString("url");
        } else {
            return "";
        }
    }

    @Override
    public String avatar() {
        
        if (object.containsKey("avatar")) {
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
