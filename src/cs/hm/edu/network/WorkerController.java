package cs.hm.edu.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkerController {
	List<Worker> worker = new ArrayList<>();
	
	public void add(User user) throws IOException {
		worker.add(new Worker(user)); // Neuer User hinzufügen
	}
	
	public void run() { // KEIN eigener Thread - standardthread (btw)
		
		for(Worker w : worker) {
			new Thread(w).start(); // Thread von worker starten
		}
		
		try (Scanner scanner = new Scanner(System.in)) {
            while(true) {
            	String s = scanner.nextLine();
            	try {
            		int i = Integer.parseInt(s);
            		if(i >= 0 && i < worker.size()) {
            			System.out.println("Try to set " + i + " auf true");
            			worker.get(i).setConfirmation(true);
            		}
            	}
            	catch (NumberFormatException e) {
            		//do nothing
            	}
            }
		}
	}
}
	
//	public void go() {
//		
//		threads.get(0).start();
//            while((c = scanner.nextLine().charAt(0))!= 'q') {
//                System.out.println(c);
//            }
//        }
//		
//		
////		for(Thread t : threads) {
////			t.start();
////		}
//	}

