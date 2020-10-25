package software.tinlion.pertwee.check;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

/**
 * Gets a value from a JsonObject if it is there. If not it returns a 
 * a sensible default: the empty string, or an empty List.
 * 
 * @author martin
 *
 */
public class GetIfPresent {
    
    private final JSONObject object;
    
    public GetIfPresent(final JSONObject o) {
        
        object = o;
    }

    public String getString(String name, boolean required) 
            throws RequiredElementNotPresentException {
        
        if (!object.optString(name).equals("")) {
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
        if (object.optJSONArray(name) != null) {
            
            JSONArray arr = object.getJSONArray(name);
            for (int i = 0; i < arr.length(); i++) {
                values.add(arr.getString(i));
            }
        }
        return values;
    }
}
