// Entity Class
// [User Stories: A lot]

// Package
//package BugTracking;

// Import Libraries
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BugList 
{
    // Default Constructor
    public BugList(){}

    // Used to insert a new bug report into the list of bugs (BugList.txt) 
    // [User Stories: #48]
    public boolean InsertBugReport(String bugReportedDate, String bugName, String bugDescription)
    {
        File file = new File("BugList.txt");
        System.out.println("From BugList, Bug Reported Date: " + bugReportedDate);
        System.out.println("From BugList, Bug Name: " + bugName );
        System.out.println("From BugList, Bug Description: " + bugDescription );

        try
        {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter (file, true)));
            writer.println(UserLoginUI.userRole + ":" + UserLoginUI.uID + ":" + "0:" + "NotFixed:" + bugName + ":" + bugDescription + ":" + bugReportedDate + ":0");                                                              
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
        File file2 = new File("userlist.txt");

        try
        {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine())
            {		
                String fileText = readFile.nextLine();
                String[] tempArray = fileText.split(":");
                //Display all reported bug by Individual Reporter
                if(Integer.parseInt(role) == 1 && tempArray[0].equals(role) && tempArray[1].equals(num))
                {
                    String bug = "Bug Reported Date: " + tempArray[6] + "\nBug Name: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n";
                    if(tempArray[7].equals("0"))
                    {
                        bug = bug + "Bug have not been fixed.\n";
                        tempArrayList.add(bug);
                    }
                    else
                    {
                        bug = bug + "Bug was fixed on: " + tempArray[7] + "\n";
                        tempArrayList.add(bug);                
                    }
                }
                //Display all bug assigned to Individual Developer
                else if(Integer.parseInt(role) == 2 && tempArray[2].equals(num))
                {
                    String bug = "Bug Reported Date: " + tempArray[6] + "\nBug Name: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\nBug Status: " + tempArray[3] + "\n"; 
                    if(tempArray[7].equals("0"))
                    {
                        bug = bug + "Bug have not been fixed.\n";
                        tempArrayList.add(bug);
                    }
                    else
                    {
                        bug = bug + "Bug was fixed on: " + tempArray[7] + "\n";
                        tempArrayList.add(bug);                
                    }
                }
                else if(Integer.parseInt(role) == 3 && Integer.parseInt(num) == 1 && tempArray[3].equals("fixed"))
                { 
                    String bug = "Bug Reported Date: " + tempArray[6] + "\nBug Name: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\nBug Status: " + tempArray[3] + "\n";   	
                    bug = bug + "Bug was fixed on: " + tempArray[7] + "\n";
                    tempArrayList.add(bug);                
                	
                }
                else if(Integer.parseInt(role) == 3 && Integer.parseInt(num) == 0 && !tempArray[3].equals("fixed"))
                {
                    String bug = "Bug Reported Date: " + tempArray[6] + "\nBug Name: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\nBug Status: " + tempArray[3] + "\n";
                    bug = bug + "Bug have not been fixed.\n";
                    tempArrayList.add(bug);
                }
                else if(Integer.parseInt(role) == 4 && Integer.parseInt(num) == 0 && Integer.parseInt(tempArray[2]) == 0)
                {
                    String bug = "Bug Reported Date: " + tempArray[6] + "\nBug Name: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\nAssigned to: Not assigned\n"; 
                    tempArrayList.add(bug);
                }
                else if(Integer.parseInt(role) == 4 && Integer.parseInt(num) == 1 && Integer.parseInt(tempArray[2]) != 0)
                {
                    Scanner readFile2 = new Scanner(file2);
                    String developerID = tempArray[2];
                    String name = "";
                    while(readFile2.hasNextLine())
                    {
                        String fileText2 = readFile2.nextLine();
                        String[] tempArray2 = fileText2.split(":");
                        if(developerID.equals(tempArray2[3]))
                        {
                            name = tempArray2[0];
                            String bug = "Assigned to Developer: " + name + "\nBug Reported Date: "+ tempArray[6] + "\nBugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n"; 
                            if(tempArray[7].equals("0"))
                            {
                                bug = bug + "Bug have not been fixed.\n";
                                tempArrayList.add(bug);
                            }
                            else
                            {
                                bug = bug + "Bug was fixed on: " + tempArray[7] + "\n";
                                tempArrayList.add(bug);                
                            }
                            break;
                        }
                    }
                    readFile2.close();             
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
                    if(tempArray2[i].equalsIgnoreCase(keyword))
                    {
                    	String bug = "Bug Reported Date: " + tempArray[6] + "\nBugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n";
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
                    if(tempArray2[i].equalsIgnoreCase(title))
                    {
                    	String bug = "Bug Reported Date: " + tempArray[6] + "\nBugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n";
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
                if(tempArray[0].equalsIgnoreCase(assignee) && Integer.parseInt(tempArray[2])== 2)
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
                    String bug = "Bug Reported Date: " + tempArray[6] + "\nBugName: " + tempArray[4] + "\nBug Description: " + tempArray[5] + "\n";
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
    
    public String[] GetBug(String input, int num)
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
                String[] tempArray2 = input.split("\\r?\\n");
                String bugName = tempArray2[1].substring(10);
                String bugDesc = tempArray2[2].substring(17);
                if(tempArray[4].equals(bugName) && tempArray[5].equals(bugDesc) && num == 0)
                {
                    for(int i = 0; i<tempArray.length;i++)
                    {
                    	tempArrayList.add(tempArray[i]);
                    }
                }
                else if (tempArray[4].equals(bugName) && tempArray[5].equals(bugDesc) && num == 1)
                {
                    for(int i = 0; i<tempArray.length;i++)
                    {
                    	tempArrayList.add(tempArray[i]);
                    }
                }
                else if (tempArray[4].equals(bugName) && tempArray[5].equals(bugDesc) && num == 2)
                {
                    for(int i = 0; i<tempArray.length;i++)
                    {
                    	tempArrayList.add(tempArray[i]);
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
    
    public boolean UpdateBugStatus(String bugName, String bugDesc, String updatedStatus, int num)
    {
    	ArrayList<String> tempArrayList = new ArrayList<String>();
    	File file = new File("BugList.txt");
    	LocalDate today = LocalDate.now();
        int date = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
    	String todayDate = date + "/" + month + "/" + year;
    	try
        {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine())
            {
            	String fileText = readFile.nextLine();
                String[] tempArray = fileText.split(":");
                if(tempArray[4].equals(bugName) && tempArray[5].equals(bugDesc))
                {
                    tempArray[3] = updatedStatus;
                    if(updatedStatus.equals("fixed"))
                    {
                        tempArray[7] = todayDate;
                        String tempStr = tempArray[0]+ ":" + tempArray[1] + ":" + tempArray[2] + ":" + tempArray[3]
                                        + ":" + tempArray[4] + ":" + tempArray[5] + ":" + tempArray[6] + ":" + tempArray[7]; 
                        tempArrayList.add(tempStr);
                    }
                    else
                    {
                        String tempStr = tempArray[0]+ ":" + tempArray[1] + ":" + tempArray[2] + ":" + tempArray[3]
                                        + ":" + tempArray[4] + ":" + tempArray[5] + ":" + tempArray[6] + ":" + tempArray[7]; 
                        tempArrayList.add(tempStr);
                    }              		
                }
                else
                {
                    tempArrayList.add(fileText);
                }
            }
            readFile.close();
            
            String[] strArray = new String[tempArrayList.size()];
            for(int i = 0; i < tempArrayList.size(); i++)
            {
                strArray[i] = tempArrayList.get(i);
            }
                      
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter (file)));
            for(int j=0; j<tempArrayList.size(); j++)
            {
                writer.println(strArray[j]);
            }                                                             
            writer.close();
        } catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}
    	return true;
    }
    
    public boolean RecordAssignment(String bugName, String bugDesc, String assignment)
    {
    	ArrayList<String> tempArrayList = new ArrayList<String>();
    	File file1 = new File("userlist.txt");
    	File file2 = new File("BugList.txt");
    	String id = "";
    	boolean exist = false;
    	try
        {
            Scanner readFile1 = new Scanner(file1);
            while (readFile1.hasNextLine())
            {
            	String fileText = readFile1.nextLine();
                String[] tempArray = fileText.split(":");
                if(tempArray[0].equalsIgnoreCase(assignment) && tempArray[2].equals("2"))
                {
                    id = tempArray[3];
                    exist = true;
                    Scanner readFile2 = new Scanner(file2);
                    while (readFile2.hasNextLine() && exist == true)
                    {
                    	String text = readFile2.nextLine();
                        String[] tempArray2 = text.split(":");
                        if(tempArray2[4].equals(bugName) && tempArray2[5].equals(bugDesc))
                        {
                            tempArray2[2] = id;
                            String tempStr = tempArray2[0]+ ":" + tempArray2[1] + ":" + tempArray2[2] + ":" + tempArray2[3]
                                            + ":" + tempArray2[4] + ":" + tempArray2[5] + ":" + tempArray2[6] + ":" + tempArray2[7]; 
                            tempArrayList.add(tempStr);
                        }
                        else
                        {
                            tempArrayList.add(text);
                        }
                    }
                    readFile2.close();
                }   
            }
            readFile1.close();
            
            String[] strArray = new String[tempArrayList.size()];
            for(int i = 0; i < tempArrayList.size(); i++)
            {
                strArray[i] = tempArrayList.get(i);
            }
                      
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter (file2)));
            for(int j=0; j<tempArrayList.size(); j++)
            {
                writer.println(strArray[j]);
            }                                                             
            writer.close();
      
        } catch (IOException e) {System.out.println("An error occurred."); e.printStackTrace();}
    	return exist;
    }
}