package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainPage implements Initializable {

	FXMLLoader loader;
	@FXML
	private ImageView imageView;
	@FXML
	private Button logOutButton;
	@FXML
	private AnchorPane scenePane;
	@FXML
	private Button voyageOrganise;
	@FXML
	private Button facturation;
	@FXML
	private Button billet;
	@FXML
	private Button visa;
	@FXML
	private Button voyageOrganiseInfo;
	@FXML
	private Button facturationInfo;
	@FXML
	private Button billetInfo;
	@FXML
	private Button visaInfo;
	private Parent root;
	private Scene scene;
	private Stage stage;
	@FXML
	private ChoiceBox<String> myChoiceBox;
	// private String[] choiceBoxOptions;

	Image icon;
	@FXML
	private ComboBox<String> comboBox;
	// private String[] comboBoxOptions;

	@FXML
	private MenuItem deleteMenuItem;

	@FXML
	private Menu editMenu;

	@FXML
	private MenuItem exportMenuItem;

	@FXML
	private Menu helpMenu;

	@FXML
	private MenuItem importMenuItem;

	@FXML
	private MenuItem incomeLMonthItem;

	@FXML
	private MenuItem incomeLastWeek;

	@FXML
	private Menu incomeMenu;

	@FXML
	private MenuItem incomeTodayMItem;

	@FXML
	private MenuBar menuBar;
	private Connection connection;
	private PreparedStatement pst;

	public MainPage() {
		// comboBoxOptions = new String[]{"Insert Client", "Get Clients"};
		// comboBox= new ComboBox<>();
		// comboBox.getItems().addAll(comboBoxOptions);
		// comboBox.setPromptText("Clients");
		// imageView = new ImageView("ninja (1).png");
		// visa.setGraphic(imageView);

	}

	@FXML
	void delete(ActionEvent event) {

	}

	@FXML
	void exportFile(ActionEvent event) {

	}

	@FXML
	void getIncomeToday(ActionEvent event) {

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root",
					"123@Kaizen");

			pst = connection.prepareStatement(
					"SELECT sum(totalPrice) FROM Facturation WHERE bookingTime = CURRENT_DATE();");
			
			loader = new FXMLLoader(getClass().getResource("/FXML_Files/IncomeTab.fxml"));
			root = loader.load();

			IncomeTab income = loader.getController();

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.centerOnScreen();
		} catch (Exception e) {
          e.printStackTrace();
		}
	}

	@FXML
	void help(ActionEvent event) {

	}

	@FXML
	void importFile(ActionEvent event) {

	}

	@FXML
	void incomeLMonth(ActionEvent event) {

	}

	@FXML
	void incomeLWeek(ActionEvent event) {

	}

	public void logOut1(ActionEvent event) {

	}

	public void logOut(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Are you sure you want to proceed?: ");

		if (alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) scenePane.getScene().getWindow();
			System.out.println("You successfully logged out");
			stage.close();
		}

	}

	@FXML
	public void goToBillet(ActionEvent event) throws IOException {

		loader = new FXMLLoader(getClass().getResource("/FXML_Files/Billet.fxml"));
		root = loader.load();

		Billet billet = loader.getController();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		//stage.setTitle("Light Lines Travel");
		stage.show();
		stage.centerOnScreen();

	}

	@FXML
	public void goToFacturation(ActionEvent event) throws IOException {

		loader = new FXMLLoader(getClass().getResource("/FXML_Files/Facturation.fxml"));
		root = loader.load();

		Facturation facturation = loader.getController();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.centerOnScreen();

	}

	@FXML
	public void goToBilletInfo(ActionEvent event) throws IOException {

		loader = new FXMLLoader(getClass().getResource("/FXML_Files/BilletInfo.fxml"));
		root = loader.load();

		BilletInfo billetInfo = loader.getController();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		// Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		// stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		// stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		stage.centerOnScreen();

	}

	@FXML
	public void goToClient(ActionEvent event) {

		try {

			String insert = comboBox.getValue();

			if (insert.equals("Insert Client")) {

				loader = new FXMLLoader(getClass().getResource("/FXML_Files/Client.fxml"));
				root = loader.load();

				Client client = loader.getController();

				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				stage.centerOnScreen();

			} else {
				loader = new FXMLLoader(getClass().getResource("/FXML_Files/ClientInfo.fxml"));
				root = loader.load();

				ClientInfo clientInfo = loader.getController();

				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				stage.centerOnScreen();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void goVOrganise1(ActionEvent event) {

	}

	@FXML
	public void goVOrganise(ActionEvent event) {
		try {
			loader = new FXMLLoader(getClass().getResource("/FXML_Files/VoyageOrganise.fxml"));
			root = loader.load();

			VoyageOrganise voyage = loader.getController();

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void goVOrganiseInfo(ActionEvent event) {
		try {
			loader = new FXMLLoader(getClass().getResource("/FXML_Files/VoyageOrganiseInfo.fxml"));
			root = loader.load();

			VoyageOrganiseInfo voyageInfo = loader.getController();

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void goToVisa(ActionEvent event) {
		try {
			loader = new FXMLLoader(getClass().getResource("/FXML_Files/Visa.fxml"));
			root = loader.load();

			Visa visa = loader.getController();

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void goToVisaInfo(ActionEvent event) {
		try {
			loader = new FXMLLoader(getClass().getResource("/FXML_Files/VisaInfo.fxml"));
			root = loader.load();

			VisaInfo visaInfo = loader.getController();

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> comboBoxList = FXCollections.observableArrayList("Insert Client", "Get Clients");
		// comboBoxOptions = new String[]{"Insert Client", "Get Clients"};
		// myChoiceBox= new ChoiceBox<>();
		comboBox.setItems(comboBoxList);
		// comboBox.getItems().addAll(comboBoxOptions);
		// comboBox.setPromptText("Clients");
		// comboBox.setOnAction(arg01 -> {
		// try {
		// goToClient(arg01);
		// } catch (IOException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// });
		/*
		 * BorderPane Bpane = new BorderPane(); Button button = new Button("Hi mate");
		 * button.setPrefSize(20, 20); button.setLayoutX(0); button.setLayoutY(0);
		 * 
		 * Bpane.setCenter(button);
		 * 
		 * 
		 * stage.setScene(scene); stage.show();
		 */
	}

}
