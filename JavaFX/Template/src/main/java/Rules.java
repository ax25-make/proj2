import javafx.scene.control.Alert;

public class Rules {

	public static void showRules() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Keno Rules");
        alert.setHeaderText("Game Rules                                                                                       ");
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
}
