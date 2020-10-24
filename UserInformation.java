// This is the Entity Class
// Package
package BugTracking;

// Import Libraries
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.Arrays;

public class UserInformation {

    // Private Variables
    private String userName; 
    private String userPassword;
    private boolean passwordMatched; 
    private ArrayList<User> UserList = new ArrayList<User>();
    
    // Default Constructor
    public UserInformation() 
    {// Calls Mutators
        setUserName("");
        setUserPassword("");
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
    public void fileIO() 
    {
        try 
        { // Read from database.txt file containing login credentials
            String fileName = "database.txt"; // File with login credentials
            File myFile = new File(fileName); 

            if (myFile.exists() && !myFile.isDirectory()) // Check if File exists 
            {// If so, then we will read it
                Scanner fileReader = new Scanner(myFile);
                while (fileReader.hasNextLine())
                {
                    String data = fileReader.nextLine();
                    String[] tempArray = data.split(":"); // To Delimit the Username and Password
                    UserList.add(new User(tempArray[0],tempArray[1]));
                }
                fileReader.close();
            }
            else 
            {// Prompt error if "database" File doesn't exist
                System.out.println("File: " + myFile.getName() + " is missing! Unable to Authenticate any Users!");
                System.out.println("Please ensure " + myFile.getName() + " exists before running the program!");
                System.exit(0); // Terminates program if file is missing. 
            }
        } 
        catch (FileNotFoundException e) {System.out.println("An error occurred."); e.printStackTrace();}
    }
     
    public boolean LoginStatus(String userName, String userPassword) // Joseph
    {// This method does the Authentication. // May want to move this to Controller class
        fileIO(); // Calls fileIO method to setup the arraylist of login credentials
        for(User u: UserList)
        {
            if (u.getUserName().toLowerCase().equals(userName.toLowerCase()) && u.getUserPassword().equals(userPassword))
            {setPasswordMatched(true);} // Return true if username and password matches
        }
        return getPasswordMatched();
    }
}

// This class below is used when generating an ArrayList of "Users" and populate
// each "user" object with the read from file "Username" and "Password"
class User {
    // private variables used to hold each user's login details read from database.txt
    private String userName;
    private String userPassword;
    
    // Default Constructor
    User() 
    {
        setUserName("");
        setUserPassword("");
    } 
        
    // Main Constructor 
    User(String userName, String userPassword) 
    {
        setUserName(userName); 
        setUserPassword(userPassword);
    }
    
    // Copy Constructor (Deep Copy) 
    User(User temp)
    {
        setUserName(temp.getUserName()); 
        setUserPassword(temp.getUserPassword());
    }

    // Accessor Methods
    String getUserName()     {return userName;}
    String getUserPassword() {return userPassword;}

    // Mutator Methods
    void setUserName(String userName)         {this.userName = userName;}
    void setUserPassword(String userPassword) {this.userPassword = userPassword;} 
}


/*
Websites used:
https://docs.oracle.com/javase/tutorial/java/javaOO/variables.html
https://www.baeldung.com/java-deep-copy
https://www.geeksforgeeks.org/file-class-in-java/
https://www.w3schools.com/java/java_files_create.asp
https://stackoverflow.com/questions/46683251/how-do-i-write-to-a-new-line-using-filewriter-in-java/46683451
https://www.tutorialspoint.com/java/java_innerclasses.htm#:~:text=In%20Java%2C%20just%20like%20methods,is%20called%20the%20outer%20class.&text=Following%20is%20the%20syntax%20to%20write%20a%20nested%20class.
https://www.w3schools.com/java/java_files_read.asp
https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
https://javarevisited.blogspot.com/2017/01/how-to-split-string-based-on-delimiter-in-java.html
https://www.w3schools.com/java/java_arraylist.asp
*/