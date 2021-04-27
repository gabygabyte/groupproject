package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * This class will display the NeedGive and Inventory Interface depending
 * on what the user clicks on. Also passes the necessary button ID to NeedGiveCOntroller class
 * that is necessary to toggle between the add and subtract logic in the need/give scene
 */

public class HotelSelectionController {
	@FXML
	private AnchorPane mainPane;

	@FXML
	public void clickHome(ActionEvent event) throws IOException {
		
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml")); //
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	@FXML
	public void hotelSelected(ActionEvent event) throws IOException {
		
	
	}
	
	@FXML
	public void addHotel(ActionEvent event) throws IOException {
		
	
	}
	
	@FXML
	public void bookingScene(ActionEvent event) throws IOException {
		
	
	}
	
	
	@FXML
	public void setHotel(ActionEvent event) throws IOException {
		
	
	}
	
	@FXML
	public void clearHotels(ActionEvent event) throws IOException {
		
	
	}
}
