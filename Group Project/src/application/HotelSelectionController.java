package application;
import model.Model;
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
	public static String hotelSelection; //to save hotel name as a string
	public static int arrayIndex; //to keep track of where in the array a hotel name will be save
	public static boolean match;
	
	
	/*
	 * This method will take user back to the Main scene
	 */
	@FXML
	public void clickHome(ActionEvent event) throws IOException {
		
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml")); //
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
		  Model.addHotel(hotelSelection, hotelArray, arrayIndex, match);
	}
	
	
	
	/*
	 * This method will clear any hotels that had been saved in the 
	 * hotelsArray array. It will also clear hotelSelection and set
	 * the arrayIndex back to 0
	 */
	
	@FXML
	public void clearHotels(ActionEvent event) throws IOException {
		
		Model.clearHotels(hotelArray, hotelSelection, arrayIndex);
	}
	
	
	
	/*
	 * This method will check to see if the value stored
	 * in hotelSelection variable is already present in
	 * hotelsArray. Sets boolean match to true if present,
	 *  otherwise will set the variable to false
	 */
	@FXML
	public void checkHotelArray() throws IOException {
		
		Model.checkHotelArray(hotelArray, hotelSelection, arrayIndex, match);
	}
	
	
	/*
	 * This method displays a message stating the
	 * names of the hotels which have already been added.
	 * If the array is empty, it displays a message saying
	 * that no hotels have been added yet
	 */
	@FXML
	public void displayHotelArray(ActionEvent event) throws IOException {
		
		Model.displayHotelArray(hotelArray, hotelSelection, arrayIndex);
	}
	
	
	/*
	 * This method will take user to the booking scene
	 * when they select a button
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
