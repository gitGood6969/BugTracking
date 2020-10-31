// Controller Class
// Package
//package BugTracking;

public class ReviewerBugListController
{
    public String[] ViewListOfBugs(String role, String num)
    {
        BugList bugList = new BugList();
        return bugList.getListOfBugs(role, num);
    }
}