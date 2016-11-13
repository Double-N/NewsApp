import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsAPI {

	public static void main(String[] args) throws Exception {
		NewsAPI getter = new NewsAPI("1d4fcf0134bf40cbab629a9012f10e09");
		System.out.println(getter.getArticlesFromSource("cnn"));

	}

	protected String APIKEY;
	protected final String USER_AGENT = "Mozilla/5.0";

	public NewsAPI(String APIKey) {
		this.APIKEY = APIKey;
	}

	public NewsAPI(String APIKey, String source) {
		this.APIKEY = APIKey;
	}

	private String sendGet(String source) throws Exception {

		String url = " https://newsapi.org/v1/articles?source=" + source + "&apiKey=" + APIKEY;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
		return response.toString();
	}

	public ArrayList<Article> getArticlesFromSource(String source) {
		String all = "";
		try {
			all = sendGet(source);
			} catch (Exception e) {
			System.out.println("sendGet() didn't work");
		}

		ArrayList<Article> articles = new ArrayList<>();
		String allUnparsedArticles = all.substring(all.indexOf('[') + 1, all.indexOf(']'));
		while (true) {
			String article = allUnparsedArticles.substring(allUnparsedArticles.indexOf("{") + 1,
					allUnparsedArticles.indexOf("}"));
			article.trim();
			articles.add(parseArticle(article));

			if (allUnparsedArticles.indexOf("}") + 2 >= allUnparsedArticles.length())
				break;
			allUnparsedArticles = allUnparsedArticles.substring(allUnparsedArticles.indexOf("}") + 2);
			allUnparsedArticles.replace("{}", "");
			
		}
		return articles;
	}

	private Article parseArticle(String article) {
		
		String testString = article.substring(1, article.length() - 1);

		String parsed[] = testString.split("\",\"");
		String[] artic = new String[7];
		for(int counter = 0; counter < parsed.length; counter++) {
		}
		for(int counter2 = 0; counter2 < parsed.length; counter2++)
		{
			String parsed2[] = parsed[counter2].split("\":\"");
			artic[counter2] = parsed2[parsed2.length-1];
		}

		return new Article(artic[0],artic[1],artic[2],artic[3],artic[4],artic[5]);
	}

}
