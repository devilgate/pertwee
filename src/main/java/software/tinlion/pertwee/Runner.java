package software.tinlion.pertwee;

import java.io.IOException;
import java.net.URL;

import software.tinlion.pertwee.feed.DefaultFeed;

/** 
 * Runs and tests the Pertwee library. Just call it with a URL and see what goes to stdout.
 * 
 * @author Martin McCallion (
 *
 */
public class Runner {
    
    public static void main(String... args) throws IllegalArgumentException, IOException {
        
        if (args == null || args[0] == null || args[0].length() == 0) {
            
            throw new IllegalArgumentException("I need a URL");
        }
        
        URL url = new URL(args[0]);
        Feed feed = DefaultFeed.fromUrl(url);
        System.out.println(feed.print());
    }

}
