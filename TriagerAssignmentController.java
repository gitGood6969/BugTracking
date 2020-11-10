// Controller Class
// [User Stories: #51]

// Package
//package BugTracking;

public class TriagerAssignmentController 
{
    private String[] strArray; // Private Variable
    public TriagerAssignmentController() {} // Default Constructor  
 
   public String[] ViewListOfBugs(String role, String id)
    {
        BugList bugList = new BugList();
        strArray = bugList.getListOfBugs(role, id);
        return strArray;
    }
    
    public String[] TriagerGetBug(String bug, int num)
    {
    	BugList bugList = new BugList();
    	strArray = bugList.GetBug(bug,num);
    	return strArray;
    }
    
    public boolean TriagerAssignment(String bugName, String bugDesc, String assignment)
    {
    	BugList bugList = new BugList();
    	return bugList.RecordAssignment(bugName, bugDesc, assignment);
    }
}