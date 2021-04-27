package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BookingController {

    @FXML
    private TextField NumChild;

    @FXML
    private Button save;

    @FXML
    private TextField NumAdults;

    @FXML
    private TextField Email;

    @FXML
    private TextField CheckOut;

    @FXML
    private Button prev;

    @FXML
    private TextField CheckIn;

    @FXML
    private TextField HotelName;

    @FXML
    private TextField NumRooms;

    @FXML
    private TextField BookingName;
    
    @FXML
    private AnchorPane bookingBack;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelSelection.fxml"));
    	bookingBack = loader.load();  	
    	Scene scene = new Scene(bookingBack);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }

    @FXML
    void saveBooking(ActionEvent event) {
    	
    	
  
    }

}
