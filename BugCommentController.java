// Controller Class
// [User Stories: #73]

// Package
//package BugTracking;


public class BugCommentController 
{
    private String[] strArray; // Private Variable
    public BugCommentController() {} // Default Constuctor
    public String[] ViewBugsForComment()
    {
        BugList bugList = new BugList();
        strArray = bugList.GetBugsForComment();
        return strArray;
    }
    public String[] CreateComments(String bug)
    {
    	BugList bugList = new BugList();
    	strArray = bugList.GetCommentForBug(bug);
    	return strArray;
    }
    public boolean InsertComment(String bugName, String bugDesc, String comment)
    {
    	BugList bugList = new BugList();
    	return bugList.CommentStatus(bugName, bugDesc, comment);
    }
}
