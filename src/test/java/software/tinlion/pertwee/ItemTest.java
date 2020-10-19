package software.tinlion.pertwee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import software.tinlion.pertwee.feed.DefaultItem;
import software.tinlion.pertwee.feed.FeedAuthor;

public class ItemTest {
    
    private static final String TAG3 = "tag3";

    private static final String TAG2 = "tag2";

    private static final String TAG1 = "tag1";

    private static final String MR_BRIGHTSIDE = "Mr Brightside";

    private static final String AN_ARBITRARY_TIME = "2017-07-20T16:00:01-04:30";

    private static final String NOT_MUCH = "Not much";

    private static final String THE_URL = "https://example.org/second-item";

    private static final String HTML_THIS_IS_A_SECOND_ITEM = "<p>This is a second item.</p>";

    private static final String THIS_IS_A_SECOND_ITEM = "This is a second item.";
    // UnitOfWork_StateUnderTest_ExpectedBehavior
    
    private Item item;
    private Item itemWithAuthor;
    private Author feedAuthor;

    @Before
    public void setUp() throws Exception {
        
    	JSONObject objFeedAuthor = new JSONObject();
    	objFeedAuthor.put("name", "Martin McCallion");
    	objFeedAuthor.put("url", "http://devilgate.org/blog/");
    	objFeedAuthor.put("avatar", "http://devilgate.org/pic.jpg");
    	feedAuthor = FeedAuthor.fromJson(objFeedAuthor);
    	
        JSONObject objItem = new JSONObject();
        objItem.put("id", "001");
        objItem.put("content_text", THIS_IS_A_SECOND_ITEM);
        objItem.put("content_html", HTML_THIS_IS_A_SECOND_ITEM);
        objItem.put("url", THE_URL);
        objItem.put("external_url", "");
        objItem.put("summary", NOT_MUCH);
        objItem.put("date_modified", AN_ARBITRARY_TIME);
        item = DefaultItem.parseItem(objItem, feedAuthor);
        
        // Second item is first but with its own Author and tags
        JSONObject objItem2 = new JSONObject(objItem);
        JSONObject objAuthor = new JSONObject();
        objAuthor.put("name", MR_BRIGHTSIDE);
        objItem2.put("author", objAuthor);
        objItem2.put("tags", new JSONArray(Arrays.asList(TAG1, TAG2, TAG3)));
        itemWithAuthor = DefaultItem.parseItem(objItem2, feedAuthor);
    }

    @Test
    public final void id_created_returnsCorrectValue() {
        
        assertEquals("001", item.id());
    }

    @Test
    public final void contentText_created_returnsCorrectValue() {
        
        assertEquals(THIS_IS_A_SECOND_ITEM, item.contentText());
    }

    @Test
    public final void contentHtml_created_returnsCorrectValue() {
        
        assertEquals(HTML_THIS_IS_A_SECOND_ITEM, item.contentHtml());
    }

    @Test
    public final void url_created_returnsCorrectValue() {
        
        assertEquals(THE_URL, item.url());
    }

    @Test
    public final void externalUrl_created_returnsEmptyString() {
        
        assertEquals("", item.externalUrl());
    }

    @Test
    public final void title_notSet_returnsEmptyString() {
        
        assertEquals("", item.title());
    }

    @Test
    public final void summary_created_returnsCorrectValue() {
        
        assertEquals(NOT_MUCH, item.summary());
    }

    @Test
    public final void image_notSet_returnsEmptyString() {
        
        assertEquals("", item.image());
    }

    @Test
    public final void bannerImage_notSet_returnsEmptyString() {
        
        assertEquals("", item.bannerImage());
    }

    @Test
    public final void datePublished_notSet_returnsEmptyString() {
        
        assertEquals("", item.datePublished());
    }

    @Test
    public final void DateModified_created_returnsCorrectValue() {
        
        assertEquals(AN_ARBITRARY_TIME, item.dateModified());
    }

    @Test
    public final void author_notSetInItem_returnsFeedAuthor() {
        
        assertEquals(feedAuthor, item.author());
    }
    
    @Test
    public final void author_setInItem_returnsItemAuthor() {
        
        assertEquals(MR_BRIGHTSIDE, itemWithAuthor.author().name());
    }

    @Test
    public final void tags_notSet_returnsEmptyList() {
        
        assertTrue(item.tags().isEmpty());
    }
    
    @Test 
    public final void tags_set_returnsListOfStrings() {
        
        List<String> tags = itemWithAuthor.tags();
        assertEquals(TAG1, tags.get(0));
        assertEquals(TAG2, tags.get(1));
        assertEquals(TAG3, tags.get(2));
    }

}
