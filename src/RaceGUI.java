import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Catherine
 */
public class RaceGUI extends Application {
    private static float XCIRCLEOFFSET = 9.25f;
    private static float YCIRCLEOFFSET = 5;

    private Group circles;
    private Button btn;
    private Simulator simulator;
    private ArrayList<Car> cars;
    private ArrayList<Location> locations;
    private Group root;
    private ArrayList<Label> locationsVisitedLabels; private ArrayList<Label> currentSpeedLabels;
    
    @Override
    public void start(Stage primaryStage) {
        simulator = new Simulator();
        cars = simulator.getCars();
        locations = simulator.getLocations();
        root = new Group();

        locationsVisitedLabels = new ArrayList<Label>(); currentSpeedLabels = new ArrayList<Label>();
        for (int i = 0; i < cars.size(); i++) {
            locationsVisitedLabels.add(new Label(""));
            currentSpeedLabels.add(new Label("0"));
        }
        
        //Map Image
        Image map = null;
        try {
            map = new Image(new FileInputStream("src/US.Map.grey.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView iv = new ImageView(map);
        root.getChildren().add(iv);

        initAll();
        
        Scene scene = new Scene(root, 900, 400);
        
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** This logic is brutally over complicated */
    private void updateGUI(){
        for (Node n : root.getChildren()){
            if (n.getUserData() != null) {

                int edgeWeight = cars.get((Integer.decode(((String) n.getUserData()).substring(3)))).getTotalDistancetoNextLocation();
                int DTND = cars.get((Integer.decode(((String) n.getUserData()).substring(3)))).getDistanceToNextDestination();
                int x1 = locations.get(cars.get((Integer.decode(((String) n.getUserData()).substring(3)))).getCurrentLocation()).getxCord();
                int y1 = locations.get(cars.get((Integer.decode(((String) n.getUserData()).substring(3)))).getCurrentLocation()).getyCord();

                if (((String) n.getUserData()).startsWith("car")) {
                    n.setTranslateX(locations.get(cars.get((Integer.decode(((String) n.getUserData()).substring(3)))).getCurrentLocation()).getxCord());
                    n.setTranslateY(locations.get(cars.get((Integer.decode(((String) n.getUserData()).substring(3)))).getCurrentLocation()).getyCord());
                }
            }
        }
        //Records current location
        for(int i = 0; i < cars.size(); i++) {
            String s = "";
            for (Integer j :cars.get(i).getLocationsVisited())
                s += locations.get(j).getName() + " ";
                 locationsVisitedLabels.get(i).setText(s);

            currentSpeedLabels.get(i).setText(Integer.toString(cars.get(i).getCurrentSpeed()));
        }
    }

    private void initAll(){
        Image carImage = null;

        createMarkers();
        carMovement();

        for (int i = 0; i < circles.getChildren().size(); i++){ /** Initializing location circles */
            circles.getChildren().get(i).setTranslateX(locations.get(i).getxCord() + XCIRCLEOFFSET);
            circles.getChildren().get(i).setTranslateY(locations.get(i).getyCord() - YCIRCLEOFFSET);
        }

        root.getChildren().add(circles);

        for (int i = 0; i < locations.size(); i++){     /** Initializing locations */
            root.getChildren().add(new Text(locations.get(i).getxCord(), locations.get(i).getyCord(), locations.get(i).getName()));
        }

        try {   /** Initializing cars */
            carImage = new Image((new FileInputStream("src/car.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cars.size(); i++){
            ImageView im = new ImageView(carImage);
            im.setUserData("car" + i);
            im.setTranslateX(locations.get(cars.get(i).getCurrentLocation()).getxCord());
            im.setTranslateY(locations.get(cars.get(i).getCurrentLocation()).getyCord());
            root.getChildren().add(im);
        }

        root.getChildren().add(btn);
        
        Rectangle leaderboard = new Rectangle(650, 50, 200, 300);
        leaderboard.setFill(Color.WHITE);
        leaderboard.setStrokeType(StrokeType.OUTSIDE);
        leaderboard.setStroke(Color.BLACK);
        leaderboard.setStrokeWidth(2);
        root.getChildren().add(leaderboard);
        
        Label board = new Label();
        board.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        board.setText("Leaderboard");
        board.setTranslateX(660);
        board.setTranslateY(60);

        Label currentSpeedLabel = new Label();
        currentSpeedLabel.setFont(Font.font("Verdana", 10));
        currentSpeedLabel.setText("Current Speed:");
        currentSpeedLabel.setTranslateX(750);
        currentSpeedLabel.setTranslateY(80);

        for (int i = 0 ; i < cars.size(); i++) {
            locationsVisitedLabels.get(i).setTranslateX(670 + (counter * 20));
            locationsVisitedLabels.get(i).setTranslateY(113 + (i * 25));
            root.getChildren().add(locationsVisitedLabels.get(i));

            currentSpeedLabels.get(i).setTranslateX(800);
            currentSpeedLabels.get(i).setTranslateY(113 + (i * 25));
            root.getChildren().add(currentSpeedLabels.get(i));
        }
        root.getChildren().add(board);
        root.getChildren().add(currentSpeedLabel);


        //Places label for each car
        for(int i = 0; i < cars.size(); i++) {
            Label carlabel = new Label();
            carlabel.setTranslateX(660);
            carlabel.setTranslateY(100 + (i * 25));
            carlabel.setText("Car" + (i+1));
            root.getChildren().add(carlabel);
        }
    }

    /**
    * Places circular markers for each location
    */
    public void createMarkers()
    {
        circles = new Group();
        for (int i = 0; i < 5; i++) {
           Circle circle = new Circle(10, Color.web("white"));
           circle.setStrokeType(StrokeType.OUTSIDE);
           circle.setStroke(Color.web("black", 0.16));
           circle.setStrokeWidth(2);
           circles.getChildren().add(circle);
        }
    }    
    
    private int counter = 0;
    /**
    * Determines Next button's features and actions
    */
    public void carMovement()
    {
        btn = new Button();
        btn.setText("Next");
        btn.setTranslateX(550);
        btn.setTranslateY(360);
	btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simulator.updateCars();
                updateGUI();
                simulator.checkForWinner();
                counter = counter + 1;
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
