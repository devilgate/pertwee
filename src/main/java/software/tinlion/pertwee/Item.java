package software.tinlion.pertwee;

/**
 * Represents an item in a feed, eg a blog post, podcast episode, etc.
 */
import java.util.List;

import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

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

    public List<String> tags();

}
