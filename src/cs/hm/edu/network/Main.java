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
		
//		System.out.println(param.substring(0, param.length() - 1));
		
		String[] s = param.substring(0, param.length() - 1).split("\\|");
		
//		for(String a : s) {
//			System.out.println(a);
//		}
		
		if(s.length != (NUMBER_OF_PARAMETERS * NUMBER_OF_USERS)) { // 7 arguments needed: COlour, address (4x), work begin, transport
			System.out.println("Wrong configuration");
			return;
		}
		
		WorkerController wc = new WorkerController();
		
		for(int i = 0; i < NUMBER_OF_USERS * 3; i += 3) {
//			list.add(new User(String.format("%s %s, %s %s", args[i + 0], args[i + 1], args[i + 2], args[i + 3]), args[i + 4], args[i + 5]));
			wc.add(new User(s[i + 0], s[i + 1], s[i + 2]));
//			System.out.println(s[i + 0] +  s[i + 1] +  s[i + 2]);
		}
		
		wc.run();
//		
//		
//		
//		
//		
//		try {
//			HUEClient.setOrange(1);
//			HUEClient.turnOff(1);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			// Example:
//			System.out.println(
//					GoogleClient.getDuration("Weidenstraße 2, 85368 Moosburg", IConstants.GOOGLE_TRAVEL_MODE_DRIVING));
//			HUEClient.setOrange(1);
//			HUEClient.setWhite(2);
//			HUEClient.setRed(3);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
