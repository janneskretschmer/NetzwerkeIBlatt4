package cs.hm.edu.network;

import java.io.IOException;

import cs.hm.edu.network.clients.GoogleClient;
import cs.hm.edu.network.clients.HUEClient;

public class Worker extends Thread {
	
	private static int red = -1;
	
	private boolean confirmation = false;
	private final User user;
	
	public Worker(User user) throws IOException {
		this.user = user;
		HUEClient.setWhite(user.getLampID());
		System.out.println("Fertig. User eingetragen!");
	}
	
	public void setConfirmation(boolean c) { // Methode um die Best�tigung zu erhalten.
		confirmation = c;
	}	
	
	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static final int WHITE = 0; // Noch kein Stress... �ber 2 Minuten noch Zeit.
	public static final int ORANGE = 1; // 2min vor Abfahrtszeit
	public static final int RED = 2; // 1min vor Abfahrtszeit
	public static final int ALL_RED = 3; // H�chste Eisenbahn! Die Mitbewohner m�ssen den Penner wecken!
	
	private int status() {
		
		try {
			int duration = GoogleClient.getDuration(user.getAddress(), user.getMode()); // Vom Googleserver
			System.out.println("Duration (" + user.getLampID() + "): " + duration);
			
			
			int currentTime = (int) (System.currentTimeMillis() / 1000); // currentTime in seconds - not milliseconds
			int timeToGo = (int) (user.getTime().getTimeInMillis() / 1000) - duration; // Der Zeitpunkt an dem man losgehen muss.
			
			int diff = timeToGo - currentTime; // Wieviel Zeit bleibt noch bis man gehen muss?
			System.out.println("Differenz (" + user.getLampID() + "): " + diff);
			
			if(diff <= 120) { // Lampe darf nicht wei� sein!
				if(diff <= 60) { // Lampe darf nicht wei� und nicht orange sein
					if(diff <= 0) { // Warnung: Mitbewohner muss schon l�ngst gegangen sein!
						return ALL_RED;
					}
					return RED;
				}
				return ORANGE;
			}
			return WHITE;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
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
			e.printStackTrace();
		}
	}
	
	private final void setWhite() {
		try {
			HUEClient.setWhite(user.getLampID());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private final void setOrange() {
		try {
			HUEClient.setOrange(user.getLampID());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean isOn = true;		
		while(true) {
			if(confirmation) { // Es wurde best�tigt
				confirmation = false;
				int s = status();
				if(s != WHITE) { // Best�tigung nur sinnvoll, wenn Lampe nicht wei� ist
					user.nextDay();
					if(red == user.getLampID()) { // Wenn diese Lampe der Ausl�ser f�r das Blinken ist:
						red = -1; // Blinken zur�cksetzen
					}
				}
				continue; // N�chster Schleifendurchgang...
			}
			if(red != -1) { // Wenn alle Lampen blinken sollen...
				if(isOn) {
					turnOff(); // Ausschalten
					isOn = false;
					sleep(IConstants.BLINK_TIMEOUT);
					continue; // N�chster Schleifendurchgang...
				}
				else {
					setRed(); // einschalten
					isOn = true;
					sleep(IConstants.BLINK_TIMEOUT);
					continue; // N�chster Schleifendurchgang...
				}
			}
			switch(status()) {
				case WHITE: {
					setWhite();
//					System.out.println("WHITE");
					break;
				}
				case RED: {
					setRed();
//					System.out.println("RED");
					break;
				}
				case ALL_RED: {
					setRed();
					red = user.getLampID(); // Damit der Ausl�ser f�r ALL_RED identifizierbar ist.
					isOn = true;
//					System.out.println("ALL RED");
					continue;
				}
				case ORANGE: {
					setOrange();
//					System.out.println("ORANGE");
					break;
				}
				default: { }
			}
			sleep(5000); // nur alle 5 sekunden checken!		
		}
	}
}
