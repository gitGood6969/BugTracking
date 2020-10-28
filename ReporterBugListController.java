// This is the Controller Class
// Package
//package BugTracking;

public class ReporterBugListController 
{	
    public String[] ViewListOfBugs(String role, String uID)
    {
        BugList bugList = new BugList();
        return bugList.getListOfBugs(role, uID);
    }
}
