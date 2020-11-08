// Entity Class
// [User Stories: #40]

// Package
// package BugTracking;

// Import Libraries
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class User 
{
    // Default Constructor
    public User(){}
    
    // Auxiliary Methods
    public boolean LoginStatus(String userName, String userPassword)
    { // Read and Validate user login details. 
        boolean match = false;
        try 
        { // Read from database.txt file containing login credentials
            String fileName = "userlist.txt"; // File with login credentials
            File myFile = new File(fileName); 

            if (myFile.exists() && !myFile.isDirectory()) // Check if File exists 
            {// If so, then we will read it
                Scanner fileReader = new Scanner(myFile);
                while (fileReader.hasNextLine())
                {
                    String data = fileReader.nextLine();
                    String[] tempArray = data.split(":"); // To Delimit the Username and Password
                    if (tempArray[0].toLowerCase().equals(userName.toLowerCase()) && tempArray[1].equals(userPassword))
                    {match = true;} // Return true if username and password matches
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
        return match;
    }
    public int[] getID(String userName, String userPassword)
    {// Read and Validate user login details after that, return the user's clearance level. 
        int[] userID = new int[2];
        try 
        { // Read from database.txt file containing login credentials
            String fileName = "userlist.txt"; // File with login credentials
            File myFile = new File(fileName); 

            if (myFile.exists() && !myFile.isDirectory()) // Check if File exists 
            {// If so, then we will read it
                Scanner fileReader = new Scanner(myFile);
                while (fileReader.hasNextLine())
                {
                    String data = fileReader.nextLine();
                    String[] tempArray = data.split(":"); // To Delimit the Username and Password
                    if (tempArray[0].toLowerCase().equals(userName.toLowerCase()) && tempArray[1].equals(userPassword))
                    {
                    	userID[0] = Integer.parseInt(tempArray[2]);
                    	userID[1] = Integer.parseInt(tempArray[3]);
                    } // Return clearance level 
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
        return userID;
    }
}