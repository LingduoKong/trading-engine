package camelinaction;


import java.text.DecimalFormat;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StockDataProcessor implements Processor{
	
	private Stock stock;
	
	public StockDataProcessor(Stock stock){
		this.stock = stock;
	}

	@Override
	public void process(Exchange e) throws Exception {
		
    	final DecimalFormat df = new DecimalFormat("###.##");
		// TODO Auto-generated method stub
		String info = e.getIn().getBody(String.class);
		String tradingresult = stock.addTicInfo(info.substring(info.indexOf('['),info.indexOf(']')));

		StringBuilder sb = new StringBuilder();
		sb.append(stock.name + "\n");
		sb.append("Current price:" + stock.current_price + "\n");
		sb.append(df.format(stock.calcStats(new CalcAskPriceMean())) + "\n");
		sb.append(df.format(stock.calcStats(new CalcAskPriceSD())) + "\n");
		sb.append(df.format(stock.calcStats(new CalcAskPriceVariance())) + "\n");
		sb.append(df.format(stock.calcStats(new CalcBidPriceMean())) + "\n");
		sb.append(df.format(stock.calcStats(new CalcBidPriceSD())) + "\n");
		sb.append(df.format(stock.calcStats(new CalcBidPriceVariance())) + "\n");
		sb.append(tradingresult);

		e.getIn().setBody(sb.toString());
		
	}

}
