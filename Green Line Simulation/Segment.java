public class Segment {

    // Fields
    private double time;
    private Q1 q;
    private Segment next;

    // Constructor
    public Segment(double t) {
        time = t;
        q = new Q1();
        next = null;
    }

    // Methods
    public double getTime() {
        return time;
    }

    public Q1 getEvents() {
        return q;
    }

    public Segment getNext() {
        return next;
    }

    public void setNext(Segment nextSegment) {
        next = nextSegment;
    }

}
