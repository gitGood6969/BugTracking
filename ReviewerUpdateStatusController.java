// Controller Class
// [User Stories: #50]

// Package
//package BugTracking;

public class ReviewerUpdateStatusController 
{
    private String[] strArray; // Private Variable
    public ReviewerUpdateStatusController() {} // Default Constructor  
 
   public String[] ViewListOfBugs(String role, String id)
    {
        BugList bugList = new BugList();
        strArray = bugList.getListOfBugs(role, id);
        return strArray;
    }
    
    public String[] ReviewerGetBug(String bug, int num)
    {
    	BugList bugList = new BugList();
    	strArray = bugList.GetBug(bug,num);
    	return strArray;
    }
    
    public boolean ReviewerUpdateStatus(String bugName, String bugDesc, String updatedStatus, int num)
    {
    	BugList bugList = new BugList();
    	return bugList.UpdateBugStatus(bugName, bugDesc, updatedStatus, num);
    }
}