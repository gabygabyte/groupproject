package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * This class contains methods for the Hotel Selections interface.
 * Methods will allow the user to select from 6 different hotel branches.
 * They will be able to view what options they have selected as well as clear
 * all options. Once they have selected the desired hotels, the next button will
 * take the user to the booking scene 
 */

public class HotelSelectionController {
	
	@FXML
	private AnchorPane mainPane;
	
	public static String[] hotelArray = new String[6];	//array that will contain the selected hotels
	public String hotelSelection; //to save hotel name as a string
	public int arrayIndex; //to keep track of where in the array a hotel name will be save
	public boolean match;
	
	
	/*
	 * This method will take user back to the Main scene
	 */
	@FXML
	public void clickHome(ActionEvent event) throws IOException {
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml")); 
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	
	
	/*
	 *  This method will get the button ID and then
	 *   set the public string: hotelSelection to 
	 *   the appropriate hotel name
	 */
	@FXML
	public void setHotel(ActionEvent event) throws IOException {
				
		hotelSelection = ((Node) event.getSource()).getId(); //saves hotel name to hotelSelection
	
	}
	
	
	/*
	 * This method will add the string that saved onto
	 * hotelSelection to the array hotelArray at index saved onto arrayIndex;
	 * If that hotel has already been added to the array, a message
	 * will display, letting user know to select something else
	 */
	@FXML
	public void addHotel(ActionEvent event) throws IOException {
		
		 checkHotelArray(); //checks if hotel has already been added
		
		  //if checkHotelArray returns false 
		  if(!match) {
			hotelArray[arrayIndex] = hotelSelection; //save hotelSelection to an index in hotelArray
			arrayIndex++; //increment hotelIndex for next entry
				Alert a = new Alert(Alert.AlertType.CONFIRMATION, hotelSelection + " has been successfully added.");
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
	
	
	
	
	/*
	 * WILL PASS HOTELS SOMEHOW. THIS REMAINS TO BE FIGURED OUT. 
	 */
	
	public void getHotels() throws IOException {
		
	
	}
	
	
	
	/*
	 * This method will clear any hotels that had been saved in the 
	 * hotelsArray array. It will also clear hotelSelection and set
	 * the arrayIndex back to 0
	 */
	
	@FXML
	public void clearHotels(ActionEvent event) throws IOException {
		
		for(int i=0; i<hotelArray.length; i++) { //traverse through array
			hotelArray[i] = null; //set each entry to null
		}
		hotelSelection = null; //clear anything that was saved to hotelSelection
		arrayIndex = 0; //set array index back to start
		Alert a = new Alert(Alert.AlertType.CONFIRMATION, "All selections have been cleared.");
		a.show();//show confirmation message
	}
	
	
	
	/*
	 * This method will check to see if the value stored
	 * in hotelSelection variable is already present in
	 * hotelsArray. Sets boolean match to true if present,
	 *  otherwise will set the variable to false
	 */
	@FXML
	public void checkHotelArray() throws IOException {
		
		for(int i=0; i<hotelArray.length; i++) { //traverse through each array index
			if (hotelSelection == hotelArray[i]) { //tests for a matching string
				match = true; //match set to true if match found
				break; //break out of loop
			}
		else {
			match = false; //if no match found return false
		}
		}
	}
	
	
	/*
	 * This method displays a message stating the
	 * names of the hotels which have already been added.
	 * If the array is empty, it displays a message saying
	 * that no hotels have been added yet
	 */
	@FXML
	public void displayHotelArray(ActionEvent event) throws IOException {
		String hotels = "";
		for(int i=0; i<hotelArray.length; i++) { //traverse through each array index
			if (hotelArray[i] != null) { //tests if string is empty
				hotels = hotels+"\n"+hotelArray[i]; //append hotel at each index to hotels variable
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
	
	
	/*
	 * This method will take user to the booking scene
	 */
	@FXML
	public void bookingScene(ActionEvent event) throws IOException {

		mainPane = FXMLLoader.load(getClass().getResource("Booking.fxml")); //
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	
	}
	
}
