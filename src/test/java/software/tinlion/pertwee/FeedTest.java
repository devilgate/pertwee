package software.tinlion.pertwee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import software.tinlion.pertwee.feed.DefaultFeed;

public class FeedTest {
    
    private static final String SIMPLE_EXAMPLE = "{" + 
            "\"version\": \"https://jsonfeed.org/version/1\"," + 
            "\"title\": \"My Example Feed\"," + 
            "\"home_page_url\": \"https://example.org/\"," + 
            "\"feed_url\": \"https://example.org/feed.json\"," +
            "\"author\": " +
                "{\"name\": \"Martin McCallion\"}," +
            "\"items\": [ "+ 
                "{" + 
                    "\"id\": \"2\"," + 
                    "\"content_text\": \"This is a second item.\"," + 
                    "\"url\": \"https://example.org/second-item\" " + 
                "}, " + 
                "{ "+  
                    "\"id\": \"1\", " + 
                    "\"content_html\": \"<p>Hello, world!</p>\", " + 
                    "\"url\": \"https://example.org/initial-post\" " + 
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
        
        assertEquals("https://jsonfeed.org/version/1", SIMPLE_FEED.getVersion());
    }
    
    @Test
    public void testTitleInitialised() {
        
        assertEquals("My Example Feed", SIMPLE_FEED.getTitle());
    }
    
    @Test
    public void testHomePageInitialised() {
        
        assertEquals("https://example.org/", SIMPLE_FEED.getHomePageUrl());
    }
    
    @Test
    public void testHFeedUrlInitialised() {
        
        assertEquals("https://example.org/feed.json", SIMPLE_FEED.getFeedUrl());
    }
    
    @Test
    public void itemsArrayLengthIs2() {
        
        assertEquals(2, SIMPLE_FEED.getItems().size());
    }
    
    @Test
    public void itemsAreCorrect() {
        
        assertTrue(SIMPLE_FEED.getItems().get(0) != null);
        Item i = SIMPLE_FEED.getItems().get(0);
        assertEquals("2", i.getId());
        assertEquals("This is a second item.", i.getContentText());
        
        assertTrue(SIMPLE_FEED.getItems().get(1) != null);
        Item i2 = SIMPLE_FEED.getItems().get(1);
        assertEquals("1", i2.getId());
        assertEquals("<p>Hello, world!</p>", i2.getContentHtml());
        assertEquals("https://example.org/initial-post", i2.getUrl());
        
        assertFalse(SIMPLE_FEED.getItems().size() > 2);
    }

}
