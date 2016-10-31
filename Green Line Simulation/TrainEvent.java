/**
 * Created by halvo431 on 4/21/16.
 */
public class TrainEvent implements Event {
    // Fields
    Train train;
    Stop stop;

    // Constructor
    public TrainEvent(Train train, Stop stop) { // Created every time a train arrives at a stop
        this.train = train;
        this.stop = stop;
    }

    // Methods
    public void run() {
        double time = 180.0;

        // PassengerEvent incorporated in here
        for(int i = 0; i < train.trainCars.size(); i++) {
            for(int j = 0; j < train.trainCars.get(i).passengerList.size(); j++) {
                Passenger passenger = train.trainCars.get(i).passengerList.get(j);
                if(passenger.destination == stop) {
                    train.trainCars.get(i).passengerList.remove(passenger);
                    time += 2; // Add two seconds for passenger to get off train
                    Stats.totalPassengersArrived++;
                    if((TrainSim.agenda.getCurrentTime() - passenger.startTime) > Stats.maxWaitTime) {
                        Stats.maxWaitTime = (TrainSim.agenda.getCurrentTime() - passenger.startTime);
                    }
                    Stats.totalTravelTime += (TrainSim.agenda.getCurrentTime() - passenger.startTime);
                }
            }
        }

        if (train.direction.equals("east")) {
            time += 1 * stop.eastboundPassengers.length(); // Add a second for passengers to get on
            while (!train.isFull() && stop.eastboundPassengers.length() > 0) {
                train.add((Passenger)stop.eastboundPassengers.remove());
            }
            time -= 1 * stop.eastboundPassengers.length(); // If some passengers weren't added, their time doesn't count for holding back the train
        } else { // Train direction is west
            time += 1 * stop.westboundPassengers.length();
            while (!train.isFull() && stop.westboundPassengers.length() > 0) {
                train.add((Passenger)stop.westboundPassengers.remove());
            }
            time -= 1 * stop.westboundPassengers.length();
        }
        if (time < 195.0) {
            time = 195.0;
        }
        Stats.totalNumStops++;
        Stats.totalTimeAtStops += time;

        TrainSim.agenda.add(new TrainEvent(train, train.findNextStop()), time);
        train.currentStop = train.getNextStop();
    }
    public String toString() {
        return "TrainEvent @ " + stop.name;
    }
}
