package cs.hm.edu.network.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

/**
 * @author Jannes Kretschmer
 *
 */
public class WebUtil {

	public static UrlBuilder getUrlBuilder(String url) {
		return new WebUtil().new UrlBuilder(url);
	}

	public static void doPutRequest(URL url, Object data) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
		osw.write(new Gson().toJson(data));
		osw.flush();
		osw.close();
		if (connection.getResponseCode() != 200)
			throw new IOException("not successfull");
	}

	private static String doGetRequest(URL url) throws IOException {
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
				return append(URLEncoder.encode(parameter, "UTF-8"), URLEncoder.encode(value, "UTF-8"));
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
			System.out.println(sb);
			return new URL(sb.toString());
		}

		public String request() throws IOException {
			return doGetRequest(build());
		}
	}
}
