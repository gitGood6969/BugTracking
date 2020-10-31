// Boundary Class
// Package
//package BugTracking;

// Import Libraries
import javafx.scene.Scene;                 // To use Scene 
import javafx.stage.Stage;                 // To use Stage 
import javafx.scene.layout.GridPane;       // To use Grid 
import javafx.geometry.Pos;                // To use Pos 
import javafx.geometry.Insets;             // To use Insets 
import javafx.scene.text.Text;             // To use Text 
import javafx.scene.control.TextArea;      // To use TextArea
import javafx.scene.layout.BackgroundFill; // To use BackgroundFill 
import javafx.scene.paint.Color;           // To use Color
import javafx.scene.layout.CornerRadii;    // To use CornerRadii
import javafx.scene.layout.Background;     // To use Background
import javafx.scene.control.Button;        // To use Button
import javafx.event.EventHandler;          // To use Event Handler
import javafx.event.ActionEvent;           // To use Action Event

public class ReviewerBugListUI
{
    public ReviewerBugListUI() {} // Default constructor
    
    public static Scene create (Stage stage)
    {
        Scene scene; // Create the backdrop for elements to be placed

        GridPane grid = new GridPane(); // Create Grid type backdrop to place elements on
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text bugListLabel = new Text("Bug Fixed"); // Create a text label/header
        TextArea listFixed = new TextArea(); // Create list to hold "list of bugs"
        
        ReviewerBugListController ctrl = new ReviewerBugListController();
        String[] strArray = ctrl.ViewListOfBugs(Integer.toString(UserLoginUI.userRole), Integer.toString(1));
        for(int i = 0; i < strArray.length; i++)
        {
            if(i==0)
            {listFixed.appendText(strArray[i]);}
            else
            {listFixed.appendText("\n" + strArray[i]);}
        }        
        listFixed.setEditable(false); // Don't allow users to edit the list
        
        Text bugListLabel2 = new Text("Bug Unfixed"); // Create a text label/header
        TextArea listUnfixed = new TextArea(); // Create list to hold "list of bugs"
        
        String[] strArray2 = ctrl.ViewListOfBugs(Integer.toString(UserLoginUI.userRole), Integer.toString(0));
        for(int i = 0; i < strArray2.length; i++)
		{
			if(i==0)
				listUnfixed.appendText(strArray2[i]);
			else
				listUnfixed.appendText("\n" + strArray2[i]);
		}        
        listUnfixed.setEditable(false); // Don't allow users to edit the list

        Button buttonBack = new Button("Back");  // "back" button to go back the Developer homepage
        buttonBack.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {stage.setScene(UserLoginUI.ReviewerPage(stage));}
        });

        grid.add(bugListLabel, 0, 0); // Adding label to grid
        grid.add(listFixed, 0, 1);    // Adding list to grid
        grid.add(bugListLabel2, 0, 2);
        grid.add(listUnfixed, 0, 3);
        grid.add(buttonBack, 0, 4);   // Adding the "back" button to grid 

        // Change the color of the background
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);    // Adding Background to Grid

        scene = new Scene(grid, 450, 400); // Adding Grid to Scene and setting dimensions
        stage.setTitle("Reviewer Bug List");
        stage.setScene(scene);
        stage.show();
        return scene; 
    }
}