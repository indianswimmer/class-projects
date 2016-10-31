/**
 * Created by halvo431 on 4/21/16.
 */
public class Passenger {
    // Fields
    double startTime;
    Stop destination;
    String direction; // east or west

    // Constructor
    public Passenger(String direction, Stop destination) {
        this.direction = direction;
        this.destination = destination;
        this.startTime = TrainSim.agenda.getCurrentTime();
    }

    // Methods
    public String getDirection() {
        return direction;
    }
}
