package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;


public class Main extends Application {//this is inheritance 
	//Image is the photo
	//ImageVirew is the frame for that photo
	//private ImageView imageView;
	//private Image image;
	private Parent root;
	private Scene scene;
	private Alert alert;
	private Image icon;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource("/FXML_Files/LoginPage.fxml"));	
			//Group group = new Group();
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			icon = new Image("/Images/0.jpeg");
			
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Light Lines Travel");
			primaryStage.centerOnScreen();
			//primaryStage.setFullScreen(true);
			//primaryStage.setFullScreenExitHint("YOU CAN'T ESCAPE UNLESS YOU PRESS Q");
			//primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
			
			
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
			logOut(primaryStage);
			});
			
			
		} 
		catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	
	public void logOut(Stage stage ) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout!");
		alert.setContentText("Are you sure you want to proceed?");
		
		
		if (alert.showAndWait().get() == ButtonType.OK) {
			System.out.println("You successfully logged out");
			stage.close();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
