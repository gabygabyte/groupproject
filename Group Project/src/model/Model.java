package model;


import application.HotelSelectionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class Model {

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
