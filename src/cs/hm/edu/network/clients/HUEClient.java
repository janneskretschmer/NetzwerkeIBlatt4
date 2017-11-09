package cs.hm.edu.network.clients;

import java.io.IOException;
import java.net.URL;

import cs.hm.edu.network.IConstants;

/**
 * @author Jannes Kretschmer
 *
 */
public class HUEClient {

	public static void setOrange(int id) throws IOException {
		setState(id, HUEStateDTO.STATE_ORANGE);
	}

	public static void setRed(int id) throws IOException {
		setState(id, HUEStateDTO.STATE_RED);
	}

	public static void setWhite(int id) throws IOException {
		setState(id, HUEStateDTO.STATE_WHITE);
	}

	public static void turnOff(int id) throws IOException {
		setState(id, HUEStateDTO.STATE_OFF);
	}

	private static void setState(int id, HUEStateDTO state) throws IOException {
		if (id < 0 || id > IConstants.LIGHT_COUNT)
			throw new IllegalArgumentException();
		WebUtil.doPutRequest(new URL(IConstants.HUE_API_URL_PREFIX + id + IConstants.HUE_API_URL_SUFFIX), state);
	}
}
