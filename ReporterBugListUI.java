// Boundary Class
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReporterBugListUI 
{
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

        //Code to get data from controller here
        ReporterBugListController controller = new ReporterBugListController();
        String[] strArray = getListOfBugs(controller);
        for(int i = 0; i < strArray.length; i++)
        {
            if(i==0)
            {list.appendText(strArray[i]);}
            else
            {list.appendText("\n" + strArray[i]);}
        }
        list.setEditable(false);


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

        grid.add(bugListLabel, 0, 0);
        grid.add(list, 0, 1);
        grid.add(buttonBack, 0, 3);

        BackgroundFill background_fill = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        scene = new Scene(grid, 450, 400);
        stage.setTitle("Reporter Bug List");
        stage.setScene(scene);
        stage.show();
        return scene;
    }    
    
    public static String[] getListOfBugs(ReporterBugListController controller)
    {
        String role = Integer.toString(UserLoginUI.userRole);
        String id = Integer.toString(UserLoginUI.uID);
        String[] strArray = controller.ViewListOfBugs(role, id);
        return strArray;
    }
}
