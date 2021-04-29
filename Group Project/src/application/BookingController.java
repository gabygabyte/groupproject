package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Model;

public class BookingController implements Initializable{

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
    void saveBooking(ActionEvent event) throws IOException, ParseException { 	
    	
    	
    	//Check for empty fields
    	String fields = Model.checkFields(BookingName, Email, HotelName, CheckIn, CheckOut, 
    			NumRooms, NumAdults, NumChild);
    	
    	if (fields.isEmpty()) {
    		// Set Variables from text fields
    		String name = BookingName.getText();
    		String hotels = HotelName.getText();
    		String emailAddress = Email.getText();
    		String checkInDate = CheckIn.getText();
    		String checkOutDate = CheckOut.getText();
    		int rooms = Integer.parseInt(NumRooms.getText());
    		int adults = Integer.parseInt(NumAdults.getText());
    		int children = Integer.parseInt(NumChild.getText());
    		
    		// Merge variables into one string to be stored in hashmap
    		String Bookings = name + ":" + hotels + ":" + checkInDate + ":" + checkOutDate + ":" + String.valueOf(rooms)
    		+ ":" + String.valueOf(adults) + ":" + String.valueOf(children);
    		
    		// Create hashmap
    		HashMap<String, String> h = new HashMap<String, String>();
    		File file = new File("bookings.properties");
    		FileInputStream reader = new FileInputStream(file);
    		Properties properties = new Properties();
    		properties.load(reader);
    		reader.close();
    	
    		for(String key: properties.stringPropertyNames()) {
    			h.put(key, properties.get(key).toString());
    		}	
    		    	
    		h.put(emailAddress, Bookings);
    		
    		// Store hashmap into properties 
    		properties.putAll(h);
    		
    		// Write properties to file
    		FileOutputStream writer = new FileOutputStream(file);
    		properties.store(writer, null);
    		
    		// Close writer
    		writer.close();
    		
    		
    		// Display confirmation message
    		new Alert(Alert.AlertType.CONFIRMATION, "Booking successfully saved!" 
    			+ "\nYour information has been sent to the selected hotels and you will hear "
    			+ "from them shortly. \nYou can select the view/edit option on the main menu"
    			+ " if you would like to edit your booking.").showAndWait();
    		
    		// Clear hotel selections
    		Model.clearHotels(HotelSelectionController.hotelArray, HotelSelectionController.hotelSelection, HotelSelectionController.arrayIndex);
    		
    		// Return to main page
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        	bookingBack = loader.load();  	
        	Scene scene = new Scene(bookingBack);
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.setScene(scene);
        	window.show();
    	
    	}
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int size = HotelSelectionController.hotelArray.length;
		int i;
		String hotels = HotelSelectionController.hotelArray[0];
		for (i = 1; i < size - 1; i++) {
			if (HotelSelectionController.hotelArray[i] != null) {
				hotels = hotels + "," + HotelSelectionController.hotelArray[i];
			}
		}	
		HotelName.setText(hotels); 	
	}
}

