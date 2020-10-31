// Controller Class
// Package
//package BugTracking;

public class ReviewerBugListController
{
    private String[] strArray; // Private Variable
    public ReviewerBugListController() {} // Default Constructor 
    public String[] ViewListOfBugs(String role, String num)
    {
        BugList bugList = new BugList();
        strArray = bugList.getListOfBugs(role, num);
        return strArray;
    }
}