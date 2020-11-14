// Boundary Class
// [User Stories: #73]

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BugCommentUI 
{
	static String input = "";
    public static Scene create(Stage stage)
    {
        Scene scene;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text bugListLabel = new Text("Bug List");
        TextArea list = new TextArea();
        list.setPrefHeight(300);

        //Code to get data from controller here
        BugCommentController controller = new BugCommentController();
        String[] strArray = GetListOfBugs(controller);
        for(int i = 0; i < strArray.length; i++)
        {
            if(i==0)
            {list.appendText((i+1) +". "+strArray[i]);}
            else
            {list.appendText("\n" + (i+1) + ". " + strArray[i]);}
        }
        list.setEditable(false);

        Button buttonBack = new Button("Back"); 
        buttonBack.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {
            	if(UserLoginUI.userRole == 1)
                {
                    stage.setScene(UserLoginUI.ReporterPage(stage));
                }
                else if(UserLoginUI.userRole == 2)
                {
                    stage.setScene(UserLoginUI.DeveloperPage(stage));
                }
                else if(UserLoginUI.userRole == 3)
                {
                    stage.setScene(UserLoginUI.ReviewerPage(stage));
                }
                else if(UserLoginUI.userRole == 4)
                {
                    stage.setScene(UserLoginUI.TriagerPage(stage));
                }
            }
        });
        
        TextField bugNum = new TextField();
        bugNum.setText("Enter Bug Number to comment on.");
        bugNum.setOnMouseClicked(event-> {bugNum.clear();});
        
        Button nextBtn = new Button("Continue");  // "back" button to go back the Developer homepage
        nextBtn.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {
            	input = bugNum.getText(); // Retrieve comments

                // Validate if comment is empty or NULL
            	if (bugNum.getText() == null || bugNum.getText().trim().isEmpty())
                {
                    Alert empty = new Alert(AlertType.WARNING);
                    empty.setHeaderText("Empty Field Detected.");
                    empty.setContentText("You have not typed in anything!");
                    empty.showAndWait();
                    bugNum.clear();
                }
            	else if(bugNum.getText().matches(".*\\s.*") || !bugNum.getText().matches("[0-9]+") || Integer.parseInt(bugNum.getText())<=0)
            	{ // Validate if comment is just empty spaces
                    Alert space = new Alert(AlertType.WARNING);
                    space.setHeaderText("Bad input.");
                    space.setContentText("Please enter a number from 1 onwards!");
                    space.showAndWait();
                    bugNum.clear();
            	}
            	else if(Integer.parseInt(bugNum.getText())>strArray.length)
            	{// check that the user input is within the arrays length if not it does not exist
                    Alert outOfBounds = new Alert(AlertType.ERROR);
                    outOfBounds.setHeaderText("Failed getting bug comments.");
                    outOfBounds.setContentText("The Bug Number " + input + " was not found.");
                    outOfBounds.showAndWait();
                    bugNum.clear();
            	}
            	else
            	{
                    // Calls controller class
                    BugCommentController ctrl = new BugCommentController();
                    
                    // Retrieve comments already made for the specific bug which has been selected
                    String[] strArray2 = ctrl.CreateComments(strArray[Integer.parseInt(input)-1]);

                    // Transition to scene2 to add a new comment for the bug
                    stage.setScene(scene2(stage, strArray2));
            	}
            	
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().add(buttonBack);
        Pane filler = new Pane();
        hbox.getChildren().add(filler);
        HBox.setHgrow(filler, Priority.ALWAYS);
        hbox.getChildren().add(nextBtn);

        grid.addRow(0, bugListLabel); 
        grid.addRow(1, list);         
        grid.addRow(2, bugNum);   
        grid.addRow(3, hbox);

        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        scene = new Scene(grid, 550, 500);
        stage.setTitle("Bug List for Comment");
        stage.setScene(scene);
        stage.show();
        return scene;
    }    
    
    // Method calls controller class to retrieve entire list of bugs
    public static String[] GetListOfBugs(BugCommentController controller)
    {
        String[] strArray = controller.ViewBugsForComment();
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
        alert.setHeaderText("Successfully posted your comments.");
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
        bugStatusField.setEditable(false);
        
        Label bugCommentLabel = new Label("Comments on bug:");
        TextArea bugCommentArea = new TextArea();
        bugCommentArea.setPrefHeight(100);
        bugCommentArea.setPrefWidth(200);
        for(int i = 9; i < bugInfo.length; i++)
        {         
            String[] tempArray = bugInfo[i].split(";");
            bugCommentArea.appendText(tempArray[0] +" says: " + tempArray[1] + "\n");
        }
        bugCommentArea.setEditable(false);
        
        Label bugNewCommentLabel = new Label("New Comment:");
        TextField bugNewCommentField = new TextField();
        bugNewCommentField.setText("Enter new comments here!");
        bugNewCommentField.setOnMouseClicked(event-> {bugNewCommentField.clear();});
        
        Button buttonBack = new Button("Back");  // "back" button to go back the Developer homepage
        buttonBack.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {stage.setScene(create(stage));}
        });
        
        Button buttonUpdate = new Button("Submit");  // "back" button to go back the Developer homepage
        buttonUpdate.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {
                // Validate if bugStatusField is NULL or empty
            	if(bugStatusField.getText() == null || bugStatusField.getText().trim().isEmpty())
            	{
                    Alert empty = new Alert(AlertType.ERROR);
                    empty.setHeaderText("Empty Field Detected.");
                    empty.setContentText("Empty comment cannot be submitted.");
                    empty.showAndWait();
                    bugStatusField.setText(bugInfo[3]);
            	}
            	else // If successful validation
            	{   
                    // Retrieve comment text
                    String comment = bugNewCommentField.getText();

                    // Calls Controller Class
                    BugCommentController controller = new BugCommentController();

                    // Calls the InsertComment method and send the comment data to the controller.
                    // Controller will forward data to Entity class (BugList) to insert new comment in database.
                    if(controller.InsertComment(bugInfo[4], bugInfo[5], comment) == true)
                    {
                        stage.setScene(create(stage));
                        alert.showAndWait();
                    }
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
        grid.add(bugCommentLabel, 0, 3);
        grid.add(bugCommentArea, 1, 3);
        grid.add(bugNewCommentLabel, 0, 4);
        grid.add(bugNewCommentField, 1, 4);
        grid.add(buttonBack, 0, 5);
        grid.add(hbox, 1, 5);
    	
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);    // Adding Background to Grid
        
        Scene scene2 = new Scene(grid, 550, 500); // Adding Grid to Scene and setting dimensions
        stage.setTitle("Bug Comment Page");
        stage.setScene(scene2);
        stage.show();
        return scene2;
    }
}