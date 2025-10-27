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
import java.util.ArrayList;

public class GameScene {
    private Scene scene;
    private KenoGame game;
    private GridPane betCardGrid;
    private GridPane costGrid;
    private Bet currentBet;
    private int spotsSelected;
    private int numDrawings = 1;
    private int selectedSpots = 0;
    private int drawings = 0;

    public GameScene(Stage stage) {
        stage.setTitle("Keno Game");
        game = new KenoGame();

        // ---- MENU ----
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");

        MenuItem rules = new MenuItem("Rules");
        rules.setOnAction(e -> Rules.showRules());

        MenuItem odds = new MenuItem("Odds");
        odds.setOnAction(e -> Odds.showOdds());

        MenuItem newLook = new MenuItem("New Look");

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> stage.close());

        menu.getItems().addAll(rules, odds, newLook, exit);
        menuBar.getMenus().add(menu);

        Label selectSpotsLabel = new Label("Select Your Spots");
        selectSpotsLabel.setStyle(
            "-fx-background-color: #6A6A80;" + 
            "-fx-text-fill: white;" +          
            "-fx-font-size: 20px;" +           
            "-fx-font-weight: bold;" +         
            "-fx-background-radius: 20;" +     
            "-fx-padding: 10px 30px 10px 30px;"
        );

        // ---- COST GRID ----
        costGrid = new GridPane();
        costGrid.setHgap(5);
        costGrid.setVgap(5);
        costGrid.setAlignment(Pos.CENTER);

        Label costLabel = new Label("Cost:");
        Label costValueLabel = new Label("-");

        costGrid.add(costLabel, 1, 0);
        costGrid.add(costValueLabel, 1, 1);

        // ---- IMAGE ----
        Image formPNG = new Image("https://static.thenounproject.com/png/25603-200.png"); 
        ImageView formPNGView = new ImageView(formPNG);
        formPNGView.setFitHeight(30);
        formPNGView.setFitWidth(30);
        formPNGView.setPreserveRatio(true);

        // ---- ENTER TICKET BUTTON ----
        Button enterTicketButton = new Button("Enter Ticket");
        enterTicketButton.setDisable(true);
        enterTicketButton.setStyle(
            "-fx-background-color: #F0E68C;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" + 
            "-fx-padding: 10 10 10 10;"
        );

        // ---- CONTINUE BUTTON ----
        Button continueButton = new Button("Continue");
        continueButton.setDisable(true); 
        continueButton.setStyle(
            "-fx-background-color: #87CEEB;" +
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 10 10 10 10;"
        );

        // ---- DRAW AND SPOTS COMBOBOX ----
        ComboBox<String> drawBox = new ComboBox<>(FXCollections.observableArrayList("1", "2", "3", "4"));
        drawBox.setPromptText("Draws");

        ComboBox<String> spotsBox = new ComboBox<>(FXCollections.observableArrayList("1", "4", "8", "10"));
        spotsBox.setPromptText("1, 4, 8, or 10 Spots");

        // ---- BUTTON ACTIONS ----
        enterTicketButton.setOnAction(e -> {
            drawings = 1;
            ArrayList<Integer> draw = game.generateDrawing();
            ArrayList<Integer> matches = game.getMatches(draw);
            highlightDraw(draw, matches);

            int winnings = game.calculateWinnings(matches.size());
            System.out.println("Winnings:" + winnings);

            // Multi-draw: enable Continue, disable Enter Ticket and selections
            if (numDrawings >= 2 && numDrawings <= 4) {
                continueButton.setDisable(false);
                enterTicketButton.setDisable(true);
                spotsBox.setDisable(true);
                drawBox.setDisable(true);
                disableBetCard(true);
            } else {
                continueButton.setDisable(true);
                enterTicketButton.setDisable(true);
            }
        });

        continueButton.setOnAction(e -> {
            if (currentBet != null && drawings < numDrawings) {
                ArrayList<Integer> draw = game.generateDrawing();
                ArrayList<Integer> matches = game.getMatches(draw);
                highlightDraw(draw, matches);
                drawings++;

                int winnings = game.calculateWinnings(matches.size());
                System.out.println("Winnings from draw " + drawings + ": " + winnings);

                if (drawings >= numDrawings) {
                    continueButton.setDisable(true);
                    enterTicketButton.setDisable(false);
                    spotsBox.setDisable(false);
                    drawBox.setDisable(false);
                    disableBetCard(false);
                }
            }
        });

        // ---- SPOTS AND DRAWS SELECTION ----
        spotsBox.setOnAction(event -> handleSelection(spotsBox, drawBox, enterTicketButton));
        drawBox.setOnAction(event -> handleSelection(spotsBox, drawBox, enterTicketButton));

        // ---- LAYOUT ----
        HBox ticketBox = new HBox(10, formPNGView, enterTicketButton, continueButton);
        HBox secondRow = new HBox(10, costGrid, ticketBox);
        secondRow.setAlignment(Pos.CENTER);

