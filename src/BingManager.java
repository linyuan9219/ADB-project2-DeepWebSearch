import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BingManager {
	static String accountKey;
	static String site_record;
	static String query_record;
	static String content_record;
	
	static void SetAccountKey(String key) {
		accountKey = key;
	}
	
	static String Query4Content(String site, String query) {
		
		if (site.equals(site_record) && query.equals(query_record)) {
			return content_record;
		}
		else {
			site_record = site;
			query_record = query;
		}
		
		String bingUrl = "https://api.datamarket.azure.com/Data.ashx/Bing/SearchWeb/v1/Composite?Query=%27site%3a[site]%20[query]%27&$top=10&$format=Atom";
		String utf8_query = "";
		try {
			utf8_query = URLEncoder.encode(query, "utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			site_record = "";
			query_record = "";
			return "";
		}
		bingUrl = bingUrl.replace("[query]", utf8_query);
		bingUrl = bingUrl.replace("[site]", site);
		
		Encoder e = Base64.getEncoder();
		byte[] accountKeyBytes = e.encode((accountKey + ":" + accountKey).getBytes());
		String accountKeyEnc = new String(accountKeyBytes);

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
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		content_record = content;
		return content;
	}
	
	static int Query4RelevantWebNum(String site, String query) {
		String query_res = Query4Content(site, query);
		int res = 0;
	
		Pattern p = Pattern.compile(".*<d:WebTotal.*?>(.*?)</d:WebTotal>.*");
		Matcher m = p.matcher(query_res);
		m.matches();
		
		if (m.groupCount() > 0)
			res = Integer.parseInt(m.group(1));
		
		return res;
	}
	
	static String Query4RelevantWebContent(String site, String query, int topn) {
		return null;
	}
}
