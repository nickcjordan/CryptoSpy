package crypto;

public class Log {

	public static void err(String message) {
		try {
			System.out.println(buildLog("[ERROR]\t") + message);
		} catch (Exception e) {
			System.out.println("[ERROR]\t" + message);
		}
	}

	public static void info(String message) {
		try {
			System.out.println(buildLog("[INFO]\t") + message);
		} catch (Exception e) {
			System.out.println("[INFO]\t" + message);
		}
	}
	
	public static void deb(String message) {
		try {
			System.out.println(buildLog("[DEBUG]\t") + message);
		} catch (Exception e) {
			System.out.println("[DEBUG]\t" + message);
		}
	}
	
	private static String buildLog(String level) {
		return level; //+ String.format("%-10s", "[" + "." + "]");
	}

}
