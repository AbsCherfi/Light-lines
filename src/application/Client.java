package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Client {
	
	Alert alert;
	@FXML
    private AnchorPane anchorPane;
	
	 @FXML
	    private ImageView imageView;

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    @FXML
    private Label surnameLabel;

    @FXML
    private TextField surnameTextField;

    private Stage stage;
    private Connection connection;
    //private ResultSet rs;
    private PreparedStatement pst;
    
    private Parent root;
	private Scene scene;
	//private Alert alert;
	private FXMLLoader loader;
  
    
	public Client() {
		
	}
    
    @FXML
    void submit(ActionEvent event) {

    	String name = nameTextField.getText();
		String surname = surnameTextField.getText();
		String email = emailTextField.getText();
		String phone = phoneTextField.getText();
		String address = addressTextField.getText();
		
		//boolean repeat = true;
		//while(repeat) {
		if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() ||
				emailTextField.getText().isEmpty() || phoneTextField.getText().isEmpty() ||
				addressTextField.getText().isEmpty()) {
			//repeat = false;
			Alert alt = new Alert(Alert.AlertType.WARNING);
			alt.setTitle("Warning");
			alt.setHeaderText(null);
			alt.setContentText("One of the fields is empty");
			alt.showAndWait();
			
		}else {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Submission");
			alert.setHeaderText("You are about to Enter a Client into the System!");
			alert.setContentText("press 'OK' to submit, otherwise 'Cancel' to double check the Info again");
			
		
		//}
		
    	
   
		if (alert.showAndWait().get() == ButtonType.OK ) {
			
			try {
				connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root", "123@Kaizen");
				
				pst = connection.prepareStatement("INSERT INTO Client(name, surname, email, phone, address) "
						+ "VALUES (?, ?, ?, ?, ?); ");
				
				pst.setString(1, name);
				pst.setString(2, surname);
				pst.setString(3, email);
				pst.setString(4, phone);
				pst.setString(5, address);
				
				
				pst.executeLargeUpdate();
				nameTextField.setText("");
				surnameTextField.setText("");
				emailTextField.setText("");
				phoneTextField.setText("");
				addressTextField.setText("");

				//if (rs.next()) {
					/*
				loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
					root = loader.load();
					 
					MainPage mainPage = loader.getController(); 
					
				//NEED TO CHANGE THE COLOUR OF THE TEXTFIELD AND PASSWORD FILELD
					
					
					
					stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				//}
				 
				 */
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			//stage = (Stage) anchorPane.getScene().getWindow();
		}
		}
    
    }
    
    @FXML
    public void goBack(ActionEvent event) {
    	try {
    		loader = new FXMLLoader(getClass().getResource("/FXML_Files/MainPage.fxml"));
    		root = loader.load();
    		 
    		//MainPage mainPage = loader.getController(); 
    		
    	//NEED TO CHANGE THE COLOUR OF THE TEXTFIELD AND PASSWORD FILELD
    		
    		
    		
    		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
			stage.centerOnScreen();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    
    
    
}
