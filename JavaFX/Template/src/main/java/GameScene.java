import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        // ---- Bottom mini menu ----
        ComboBox<String> spotsBox = new ComboBox<>(FXCollections.observableArrayList("1", "4", "8", "10"));
        spotsBox.setPromptText("1, 4, 8, or 10 Spots");

        spotsBox.setOnAction(event -> {
            System.out.println("Selected: " + spotsBox.getValue());
        });
        spotsBox.setStyle(
            "-fx-background-color: white;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 12px;" + 
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 25;" + 
            "-fx-border-color: #A0A0A0;" + 
            "-fx-border-width: 1;" +
            "-fx-border-radius: 25;" +
            "-fx-padding: 10 20 10 20;"
        );

        Button multiButton = new Button("Multiplier");
        multiButton.setStyle(
            "-fx-background-color: white;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 14px;" + 
            "-fx-font-weight: normal;" +
            "-fx-background-radius: 20;" +
            "-fx-border-color: #C0C0C0;" + 
            "-fx-border-width: 1;" +
            "-fx-border-radius: 20;" +
            "-fx-padding: 5 15 5 15;"
        );

        HBox firstRow = new HBox(10, spotsBox, multiButton);

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
        enterTicketButton.setStyle(
            "-fx-background-color: #F0E68C;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" + 
            "-fx-padding: 10 10 10 10;"
        );

        HBox ticketBox = new HBox(10, formPNGView, enterTicketButton);

        HBox secondRow = new HBox(10, drawCost, ticketBox);

        Button watchDrawLabel = new Button("Watch Drawings");
        watchDrawLabel.setStyle(
            "-fx-background-color: #D3D3D3;" +
            "-fx-text-fill: black;" + 
            "-fx-font-size: 14px;" +
            "-fx-font-weight: normal;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 5 10 5 10;"
        );
        Button skipButton = new Button("Skip to Results");
        skipButton.setStyle(
            "-fx-background-color: transparent;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 14px;" +
            "-fx-font-weight: normal;" +
            "-fx-underline: false;"
        );
        HBox thirdRow = new HBox(10, watchDrawLabel, skipButton);

        firstRow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        thirdRow.setAlignment(Pos.CENTER);

        VBox miniMenu = new VBox(10, firstRow, secondRow, thirdRow);
        miniMenu.setAlignment(Pos.TOP_CENTER);
        miniMenu.setStyle(
            "-fx-padding: 20 0 0 0;"
        );

        VBox layout = new VBox(10, selectSpotsLabel, betCardGrid, miniMenu);
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