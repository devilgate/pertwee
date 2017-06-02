/**
 * 
 */
package software.tinlion.pertwee;

import java.io.IOException;
import java.util.List;

import javax.json.JsonObject;

/**
 * The main interface for the Pertwee JSON Feed parser
 * 
 * @author Martin McCallion (martin@tinlion.software)
 *
 */
public interface Feed {

    /**
     * Returns the next {@code Item} from the feed. (Not sure about this.)
     * 
     * @return
     */
    public Item nextItem();
    
    public boolean hasNextItem();
    
    public String version();
    
    public String title();
    
    public String homePageUrl();
    
    public String feedUrl();
    
    public String description();
    
    public String userComment();
    
    public String nextUrl();
    
    /**
     * A convenience method to get the contents of nextUrl as a Feed.
     *  
     * @return
     * @throws IOException 
     */
    public Feed nextFeed() throws IOException;
    
    public String icon();
    
    public String favicon();
    
    public Author author();
    
    public boolean hasExpired();
    
    public List<Item> items();
    
    public List<Hub> hubs();
    
    public boolean hasAttachments();
    
    public List<Attachment> attachments();

    public boolean hasExtensions();
    
    public JsonObject getByName(String name);
}
