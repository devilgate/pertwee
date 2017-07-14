package software.tinlion.pertwee.check;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

/**
 * Gets a value from a JsonObject if it is there. If not it returns a 
 * a sensible default: the empty string, or an empty List.
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
    
    public List<String> getStringList(String name) {

        List<String> values = new ArrayList<>();
        if (object.containsKey(name)) {
            
            JsonArray arr = object.getJsonArray(name);
            for (JsonString element : arr.getValuesAs(JsonString.class)) {
                
                values.add(element.getString());
            }
        }
        return values;
    }
}
