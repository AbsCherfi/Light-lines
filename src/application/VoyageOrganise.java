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
import javafx.stage.Stage;

public class VoyageOrganise {
	
	private double boughtPrc;
	private int quantityConverted;
	private double salePriceConverted;

    @FXML
    private Label boughtPrice;

    @FXML
    private TextField boughtPriceTextField;

    @FXML
    private Label returnDate;

    @FXML
    private TextField returnField;

    @FXML
    private Label departureDate;

    @FXML
    private TextField departureDateTextField;

    @FXML
    private Label destination;

    @FXML
    private TextField destinationTextField;

    @FXML
    private Label name;

    @FXML
    private TextField nameTextField;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button submitButton;

    @FXML
    private Label quantity;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Label salePrice;

    @FXML
    private TextField salePriceTextField;

    @FXML
    private Label surname;

    @FXML
    private TextField surnameTextField;
    
    private Stage stage;
    private Parent root;
   	private Scene scene;
   	//private Alert alert;
   	private FXMLLoader loader;
    private Connection connection;
    private PreparedStatement pst;    
    private PreparedStatement pst1;     

    @FXML
    void goBack(ActionEvent event) {

    	try {
    		
    		Client client = new Client();
    		client.goBack(event);
    		
    		/*
    		loader = new FXMLLoader(getClass().getResource("/FXML_Files/MainPage.fxml"));
    		root = loader.load();
    		 
    		MainPage mainPage = loader.getController(); 
    		
    	//NEED TO CHANGE THE COLOUR OF THE TEXTFIELD AND PASSWORD FILELD
    		
    		
    		
    		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
			stage.centerOnScreen();
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

   

    @FXML
    void submit(ActionEvent event) {
    	//String [] fields  = new String[8];  
    	
    	String name = nameTextField.getText();
		String surname = surnameTextField.getText();
		String destination = destinationTextField.getText();
		String qtt = quantityTextField.getText();
		String bPrice = boughtPriceTextField.getText();
		String sPrice = salePriceTextField.getText();
		String departureDate = departureDateTextField.getText();
		String returnDate = returnField.getText();
		
		
		if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || destinationTextField.getText().isEmpty()|| 
				departureDateTextField.getText().isEmpty() || returnField.getText().isEmpty()) {
			
			Alert alert1 = new Alert(Alert.AlertType.WARNING);
			alert1.setTitle("Warning");
			alert1.setHeaderText(null);
			alert1.setContentText("One of the fields is empty");
			alert1.showAndWait();
			
		}else if (!quantityTextField.getText().isEmpty() && !boughtPriceTextField.getText().isEmpty()
				&& !salePriceTextField.getText().isEmpty()) {
			quantityConverted = Integer.parseInt(qtt);
			boughtPrc = Double.parseDouble(bPrice);
			salePriceConverted = Double.parseDouble(sPrice);
		//}
		
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Submission");
		alert.setHeaderText("You are about to Enter a 'Voyage Organise' into the System!");
		alert.setContentText("press 'OK' to submit, otherwise 'Cancel' to double check the Info again");
	

	if (alert.showAndWait().get() == ButtonType.OK) {
		
			
			try {
				connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root", "123@Kaizen");
				
				pst = connection.prepareStatement("INSERT INTO VoyageOrganise(name, surname, destination, "
						+ "quantity, boughtPrice, salePrice, departureDate, returnDate) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?); ");
				
				pst.setString(1, name);
				pst.setString(2, surname);
				pst.setString(3, destination);
				pst.setInt(4, quantityConverted);
				pst.setDouble(5, boughtPrc);
				pst.setDouble(6, salePriceConverted);
				pst.setString(7, departureDate);
				pst.setString(8, returnDate);
				
				
				pst.executeLargeUpdate();
				nameTextField.setText("");
				surnameTextField.setText("");
				destinationTextField.setText("");
				quantityTextField.setText("");
				boughtPriceTextField.setText("");
				salePriceTextField.setText("");
				departureDateTextField.setText("");
				returnField.setText("");
				
				
				double totalPrice = quantityConverted * salePriceConverted;
				String serviceType = "Voyage Organisee";
				
				pst1 = connection.prepareStatement("INSERT INTO Facturation(name, surname, totalPrice, serviceType)"
						+ "VALUES (?, ?, ?, ?);");
				
				pst1.setString(1, name);
				pst1.setString(2, surname);
				pst1.setDouble(3, totalPrice);
				pst1.setString(4, serviceType);
				pst1.executeLargeUpdate();


				Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
				alert2.setTitle("Success");
				alert2.setHeaderText(null);
				alert2.setContentText("You have successfully entered a 'Voyage Organise' into the System!");
				alert2.showAndWait();
				
			} catch (Exception e) {
				//e.printStackTrace();
				Alert alert1 = new Alert(Alert.AlertType.WARNING);
				alert1.setTitle("Warning");
				alert1.setHeaderText(null);
				alert1.setContentText("One of the fields is empty");
				alert1.showAndWait();
			}
		
		}
		}

    }

}