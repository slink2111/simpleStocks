package simpleStocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String buffStockName = ""; // the stock name in buffer
		int buffQuantOfShares = 0; // quantity of shares in buffer
		boolean buffIsBuy = false; // buy or sell indicator in buffer
		int buffPrice = 0; // stock trade price in buffer

		ArrayList<StockTrade> tradesList = new ArrayList<StockTrade>(); // the
																		// arraylist
																		// with
																		// all
																		// the
																		// trades
		ArrayList<Stock> stocksList = new ArrayList<Stock>(); // the arraylist
																// with all the
																// stocks

		// Creates the sample stocks and adds them to the stocksList Arraylist
		createSampleStocks(stocksList);

		
		// The stream and buffer readers for the input
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		System.out.println("Input 1 to make a trade and see the results,");
		System.out.println("GBCE to see the All Share Index or press enter without any input to stop the program");

		try {
			String line = null;

			while (!(line = br.readLine()).trim().equals("")) {

				if (line.equals("GBCE")) {

					// Prints the the GBCE All Share Index
					System.out.println("GBCE All Share Index is: " + geometricMean(stocksList));
				}

				else {
 
					// insert stockName from input
					System.out.println("Insert stock name, be careful it is case sensitive");
					buffStockName = br.readLine();

					// insert price from input
					System.out.println("Insert price");
					buffPrice = Integer.parseInt(br.readLine());

					// insert quantity of shares from input
					System.out.println("Insert quantity of shares");
					buffQuantOfShares = Integer.parseInt(br.readLine());

					// insert if it is buy or sell from input
					System.out.println("Insert b if its buy or s for a sell");
					buffIsBuy = br.readLine().equals("b") ? true : false;

					// creates an Object StockTrade and stores it in the
					// tradeList which list all the trades
					StockTrade tradeItem = new StockTrade(buffStockName, buffQuantOfShares, buffIsBuy, buffPrice);

					// we add the trade to the array of trades
					tradesList.add(tradeItem);

					// stock price for the stock in the buffer, based on the
					// trades of the last 15'
					double stockPrice15 = getStockPrice(buffStockName, tradesList);
					System.out.println("Stock Price based on trades recorded in past 15 minutes is: " + stockPrice15);

					// finds the stock of this trade in the stocksList
					for (Stock stock : stocksList) {

						if (stock.Symbol.equals(buffStockName)) {
							// calls the print function from the tradeItem, to
							// show the Dividend Yield and the P/E ratio
							stock.printStockOutput(tradeItem.price);
							stock.setStockPrice(stockPrice15);
						}
					}

				}

				// print this output after the first iteration
				System.out.println();
				System.out.println("Input 1 to make a trade and see the results,");
				System.out.println("GBCE to see the All Share Index or press enter without any input to stop the program");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < tradesList.size(); i++) {
			System.out.println(tradesList.get(i));
		}

	}

	// returns the stock price, based on the trades of the last 15'
	private static double getStockPrice(String buffStockName, ArrayList<StockTrade> tradesList) {
		double stockPrice15;
		double sumQuanOfShares = 0.0; // calculation of sums of the quantities of a
									// share on the last 15' trades
		double sumOfProduct = 0.0; // calculation of sum of products of the quantity
								// and price on the last 15' trades

		for (StockTrade buffTrade : tradesList) {
			
			//checking of the name and the time limit of 15'
			if (buffTrade.stockName.equals(buffStockName) && (System.currentTimeMillis() - buffTrade.tradeTime <= 900000)) {

				sumQuanOfShares += buffTrade.quantOfShares;
				sumOfProduct += buffTrade.quantOfShares * buffTrade.price;
			}

		}
		stockPrice15 = sumOfProduct / sumQuanOfShares;
		return stockPrice15;

	}

	// It initializes the stocksList Arraylist with sample data
	private static void createSampleStocks(ArrayList<Stock> stocksList) {
		Stock tea = new Stock("TEA", 0, 100);
		Stock pop = new Stock("POP", 8, 100);
		Stock ale = new Stock("ALE", 23, 60);
		Stock gin = new Stock("GIN", 8, 0.02, 100);
		Stock joe = new Stock("JOE", 13, 250);
		stocksList.add(tea);
		stocksList.add(pop);
		stocksList.add(ale);
		stocksList.add(gin);
		stocksList.add(joe);
	}

	private static double geometricMean(ArrayList<Stock> stocksList) {

		double geometricMean = 0.0;
		double product = 1.0;
		int i=0;
		for (Stock stock : stocksList) {
			if (stock.stockPrice15!=0){
			i++;	
			product *= stock.stockPrice15;
			}
		}
		geometricMean = Math.pow(product, 1.0 / (double)i);

		return geometricMean;
	}

}
