package software.tinlion.pertwee;

public interface Attachment {

    public String url();
    
    public String mimeType();
    
    public String title();
    
    public long sizeInBytes();
    
    public long durationInSeconds();
}
