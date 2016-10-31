import java.util.ArrayList;

public class PassengerMaker implements Event {
    // Fields
    Stop stop;
    static double interArrivalTime = TrainSim.interArrivalTime;

    // Constructor
    public PassengerMaker(Stop stop) {
        this.stop = stop;
    }

    // Methods
    public static double randomInterval(Stop stop) { // [Working] Could be non-static but is static for testing purposes
        double time;
        double temp = interArrivalTime;

        if (stop.type.equals("downtown")) {
            temp -= 10;
        } else if (stop.type.equals("campus")) {
            temp -= 5;
        }

        int randInt = (int) (Math.random() * 100 + 1);
        if (randInt <= 10)
            time = 1.75 * temp;
        else if (randInt <= 25)
            time = 1.50 * temp;
        else if (randInt <= 45)
            time = 1.20 * temp;
        else if (randInt <= 55)
            time = 1.00 * temp;
        else if (randInt <= 75)
            time = 0.80 * temp;
        else if (randInt <= 90)
            time = 0.50 * temp;
        else
            time = 0.25 * temp;

        return time;
    }
    public static Stop getDestination(Stop stop) { // [Working] Random destination generation
        int randInt = (int) (Math.random() * 69 + 1);
        Stop destination;
        if (randInt <= 5)
            destination = TrainSim.stopList.get(1);
        else if (randInt <= 10)
            destination = TrainSim.stopList.get(2);
        else if (randInt <= 15)
            destination = TrainSim.stopList.get(3);
        else if (randInt <= 20)
            destination = TrainSim.stopList.get(4);
        else if (randInt <= 25)
            destination = TrainSim.stopList.get(5);
        else if (randInt <= 28)
            destination = TrainSim.stopList.get(6);
        else if (randInt <= 31)
            destination = TrainSim.stopList.get(7);
        else if (randInt <= 34)
            destination = TrainSim.stopList.get(8);
        else if (randInt <= 35)
            destination = TrainSim.stopList.get(9);
        else if (randInt <= 36)
            destination = TrainSim.stopList.get(10);
        else if (randInt <= 37)
            destination = TrainSim.stopList.get(11);
        else if (randInt <= 38)
            destination = TrainSim.stopList.get(12);
        else if (randInt <= 39)
            destination = TrainSim.stopList.get(13);
        else if (randInt <= 40)
            destination = TrainSim.stopList.get(14);
        else if (randInt <= 41)
            destination = TrainSim.stopList.get(15);
        else if (randInt <= 42)
            destination = TrainSim.stopList.get(16);
        else if (randInt <= 43)
            destination = TrainSim.stopList.get(17);
        else if (randInt <= 44)
            destination = TrainSim.stopList.get(18);
        else if (randInt <= 49)
            destination = TrainSim.stopList.get(19);
        else if (randInt <= 54)
            destination = TrainSim.stopList.get(20);
        else if (randInt <= 59)
            destination = TrainSim.stopList.get(21);
        else if (randInt <= 64)
            destination = TrainSim.stopList.get(22);
        else
            destination = TrainSim.stopList.get(23);

        if(!destination.name.equals(stop.name)) {
            return destination;
        } else {
            return getDestination(stop);
        }
    }
    private Passenger genPass() {
        Stop destination = getDestination(stop);
        while(destination == stop) {
            destination = getDestination(stop);
        }
        String direction;
        if (stop.ID < destination.ID) {
            direction = "east";
        } else {
            direction = "west";
        }
        return new Passenger(direction, destination);
    }
    public void run() {
        TrainSim.agenda.add(new PassengerMaker(stop), randomInterval(stop));
        Stats.totalPassengers++;
        stop.addPassenger(genPass());
    }
    public String toString() {
        return "PassengerMaker @ " + stop.name;
    }

    /*
    // Main Method for Testing Purposes
    public static void main(String[] args) {
        ArrayList<Stop> stopList = new ArrayList<>();
        TrainSim.fillStopList();

        Stop stop = new Stop("Target Field", 1, "downtown");
        for(int i = 0; i < 20; i++) {
            System.out.println(getDestination(stop));
        }
    }*/
}
