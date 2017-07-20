package software.tinlion.pertwee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import software.tinlion.pertwee.feed.DefaultItem;
import software.tinlion.pertwee.feed.FeedAuthor;

public class ItemTest {
    
    private static final String TAG3 = "tag3";

    private static final String TAG2 = "tag2";

    private static final String TAG1 = "tag1";

    private static final String MR_BRIGHTSIDE = "Mr Brightside";

    private static final String AN_ARBITRARY_TIME = "2017-07-20T16:00:01-14:32";

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
        
        feedAuthor = FeedAuthor.fromJson(Json.createObjectBuilder()
                .add("name", "Martin McCallion")
                .add("url", "http://devilgate.org/blog/")
                .add("avatar", "http://devilgate.org/pic.jpg")
                .build());
        
        JsonObject objItem = Json.createObjectBuilder()
                .add("id", "001")
                .add("content_text", THIS_IS_A_SECOND_ITEM)
                .add("content_html", HTML_THIS_IS_A_SECOND_ITEM)
                .add("url", THE_URL)
                .add("external_url", "")
                .add("summary", NOT_MUCH)
                .add("date_modified", AN_ARBITRARY_TIME)
                .build();
        item = DefaultItem.parseItem(objItem, feedAuthor);
        
        // Second item is first but with its own Author and tags
        JsonObject objItem2 = Json.createObjectBuilder(objItem)
                .add("author", 
                     Json.createObjectBuilder()
                         .add("name", MR_BRIGHTSIDE))
                .add("tags", Json.createArrayBuilder()
                        .add(TAG1).add(TAG2).add(TAG3).build())
                .build();
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
