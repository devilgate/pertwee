package software.tinlion.pertwee.feed;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;

import software.tinlion.pertwee.Attachment;
import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.Item;
import software.tinlion.pertwee.check.GetIfPresent;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class DefaultItem implements Item {

    private JSONObject itemObject;
    private GetIfPresent feedGet;
    private final Author feedAuthor;
    private final List<Author> feedAuthors;

    public static Item parseItem(JSONObject value, Author feedAuthor, List<Author> feedAuthors) {

        return new DefaultItem(value, feedAuthor, feedAuthors);
    }

    private DefaultItem(JSONObject value, Author feedAuthor, List<Author> feedAuthors) {

        if (!(value instanceof JSONObject)) {

            throw new IllegalStateException("Received a JsonValue which "
                    + "is not a JsonObject. Value is " + value);
        }

        itemObject = (JSONObject)value;
        this.feedAuthor = feedAuthor;
        this.feedAuthors = feedAuthors;
        feedGet = new GetIfPresent(itemObject);
    }

    @Override
    public String id() throws RequiredElementNotPresentException {

        return feedGet.getString("id", true);
    }

    @Override
    public String contentText() {

        return feedGet.getString("content_text", false);
    }

    @Override
    public String contentHtml() {

        return feedGet.getString("content_html", false);
    }

    @Override
    public String url() {

        return feedGet.getString("url", false);
    }

    @Override
    public String externalUrl() {

        return feedGet.getString("external_url", false);
    }

    @Override
    public String title() {

        return feedGet.getString("title", false);
    }

    @Override
    public String summary() {

        return feedGet.getString("summary", false);
    }

    @Override
    public String image() {

        return feedGet.getString("image", false);
    }

    @Override
    public String bannerImage() {

        return feedGet.getString("banner_image", false);
    }

    @Override
    public String datePublished() {

        return feedGet.getString("date_published", false);
    }

    @Override
    public String dateModified() {

        return feedGet.getString("date_modified", false);
    }

    @Override
    public Author author() {

        if (itemObject.optJSONObject("author") != null) {
            return FeedAuthor.fromJson(itemObject.getJSONObject("author"));
        } else {
            return feedAuthor;
        }
    }


    @Override
    public List<Author> authors() {

        if (itemObject.optJSONArray("authors") != null) {
            List<Author> authors = new ArrayList<>();
            for (Object val : itemObject.getJSONArray("authors")) {
                authors.add(FeedAuthor.fromJson((JSONObject) val));
            }
            return authors;
        } else {
            return feedAuthors;
        }
    }



    @Override
    public List<String> tags() {

        return feedGet.getStringList("tags");
    }

    @Override
    public String language() {

        return feedGet.getString("language", false);
    }

    @Override
    public String toString() {

        return itemObject.toString();
    }

    @Override
    public boolean hasAttachments() {

        return itemObject.has("attachments")
               && itemObject.optJSONArray("attachements") != null
               && !itemObject.getJSONArray("attachments").isEmpty();
    }

    @Override
    public List<Attachment> attachments() {

        if (hasAttachments()) {

            return AnAttachment.parseAttachmentsFromJson(itemObject.getJSONArray("attachments"));
        }
        return null;
    }


}
