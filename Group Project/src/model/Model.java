package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import application.HotelSelectionController;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


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
	
	public static String checkFields(TextField location, TextField nameText, TextField emailText, TextField hotelText, TextField checkinText, 
			TextField checkoutText, TextField roomText, TextField adultsText, TextField childrenText) throws ParseException, FileNotFoundException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String fields = "";
    	if(location.getText().isEmpty() && nameText.getText().isEmpty() && hotelText.getText().isEmpty() && checkinText.getText().isEmpty() && 
    			checkinText.getText().isEmpty() && checkoutText.getText().isEmpty() && roomText.getText().isEmpty() && 
    			adultsText.getText().isEmpty() && childrenText.getText().isEmpty()) 
    	{
    		new Alert(Alert.AlertType.ERROR, "All information is blank, please type in you email then press search to load your information").showAndWait();
    		fields = "empty";
    	}
    	else 
    	{
    		// Check for hotels
    		if(hotelText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter at least one hotel.").showAndWait();
	    	}
    		
    		// Check for location
    		if(location.getText().isEmpty()) {
    			fields = "incomplete";
    			new Alert(Alert.AlertType.ERROR, "Please enter a location");
    		}
    		
    		// Check for name
    		if(nameText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter your name").showAndWait();
	    	}
    		
    		// Check for email address
    		if(emailText.getText().isEmpty() | (!emailText.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) ) {
        		fields = "incomplete";
        		new Alert(Alert.AlertType.ERROR, "Please enter a valid email address").showAndWait();
        	} else {
    		
	    		//check for previous bookings under an email
	        	try (BufferedReader br = new BufferedReader(new FileReader("bookings.properties"))) {
	    	    	byte[] bytes = Files.readAllBytes(Paths.get("bookings.properties"));
	    	    	String s = new String(bytes);
	    	    	String s2 = emailText.getText();
	    	    	if(s.contains(s2) == true) {
	    	    		fields = "incomplete";
	    	    		new Alert(Alert.AlertType.ERROR, "Booking already present under that email").showAndWait();	
	    	    	}	
	    		}
        	}
    		// Check for checkIn
	     	if(checkinText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter a check in date").showAndWait();
	    	}
	    	
	    	// Check for correct date format
	    	if(!checkinText.getText().matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter a valid check in date (mm/dd/yyyy)").showAndWait();    		
	    	}
	    	
	    	// Check for checkOut
	    	if(checkoutText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter a check out date").showAndWait();
	    	}
	    	
	    	// Check for correct date format
	    	if(!checkoutText.getText().matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter a valid check out date (mm/dd/yyyy)").showAndWait();
	    		
	    	}
	    	
	    	//Verify if check out date is after check in date
	    	if(checkinText.getText().isEmpty() == false && checkoutText.getText().isEmpty() == false ) {
	    		
	    		Date date1 = sdf.parse(checkinText.getText());
	    		Date date2 = sdf.parse(checkoutText.getText());
	    		
	    		if (date1.after(date2)) {
	    			fields = "incomplete";
	    			new Alert(Alert.AlertType.ERROR, "Conflicting dates: your check out date is before your check in date! ").showAndWait();
	    		}
	    	 	
	    		//Verify that the date chosen has not already passed
	    		java.util.Date now=new java.util.Date();
	    		if(now.after(date1) || now.after(date2)) {
	    			fields = "incomplete";
	    			new Alert(Alert.AlertType.ERROR, "Invalid Date: Date chosen no longer available").showAndWait();
	    		}
	    	}
	    	
	    	// Check for amount of rooms
	    	if(roomText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter number of rooms needed").showAndWait();
	    	}
	    	
	    	// Check for amount of adults
	    	if(adultsText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter the number of adults").showAndWait();
	    	}
	    	else {
	    	// Check for at least 1 adult
	    		int numAdults = Integer.parseInt(adultsText.getText());
	    		if(numAdults == 0) {
	    			fields = "incomplete";
	    			new Alert(Alert.AlertType.ERROR, "Number of adults should be more than 0").showAndWait();
	    		}
	    	}
	    	// Check for amount of children
	    	if(childrenText.getText().isEmpty()) {
	    		fields = "incomplete";
	    		new Alert(Alert.AlertType.ERROR, "Please enter the number of children").showAndWait();
	    	}
    	}
    	
    	return fields;
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
		new Alert(Alert.AlertType.INFORMATION, "Booking successfully updated!" 
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
