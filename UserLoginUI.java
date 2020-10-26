// This is the Boundary Class
// Package
//package BugTracking;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;

public class UserLoginUI extends Application 
{    
    Scene scene1; // Used for User Login 
    Scene scene2; // Used for Successful login
    int clearance = 0; // Used to contain the user's clearance level
    @Override
    public void start(Stage primaryStage) 
    {
        //========================================================================
        // Scene 1 (User Login Screen)
        //========================================================================
        primaryStage.setTitle("UserLoginUI"); // Name of Page

        Label labelUsername = new Label("Username:"); // Username Box
        TextField usernameTextField = new TextField ();
        usernameTextField.setPromptText("Enter your username");

        Label labelPassword = new Label("Password:");  // Obscure Password Box
        PasswordField passwordTextField = new PasswordField ();
        passwordTextField.setPromptText("Enter Password");
        
        Alert alert = new Alert(AlertType.WARNING); // Joseph
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Invalid Authentication!");
        alert.setContentText("Wrong Username or Password.");

        Button btn1 = new Button(); // Login Button
        btn1.setText("Login");
        btn1.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happend when the button is clicked.   
            @Override
            public void handle(ActionEvent event)  // Joseph
            {// Initializes the Controller Class UserLoginController
            	UserLoginController loginController = new UserLoginController(); 
            	if(loginController.ValidateUser(usernameTextField.getText(), passwordTextField.getText()) == true)
            	{
                    primaryStage.setScene(scene2); // Loads Scene 2, Successful login screen
                    System.out.println("User has logged in!"); // Prints to command line
                    clearance = loginController.getClearance(usernameTextField.getText(), passwordTextField.getText());
                    changeScene(clearance); 
            	}
            	else
            	{
                   alert.showAndWait();
            	}
                usernameTextField.clear(); // Clears Username Box
                passwordTextField.clear(); // Clears Password Box
            }	
        });
  
        HBox hbUsername = new HBox(); // Combine components for Username Field
        hbUsername.getChildren().addAll(labelUsername, usernameTextField);
        hbUsername.setSpacing(10);

        HBox hbPassword = new HBox(); // Combine components for password Field
        hbPassword.getChildren().addAll(labelPassword, passwordTextField);
        hbPassword.setSpacing(10);
        
        VBox layout1 = new VBox(20);  // Combining all Elements to a single screen  
        layout1.getChildren().addAll(hbUsername, hbPassword, btn1);
        scene1= new Scene(layout1, 300, 250);

        //========================================================================
        // Scene2 (Logout Screen which shows after user has been authenticated)
        //========================================================================
        Label label2 = new Label("This is the second scene"); // On-Screen text

        Button btn2 = new Button(); // Logout Button
        btn2.setText("Logout");
        btn2.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happend when the button is clicked.  
            @Override
            public void handle(ActionEvent event) 
            {
                primaryStage.setScene(scene1);
                System.out.println("User has logged out!");
            }
        });
        VBox layout2 = new VBox(20);  // Combining all Elements to a single screen    
        layout2.getChildren().addAll(label2, btn2);
        scene2= new Scene(layout2, 300, 250);
        
        //========================================================================
        // Code to set the Login page when startup
        //========================================================================
        primaryStage.setScene(scene1); // Sets the login page to appear
        primaryStage.show(); // Displays the page to appear
    }

    public static void changeScene(int clearance)
    {
        // Decision making code here to display corresponding scene according to clearance level 
        if (clearance == 1)
        {// Call the Reporter page UI here
            System.out.println("Display Reporters page");
        }
        else if (clearance == 2)
        {// Call the Developer page UI here
            System.out.println("Display Developer page");
        }
        else if (clearance == 3)
        {// Call the Reviewer page UI here
            System.out.println("Display Reviewer page");
        }
        else if (clearance == 4)
        {// Call the Triager page UI here
            System.out.println("Display Triager page");
        }
        else
        { // Display error message here!
            System.out.println("Illegal clearance! Please re-loggin and try again!");
        }
    }
}  

/* Websites used:
http://www.learningaboutelectronics.com/Articles/How-to-create-multiple-scenes-and-switch-between-scenes-in-JavaFX.php
https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
https://docs.oracle.com/javafx/2/ui_controls/password-field.htm
http://www.java2s.com/Tutorials/Java/JavaFX/0470__JavaFX_PasswordField.htm
*/