/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racegui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author bjs
 */
public class RaceGUI extends Application {
    private Group circles;
    private Button btn;
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        
        //Map Image
        Image map = new Image("file:\\C:\\Users\\bjs\\Documents\\NetBeansProjects\\RaceGUI\\src\\racegui\\US Map grey.png");
        ImageView iv = new ImageView(map);
        root.getChildren().add(iv);
        
        root.getChildren().add(btn);
        root.getChildren().add(circles);
        
        createMarkers();
        carMovement();
        
        //Labels for markers
        Text l1 = new Text();
        Text l2 = new Text();
        Text l3 = new Text();
        Text l4 = new Text();
        Text l5 = new Text();
        
        Node location1 = circles.getChildren().get(0);
        Node location2 = circles.getChildren().get(1);
        Node location3 = circles.getChildren().get(2);
        Node location4 = circles.getChildren().get(3);
        Node location5 = circles.getChildren().get(4);
        
	//North Dakota
        l1.setText("ND");
        l1.setTranslateX(243);
        l1.setTranslateY(60);
        l1.setFont(Font.font("Verdana", 10));
        location1.setTranslateX(250);
        location1.setTranslateY(58);
        root.getChildren().add(l1);
        
        //Nevada
	l2.setText("NV");
	l2.setTranslateX(103);
	l2.setTranslateY(136);
        l2.setFont(Font.font("Verdana", 10));
        location2.setTranslateX(110);
        location2.setTranslateY(134);
        root.getChildren().add(l2);
        
        //Colorado
	l3.setText("CO");
	l3.setTranslateX(206);
	l3.setTranslateY(182);
        l3.setFont(Font.font("Verdana", 10));
        location3.setTranslateX(213);
        location3.setTranslateY(180);
        root.getChildren().add(l3);
        
        //Texas
	l4.setText("TX");
	l4.setTranslateX(299);
	l4.setTranslateY(286);
        l4.setFont(Font.font("Verdana", 10));
        location4.setTranslateX(305);
        location4.setTranslateY(284);
        root.getChildren().add(l4);
        
        //New York
        l5.setText("NY");
	l5.setTranslateX(497);
	l5.setTranslateY(96);
        l5.setFont(Font.font("Verdana", 10));
        location5.setTranslateX(504);
        location5.setTranslateY(94);
        root.getChildren().add(l5);
       
        //Car 
        Image car = new Image("file:\\C:\\Users\\bjs\\Documents\\NetBeansProjects\\RaceGUI\\src\\racegui\\car.png");
        ImageView iv2 = new ImageView(car);
        iv2.setTranslateX(100);
        iv2.setTranslateY(120);
        root.getChildren().add(iv2);
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
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
                System.out.println("Next");
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