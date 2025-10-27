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
import javafx.util.Duration;
import java.util.Random;
import java.util.Collections;

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
        //newLookItem.setOnAction(e -> applyNewLook());

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
                    //Delay for a second
                    Duration.seconds(1);
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
        HBox secondRow = new HBox(10, autoPickButton, costGrid, ticketBox);

        // ---- DRAW BUTTON ----
        ComboBox<String> drawBox = new ComboBox<>(FXCollections.observableArrayList("1", "2", "3", "4"));
        drawBox.setPromptText("Draws");
        drawBox.setOnAction(event -> {
            if (drawBox.getValue() != null) {
                numDrawings = Integer.parseInt(drawBox.getValue());
            }
        });

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

        // ---- Bottom mini menu ----
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
        spotsBox.setOnAction(event -> {
            String selected = spotsBox.getValue();
            if (spotsBox.getValue() != null && drawBox.getValue() != null) {
                spotsSelected = Integer.parseInt(selected);
                currentBet = new Bet(spotsSelected, numDrawings);
                game.setBet(currentBet);
                System.out.println("Selected: " + spotsBox.getValue());
                enableBetCard();
                resetBetCard();
                enterTicketButton.setDisable(true);
                autoPickButton.setDisable(false);
                selectedSpots = 0;
                drawings = 0;
            }
        });

        drawBox.setOnAction(event -> {
            String selected = spotsBox.getValue();
            if (spotsBox.getValue() != null && drawBox.getValue() != null) {
                spotsSelected = Integer.parseInt(selected);
                currentBet = new Bet(spotsSelected, numDrawings);
                game.setBet(currentBet);
                System.out.println("Selected: " + spotsBox.getValue());
                enableBetCard();
                resetBetCard();
                enterTicketButton.setDisable(true);
                autoPickButton.setDisable(false);
                selectedSpots = 0;
                drawings = 0;
            }
        });

        HBox firstRow = new HBox(10, spotsBox, drawBox);

        firstRow.setAlignment(Pos.CENTER);
        secondRow.setAlignment(Pos.CENTER);
        // thirdRow.setAlignment(Pos.CENTER);

        VBox miniMenu = new VBox(10, firstRow, secondRow);
        miniMenu.setAlignment(Pos.TOP_CENTER);
        miniMenu.setStyle(
            "-fx-padding: 20 0 0 0;"
        );

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
                        selectedSpots--;
                        
                        //If selected numbers don't match up with drawings number, then don't enable "enter ticket"
                        drawings = 0;
                        if(spotsBox.getValue() != null){
                            drawings = Integer.parseInt(spotsBox.getValue());
                        }
                        
                        
                        if(selectedSpots == drawings){
                            enterTicketButton.setDisable(false);
                        }
                        else{
                            enterTicketButton.setDisable(true);
                        }
                        System.out.println("Selected total" + selectedSpots + "Set Drawings" + drawings);
                    } else if (currentBet.addNumber(number)) {
                        spot.setStyle("-fx-background-radius: 50;" + 
                                        "-fx-border-radius: 50;" +  
                                        "-fx-background-color: #2bff00ff;" +
                                        "-fx-text-fill: black;" +
                                        "-fx-font-weight: bold;");
                        selectedSpots++;
                        //If selected numbers don't match up with drawings number, then don't enable "enter ticket"
                        drawings = 0;
                        if(spotsBox.getValue() != null){
                            drawings = Integer.parseInt(spotsBox.getValue());
                        }
                        
                        
                        if(selectedSpots == drawings){
                            enterTicketButton.setDisable(false);
                        }
                        else{
                            enterTicketButton.setDisable(true);
                        }
                        System.out.println("Selected total" + selectedSpots + "Set Drawings" + drawings);
                    }
                }
            });

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