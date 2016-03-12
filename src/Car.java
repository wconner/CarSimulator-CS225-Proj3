import java.util.ArrayList;

/**
 * Created by Bill on 2/22/16.
 * Expanded by Connor, last update on 3/10/16
 * Car holds its current location, locationsVisited, and distance traveled.
 * <p>
 * As of right now I don't have any parameters like color, or tires in here (not sure why your tires would matter).
 */
public class Car {

    private int currentLocation;
    private int currentDestination;
    private int distanceTraveled;
    private ArrayList<Integer> locationsVisited;
    private int maxVelocity;
    private int acceleration;
    private int currentSpeed;
    private int distanceToNextDestination;
    private int totalDistancetoNextLocation;
    private int firstTime;
    private int racingNumber;

    /**
     * Constructor class for Car
     * @param currentLocation
     * @param currentDestination
     */
    public Car(int currentLocation, int currentDestination) {
        firstTime = 0;
        racingNumber = currentLocation + 1;
        this.currentLocation = currentLocation;
        this.currentDestination = currentDestination;
        distanceTraveled = 0;
        locationsVisited = new ArrayList<Integer>();
        locationsVisited.add(currentLocation);
        acceleration = Simulator.random.nextInt(1)+1;
        maxVelocity = Simulator.random.nextInt(2)+2;
    }

    /**
     * This is the most complicated (weirdest) method in the class.  Starting at a random number I traverse
     * the possibleMoves and see if I've been to its destination.  If not, move there.  Once I've reached the end and
     * found no new places to travel to, I simple pick a random destination to go to.
     * -Bill
     *
     * (This is probably not the case anymore)
     * -Connor
     *
     * @param possibleMoves
     */
    public void updateLocation(ArrayList<Edge> possibleMoves) {
        //logic for acceleration---------------------------
        if((currentSpeed+acceleration) <=maxVelocity){
            currentSpeed += acceleration;
        }
        else
        {
            currentSpeed = maxVelocity;
        }
        if(firstTime ==0){
            for(Edge e : possibleMoves){
                if(e.getDest() == currentDestination){
                    distanceToNextDestination = (int)e.getWeight();
                    totalDistancetoNextLocation = distanceToNextDestination;
                }
            }
            //System.out.println("Car #"+racingNumber+":"+distanceToNextDestination);
            firstTime =1;
        }

        //System.out.println("Car: ("+currentLocation+" to "+ currentDestination+") : "+distanceToNextDestination);
        //System.out.println("^Before Logic^");
        //moves car towards destination or registers car hitting destination -------------------
        if((distanceToNextDestination-currentSpeed)>0){
            distanceToNextDestination -= currentSpeed; //case: car still traveling: progress towards destination, records distance traveled
            distanceTraveled += currentSpeed;

        }
        else{
            distanceTraveled += distanceToNextDestination;//case: car can hit destination: stops the car at the destination and figures out new destination (spends the rest of the time resupplying)
            currentSpeed = 0;
            chooseNewDestination(possibleMoves);
        }
        //System.out.println("Car: ("+currentLocation+" to "+ currentDestination+") : "+distanceToNextDestination);
        //System.out.println("^After Logic^");
        //System.out.println("Locations Visted"+locationsVisited.toString());
        //System.out.println("++++++++++++++++++++++++");
    }

    // previously named chooseNewLocation
    // job is set it so that the new currentLocation is now the previous destination, then to choose next destination if it can, then set the new distanceToNextDestination
    private void chooseNewDestination(ArrayList<Edge> possibleMoves) {

        //if(firstTime == 1) {


            currentLocation = currentDestination;

            if (!locationsVisited.contains(currentLocation)) {
            locationsVisited.add(currentLocation);
            }

            for (int i = Simulator.random.nextInt(possibleMoves.size() - 1) % Simulator.NUMOFLOCATIONS; i < possibleMoves.size(); i++)
                if (!locationsVisited.contains(possibleMoves.get(i).getDest())) {
                    currentDestination = possibleMoves.get(i).getDest();
                    distanceToNextDestination = (int) possibleMoves.get(i).getWeight(); //issue with types, probably gonna consult for this~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    totalDistancetoNextLocation = distanceToNextDestination;
                    //locationsVisited.add(possibleMoves.get(i).getDest()); <<<<<<<<< I think this might be obsolete with the above code about locations visited
                    return;
                }
        //}
        //firstTime = 1;
        int x = Simulator.random.nextInt(possibleMoves.size());
        currentDestination = possibleMoves.get(x).getDest();
        distanceToNextDestination = (int)possibleMoves.get(x).getWeight(); //issue with types, probably gonna consult for this~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        totalDistancetoNextLocation = distanceToNextDestination;
    }

    public boolean checkWin() {
        if (locationsVisited.size() == Simulator.NUMOFLOCATIONS)
            return true;
        return false;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public int getCurrentDestination() {
        return currentDestination;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    public int getCurrentSpeed(){ return currentSpeed;}

    public int getRacingNumber(){return racingNumber;}

    public int getDistanceToNextDestination() { return distanceToNextDestination;}

    public int getTotalDistancetoNextLocation() { return totalDistancetoNextLocation;}

    public ArrayList<Integer> getLocationsVisited() {return locationsVisited;}
}