// Boundary Class
// [User Stories: #76]

// Package
//package BugTracking;

// Import Libraries
import javafx.scene.Scene;                 // To use Scene 
import javafx.stage.Stage;                 // To use Stage 
import javafx.scene.layout.GridPane;       // To use Grid 
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;                // To use Pos 
import javafx.geometry.Insets;             // To use Insets 
import javafx.scene.text.Text;             // To use Text 
import javafx.scene.control.TextArea;      // To use TextArea
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BackgroundFill; // To use BackgroundFill 
import javafx.scene.paint.Color;           // To use Color
import javafx.scene.layout.CornerRadii;    // To use CornerRadii
import javafx.scene.layout.Background;     // To use Background
import javafx.scene.control.Alert;
import javafx.scene.control.Button;        // To use Button
import javafx.scene.control.Label;
import javafx.event.EventHandler;          // To use Event Handle
import javafx.event.ActionEvent;           // To use Action Event
import javafx.scene.control.ComboBox;      // To use the Dropdown combo box for selecting which Month
import java.util.ArrayList;                // To use the ArrayList variable container
import java.util.Arrays;                   // To use Array containers
import javafx.scene.control.DatePicker;    // To use the DatePicker/ Calendar 
import java.time.LocalDate;                // To be able to take in Date and manipulate it 

public class WeeklyBugReportUI 
{
    public WeeklyBugReportUI() {} // Default constructor

    public static Scene create (Stage stage)
    {
        Scene scene; // Create the backdrop for elements to be placed

        // Create Grid type backdrop to place elements on
        GridPane grid = new GridPane(); 
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create a text label/header
        Text bugListLabel = new Text("Report:"); 
        Text informUser = new Text("Please select a day:");

        // Create list to hold "list of bugs"
        TextArea list = new TextArea(); 

        // Adjust Size of Text Area (List)
        double height = 400; 
        double width = 600;  
        list.setPrefHeight(height);  //sets height of the TextArea  
        list.setPrefWidth(width);    //sets width of the TextArea 

        list.setEditable(false); // Don't allow users to edit fields
        list.setWrapText(true); // Set Wrap Text

        // Create the calendar/datepicker object
        DatePicker selectDate = new DatePicker();

        // Set default value to current date
        selectDate.setValue(LocalDate.now());

        // Error message for when invalid input is supplied to Dropdown/combo box
        Alert alert1 = new Alert(AlertType.ERROR); 
        alert1.setTitle("Something went wrong...");
        alert1.setHeaderText("Wrong Day Detected");
        alert1.setContentText("Please enter a valid Day!");


        // Adding a Generate Button
        Button generate = new Button("Generate Report"); 

        // Add an event handler to the generate button to process data when clicked   
        generate.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event)
            {
                // validate contents of dropdown/combo box 
                // Checks that it isn't null
                if (selectDate.getValue() != null) 
                {
                    LocalDate inputDate = selectDate.getValue();
                    
                    // Calls the controller class and retrieve the report
                    WeeklyBugReportController ctrl = new WeeklyBugReportController(); 
                    ArrayList<String> report = ctrl.compileBugReport(inputDate);

                    list.clear(); // Resets and empties the TextArea of previous Data
                    for (String str : report) // Display Report
                    {
                        list.appendText(str + "\n");
                    }
                }
                else 
                {
                    alert1.showAndWait();
                    selectDate.setValue(LocalDate.now()); // Reset the datepicker
                }  
            }
        });

        Button buttonBack = new Button("Back");  // "back" button to go back the Developer homepage
        buttonBack.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {stage.setScene(UserLoginUI.GenerateReport(stage));}
        });

        // Creating a hbox to store elements horizontally  
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.getChildren().add(informUser);  // Adding the label to tell users to select a day
        hbox.getChildren().add(selectDate);  // Adding datepicker/calendar box
        hbox.getChildren().add(generate);    // Adding "Generate" button

        // Putting elements together
        grid.addRow(0, hbox);         // Adding datepicker and generate report button to grid
        grid.addRow(1, bugListLabel); // Adding label to grid
        grid.addRow(2, list);         // Adding list to grid
        grid.addRow(3, buttonBack);   // Adding the "back" button to grid
        
        // Change the color of the background
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);    // Adding Background to Grid

        // Adding Grid to Scene and setting dimensions
        scene = new Scene(grid, 700, 500); 
        stage.setTitle("Weekly Bug Report Page"); // Title of the Page
        stage.setScene(scene); 
        stage.show(); // Display Scene
        return scene; 
    }
}
