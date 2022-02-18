package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VisaInfo implements Initializable{
	Connection connection;
	private Stage stage;
	
	@FXML
	private TableColumn<VisaModel, String> name;
	
	@FXML
	private TableColumn<VisaModel, String> surname;
	

	@FXML
	private TableColumn<VisaModel, String> destination;
	
	@FXML
	private TableColumn<VisaModel, String> type;
	
	@FXML
	private TableColumn<VisaModel, String> quantity;
	
	@FXML
	private TableColumn<VisaModel, String> boughtPrice;
	
	@FXML
	private TableColumn<VisaModel, String> salePrice;

	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Button backButton;
	
	@FXML
	private JFXButton exportButon;

	
	@FXML
	private TableView<VisaModel> table;
	private Parent root;
	private Scene scene;
	private FXMLLoader loader;

	ObservableList<VisaModel> obesrvableList = FXCollections.observableArrayList();

	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root",
					"123@Kaizen");
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Visa");
			while (rs.next()) {
				obesrvableList.add(new VisaModel(rs.getString("name"), rs.getString("surname"), rs.getString("destination"),
						rs.getString("type"),rs.getInt("quantity"), rs.getDouble("boughtPrice"),  rs.getDouble("salePrice")));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		boughtPrice.setCellValueFactory(new PropertyValueFactory<>("boughtPrice"));
		salePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
		

		table.setItems(obesrvableList);
		
	}
	
	
	@FXML
	public void goBack(ActionEvent event) {
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

}
