import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.*;


public class WebParseAPI {
	protected String APIKEY;
	protected final String USER_AGENT = "Mozilla/5.0";
	
	public WebParseAPI() {
		
	}
	
	public WebParseAPI(String APIKey) {
		this.APIKEY = APIKey;
	}
	
	private String sendGet(String pageURL) throws Exception {

		String url = " https://mercury.postlight.com/parser?url=" + pageURL;

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
		
		return response.toString();
	}
	
	public String getData(String url) throws Exception {
		String data = sendGet(url);
		int beginningIndex = data.indexOf("\"content\":")+11;
		int endIndex = data.indexOf("\"next_page_url\":")-2;
		return data.substring(beginningIndex, endIndex);
	}
}
