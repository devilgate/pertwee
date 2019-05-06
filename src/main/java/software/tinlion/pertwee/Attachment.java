package software.tinlion.pertwee;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

/**
 * 
 * Represents an attachment to a feed. According to the spec:
   <blockquote>
       attachments (optional, array) lists related resources. 
       Podcasts, for instance, would include an attachment that’s 
       an audio or video file.
   </blockquote>
 * 
 * @author Martin McCallion (martin@tinlion.software)
 *
 */
public interface Attachment {

    String url() throws RequiredElementNotPresentException;
    
    String mimeType() throws RequiredElementNotPresentException;
    
    String title();
    
    long sizeInBytes();
    
    long durationInSeconds();
}
