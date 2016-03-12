/**
 * Created by Bill on 2/29/16.
 */

public class Location {
    private int value;
    private int xCord;
    private int yCord;
    private String name;

    public Location(String name, int value, int x, int y){
        this.name = name;
        this.value = value;
        this.xCord = x;
        this.yCord = y;
    }

    public int getxCord(){ return xCord;}
    public int getyCord(){ return yCord;}
    public String getName() {return name;}
}