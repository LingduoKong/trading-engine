package camelinaction;

public class CalcBidPriceMean extends CalcStrategy{

    @Override
    public double getCalcStats(Stock stock) {
        return stock.bid_price_multiply_quantity_sum / stock.bid_quantity_sum;
    }
}
