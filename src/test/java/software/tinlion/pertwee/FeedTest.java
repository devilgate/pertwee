package software.tinlion.pertwee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import software.tinlion.pertwee.feed.DefaultFeed;

public class FeedTest {

    private static final String SIMPLE_EXAMPLE = "{" +
            "\"version\": \"https://jsonfeed.org/version/1.1\"," +
            "\"title\": \"My Example Feed\"," +
            "\"home_page_url\": \"https://example.org/\"," +
            "\"feed_url\": \"https://example.org/feed.json\"," +
            "\"author\": " +
                "{\"name\": \"Martin McCallion\"}," +
            "\"authors\": " +
                "[ {\"name\": \"Silurians\"},  {\"name\": \"Daleks\"} ]," +
            "\"language\": \"en-GB\"," +
            "\"items\": [ "+
                "{" +
                    "\"id\": \"2\"," +
                    "\"content_text\": \"This is a second item.\"," +
                    "\"url\": \"https://example.org/second-item\"," +
                    "\"language\": \"en-IE\"," +
                "}, " +
                "{ "+
                    "\"id\": \"1\", " +
                    "\"content_html\": \"<p>Hello, world!</p>\", " +
                    "\"url\": \"https://example.org/initial-post\"," +
                    "\"language\": \"en-US\"," +
                "} " +
            "] " +
        "}";

    private Feed SIMPLE_FEED;

    @Before
    public void setup() throws IOException {

        SIMPLE_FEED = DefaultFeed.fromString(SIMPLE_EXAMPLE);

    }

    @Test
    public void testVersionInitialised() {

        assertEquals("https://jsonfeed.org/version/1.1", SIMPLE_FEED.version());
    }

    @Test
    public void testTitleInitialised() {

        assertEquals("My Example Feed", SIMPLE_FEED.title());
    }

    @Test
    public void testHomePageInitialised() {

        assertEquals("https://example.org/", SIMPLE_FEED.homePageUrl());
    }

    @Test
    public void testHFeedUrlInitialised() {

        assertEquals("https://example.org/feed.json", SIMPLE_FEED.feedUrl());
    }

    @Test
    public void testLanguageInitialised() {

        assertEquals("en-GB", SIMPLE_FEED.language());
    }

    @Test
    public void authorsArrayLengthIs2() {

        assertEquals(2, SIMPLE_FEED.authors().size());
    }

    @Test
    public void authorsAreCorrect() {

        Author a = SIMPLE_FEED.authors().get(0);
        assertEquals("Silurians", a.name());

        Author a2 = SIMPLE_FEED.authors().get(1);
        assertEquals("Daleks", a2.name());
    }

    @Test
    public void itemsArrayLengthIs2() {

        assertEquals(2, SIMPLE_FEED.items().size());
    }

    @Test
    public void itemsAreCorrect() {

        assertTrue(SIMPLE_FEED.hasNextItem());
        Item i = SIMPLE_FEED.nextItem();
        assertEquals("2", i.id());
        assertEquals("This is a second item.", i.contentText());
        assertEquals("en-IE", i.language());

        assertTrue(SIMPLE_FEED.hasNextItem());
        Item i2 = SIMPLE_FEED.nextItem();
        assertEquals("1", i2.id());
        assertEquals("<p>Hello, world!</p>", i2.contentHtml());
        assertEquals("https://example.org/initial-post", i2.url());
        assertEquals("en-US", i2.language());

        assertFalse(SIMPLE_FEED.hasNextItem());
    }

}
