// Boundary class 
// [User Stories: #80]

// Package
//package BugTracking;

// Import Libraries
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SearchByTitleUI 
{
    // Default Constructor
    public SearchByTitleUI(){}

    public static Scene create (Stage stage)
    {
        Scene scene; // Create the backdrop for elements to be placed

        GridPane grid = new GridPane(); // Create Grid type backdrop to place elements on
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField searchTextField = new TextField ();
        searchTextField.setText("Enter Bug Title here");
        searchTextField.setOnMouseClicked(event-> {searchTextField.clear();});
        
        TextArea results = new TextArea();
        results.setPrefWidth(300);
        results.setEditable(false);
        
        Button searchBtn = new Button("Search");  // "back" button to go back the Developer homepage
        searchBtn.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {
            	results.clear();
            	if (searchTextField.getText() == null || searchTextField.getText().trim().isEmpty())
                {
                    Alert empty = new Alert(AlertType.WARNING);
                    empty.setHeaderText("Failed Search");
                    empty.setContentText("You have not typed in anything!");
                    empty.showAndWait();
                    searchTextField.clear();
                }
            	else if(searchTextField.getText().matches(".*\\s.*"))
            	{
            		Alert space = new Alert(AlertType.WARNING);
                    space.setHeaderText("Failed Search");
                    space.setContentText("Please type only 1 word");
                    space.showAndWait();
                    searchTextField.clear();
            	}
            	else
            	{
                    String title = searchTextField.getText();
                    SearchByTitleController ctrl = new SearchByTitleController();
                    String[] strArray = ctrl.ValidateTitle(title);

                    if(strArray.length==0)
                    {results.appendText("No bug title found with " + title + ".");}
                    else
                    {
                        for(int i = 0; i < strArray.length; i++)
                        {
                            if(i==0) {results.appendText(strArray[i]);} 
                            else {results.appendText("\n" + strArray[i]);} 
                        }        		
                    }
            	}
            }
        });
          
        Button backBtn = new Button("Back");  // "back" button to go back the Developer homepage
        backBtn.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {stage.setScene(UserLoginUI.SearchPage(stage));}
        });

        grid.add(searchTextField, 0, 0); 			
        grid.add(searchBtn, 1, 0);       
        grid.addRow(1, results); 	
        grid.add(backBtn, 0, 2);
  
        // Change the color of the background
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);    // Adding Background to Grid

        scene = new Scene(grid, 550, 500); // Adding Grid to Scene and setting dimensions
        stage.setTitle("Search by Title");
        stage.setScene(scene);
        stage.show();
        return scene; 
    }
}
