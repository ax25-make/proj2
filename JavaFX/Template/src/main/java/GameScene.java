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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


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
    private ArrayList<Integer> autoNumbers = new ArrayList<>();
    private Random random = new Random();
    private ArrayList<Integer> randomDraw = new ArrayList<>(autoNumbers.subList(0, spotsSelected));
    private BorderPane borderPane;
    private String winningString;
    private String matchString;
    private Label winningsLabel;
    private Label matchLabel;

    private int playerBalance = 10;  // starting money
    private int ticketCost = 1;       // cost per draw
    private Label balanceLabel;

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
        newLook.setOnAction(e -> {
        // Toggle between two themes: light and dark
        String currentStyle = borderPane.getStyle();

        if (currentStyle.contains("#ececec")) {
            // Switch to dark mode
            borderPane.setStyle("-fx-background-color: #2e2e2e;");
            betCardGrid.setStyle("-fx-background-color: #3a3a3a;");
        } else {
            // Switch back to light mode
            borderPane.setStyle("-fx-background-color: #ecececff;");
            betCardGrid.setStyle("-fx-background-color: transparent;");
        }
    });

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

        // ---- AUTO PICK ----
        Button autoPickButton = new Button("Auto Pick");
        autoPickButton.setDisable(true);

        autoPickButton.setStyle(
            "-fx-background-color: #D3D3D3;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 16px;" + 
            "-fx-font-weight: normal;" + 
            "-fx-background-radius: 3;" +
            "-fx-padding: 8 15 8 15;"
        );
        // ---- Click Effect ---- ^
		autoPickButton.setOnMouseReleased(e -> autoPickButton.setStyle(
			"-fx-background-color: #D3D3D3;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 16px;" + 
            "-fx-font-weight: normal;" + 
            "-fx-background-radius: 3;" +
            "-fx-padding: 8 15 8 15;"
		));
		autoPickButton.setOnMousePressed(e -> autoPickButton.setStyle(
			"-fx-background-color: #B0B0B0;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 16px;" + 
            "-fx-font-weight: normal;" + 
            "-fx-background-radius: 3;" +
            "-fx-padding: 8 15 8 15;"
		));	

        // ---- COST GRID ----
        costGrid = new GridPane();
        costGrid.setHgap(5);
        costGrid.setVgap(5);
        costGrid.setAlignment(Pos.CENTER);

        Label costLabel = new Label("Ticket Cost:");
        Label costValueLabel = new Label("$" + ticketCost);

        Label balanceTextLabel = new Label("Balance:");
        balanceLabel = new Label("$" + playerBalance);

        costGrid.add(costLabel, 0, 0);
        costGrid.add(costValueLabel, 1, 0);
        costGrid.add(balanceTextLabel, 0, 1);
        costGrid.add(balanceLabel, 1, 1);

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

        // ---- NEW TICKET BUTTON ----
        Button newTicketButton = new Button("New Ticket");
        newTicketButton.setDisable(true);
        newTicketButton.setStyle(
            "-fx-background-color: #fc0000ff;" +
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 10 10 10 10;"
        );


        // ---- ENABLE AUTOPICK ----
        autoPickButton.setOnAction(event -> {
            currentBet.reset();
            resetBetCard();
            
            autoNumbers = new ArrayList<>();

            for (int x = 1; x <= 80; x++) {
                autoNumbers.add(x);
            }
            Collections.shuffle(autoNumbers, random);
            randomDraw = new ArrayList<>(autoNumbers.subList(0, spotsSelected));
            Collections.sort(randomDraw);

            int index = 1;
            selectedSpots = spotsSelected;
            enterTicketButton.setDisable(false);
            for (var node : betCardGrid.getChildren()) {
                if (randomDraw.contains(index)) {
                    node.setStyle("-fx-background-radius: 50;" + 
                                    "-fx-border-radius: 50;" +  
                                    "-fx-background-color: #2bff00ff;" +
                                    "-fx-text-fill: black;" +
                                    "-fx-font-weight: bold;");
                    currentBet.addNumber(index);
                }
                
                index++;
            }

        });

        // ---- DRAW AND SPOTS COMBOBOX ----
        ComboBox<String> drawBox = new ComboBox<>(FXCollections.observableArrayList("1", "2", "3", "4"));
        drawBox.setPromptText("Draws");
        drawBox.setStyle(
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

        ComboBox<String> spotsBox = new ComboBox<>(FXCollections.observableArrayList("1", "4", "8", "10"));
        spotsBox.setPromptText("1, 4, 8, or 10 Spots");
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

        

        // ---- DOG IMAGE ----
        Image dogPNG = new Image("https://preview.redd.it/familiar-friday-v0-elgmhuor118f1.png?width=320&crop=smart&auto=webp&s=0c017a56a5bfeb9a2a5363e2d83f124a7ac6ef2b"); 
        ImageView dogPNGView = new ImageView(dogPNG);
        dogPNGView.setFitHeight(100);
        dogPNGView.setFitWidth(100);
        dogPNGView.setPreserveRatio(true);

        matchLabel = new Label("No Spots Matched");
        matchLabel.setStyle(
            "-fx-text-fill: black;" + 
            "-fx-font-size: 18px;" +
            "-fx-font-weight: normal;"
        );

        winningString = "Ticket Winnings:\n$0";
        winningsLabel = new Label(winningString);

        winningsLabel.setStyle(
            "-fx-background-color: white;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 20px;" + 
            "-fx-font-weight: bold;" + 
            "-fx-background-radius: 50;" + 
            "-fx-padding: 15px 30px 15px 30px;"
        );
        winningsLabel.setTextAlignment(TextAlignment.CENTER);

        VBox resultsBox = new VBox(10, dogPNGView, matchLabel, winningsLabel, newTicketButton);
        resultsBox.setStyle(
            "-fx-background-color: #E0E0E0;" + 
            "-fx-background-radius: 20;" +
            "-fx-padding: 15px;" 
        );
        resultsBox.setAlignment(Pos.CENTER);
        Button resultsButton = new Button("View Results");
        resultsButton.setDisable(true);
        resultsButton.setStyle(
            "-fx-background-color: #87eb8cff;" +
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 10 10 10 10;"
        );

        


        // ---- SPOTS AND DRAWS SELECTION ----
        spotsBox.setOnAction(event -> handleSelection(spotsBox, drawBox, enterTicketButton, autoPickButton));
        drawBox.setOnAction(event -> handleSelection(spotsBox, drawBox, enterTicketButton, autoPickButton));

        // ---- LAYOUT ----
        HBox ticketBox = new HBox(10, formPNGView, enterTicketButton);
        HBox secondRow = new HBox(10, autoPickButton, costGrid, ticketBox); 
        HBox thirdRow = new HBox(10, continueButton, resultsButton);
        secondRow.setAlignment(Pos.CENTER);
        thirdRow.setAlignment(Pos.CENTER);

        HBox firstRow = new HBox(10, spotsBox, drawBox);
        firstRow.setAlignment(Pos.CENTER);

        VBox miniMenu = new VBox(10, firstRow, secondRow, thirdRow);
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
        disableBetCard(true);

        StackPane centerSwapPane = new StackPane(betCardGrid);
        
        VBox layout = new VBox(10, selectSpotsLabel, centerSwapPane, miniMenu);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-padding: 30 0 0 0;");

        borderPane = new BorderPane(); 
        borderPane.setStyle("-fx-background-color: #ecececff;");
        borderPane.setPadding(new Insets(10));
        borderPane.setTop(menuBar);
        borderPane.setCenter(layout);

        //Change results screen
        resultsButton.setOnAction(event -> {
            centerSwapPane.getChildren().setAll(resultsBox);
            //Labu
            int totalWinnings = game.getTotalWinnings();
            if(totalWinnings == 0){
                matchString = "No Spots Matched";
                winningString = "Ticket Winnings:\n$" + totalWinnings;
            }
            else{
                matchString = "CONGRATULATIONS! YOU WON! -Alex Xu";
                winningString = "Ticket Winnings:\n$" + totalWinnings;
            }
            matchLabel.setText(matchString);
            winningsLabel.setText(winningString);

            
        });

        scene = new Scene(borderPane, 700, 900);

        // ---- BUTTON ACTIONS ----
        enterTicketButton.setOnAction(e -> {
            // Deduct cost once when starting draws
            playerBalance -= ticketCost;
            if (playerBalance < 0) playerBalance = 0;
            balanceLabel.setText("$" + playerBalance);

            // Check if player is out of money
            if (playerBalance <= 0) {
                disableAllGameplay();
                matchLabel.setText("You’re out of money!");
                winningsLabel.setText("Game Over");
                resultsButton.setDisable(false);
                return;
            }

            drawings = 1;
            ArrayList<Integer> draw = game.generateDrawing();
            ArrayList<Integer> matches = game.getMatches(draw);
            highlightDraw(draw, matches);

            int winnings = game.calculateWinnings(matches.size());
            playerBalance += winnings;
            balanceLabel.setText("$" + playerBalance);

            System.out.println("Winnings:" + winnings);
            System.out.println("Total Winnings: " + game.getTotalWinnings());

            if (numDrawings == 1) {
                resultsButton.setDisable(false);
                newTicketButton.setDisable(false);
                autoPickButton.setDisable(true);
                enterTicketButton.setDisable(true);
                spotsBox.setDisable(false);
                drawBox.setDisable(false);
                disableBetCard(true);
            } else if (numDrawings >= 2 && numDrawings <= 4) {
                continueButton.setDisable(false);
                resultsButton.setDisable(true);
                enterTicketButton.setDisable(true);
                autoPickButton.setDisable(true);
                spotsBox.setDisable(true);
                drawBox.setDisable(true);
                disableBetCard(true);
            }

            // Check for loss after winnings
            if (playerBalance <= 0) {
                disableAllGameplay();
                matchLabel.setText("You’re out of money!");
                winningsLabel.setText("Game Over");
                resultsButton.setDisable(false);
            }
        });

        continueButton.setOnAction(e -> {
            if (currentBet != null && drawings < numDrawings) {
                // Check if player has enough money for next draw
                if (playerBalance < ticketCost) {
                    disableAllGameplay();
                    matchLabel.setText("Not enough money to continue!");
                    winningsLabel.setText("Game Over");
                    continueButton.setDisable(true);
                    newTicketButton.setDisable(false);
                    resultsButton.setDisable(false);
                    return;
                }

                // Deduct ticket cost before performing draw
                playerBalance -= ticketCost;
                balanceLabel.setText("$" + playerBalance);

                // Perform the draw
                ArrayList<Integer> draw = game.generateDrawing();
                ArrayList<Integer> matches = game.getMatches(draw);
                highlightDraw(draw, matches);
                drawings++;

                // Calculate winnings and add to balance
                int winnings = game.calculateWinnings(matches.size());
                playerBalance += winnings;
                balanceLabel.setText("$" + playerBalance);

                System.out.println("Winnings from draw " + drawings + ": " + winnings);
                System.out.println("Total Winnings: " + game.getTotalWinnings());

                // Disable Continue if final draw reached
                if (drawings >= numDrawings) {
                    continueButton.setDisable(true);
                    newTicketButton.setDisable(false);
                    resultsButton.setDisable(false);
                }
            }
        });

        newTicketButton.setOnAction(e -> {
            // Reset selections
            spotsBox.setValue(null);
            drawBox.setValue(null);

            // Reset bet and spots
            // Reset current bet and selected spots
            selectedSpots = 0;
            drawings = 0;
            spotsSelected = 0;
            numDrawings = 1;

            spotsBox.setDisable(false);
            drawBox.setDisable(false);
            resultsButton.setDisable(true);

            for (var node : betCardGrid.getChildren()) {
                if (node instanceof Button) {
                    Button spot = (Button) node;
                    spot.setStyle("-fx-background-radius: 50;-fx-border-radius: 50;-fx-background-color: #a5a5a5ff;-fx-text-fill: black;-fx-font-weight: bold;");
                }
            }
            
            newTicketButton.setDisable(true);
            centerSwapPane.getChildren().setAll(betCardGrid);
        });
    }

    // ---- HANDLE SELECTION OF SPOTS AND DRAWS ----
    private void handleSelection(ComboBox<String> spotsBox, ComboBox<String> drawBox, Button enterTicketButton, Button autoPickButton) {
        if (spotsBox.getValue() != null && drawBox.getValue() != null) {
            spotsSelected = Integer.parseInt(spotsBox.getValue());
            numDrawings = Integer.parseInt(drawBox.getValue());
            ticketCost = 1;
            ((Label) costGrid.getChildren().get(1)).setText("$" + ticketCost);
            currentBet = new Bet(spotsSelected, numDrawings);
            game.setBet(currentBet);
            enableBetCard();
            resetBetCard();
            enterTicketButton.setDisable(true);
            autoPickButton.setDisable(false);
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
                node.setMouseTransparent(false); 
                node.setFocusTraversable(true); 
            }
        }
    }

    private void disableBetCard(boolean disable) {
        for (var node : betCardGrid.getChildren()) {
            if (node instanceof Button) {
                node.setMouseTransparent(disable); 
                node.setFocusTraversable(!disable); 
            }
        }
    }
    
    private void disableAllGameplay() {
        disableBetCard(true);
    }


    public Scene getScene() {
        return scene;
    }
}
