package examples;// Priority Queue and Simulation
// examples.Segment class used in priority queue (examples.PQ.java)
// Uses queue class examples.Q1.java

public class Segment {

    private double time;
    private Q1 q;
    private Segment next;

    // constructor

    public Segment(double t) {
        time = t;
        q = new Q1();
        next = null;
    }

    // methods

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

}  // examples.Segment class
