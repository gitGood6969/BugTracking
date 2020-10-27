// This is the Boundary Class
// Package
//package BugTracking;

// Import Libraries
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextField; 
import javafx.stage.Stage; 

public class BugReportPageUI
{ 
    public static Scene create (Stage stage) 
    { 
        //Label for bug name
        Text bugNameLabel = new Text("Bug Name: "); 
        bugNameLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 

        //Text field for bug name 
        TextField bugNameText = new TextField();
        bugNameText.setStyle("-fx-font: normal 15px 'serif' ");

        //Label for details of bug
        Text detailsBugLabel = new Text("Details of bug");
        detailsBugLabel.setStyle("-fx-font: normal bold 15px 'serif' ");

        //Text field details of bug
        TextArea detailsBugArea = new TextArea();
        detailsBugArea.setStyle("-fx-font: normal 15px 'serif' ");
        detailsBugArea.setWrapText(true);

        //Label for back 
        Button buttonBack = new Button(); 
        buttonBack.setText("Back");
        //buttonBack.setMouseTransparent(true);

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

        //Creating a Grid Pane 
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Arranging all the nodes in the grid 
        grid.add(bugNameLabel, 0, 0); 
        grid.add(bugNameText, 1, 0); 

        grid.add(detailsBugLabel, 0, 1);       
        grid.add(detailsBugArea, 1, 1); 

        grid.add(buttonBack, 0, 2);

        HBox submitBtn = new HBox(10);        
        submitBtn.setAlignment(Pos.BOTTOM_RIGHT);
        submitBtn.getChildren().add(buttonSubmit);
        grid.add(submitBtn, 1, 2);  // Add Submit Button

        //styling background of grid
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        //Creating a scene object 
        Scene scene = new Scene(grid); 

        //Setting title to the Stage 
        stage.setTitle("Bug Report"); 

        //Adding scene to the stage 
        stage.setScene(scene);  

        //Displaying the contents of the stage 
        stage.show();
        return scene;
    }
}