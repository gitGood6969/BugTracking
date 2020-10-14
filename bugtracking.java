// Package
package BugTracking;
public class BugTracking extends UserLoginUI{
    public static void main(String[] args) {
        UserLoginController controlOne = new UserLoginController();
        controlOne.displayOne();
        UserInformation infoOne = new UserInformation();
        UserInformation infoTwo = new UserInformation("John", "Cron", true);
        UserInformation infoThree = new UserInformation (infoTwo);
        //infoTwo.setUserName("John");
        //infoTwo.setUserPassword("Cron");
        //infoTwo.setPasswordMatched(true);
        System.out.println(infoThree.getUserName());
        System.out.println(infoThree.getUserPassword());
        System.out.println(infoThree.getPasswordMatched());
        launch(args);
    }
}
