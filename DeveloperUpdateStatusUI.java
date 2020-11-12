// Boundary Class
// [User Stories: #49]

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

public class DeveloperUpdateStatusUI
{
    static String input = "";
	
    public DeveloperUpdateStatusUI() {} // Default constructor
    
    public static Scene create (Stage stage)
    {
        Scene scene; // Create the backdrop for elements to be placed

        GridPane grid = new GridPane(); // Create Grid type backdrop to place elements on
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text bugListLabel = new Text("Bug List assigned to me"); // Create a text label/header
        TextArea list = new TextArea(); // Create list to hold "list of bugs"
        
        DeveloperUpdateStatusController ctrl = new DeveloperUpdateStatusController();
        String[] strArray = getListOfBugs(ctrl);
        for(int i = 0; i < strArray.length; i++)
        {
            if(i==0)
            {list.appendText((i+1) +". "+strArray[i]);}
            else
            {list.appendText("\n" + (i+1) + ". " + strArray[i]);}
        }
        
        list.setEditable(false); // Don't allow users to edit the list
        
        TextField bugNum = new TextField();
        bugNum.setText("Enter Bug Number to update.");
        bugNum.setOnMouseClicked(event-> {bugNum.clear();});
        
        Button nextBtn = new Button("Continue");  // "back" button to go back the Developer homepage
        nextBtn.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {
            	input = bugNum.getText();
            	if (bugNum.getText() == null || bugNum.getText().trim().isEmpty())
                {
                    Alert empty = new Alert(AlertType.WARNING);
                    empty.setHeaderText("Empty Field Detected.");
                    empty.setContentText("You have not typed in anything!");
                    empty.showAndWait();
                    bugNum.clear();
                }
            	else if(bugNum.getText().matches(".*\\s.*") || !bugNum.getText().matches("[0-9]+") || Integer.parseInt(bugNum.getText())<=0)
            	{
            		Alert space = new Alert(AlertType.WARNING);
                    space.setHeaderText("Bad input.");
                    space.setContentText("Please enter a number from 1 onwards!");
                    space.showAndWait();
                    bugNum.clear();
            	}
            	else if(Integer.parseInt(bugNum.getText())>strArray.length)
            	{
            		Alert outOfBounds = new Alert(AlertType.ERROR);
                    outOfBounds.setHeaderText("Failed Update");
                    outOfBounds.setContentText("The Bug Number " + input + " was not found.");
                    outOfBounds.showAndWait();
                    bugNum.clear();
            	}
            	else
            	{
            		//System.out.println(strArray[Integer.parseInt(input)-1]);
            		DeveloperUpdateStatusController ctrl = new DeveloperUpdateStatusController();
            		String[] strArray2 = ctrl.DeveloperGetBug(strArray[Integer.parseInt(input)-1], 0);
            		stage.setScene(scene2(stage, strArray2));
            	}
            	
            }
        });

        Button buttonBack = new Button("Back");  // "back" button to go back the Developer homepage
        buttonBack.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {stage.setScene(UserLoginUI.DeveloperPage(stage));}
        });
        
        HBox hbox = new HBox();
        hbox.getChildren().add(buttonBack);
        Pane filler = new Pane();
        hbox.getChildren().add(filler);
        HBox.setHgrow(filler, Priority.ALWAYS);
        hbox.getChildren().add(nextBtn);

        grid.addRow(0, bugListLabel); // Adding label to grid
        grid.addRow(1, list);         // Adding list to grid
        grid.addRow(2, bugNum);   // Adding the "back" button to grid 
        grid.addRow(3, hbox);
      
        
        // Change the color of the background
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);    // Adding Background to Grid

        scene = new Scene(grid, 450, 400); // Adding Grid to Scene and setting dimensions
        stage.setTitle("Developer Update Status Page");
        stage.setScene(scene);
        stage.show();
        return scene; 
    }

    public static String[] getListOfBugs(DeveloperUpdateStatusController ctrl)
    {
        String role = Integer.toString(UserLoginUI.userRole);
        String id = Integer.toString(UserLoginUI.uID);
        String[] strArray = ctrl.ViewListOfBugs(role, id);
        return strArray;
    }
    
    public static Scene scene2 (Stage stage, String[] bugInfo)
    {
    	GridPane grid = new GridPane(); // Create Grid type backdrop to place elements on
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setHgap(30);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Successfully Updated!");
        alert.setContentText("You will be moved back to the previous page!");
        
        Label bugNameLabel = new Label("Bug Name:");
        TextField bugNameField = new TextField();
        bugNameField.setPrefHeight(30);
        bugNameField.setText(bugInfo[4]);
        bugNameField.setEditable(false);
        
        Label bugDescLabel = new Label("Bug Description:");
        TextArea bugDescArea = new TextArea();
        bugDescArea.setWrapText(true);
        bugDescArea.setPrefHeight(100);
        bugDescArea.setPrefWidth(200);
        bugDescArea.setText(bugInfo[5]);
        bugDescArea.setEditable(false);
        
        Label bugStatusLabel = new Label("Bug Status:");
        TextField bugStatusField = new TextField();
        bugStatusField.setText(bugInfo[3]);
        bugStatusField.setOnMouseClicked(event-> {bugStatusField.clear();});
        
        Button buttonBack = new Button("Back");  // "back" button to go back the Developer homepage
        buttonBack.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {stage.setScene(create(stage));}
        });
        
        Button buttonUpdate = new Button("Update");  // "back" button to go back the Developer homepage
        buttonUpdate.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {
            	if(bugStatusField.getText() == null || bugStatusField.getText().trim().isEmpty())
            	{
                    Alert empty = new Alert(AlertType.WARNING);
                    empty.setHeaderText("Empty Field Detected.");
                    empty.setContentText("Bug Status must not be left Empty");
                    empty.showAndWait();
                    bugStatusField.setText(bugInfo[3]);
            	}
            	else if(bugStatusField.getText().toLowerCase().equals("fixed") || bugStatusField.getText().toLowerCase().equals("fixing"))
            	{
                    String status = bugStatusField.getText().toLowerCase();
                    DeveloperUpdateStatusController controller = new DeveloperUpdateStatusController();
                    if(controller.DeveloperUpdateStatus(bugInfo[4], bugInfo[5], status, 0) == true)
                    {
                        alert.showAndWait();
                        stage.setScene(create(stage));
                    }
            		
            	}
            	else
            	{
            		Alert badStatus = new Alert(AlertType.ERROR);
                    badStatus.setHeaderText("Bad Status detected");
                    badStatus.setContentText("Bug Status can only be changed to fixed or fixing");
                    badStatus.showAndWait();
                    bugStatusField.setText(bugInfo[3]);
            	}
            }
        });
        
        HBox hbox = new HBox();
        hbox.getChildren().add(buttonUpdate);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
             
        grid.add(bugNameLabel, 0, 0);
        grid.add(bugNameField, 1, 0);
        grid.add(bugDescLabel, 0, 1);
        grid.add(bugDescArea, 1, 1);
        grid.add(bugStatusLabel, 0, 2);
        grid.add(bugStatusField, 1, 2);
        grid.add(buttonBack, 0, 3);
        grid.add(hbox, 1, 3);
    	
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);    // Adding Background to Grid
        
        Scene scene2 = new Scene(grid, 450, 400); // Adding Grid to Scene and setting dimensions
        stage.setTitle("Bug Information");
        stage.setScene(scene2);
        stage.show();
        return scene2;
    }
}
