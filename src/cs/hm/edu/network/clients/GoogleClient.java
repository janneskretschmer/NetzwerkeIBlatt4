package cs.hm.edu.network.clients;

import java.io.IOException;

import com.google.gson.JsonParser;

import cs.hm.edu.network.IConstants;

/**
 * @author Jannes Kretschmer
 *
 */
public class GoogleClient {

	public static int getDuration(String destination, String mode) throws IOException {
		return new JsonParser().parse(WebUtil.getUrlBuilder(IConstants.GOOGLE_API_URL).append("key", IConstants.GOOGLE_API_KEY)
				.appendEscaped("mode", mode).appendEscaped("destinations", destination)
				.appendEscaped("origins", IConstants.START_ADRESS).request()).getAsJsonObject().get("rows").getAsJsonArray().get(0).getAsJsonObject()
				.get("elements").getAsJsonArray().get(0).getAsJsonObject().get("duration").getAsJsonObject().get("value").getAsInt();
	}
}
