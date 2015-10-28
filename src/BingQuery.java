import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Base64.Encoder;

public class BingQuery {
	static String Query(String site, String query, String accountKey) {
		String bingUrl = "https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/Composite?Query=%27site%3a[site]%20[query]%27&$top=10&$format=Atom";
		bingUrl = bingUrl.replace("[query]", query);
		bingUrl = bingUrl.replace("[site]", site);
		
		Encoder e = Base64.getEncoder();
		byte[] accountKeyBytes = e.encode((accountKey + ":" + accountKey).getBytes());
		String accountKeyEnc = new String(accountKeyBytes);
		
		System.out.println(accountKeyEnc);

		URL url;
		String content = null;
		try {
			url = new URL(bingUrl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
					
			InputStream inputStream = (InputStream) urlConnection.getContent();		
			byte[] contentRaw = new byte[urlConnection.getContentLength()];
			inputStream.read(contentRaw);
			content = new String(contentRaw);
			
			System.out.println(content);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return content;
	}
}
