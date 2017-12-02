package cs.hm.edu.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Niko Paripovic
 *
 */
public class WorkerController {
	List<Worker> worker = new ArrayList<>();
	
	public void add(User user) throws IOException {
		worker.add(new Worker(user)); // Neuer User hinzufügen
	}
	
	public void run() { // KEIN eigener Thread - standardthread (btw)
		
		
		for(Worker w : worker) {
			new Thread(w).start(); // Thread von worker starten
		}
		
		//erwarte Eingabe vom Nutzer...
		try (Scanner scanner = new Scanner(System.in)) {
            while(true) {
            	String s = scanner.nextLine();
            	try {
            		int i = Integer.parseInt(s);
            		if(i >= 0 && i < worker.size()) { // Als Input wird die User (bzw. UserLampID) verwendet.
            			worker.get(i).setConfirmation(true); // schicke Bestätigung an worker-Klasse
            		}
            	}
            	catch (NumberFormatException e) {
            		//do nothing
            	}
            }
		}
	}
}

