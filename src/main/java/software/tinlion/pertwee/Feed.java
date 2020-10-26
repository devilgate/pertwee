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
 * Where an element is marked as "<code>required</code>", such as 
 * <code>version</code>, for example, a {@code RequiredElementNotPresentException} 
 * will be thrown. This is a RuntimeException, but methods for handling 
 * required elements declare it thrown, to allow client classes to decide
 * how to handle it.
 * 
 * Most of the methods here simply return the comparably-named element from 
 * the provided JSON (for example, <code>feedUrl</code> returns the element called 
 * "<code>feed_url</code>"). See the spec for detailed explanations of what each 
 * element means.
 * 
 * @author Martin McCallion (martin@devilgate.org)
 * @version 1.0.1
 *
 */
public interface Feed {

    /**
     * The version is required.
     * 
     * @return the version as a string
     * @throws RequiredElementNotPresentException for obvious reasons
     */
    String version() throws RequiredElementNotPresentException;
    
    /** 
     * Title is required.
     * 
     * @return the title
     * @throws RequiredElementNotPresentException for obvious reasons
     */
    String title() throws RequiredElementNotPresentException;
    
    /**
     * This is described as "optional but strongly recommended."
     * 
     * @return the URL
     */
    String homePageUrl();
    
    /**
     * This is described as "optional but strongly recommended."
     * 
     * @return the URL
     */
    String feedUrl();
    
    String description();
    
    String userComment();
    
    String nextUrl();
    
    /**
     * A convenience method to get the contents of nextUrl as a Feed.
     *  
     * @return the next feed
     * @throws IOException on problems
     */
    Feed nextFeed() throws IOException;
    
    String icon();
    
    String favicon();
    
    /**
     * Optional, but if present certain elements within it must be present.
     * 
     * @return The author details of the feed; see {@link Author}
     * @throws RequiredElementNotPresentException if the contents of the 
     * "<code>author</code>" element do not meet their requirements
     *  
     */
    Author author() throws RequiredElementNotPresentException;
    
    boolean hasExpired();
    
    List<Item> items() throws RequiredElementNotPresentException;
    
    /**
     * Returns the next {@code Item} from the feed.
     * 
     * @return the Item
     */
    Item nextItem();
    
    /**
     * Tells us whether the feed has another item.
     * 
     * @return true if there is a next Item
     */
    boolean hasNextItem();
    
    List<Hub> hubs();
    
    boolean hasAttachments();
    
    List<Attachment> attachments();

    boolean hasExtensions();
    
    /**
     * Prints the feed;
     * 
     * @return the feed as a string
     */
    public String print();
}
