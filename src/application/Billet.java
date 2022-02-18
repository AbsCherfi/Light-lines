package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Billet {
	
	 private Parent root;
		private Scene scene;
		private Alert alert;
		private FXMLLoader loader;
		private Stage stage;
		private Connection connection;
		private PreparedStatement pst;
		private PreparedStatement pst1;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;

    @FXML
    private TextField boughtPriceField;

    @FXML
    private Label boughtPriceLabel;

    @FXML
    private TextField departDateFiled;

    @FXML
    private Label departDateLabel;

    @FXML
    private Pane greenPane;

    @FXML
    private Pane greyPane;

    @FXML
    private ImageView image;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField quantityField;

    @FXML
    private Label quantityLabel;

    @FXML
    private Pane redPane;

    @FXML
    private TextField returnDateField;

    @FXML
    private Label returnDateLabel;

    @FXML
    private TextField salePriceField;

    @FXML
    private Label salePriceLabel;

    @FXML
    private Button submitButton;

    @FXML
    private Label surnameLabel;

    @FXML
    private TextField surnameTextField;

    @FXML
    void goBack(ActionEvent event) {
    	try {
    		
    		Client client = new Client();
			client.goBack(event);
			/*
    		loader = new FXMLLoader(getClass().getResource("/FXML_Files/MainPage.fxml"));
    		root = loader.load();
    		 
    		MainPage mainPage = loader.getController(); 
    		
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
    	String[] fields = new String[7];
    	
    	String name = nameTextField.getText();
    	fields[0] = name;
    	
		String surname = surnameTextField.getText();
		fields[1] = surname;
		
		String departureDate = departDateFiled.getText();
		fields[2] = departureDate;
		
		String returnDate = returnDateField.getText();
		fields[3] = returnDate;
		
		String qnt = quantityField.getText();
		fields[4] = qnt;
		int quantity = Integer.parseInt(qnt);
				
		String bPrice =  boughtPriceField.getText();
		fields[5] = bPrice;
		double boughtPrice = Double.parseDouble(bPrice);
		
		String sPrice = salePriceField.getText();
		fields[6] = sPrice;
		double salePrice = Double.parseDouble(sPrice);
		
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Submission");
		alert.setHeaderText("You are about to Enter a 'Billet' into the System!");
		alert.setContentText("press 'OK' to submit, otherwise 'Cancel' ");
		
		
		if(alert.showAndWait().get() == ButtonType.OK) {
		
			try {
				connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root", "123@Kaizen");
				
				pst = connection.prepareStatement("INSERT INTO Billet(name, surname, departureDate, returnDate ,quantity, boughtPrice, salePrice)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?);"); //DO WE NEED THE START DATE AND THE EXPIRY DATE
				
				pst.setString(1, name);
				pst.setString(2, surname);
				pst.setString(3, departureDate);
				pst.setString(4, returnDate);
				pst.setInt(5, quantity);
				pst.setDouble(6, boughtPrice);
				pst.setDouble(7, salePrice);
			
				
				
				pst.executeLargeUpdate();
				nameTextField.setText("");
				surnameTextField.setText("");
				departDateFiled.setText("");
				returnDateField.setText("");
				quantityField.setText("");
				boughtPriceField.setText("");
				salePriceField.setText("");
				/*
				 
				 */
				double totalPrice = quantity * salePrice;
				String serviceType = "Billet";
				
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
				alert2.setContentText("You have successfully entered a 'Visa Entry' into the System!");
				alert2.showAndWait();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
    }

}