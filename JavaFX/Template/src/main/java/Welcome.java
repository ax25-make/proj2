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

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Start Page");

		// ---- MENU ----
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");

		MenuItem rulesItem = new MenuItem("Rules");
		rulesItem.setOnAction(e -> Rules.showRules());

		MenuItem oddsItem = new MenuItem("Odds");
		oddsItem.setOnAction(e -> Odds.showOdds());

		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction(e -> primaryStage.close());

		menu.getItems().addAll(rulesItem, oddsItem, exitItem);
		menuBar.getMenus().add(menu);

		// ---- TEXT ----
		Label text1 = new Label("ALEX'S WORLD FAMOUS");
		Label text2 = new Label("KENO");
		Label text3 = new Label("GAME");

		text1.setStyle("-fx-text-fill: black; -fx-font-size: 18px;");
		text2.setStyle("-fx-text-fill: black; -fx-font-size: 100px;");
		text3.setStyle("-fx-text-fill: black; -fx-font-size: 18px;");

		VBox textBox = new VBox(5, text1, text2, text3);
		textBox.setAlignment(Pos.CENTER);

		// ---- IMAGE ----
		Image image = new Image("https://img.freepik.com/premium-photo/red-board-with-red-white-game-middle-it_1354347-1648.jpg?w=360"); 
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(400);
		imageView.setFitWidth(500);
		imageView.setPreserveRatio(true);

		// ---- BUTTON ----
		Button startButton = new Button("START");
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

		Label startText = new Label("LOOSE ALL YOUR MONEY");
		startText.setStyle(
			"-fx-text-fill: black;" +
			"-fx-font-size: 15px;" +
			"-fx-font-weight: bold;" +
			"-fx-padding: 10px;"
		);

		VBox buttonBox = new VBox(10, startText, startButton);
		buttonBox.setAlignment(Pos.CENTER);

		// ---- MAIN LAYOUT ----
		VBox mainBox = new VBox(20, textBox, imageView, buttonBox);
		mainBox.setAlignment(Pos.CENTER);
		mainBox.setPadding(new Insets(20));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setCenter(mainBox);

		Scene scene = new Scene(borderPane, 700, 900);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
