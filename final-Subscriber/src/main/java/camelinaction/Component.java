package camelinaction;

public abstract class Component {

    private String name;
    private double current_price;
    private double ask_price_mean;
    private double bid_price_mean;
    private double ask_price_SD;
    private double bid_price_SD;
    private double ask_price_variance;
    private double bid_price_variance;

    public Component(String name){
        this.name = name;
    }

    public abstract void add(Component component);

    public abstract void updateStats(String message);

    public String reportStats(){
        StringBuilder sb = new StringBuilder();
        sb.append(getName() + " statics data : ");
        sb.append("current price : " + getCurrent_price() + "\n");
        sb.append("bid price mean : " + getBid_price_mean() + "\n");
        sb.append("bid price standard deviation : " + getBid_price_SD() + "\n");
        sb.append("bid price variance : " + getBid_price_variance() + "\n");
        sb.append("ask price mean : " + getAsk_price_mean() + "\n");
        sb.append("ask price standard deviation : " + getAsk_price_SD() + "\n");
        sb.append("ask price variance : " + getAsk_price_variance() + "\n");
        return sb.toString();
    }

    public String getName(){
        return name;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public double getBid_price_variance() {
        return bid_price_variance;
    }

    public void setBid_price_variance(double bid_price_variance) {
        this.bid_price_variance = bid_price_variance;
    }

    public double getAsk_price_variance() {
        return ask_price_variance;
    }

    public void setAsk_price_variance(double ask_price_variance) {
        this.ask_price_variance = ask_price_variance;
    }

    public double getBid_price_SD() {
        return bid_price_SD;
    }

    public void setBid_price_SD(double bid_price_SD) {
        this.bid_price_SD = bid_price_SD;
    }

    public double getAsk_price_SD() {
        return ask_price_SD;
    }

    public void setAsk_price_SD(double ask_price_SD) {
        this.ask_price_SD = ask_price_SD;
    }

    public double getBid_price_mean() {
        return bid_price_mean;
    }

    public void setBid_price_mean(double bid_price_mean) {
        this.bid_price_mean = bid_price_mean;
    }

    public double getAsk_price_mean() {
        return ask_price_mean;
    }

    public void setAsk_price_mean(double ask_price_mean) {
        this.ask_price_mean = ask_price_mean;
    }
}
