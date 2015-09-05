package camelinaction;

public class CalcBidPriceSD extends CalcStrategy {

    @Override
    public double getCalcStats(Stock stock) {
        return Math.sqrt( stock.bid_price_square_multiply_quantity_sum / stock.bid_quantity_sum - Math.pow(stock.bid_price_multiply_quantity_sum / stock.bid_quantity_sum,2));
    }
}
