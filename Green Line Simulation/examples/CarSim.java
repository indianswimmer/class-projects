package examples;// Priority Queue and Simulation

// examples.CarSim is the driver routine for the examples.Car Wash Simulation
// Uses examples.PQ.java, examples.Washer.java, examples.Stat.java

public class CarSim {

    // public variables

    static PQ agenda = new PQ();
    static Washer washer;  // the car washer

    // methods

    public static void main(String args[]) {

        int distArray[] = {20, 40};
        
        agenda.add(new CarMaker(30, distArray), 10);
//      washer = new examples.Washer();
        
        while (agenda.getCurrentTime() <= 15000)
            agenda.remove().run();

        Stat.displayStats();

    }  // main method
    

}  // examples.CarSim class
