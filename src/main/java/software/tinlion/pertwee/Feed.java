/**
 * 
 */
package software.tinlion.pertwee;

import java.io.IOException;
import java.util.List;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;
import software.tinlion.pertwee.feed.DefaultFeed;

/**
 * The main interface for the Pertwee JSON Feed parser. See 
 * <a href="https://jsonfeed.org/version/1">the JSON Feed spec</a>.
 * 
 * To get an instance use one of the factory methods in {@link DefaultFeed}.
 * 
 * Once you have an instance you can use the various methods to get the 
 * various elements of the feed.
 * 
 * This implementation makes a fairly strict interpretation of the spec. 
 * Where an element is marked as "<tt>required</tt>", such as 
 * <tt>version</tt>, for example, a {@code RequiredElementNotPresentException} 
 * will be thrown. This is a RuntimeException, but methods for handling 
 * required elements declare it thrown, to allow client classes to decide
 * how to handle it.
 * 
 * Most of the methods here simply return the comparably-named element from 
 * the provided JSON (for example, <tt>feedUrl</tt> returns the element called 
 * "<tt>feed_url</tt>"). See the spec for detailed explanations of what each 
 * element means.
 * 
 * @author Martin McCallion (martin@tinlion.software)
 * @version 1.0.1
 *
 */
public interface Feed {

    /**
     * The version is required.
     * 
     * @return
     * @throws RequiredElementNotPresentException 
     */
    public String version() throws RequiredElementNotPresentException;
    
    /** 
     * Title is required.
     * 
     * @return
     * @throws RequiredElementNotPresentException 
     */
    public String title() throws RequiredElementNotPresentException;
    
    /**
     * This is described as "optional but strongly recommended."
     * 
     * @return
     */
    public String homePageUrl();
    
    /**
     * This is described as "optional but strongly recommended."
     * 
     * @return
     */
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
    
    /**
     * Optional, but if present certain elements within it must be present.
     * 
     * @return The author details of the feed; see {@link Author}
     * @throws RequiredElementNotPresentException if the contents of the 
     * "<tt>author</tt>" element do not meet their requirements
     *  
     */
    public Author author() throws RequiredElementNotPresentException;
    
    public boolean hasExpired();
    
    public List<Item> items() throws RequiredElementNotPresentException;
    
    /**
     * Returns the next {@code Item} from the feed.
     * 
     * @return
     */
    public Item nextItem();
    
    /**
     * Tells us whether the feed has another item.
     * 
     * @return
     */
    public boolean hasNextItem();
    
    public List<Hub> hubs();
    
    public boolean hasAttachments();
    
    public List<Attachment> attachments();

    public boolean hasExtensions();
    
    /**
     * Prints the feed;
     * 
     * @return
     */
    public String print();
}
