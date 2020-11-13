// Controller Class
// [User Stories: #75]

// Package
//package BugTracking;

// Import Libraries
import java.util.ArrayList; // To use the ArrayList variable container
import java.util.Arrays;    // To use Array containers


public class MonthlyBugReportController 
{   
    // Private Variable
    private ArrayList<String []> rawData = new ArrayList<String []>();

    // Default Constructor
    // Calls Method to retrieve list of bug reports 
    // from Entity Class  
    public MonthlyBugReportController() 
    {getRawData();} 
      
    
    // Gets list of all data from entity class
    public void getRawData()
    {// Calls Entity Class
        GenerateReport test = new GenerateReport();
        rawData = test.getRawData();
    }

    // Counts the number of bugs reported within a month
    public int countBugsPerMonth(int m)
    {// m is the month in number form e.g. January = 1, February = 2, etc.
        int counter = 0;
        for (String[] strArr : rawData) 
        {
            String initial = strArr[6]; // Extract Date
            String[] tempArray = initial.split("/"); // Delimit Date according to "/"
            int a = Integer.parseInt(tempArray[1]); // Extract Month and convert to integer
            if (a == m) // Compare integers to parameter to check match
            {counter++;}  // If month matches, increment counter
        }
        return counter;
    }

    public ArrayList<String> compileBugReport(int m)
    {// m is the month in number form e.g. January = 1, February = 2, etc.
        
        // Retrieve the total number of bugs per month specified
        int totalNumberOfBugs = countBugsPerMonth(m);

        // Arraylist used to compile and hold the final report to be displayed
        ArrayList<String> compiledReport = new ArrayList<String>();

        // Add the first line to the report which is the total number of bugs 
        compiledReport.add("Total Number of Bugs for the month: " + totalNumberOfBugs);
        
        // Extract the bug name and description and add them to the report
        for (String[] strArr : rawData)
        {
            String initial = strArr[6]; // Extract Date
            String[] tempArray = initial.split("/"); // Delimit Date according to "/"
            int a = Integer.parseInt(tempArray[1]); // Extract Month and convert to integer
            if (a == m) // Compare integers to parameter to check match
            {// If month matches, increment counter
                String bugName = strArr[4];
                String bugDescription = strArr[5];
                compiledReport.add("\nBug Name: " + bugName);
                compiledReport.add("Bug Description: " + bugDescription);
            }    
        }
        return compiledReport;
    }
    
}
