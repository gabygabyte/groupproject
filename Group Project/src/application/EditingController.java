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
    		 		+ " please try again.").showAndWait();
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
		
		String type; //used to set which type of alert should be printed
		
		//clears the corresponding button and prints a confirmation 	
		if(nameEdit == pressedButton) {
			nameText.clear();
			type = "name";
			Model.printAlert(type);
		}
		if(hotelEdit == pressedButton) {
			hotelText.clear();
			type = "hotels";
			Model.printAlert(type);
		}
		if(checkinEdit == pressedButton) {
			checkinText.clear();
			type = "check in date";
			Model.printAlert(type);
		}
		if(checkoutEdit == pressedButton) {
			checkoutText.clear();
			type = "check out date";
			Model.printAlert(type);
		}
		if(roomsEdit == pressedButton) {
			roomText.clear();
			type = "number of rooms";
			Model.printAlert(type);
		}
		if(adultsEdit == pressedButton) {
			adultsText.clear();
			type = "number of adult guests";
			Model.printAlert(type);
		}
		if(childrenEdit == pressedButton) {
			childrenText.clear();
			type = "number of child guests";
			Model.printAlert(type);
		}
	}
	
	@FXML
	public void updateInfo(ActionEvent event) throws IOException, ParseException {
		//Check for empty fields
		String fields = "";
    	Model.checkEmpty(fields, nameText, emailText, checkinText, checkoutText, roomText, adultsText, childrenText);
    	
    	//Verify if check out date is after check in date
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	String checkInDate = checkinText.getText();
    	String checkOutDate = checkoutText.getText();
    	Date date1 = sdf.parse(checkInDate);
    	Date date2 = sdf.parse(checkOutDate);
    	if (date1.after(date2)) {
            fields = "incomplete";
            new Alert(Alert.AlertType.ERROR, "Conflicting dates: your check out date is before your check in date! ").showAndWait();
        }
    	
    	if (fields.isEmpty()) {
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
    		
    		Model.saveInfo(emailAddress, Bookings);
    		
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
