package software.tinlion.pertwee;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import software.tinlion.pertwee.feed.DefaultFeed;

/**
 * The main interface for the Pertwee JSON Feed parser. See 
 * <a href="https://jsonfeed.org/version/1">the JSON Feed spec</a>.
 * 
 * To get an instance use one of the factory methods in {@link DefaultFeed}.
 * 
 * Once you have an instance you can use the various methods to get the 
 * various elements of the feed.
 * 
 * This implementation makes a fairly strict interpretation of the spec. 
 * Where an element is marked as "<code>required</code>", such as 
 * <code>version</code>, for example, a {@code RequiredElementNotPresentException} 
 * will be thrown. This is a RuntimeException, but methods for handling 
 * required elements declare it thrown, to allow client classes to decide
 * how to handle it.
 * 
 * Most of the methods here simply return the comparably-named element from 
 * the provided JSON (for example, <code>feedUrl</code> returns the element called 
 * "<code>feed_url</code>"). See the spec for detailed explanations of what each 
 * element means.
 * 
 * @author Martin McCallion (martin@tinlion.software)
 * @version 1.0.1
 *
 */
public class Feed {
	
	private String version;
	private String title;
	@SerializedName(value = "home_page_url")
	private String homePageUrl;
	@SerializedName(value = "feed_url")
	private String feedUrl;
	private String description;
	@SerializedName(value = "user_comment")
	private String userComment;
	@SerializedName(value = "next_url")
	private String nextUrl;
	private String icon;
	private String favicon;
	private Author author;
	private boolean expired;
	private ArrayList<Hub> hubs = new ArrayList<Hub>();
	private ArrayList<Item> items = new ArrayList<Item>();

	 /**
     * The version is required.
     * 
     * @return the version as a string
     */
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	 /** 
     * Title is required.
     * 
     * @return the title
     */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    /**
     * This is described as "optional but strongly recommended."
     * 
     * @return the URL
     */
	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

    /**
     * This is described as "optional but strongly recommended."
     * 
     * @return the URL
     */
	public String getFeedUrl() {
		return feedUrl;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getFavicon() {
		return favicon;
	}

	public void setFavicon(String favicon) {
		this.favicon = favicon;
	}

	/**
     * Optional, but if present certain elements within it must be present.
     * 
     * @return The author details of the feed; see {@link Author}
     *  
     */
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public List<Hub> getHubs() {
		return hubs;
	}

	public void setHubs(ArrayList<Hub> hubs) {
		this.hubs = hubs;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}	
	
	/**
     * A convenience method to get the contents of nextUrl as a Feed.
     *  
     * @return the next feed
     * @throws IOException on problems
     */
    public Feed nextFeed() throws IOException {
    	String nextUrl = this.getNextUrl();
    	
    	if (nextUrl == null) {
    		return null;
    	}
    	
		return DefaultFeed.fromUrl(new URL(nextUrl));
    }
    
    @Override
    public String toString() {    
        StringBuilder output = new StringBuilder();
        
        output.append(getVersion()).append("\n");
        output.append(getTitle()).append("\n");
        output.append(getHomePageUrl()).append("\n");
        output.append(getFeedUrl()).append("\n");
        output.append(getDescription()).append("\n");
        output.append(getUserComment()).append("\n");
        output.append(getNextUrl()).append("\n");
		output.append(getIcon()).append("\n");
		output.append(getFavicon()).append("\n");
		output.append(getAuthor().getName()).append("\n");
		output.append(getAuthor().getUrl()).append("\n");
		output.append(getAuthor().getAvatar()).append("\n");
		output.append(isExpired()).append("\n");
		output.append(getHubs().size()).append("\n");
		
		output.append("---Hubs---").append("\n");
		
		for (Hub hub: getHubs()) {
			output.append(hub.getType()).append("\n");
			output.append(hub.getUrl()).append("\n");
		}
		
		output.append(getItems().size()).append("\n");
		
		output.append("---Items---").append("\n");
		
		for (Item item: getItems()) {
			output.append(item.getId()).append("\n");
			output.append(item.getUrl()).append("\n");
			output.append(item.getExternalUrl()).append("\n");
			output.append(item.getTitle()).append("\n");
			output.append(item.getContentHtml()).append("\n");
			output.append(item.getContentText()).append("\n");
			output.append(item.getSummary()).append("\n");
			output.append(item.getImage()).append("\n");
			output.append(item.getBannerImage()).append("\n");
			output.append(item.getDatePublished()).append("\n");
			output.append(item.getDateModified()).append("\n");
			output.append(item.getAuthor()).append("\n");
			output.append(item.getTags().size()).append("\n");
			
			output.append("---Tags---").append("\n");
			
			for (String tag: item.getTags()) {
				output.append(tag).append("\n");
			}
			
			output.append(item.getAttachments().size()).append("\n");
			
			output.append("---Attachments---").append("\n");
			
			for (Attachment attachment: item.getAttachments()) {
				output.append(attachment.getUrl()).append("\n");
				output.append(attachment.getMimeType()).append("\n");
				output.append(attachment.getTitle()).append("\n");
				output.append(attachment.getSizeInBytes()).append("\n");
				output.append(attachment.getDurationInSeconds()).append("\n");
			}
		}
        
        return output.toString();
    }
}
