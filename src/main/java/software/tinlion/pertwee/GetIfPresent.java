package software.tinlion.pertwee;

import javax.json.JsonObject;

/**
 * Gets a value from a JsonObject if it is there.
 * 
 * @author martin
 *
 */
public class GetIfPresent {
    
    private final JsonObject object;
    
    public GetIfPresent(final JsonObject o) {
        
        object = o;
    }

    public String getString(String name) {
        
        if (object.containsKey(name)) {
            return object.getString(name);
        }
        
        return "";
    }
}
