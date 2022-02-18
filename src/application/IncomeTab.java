package application;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class IncomeTab implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
	private TableColumn<IncomeTabModel, String> income;
    ObservableList<IncomeTabModel> obesrvableList = FXCollections.observableArrayList();
	
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    
	}
    
}
