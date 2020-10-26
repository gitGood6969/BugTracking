// This is the Boundary Class
// Package
//package BugTracking;

// import libraries
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.stage.Stage; 
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.application.Application;

public class UserLoginUI 
{
    public static Scene create(Stage stage)
    {
        stage.setTitle("UserLoginUI"); // Name of Page

        GridPane grid = new GridPane(); // Setup the grid background
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Adjusting background colour
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Label labelUsername = new Label("Username:"); // Username Box
        TextField usernameTextField = new TextField ();
        usernameTextField.setPromptText("Enter your username");

        Label labelPassword = new Label("Password:");  // Obscure Password Box
        PasswordField passwordTextField = new PasswordField ();
        passwordTextField.setPromptText("Enter Password");

        Alert alert = new Alert(AlertType.WARNING); // Invalid authentication warning box [Joseph]
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Invalid Authentication!");
        alert.setContentText("Wrong Username or Password.");

        Alert alert2 = new Alert(AlertType.ERROR); // Empty field error box [Joseph]
        alert2.setTitle("Error Dialog");
        alert2.setHeaderText("Empty Field Detected");
        alert2.setContentText("Username and Password are required!");

        Button btn1 = new Button(); // Login Button
        btn1.setText("Login");
        btn1.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happend when the button is clicked.   
            @Override
            public void handle(ActionEvent event)  // Joseph
            {// Initializes the Controller Class UserLoginController
                UserLoginController loginController = new UserLoginController();
                if(usernameTextField.getText() == null || usernameTextField.getText().trim().isEmpty() 
                    || passwordTextField.getText() == null || passwordTextField.getText().trim().isEmpty())
                {alert2.showAndWait();}
                else
                {
                    if(loginController.ValidateUser(usernameTextField.getText(), passwordTextField.getText()) == true)
                    {
	                //primaryStage.setScene(scene2);             // Loads Scene 2, Successful login screen
	                //System.out.println("User has logged in!"); // Prints to command line
                        int clearance = loginController.getClearance(usernameTextField.getText(), passwordTextField.getText());
                        if (clearance == 1)
                        {stage.setScene(ReporterHomePage.create(stage));} // Call the Reporter page UI here
                        else if (clearance == 2)
                        {System.out.println("Display Developer page");}  // Call the Developer page UI here
                        else if (clearance == 3)
                        {System.out.println("Display Reviewer page");}   // Call the Reviewer page UI here
                        else if (clearance == 4)
                        {System.out.println("Display Triager page");}    // Call the Triager page UI here
                        else
                        {System.out.println("Illegal clearance! Please re-loggin and try again!");}// Display error message here!   
                    }
                    else
                    {alert.showAndWait();}
 
                    usernameTextField.clear(); // Clears Username Box
                    passwordTextField.clear(); // Clears Password Box
                }
            }
        });
  
        Text scenetitle = new Text("Login Page"); // Creating Scene
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1); // Add scene title 
        
        grid.add(labelUsername, 0, 1);    // Add label Username
        grid.add(usernameTextField, 1, 1);// Add username text box
        
        grid.add(labelPassword, 0, 2);    // Add label password
        grid.add(passwordTextField, 1, 2);// Add password text box
        
        HBox hbBtn = new HBox(10);        
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn1);
        grid.add(hbBtn, 1, 4);            // Add Login Button
        
        Scene scene1 = new Scene(grid, 350, 300); // Adjust screen size
        return scene1; 
    }
}

/* Websites used:
http://www.learningaboutelectronics.com/Articles/How-to-create-multiple-scenes-and-switch-between-scenes-in-JavaFX.php
https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
https://docs.oracle.com/javafx/2/ui_controls/password-field.htm
http://www.java2s.com/Tutorials/Java/JavaFX/0470__JavaFX_PasswordField.htm
*/