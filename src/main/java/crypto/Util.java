package crypto;

import java.text.DecimalFormat;

public class Util {

	public static Double format(int i, double val) {
		DecimalFormat twoPlaces = new DecimalFormat("#.##");
		DecimalFormat sixPlaces = new DecimalFormat("#.######");
		return i == 2 ? Double.valueOf(twoPlaces.format(val)) : Double.valueOf(sixPlaces.format(val));
	}
}