        HBox firstRow = new HBox(10, spotsBox, drawBox);
        firstRow.setAlignment(Pos.CENTER);

        VBox miniMenu = new VBox(10, firstRow, secondRow);
        miniMenu.setAlignment(Pos.TOP_CENTER);
        miniMenu.setStyle("-fx-padding: 20 0 0 0;");

        // ---- BET CARD GRID ----
        betCardGrid = new GridPane();
        betCardGrid.setHgap(5);
        betCardGrid.setVgap(5);
        betCardGrid.setAlignment(Pos.CENTER);

        for (int i = 1; i <= 80; i++) {
            Button spot = new Button(String.valueOf(i));
            final int number = i;
            spot.setDisable(true);

            spot.setOnAction(e -> {
                if (currentBet != null) {
                    if (currentBet.getChosenNumbers().contains(number)) {
                        currentBet.removeNumber(number);
                        spot.setStyle("-fx-background-radius: 50;-fx-border-radius: 50;-fx-background-color: #a5a5a5ff;-fx-text-fill: black;-fx-font-weight: bold;");
                        selectedSpots--;
                    } else if (currentBet.addNumber(number)) {
                        spot.setStyle("-fx-background-radius: 50;-fx-border-radius: 50;-fx-background-color: #2bff00ff;-fx-text-fill: black;-fx-font-weight: bold;");
                        selectedSpots++;
                    }
                    enterTicketButton.setDisable(selectedSpots != spotsSelected);
                }
            });

            spot.setMinSize(40, 40);
            spot.setStyle("-fx-background-radius: 50;-fx-border-radius: 50;-fx-background-color: #a5a5a5ff;-fx-text-fill: black;-fx-font-weight: bold;");
            betCardGrid.add(spot, (i - 1) % 10, (i - 1) / 10);
        }

        VBox layout = new VBox(10, selectSpotsLabel, betCardGrid, miniMenu);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-padding: 30 0 0 0;");

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #ecececff;"); 
        borderPane.setPadding(new Insets(10));
        borderPane.setTop(menuBar);
        borderPane.setCenter(layout);

        scene = new Scene(borderPane, 700, 900);
    }

    // ---- HANDLE SELECTION OF SPOTS AND DRAWS ----
    private void handleSelection(ComboBox<String> spotsBox, ComboBox<String> drawBox, Button enterTicketButton) {
        if (spotsBox.getValue() != null && drawBox.getValue() != null) {
            spotsSelected = Integer.parseInt(spotsBox.getValue());
            numDrawings = Integer.parseInt(drawBox.getValue());
            currentBet = new Bet(spotsSelected, numDrawings);
            game.setBet(currentBet);
            enableBetCard();
            resetBetCard();
            enterTicketButton.setDisable(true);
            selectedSpots = 0;
            drawings = 0;
        }
    }

    // ---- HIGHLIGHT DRAW ----
    private void highlightDraw(ArrayList<Integer> draw, ArrayList<Integer> matches) {
        resetBetCard();
        int index = 1;
        for (var node : betCardGrid.getChildren()) {
            if (node instanceof Button) {
                Button spot = (Button) node;
                if (draw.contains(index)) {
                    if (matches.contains(index)) {
                        spot.setStyle("-fx-background-radius: 50;-fx-border-radius: 50;-fx-background-color: #2bff00ff;-fx-text-fill: black;-fx-font-weight: bold;-fx-border-color: #FFD700;-fx-border-width: 3px;");
                    } else {
                        spot.setStyle("-fx-background-radius: 50;-fx-border-radius: 50;-fx-background-color: #a5a5a5ff;-fx-text-fill: black;-fx-font-weight: bold;-fx-border-color: #FFD700;-fx-border-width: 3px;");
                    }
                }
                index++;
            }
        }
    }

    private void resetBetCard() {
        for (var node : betCardGrid.getChildren()) {
            if (node instanceof Button) {
                Button spot = (Button) node;
                int number = Integer.parseInt(spot.getText());
                if (currentBet.getChosenNumbers().contains(number)) {
                    spot.setStyle("-fx-background-radius: 50;-fx-border-radius: 50;-fx-background-color: #2bff00ff;-fx-text-fill: black;-fx-font-weight: bold;");
                } else {
                    spot.setStyle("-fx-background-radius: 50;-fx-border-radius: 50;-fx-background-color: #a5a5a5ff;-fx-text-fill: black;-fx-font-weight: bold;");
                }
            }
        }
    }

    private void enableBetCard() {
        for (var node : betCardGrid.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(false);
            }
        }
    }

    private void disableBetCard(boolean disable) {
        for (var node : betCardGrid.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(disable);
            }
        }
    }

    public Scene getScene() {
        return scene;
    }
}
