// Controller Class
// [User Stories: #73]

// Package
//package BugTracking;


public class BugCommentController 
{
    private String[] strArray; // Private Variable
    public BugCommentController() {} // Default Constuctor

    // Gets list of bugs to display to user
    public String[] ViewBugsForComment()
    {
        BugList bugList = new BugList();
        strArray = bugList.GetBugsForComment();
        return strArray;
    }

    // Get comments for the specific bug supplied in as a parameter
    public String[] CreateComments(String bug)
    {// Method retrieves all comments for the particular bug supplied as a parameter
    	BugList bugList = new BugList();
    	strArray = bugList.GetCommentForBug(bug);
    	return strArray;
    }
    public boolean InsertComment(String bugName, String bugDesc, String comment)
    {// Method inserts a new comment to a bug
        // Calls the Entity class
    	BugList bugList = new BugList();
        // Calls the method CommentStatus in entity class to insert new comment into database
    	return bugList.CommentStatus(bugName, bugDesc, comment);
    }
}
