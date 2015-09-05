package camelinaction;

public class ReportEngine {

    private String name;
    private PortofolioStats root;

    public ReportEngine(String name, PortofolioStats root) {
        this.name = name;
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public PortofolioStats getComponets() {
        return root;
    }

    public void update(String message){
        ListNodeIterator it = root.components.iterator();
        while (it.hasNext()){
            Component c = (Component)it.next();
            c.updateStats(message);
        }
    }

    public String report(){
    	StringBuilder sb = new StringBuilder();
        ListNodeIterator it = root.components.iterator();
        while (it.hasNext()){
            Component c = (Component)it.next();
            sb.append(c.reportStats() + "\n");
        }
        return sb.toString();
    }
}
