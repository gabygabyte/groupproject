package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    void saveBooking(ActionEvent event) throws IOException, ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
    	//Check for empty fields
    	String fields = "";
    	if(Email.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter an email address").showAndWait();
    	}
    	
    	//check for valid email address
    	if(!Email.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
    		fields = "incomplete";
    		Email.clear();
    		new Alert(Alert.AlertType.ERROR, "Please enter valid email address").showAndWait();
    		
    	}
  
    	
    	if(CheckIn.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a check in date").showAndWait();
    	}
    	
    	//check for correct date format
    	if(!CheckIn.getText().matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a valid check in date (mm/dd/yyyy)").showAndWait();
    		
    	}
    	
    	
    	if(CheckOut.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a check out date").showAndWait();
    	}
    	
    	//check for correct date format
    	if(!CheckOut.getText().matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a valid check out date (mm/dd/yyyy)").showAndWait();
    		
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
    	
    	
    	//check for previous bookings under an email
    	try (BufferedReader br = new BufferedReader(new FileReader("bookings.properties"))) {
    		byte[] bytes = Files.readAllBytes(Paths.get("bookings.properties"));
    		String s = new String(bytes);
    		String s2 = Email.getText();
    		if(s.contains(s2) == true) {
    			fields = "incomplete";
    			new Alert(Alert.AlertType.ERROR, "Booking already present under that email").showAndWait();
    			
    		}
    		
		}
    	
    	
    	
    	//Verify if check out date is after check in date
    	String checkInDate1 = CheckIn.getText();
    	String checkOutDate1 = CheckOut.getText();
    	Date date1 = sdf.parse(checkInDate1);
    	Date date2 = sdf.parse(checkOutDate1);
    	if (date1.after(date2)) {
            fields = "incomplete";
            new Alert(Alert.AlertType.ERROR, "Conflicting dates: your check out date is before your check in date! ").showAndWait();
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
