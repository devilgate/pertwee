package software.tinlion.pertwee;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * Represents an attachment to a feed. According to the spec:
   <blockquote>
       attachments (optional, array) lists related resources. 
       Podcasts, for instance, would include an attachment that’s 
       an audio or video file.
   </blockquote>
 * 
 * @author Martin McCallion (martin@tinlion.software)
 *
 */
public class Attachment {

	private String url;
	@SerializedName(value = "mime_type")
	private String mimeType;
	private String title;
	@SerializedName(value = "size_in_bytes")
	private long sizeInBytes;
	@SerializedName(value = "duration_in_seconds")
	private long durationInSeconds;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public long getSizeInBytes() {
		return sizeInBytes;
	}
	
	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}
	
	public long getDurationInSeconds() {
		return durationInSeconds;
	}
	
	public void setDurationInSeconds(long durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
}
