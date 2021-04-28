package application;

import model.Model;//created this model class

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

public class EditingController implements Initializable
{
    @FXML
    private AnchorPane editingPanel;
    @FXML
    private TextField checkinText;
    @FXML
    private TextField roomText;
    @FXML
    private TextField childrenText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField adultsText;
    @FXML
    private TextField checkoutText;
    @FXML
    private Button hotelEdit;
    @FXML
    private TextField hotelText;
    @FXML
    private Button childrenEdit;
    @FXML
    private Button checkoutEdit;
    @FXML
    private Button emailEdit;
    @FXML
    private Button nameEdit;
    @FXML
    private Button checkinEdit;
    @FXML
    private Button roomsEdit;
    @FXML
    private Button adultsEdit;

	@FXML
	public void toHome(ActionEvent event) throws IOException {
		
		editingPanel = FXMLLoader.load(getClass().getResource("Main.fxml")); 
		Scene scene = new Scene(editingPanel);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	public void Search(ActionEvent event) throws IOException  {
		String compKey = emailText.getText().toString();
		
		String info = Model.getUserInfo(compKey); //get info from model class method
    	 
    	 if(info.equals("Invalid email")) //when email entered isn't a valid key print an error
    	 { 
    		 new Alert(Alert.AlertType.ERROR, "Entered email is not associated with any information, "
    		 		+ "please try again.").showAndWait();
    	 }
    	 else 
    	 { 
	    	 String[] infoStr = info.split(","); //split user info by the commas added in the booking process
	    	 
	    	 //put all the info in the proper text box
	    	 nameText.setText(infoStr[0]);
	    	 hotelText.setText(infoStr[1]);
	    	 checkinText.setText(infoStr[2]);
	    	 checkoutText.setText(infoStr[3]);
	    	 roomText.setText(infoStr[4]);
	    	 adultsText.setText(infoStr[5]);
	    	 childrenText.setText(infoStr[6]);
    	 }
	}
	
	@FXML
	public void allowEdit(ActionEvent event) throws IOException {
		Button pressedButton = (Button) event.getSource(); //gets id for pressed button
		String id = pressedButton.getId();
		
		//clears the corresponding button and prints a confirmation 
		if(hotelEdit == pressedButton) {
			hotelText.clear();
			new Alert(Alert.AlertType.CONFIRMATION, "You can now update to desired hotels, "
					+ "click update when all fields are properly up to date.").showAndWait();
		}
			
		if(nameEdit == pressedButton) {
			nameText.clear();
			new Alert(Alert.AlertType.CONFIRMATION, "You can now update to desired email, "
					+ "click update when all fields are properly up to date.").showAndWait();
		}
		if(checkinEdit == pressedButton) {
			checkinText.clear();
			new Alert(Alert.AlertType.CONFIRMATION, "You can now update to desired checkin date, "
					+ "click update when all fields are properly up to date.").showAndWait();
		}
		if(checkoutEdit == pressedButton) {
			checkoutText.clear();
			new Alert(Alert.AlertType.CONFIRMATION, "You can now update to desired checkout date, "
					+ "click update when all fields are properly up to date.").showAndWait();
		}
		if(roomsEdit == pressedButton) {
			roomText.clear();
			new Alert(Alert.AlertType.CONFIRMATION, "You can now update to desired number of rooms, "
					+ "click update when all fields are properly up to date.").showAndWait();
		}
		if(adultsEdit == pressedButton) {
			adultsText.clear();
			new Alert(Alert.AlertType.CONFIRMATION, "You can now update to desired number of adult guests, "
					+ "click update when all fields are properly up to date.").showAndWait();
		}
		if(childrenEdit == pressedButton) {
			childrenText.clear();
			new Alert(Alert.AlertType.CONFIRMATION, "You can now update to desired number of child guests, "
					+ "click update when all fields are properly up to date.").showAndWait();
		}
	}
	
	@FXML
	public void Update(ActionEvent event) throws IOException, ParseException {
		//Check for empty fields
    	String fields = "";
    	
    	if(nameText.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter your name").showAndWait();
    	}
    	if(emailText.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter an email address").showAndWait();
    	}
    	
    	//check for valid email address
    	if(!emailText.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
    		fields = "incomplete";
    		emailText.clear();
    		new Alert(Alert.AlertType.ERROR, "Please enter valid email address").showAndWait();
    		
    	}
  
    	
    	if(checkinText.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a check in date").showAndWait();
    	}
    	
    	//check for correct date format
    	if(!checkinText.getText().matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a valid check in date (mm/dd/yyyy)").showAndWait();
    		
    	}
    	
    	
    	if(checkoutText.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a check out date").showAndWait();
    	}
    	
    	//check for correct date format
    	if(!checkoutText.getText().matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter a valid check out date (mm/dd/yyyy)").showAndWait();
    		
    	}
    	
    	
    	if(roomText.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter number of rooms needed").showAndWait();
    	}
    	if(adultsText.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter the number of adults").showAndWait();
    	}
    	if(childrenText.getText().isEmpty()) {
    		fields = "incomplete";
    		new Alert(Alert.AlertType.ERROR, "Please enter the number of children").showAndWait();
    	}
    	
    	//Verify if check out date is after check in date
    	SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
    	String checkInDate = checkinText.getText();
    	String checkOutDate = checkoutText.getText();
    	Date date1 = sdf.parse(checkInDate);
    	Date date2 = sdf.parse(checkOutDate);
    	if (date1.after(date2)) {
            fields = "incomplete";
            new Alert(Alert.AlertType.ERROR, "Conflicting dates: your check out date is before your check in date! ").showAndWait();
        }
    	
    	if (fields.isEmpty()) {
    		// Set Variables from text fields
    		String name = nameText.getText();
    		String hotels = hotelText.getText();
    		String emailAddress = emailText.getText();
    		String checkIn = checkinText.getText();
    		String checkOut = checkoutText.getText();
    		int rooms = Integer.parseInt(roomText.getText());
    		int adults = Integer.parseInt(adultsText.getText());
    		int children = Integer.parseInt(childrenText.getText());
    		
    		// Merge variables into one string to be stored in hashmap
    		String Bookings = name + "," + hotels + "," + checkIn + "," + checkOut + "," + String.valueOf(rooms)
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
    		h.put(emailAddress, Bookings);
    		
    		// Store hashmap into propreties 
    		properties.putAll(h);
    		
    		// Write propreties to file
    		FileOutputStream writer = new FileOutputStream(file);
    		properties.store(writer, null);
    		
    		// Close writer
    		writer.close();
    		
    		// Display confirmation message
    		new Alert(Alert.AlertType.CONFIRMATION, "Booking successfully updated!" 
    			+ "\nYour information has been updated and sent to the selected hotels and you will hear "
    			+ "from them shortly.").showAndWait();
    		
    		// Return to main page
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        	editingPanel = loader.load();  	
        	Scene scene = new Scene(editingPanel);
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.setScene(scene);
        	window.show();
    	
    	}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { //start name text box with message
		emailText.setText("Enter email associated with information here, then press search");
	}
}
