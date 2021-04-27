package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditingController 
{
	@FXML
	private AnchorPane editingPanel;

	@FXML
	public void toHome(ActionEvent event) throws IOException {
		
		editingPanel = FXMLLoader.load(getClass().getResource("Main.fxml")); 
		Scene scene = new Scene(editingPanel);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
