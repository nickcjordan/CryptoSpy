package crypto.spy;

import java.text.DecimalFormat;

import crypto.Util;

public class Currency {

	private String name;
	private CurrencySymbol symbol;
	private double quantity;
	private double currentUnitPriceBTC;
	private double currentUnitPriceUSD;
	private double initialValueBTC;
	private double initialValueUSD;
	private double currentValueBTC;
	private double currentValueUSD;

	public Currency(String name, CurrencySymbol sym) {
		this.name = name;
		this.symbol = sym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CurrencySymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(CurrencySymbol symbol) {
		this.symbol = symbol;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getCurrentUnitPriceBTC() {
		return Util.format(6, currentUnitPriceBTC);
	}

	public void setCurrentUnitPriceBTC(double currentUnitPriceBTC) {
		this.currentUnitPriceBTC = currentUnitPriceBTC;
	}

	public double getCurrentUnitPriceUSD() {
		return Util.format(2, currentUnitPriceUSD);
	}

	public void setCurrentUnitPriceUSD(double currentUnitPriceUSD) {
		this.currentUnitPriceUSD = currentUnitPriceUSD;
	}

	public double getInitialValueBTC() {
		return Util.format(6, initialValueBTC);
	}

	public void setInitialValueBTC(double initialValueBTC) {
		this.initialValueBTC = initialValueBTC;
	}

	public double getInitialValueUSD() {
		return Util.format(2, initialValueUSD);
	}

	public void setInitialValueUSD(double initialValueUSD) {
		this.initialValueUSD = initialValueUSD;
	}

	public double getCurrentValueBTC() {
		return Util.format(6, currentValueBTC);
	}

	public void setCurrentValueBTC(double currentValueBTC) {
		this.currentValueBTC = currentValueBTC;
	}

	public double getCurrentValueUSD() {
		return Util.format(2, currentValueUSD);
	}

	public void setCurrentValueUSD(double currentValueUSD) {
		this.currentValueUSD = currentValueUSD;
	}

	public String getHistory() {
		StringBuilder s = new StringBuilder();
		s.append("\n\n" + getName() + "(" + getSymbol().getSymbol() + ") " + Util.format(6, getQuantity()) + " coins owned at a unit price of $" + Util.format(2, getCurrentUnitPriceUSD()) + ". \n");
		s.append("\t\t  INITIAL     CURRENT\t% CHANGE\n");
		double deltaBTC = (getCurrentValueBTC() - getInitialValueBTC());
		String addSignToNumberBTC = deltaBTC > 0 ? "+" : "";
		double deltaBTCPercent = ((deltaBTC/getInitialValueBTC())*100);
		s.append("\t(BTC) :: " + (getInitialValueBTC()) + " --> " + (getCurrentValueBTC()) + "\t[" + addSignToNumberBTC + Util.format(2, deltaBTCPercent) + "%]\n");
		s.append("\t(USD) :: \t      $" + (getCurrentValueUSD()) + "\n");
		return s.toString();
	}

	@Override
	public String toString() {
		return "Currency [name=" + name + ", symbol=" + symbol.getSymbol() + ", quantity=" + quantity + ", currentUnitPriceBTC="
				+ currentUnitPriceBTC + ", currentUnitPriceUSD=" + currentUnitPriceUSD + ", initialValueBTC="
				+ initialValueBTC + ", currentValueBTC=" + currentValueBTC + ", currentValueUSD=" + currentValueUSD
				+ "]";
	}

}
