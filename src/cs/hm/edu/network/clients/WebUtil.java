package cs.hm.edu.network.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author Jannes Kretschmer
 *
 */
public class WebUtil {

	public static UrlBuilder getUrlBuilder(String url) {
		return new WebUtil().new UrlBuilder(url);
	}

	private static String doRequest(URL url) throws IOException {
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		in.close();
		connection.disconnect();

		return content.toString();
	}

	public class UrlBuilder {
		final private StringBuffer sb;
		private boolean hasParameters = false;

		public UrlBuilder(String url) {
			sb = new StringBuffer(url);
		}

		public UrlBuilder appendEscaped(String parameter, String value) {
			try {
				return append(URLEncoder.encode(parameter, "UTF-8"), URLEncoder.encode(parameter, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return append(parameter, value);
		}

		public UrlBuilder append(String parameter, String value) {
			sb.append(hasParameters ? "&" : "?").append(parameter).append("=").append(value);
			hasParameters = true;
			return this;
		}

		public URL build() throws IOException {
			return new URL(sb.toString());
		}

		public String request() throws IOException {
			return doRequest(build());
		}
	}
}
