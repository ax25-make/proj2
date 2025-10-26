import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

		MenuItem rules = new MenuItem("Rules");
		rules.setOnAction(e -> Rules.showRules());

		MenuItem odds = new MenuItem("Odds");
		odds.setOnAction(e -> Odds.showOdds());

		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> primaryStage.close());

		menu.getItems().addAll(rules, odds, exit);
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
		
		// ---- Hover Effect ----
		startButton.setOnMouseEntered(e -> startButton.setStyle(
			"-fx-background-color: #ffffaa;" +   // lighter yellow
			"-fx-text-fill: black;" +           
			"-fx-font-size: 24px;" +            
			"-fx-font-weight: bold;" +          
			"-fx-background-radius: 50;" +      
			"-fx-border-color: #bbbbbb;" +      
			"-fx-border-width: 2;" +            
			"-fx-border-radius: 50;" +
			"-fx-padding: 10 30 10 30;"
		));

		startButton.setOnMouseExited(e -> startButton.setStyle(
			"-fx-background-color: white;" +   
			"-fx-text-fill: black;" +           
			"-fx-font-size: 24px;" +            
			"-fx-font-weight: bold;" +          
			"-fx-background-radius: 50;" +      
			"-fx-border-color: #bbbbbb;" +      
			"-fx-border-width: 2;" +            
			"-fx-border-radius: 50;" +
			"-fx-padding: 10 30 10 30;"
		));

		// ---- Click Effect ----
		startButton.setOnMousePressed(e -> startButton.setStyle(
			"-fx-background-color: #ffdd55;" +   // slightly darker yellow
			"-fx-text-fill: black;" +           
			"-fx-font-size: 24px;" +            
			"-fx-font-weight: bold;" +          
			"-fx-background-radius: 50;" +      
			"-fx-border-color: #bbbbbb;" +      
			"-fx-border-width: 2;" +            
			"-fx-border-radius: 50;" +
			"-fx-padding: 10 30 10 30;"
		));

		startButton.setOnMouseReleased(e -> startButton.setStyle(
			"-fx-background-color: #ffffaa;" +  // hover color if mouse still over
			"-fx-text-fill: black;" +           
			"-fx-font-size: 24px;" +            
			"-fx-font-weight: bold;" +          
			"-fx-background-radius: 50;" +      
			"-fx-border-color: #bbbbbb;" +      
			"-fx-border-width: 2;" +            
			"-fx-border-radius: 50;" +
			"-fx-padding: 10 30 10 30;"
		));	

		// ---- START TEXT ----
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
		
		// ---- BUTTON ACTION ----
        startButton.setOnAction(e -> {
            GameScene gameScene = new GameScene(primaryStage);
            primaryStage.setScene(gameScene.getScene());
        });
	}

}
