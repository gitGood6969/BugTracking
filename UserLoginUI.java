// This is the Boundary Class
// Package
package BugTracking;

// Import Libraries
import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.stage.Stage; 
import static javafx.application.Application.launch;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserLoginUI extends Application {    
 
    Scene scene1;
    Scene scene2;
    
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("UserLoginUI");
        
        // Scene1 implementation code here
        Label labelUsername = new Label("Username:");
        TextField usernameTextField = new TextField ();
        Label labelPassword = new Label("Password:");  // change to obscure password
        TextField passwordTextField = new TextField ();
        Button btn1 = new Button();
        btn1.setText("Login");
        btn1.setOnAction(new EventHandler<ActionEvent>()  // do event handler to capture user input
        {
            // Code here handles what happens when the button is clicled.
            @Override
            public void handle(ActionEvent event)  // Joseph
            {
            	UserLoginController loginController = new UserLoginController();
            	if(loginController.ValidateUser(usernameTextField.getText(), passwordTextField.getText()) == true)
            	{
            		primaryStage.setScene(scene2);
            		System.out.println("User has logged in!");
            	}
            	else
            	{
            		System.out.println("User unauthorised.");
            	}
            }	
        });
        // Grouping elements together
        HBox hbUsername = new HBox(); // For Username Field
        hbUsername.getChildren().addAll(labelUsername, usernameTextField);
        hbUsername.setSpacing(10);
        HBox hbPassword = new HBox(); // For Password Field
        hbPassword.getChildren().addAll(labelPassword, passwordTextField);
        hbPassword.setSpacing(10);
        
        VBox layout1 = new VBox(20);     
        layout1.getChildren().addAll(hbUsername, hbPassword, btn1);
        scene1= new Scene(layout1, 300, 250);
        
        // Scene2 implementation
        Label label2 = new Label("This is the second scene");
        Button btn2 = new Button();
        btn2.setText("Logout");
        btn2.setOnAction(new EventHandler<ActionEvent>() 
        {
            // Code here handles what happens when the button is clicled.
            @Override
            public void handle(ActionEvent event) 
            {
                primaryStage.setScene(scene1);
                System.out.println("User has logged out!");
            }
        });
        VBox layout2 = new VBox(20);     
        layout2.getChildren().addAll(label2, btn2);
        scene2= new Scene(layout2, 300, 250);

        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}  

/*
Need to have variables:
userName
userPassword
EmployeeID

Need to have Methods:
Display()
OnLoginSubmit(userName, userPassword, EmployeeID)
DisplaySuccess();
DisplayError();
CheckNull();
*/


/* Websites used:
http://www.learningaboutelectronics.com/Articles/How-to-create-multiple-scenes-and-switch-between-scenes-in-JavaFX.php
https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
https://docs.oracle.com/javafx/2/ui_controls/password-field.htm
*/