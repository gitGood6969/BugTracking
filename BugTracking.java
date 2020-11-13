// Driver Class
// Package
//package BugTracking;

// Import Libraries
import javafx.application.Application; 
import javafx.stage.Stage; 
//import java.util.ArrayList; // To use the ArrayList variable container
//import java.util.Arrays;    // To use Array containers

public class BugTracking extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setScene(UserLoginUI.create(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}


