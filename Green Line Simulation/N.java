public class N {
    // Fields
    private Object data;
    private N next;

    // Constructors
    public N() {}

    public N(Object o, N link) {
        this.data = o;
        this.next = link;
    }

    // Methods
    public Object getData() {
        return data;
    }

    public void setData(Object o) {
        data = o;
    }

    public N getNext() {
        return next;
    }

    public void setNext(N link) {
        next = link;
    }
}
