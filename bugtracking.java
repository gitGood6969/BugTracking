// This is the Driver Class
// Package
package BugTracking;
public class BugTracking extends UserLoginUI{
    public static void main(String[] args) {
        //UserLoginController controlOne = new UserLoginController();
        //UserInformation infoOne = new UserInformation();
        //infoOne.displayInner();
        //infoOne.fileIO();
        UserLoginUI ui = new UserLoginUI();
        ui.launch(args); // Starts UserLoginUI
        //System.out.println("Hello World");
    }
}
