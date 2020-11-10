// Controller class 
// [User Stories: #81]

// Package
//package BugTracking;

// Import Libraries

public class SearchByAssigneeController 
{	
    // Default Constructor
    public SearchByAssigneeController(){}

    // Method passes a String Assignee to the Entity class
    // Returns a String Array
    public String[] ValidateAssignee(String assignee)
    {
        BugList bugList = new BugList();
        return bugList.searchByAssigneeResult(assignee);
    }
}
