/**
 * Created by halvo431 on 4/21/16.
 */
public class Stop {
    // Fields
    Q1 eastboundPassengers = new Q1();
    Q1 westboundPassengers = new Q1();
    String name;
    int ID;
    String type;

    // Constructor
    public Stop(String name, int ID, String type) {
        this.name = name;
        this.ID = ID;
        this.type = type;
    }

    // Methods
    public void addPassenger(Passenger passenger) {
        if(passenger.getDirection().equals("east")) {
            eastboundPassengers.add(passenger);
        } else {
            westboundPassengers.add(passenger);
        }
    }
    public int getID() {
        return ID;
    }

    public String toString() {
        return this.name + ", " + this.ID;
    }
    public void printPassengers() {
        System.out.println(this.ID + ": " + this.name);
        System.out.println("\tEastbound: " + eastboundPassengers.length());
        System.out.println("\tWestbound: " + westboundPassengers.length());
    }
}
