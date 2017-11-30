package cs.hm.edu.network;

public interface IConstants {
	public static final String GOOGLE_API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";
	public static final String GOOGLE_API_KEY = "AIzaSyDJYPydoIhdhO2IzbCWJDWHdpoZe_WHrPE";

	public static final String GOOGLE_TRAVEL_MODE_DRIVING = "driving";
	public static final String GOOGLE_TRAVEL_MODE_WALKING = "walking";
	public static final String GOOGLE_TRAVEL_MODE_BICYCLING = "bicycling";
	public static final String GOOGLE_TRAVEL_MODE_TRANSIT = "transit";

//	public static final String HUE_API_URL_PREFIX = "http://localhost:81/api/newdeveloper/lights/";
	public static final String HUE_API_URL_PREFIX = "http://10.28.209.13:9000/api/197ea42c25303cef1a68c4042ed56887/lights/";
	public static final String HUE_API_URL_SUFFIX = "/state";
	
	public static final String START_ADRESS = "Lothstraße 64, München";

	public static final int LIGHT_COUNT = 3;

	public static final int BLINK_TIMEOUT = 1000;
}
