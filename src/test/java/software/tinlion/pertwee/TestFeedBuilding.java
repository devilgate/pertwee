package software.tinlion.pertwee;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import software.tinlion.pertwee.feed.SimpleFeed;

public class TestFeedBuilding {
    
    private static final String SIMPLE_EXAMPLE = "{" + 
            "\"version\": \"https://jsonfeed.org/version/1\"," + 
            "\"title\": \"My Example Feed\"," + 
            "\"home_page_url\": \"https://example.org/\"," + 
            "\"feed_url\": \"https://example.org/feed.json\"," + 
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
        
        SIMPLE_FEED = SimpleFeed.fromString(SIMPLE_EXAMPLE);
    }

    @Test
    public void testVersionInitialised() {
        
        assertEquals("https://jsonfeed.org/version/1", SIMPLE_FEED.version());
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
    public void itemsArrayLengthIs2() {
        
        assertEquals(2, SIMPLE_FEED.items().size());
    }

}
