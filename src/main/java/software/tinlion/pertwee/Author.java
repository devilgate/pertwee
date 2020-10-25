package software.tinlion.pertwee;

/**
 * Represents the author of the {@code Feed} or of an {@code Item}.
 * 
 * @author martin
 *
 */
public class Author {

	private String name;
	private String url;
	private String avatar;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}	
}
