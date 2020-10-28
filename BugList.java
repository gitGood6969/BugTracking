// This is the Entity Class
// Package
//package BugTracking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BugList 
{
    public boolean InsertBugReport(String bugName, String bugDescription)
    {
        File file = new File("BugList.txt");
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
        File file = new File("BugList.txt");

        try
        {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine())
            {		
                String fileText = readFile.nextLine();
                String[] tempArray = fileText.split(":");
                if(tempArray[0].equals(role) && tempArray[1].equals(uID))
                {
                    String bug = "BugName: " + tempArray[2] + "\nBug Description: " + tempArray[3] + "\n"; 
                    tempArrayList.add(bug);
                }
            }
            readFile.close();
        }
        catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}

        String[] strArray = new String[tempArrayList.size()];
        for(int i = 0; i < tempArrayList.size(); i++)
        {
            strArray[i] = tempArrayList.get(i);
        }
        return strArray;
    }
}
