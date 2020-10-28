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
import javafx.scene.control.Hyperlink;
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
    public static int userRole = 0;
    public static int uID = 0;

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
                        int[] userID = loginController.getUser(usernameTextField.getText(), passwordTextField.getText());
                        
                        if (userID[0] == 1) // Call the Reporter page UI here
                        {
                            userRole = userID[0];
                            uID = userID[1];
                            stage.setScene(ReporterPage(stage));
                        } 
                        else if  (userID[0] == 2) // Call the Developer page UI here
                        {
                            userRole = userID[0];
                            uID = userID[1];
                            stage.setScene(DeveloperPage(stage));
                        }
                        else if (userID[0] == 3) // Call the Reviewer page UI here
                        {
                            userRole = userID[0];
                            uID = userID[1];
                            stage.setScene(ReviewerPage(stage));
                        }   
                        else if (userID[0] == 4) // Call the Triager page UI here
                        {
                            userRole = userID[0];
                            uID = userID[1];
                            stage.setScene(TriagerPage(stage));
                        }  
                        else // Display error message here! 
                        {System.out.println("No role found.");}  
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
        
        Scene scene1 = new Scene(grid, 450, 400); // Adjust screen size
        return scene1; 
    }

    //=======================================================
    // REPORTER PAGE
    //=======================================================
    public static Scene ReporterPage (Stage stage)
    {
    	stage.setTitle("Reporter Page");
        
        GridPane grid = new GridPane(); // Setup the grid background
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setSpacing(10);

        Text title = new Text("Reporter's Task");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[] 
        {
            new Hyperlink("Report Bug"),
            new Hyperlink("View Bugs Reported"),
            new Hyperlink("Search Bugs")
            //any more you can add in 
        };
        
        // i<2 (the number 2 will change if more hyperlinks is added to options[])
        for (int i=0; i<3; i++) 
        {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);

            if (options[i].onMouseClickedProperty() != null && options[i].getText() == "Report Bug") 
            {
                options[i].setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent t)  
                    {stage.setScene(BugReportPageUI.create(stage));}  
                });
            }
            else if (options[i].onMouseClickedProperty() != null && options[i].getText() == "View Bugs Reported")
            {
                options[i].setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent t) 
                    {stage.setScene(ReporterBugListUI.create(stage));}    
                });
            }
            else if (options[i].onMouseClickedProperty() != null && options[i].getText() == "Search Bugs")
            {
                options[i].setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent t) {}
                });
            }
        }

        Button logoutBtn = new Button("Logout"); // Logout Button
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happend when the button is clicked.  
            @Override
            public void handle(ActionEvent event) 
            {
                stage.setScene(UserLoginUI.create(stage));
                System.out.println("User has logged out!");
            }
        });
        grid.add(vbox, 0, 0);
        grid.add(logoutBtn, 0, 1);

        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene2= new Scene(grid, 450, 400);
        return scene2;
    } 

    //=======================================================
    // DEVELOPER PAGE
    //=======================================================
    public static Scene DeveloperPage (Stage stage) 
    { 	
    	stage.setTitle("Developer Page");
    	
        GridPane grid = new GridPane(); // Setup the grid background
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setSpacing(10);

        Text title = new Text("Reporter's Task");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[] 
        {
            new Hyperlink("View Bugs Assigned"),
            new Hyperlink("Update Bug Status"),
            new Hyperlink("Search Bugs")						
            //any more you can add in 
        };
		
        // i<2 (the number 2 will change if more hyperlinks is added to options[])
        for (int i=0; i<3; i++) 
        {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);

            if (options[i].onMouseClickedProperty() != null && options[i].getText() == "Report Bug") 
            {
                options[i].setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent t) 
                    {
                        //stage.setScene(DeveloperBugListUI.create(stage)); 
                    }
                });
            }
        }

        Button logoutBtn = new Button("Logout"); // Logout Button
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happend when the button is clicked.  
            @Override
            public void handle(ActionEvent event) 
            {
                stage.setScene(UserLoginUI.create(stage));
                System.out.println("User has logged out!");
            }
        });
        grid.add(vbox, 0, 0);
        grid.add(logoutBtn, 0, 1);

        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene2= new Scene(grid, 450, 400);
        return scene2;
    } 

    //=======================================================
    // REVIEWER PAGE
    //=======================================================
    public static Scene ReviewerPage (Stage stage) 
    {
    	stage.setTitle("Reviewer Page");
    	
        GridPane grid = new GridPane(); // Setup the grid background
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setSpacing(10);

        Text title = new Text("Reviewer's Task");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[] 
        {
            new Hyperlink("View list of Bugs"),
            new Hyperlink("Update Bug Status"),
            new Hyperlink("Search Bugs")			
            //any more you can add in 
        };
		
        // i<2 (the number 2 will change if more hyperlinks is added to options[])
        for (int i=0; i<3; i++) 
        {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);

            if (options[i].onMouseClickedProperty() != null && options[i].getText() == "Report Bug") 
            {
                options[i].setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent t) 
                    {
                        //stage.setScene(ReviewerBugListUI.create(stage)); 
                    }
                });
            }
        }

        Button logoutBtn = new Button("Logout"); // Logout Button
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happend when the button is clicked.  
            @Override
            public void handle(ActionEvent event) 
            {
                stage.setScene(UserLoginUI.create(stage));
                System.out.println("User has logged out!");
            }
        });
        grid.add(vbox, 0, 0);
        grid.add(logoutBtn, 0, 1);

        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene2= new Scene(grid, 450, 400);
        return scene2;
    }

    //=======================================================
    // TRIAGER PAGE
    //=======================================================
    public static Scene TriagerPage (Stage stage) 
    { 	
    	stage.setTitle("Triager Page");
    	
        GridPane grid = new GridPane(); // Setup the grid background
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setSpacing(10);

        Text title = new Text("Triager's Task");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[] 
        {
            new Hyperlink("View list of Bugs"),
            new Hyperlink("Update Bug Status"),
            new Hyperlink("Search Bugs"),
            new Hyperlink("Generate Bug Report")
            //any more you can add in 
        };
		
        // i<2 (the number 2 will change if more hyperlinks is added to options[])
        for (int i=0; i<4; i++) 
        {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);

            if (options[i].onMouseClickedProperty() != null && options[i].getText() == "Report Bug")
            {
                options[i].setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent t) 
                    {
                        //stage.setScene(TraigerBugListUI.create(stage)); 
                    }
                });
            }
        }

        Button logoutBtn = new Button("Logout"); // Logout Button
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happend when the button is clicked.  
            @Override
            public void handle(ActionEvent event) 
            {
                    stage.setScene(UserLoginUI.create(stage));
                    System.out.println("User has logged out!");
            }
        });
        grid.add(vbox, 0, 0);
        grid.add(logoutBtn, 0, 1);

        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene scene2= new Scene(grid, 450, 400);
        return scene2;
    }
}