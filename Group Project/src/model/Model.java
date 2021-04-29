package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import application.HotelSelectionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Model {
	private static HashMap<String, String> h = new HashMap<String, String>();//hash map to store information
	private static Properties properties = new Properties();//Create properties file to store hash map
	
	public static String getUserInfo(String compKey) throws IOException{
		String info;
        File file = new File("bookings.properties"); //opens file
        FileInputStream reader=new FileInputStream(file); //open reader
        properties.load(reader); //loads / lets you see stored values
        reader.close();

        for(String key: properties.stringPropertyNames()){//adding info to hash map
        	h.put(key, properties.get(key).toString());
        }
    	 if(h.containsKey(compKey)){ //check if there's a key in the hash map that matches 
 	       	info = h.get(compKey); //set info that goes with the person
         }
    	 else {
    		info = "Invalid email"; //The email doesn't go with any of the hotel info
    	 }
    	 
    	 return info;
	}
	
	public static void printAlert(String type) {	
		new Alert(Alert.AlertType.INFORMATION, "You can now update to desired " + type
				+ ", click update when all fields are properly up to date.").showAndWait();
	}
	
	public static void checkEmpty(String fields, TextField nameText, TextField emailText, TextField hotelText, TextField checkinText, 
			TextField checkoutText, TextField roomText, TextField adultsText, TextField childrenText) {
    	if(nameText.getText().isEmpty() && hotelText.getText().isEmpty() && checkinText.getText().isEmpty() && checkinText.getText().isEmpty() && checkoutText.getText().isEmpty() && roomText.getText().isEmpty() && adultsText.getText().isEmpty() && childrenText.getText().isEmpty()) 
    	{
    		new Alert(Alert.AlertType.ERROR, "All infomation is blank, please type in you email then press search to load your information").showAndWait();
    	}
    	else 
    	{
	    	if(emailText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter an email address").showAndWait();
	    	}
	    	
	    	//check for valid email address
	    	if(!emailText.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
	                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter a valid email address").showAndWait();
	    	}
	    	
	    	if(nameText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter your name").showAndWait();
	    	}
	    	
	    	if(hotelText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter at least one hotel.").showAndWait();
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
    	}
	}
	
	public static void saveInfo(String emailAddress, String Bookings) throws IOException {
		File file = new File("bookings.properties");
		FileInputStream reader = new FileInputStream(file);
		properties.load(reader);
		reader.close();
	
		for(String key: properties.stringPropertyNames()) {
			h.put(key, properties.get(key).toString());
		}	
	
		// Store info into hashmap
		h.put(emailAddress, Bookings);
		
		// Store hash map into properties 
		properties.putAll(h);
		
		// Write properties to file
		FileOutputStream writer = new FileOutputStream(file);
		properties.store(writer, null);
		
		// Close writer
		writer.close();
		
		// Display confirmation message
		new Alert(Alert.AlertType.CONFIRMATION, "Booking successfully updated!" 
			+ "\nYour information has been updated and sent to the selected hotels and you will hear "
			+ "from them shortly.").showAndWait();
	}

	public static void addHotel(String hotelSelection, String[] hotelArray, int arrayIndex, boolean match) {
		if(!match) { 
			HotelSelectionController.hotelArray[HotelSelectionController.arrayIndex] = HotelSelectionController.hotelSelection; //save hotelSelection to an index in hotelArray
			HotelSelectionController.arrayIndex++; //increment hotelIndex for next entry
				Alert a = new Alert(Alert.AlertType.CONFIRMATION, HotelSelectionController.hotelSelection + " has been successfully added.");
				a.show();//show confirmation message
		  }
		  else if(hotelSelection == null) {
			  Alert a = new Alert(Alert.AlertType.ERROR, "You must select a hotel to add!");
				a.show();//show Error message
		  }
		  else {
			Alert a = new Alert(Alert.AlertType.ERROR, hotelSelection + " has already been added.");
			a.show();//show Error message
		  }	
		
	}

	public static void clearHotels(String[] hotelArray, String hotelSelection, int arrayIndex) {
		for(int i=0; i<HotelSelectionController.hotelArray.length; i++) { //traverse through array
			HotelSelectionController.hotelArray[i] = null; //set each entry to null
		}
		HotelSelectionController.hotelSelection = null; //clear anything that was saved to hotelSelection
		HotelSelectionController.arrayIndex = 0; //set array index back to start
		Alert a = new Alert(Alert.AlertType.CONFIRMATION, "All selections have been cleared.");
		a.show();//show confirmation message		
	}

	public static void checkHotelArray(String[] hotelArray, String hotelSelection, int arrayIndex, boolean match) {
		for(int i=0; i<HotelSelectionController.hotelArray.length; i++) { //traverse through each array index
			if (HotelSelectionController.hotelSelection == HotelSelectionController.hotelArray[i]) { //tests for a matching string
				HotelSelectionController.match = true; //match set to true if match found
				break; //break out of loop
			}
			else {
				HotelSelectionController.match = false; //if no match found return false
			}
		}	
	}

	public static void displayHotelArray(String[] hotelArray, String hotelSelection, int arrayIndex) {
		String hotels = "";
		for(int i=0; i<HotelSelectionController.hotelArray.length; i++) { //traverse through each array index
			if (HotelSelectionController.hotelArray[i] != null) { //tests if string is empty
				hotels = hotels+"\n"+HotelSelectionController.hotelArray[i]; //append hotel at each index to hotels variable
			}
		}
		if(hotelArray[0] == null) {
			Alert a = new Alert(Alert.AlertType.ERROR, "No hotels have been added yet!");
			a.show();//show Error message
		}
		else {
			Alert a = new Alert(Alert.AlertType.INFORMATION, "Following hotels have been added: \n" + hotels); //use hotels string to print hotels in message
			a.show();
		}
	}
	
}
