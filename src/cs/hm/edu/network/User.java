package cs.hm.edu.network;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User {
	
	private static int staticLampID = 1;
	
	private final int lampID = staticLampID++;
	private final String address;
	private Calendar time;
//	private boolean waitForInput = false;
	
	private final String mode;
	
//	public final boolean getWaitForInput() {
//		return waitForInput;
//	}
	
//	public void setTime(int hour, int minute, int second, int millisecond) {
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.HOUR, Integer.parseInt(time.split(":")[0]));
//		c.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]);
//		c.set(Calendar.SECOND, 0);
//		c.set(Calendar.MILLISECOND, 0);
//		time = c;
//	}
	
	public void nextDay() {
		time.add(Calendar.DAY_OF_YEAR, 1);
	}
	
	public User(String address, String time, String mode) {		
		this.address = address;
//		System.out.println(time);
//		System.out.println(time.split(":")[0]);
//		System.out.println(time.split(":")[1]);
		
		
		
//		this.time = IConstants.getTimeStamp(time + ":00");
		Calendar c = Calendar.getInstance();
//		System.out.println(new SimpleDateFormat("d-MM-yyyy H:mm:ss").format(c.getTime()));
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
//		System.out.println(new SimpleDateFormat("d-MM-yyyy H:mm:ss").format(c.getTime()));
		
//		int tmp = Integer.parseInt(time.split(":")[0]);
//		tmp = tmp < 12 ? tmp + 12 : tmp - 12;
		
//		System.out.println();
//		c.set(Calendar.HOUR, tmp);
//		System.out.println(c.get(Calendar.HOUR));
		
//		tmp = Integer.parseInt(time.split(":")[1]);
//		tmp = tmp < 12 ? tmp + 12 : tmp - 12;
		
		c.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
//		this.time = c;
		
//		System.out.println(new SimpleDateFormat("d-MM-yyyy HH:mm:ss").format(c.getTime()));
		
		if(c.before(Calendar.getInstance())) {
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		System.out.println(new SimpleDateFormat("d-MM-yyyy HH:mm:ss").format(c.getTime()));
		
		this.time = c;
		
		
		
		
		//Integer.parseInt(time.split(":")[0]) * 60 * 60 + Integer.parseInt(time.split(":")[1]) * 60 + Integer.parseInt(time.split(":")[2]);
		
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
