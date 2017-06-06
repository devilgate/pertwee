package software.tinlion.pertwee;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public interface Attachment {

    public String url() throws RequiredElementNotPresentException;
    
    public String mimeType() throws RequiredElementNotPresentException;
    
    public String title();
    
    public long sizeInBytes();
    
    public long durationInSeconds();
}
