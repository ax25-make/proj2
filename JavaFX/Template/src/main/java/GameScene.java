import java.util.ArrayList;

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
    private KenoGame game;
    private GridPane betCardGrid;
    private GridPane drawCost;
    private Bet currentBet;
    private int spotsSelected;
    private Stage stage;

    


    public GameScene(Stage stage) {
        this.stage = stage;
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
            final int number = i;

            spot.setDisable(true);

            spot.setOnAction(e -> {
                if (currentBet != null) {
                    if (currentBet.getChosenNumbers().contains(number)) {
                        currentBet.removeNumber(number);
                        spot.setStyle("-fx-background-radius: 50;" + 
                                        "-fx-border-radius: 50;" +  
                                        "-fx-background-color: #a5a5a5ff;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-font-weight: bold;");
                    } else if (currentBet.addNumber(number)) {
                        spot.setStyle("-fx-background-radius: 50;" + 
                                        "-fx-border-radius: 50;" +  
                                        "-fx-background-color: #2bff00ff;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-font-weight: bold;");
                    }
                }
            });

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

        // ---- DRAW & COST GRID ----
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

        // ---- DOG IMAGE ----
        Image dogPNG = new Image("https://i.redd.it/elgmhuor118f1.png"); 
        ImageView dogPNGView = new ImageView(dogPNG);
        dogPNGView.setFitHeight(30);
        dogPNGView.setFitWidth(30);
        dogPNGView.setPreserveRatio(true);

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
        enterTicketButton.setOnAction(e -> {
            ArrayList<Integer> draw = game.generateDrawing();
            ArrayList<Integer> matches = game.getMatches(draw);

            for(int num : draw){
                System.out.println("From matches: " + num);
            }
            int index = 1;
            for (var node : betCardGrid.getChildren()) {
                if (draw.contains(index)) {
                    if (matches.contains(index)){
                        node.setStyle("-fx-background-radius: 50;" + 
                                        "-fx-border-radius: 50;" +  
                                        "-fx-background-color: #2bff00ff;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-font-weight: bold;"+
                                        "-fx-border-color: #FFD700;" +
                                        "-fx-border-width: 3px;");
                    }
                    else{
                        node.setStyle("-fx-background-radius: 50;" + 
                                        "-fx-border-radius: 50;" +  
                                        "-fx-background-color: #a5a5a5ff;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-border-color: #FFD700;" + 
                                        "-fx-border-width: 3px;");
                    }
                }
                index++;
            }

            // Calculate winnings
            int winnings = game.calculateWinnings(matches.size());
            System.out.println("Winnings:" + winnings);
        });
        // ---- Click Effect ---- ^
		enterTicketButton.setOnMouseReleased(e -> enterTicketButton.setStyle(
			"-fx-background-color: #F0E68C;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" + 
            "-fx-padding: 10 10 10 10;"
		));

		enterTicketButton.setOnMousePressed(e -> enterTicketButton.setStyle(
			"-fx-background-color: #9e964eff;" + 
            "-fx-text-fill: black;" + 
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" + 
            "-fx-padding: 10 10 10 10;"
		));	


        HBox ticketBox = new HBox(10, formPNGView, enterTicketButton);

        HBox secondRow = new HBox(10, drawCost, ticketBox);

        // ---- Bottom mini menu ----
        ComboBox<String> spotsBox = new ComboBox<>(FXCollections.observableArrayList("1", "4", "8", "10"));
        spotsBox.setPromptText("1, 4, 8, or 10 Spots");
        spotsBox.setOnAction(event -> {
            String selected = spotsBox.getValue();
            if (selected != null) {
                spotsSelected = Integer.parseInt(selected);
                currentBet = new Bet(spotsSelected, 1);
                game.setBet(currentBet);
                System.out.println("Selected: " + spotsBox.getValue());
                enableBetCard();
                resetBetCard();
                enterTicketButton.setDisable(false);
            }
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

        // ---- DRAW BUTTON ----
        Button drawButton = new Button("Draw");
        drawButton.setStyle(
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

        HBox firstRow = new HBox(10, spotsBox, drawButton);

        // Button watchDrawLabel = new Button("Watch Drawings");
        // watchDrawLabel.setStyle(
        //     "-fx-background-color: #D3D3D3;" +
        //     "-fx-text-fill: black;" + 
        //     "-fx-font-size: 14px;" +
        //     "-fx-font-weight: normal;" +
        //     "-fx-background-radius: 5;" +
        //     "-fx-padding: 5 10 5 10;"
        // );
        // Button skipButton = new Button("Skip to Results");
        // skipButton.setStyle(
        //     "-fx-background-color: #D3D3D3;" +
        //     "-fx-text-fill: black;" + 
        //     "-fx-font-size: 14px;" +
        //     "-fx-font-weight: normal;" +
        //     "-fx-background-radius: 5;" +
        //     "-fx-padding: 5 10 5 10;"
        // );
        // HBox thirdRow = new HBox(10, skipButton);

        firstRow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        // thirdRow.setAlignment(Pos.CENTER);

        VBox miniMenu = new VBox(10, firstRow, secondRow);
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

    private void resetBetCard() {
        for (var node : betCardGrid.getChildren()) {
            if (node instanceof Button) {
                Button spot = (Button) node;
                int number = Integer.parseInt(spot.getText());
                if (currentBet.getChosenNumbers().contains(number)) {
                    spot.setStyle("-fx-background-radius: 50;" + 
                                        "-fx-border-radius: 50;" +  
                                        "-fx-background-color: #2bff00ff;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-font-weight: bold;");
                } else {
                    spot.setStyle("-fx-background-radius: 50;" + 
                                        "-fx-border-radius: 50;" +  
                                        "-fx-background-color: #a5a5a5ff;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-font-weight: bold;");
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

    public Scene getScene() {
        return scene;
    }
}