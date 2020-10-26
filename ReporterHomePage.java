//package
package BugTracking;

// Import libraries
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.stage.Stage; 
import javafx.scene.layout.VBox;

public class ReporterHomePage 
{
    public static Scene create(Stage stage)
    {
        Label label2 = new Label("This is the second scene"); // On-Screen text

        Button btn2 = new Button(); // Logout Button
        btn2.setText("Logout");
        btn2.setOnAction(new EventHandler<ActionEvent>() 
        {// Handles what actions happend when the button is clicked.  
            @Override
            public void handle(ActionEvent event) 
            {
                stage.setScene(UserLoginUI.create(stage));
                System.out.println("User has logged out!");
            }
        });
        VBox layout2 = new VBox(20);  // Combining all Elements to a single screen    
        layout2.getChildren().addAll(label2, btn2);
        Scene scene2= new Scene(layout2, 300, 250);
        return scene2;
    }
}
