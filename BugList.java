// This is the Entity Class
// Package
//package BugTracking;

import java.io.BufferedWriter; // To use BufferedWriter
import java.io.File;           // To use File
import java.io.FileWriter;     // To use FileWriter
import java.io.IOException;    // To handle IOException
import java.io.PrintWriter;    // To use PrintWriter to write to files
import java.util.ArrayList;    // To use ArrayList Object type
import java.util.Scanner;      // To use Scanner class 
import java.util.Arrays;       // To use Arrays 

public class BugList 
{
    String bugFile = "BugList.txt";

    public boolean InsertBugReport(String bugName, String bugDescription)
    {
        File file = new File(bugFile);
        System.out.println("From BugList, BugName: " + bugName );
        System.out.println("From BugList, BugName: " + bugDescription );

        try
        {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter (file, true)));
            writer.println(UserLoginUI.userRole + ":" + UserLoginUI.uID + ":" + bugName + ":" + bugDescription);         
            writer.close();
        }
        catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}
        return true;
    }

    public String[] getListOfBugs(String role, String uID)
    {
        ArrayList<String> tempArrayList = new ArrayList<String>();
        File file = new File(bugFile);

        try
        {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine())
            {		
                String fileText = readFile.nextLine();
                String[] tempArray = fileText.split(":");
                if(tempArray[0].equals(role) && tempArray[1].equals(uID))
                {
                    String bug = "BugName: " + tempArray[3] + "\nBug Description: " + tempArray[4] + "\n"; 
                    tempArrayList.add(bug);
                }
            }
            readFile.close();
        }
        catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}

        String[] strArray = new String[tempArrayList.size()];
        for(int i = 0; i < tempArrayList.size(); i++)
        {// convert it back to a string Array 
            strArray[i] = tempArrayList.get(i);
        }
        return strArray;
    }
    
    public ArrayList<ArrayList<String>> getRawData()
    {// Returns a 2D arrayList of ArrayList containing all the bug Reports
        ArrayList<ArrayList<String>> raw = new ArrayList<ArrayList<String>>();
        File file = new File(bugFile);
        
        try
        {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine())
            {		
                String fileText = readFile.nextLine();
                String[] tempArray = fileText.split(":");
                raw.add(new ArrayList<String>(Arrays.asList(tempArray[0], tempArray[1],
                tempArray[2], tempArray[3], tempArray[4]))); 
            }
            readFile.close();
        }
        catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}
        return raw;
    }
}
//https://www.geeksforgeeks.org/multidimensional-collections-in-java/
//https://www.geeksforgeeks.org/array-class-in-java/
//https://beginnersbook.com/2013/12/how-to-loop-arraylist-in-java/