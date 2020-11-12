// Boundary class
// [User Stories: #44]

// Package
//package BugTracking;

// import libraries
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserLogoutUI 
{
    public static Scene create(Stage stage)
    {
        Scene scene;

        stage.setTitle("Logout Page");
		
        GridPane grid = new GridPane(); 
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text logoutText = new Text("Are you sure you want to log out?");
        logoutText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        Alert alert1 = new Alert(AlertType.INFORMATION); 
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText("NO was selected.");
        alert1.setContentText("Returning to main page.");
        
        Alert alert2 = new Alert(AlertType.INFORMATION); 
        alert2.setTitle("Information Dialog");
        alert2.setHeaderText("YES was selected.");
        alert2.setContentText("Exiting program...");
       
        Button noButton = new Button(); 
        noButton.setText("NO");
        noButton.setOnAction(new EventHandler<ActionEvent>() 
        {  
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
        
        Button yesButton = new Button(); 
        yesButton.setText("YES");
        yesButton.setOnAction(new EventHandler<ActionEvent>() 
        {  
            @Override
            public void handle(ActionEvent event) 
            {
            	UserLogoutController ctrl = new UserLogoutController();
            	
            	
            	Alert alert2 = new Alert(AlertType.INFORMATION); 
                alert2.setTitle("Information Dialog");
                alert2.setHeaderText("YES was selected by " + ctrl.GetLogoutInfo(Integer.toString(UserLoginUI.userRole), Integer.toString(UserLoginUI.uID)) + ".");
                alert2.setContentText("Exiting program...");
                alert2.showAndWait();
                System.exit(0);
            }
        });
        
        
        HBox hbox = new HBox();
        hbox.getChildren().add(noButton);
        
        Pane filler = new Pane();
        hbox.getChildren().add(filler);
        HBox.setHgrow(filler, Priority.ALWAYS);
        
        hbox.getChildren().add(yesButton);
        
        grid.addRow(0, logoutText); 
        grid.addRow(1, hbox);
        
        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        scene= new Scene(grid, 550, 500);
        return scene;
	}
}
