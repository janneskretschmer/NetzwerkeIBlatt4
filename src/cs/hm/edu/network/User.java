package cs.hm.edu.network;

import java.util.Calendar;

public class User {
	
	private static int staticLampID = 1;
	
	private final int lampID = staticLampID++;
	private final String address;
	private Calendar time;
	
	private final String mode;
	
	public void nextDay() {	// setzt nach Bestätigung das Kalenderobjekt auf den nächsten Wochentag	
		switch(time.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.FRIDAY: {
				time.add(Calendar.DAY_OF_YEAR, 3);
			}
			case Calendar.SATURDAY: {
				time.add(Calendar.DAY_OF_YEAR, 2);
				break;
			}
			default: {
				time.add(Calendar.DAY_OF_YEAR, 1);
			}	
		}
	}
	
	// custom-Konstruktor
	public User(String address, String time, String mode) {		
		this.address = address;
		this.time = Calendar.getInstance();
		this.time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
		this.time.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
		this.time.set(Calendar.SECOND, 0);
		this.time.set(Calendar.MILLISECOND, 0);
		if(this.time.before(Calendar.getInstance())) {
			nextDay();
		}		
		
		if(mode.equals(IConstants.GOOGLE_TRAVEL_MODE_DRIVING)) {
			this.mode = IConstants.GOOGLE_TRAVEL_MODE_DRIVING;
		}
		else if(mode.equals(IConstants.GOOGLE_TRAVEL_MODE_BICYCLING)) {
			this.mode = IConstants.GOOGLE_TRAVEL_MODE_BICYCLING;
		}
		else if(mode.equals(IConstants.GOOGLE_TRAVEL_MODE_TRANSIT)) {
			this.mode = IConstants.GOOGLE_TRAVEL_MODE_TRANSIT;
		}
		else if(mode.equals(IConstants.GOOGLE_TRAVEL_MODE_WALKING)) {
			this.mode = IConstants.GOOGLE_TRAVEL_MODE_WALKING;
		}
		else {
			this.mode = "Failed";
		}
	}
	
	
	
	/*
	 * Getter
	 */
	
	public final int getLampID() {
		return lampID;
	}
	
	public final String getAddress() {
		return address;
	}
	
	public final Calendar getTime() {
		return time;
	}
	
	public final String getMode() {
		return mode;
	}

}
