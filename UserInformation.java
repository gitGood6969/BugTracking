// This is the Entity Class
// Package
package BugTracking;

// Import Libraries
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;

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
    /*
    public void fileIO() // WIP
    {
        try 
        {
            String fileName = "Sample.txt"; // Specify File Name
            File myFile = new File(fileName); //pass the filename or directory name to File object 

            if (myFile.createNewFile()) // Create the File if it doesn't exist
            {
                System.out.println("File created: " + myFile.getName());
                FileWriter myWriter = new FileWriter(fileName, true);
                //BufferedWriter myWriter = new BufferedWriter(fileName);
                myWriter.write("Hello ALICE!\n");
                myWriter.close();   
            }
            else // If the File if it already exists
            {
                System.out.println("File already exists.");
                FileWriter myWriter = new FileWriter(fileName, true);
                //BufferedWriter myWriter = new BufferedWriter(fileName);
                myWriter.write("Hello BOB!\n");
                myWriter.close();    
            }
        } 
        catch(IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    */
    public boolean LoginStatus(String userName, String userPassword) // Joseph
    {
       System.out.println("Username is " + userName + " Password is " + userPassword);
       if (userName.equals("Tom") && userPassword.equals("123"))
       {
           setPasswordMatched(true);
       }
          else
          {
              setPasswordMatched(false);
          }

       return getPasswordMatched();
    }

    /*
    public boolean ValidateUser(boolean passwordMatched)
    {
        // WIP
    }
    */
}

/*
Websites used:
https://docs.oracle.com/javase/tutorial/java/javaOO/variables.html
https://www.baeldung.com/java-deep-copy
https://www.geeksforgeeks.org/file-class-in-java/
https://www.w3schools.com/java/java_files_create.asp
https://stackoverflow.com/questions/46683251/how-do-i-write-to-a-new-line-using-filewriter-in-java/46683451
*/

