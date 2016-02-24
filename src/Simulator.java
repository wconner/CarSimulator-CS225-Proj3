import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bill on 2/22/16.
 * Logic controller for the simulator.
 *
 * There is no location/venue class,the location is simply an integer held by edges as sources and destinations.
 *
 * As of right now, the run loop is inside the constructor, and you can travel as many miles as you want in a day,
 * should be changed so that updateCars gets called on the car with the least amount of miles instead of counting 'days'
 *
 * I use ArrayLists everywhere, I wonder if there are better data structures to be using other than ArrayLists for everything
 *
 */
public class Simulator {
    public static Random random = new Random();
    public static int NUMOFLOCATIONS = 4;

    private ArrayList<Car> cars;
    private MapGraph map;


    public Simulator(){
        cars = new ArrayList<Car>();
        map = new MapGraph();

        init();
        while(true) {
            updateCars();
            checkForWinner();
        }
    }

    /**
     * Calls moveToNextLocation on each car.
     */
    public void updateCars(){
        for (Car c : cars)
            c.moveToNextLocation(map.getEdges(c.getCurrentLocation()));
    }

    /**
     * Checks to see if there is a winner.
     */
    private void checkForWinner(){
        for (Car c : cars)
            if (c.checkWin()) {
                System.out.println("We have a winner!\nDistance traveled by winner: " + c.getDistanceTraveled());
                System.exit(1);
            }
    }

    /**
     * Basic initialization of the map. Right now there will always be a path from 1 -> 2 -> 3 -> 4
     *
     * Car initialization should be split up from location initialization
     * May want to read in map from a text file (could use JSON)
     */
    private void init(){
        for (int i = 0; i < NUMOFLOCATIONS; i++) {
            map.insert(new Edge(i, (i + 1) % NUMOFLOCATIONS, random.nextInt(10)));
            map.insert(new Edge(i, (i + random.nextInt(NUMOFLOCATIONS - 1)) % NUMOFLOCATIONS, random.nextInt(10)));
            cars.add(new Car(i));
        }

    }

    public static void main(String[] argc){ new Simulator();}
}
