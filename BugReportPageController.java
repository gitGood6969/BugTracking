// Controller class
// Package
//package BugTracking;

public class BugReportPageController 
{	
    public boolean CreateBugReport(String bugName, String bugDescription)
    {
        BugList buglist = new BugList();
        return buglist.InsertBugReport(bugName, bugDescription);
    }

}
