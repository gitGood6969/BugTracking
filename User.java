//Package
package BugTracking;

// This class below is used when generating an ArrayList of "Users" and populate
// each "user" object with the read from file "Username" and "Password"

public class User {
    // private variables used to hold each user's login details read from database.txt
    private String userName;
    private String userPassword;
    
    // Default Constructor
    public User() 
    {
        setUserName("Ball");
        setUserPassword("Lily");
    } 
        
    // Main Constructor 
    public User(String userName, String userPassword) 
    {
        setUserName(userName); 
        setUserPassword(userPassword);
    }
    
    // Copy Constructor (Deep Copy) 
    public User(User temp)
    {
        setUserName(temp.getUserName()); 
        setUserPassword(temp.getUserPassword());
    }

    // Accessor Methods
    public String getUserName()     {return userName;}
    public String getUserPassword() {return userPassword;}

    // Mutator Methods
    public void setUserName(String userName)         {this.userName = userName;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;} 
}
