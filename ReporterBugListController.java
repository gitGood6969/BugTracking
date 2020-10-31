// Controller Class
// Package
//package BugTracking;

public class ReporterBugListController 
{
    private String[] strArray;

    public ReporterBugListController() {} // Default Constuctor

    public String[] ViewListOfBugs(String role, String uID)
    {
        BugList bugList = new BugList();
        strArray = bugList.getListOfBugs(role, uID);
        return strArray;
    }
}
