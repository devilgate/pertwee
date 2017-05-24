package software.tinlion.pertwee;

import java.util.List;

public interface Item {

    public String id();

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
