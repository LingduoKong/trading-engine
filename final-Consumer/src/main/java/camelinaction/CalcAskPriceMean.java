package camelinaction;

public class CalcAskPriceMean extends CalcStrategy{

    @Override
    public double getCalcStats(Stock stock) {
        return stock.ask_price_multiply_quantity_sum / stock.ask_quantity_sum;
    }
}
