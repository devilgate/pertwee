package software.tinlion.pertwee;

import java.util.List;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

/**
 * Represents an item in a feed, eg a blog post, podcast episode, etc.
 */
public interface Item {

    public String id() throws RequiredElementNotPresentException;

    public String contentText();

    public String contentHtml();

    public String url();

    public String externalUrl();

    public String title();

    public String summary();

    public String image();

    public String bannerImage();

    public String datePublished();

    public String dateModified();

    public Author author();
    List<Author> authors() throws RequiredElementNotPresentException;

    public List<String> tags();

    public String language();

    boolean hasAttachments();

    List<Attachment> attachments();
}
