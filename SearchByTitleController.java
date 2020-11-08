// Conroller class 
// [User Stories: #80]

// Package
// package BugTracking;

// Import Libraries

public class SearchByTitleController 
{
    // Default Constructor
    public SearchByTitleController(){}

    // Method calls passes a String as a title to Entity class
    // Returns a String Array
    public String[] ValidateTitle(String title)
    {
        BugList bugList = new BugList();
        return bugList.searchByTitleResult(title);
    }
}
