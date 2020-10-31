// Controller Class
// Package
//package BugTracking;

public class TriagerBugListController 
{
    private String[] strArray; // Private Variable
    public TriagerBugListController() {} // Default Constructor 
    public String[] ViewListOfBugs(String role, String num)
    {
        BugList bugList = new BugList();
        strArray = bugList.getListOfBugs(role, num);
        return strArray;
    }
}