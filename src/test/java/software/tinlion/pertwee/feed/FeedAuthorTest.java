package software.tinlion.pertwee.feed;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;

import software.tinlion.pertwee.Author;

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
        
    	Gson gson = new Gson();
		Author testy = gson.fromJson(goodIfIncompleteAuthor.toString(), Author.class);
        assertEquals("Iain M Banks", testy.getName());
        assertEquals("http://blueguy.com/guy.jpg", testy.getAvatar());
        assertEquals(null, testy.getUrl());
    }
    
    @Test
    public void incompleteAuthor() {
        
       	Gson gson = new Gson();
    	Author testBad = gson.fromJson(badAuthor.toString(), Author.class);
    }

    @Test 
    public void completeAuthorOk() {
        
    	Gson gson = new Gson();
		Author testComplete = gson.fromJson(completeAuthor.toString(), Author.class);
        assertEquals("Martin McCallion", testComplete.getName());
        assertEquals("http://devilgate.org/blog/", testComplete.getUrl());
        assertEquals("http://devilgate.org/pic.jpg", testComplete.getAvatar());
    }
}
;