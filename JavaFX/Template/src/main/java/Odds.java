import javafx.scene.control.Alert;

public class Odds {

    public static void showOdds() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Keno Odds");
        alert.setHeaderText("Overall Odds of Winning");
        alert.setContentText("1 Spot: 1 in 4\n" +
                        "4 Spots: 1 in 3.86\n" +
                        "8 Spots: 1 in 9.77\n" +
                        "10 Spots: 1 in 9.05");
        alert.showAndWait();
    }
}