// Controller Class
// [User Stories: #49]

// Package
//package BugTracking;


public class DeveloperUpdateStatusController 
{
    private String[] strArray; // Private Variable
    public DeveloperUpdateStatusController() {} // Default Constructor  

    public String[] ViewListOfBugs(String role, String id)
    {
        BugList bugList = new BugList();
        strArray = bugList.getListOfBugs(role, id);
        return strArray;
    }
    
    public String[] DeveloperGetBug(String bug, int num)
    {
    	BugList bugList = new BugList();
    	strArray = bugList.GetBug(bug,num);
    	return strArray;
    }
    
    public boolean DeveloperUpdateStatus(String bugName, String bugDesc, String updatedStatus, int num)
    {
    	BugList bugList = new BugList();
    	return bugList.UpdateBugStatus(bugName, bugDesc, updatedStatus, num);
    }
}
