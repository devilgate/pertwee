package software.tinlion.pertwee.feed;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import software.tinlion.pertwee.Attachment;
import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.Feed;
import software.tinlion.pertwee.Hub;
import software.tinlion.pertwee.Item;
import software.tinlion.pertwee.check.GetIfPresent;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class DefaultFeed implements Feed {

    private JSONObject feedObject;
    private GetIfPresent feedGet;
    private List<Item> itemsInFeed;
    private int itemsIndex;

    public static Feed fromString(final String jsonString) throws IOException {

        return new DefaultFeed(jsonString);
    }

    public static Feed fromUrl(URL url) throws IOException {

        return new DefaultFeed(url);
    }

    private DefaultFeed(final Reader reader) throws IOException {

        char[] buffer = new char[4096];
        int numChars;
        StringBuilder builder = new StringBuilder();

        while ((numChars = reader.read(buffer)) >= 0) {
        	builder.append(buffer, 0, numChars);
        }

        feedObject = new JSONObject(builder.toString());
        feedGet = new GetIfPresent(feedObject);
    }

    private DefaultFeed(final URL url) throws IOException {

        this(new InputStreamReader(url.openStream()));
    }

    private DefaultFeed(final String jsonString) throws IOException {

        this(new StringReader(jsonString));
    }

    @Override
    public boolean hasNextItem() {

        // Initialise first time through
        if (itemsInFeed == null) {
            itemsInFeed = items();
            itemsIndex = 0;
            if (itemsInFeed == null) {

                // Not sure if this is possible, but maybe if there are no items
                return false;
            }
        }
            return itemsInFeed.size() > 0 && itemsIndex < itemsInFeed.size();
    }

    @Override
    public Item nextItem() {

        // Initialise first time through
        if (itemsInFeed == null) {
            itemsInFeed = items();
            itemsIndex = 0;
        }
        return itemsInFeed.get(itemsIndex++);
    }

    @Override
    public String version() throws RequiredElementNotPresentException {

        return feedGet.getString("version", true);
    }

    @Override
    public String title() throws RequiredElementNotPresentException {

        return feedGet.getString("title", true);
    }

    @Override
    public String homePageUrl() {

        return feedGet.getString("home_page_url", false);
    }

    @Override
    public String feedUrl() {

        return feedGet.getString("feed_url", false);
    }

    @Override
    public String description() {

        return feedGet.getString("description", false);
    }

    @Override
    public String language() {

        return feedGet.getString("language", false);
    }

    @Override
    public String userComment() {

        return feedGet.getString("user_comment", false);
    }

    @Override
    public String nextUrl() {

        return feedGet.getString("next_url", false);
    }

    @Override
    public Feed nextFeed() throws IOException {

        return DefaultFeed.fromString(nextUrl());
    }

    @Override
    public String icon() {

        return feedGet.getString("icon", false);
    }

    @Override
    public String favicon() {

        return feedGet.getString("favicon", false);
    }

    @Override
    public Author author() throws RequiredElementNotPresentException {

        if (feedObject.optJSONObject("author") != null) {
            return FeedAuthor.fromJson(feedObject.getJSONObject("author"));

        } else {
            return FeedAuthor.nullAuthor();
        }
    }

    @Override
    public List<Author> authors() throws RequiredElementNotPresentException {

        List<Author> authors = new ArrayList<>();
        if (feedObject.optJSONArray("authors") != null) {
            for (Object val : feedObject.optJSONArray("authors")) {
                authors.add(FeedAuthor.fromJson((JSONObject) val));
            }
        }
        return authors;
    }

    @Override
    public boolean hasExpired() {

        return feedObject.getBoolean("expired");
    }

    @Override
    public List<Item> items() throws RequiredElementNotPresentException {

        if (feedObject.optJSONArray("items") == null) {

            throw new RequiredElementNotPresentException("Element 'items' is"
                    + " required, but was not found in the feed.");
        }
        List<Item> items = new ArrayList<>();
        for (Object val : feedObject.getJSONArray("items")) {

            items.add(DefaultItem.parseItem((JSONObject) val, author(), authors()));
        }
        return items;
    }

    @Override
    public List<Hub> hubs() {

        if (feedObject.optJSONArray("hubs") != null) {

            return SubHub.parseHubsFromJson(feedObject.getJSONArray("hubs"));
        }
        return null;
    }

    @Override
    public boolean hasExtensions() {
    	Iterator<String> iterator = feedObject.keys();

    	while (iterator.hasNext()) {
    		if (iterator.next().startsWith("_")) {
                return true;
            }
    		iterator.remove();
    	}
        return false;
    }

    public String print() {

        StringBuilder output = new StringBuilder();
        output
            .append(version()).append("\n")
            .append(title()).append("\n")
            .append(homePageUrl()).append("\n")
            .append(feedUrl()).append("\n")
            .append(description()).append("\n")
            .append(userComment()).append("\n")
            .append(language()).append("\n")
            .append(nextUrl()).append("\n")
            .append(icon()).append("\n")
            .append(favicon()).append("\n")
            .append(author()).append("\n").append("\n")
            .append(authors()).append("\n").append("\n")
            ;

        for (Item i : items()) {

            output.append(i.title()).append("\n")
                .append(i.id()).append("\n")
                .append(i.dateModified()).append("\n")
                .append(i.datePublished()).append("\n")
                .append(i.summary()).append("\n")
                .append(i.author()).append("\n")
                .append(i.authors()).append("\n")
                .append(i.url()).append("\n")
                .append(i.language()).append("\n")
                .append(i.contentHtml()).append("\n")
                .append(i.contentText()).append("\n");
            if (!i.tags().isEmpty()) {
                output.append("[");
                for (String tag : i.tags()) {
                    output.append(tag).append(", ");
                }
                output.setLength(output.length() - 1); // Remove last comma.
                output.append("]\n");
            }
        }

        // TODO: more

        return output.toString();
    }

}
