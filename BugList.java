// Entity class
// Package
// package BugTracking;

// Import Libraries
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BugList 
{
    // Default Constructor
    public BugList(){}

    // Used to insert a new bug report into the list of bugs (BugList.txt) 
    // [User Stories: #48]
    public boolean InsertBugReport(String bugName, String bugDescription)
    {
        File file = new File("BugList.txt");
        System.out.println("From BugList, BugName: " + bugName );
        System.out.println("From BugList, BugName: " + bugDescription );

        try
        {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter (file, true)));
            writer.println(UserLoginUI.userRole + ":" + UserLoginUI.uID + ":" + "0:" + "NotFixed:" + bugName + ":" + bugDescription);                                                              
            writer.close();
        }
        catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}
        return true;
    }

    // Retrieve list of all the bugs depending on varying requirements
    // [User Stories: #52, #53, #54, #55]
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
                else if(Integer.parseInt(role) == 3 && Integer.parseInt(num) == 1 && tempArray[3].equals("Fixed"))
                { 
                    String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n"; 
                    tempArrayList.add(bug);
                }
                else if(Integer.parseInt(role) == 3 && Integer.parseInt(num) == 0 && !tempArray[3].equals("Fixed"))
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
        {strArray[i] = tempArrayList.get(i);}
        return strArray;
    }
    
    // Used to search list of bugs by Keyword 
    // [User Stories: #79]
    public String[] searchByKeywordResult(String keyword)
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
                String[] tempArray2 = tempArray[5].split(" ");
                for(int i = 0; i<tempArray2.length; i++)
                {
                    if(tempArray2[i].equals(keyword))
                    {
                        String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n";
                        tempArrayList.add(bug);
                    }
                }
            }
            readFile.close();
        } catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}

        String[] strArray = new String[tempArrayList.size()];
        for(int i = 0; i < tempArrayList.size(); i++)
        {strArray[i] = tempArrayList.get(i);}
        return strArray;
    }

    // Used to search list of bugs by Title 
    // [User Stories: #80]
    public String[] searchByTitleResult(String title)
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
                String[] tempArray2 = tempArray[4].split(" ");
                for(int i = 0; i<tempArray2.length; i++)
                {
                    if(tempArray2[i].equals(title))
                    {
                        String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n";
                        tempArrayList.add(bug);
                    }
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
    
    // Used to search list of bugs by Assignee 
    // [User Stories: #81]
    public String[] searchByAssigneeResult(String assignee)
    {
    	ArrayList<String> tempArrayList = new ArrayList<String>();
        File file = new File("userlist.txt");
        File file2 = new File("BugList.txt");
        int developerID = 0;
        
        try
        {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine())
            {		
            	String fileText = readFile.nextLine();
                String[] tempArray = fileText.split(":");
                if(tempArray[0].equals(assignee) && Integer.parseInt(tempArray[2])== 2)
                {
                    developerID = Integer.parseInt(tempArray[3]);
                    break;
                }
                else
                {
                    developerID = 0;
                }
            }
            readFile.close();
            
            Scanner readFile2 = new Scanner(file2);
            while (readFile2.hasNextLine())
            {		
            	String fileText = readFile2.nextLine();
                String[] tempArray = fileText.split(":");
                if(Integer.parseInt(tempArray[2]) == developerID && Integer.parseInt(tempArray[2]) != 0)
                {
                    String bug = "BugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n";
                    tempArrayList.add(bug);
                }
            }
            readFile2.close();           
        } catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}

        String[] strArray = new String[tempArrayList.size()];
        for(int i = 0; i < tempArrayList.size(); i++)
        {
            strArray[i] = tempArrayList.get(i);
        }
        return strArray;
    }
}