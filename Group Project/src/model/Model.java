package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

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
	
	public static void checkEmpty(String fields, TextField nameText, TextField emailText, TextField checkinText, 
			TextField checkoutText, TextField roomText, TextField adultsText, TextField childrenText) {
    	
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
	}
	
	public static void saveInfo(TextField nameText, TextField hotelText, TextField emailText, 
			TextField checkinText, TextField checkoutText, TextField roomText, TextField adultsText, TextField childrenText) throws IOException {
		
		// Set Variables from text fields
		String name = nameText.getText();
		String hotels = hotelText.getText();
		String emailAddress = emailText.getText();
		String checkIn = checkinText.getText();
		String checkOut = checkoutText.getText();
		int rooms = Integer.parseInt(roomText.getText());
		int adults = Integer.parseInt(adultsText.getText());
		int children = Integer.parseInt(childrenText.getText());
		
		// Merge variables into one string to be stored in hash map
		String Bookings = name + "," + hotels + "," + checkIn + "," + checkOut + "," + String.valueOf(rooms)
		+ "," + String.valueOf(adults) + "," + String.valueOf(children);
		
		// Create hash map
		HashMap<String, String> h = new HashMap<String, String>();
		File file = new File("bookings.properties");
		FileInputStream reader = new FileInputStream(file);
		Properties properties = new Properties();
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
	}

	
}
