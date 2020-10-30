// This is the Controller Class
// Package
//package BugTracking;

// Import Libraries
import java.util.ArrayList;

// Need to Filter by Developer name
public class DeveloperBugListController 
{
    public DeveloperBugListController() {} // Default constructor

    public String[] getAssignedBugs(int uID)
    {
        BugList bugList = new BugList(); //Create Entity class object
        ArrayList<ArrayList<String>> raw = bugList.getRawData(); // retrieve the 2D Array
        ArrayList<String> tempArrayList = new ArrayList<String>(); // Recycle from bugList.getListOfBugs
  
        for (int c1 = 0; c1 < raw.size(); c1++) // Loop through X-Axis of 2D ArrayList
        { // Recycle from bugList.getListOfBugs		      
            if (Integer.parseInt(raw.get(c1).get(2)) == uID)
            {
                String bug = "BugName: " + raw.get(c1).get(3) + "\nBug Description: " + raw.get(c1).get(4) + "\n"; 
                tempArrayList.add(bug);
            }    		         		     
        }
        String[] strArray = new String[tempArrayList.size()];
        for(int i = 0; i < tempArrayList.size(); i++)
        {// convert it back to a string Array 
            strArray[i] = tempArrayList.get(i);
        }
        return strArray;
    }
}
//https://beginnersbook.com/2013/12/how-to-loop-arraylist-in-java/
//https://www.geeksforgeeks.org/multidimensional-collections-in-java/
//https://www.geeksforgeeks.org/array-class-in-java/

//debug code
//for (int c2 = 0; c2 < raw.get(c1).size(); c2++)  // Loop through Y-Axis of 2D ArrayList
//System.out.println(raw.get(c1).get(c2));
//System.out.println(raw); 