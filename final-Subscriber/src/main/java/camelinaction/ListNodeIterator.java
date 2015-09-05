package camelinaction;

public class ListNodeIterator {

    private List list;
    private int cur;

    public ListNodeIterator(List list) {
        this.list = list;
        cur = 0;
    }

    public Object first(){
        cur = 0;
        return list.getElementAtIndex(0);
    }

    public boolean hasNext(){
        if(cur>= list.getSize()){
            return false;
        }
        return true;
    }

    public Object next(){
        if(hasNext()) {
            return list.getElementAtIndex(cur++);
        }
        else {
            return null;
        }
    }

}
