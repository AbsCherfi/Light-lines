package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ClientInfo implements Initializable {

	Connection connection;
	private Stage stage;
	@FXML
	private TableColumn<ModelTable, String> address;

	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Button backButton;

	@FXML
	private TableColumn<ModelTable, String> email;

	@FXML
	private TableColumn<ModelTable, String> name;

	@FXML
	private TableColumn<ModelTable, String> phone;

	@FXML
	private TableColumn<ModelTable, String> surname;

	@FXML
	private TableView<ModelTable> table;
	private Parent root;
	private Scene scene;
	private FXMLLoader loader;

	ObservableList<ModelTable> obesrvableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root",
					"123@Kaizen");
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Client");
			while (rs.next()) {
				obesrvableList.add(new ModelTable(rs.getString("name"), rs.getString("surname"), rs.getString("email"),
						rs.getString("phone"), rs.getString("address")));
			}

		} catch (Exception e) {
		}
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));

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
