import java.util.ArrayList;

/**
 * Created by Bill on 2/22/16.
 * Car holds its current location, locationsVisited, and distance traveled.
 *
 * As of right now I don't have any parameters like color, or tires in here (not sure why your tires would matter).
 */
public class Car {

    private int currentLocation;
    private int currentDestination;
    private int distanceTraveled;
    private ArrayList<Integer> locationsVisited;

    public Car(int currentLocation){
        this.currentLocation = currentLocation;
        distanceTraveled = 0;
        locationsVisited = new ArrayList<Integer>();
        locationsVisited.add(currentLocation);
    }

    /**
     * This is the most complicated (weirdest) method in the class.  Starting at a random number I traverse
     * the possibleMoves and see if I've been to its destination.  If not, move there.  Once I've reached the end and
     * found no new places to travel to, I simple pick a random destination to go to.
     * @param possibleMoves
     */
    public void moveToNextLocation(ArrayList<Edge> possibleMoves){
        for (int i = Simulator.random.nextInt(possibleMoves.size() - 1) % Simulator.NUMOFLOCATIONS; i < possibleMoves.size(); i++)
            if (!locationsVisited.contains(possibleMoves.get(i).getDest())){
                distanceTraveled += possibleMoves.get(i).getWeight();
                currentLocation = possibleMoves.get(i).getDest();
                locationsVisited.add(possibleMoves.get(i).getDest());
                return;
            }
        currentLocation = possibleMoves.get(Simulator.random.nextInt(possibleMoves.size() - 1)).getDest();
    }

    public boolean checkWin(){
        if (locationsVisited.size() == Simulator.NUMOFLOCATIONS)
            return true;
        return false;
    }

    public int getCurrentLocation(){return currentLocation;}

    public int getCurrentDestination(){ return currentDestination;}

    public int getDistanceTraveled(){ return distanceTraveled;}
}
