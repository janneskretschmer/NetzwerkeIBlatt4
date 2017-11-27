package cs.hm.edu.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkerController {
	
//	List<User> userList = new ArrayList<>();
	List<Worker> worker = new ArrayList<>();
	List<Thread> threads = new ArrayList<>();
	
	public void add(User user) throws IOException {
//		userList.add(user);
		Worker w = new Worker(user);
		worker.add(w);
		threads.add(new Thread(w));
	}
	
	public void run() {
		
		int i2 = 0;
		for(Thread t : threads) {
//			if(i2++ != 0) continue;
			t.start();
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

