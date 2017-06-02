package software.tinlion.pertwee.feed;

import javax.json.JsonObject;

import software.tinlion.pertwee.Author;

public class FeedAuthor implements Author {

    private final JsonObject object;
    private static final Author NOBODY = new FeedAuthor();
    
    public static Author fromJson(JsonObject val) {
        
        return new FeedAuthor(val);
    }
    
    public static Author nullAuthor() {
        
        return NOBODY;
    }
    
    private FeedAuthor() {
        // Just used for creating the null author singleton.
        // The line below is needed to make the compiler happy.
        object = null;
    }

    private FeedAuthor(final JsonObject value) {

        object = value;
        if (!object.containsKey("name") && !object.containsKey("url")) {
            
            throw new IllegalStateException("Author must contain at least on of 'name' and 'url'. "
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

}
