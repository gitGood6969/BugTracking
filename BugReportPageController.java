// Package
//package BugTracking;

// Controller class
public class BugReportPageController 
{
    // Default Constructor
    public BugReportPageController(){}

    // Auxiliary Method
    public boolean CreateBugReport(String bugName, String bugDescription)
    {
        BugList buglist = new BugList();
        return buglist.InsertBugReport(bugName, bugDescription);
    }     
}
