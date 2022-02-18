package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Facturation implements Initializable {

	Connection connection;
	private Stage stage;
	@FXML
	private TextField searchDateTextField;
	
	@FXML
	private TableColumn<FacturationTable, String> name;
	
	@FXML
	private TableColumn<FacturationTable, String> surname;
	
	@FXML
	private TableColumn<FacturationTable, Double> totalPrice;
	
	@FXML
	private TableColumn<FacturationTable, String> serviceType;
	
    @FXML
    private TableColumn<FacturationTable, String> bookingTime;
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Button button;
	
	@FXML
	private TableView<FacturationTable> table;
	private Parent root;
	private Scene scene;
	private FXMLLoader loader;

	ObservableList<FacturationTable> obesrvableList = FXCollections.observableArrayList();

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root",
					"123@Kaizen");
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Facturation");
			while (rs.next()) {
				obesrvableList.add(new FacturationTable(rs.getString("name"), rs.getString("surname"),rs.getDouble("totalPrice"),
						rs.getString("serviceType"), rs.getString("bookingTime")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		serviceType.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
		bookingTime.setCellValueFactory(new PropertyValueFactory<>("bookingTime"));
		table.setItems(obesrvableList);
		
		FilteredList<FacturationTable> filteredData = new FilteredList<>(obesrvableList, b -> true);
		searchDateTextField.textProperty().addListener((observable, oldValue, newValue)->{
			filteredData.setPredicate(facturationTable ->{
            if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
				return true;	
				}
            String searchKeyWord = newValue.toLowerCase();
            if (facturationTable.getBookingTime().indexOf(searchKeyWord)> -1) {
				return true;
			}else 
				return false;
			});
		});
		SortedList<FacturationTable> sortedData = new SortedList<>(filteredData);
	    sortedData.comparatorProperty().bind(table.comparatorProperty());
	table.setItems(sortedData);
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
