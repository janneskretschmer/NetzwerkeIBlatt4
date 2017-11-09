package cs.hm.edu.network.clients;

import cs.hm.edu.network.IConstants;

/**
 * @author Jannes Kretschmer
 *
 */
public class GoogleClient {

	private WebUtil.UrlBuilder getGoogleUrlBuilder() {
		return WebUtil.getUrlBuilder(IConstants.GOOGLE_API_URL).append("key", IConstants.GOOGLE_API_KEY);
	}
}
