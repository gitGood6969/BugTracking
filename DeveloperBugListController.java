// Controller Class
// [User Stories: #52]

// Package
//package BugTracking;

public class DeveloperBugListController 
{
    private String[] strArray; // Private Variable
    public DeveloperBugListController() {} // Default Constructor  

    public String[] ViewListOfBugs(String role, String id)
    {
        BugList bugList = new BugList();
        strArray = bugList.getListOfBugs(role, id);
        return strArray;
    }
}
