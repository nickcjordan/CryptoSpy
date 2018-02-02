package displayme.app.api;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import crypto.spy.Currency;
import crypto.spy.api.CryptoCompareAPI;

public class APITest {

	@Test
	public void testMe() throws InterruptedException {
		int i = 0;
		while(i++ < 50) {
			CryptoCompareAPI api = new CryptoCompareAPI();
			System.out.println(api.getTransactionHistory());
			System.out.println("Sleeping for 60 seconds...\n\n");
			for (int sec = 0; sec < 60; sec++) {
				TimeUnit.SECONDS.sleep(1);
				if (sec % 5 == 0) {
					System.out.println(sec + "...");
				}
			}
			
		}
		
	}

}
