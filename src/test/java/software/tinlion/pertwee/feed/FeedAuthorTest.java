package software.tinlion.pertwee.feed;

import static org.junit.Assert.assertEquals;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import software.tinlion.pertwee.Author;
import software.tinlion.pertwee.exception.RequiredElementNotPresentException;

public class FeedAuthorTest {
    
    private JsonObject goodIfIncompleteAuthor;
    private JsonObject badAuthor;
    private JsonObject completeAuthor;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setup() {
        
        goodIfIncompleteAuthor = Json.createObjectBuilder()
                .add("name", "Iain M Banks")
                .add("avatar", "http://blueguy.com/guy.jpg")
                .build();
        
        badAuthor = Json.createObjectBuilder()
                .add("avatar", "jpg").build();
        
        completeAuthor = Json.createObjectBuilder()
                .add("name", "Martin McCallion")
                .add("url", "http://devilgate.org/blog/")
                .add("avatar", "http://devilgate.org/pic.jpg")
                .build();
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