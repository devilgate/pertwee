/**
 * 
 */
package software.tinlion.pertwee;

import java.util.List;

/**
 * The main interface for the Pertwee JSON Feed parser
 * 
 * @author Martin McCallion (martin@tinlion.software)
 *
 */
public interface Feed {

    // public static Feed getFeed(String url) {
    //
    // return new Feed();
    // }

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
     */
    public Feed nextFeed();
    
    public String icon();
    
    public String favicon();
    
    public Author author();
    
    public boolean hasExpired();
    
    public List<Item> items();
    
    public List<Hub> hubs();

}
