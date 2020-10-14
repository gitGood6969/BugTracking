// This is the Controller Class
// Package
package BugTracking;

public class UserLoginController {
    
    // Private Variables
    private String userName;
    private String userPassword;

    // Default Constructor
    public UserLoginController()
    {// Call Mutators
        setUserName("Purple");
        setUserPassword("Fanta");
    }

    // Main Constructor
    public UserLoginController(String userName, String userPassword)
    {// Calls Mutators
        setUserName(userName);
        setUserPassword(userPassword);
    }

    // Copy Constructor (Deep Copy)
    public UserLoginController(UserLoginController temp)
    {// Calls Default Constructor
        this(temp.getUserName(), temp.getUserPassword());
    }

    // Accessor Methods
    public String getUserName()     {return userName;}
    public String getUserPassword() {return userPassword;}

    // Mutator Methods
    public void setUserName(String userName)         {this.userName = userName;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}

    // Auxiliary Methods
    public boolean ValidateUser(String userName, String userPassword) // Joseph
    {
        UserInformation userInformation = new UserInformation();
        return userInformation.LoginStatus(userName, userPassword);
    }

    /*
    public boolean DisplaySuccess()
    {
        WIP
    }
    
    public boolean DisplayError()
    {
        WIP
    }

    public boolean StartSession()
    {
        WIP
    }

    */
}