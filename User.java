// This is the Entity Class
// Package
//package BugTracking;

// Import Libraries
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.Arrays;

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
            String fileName = "database.txt"; // File with login credentials
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
    public int getClearanceLevel(String userName, String userPassword)
    {// Read and Validate user login details after that, return the user's clearance level. 
        int clearanceLevel = 0;
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
                    if (tempArray[0].toLowerCase().equals(userName.toLowerCase()) && tempArray[1].equals(userPassword))
                    {clearanceLevel = Integer.parseInt(tempArray[2]);} // Return clearance level 
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
        return clearanceLevel;
    }
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