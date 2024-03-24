package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	LoginModel loginModel = new LoginModel();
	@FXML
	private Label dbstatus;
	@FXML
	private PasswordField password;
	@FXML
	private Button loginButton;
	private boolean firstTimeUser = true;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(this.loginModel.isDatabaseConnected()){
			this.dbstatus.setText("Connected!");
		} else {
			this.dbstatus.setText("Not connected to database");
		}
	}
	
	/*
	 * Logic to show window
	 */
	public void Login(ActionEvent event) {
		try {
			if(isValidLogin()) {
				closeLoginWindow();
				
				if(this.loginModel.isLogin("1")) {
					showResetPasswordWindow();
				} else {
					showMainWindow();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Directs user to change the default password
	 */
	private void showResetPasswordWindow() {
		try {
			
			VBox root = (VBox)FXMLLoader.load(getClass().getClassLoader().getResource("view/reset.fxml"));
			Stage resetStage = new Stage();
			Scene scene = new Scene(root, 600, 600);
			resetStage.setScene(scene);
			resetStage.setTitle("Change Password");
			resetStage.show();
				
		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
	
	/*
	 * Directs user to the main page after resetting their password
	 */
	public void showMainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane)loader.load(getClass().getResource("view/main.fxml").openStream());
			Stage mainStage = new Stage();
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
			mainStage.setTitle("Main Page");
			mainStage.setResizable(false);
			mainStage.show();
				
		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
	
	/*
	 * To check if the login is valid
	 */
	private boolean isValidLogin() throws Exception {
		return loginModel.isLogin(password.getText());
	}
	
	/*
	 * Closes the current window
	 */
	private void closeLoginWindow() {
		Stage loginStage = (Stage) loginButton.getScene().getWindow();
		loginStage.close();
	}
	
	
}


