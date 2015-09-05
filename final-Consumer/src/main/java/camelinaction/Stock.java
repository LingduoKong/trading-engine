package camelinaction;

public class Stock {
	
	String name;

	protected double current_price;
	
	public int bid_quantity;
	public double bid_price;
	public int ask_quantity;
	public double ask_price;
	
	protected int bid_quantity_sum;
	protected int ask_quantity_sum;
	protected double bid_price_multiply_quantity_sum;
	protected double ask_price_multiply_quantity_sum;
	protected double bid_price_square_multiply_quantity_sum;
	protected double ask_price_square_multiply_quantity_sum;

	private TrandingQueue trandingQueue;
	
	public Stock(String name){
		this.name = name;
		trandingQueue = new TrandingQueue(this);
	}
	
	public double calcStats(CalcStrategy strategy){
		return strategy.getCalcStats(this);
	}

	public String  addTicInfo(String info){
		String[] body = info.split("\t");

		bid_price = Double.valueOf(body[1]);
		bid_quantity = Integer.valueOf(body[2]);
		ask_price = Double.valueOf(body[3]);
		ask_quantity = Integer.valueOf(body[4]);

		bid_quantity_sum += bid_quantity;
		ask_quantity_sum += ask_quantity;
		bid_price_multiply_quantity_sum += bid_price * bid_quantity;
		ask_price_multiply_quantity_sum += ask_price * ask_quantity;
		bid_price_square_multiply_quantity_sum += bid_price * bid_price * bid_quantity;
		ask_price_square_multiply_quantity_sum += ask_price * ask_price * ask_quantity;

		return trandingQueue.update();

	}
	
}
