// Entity Class
// Package
//package BugTracking;

// import libraries
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
            writer.println(UserLoginUI.userRole + ":" + UserLoginUI.uID + ":" + "0:" + "0:" + bugName + ":" + bugDescription);                                                              
            writer.close();
        }
        catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}
        return true;
    }
	
    public String[] getListOfBugs(String role, String num)
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
                if(Integer.parseInt(role) == 1 && tempArray[0].equals(role) && tempArray[1].equals(num))
                {
                    String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n"; 
                    tempArrayList.add(bug);
                }
                else if(Integer.parseInt(role) == 2 && tempArray[2].equals(num))
                {
                    String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n"; 
                    tempArrayList.add(bug);
                }
                else if(Integer.parseInt(role) == 3 && Integer.parseInt(num) == 1 && Integer.parseInt(tempArray[3]) == 1)
                {
                    String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n"; 
                    tempArrayList.add(bug);
                }
                else if(Integer.parseInt(role) == 3 && Integer.parseInt(num) == 0 && Integer.parseInt(tempArray[3]) == 0)
                {
                    String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n"; 
                    tempArrayList.add(bug);
                }
                else if(Integer.parseInt(role) == 4 && Integer.parseInt(num) == 0 && Integer.parseInt(tempArray[2]) == 0)
                {
                    String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n"; 
                    tempArrayList.add(bug);
                }
                else if(Integer.parseInt(role) == 4 && Integer.parseInt(num) == 1 && Integer.parseInt(tempArray[2]) != 0)
                {
                    String bug = "Assigned to Developer: " + tempArray[2] + "\nBugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n"; 
                    tempArrayList.add(bug);
                }
            }
            readFile.close();
        } catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}

        String[] strArray = new String[tempArrayList.size()];
        for(int i = 0; i < tempArrayList.size(); i++)
        {
            strArray[i] = tempArrayList.get(i);
        }
        return strArray;
    }
}
