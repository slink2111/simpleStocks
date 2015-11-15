package simpleStocks;

public class StockTrade {

	String stockName = ""; // the stock name
	long tradeTime = 0; // time stamp
	int quantOfShares = 0; // quantity of shares
	boolean isBuy = false; // buy or sell indicator
	double price = 0.0;	//the price for this trade inserted by user

	public StockTrade(String stockName, int quantOfShares, boolean isBuy, double price) {
		super();
		this.stockName = stockName;
		this.tradeTime = System.currentTimeMillis(); // the difference, measured in milliseconds, between the current time and midnight, January 1, 1970 UTC.
		this.quantOfShares = quantOfShares;
		this.isBuy = isBuy;
		this.price = price;

	}

}
