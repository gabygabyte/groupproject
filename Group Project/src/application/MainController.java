package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button editView;

    @FXML
    private Button book;
    
    @FXML
    private AnchorPane mainBack;

    @FXML
    void goToHotels(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelSelection.fxml"));
    	mainBack = loader.load();  	
    	Scene scene = new Scene(mainBack);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }

    @FXML
    void goToViewEdit(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Editing.fxml"));
    	mainBack = loader.load();    	
    	Scene scene = new Scene(mainBack);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
    }

}
