package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    void saveBooking(ActionEvent event) throws IOException {
    	//Check for empty fields
    	String fields = "";
    	
    	if(BookingName.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter your name").showAndWait();
    	}
    	if(Email.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter an email address").showAndWait();
    	}
    	if(CheckIn.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a check in date").showAndWait();
    	}
    	if(CheckOut.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a check out date").showAndWait();
    	}
    	if(NumRooms.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter number of rooms needed").showAndWait();
    	}
    	if(NumAdults.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter the number of adults").showAndWait();
    	}
    	if(NumChild.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter the number of children").showAndWait();
    	}
    	
    	if (fields.isEmpty()) {
    		// Set Variables from text fields
    		String hotels = HotelName.getText();
    		String emailAddress = Email.getText();
    		String checkInDate = CheckIn.getText();
    		String checkOutDate = CheckOut.getText();
    		int rooms = Integer.parseInt(NumRooms.getText());
    		int adults = Integer.parseInt(NumAdults.getText());
    		int children = Integer.parseInt(NumChild.getText());
    		
    		// Merge variables into one string to be stored in hashmap
    		String Bookings = hotels + "," + emailAddress + "," + checkInDate + "," + checkOutDate + "," + String.valueOf(rooms)
    		+ "," + String.valueOf(adults) + "," + String.valueOf(children);
    		
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
    		
    		// Check for prev bookings under given name
    	
    		// If no prev bookings with same name, store info into hashmap
    		h.put(BookingName.getText().toString(), Bookings);
    		
    		// Store hashmap into propreties 
    		properties.putAll(h);
    		
    		// Write propreties to file
    		FileOutputStream writer = new FileOutputStream(file);
    		properties.store(writer, null);
    		
    		// Close writer
    		writer.close();
    		
    		// Display confirmation message
    		new Alert(Alert.AlertType.CONFIRMATION, "Booking successfully saved!" 
    			+ "\nYour information has been sent to the selected hotels and you will hear "
    			+ "from them shortly. \nYou can select the view/edit option on the main menu"
    			+ " if you would like to edit your booking.").showAndWait();
    		
    		// Return to main page
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        	bookingBack = loader.load();  	
        	Scene scene = new Scene(bookingBack);
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.setScene(scene);
        	window.show();
    	
    	}
    }

}
