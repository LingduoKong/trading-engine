package camelinaction;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrandingQueue {

    private PriorityQueue<PriceEntry> askQueue, bidQueue;
    private Stock stock;

    public TrandingQueue(Stock stock){
        this.stock = stock;
        askQueue = new PriorityQueue<>(50,new AskQueueSort());
        bidQueue = new PriorityQueue<>(50, new BidQueueSort());
    }

    public String update(){
        askQueue.add(new PriceEntry(stock.ask_price, stock.ask_quantity));
        bidQueue.add(new PriceEntry(stock.bid_price, stock.bid_quantity));
        return trade();
    }

    private String trade(){
    	StringBuilder sb = new StringBuilder();
        while(!askQueue.isEmpty() && !bidQueue.isEmpty() && askQueue.peek().price <= bidQueue.peek().price ){
            PriceEntry b = bidQueue.poll();
            PriceEntry a = askQueue.poll();
            stock.current_price = b.price;
            if(a.quantity > b.quantity){
                a.quantity -= b.quantity;
                sb.append("make a deal at of " + stock.name + " at " + b.price + " for " + b.quantity + "\n");
                askQueue.add(a);
            }
            else if (a.quantity < b.quantity){
                b.quantity -= a.quantity;
                sb.append("make a deal of " + stock.name + " at " + b.price + " for " + a.quantity + "\n");
                bidQueue.add(b);
            }
            else {
            	sb.append("make a deal of " + stock.name + " at " + b.price + " for " + b.quantity + "\n");
                break;
            }
        }
        return sb.toString();
    }

    private class PriceEntry {
        double price;
        int quantity;
        PriceEntry(double price, int quantity){
            this.price = price;
            this.quantity = quantity;
        }
    }

    private static class BidQueueSort implements Comparator<PriceEntry> {

        @Override
        public int compare(PriceEntry priceEntry, PriceEntry t1) {

            if( priceEntry.price > t1.price){
                return -1;
            }
            else if( priceEntry.price < t1.price ){
                return 1;
            }
            else return 0;
        }
    }

    private static class AskQueueSort implements Comparator<PriceEntry> {

        @Override
        public int compare(PriceEntry priceEntry, PriceEntry t1) {
            if( priceEntry.price > t1.price){
                return 1;
            }
            else if( priceEntry.price < t1.price ){
                return -1;
            }
            else return 0;
        }
    }



}
