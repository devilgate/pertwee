package software.tinlion.pertwee;

/**
 * Hubs are, according the JSON Feed spec: 
   <blockquote>
       endpoints that can be used to subscribe to real-time notifications from the publisher of this feed.
   </blockquote>
 * 
 * @author Martin McCallion (martin@devilgate.org)
 *
 */
public interface Hub {

    String type();
    
    String url();
}
