import java.io.FileInputStream;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to JavaFX");

		// FileInputStream inputstream = new FileInputStream("https://img.freepik.com/premium-photo/red-board-with-red-white-game-middle-it_1354347-1648.jpg?w=360"); 
		Image image = new Image("https://img.freepik.com/premium-photo/red-board-with-red-white-game-middle-it_1354347-1648.jpg?w=360"); 

		ImageView imageView = new ImageView(image);

		//Setting the position of the image 
		imageView.setX(50); 
		imageView.setY(25); 

		//setting the fit height and width of the image view 
		imageView.setFitHeight(455); 
		imageView.setFitWidth(500); 
		
		//Setting the preserve ratio of the image view 
		imageView.setPreserveRatio(true);  
		

		//asas

		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #ffffffff;"); 
		borderPane.setPadding(new Insets(10));
		borderPane.setCenter(imageView);
		// borderPane.setTop(text2);
		// borderPane.setLeft(buttonBox);

		// BorderPane.setMargin(buttonBox, new Insets(50));
        // BorderPane.setMargin(text2, new Insets(50));
				
		Scene scene = new Scene(borderPane, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
		
				
		
	}

}
