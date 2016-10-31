/**
 * Created by Martin on 4/24/2016.
 */
public class Stats {
    static boolean showEvents = false;

    static int totalPassengers;

    static int totalPassengersInTransit = 0;
    static int totalTravelTime = 0;
    static int totalPassengersArrived = 0;

    static int totalTimeAtStops = 0;
    static int totalNumStops = 0;

    static double maxWaitTime = 0;

    public static void getStats() {
        System.out.println("Total Passengers Created: " + totalPassengers);
        System.out.println("Total Travel Time: " + totalTravelTime + " seconds");
        System.out.println("Total Passengers Waiting: " + (totalPassengers - totalPassengersInTransit));
        System.out.println("Total Passengers in Transit: " + (totalPassengersInTransit - totalPassengersArrived));
        System.out.println("Total Passengers Arrived: " + totalPassengersArrived);
        System.out.println("Max Wait Time: " + (int)maxWaitTime);
        System.out.println("Average Travel Time: " + totalTravelTime / totalPassengersArrived + " seconds");
        System.out.println("\nTotal Time Trains Spend at Stops: " + totalTimeAtStops + " seconds");
        System.out.println("Total Number of Stops made by Trains: " + totalNumStops);
        System.out.println("Average Time Train Waits Per Stop: " + (totalTimeAtStops / totalNumStops - 180) + " seconds");
        System.out.println("\t***180 seconds of travel time too!");
        System.out.println("Average Train Capacity: " + (int)(100.0 * (1.0 * totalPassengersInTransit - totalPassengersArrived) / (TrainSim.carsPerTrain * TrainSim.totalNumTrains * 50)) + "%");
    }
    public static void showWaitingPassengers() {
        System.out.println("\nPassengers Waiting:");
        for(int i = 1; i < TrainSim.stopList.size(); i++) {
            TrainSim.stopList.get(i).printPassengers();
        }
        System.out.println();
    }
}
