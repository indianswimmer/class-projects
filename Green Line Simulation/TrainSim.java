import java.util.ArrayList;

/**
 * Created by Martin on 4/22/2016.
 */
public class TrainSim {
    // Fields
    static PQ agenda = new PQ();
    static ArrayList<Stop> stopList = new ArrayList<>();

    static int totalNumTrains = 15;
    static int carsPerTrain = 2;
    static int simRunTime = 10000;
    static double interArrivalTime = 45.0;


    // Main Method
    public static void main(String[] args) {
        // Initializing stops
        fillStopList();

        // Creating a PassengerMaker for each stop (stops start at 1)
        for(int i = 1; i < stopList.size(); i++) {
            agenda.add(new PassengerMaker(stopList.get(i)), agenda.getCurrentTime());
        }
        // Create trains
        for(int i = 1; i <= totalNumTrains / 2; i++) { // Half start going east
            Train train = new Train(stopList.get((int)(23 / (totalNumTrains / 2.0) * i)), "east", carsPerTrain);
            agenda.add(new TrainEvent(train, train.getCurrentStop()), agenda.getCurrentTime());
        }
        for(int i = 1; i <= (totalNumTrains + 1) / 2; i++) { // Half start going west
            Train train = new Train(stopList.get((int)(23 / ((totalNumTrains + 1) / 2.0) * i)), "west", carsPerTrain);
            agenda.add(new TrainEvent(train, train.getCurrentStop()), agenda.getCurrentTime());
        }

        Stats.showEvents = false;

        while (agenda.getCurrentTime() <= simRunTime && !agenda.isEmpty()) {
            Event e = agenda.remove();
            if(Stats.showEvents) {
                System.out.println("Event " + e + " removed and running");
            }
            e.run();
        }

        Stats.getStats();
        //Stats.showWaitingPassengers();

    }

    // Methods
    public static void fillStopList() {
        stopList.add(new Stop("Stop Error (0)", 0, "error"));
        stopList.add(new Stop("Target Field", 1, "downtown"));
        stopList.add(new Stop("Warehouse/Hennepin Ave", 2, "downtown"));
        stopList.add(new Stop("Nicollet Mall", 3, "downtown"));
        stopList.add(new Stop("Government Plaza", 4, "downtown"));
        stopList.add(new Stop("U.S. Bank Stadium", 5, "downtown"));
        stopList.add(new Stop("West Bank", 6, "campus"));
        stopList.add(new Stop("East Bank", 7, "campus"));
        stopList.add(new Stop("Stadium Village", 8, "campus"));
        stopList.add(new Stop("Prospect Park", 9, "normal"));
        stopList.add(new Stop("Westgate", 10, "normal"));
        stopList.add(new Stop("Raymond Ave", 11, "normal"));
        stopList.add(new Stop("Fairview Ave", 12, "normal"));
        stopList.add(new Stop("Snelling Ave", 13, "normal"));
        stopList.add(new Stop("Hamline Ave", 14, "normal"));
        stopList.add(new Stop("Lexington Pkwy", 15, "normal"));
        stopList.add(new Stop("Victoria St", 16, "normal"));
        stopList.add(new Stop("Dale St", 17, "normal"));
        stopList.add(new Stop("Western Ave", 18, "normal"));
        stopList.add(new Stop("Capitol/Rice St", 19, "downtown"));
        stopList.add(new Stop("Robert St", 20, "downtown"));
        stopList.add(new Stop("10th St", 21, "downtown"));
        stopList.add(new Stop("Central", 22, "downtown"));
        stopList.add(new Stop("Union Depot", 23, "downtown"));
    }
}
