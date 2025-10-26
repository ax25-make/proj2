import java.io.FileInputStream;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Welcome extends Application {
	Stage welcomeScene, gameScene;
	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Start Page");

		// menu
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");

		MenuItem rulesItem = new MenuItem("Rules");
        //rulesItem.setOnAction(e -> showRules());

        MenuItem oddsItem = new MenuItem("Odds");
        //oddsItem.setOnAction(e -> showOdds());

        MenuItem exitItem = new MenuItem("Exit");
        //exitItem.setOnAction(e -> window.close());

        menu.getItems().addAll(rulesItem, oddsItem, exitItem);
        menuBar.getMenus().add(menu);

		// FileInputStream inputstream = new FileInputStream("https://img.freepik.com/premium-photo/red-board-with-red-white-game-middle-it_1354347-1648.jpg?w=360"); 
		Image image = new Image("https://img.freepik.com/premium-photo/red-board-with-red-white-game-middle-it_1354347-1648.jpg?w=360"); 

		ImageView imageView = new ImageView(image);
		
	

		//Setting the position of the image 
		imageView.setX(50); 
		imageView.setY(25); 

		//setting the fit height and width of the image view 
		imageView.setFitHeight(455); 
		imageView.setFitWidth(500); 
		
		//Setting the preserve ratio of the image view 
		imageView.setPreserveRatio(true);  

		Label text1 = new Label("ALEX'S WORLD FAMOUS");
		Label text2 = new Label("KENO");
		Label text3 = new Label("GAME");

		String smallTextStyle = 
			"-fx-text-fill: black;" + 
			"-fx-font-size: 18px;";

		text1.setStyle(smallTextStyle); // "ALEX'S WORLD FAMOUS"
		text3.setStyle(smallTextStyle); // "GAME"

		
		text2.setStyle( // For "KENO"
			"-fx-text-fill: black;" + 
			"-fx-font-size: 100px;" +       
			"-fx-font-weight: normal;"
		);

		VBox textBox = new VBox(1, text1, text2, text3); // With Spacing of 10
		
		Button startButton = new Button("START");
		// startButton.setOnAction(e -> {
        //     setupGameScene(); // create game scene
        //     window.setScene(gameScene);
        // });
		Label startText = new Label("LOOSE ALL YOUR MONEY");
		VBox buttonBox = new VBox(10, startText, startButton); // With Spacing of 10

		startButton.setStyle(
			"-fx-background-color: white;" +   
			"-fx-text-fill: black;" +           
			"-fx-font-size: 24px;" +            
			"-fx-font-weight: bold;" +          
			"-fx-background-radius: 50;" +      
			"-fx-border-color: #bbbbbb;" +      
			"-fx-border-width: 2;" +            
			"-fx-border-radius: 50;" +
			"-fx-padding: 10 30 10 30;"
		);

		startText.setStyle(
			"-fx-text-fill: black;" +
			"-fx-font-size: 15px;" +
			"-fx-font-weight: bold;" +
			"-fx-padding: 10px;"
		);

		textBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		textBox.setPadding(new Insets(0,0,10,0));
		buttonBox.setPadding(new Insets(0,0,50,0));


		// button1.setOnAction(new EventHandler<ActionEvent>(){
		// 	public void handle(ActionEvent action){
		// 		String newText = text1.getText() + " : from the center text field!";
		// 		text2.setText(newText);
		// 		button1.setDisable(true);
		// 		button1.setText("pressed");
		// 	}
		// });


		//asas
		// VBox bottomBox = new VBox(10, imageView, button2);

		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #ffffffff;"); 
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(textBox);
		borderPane.setCenter(imageView);
		borderPane.setBottom(buttonBox);
		// borderPane.setTop(text2);
		// borderPane.setLeft(buttonBox);

		// BorderPane.setMargin(buttonBox, new Insets(50));
        // BorderPane.setMargin(text2, new Insets(50));
				
		borderPane.setTop(menuBar);

		Scene scene = new Scene(borderPane, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
		
				
		
	}

}
