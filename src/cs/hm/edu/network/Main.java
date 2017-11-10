package cs.hm.edu.network;

import java.io.IOException;

import cs.hm.edu.network.clients.GoogleClient;
import cs.hm.edu.network.clients.HUEClient;

public class Main {
	public static void main(String... args) {
		try {
			// Example:
			System.out.println(
					GoogleClient.getDuration("Weidenstraﬂe 2, 85368 Moosburg", IConstants.GOOGLE_TRAVEL_MODE_DRIVING));
			HUEClient.setOrange(1);
			HUEClient.setWhite(2);
			HUEClient.setRed(3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
