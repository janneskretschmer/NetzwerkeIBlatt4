package cs.hm.edu.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	/*Input:<Nutzer1_Zieladresse>|<Nutzer1_Arbeitsbeginn>|<Nutzer1_Verkehrsmittel>|
			<Nutzer2_Zieladresse>|<Nutzer2_Arbeitsbeginn>|<Nutzer2_Verkehrsmittel>|
			...
			<NutzerX_Zieladresse>|<NutzerX_Arbeitsbeginn>|<NutzerX_Verkehrsmittel>
			X ist Element der Natürlichen Zahlen
			
			Hinweise:
			NutzerX_Zieladresse = Straße Hausnummer, PLZ, Ort
			NutzerX_Arbeitsbeginn = hh:mm
			NutzerX_Verkehrsmittel = driving / walking / bicycling / transit
			
	*/
	
	public static final int NUMBER_OF_USERS = 3;
	public static final int NUMBER_OF_PARAMETERS = 3;
	
	static List<User> list = new ArrayList<>();
	
	
	public static void main(String... args) throws IOException {
		
		String param = "";
		for(String s : args) {
			param += s + " ";
		}
		
		String[] s = param.substring(0, param.length() - 1).split("\\|");
		
		if(s.length != (NUMBER_OF_PARAMETERS * NUMBER_OF_USERS)) { // 7 arguments needed: COlour, address (4x), work begin, transport
			System.out.println("Wrong configuration");
			return;
		}
		
		WorkerController wc = new WorkerController();
		
		for(int i = 0; i < NUMBER_OF_USERS * 3; i += 3) { 
			wc.add(new User(s[i + 0], s[i + 1], s[i + 2])); // Füge einen neuen User hinzu
		}		
		wc.run(); // Kein Thread-Aufruf!
	}
}
