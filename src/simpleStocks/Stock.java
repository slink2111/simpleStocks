package simpleStocks;

public class Stock {

	String Symbol = "";	//stock name 
	boolean isPreferred = false; //if the stock is preferred it is true
	int lastDividend = 0;	//the last dividend
	double fixedDividend = 0.0;	//the fixed divident
	int parValue = 0;	//the par value
	double stockPrice15 = 0.0; //the last Stock Price calculated based on trades recorded in past 15 minutes. 


	// constructor for the Preferred stock
	public Stock(String symbol, int lastDividend, double fixedDividend, int parValue) {
		super();
		Symbol = symbol;
		this.isPreferred = true;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	// constructor for the Common stock
	public Stock(String symbol, int lastDividend, int parValue) {
		super();
		Symbol = symbol;
		this.isPreferred = false;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
	}

	
	//prints to the output the dividend and the P/E ratio
	void printStockOutput(double tickerPrice) {
		double dividend = 0.0;
	
		if (isPreferred) {
			//Dividend for Preferred
			dividend =  fixedDividend * parValue / tickerPrice;
		} else {
			//Dividend for Common
			dividend = lastDividend / tickerPrice;
		}
		
		if (dividend!=0.0){
		System.out.println("The Dividend Yield is: " + dividend);
		System.out.println("The P/E ratio is: " + tickerPrice/dividend);
		
		}
		else{
			System.out.println("The Dividend Yield is 0.0");
		}
		
	}
		

	
	//getter of stockPrice (the last Stock Price calculated based on trades recorded in past 15 minutes.) 
	double getStockPrice() {
		return stockPrice15;
	}

	//setter of stockPrice (the last Stock Price calculated based on trades recorded in past 15 minutes.)
	void setStockPrice(double stockPrice) {
		this.stockPrice15 = stockPrice;
	}


}
