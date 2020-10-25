// This is the Controller Class
// Package
package BugTracking;

public class UserLoginController {

    // Default Constructor
    public UserLoginController(){}
    
    // Auxiliary Methods
    public boolean ValidateUser(String userName, String userPassword) // Joseph
    {
        User tempUser = new User(); // Create Entity class User 
        return tempUser.LoginStatus(userName, userPassword);
    }
}