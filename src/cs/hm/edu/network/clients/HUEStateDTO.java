package cs.hm.edu.network.clients;

/**
 * @author Jannes Kretschmer
 *
 */
public class HUEStateDTO {
	private boolean on;
	private int sat;
	private int bri;
	private int hue;
	
	//if on is set to false some errors occur, so it is set to black instead
	public static final HUEStateDTO STATE_OFF = new HUEStateDTO(true, 0, 0, 0);
	public static final HUEStateDTO STATE_WHITE = new HUEStateDTO(true, 0, 254, 0);
	public static final HUEStateDTO STATE_ORANGE = new HUEStateDTO(true, 254, 254, 5461);
	public static final HUEStateDTO STATE_RED = new HUEStateDTO(true, 254, 254, 0);

	public HUEStateDTO(boolean on, int sat, int bri, int hue) {
		setOn(on);
		setSat(sat);
		setBri(bri);
		setHue(hue);
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = Math.max(0, Math.min(254, sat));
	}

	public int getBri() {
		return bri;
	}

	public void setBri(int bri) {
		this.bri = Math.max(0, Math.min(254, bri));
	}

	public int getHue() {
		return hue;
	}

	public void setHue(int hue) {
		this.hue = Math.max(0, Math.min(65535, hue));
	}

}
