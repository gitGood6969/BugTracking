// Boundary Class
// [User Stories: #48]

// Package
//package BugTracking;

// Import Libraries
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField; 
import javafx.stage.Stage; 

public class BugReportPageUI
{ 
    public static Scene create (Stage stage) 
    { 
    	//date returns current date
        LocalDate today = LocalDate.now();
        int date = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();

        //label for bug reported date
        Text bugDateLabel = new Text ("Date: ");
        bugDateLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 

        //Label for bug reported date
        Text bugReportedDate = new Text();
        bugReportedDate.setText(date + "/" + month + "/" + year);
        bugReportedDate.setStyle("-fx-font: normal 15px 'serif' ");
        bugReportedDate.setTextAlignment(TextAlignment.CENTER);

        //Label for bug ID
        Text bugIDLabel = new Text("Bug ID: "); 
        bugIDLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 

        //Label for bug Status
        //Text bugStatusLabel = new Text("Bug Status: "); 
        //bugStatusLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 

        //ComboBox for bug Status (non-editable for reporters!)
        //ComboBox<String> cBoxBugStatus = new ComboBox<String>();
        //cBoxBugStatus.setValue("Unassigned");
        //cBoxBugStatus.setEditable(false);
        //cBoxBugStatus.setStyle("-fx-font: normal bold 15px 'serif' ");

        //Label and TextField for BugName
        Text bugNameLabel = new Text("Bug Name: "); 
        bugNameLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        TextField bugNameText = new TextField();

        //Label for details of bug
        Text descriptionBugLabel = new Text("Details of bug");
        descriptionBugLabel.setStyle("-fx-font: normal bold 15px 'serif' ");

        //Text field details of bug
        TextArea descriptionBugArea = new TextArea();
        descriptionBugArea.setStyle("-fx-font: normal 15px 'serif' ");

        //set height and width (to our preference - changeable)
        descriptionBugArea.setPrefHeight(200);  
        descriptionBugArea.setPrefWidth(250); 

        Alert alert = new Alert(AlertType.INFORMATION); // Invalid authentication warning box [Joseph]
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Successfully Reported!");
        alert.setContentText("You will be moved back to the previous page!");
        
        Alert alert2 = new Alert(AlertType.ERROR); // Invalid authentication warning box [Joseph]
        alert2.setTitle("Error Dialog");
        alert2.setHeaderText("Invalid Character Detected");
        alert2.setContentText("Please do not use ':' character!");

        //Label for back 
        Button buttonBack = new Button("Back"); 
        buttonBack.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {
                stage.setScene(UserLoginUI.ReporterPage(stage));
                System.out.println("Back to reporter's page!");
            }
        });


        //Label for submit 
        Button buttonSubmit = new Button("Submit"); 
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happened when the button is clicked.   
            @Override
            public void handle(ActionEvent event) 
            {	
                //Check if BugName is empty
                if (bugNameText.getText() == null || bugNameText.getText().trim().isEmpty())
                {
                    Alert fail= new Alert(AlertType.INFORMATION);
                    fail.setHeaderText("Fail to Submit");
                    fail.setContentText("You have not typed in anything for 'Bug Name'");
                    fail.showAndWait();
                }	
                //Check if Bug Details is empty
                else if (descriptionBugArea.getText() == null || descriptionBugArea.getText().trim().isEmpty()) 
                {
                    Alert fail= new Alert(AlertType.INFORMATION);
                    fail.setHeaderText("Fail to Submit");
                    fail.setContentText("You have not typed in anything in the 'Details of Bug'");
                    fail.showAndWait();
                }
                //Pass to BugReportPageController here (BugID, BugName, Details of bug)
                else 
                {
                    String name = bugNameText.getText();
                    String description = descriptionBugArea.getText();
                    String specialChar = ":";
                    boolean test1 = false;
                    boolean test2 = false;
					
                    for(int i = 0; i < name.length(); i++)
                    {
                        char ch = name.charAt(i);
                        if(specialChar.contains(Character.toString(ch)))
                        {
                            System.out.println("Invalid Character detected in Bug Description");
                            break;
                        }
                        else if (i == name.length()-1)
                        {test1 = true;}						
                    }
						
                    for(int j = 0; j < description.length(); j++)
                    {
                        char ch = description.charAt(j);
                        if(specialChar.contains(Character.toString(ch)))
                        {
                            System.out.println("Invalid Character detected in Bug Description");
                            break;
                        }
                        else if (j == description.length()-1)
                        {test2 = true;}	
                    }										
					
                    if(test1 == true && test2 == true)
                    {
                        BugReportPageController	controller = new BugReportPageController();
                        if(controller.CreateBugReport(bugReportedDate.getText(), bugNameText.getText(), descriptionBugArea.getText()) == true)
                        {
                            System.out.println("Successfully reported");
                            alert.showAndWait();
                            stage.setScene(UserLoginUI.ReporterPage(stage));					
                        }
                        else
                        {System.out.println("Failed to report.");}
                    }
                    else
                    {alert2.showAndWait();}				
                }
            }		
        });
		
        //Creating a Grid Pane 
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Arranging all the nodes in the grid 
        grid.add(bugDateLabel, 0, 0);			//Add bugDate label 
        grid.add(bugReportedDate, 1, 0);//Add bugDate label (sets to the current date ("DD/MM/YYYY"))
		
        grid.add(bugNameLabel, 0, 1);			// Add bugName label
        grid.add(bugNameText, 1, 1);			// Add bugName TextField

        HBox descriptionBugTextLbl = new HBox(10);        
        descriptionBugTextLbl.setAlignment(Pos.TOP_LEFT);
        descriptionBugTextLbl.getChildren().add(descriptionBugLabel);
        grid.add(descriptionBugTextLbl, 0, 2); 		// Add detailsOfBug Label

        grid.add(descriptionBugArea, 1, 2); 		// Add detailsOfBug TextArea

        grid.add(buttonBack, 0, 3);			// Add Back Button

        HBox submitBtn = new HBox(10);        
        submitBtn.setAlignment(Pos.BOTTOM_RIGHT);
        submitBtn.getChildren().add(buttonSubmit);
        grid.add(submitBtn, 1, 3);           	// Add Submit Button

        //styling background of grid
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        //Creating a scene object 
        Scene scene = new Scene(grid, 550, 500); 

        //Setting title to the Stage 
        stage.setTitle("Bug Report"); 

        //Adding scene to the stage 
        stage.setScene(scene);  

        //Displaying the contents of the stage 
        stage.show();
        return scene;
    }
}
	