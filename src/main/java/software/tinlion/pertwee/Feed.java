/**
 * 
 */
package software.tinlion.pertwee;

import java.io.IOException;
import java.util.List;

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

}
