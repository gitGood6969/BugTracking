// Entity Class
// [User Stories: #75]

// Package
//package BugTracking;

// Import Libraries
import java.io.File;        // To use File class
import java.io.IOException; // To use handle IOException when Reading files in case it fails
import java.util.Scanner;   // To use Scanner class to read in data from files
import java.util.ArrayList; // To use the ArrayList variable container
import java.util.Arrays;    // To use Array containers

public class GenerateReport 
{
    private String nameOfBugDatabase = ("BugList.txt");

    // Default Constructor
    public GenerateReport(){}

    // Method reads the raw data in and delimits it to a string array
    // then adds it to an arraylist and returns the whole list.
    public ArrayList<String []> getRawData()
    {
        ArrayList<String []> raw = new ArrayList<String []>();
        File fileObj = new File(nameOfBugDatabase);
        try
        {
            Scanner scannerObj = new Scanner(fileObj);  
            while (scannerObj.hasNextLine())
            {
                String fileText = scannerObj.nextLine();
                String[] tempArray = fileText.split(":");
                raw.add(tempArray);
            }
            scannerObj.close();
        }
        catch (IOException e) 
        {
            System.out.println("Whoops! An error has occurred! :=(");
            System.out.println("Ensure " + nameOfBugDatabase + " exists and is properly formatted!\n"); 
            e.printStackTrace();
        }
        return raw;
    }
}
