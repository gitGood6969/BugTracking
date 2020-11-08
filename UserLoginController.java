// Controller Class
// [User Stories: #40]
    
// Package
// package BugTracking;

public class UserLoginController 
{
    // Default Constructor
    public UserLoginController(){}
    
    // Auxiliary Methods
    public boolean ValidateUser(String userName, String userPassword) // Joseph
    {
        User tempUser = new User(); // Create Entity class User 
        return tempUser.LoginStatus(userName, userPassword);
    }
    
    public int[] getUser(String userName, String userPassword)   
    {
        User tempUser = new User(); // Create Entity class User 
        return tempUser.getID(userName, userPassword);
    } 
}