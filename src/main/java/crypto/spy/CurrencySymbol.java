package crypto.spy;

public enum CurrencySymbol {

	BITCOIN("BTC"),
	RIPPLE("XRP"),
	IOTA("IOTA"),
	ETHEREUM("ETH"),
	LIGHTCOIN("LTC"),
	TRONIX("TRX"),
	ENJIN("ENJ"),
	LEND("LEND"),
	MONETHA("MTH"),
	LOOPRING("LRC"),
	VERGE("XVG"),
	MONERO("XMR"),
	NEO("NEO"),
	CARDANO("ADA"),
	TIME_NEW_BANK("TNB"),
	STATUS("SNT"),
	CINDICATOR("CND"),
	POET("POE"),
	FUN_FAIR("FUN"),
	VECHAIN("VEN"),
	STELLAR("XLM"),
	Tierion("TNT"),
	BASIC_ATTENTION_TOKEN("BAT"),
	CHAIN_LINK("LINK"),
	SONM("SNM"),
	ETHOS("BQX");
	
	private String symbol;

	CurrencySymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return this.symbol;
	}
	
	public CurrencySymbol getCurrencyFromSymbol(String symbol) {
		for (CurrencySymbol coin : CurrencySymbol.values()) {
			if (coin.symbol.equals(symbol)) {
				return coin;
			}
		}
		return null;
	}
}
