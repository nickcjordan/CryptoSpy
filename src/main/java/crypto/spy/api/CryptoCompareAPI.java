package crypto.spy.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.jaunt.JNode;
import com.jaunt.NotFound;
import com.jaunt.UserAgent;

import crypto.Log;
import crypto.Util;
import crypto.spy.Currency;
import crypto.spy.CurrencySymbol;

public class CryptoCompareAPI extends APIConnection {

	private static final String BASE_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=";
	private static final String USD_URL_END = "&tsyms=USD,BTC";
	private static final String BITCOIN_TO_USD_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC&tsyms=USD";
	private static final String ETHEREUM_TO_BITCOIN_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=ETH&tsyms=BTC";
	
	private static final String TRADE_HISTORY = "src/main/resources/data/CurrentTradeHistory.csv";
	
	private static HashMap<String, Currency> coins;
	
	private static double previousBitcoinPrice = 0;
	public static double previousProfitPrice = 0;
	
	private static double bitcoinPriceUSD;
	private static double ethereumPriceBTC;
	private static Double[] totalSpent = {51.0, 94.03, 39.49, 86.44, 150.49, 150.17, 124.53, 150.32, 194.15};
	
	
	public CryptoCompareAPI() {
		bitcoinPriceUSD = setBitcoinPrice();
		ethereumPriceBTC = setEthereumPrice();
		coins = initCoinList();
		updateCoinListWithInitialValues();
		publishValues();
	}
	
	private void publishValues() {
		System.out.println(getTransactionHistory());
	}

	public HashMap<String, Currency> getCoins() {
		return coins;
	}
	
	private double setEthereumPrice() {
		try { return new UserAgent().openJSON(GET(ETHEREUM_TO_BITCOIN_URL)).get(CurrencySymbol.ETHEREUM.getSymbol()).get(CurrencySymbol.BITCOIN.getSymbol()).toDouble(); } 
		catch (NotFound e) { e.printStackTrace(); return 0; }
	}

	private double setBitcoinPrice() {
		try { return new UserAgent().openJSON(GET(BITCOIN_TO_USD_URL)).get(CurrencySymbol.BITCOIN.getSymbol()).get("USD").toDouble(); } 
		catch (NotFound e) { e.printStackTrace(); return 0; }
	}

	private HashMap<String, Currency> initCoinList() {
		HashMap<String, Currency> currencies = new HashMap<String, Currency>();
		String responseData = GET(buildUrl(CurrencySymbol.values()));
		JNode response = new UserAgent().openJSON(responseData);
		
		for (CurrencySymbol sym : CurrencySymbol.values()) {
			try {
				Currency coin = new Currency(sym.name(), sym);
				coin.setCurrentUnitPriceUSD(response.get(sym.getSymbol()).get("USD").toDouble());
				coin.setCurrentUnitPriceBTC(response.get(sym.getSymbol()).get("BTC").toDouble());
				currencies.put(coin.getSymbol().getSymbol(), coin);
			} catch (NotFound e) { Log.err("API did not find " + sym.getSymbol()); }
		}
			
		return currencies;
	}

	private String buildUrl(CurrencySymbol[] currencies) {
		StringBuilder sb = new StringBuilder(BASE_URL);
		for (CurrencySymbol coin : currencies) {
			sb.append(coin.getSymbol() + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(USD_URL_END);
		return sb.toString();
	}
	
	private void updateCoinListWithInitialValues() {
		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(TRADE_HISTORY));
			reader.readLine(); // move past headers
			while ((line = reader.readLine()) != null) {
				updateCoinWithInitialValues(line.split(","));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	private void updateCoinWithInitialValues(String[] split) {
		try {
			Currency coin = coins.get(split[1].substring(0, split[1].indexOf("/")));
			
			double cQuantity = Double.parseDouble(split[4]) - Double.parseDouble(split[6]); // amount - fee
			double cInitBTC = Double.parseDouble(split[5]);
			String cSym = split[1].substring(split[1].indexOf("/")+1, 7);
			double cCurrentValBTC = cQuantity * coin.getCurrentUnitPriceBTC();
			double cCurrentValUSD = cQuantity * coin.getCurrentUnitPriceUSD();
			
			coin.setQuantity(coin.getQuantity() + cQuantity); 
			if (cSym.equals(CurrencySymbol.BITCOIN.getSymbol())) { // exchange in terms of BTC
				coin.setInitialValueBTC(coin.getInitialValueBTC() + cInitBTC); // total
			} else { // exchange in terms of ETH
				coin.setInitialValueBTC(coin.getInitialValueBTC() + (cInitBTC * ethereumPriceBTC)); // total
			}
			
			coin.setCurrentValueBTC(coin.getCurrentValueBTC() + cCurrentValBTC);
			coin.setCurrentValueUSD(coin.getCurrentValueUSD() + cCurrentValUSD);
			
			double cInitValUSD = coin.getInitialValueBTC() * bitcoinPriceUSD;
			coin.setInitialValueUSD(cInitValUSD);
		} catch (Exception e) {
			Log.err("Could not find " + split[7] + " in table");
		}
	}

	public String getTransactionHistory() {
		double sum = 0;
		StringBuilder s = new StringBuilder("\nCurrent price of 1 Bitcoin (BTC) = $" + Util.format(2, bitcoinPriceUSD) + " [$" + Util.format(2, (bitcoinPriceUSD - previousBitcoinPrice)) + "]\n\n");
		for (Currency coin : getCoins().values()) {
			if (coin.getQuantity() > 0) {
				s.append(coin.getHistory());
				sum += coin.getCurrentValueUSD();
			}
		}
		double profitPrice = sum - getTotalSpent();
		s.append("\nTotal value of all coins = $" + Util.format(2, getTotalSpent()) + " --> $" + Util.format(2, sum) + " ==> difference = $" + Util.format(2, profitPrice) + " [" + Util.format(2, (profitPrice - previousProfitPrice)) + "%]\n\n");
		previousBitcoinPrice = bitcoinPriceUSD;
		previousProfitPrice = profitPrice;
		return s.toString();
	}

	private double getTotalSpent() {
		double sum = 0;
		for (Double num : totalSpent) {
			sum+=num;
		}
		return sum;
	}
	
	
	
}
