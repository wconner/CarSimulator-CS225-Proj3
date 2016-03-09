import javax.swing.*;
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
    public static int NUMOFLOCATIONS;

    private ArrayList<Car> cars;
    private ArrayList<Location> locations;
    private MapGraph map;


    public Simulator(){
        cars = new ArrayList<Car>();
        locations = new ArrayList<Location>();
        map = new MapGraph();

        init();
    }

    /**
     * Calls updateLocation on each car.
     */
    public void updateCars(){
        for (Car c : cars)
            c.updateLocation(map.getEdges(c.getCurrentLocation()));
    }

    /**
     * Checks to see if there is a winner.
     */
    public void checkForWinner(){
        for (Car c : cars)
            if (c.checkWin()) {
                JOptionPane.showMessageDialog(null, "We have a winner!\nDistance traveled by winner: " + c.getDistanceTraveled());
            }
    }

    /**
     * Basic initialization of the map. Right now there will always be a path from 1 -> 2 -> 3 -> 4
     *
     * Car initialization should be split up from location initialization
     * May want to read in map from a text file (could use JSON)
     */
    private void init(){

        initLocations();
        initCars();
        NUMOFLOCATIONS = cars.size();
    }

    private void initCars(){
        for (int i = 0; i < 4; i++)
            cars.add(new Car(i, map.getEdges(i).get(random.nextInt(map.getEdges(i).size())).getDest()));
    }

    private void initLocations(){
        locations.add(new Location("NY", 0, 497, 96));
        map.insert(new Edge(0, 1, 10));
        map.insert(new Edge(0, 2, 13));
        map.insert(new Edge(0, 2, 9));

        locations.add(new Location("TX", 1, 299, 286));
        map.insert(new Edge(1, 2, 5));
        map.insert(new Edge(1, 3, 3));

        locations.add(new Location("CO", 2, 206, 182));
        map.insert(new Edge(2, 3, 8));
        map.insert(new Edge(2, 4, 6));

        locations.add(new Location("NV", 3, 103, 136));
        map.insert(new Edge(3, 4, 2));

        locations.add(new Location("ND", 4, 243, 60));
        map.insert(new Edge(4, 1, 12));
    }

    public ArrayList<Location> getLocations(){ return locations;}
    public ArrayList<Car> getCars(){ return cars;}
}
