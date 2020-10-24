// This is the Controller Class
// Package
package BugTracking;

public class UserLoginController {

    // Default Constructor
    public UserLoginController(){}
    
    // Auxiliary Methods
    public boolean ValidateUser(String userName, String userPassword) // Joseph
    {
        UserInformation userInformation = new UserInformation(); // Create Entity class UserInformation 
        return userInformation.LoginStatus(userName, userPassword);
    }
}