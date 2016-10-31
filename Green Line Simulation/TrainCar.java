import java.util.ArrayList;

/**
 * Created by halvo431 on 4/21/16.
 */
public class TrainCar {
    // Fields
    ArrayList<Passenger> passengerList = new ArrayList<>();

    // Constructor
    public TrainCar() {}

    // Methods
    public boolean add(Passenger p) {
        if(passengerList.size() < 50) {
            passengerList.add(p);
            return true;
        } else {
            if(Stats.showEvents) {
                System.out.println("Train Car is full, cannot add passenger.");
            }
            return false;
        }
    }
}
