package software.tinlion.pertwee;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

/**
 * 
 * Represents an attachment to a feed. According to the spec:
   <blockquote>
       attachments (optional, array) lists related resources. 
       Podcasts, for instance, would include an attachment that’s 
       an audio or video file.
   </blockqoute>
 * 
 * @author Martin McCallion (martin@tinlion.software)
 *
 */
public interface Attachment {

    public String url() throws RequiredElementNotPresentException;
    
    public String mimeType() throws RequiredElementNotPresentException;
    
    public String title();
    
    public long sizeInBytes();
    
    public long durationInSeconds();
}
