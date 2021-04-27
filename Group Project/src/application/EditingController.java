package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditingController 
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
    private TextField hotelText;

    @FXML
    private TextField childrenText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField checkoutText;

    @FXML
    private TextField adultsText;
    
    @FXML
    private TextField emailText;

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
    	 
    	 System.out.println(info);
    	 
    	 /*
    	 hotelText.setText();
    	 emailText.setText();
    	 checkinText.setText();
    	 checkoutText.setText();
    	 roomText.setText();
    	 adultsText.setText();
    	 childrenText.setText();*/
	}
	
	@FXML
	public void Update(ActionEvent event) throws IOException {
		
		
	}
}
