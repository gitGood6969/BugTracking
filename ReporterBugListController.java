// Controller Class
// [User Stories: #52]

// Package
//package BugTracking;

public class ReporterBugListController 
{
    private String[] strArray; // Private Variable
    public ReporterBugListController() {} // Default Constuctor

    public String[] ViewListOfBugs(String role, String uID)
    {
        BugList bugList = new BugList();
        strArray = bugList.getListOfBugs(role, uID);
        return strArray;
    }
}