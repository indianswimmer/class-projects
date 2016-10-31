public class Q1 implements Q {

    // Fields
    private int size;
    private N front;
    private N rear;

    // Constructor
    public Q1() {}

    // Methods
    public void add(Object o) {

        if (size == 0) {
          front = new N(o, null);
          rear = front;
        }
        else {
          rear.setNext(new N(o, null));
          rear = rear.getNext();
        }
        size++;
    }
    public Object remove() {

        Object answer;

        if (size == 0)
          return null;
        
        answer = front.getData();
        front = front.getNext();
        size--;
        if (size == 0)
          rear = null;
        return answer;
    }
    public int length() {
        return size;
    }
    public void print() {
        N ptr = front;
        while(ptr != null) {
            System.out.println(ptr.getData());
        }
    }
}

