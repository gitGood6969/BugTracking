// Controller Class
// [User Stories: #48]

// Package
//package BugTracking;

public class BugReportPageController 
{
    // Default Constructor
    public BugReportPageController(){}
	
    public boolean CreateBugReport(String bugReportedDate, String bugName, String bugDescription)
    {
        BugList buglist = new BugList();
        return buglist.InsertBugReport(bugReportedDate, bugName, bugDescription);
    }

}