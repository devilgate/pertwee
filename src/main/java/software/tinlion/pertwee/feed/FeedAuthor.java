package software.tinlion.pertwee.feed;

import javax.json.JsonObject;

import software.tinlion.pertwee.Author;

public class FeedAuthor implements Author {

    private final JsonObject object;
    
    public static Author fromJson(JsonObject val) {
        
        return new FeedAuthor(val);
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
        
        return object.getString("name");
    }

    @Override
    public String url() {
   
        if (object.containsKey("url")) {
            return object.getString("url");
        } else {
            return null;
        }
    }

    @Override
    public String avatar() {
        
        return object.getString("avatar");
    }

}
