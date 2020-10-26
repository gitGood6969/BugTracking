// This is the Driver Class
// Package
package BugTracking;
public class BugTracking extends UserLoginUI{
    public static void main(String[] args) {
        
        UserLoginUI ui = new UserLoginUI();
        ui.launch(args); // Starts UserLoginUI

        //========================================================================
        // Debug Code
        //========================================================================
        //UserLoginController controlOne = new UserLoginController();
        //UserInformation infoOne = new UserInformation();
        //infoOne.displayInner();
        //infoOne.fileIO();
        //System.out.println("Hello World");
    }
}
