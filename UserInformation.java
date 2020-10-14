// Package
package BugTracking;
public class UserInformation {

    // Private Variables
    private String userName;
    private String userPassword;
    private boolean passwordMatched;

    // Default Constructor
    public UserInformation() 
    {// Calls Mutators
        setUserName("apple");
        setUserPassword("pear");
        setPasswordMatched(false);
    }

    // Main Constructor
    public UserInformation(String userName, String userPassword, boolean passwordMatched) 
    {// Calls Mutators
        setUserName(userName);
        setUserPassword(userPassword);
        setPasswordMatched(passwordMatched);
    }

    // Copy Constructor (Deep Copy)
    public UserInformation (UserInformation temp)
    {// Calls Default Constructor
        this(temp.getUserName(),temp.getUserPassword(), temp.getPasswordMatched());
    }
    
    // Accessor Methods
    public String getUserName()         {return userName;}
    public String getUserPassword()     {return userPassword;}
    public boolean getPasswordMatched() {return passwordMatched;}

    // Mutator Methods
    public void setUserName(String userName)                {this.userName = userName;} 
    public void setUserPassword(String userPassword)        {this.userPassword = userPassword;}
    public void setPasswordMatched(boolean passwordMatched) {this.passwordMatched = passwordMatched;}

    // Auxiliary Methods
    public bool LoginStatus()
    {
        // WIP
    }
    public bool ValidateUser(passwordMatched)
    {
        // WIP
    }
}

/*
Websites used:
https://docs.oracle.com/javase/tutorial/java/javaOO/variables.html
https://www.baeldung.com/java-deep-copy
*/

