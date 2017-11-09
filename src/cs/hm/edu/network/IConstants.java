package cs.hm.edu.network;

public interface IConstants {
	public static final String GOOGLE_API_URL="https://maps.googleapis.com/maps/api/distancematrix/json";
	public static final String GOOGLE_API_KEY="AIzaSyDJYPydoIhdhO2IzbCWJDWHdpoZe_WHrPE";
	
	public static final String HUE_API_URL_PREFIX="http://localhost/api/newdeveloper/lights/";
	public static final String HUE_API_URL_SUFFIX="/state";
	
	public static final int LIGHT_COUNT = 3;
	
	public static final int BLINK_TIMEOUT = 100;
}
