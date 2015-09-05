package camelinaction;

public class PortofolioStats extends Component {

    List components;

    public PortofolioStats(String name) {
        super(name);
        components = new List();
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void updateStats(String message) {

        double current_price_sum = 0;
        double ask_price_mean_sum = 0;
        double bid_price_mean_sum = 0;
        double ask_price_SD_sum = 0;
        double bid_price_SD_sum = 0;
        double ask_price_variance_sum = 0;
        double bid_price_variance_sum = 0;

        ListNodeIterator it = components.iterator();
        while (it.hasNext()){
            Component c = (Component)it.next();
            c.updateStats(message);
            current_price_sum += c.getCurrent_price();
            ask_price_mean_sum += c.getAsk_price_mean();
            ask_price_SD_sum += c.getAsk_price_SD();
            ask_price_variance_sum += c.getAsk_price_variance();
            bid_price_mean_sum += c.getBid_price_mean();
            bid_price_SD_sum += c.getBid_price_SD();
            bid_price_variance_sum += c.getBid_price_variance();
        }

        setCurrent_price(current_price_sum / components.getSize());
        setAsk_price_mean(ask_price_mean_sum / components.getSize()) ;
        setBid_price_mean(bid_price_mean_sum / components.getSize());
        setAsk_price_SD(ask_price_SD_sum / components.getSize());
        setBid_price_SD(bid_price_SD_sum / components.getSize());
        setAsk_price_variance(ask_price_variance_sum / components.getSize());
        setBid_price_variance(bid_price_variance_sum / components.getSize());
    }

}
