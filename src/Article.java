
public class Article {
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private String publishDate;
	
	public Article(String author, String title, String description,
			String url, String urlToImage, String publishDate) {
		this.author = author;
		this.title = title;
		this.description = description;
		this.url = url;
		this.urlToImage = urlToImage;
		this.publishDate = publishDate;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	public String getURL() {
		return url;
	}
	public String getURLToImage() {
		return urlToImage;
	}
	public String getPublishDate() {
		return publishDate;
	}
	
	public String toString() {
		String result= "";
		result+=("Author: "+ author+"\n");
		result+=("Title: "+ title+"\n");
		result+=("Description: "+ description+"\n");
		result+=("URL: "+ url+"\n");
		result+=("url To Image: "+ urlToImage+"\n");
		result+=("publish date: " +publishDate+"\n");
		return result;
	}
}
