import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author bjs
 */
public class RaceGUI extends Application {
    private Group circles;
    private Button btn;
    private Simulator simulator;
    private ArrayList<Car> cars;
    private ArrayList<Location> locations;
    private Group root;
    
    @Override
    public void start(Stage primaryStage) {
        simulator = new Simulator();
        cars = simulator.getCars();
        locations = simulator.getLocations();
        root = new Group();
        
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
        
        root.getChildren().add(btn);
        root.getChildren().add(circles);
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** This logic is brutally over complicated */
    private void updateGUI(){
        for (Node n : root.getChildren()){
            if (n.getUserData() != null)
              if (((String) n.getUserData()).startsWith("car")) {
                  n.setTranslateX(locations.get(cars.get((Integer.decode(((String) n.getUserData()).substring(3)))).getCurrentLocation()).getxCord());
                  n.setTranslateY(locations.get(cars.get((Integer.decode(((String) n.getUserData()).substring(3)))).getCurrentLocation()).getyCord());
              }
        }
    }

    private void initAll(){
        Image carImage = null;
        createMarkers();
        carMovement();

        for (int i = 0; i < locations.size(); i++){     /** Initializing locations */
            root.getChildren().add(new Text(locations.get(i).getxCord(),locations.get(i).getyCord(),locations.get(i).getName()));
        }

        for (int i = 0; i < circles.getChildren().size(); i++){ /** Initializing location circles */
            circles.getChildren().get(i).setTranslateX(locations.get(i).getxCord() + 9.25);
            circles.getChildren().get(i).setTranslateY(locations.get(i).getyCord() - 5);
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
    }
    
    /**
     * Creates circular markers for the chosen locations locations  
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
