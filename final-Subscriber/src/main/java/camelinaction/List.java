package camelinaction;

public class List {

    ListNode head;

    public List() {
        this.head = new ListNode(new Object());
    }

    public ListNodeIterator iterator(){
        return new ListNodeIterator(this);
    }

    public int getSize(){
        int length = 0;
        ListNode temp = head;
        while(temp.next!=null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    public void add(Object obj){
        ListNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = new ListNode(obj);
    }

    public Object getElementAtIndex(int i){

        if (i < 0 || getSize() < i){
            return null;
        }
        ListNode temp = head;
        while (i>0){
            temp = temp.next;
            i--;
        }
        return temp.next.obj;

    }
}
