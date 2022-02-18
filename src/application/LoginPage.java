package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage {
	private Connection connector;
	private PreparedStatement pst;
	private ResultSet rst;
	
	@FXML
	private ImageView lightLinesLogo;

	@FXML
	private ImageView myLogo;
	
	@FXML
	private Text adminLoginPage;

	@FXML
	private ImageView imageView;

	@FXML
	private Text lightLineTravelsText;

	@FXML
	private Button loginButton;

	@FXML
	private Pane pane;

	@FXML
	private Label password;

	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField textField;

	@FXML
	private Label user;

	private Parent root;
	private Stage stage;
	private Scene scene;
	Alert alert;

	public LoginPage() {

		connector = null;
	}

	@FXML
	public void login(ActionEvent event) throws IOException {
		String username = textField.getText();
		String password = passwordField.getText();
		// KeyEvent key = new KeyEvent();

		if (username.equals("") && password.equals("")) {
			// JOptionPane.showMessageDialog(null, "The fields are blank");
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Ooops!");
			alert.setHeaderText("Empty credentials");
			// alert.setContentText("Are you sure you want to proceed?: ");
			if (alert.showAndWait().get() == ButtonType.OK) {// if they press ok then
				stage = (Stage) pane.getScene().getWindow();// stay in the same scene

			}

		} else {
			try {
				// Class.forName("cpm.mysql.jdbc.Driver");
				connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root",
						"123@Kaizen");// connect to the database

				// set pst to be the prepared statement
				pst = connector.prepareStatement("SELECT * FROM LightLinesAdmin WHERE USERNAME = ? AND PASSWORD =? ");

				pst.setString(1, username);// set username to whatever they have inputted
				pst.setString(2, password);// set password to whatever they have inputted

				rst = pst.executeQuery();// execute the query

				if (rst.next()) {// if there is no issue then

					// JOptionPane.showMessageDialog(null, "You have logged in successfully");
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_Files/MainPage.fxml"));
					root = loader.load();

					MainPage mainPage = loader.getController();

					// NEED TO CHANGE THE COLOUR OF THE TEXTFIELD AND PASSWORD FILELD

					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					stage.centerOnScreen();

				} else {// else do this
						// JOptionPane.showMessageDialog(null, "Login failed");
					alert = new Alert(AlertType.CONFIRMATION);// alert window
					alert.setTitle("Ooops!");// called oops!
					alert.setHeaderText("Login failed");// tell the user the login failed
					if (alert.showAndWait().get() == ButtonType.OK) {// if they press ok
						stage = (Stage) pane.getScene().getWindow();// stay in the same stage

					}
					textField.setText("");// clear the textfields
					passwordField.setText("");
					textField.requestFocus();
				}

			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "An error Occured");
				e.printStackTrace();// print the error to the console
			}

		}

	}

	@FXML
	void enter(KeyEvent event) {

	}
}
