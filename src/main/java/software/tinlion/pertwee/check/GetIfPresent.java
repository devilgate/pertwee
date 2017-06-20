package software.tinlion.pertwee.check;

import javax.json.JsonObject;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

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

    public String getString(String name, boolean required) 
            throws RequiredElementNotPresentException {
        
        if (object.containsKey(name)) {
            return object.getString(name);
        } else if (required) {
            
            String message = String.format("Element '%s' is required, but was "
                    + "not found in the feed.", name);
            throw new RequiredElementNotPresentException(message);
        }
        
        return "";

    }
}
