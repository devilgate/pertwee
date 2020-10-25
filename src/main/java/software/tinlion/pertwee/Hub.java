package software.tinlion.pertwee;

/**
 * Hubs are, according the JSON Feed spec: 
   <blockquote>
       endpoints that can be used to subscribe to real-time notifications from the publisher of this feed.
   </blockquote>
 * 
 * @author Martin McCallion (martin@tinlion.software)
 *
 */
public class Hub {

	private String type;
	private Author url;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Author getUrl() {
		return url;
	}
	public void setUrl(Author url) {
		this.url = url;
	}
}
