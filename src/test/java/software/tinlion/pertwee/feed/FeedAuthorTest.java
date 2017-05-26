package software.tinlion.pertwee.feed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertthr;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import software.tinlion.pertwee.Author;

public class FeedAuthorTest {
    
    private JsonObject goodIfIncompleteAuthor;
    private JsonObject badAuthor;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setup() {
        
        goodIfIncompleteAuthor = Json.createObjectBuilder()
                .add("name", "Iain M Banks")
                .add("avatar", "http://blueguy.com/guy.jpg")
                .build();
        
        badAuthor = Json.createObjectBuilder()
                .add("avator", "jpg").build();
    }

    @Test
    public void authorFieldsOk() {
        
        Author testy = FeedAuthor.fromJson(goodIfIncompleteAuthor);
        assertEquals("Iain M Banks", testy.name());
        assertEquals("http://blueguy.com/guy.jpg", testy.avatar());
        assertNull(testy.url());
    }
    
    @Test
    public void incompleteAuthor() {
        
        exception.expect(IllegalStateException.class);
        @SuppressWarnings("unused")
        Author testBad = FeedAuthor.fromJson(badAuthor);
    }

}
;