package software.tinlion.pertwee;

/**
 * Hubs are, according the JSON Feed spec: 
   <blockquote>
       endpoints that can be used to subscribe to real-time notifications from the publisher of this feed.
   </blockquote>
 * 
 * @author Martin McCallion (martin@tinlion.software)
 *
 */
public interface Hub {

    public String type();
    
    public String url();
}
