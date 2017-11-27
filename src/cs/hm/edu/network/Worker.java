package cs.hm.edu.network;

import java.io.IOException;

import cs.hm.edu.network.clients.GoogleClient;
import cs.hm.edu.network.clients.HUEClient;

public class Worker extends Thread {
	
	private static int red = -1;
	private int status = 0;
	
	private boolean confirmation = false;
	
	public void setConfirmation(boolean c) {
		System.out.println("Erfolgreich gesetzt: Conf auf " + c + " von User " + user.getLampID());
		confirmation = c;
	}
	
	private final User user;
	
	public Worker(User user) throws IOException {
		this.user = user;
		HUEClient.setWhite(user.getLampID());
		System.out.println("Fertig. User eingetragen!");
	}
	
	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static final int WHITE = 0; // Nicht abfahren
	public static final int ORANGE = 1; // 2min vor Abfahrtszeit
	public static final int RED = 2; // 1min vor Abfahrtszeit
	public static final int ALL_RED = 3;
	
	public int getStatus() {
		return status;
	}
	
	private int status() {
		
		try {
			int duration = GoogleClient.getDuration(user.getAddress(), user.getMode());
			System.out.println("Duration: " + duration);
			
			
			int currentTime = (int) (System.currentTimeMillis() / 1000);
			int timeToGo = (int) (user.getTime().getTimeInMillis() / 1000) - duration;
			
			
			
//			Calendar current = Calendar.getInstance();
//			Calendar timeToGo = user.getTime();
//			timeToGo.setLenient(true);
//			timeToGo.add(Calendar.SECOND, -duration);
			
//			
//			String s = ;
//			String s = sdf.format(current);
//	        String s = sdf.format(cal.getTime());
//	        System.out.println(s);
			
			
//			System.out.println(new SimpleDateFormat("d-MM-yyyy HH:mm:ss").format(current.getTime()) + "  UND " + new SimpleDateFormat("d-MM-yyyy HH:mm:ss").format(timeToGo.getTime()));
			
//			int diff = (int) ((current.getTimeInMillis() - timeToGo.getTimeInMillis()) / 1000);
			int diff = timeToGo - currentTime;
			System.out.println(diff);
			
			if(diff <= 120) {
				if(diff <= 60) {
					if(diff <= 0) {
						status = ALL_RED;
						return status;
					}
					status = RED;
					return status;
				}
				status = ORANGE;
				return status;
			}
			status = WHITE;
			return status;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
		
//		Calendar c = Calendar.getInstance();
		
//		Calendar 
//		
//		
//		current.compareTo(next);
//		
//		
//		System.
//		int timeToGo = user.getTime() - duration;
//		
//		if(
//		
//		
//		int timeToSwitchToOrange = timeToGo - 120;
//		int timeToSwitchToRed = timeToGo - 60;
//		
//		return 0;
	}
	
	private final void turnOff() {
		try {
			HUEClient.turnOff(user.getLampID());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private final void setRed() {
		try {
			HUEClient.setRed(user.getLampID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final void setWhite() {
		try {
			HUEClient.setWhite(user.getLampID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final void setOrange() {
		try {
			HUEClient.setOrange(user.getLampID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		boolean isOn = true;
		
		while(true) {
			if(confirmation) { // Es wurde bestätigt
				System.out.println("Habe eine Confirmation erhalten!");
				confirmation = false;
				int s = status();
				if(s != WHITE) {
					user.nextDay();
					if(red == user.getLampID()) {
						red = -1;
					}
				}
				continue;
			}
			if(red != -1) {
				if(isOn) {
					turnOff();
					isOn = false;
					sleep(200);
					continue;
				}
				else {
					setRed();
					isOn = true;
					sleep(200);
					continue;
				}
			}
			switch(status()) {
				case WHITE: {
					setWhite();
					System.out.println("WHITE");
					break;
				}
				case RED: {
					setRed();
					System.out.println("RED");
					break;
				}
				case ALL_RED: {
					setRed();
					red = user.getLampID();
					isOn = true;
					System.out.println("ALL RED");
					break;
				}
				case ORANGE: {
					setOrange();
					System.out.println("ORANGE");
					break;
				}
				default: { }
			}
			sleep(5000);
			
		}
		
		
		
//		Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        String s = sdf.format(cal.getTime());
//        System.out.println(s);
		
		
//		boolean wasRed = false;
//		
//		try {
//				
//			while(true) {
//				if(red == true) {
//					if(isOn) {
//						HUEClient.turnOff(user.getLampID());
//						isOn = false;
//						sleep(200);
//						continue;
//					}
//					HUEClient.setRed(user.getLampID());
//					isOn = true;
//					sleep(200);
//					continue;
//				}
//				
//				
//				
//				int time = IConstants.getTimeStamp(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
//				
//				
//				
//				if(time >= timeToSwitchToOrange) {
//					if(time >= timeToSwitchToRed) {
//						System.out.println("Time: " + time + "TimeToGo: " + timeToGo);
//						if(time >= timeToGo && time < timeToGo + 5 * 60) {
//							HUEClient.setRed(user.getLampID());
//							red = true;
//							isOn = true;
//							wasRed = true;
//							sleep(200);
//							continue;
//						}
//						HUEClient.setRed(user.getLampID());
//						sleep(5000);
//						continue;
//					}
//					HUEClient.setOrange(user.getLampID());
//					sleep(5000);
//					continue;
//				}
//			sleep(5000);
//			}
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
			
			
			
			
//			if(wasRed) {
//				wasRed = false;
//				HUEClient.setOrange(user.getLampID());
//			}
//		}
        
		
		
		
//		if((System.currentTimeMillis() / 1000) + duration > user.getTime()) {
//			System.out.println("DU musst los!!");
//		}
		
//		System.out.println("Run ausgelöst. Adresse: " + user.getAddress() + ", Zeit: " + user.getTime() + ", Mode: " + user.getMode() + ", ID: " + user.getLampID());
		
//		while(true) {
//			
//		}
	}

}
