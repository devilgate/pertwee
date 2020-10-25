package software.tinlion.pertwee.feed;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import com.google.gson.Gson;

import software.tinlion.pertwee.Feed;

public class DefaultFeed {

    public static Feed fromString(final String jsonString) throws IOException {
    	Gson gson = new Gson();
		return gson.fromJson(jsonString, Feed.class);
    }
    
    public static Feed fromUrl(URL url) throws IOException {
        
    	Reader reader = new InputStreamReader(url.openStream());
    	char[] buffer = new char[4096];
    	int numChars;
    	StringBuilder builder = new StringBuilder();

    	while ((numChars = reader.read(buffer)) >= 0) {
    		builder.append(buffer, 0, numChars);
    	}

    	reader.close();
    	
    	Gson gson = new Gson();
		return gson.fromJson(builder.toString(), Feed.class);
    }
}
