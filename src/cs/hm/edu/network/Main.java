package cs.hm.edu.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs.hm.edu.network.clients.GoogleClient;

public class Main {
	public static final String USAGE = "<Nutzer1_Zieladresse>|<Nutzer1_Arbeitsbeginn>|<Nutzer1_Verkehrsmittel>|"
			+ "<Nutzer2_Zieladresse>|<Nutzer2_Arbeitsbeginn>|<Nutzer2_Verkehrsmittel>|" + "...|"
			+ "<NutzerX_Zieladresse>|<NutzerX_Arbeitsbeginn>|<NutzerX_Verkehrsmittel>"
			+ "\nX ist Element der Natürlichen Zahlen" + "\n" + "\nHinweise:"
			+ "\nNutzerX_Zieladresse = Straße Hausnummer, PLZ, Ort" + "\nNutzerX_Arbeitsbeginn = hh:mm"
			+ "\nNutzerX_Verkehrsmittel = driving / walking / bicycling / transit";
	public static final String CONNECTION_PROBLEM = "Bei der Verbindung zum Internet ist ein Fehler aufgetreten :(";

	public static final int NUMBER_OF_USERS = 3;
	public static final int NUMBER_OF_PARAMETERS = 3;

	static List<User> list = new ArrayList<>();

	public static void main(String... args) {

		if (args.length <= 0) {
			System.out.println(USAGE);
			return;
		}

		String param = "";
		for (String s : args) {
			param += s + " ";
		}

		String[] s = param.substring(0, param.length() - 1).split("\\|");

		if (s.length != (NUMBER_OF_PARAMETERS * NUMBER_OF_USERS)) { // 7 arguments needed: COlour, address (4x), work
																	// begin, transport
			System.out.println(USAGE);
			return;
		}

		for (int i = 0; i < NUMBER_OF_USERS * 3; i += 3) {
			try {
				if (!GoogleClient.checkAdress(s[i + 0])) {
					System.out.println(
							"Die Zieladresse muss existieren und ein gültiges Format, z.B. 'Straße Hausnummer, PLZ, Ort', haben.");
					return;
				}
			} catch (IOException e) {
				System.out.println(CONNECTION_PROBLEM);
				return;
			}
			if (!s[i + 1].matches("(\\d\\d|\\d):\\d\\d")) {
				System.out.println("Der Arbeitsbeginn muss das Format 'hh:mm' haben.");
				return;
			}
			String[] tmp = s[i + 1].split(":");
			int hh = Integer.parseInt(tmp[0]);
			int mm = Integer.parseInt(tmp[1]);
			if (hh < 0 || hh > 23) {
				System.out.println("Die Stunde des Arbeitsbeginns muss zwischen 0 und 23 (inklusive) liegen.");
				return;
			}
			if (mm < 0 || mm > 59) {
				System.out.println("Die Minute des Arbeitsbeginns muss zwischen 0 und 59 (inklusive) liegen.");
				return;
			}
			if (!s[i + 2].matches(IConstants.GOOGLE_TRAVEL_MODE_WALKING + "|" + IConstants.GOOGLE_TRAVEL_MODE_DRIVING
					+ "|" + IConstants.GOOGLE_TRAVEL_MODE_TRANSIT + "|" + IConstants.GOOGLE_TRAVEL_MODE_BICYCLING)) {
				System.out.println("Das Verkehrsmittel muss '" + IConstants.GOOGLE_TRAVEL_MODE_WALKING + "','"
						+ IConstants.GOOGLE_TRAVEL_MODE_DRIVING + "','" + IConstants.GOOGLE_TRAVEL_MODE_TRANSIT
						+ "' oder '" + IConstants.GOOGLE_TRAVEL_MODE_BICYCLING + "' sein.");
				return;
			}

		}

		WorkerController wc = new WorkerController();

		for (int i = 0; i < NUMBER_OF_USERS * 3; i += 3) {
			try {
				wc.add(new User(s[i + 0], s[i + 1], s[i + 2]));
			} catch (IOException e) {
				System.out.println(CONNECTION_PROBLEM);
				System.exit(0);
			} // Füge einen neuen User hinzu
		}
		wc.run(); // Kein Thread-Aufruf!
	}
}
