package examples;// Priority Queue and Simulation

// examples.Car class the represents cars in the carwash simulation
// examples.Car objects contain the time they were created and their service time.
// examples.Car objects are immediately queued in the washer queue.
// examples.Car objects are passive except in constructor (creation)

public class Car {

    // constructor

    public Car(double t, double servTime) {
        arrivalTime = t;
        serviceTime = servTime;
        Washer.enter(this);
        if (!Washer.isBusy())
          new Washer().run();  // wake-up washer if idle
    }

    public double getArrivalTime() {
        return arrivalTime;
    }
  
    public double getServiceTime() {
        return serviceTime;
    }

    // private variables
         
    private double arrivalTime;
    private double serviceTime;

}  // examples.Car class
