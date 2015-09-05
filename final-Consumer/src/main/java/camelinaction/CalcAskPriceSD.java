package camelinaction;

public class CalcAskPriceSD extends CalcStrategy{

    @Override
    public double getCalcStats(Stock stock) {
        return Math.sqrt( stock.ask_price_square_multiply_quantity_sum / stock.ask_quantity_sum - Math.pow(stock.ask_price_multiply_quantity_sum / stock.ask_quantity_sum, 2));
    }
}
