package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.net.URL;
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
	private static HashMap<String, String> h = new HashMap<String, String>();
	private static Properties properties = new Properties();//Create properties file to store hash map
	
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
		String compKey = nameText.getText().toString();
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
    		info = "Invalid name"; //The name doesn't go with any of the hotel info
    	 }
    	 
    	 if(info.equals("Invalid name")) { //when name enter isn't a valid key print an error
    		 new Alert(Alert.AlertType.ERROR, "Entered name is not associated with any information, "
    		 		+ "please try again.").showAndWait();
    	 }
    	 else { 
	    	 String[] infoStr = info.split(","); //split user info by the commas added in the booking process
	    	 
	    	 //put all the info in the proper text box
	    	 hotelText.setText(infoStr[0]);
	    	 emailText.setText(infoStr[1]);
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
			
		if(emailEdit == pressedButton) {
			emailText.clear();
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
	public void Update(ActionEvent event) throws IOException {
		
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { //start name text box with message
		nameText.setText("Enter name associated with information here then press search");
	}
}
