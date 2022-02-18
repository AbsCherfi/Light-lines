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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Visa {
	
	 private Parent root;
		private Scene scene;
		private Alert alert;
		private FXMLLoader loader;
		private Stage stage;

    @FXML
    private Button backButton;

    @FXML
    private Label boughtPrice;

    @FXML
    private TextField boughtPriceTextField;

    @FXML
    private Label destination;

    @FXML
    private TextField destinationTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private Label name;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label quantity;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Label salePrice;

    @FXML
    private TextField salePriceTextField;

    @FXML
    private Button submitButton;

    @FXML
    private Label surname;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Label type;

    @FXML
    private TextField typeTextField;

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
		
		String destination = destinationTextField.getText();
		fields[2] = destination;
		
		String type = typeTextField.getText();
		fields[3] = type;
		
		String qnt = quantityTextField.getText();
		fields[4] = qnt;
		int quantity = Integer.parseInt(qnt);
				
		String bPrice =  boughtPriceTextField.getText();
		fields[5] = bPrice;
		double boughtPrice = Double.parseDouble(bPrice);
		
		String sPrice = salePriceTextField.getText();
		fields[6] = sPrice;
		double salePrice = Double.parseDouble(sPrice);
		
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Submission");
		alert.setHeaderText("You are about to Enter a 'Visa Entry' into the System!");
		alert.setContentText("press 'OK' to submit, otherwise 'Cancel' ");
		
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			
			boolean finished = false;
			while(finished) {
			for(int i = 0; i< fields.length; i++) {
				if (fields[i] == null || fields[i] == "") {
					Alert al = new Alert(Alert.AlertType.WARNING);
					al.setTitle("Warning");
					al.setHeaderText(null);
					al.setContentText("One of the fields is empty");
					al.showAndWait();
					finished =false;
					//break;
					
				}
				
				
				else {
					finished = true;
				}
			}
			}
			try {
				connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root", "123@Kaizen");
				
				pst = connection.prepareStatement("INSERT INTO Visa(name, surname, destination, type ,quantity, boughtPrice, salePrice)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?);"); //DO WE NEED THE START DATE AND THE EXPIRY DATE
				
				pst.setString(1, name);
				pst.setString(2, surname);
				pst.setString(3, destination);
				pst.setString(4, type);
				pst.setInt(5, quantity);
				pst.setDouble(6, boughtPrice);
				pst.setDouble(7, salePrice);
			
				
				
				pst.executeLargeUpdate();
				nameTextField.setText("");
				surnameTextField.setText("");
				destinationTextField.setText("");
				typeTextField.setText("");
				quantityTextField.setText("");
				boughtPriceTextField.setText("");
				salePriceTextField.setText("");
				

				
				double totalPrice = quantity * salePrice;
				String serviceType = "Billet";
				
				pst1 = connection.prepareStatement("INSERT INTO Facturation(name, surname, totalPrice, serviceType)"
						+ "VALUES (?, ?, ?, ?);");
				
				pst1.setString(1, name);
				pst1.setString(2, surname);
				pst1.setDouble(3, totalPrice);
				pst1.setString(4, type);
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