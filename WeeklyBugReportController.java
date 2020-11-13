// Controller Class
// [User Stories: #76]

// Package
//package BugTracking;

// Import Libraries
import java.util.ArrayList; // To use the ArrayList variable container
import java.util.Arrays;    // To use Array containers.
import java.time.LocalDate; // To be able to take in Date and manipulate it 
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter so can adjust the output of the date
import java.lang.Math;      // To use the Absolute (Math.abs) method to return positive values during date comparison

public class WeeklyBugReportController 
{
    // Private Variables
    private ArrayList<String []> rawBugData = new ArrayList<String []>();
    private ArrayList<String []> rawUserData = new ArrayList<String []>();

    // Default Constructor
    public WeeklyBugReportController() 
    {
        getData();
    }
    
    // Gets list of all data from entity class
    public void getData()
    {// Calls Entity Class
        GenerateReport test = new GenerateReport();
        rawBugData = test.getRawData();
        rawUserData = test.getUserRawData();
    }
    
    public ArrayList<String> compileBugReport(LocalDate startDate)
    {
        // Arraylist used to compile and hold the final report to be displayed
        ArrayList<String> compiledReport = new ArrayList<String>();

        // Variable to set the number of days passed
        // Since its a week, we set it to "7"
        int numOfDaysPast = 7;

        // Adds 7 days to the start date and assigns to endDate variable
        LocalDate endDate = startDate.plusDays(numOfDaysPast);

        // Counter to keep track of the total number of bugs resolved
        int counter = 0;

        // Looping through the list of bug reports
        for (String[] strArr : rawBugData)
        {
            String initial = strArr[7]; // Extract Bug Fixed Date field

            // Checking to see if there is a bug fixed date
            String[] tempArray = initial.split("/"); // Delimit Date according to "/"
            int sizeOfArray = tempArray.length;      // Getting length of array

            // Check Array size
            if (sizeOfArray == 3) // That means there is a date in the format of dd/MM/yyyy
            {
                // Format the date to a String of format "yyyy-MM-dd"
                String tempDateFormat = tempArray[2] + "-" + tempArray[1] + "-" + tempArray[0];
                
                // Create Date object using the formatted date String
                LocalDate dateTemp = LocalDate.parse(tempDateFormat);

                // Obtain the difference in date and take it as an "absolute" value (No negative values)
                int differenceFromStartDate = Math.abs(startDate.compareTo(dateTemp));
                int differenceFromEndDate   = Math.abs(endDate.compareTo(dateTemp));

                // Checks if the difference is within the threshold of a week
                if (differenceFromStartDate <= numOfDaysPast && differenceFromEndDate <= numOfDaysPast)
                {
                    // If its within, we extract the relevant information and generate the report
                    String bugName = strArr[4];
                    String bugDescription = strArr[5];
 
                    // Calles method getDeveloperName to retrieve the developer's name
                    String developerName = getDeveloperName(strArr[2]);

                    compiledReport.add("\nBug Name: " + bugName);
                    compiledReport.add("Bug Description: " + bugDescription);
                    compiledReport.add("Bug was fixed on: " + initial);  
                    compiledReport.add("Bug was fixed by: " + developerName);
                    counter++; // Increment counter
                }
                else
                {
                    // If NOT, then the program ignores such lines
                }    
            }
            else                
            {    
                // That means that it is either "0" which is no date as its 
                // still being fixed, or not properly formatted date.
                // Program will ignore such lines.  
            }
        }

        // Set the format for the date to be displayed
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Appending to the front of the arrayList and adjusting the date format.
        // The way it appends is in reverse order as we are adding to the front
        // of the Arraylist.
        String appendText = "To: " + endDate.format(dateFormat);
        compiledReport.add(0, appendText);
        appendText = "From: " + startDate.format(dateFormat);
        compiledReport.add(0, appendText);
        appendText = "Number of bugs resolved in the week: " + counter;
        compiledReport.add(0, appendText);

        return compiledReport;
    }
    
    // Method takes in the assignee ID and compares it against the user list 
    // Returns the name of the user
    public String getDeveloperName(String assignID)
    {
        String username = "Anonymous";

        // Convert the string value to integer
        int initialID = Integer.parseInt(assignID);
        
        // Loop through the user list 
        for (String[] strArr : rawUserData)
        {
            int userID = Integer.parseInt(strArr[3]);
            
            // Checks if ID matches
            if (userID == initialID)
            {// if it does it will return the user's name
                username = strArr[0];
            }
            else
            {
                // If it does not match, it will continue searching.
            }
        }
        return username;
    }
}