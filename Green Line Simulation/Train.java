import java.util.ArrayList;

/**
 * Created by halvo431 on 4/21/16.
 */

public class Train { // Maximum number of trains is 1 per stop (23 stops total)
    // Fields
    ArrayList<TrainCar> trainCars = new ArrayList<>(); // Can have up to three cars
    String direction; // east or west
    Stop currentStop;

    // Constructor
    public Train(Stop initialStop, String direction, int numCars) {
        this.currentStop = initialStop;
        this.direction = direction;
        for(int i = 0; i < numCars; i++) { // Initialize number of train cars
            trainCars.add(new TrainCar());
        }
    }

    // Methods
    public Stop getNextStop() { // Unlike findNextStop, this changes the direction of the train at the boundaries
        int newID;
        if(this.direction.equals("east")) {
            newID = this.currentStop.getID() + 1;
        } else { // going west
            newID = this.currentStop.getID() - 1;
        }
        if(newID == 0) {
            newID = 1;
            this.direction = "east";
        } else if(newID == 24) {
            newID = 23;
            this.direction = "west";
        }
        return TrainSim.stopList.get(newID);
    }
    public Stop findNextStop() { // Doesn't change direction train is going, relevant in TrainEvent.run()
        int newID;
        if(this.direction.equals("east")) {
            newID = this.currentStop.getID() + 1;
        } else { // going west
            newID = this.currentStop.getID() - 1;
        }
        if(newID == 0) {
            newID = 1;
        } else if(newID == 24) {
            newID = 23;
        }
        return TrainSim.stopList.get(newID);
    }
    public Stop getCurrentStop() {
        return currentStop;
    }
    public boolean isFull() {
        for(int i = 0; i < trainCars.size(); i++) {
            if(trainCars.get(i).passengerList.size() < 50) {
                return false;
            }
        }
        return true;
    }
    public boolean add(Passenger p) { // Returns true if passenger added to one of the TrainCar(s), false otherwise
        for(int i = 0; i < trainCars.size(); i++) {
            if(trainCars.get(i).add(p)) {
                Stats.totalPassengersInTransit++;
                return true;
            }
        }
        return false;
    }
}
