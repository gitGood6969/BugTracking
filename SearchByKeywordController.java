// Controller class
// [User Stories: #79]

// Package
// package BugTracking;

// Import Libraries

public class SearchByKeywordController 
{
    // Default Constructor
    public SearchByKeywordController(){}

    // Method forwards a keyword to entity class and returns a 
    // String array to boundary class
    public String[] ValidateKeyword(String keyword)
    {
        BugList bugList = new BugList();
        return bugList.searchByKeywordResult(keyword);
    }
}