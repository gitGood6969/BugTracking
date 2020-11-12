// Controller class
// [User Stories: #44]

// Package
//package BugTracking;

public class UserLogoutController 
{
    public String GetLogoutInfo(String userRole, String userID)
    {
        User user = new User();
        return user.LogoutInfo(userRole, userID);
    }
}
