package camelinaction;

public class StockStats extends Component {

    public StockStats(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("Can not add component to a stock!");
    }

    @Override
    public void updateStats(String message) {
        String[] body = message.split("\n");
        if( ! getName().equalsIgnoreCase(body[0])){
            return;
        }
        setCurrent_price(Double.valueOf(body[1].split(":")[1]));
        setBid_price_mean(Double.valueOf(body[2]));
        setBid_price_SD(Double.valueOf(body[3]));
        setBid_price_variance(Double.valueOf(body[4]));
        setAsk_price_mean(Double.valueOf(body[5]));
        setAsk_price_SD(Double.valueOf(body[6]));
        setAsk_price_variance(Double.valueOf(body[7]));
    }
}
