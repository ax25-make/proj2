import javafx.scene.control.Alert;

public class Rules {

	public static void showRules() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Keno Rules");
        alert.setHeaderText("Keno Game Rules");
        alert.setContentText("1. The Game Board and Numbers\n" + //
                        "The Board: The Keno board contains 80 unique numbers, usually arranged in a grid.\n" + //
                        "\n" + //
                        "Player's Goal: The player's goal is to correctly predict which of these numbers will be randomly drawn.\n" + //
                        "\n" + //
                        "2. Marking Your Ticket (Picking Spots)\n" + //
                        "Spots: The numbers you select are called \"spots.\"\n" + //
                        "\n" + //
                        "Selection: The player chooses a set of spots, between 1 and 10, by marking them electronically.\n" + //
                        "\n" + //
                        "Note: The number of spots picked directly affects the potential payout.\n" + //
                        "\n" + //
                        "Wager: The player places a monetary bet (wager) on the selected numbers.\n" + //
                        "\n" + //
                        "3. The Draw\n" + //
                        "Drawing: After all bets are placed, 20 numbers are randomly drawn. These are often referred to as the \"winning numbers\" or \"called numbers.\"\n" + //
                        "\n" + //
                        "4. Determining a Win (The Payout)\n" + //
                        "Catching Numbers: A \"catch\" is when one of your selected spots matches one of the 20 drawn numbers.\n" + //
                        "\n" + //
                        "Winning: To win, a player must \"catch\" a minimum number of their selected spots.\n" + //
                        "\n" + //
                        "Payouts: Payouts are based on a payout table that dictates how much is won based on:\n" + //
                        "\n" + //
                        "The total number of spots the player chose (e.g., a \"6-Spot\" ticket).\n" + //
                        "\n" + //
                        "The number of spots the player caught (e.g., catching 4 out of 6 spots).\n" + //
                        "\n" + //
                        "The amount of the initial wager.");
        alert.showAndWait();
	}

	// //feel free to remove the starter code from this method
	// @Override
	// public void start(Stage primaryStage) throws Exception {
	// 	// TODO Auto-generated method stub
	// 	primaryStage.setTitle("Rules Page");

    //     Label rulesTitle = new Label("Rules");
    //     rulesTitle.setStyle(
    //     "-fx-text-fill: black;" + 
    //     "-fx-font-size: 30px;" +
    //     "-fx-font-weight: normal;" +
    //     "-fx-padding: 50 0 50 0;" 
    //     );


	// 	Label rulesText = new Label("1. The Game Board and Numbers\n" + //
    //                     "The Board: The Keno board contains 80 unique numbers, usually arranged in a grid.\n" + //
    //                     "\n" + //
    //                     "Player's Goal: The player's goal is to correctly predict which of these numbers will be randomly drawn.\n" + //
    //                     "\n" + //
    //                     "2. Marking Your Ticket (Picking Spots)\n" + //
    //                     "Spots: The numbers you select are called \"spots.\"\n" + //
    //                     "\n" + //
    //                     "Selection: The player chooses a set of spots, between 1 and 10, by marking them electronically.\n" + //
    //                     "\n" + //
    //                     "Note: The number of spots picked directly affects the potential payout.\n" + //
    //                     "\n" + //
    //                     "Wager: The player places a monetary bet (wager) on the selected numbers.\n" + //
    //                     "\n" + //
    //                     "3. The Draw\n" + //
    //                     "Drawing: After all bets are placed, 20 numbers are randomly drawn. These are often referred to as the \"winning numbers\" or \"called numbers.\"\n" + //
    //                     "\n" + //
    //                     "4. Determining a Win (The Payout)\n" + //
    //                     "Catching Numbers: A \"catch\" is when one of your selected spots matches one of the 20 drawn numbers.\n" + //
    //                     "\n" + //
    //                     "Winning: To win, a player must \"catch\" a minimum number of their selected spots.\n" + //
    //                     "\n" + //
    //                     "Payouts: Payouts are based on a payout table that dictates how much is won based on:\n" + //
    //                     "\n" + //
    //                     "The total number of spots the player chose (e.g., a \"6-Spot\" ticket).\n" + //
    //                     "\n" + //
    //                     "The number of spots the player caught (e.g., catching 4 out of 6 spots).\n" + //
    //                     "\n" + //
    //                     "The amount of the initial wager.");

    //     rulesText.setStyle(
    //     "-fx-text-fill: black;" + 
    //     "-fx-font-size: 14px;" +
    //     "-fx-font-weight: normal;"
    //     );
    //     rulesTitle.setTextAlignment(TextAlignment.CENTER);
    //     rulesText.setTextAlignment(TextAlignment.CENTER);

    //     VBox rulesBox = new VBox(0, rulesTitle, rulesText);
    //     rulesBox.setAlignment(Pos.CENTER);



	// 	BorderPane borderPane = new BorderPane();
	// 	borderPane.setStyle("-fx-background-color: #ffffffff;"); 
	// 	borderPane.setPadding(new Insets(10));
	// 	borderPane.setCenter(rulesBox);
				
	// 	Scene scene = new Scene(borderPane, 700,700);
	// 	primaryStage.setScene(scene);
	// 	primaryStage.show();
		
				
		
	// }

}
