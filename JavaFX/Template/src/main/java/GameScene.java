import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameScene {
    private Scene scene;
    //private KenoGame game;
    private GridPane betCardGrid;
    private int spotsSelected;
    private int drawingsSelected;
    private Label infoLabel;
    private Button drawButton;
    private Stage stage;


    public GameScene(Stage stage) {
        this.stage = stage;
        stage.setTitle("Keno Game");
        KenoGame game = new KenoGame();

        // ---- MENU ----
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");

        MenuItem rules = new MenuItem("Rules");
        rules.setOnAction(e -> Rules.showRules());

        MenuItem odds = new MenuItem("Odds");
        odds.setOnAction(e -> Odds.showOdds());

        MenuItem newLook = new MenuItem("New Look");
        //newLookItem.setOnAction(e -> applyNewLook());

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> stage.close());

        menu.getItems().addAll(rules, odds, newLook, exit);
        menuBar.getMenus().add(menu);

        // ---- Bet Card Grid ----
        betCardGrid = new GridPane();
        betCardGrid.setHgap(5);
        betCardGrid.setVgap(5);
        betCardGrid.setAlignment(Pos.CENTER);

        for (int i = 1; i <= 80; i++) {
            Button spot = new Button(String.valueOf(i));
            spot.setDisable(true); // enable later after spot selection
            //spot.setOnAction(e -> selectNumber(spot));
            spot.setMinSize(40, 40);
            spot.setStyle(
                "-fx-background-radius: 50;" + 
                "-fx-border-radius: 50;" +  
                "-fx-background-color: #a5a5a5ff;" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;"
            );

            betCardGrid.add(spot, (i - 1) % 10, (i - 1) / 10);
        }

        Label selectSpotsLabel = new Label("Select Your Spots");

        selectSpotsLabel.setStyle(
            "-fx-background-color: #6A6A80;" + 
            "-fx-text-fill: white;" +          
            "-fx-font-size: 20px;" +           
            "-fx-font-weight: bold;" +         
            "-fx-background-radius: 20;" +     
            "-fx-padding: 10px 30px 10px 30px;"
        );

        VBox layout = new VBox(10, selectSpotsLabel, betCardGrid);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle(
            "-fx-padding: 30 0 0 0;"
        );

        BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #ecececff;"); 
		borderPane.setPadding(new Insets(10));
        borderPane.setTop(menuBar);
        borderPane.setCenter(layout);

        scene = new Scene(borderPane, 700,900);
    }

    public Scene getScene() {
        return scene;
    }
}