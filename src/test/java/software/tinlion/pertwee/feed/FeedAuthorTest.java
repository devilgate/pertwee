package software.tinlion.pertwee.feed;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class FeedAuthorTest {
    
    private JSONObject goodIfIncompleteAuthor;
    private JSONObject badAuthor;
    private JSONObject completeAuthor;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setup() {
        
        goodIfIncompleteAuthor = new JSONObject();
        goodIfIncompleteAuthor.put("name", "Iain M Banks");
        goodIfIncompleteAuthor.put("avatar", "http://blueguy.com/guy.jpg");
        
        badAuthor = new JSONObject();
        badAuthor.put("avatar", "jpg");
        
        completeAuthor = new JSONObject();
        completeAuthor.put("name", "Martin McCallion");
        completeAuthor.put("url", "http://devilgate.org/blog/");
        completeAuthor.put("avatar", "http://devilgate.org/pic.jpg");
    }

    @Test
    public void authorFieldsOk() {
        
        Author testy = FeedAuthor.fromJson(goodIfIncompleteAuthor);
        assertEquals("Iain M Banks", testy.name());
        assertEquals("http://blueguy.com/guy.jpg", testy.avatar());
        assertEquals("", testy.url());
    }
    
    @Test
    public void incompleteAuthor() {
        
        exception.expect(RequiredElementNotPresentException.class);
        @SuppressWarnings("unused")
        Author testBad = FeedAuthor.fromJson(badAuthor);
    }

    @Test 
    public void completeAuthorOk() {
        
        Author testComplete = FeedAuthor.fromJson(completeAuthor);
        assertEquals("Martin McCallion", testComplete.name());
        assertEquals("http://devilgate.org/blog/", testComplete.url());
        assertEquals("http://devilgate.org/pic.jpg", testComplete.avatar());
    }
}
;