import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameScene {
    private Scene scene;
    //private KenoGame game;
    private GridPane betCardGrid;
    private GridPane drawCost;
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

        Button spotsButton = new Button("1, 4, 8, or 10 Spots");
        Button multiButton = new Button("Multiplier");

        HBox firstRow = new HBox(10, spotsButton, multiButton);

        drawCost = new GridPane();
        drawCost.setHgap(5);
        drawCost.setVgap(5);
        drawCost.setAlignment(Pos.CENTER);

        Label showDrawLabel = new Label("Showing draw:");
        Label costLabel = new Label("Cost:");
        Label showDrawValueLabel = new Label("-");
        Label costValueLabel = new Label("-");

        drawCost.add(showDrawLabel, 1, 0);
        drawCost.add(costLabel, 2, 0);
        drawCost.add(showDrawValueLabel, 1, 1);
        drawCost.add(costValueLabel, 2, 1);

        // ---- IMAGE ----
		Image formPNG = new Image("https://static.thenounproject.com/png/25603-200.png"); 
		ImageView formPNGView = new ImageView(formPNG);
		formPNGView.setFitHeight(30);
		formPNGView.setFitWidth(30);
		formPNGView.setPreserveRatio(true);
        Button enterTicketButton = new Button("Enter Ticket");
        //Styling for button?

        HBox ticketBox = new HBox(10, formPNGView, enterTicketButton);
        // ticketBox.setAlignment(Pos.CENTER_LEFT);
        // ticketBox.setPadding(new Insets(10));

        HBox secondRow = new HBox(10, drawCost, ticketBox);

        Button watchDrawLabel = new Button("Watch Drawings");
        Button skipButton = new Button("Skip to Results");
        HBox thirdRow = new HBox(10, watchDrawLabel, skipButton);

        VBox miniMenu = new VBox(10, firstRow, secondRow, thirdRow);
        miniMenu.setStyle(
            "-fx-padding: 0 0 50 0;"
        );
        miniMenu.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #ecececff;"); 
		borderPane.setPadding(new Insets(10));
        borderPane.setTop(menuBar);
        borderPane.setCenter(layout);
        borderPane.setBottom(miniMenu);

        scene = new Scene(borderPane, 700,900);
    }

    public Scene getScene() {
        return scene;
    }
}