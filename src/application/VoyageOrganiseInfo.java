package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.ModelTable;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VoyageOrganiseInfo implements Initializable{
	
	Connection connection;
	private Stage stage;
	
    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;
    
    @FXML
    private AnchorPane pane;
    
	
	@FXML
	private TableColumn<VoyageOrganiseModel, String> name;
	
	@FXML
	private TableColumn<VoyageOrganiseModel, String> surname;
	

	@FXML
	private TableColumn<VoyageOrganiseModel, String> destination;
	
	@FXML
	private TableColumn<VoyageOrganiseModel, String> quantity;
	
	@FXML
	private TableColumn<VoyageOrganiseModel, String> boughtPrice;
	
	@FXML
	private TableColumn<VoyageOrganiseModel, String> salePrice;
	
	

	@FXML
	private TableColumn<VoyageOrganiseModel, String> departureDate;
	@FXML
	private TableColumn<VoyageOrganiseModel, String> returnDate;

	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Button backButton;

	
	@FXML
	private TableView<VoyageOrganiseModel> table;
	private Parent root;
	private Scene scene;
	private FXMLLoader loader;

	ObservableList<VoyageOrganiseModel> obesrvableList = FXCollections.observableArrayList();

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LightLinesTravels", "root",
					"123@Kaizen");
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM VoyageOrganise");
			while (rs.next()) {
				obesrvableList.add(new VoyageOrganiseModel(rs.getString("name"), rs.getString("surname"), rs.getString("destination"),
						rs.getInt("quantity"), rs.getDouble("boughtPrice"),  rs.getDouble("salePrice"),  rs.getString("departureDate"),
						rs.getString("returnDate")));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		boughtPrice.setCellValueFactory(new PropertyValueFactory<>("boughtPrice"));
		salePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
		departureDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
		returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

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
