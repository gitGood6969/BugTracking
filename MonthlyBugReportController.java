// Controller Class
// [User Stories: #75]

// Package
//package BugTracking;

// Import Libraries
import java.util.ArrayList; // To use the ArrayList variable container
import java.util.Arrays;    // To use Array containers
import java.time.LocalDate; // To be able to take in Date and manipulate it 
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter so can adjust the output of the date

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
    public int countBugsPerMonth(LocalDate startDate)
    {
        int numOfDaysPast = 31;

        // Adds 31 days to the start date and assigns to endDate variable
        LocalDate endDate = startDate.plusDays(numOfDaysPast);

        int counter = 0;

        for (String[] strArr : rawData) 
        {
            String initial = strArr[6]; // Extract Date reported
            String[] tempArray = initial.split("/"); // Delimit Date according to "/"
            int sizeOfArray = tempArray.length;      // Getting length of array

            if (sizeOfArray == 3)
            {
                // Extract values year, month, day
                int year = Integer.valueOf(tempArray[2]);
                int month = Integer.valueOf(tempArray[1]);
                int day = Integer.valueOf(tempArray[0]);

                // Create Date object using the formatted date String
                LocalDate dateTemp = LocalDate.of(year, month, day);
                
                // Obtain the difference in date
                boolean differenceFromStartDate = dateTemp.isAfter(startDate);
                boolean differenceFromEndDate = dateTemp.isBefore(endDate);
                boolean equalToStartDate = dateTemp.equals(startDate);
                boolean equalToEndDate = dateTemp.equals(endDate);

                if (differenceFromStartDate == true || equalToStartDate == true)    
                {
                    if(differenceFromEndDate == true || equalToEndDate == true)
                    {
                        counter++;
                    }
                    else {} // If NOT, then the program ignores such lines
                }
                else {} // If NOT, then the program ignores such lines
            }
            else                
            {    
                // That means that it is either "0" which is no date as its 
                // still being fixed, or not properly formatted date.
                // Program will ignore such lines.  
            }
        }
        return counter;
    }

    public ArrayList<String> compileBugReport(LocalDate startDate)
    {// m is the month in number form e.g. January = 1, February = 2, etc.

        // Retrieve the total number of bugs per month specified
        int totalNumberOfBugs = countBugsPerMonth(startDate);     

        // Arraylist used to compile and hold the final report to be displayed
        ArrayList<String> compiledReport = new ArrayList<String>();

        // Add the first line to the report which is the total number of bugs 
        compiledReport.add("Total Number of Bugs for the month: " + totalNumberOfBugs);

        int numOfDaysPast = 31;
        LocalDate endDate = startDate.plusDays(numOfDaysPast);
     
        // Extract the bug name and description and add them to the report
        for (String[] strArr : rawData)
        {
            String initial = strArr[6]; // Extract Date Reported
            String[] tempArray = initial.split("/"); // Delimit Date according to "/"
            int sizeOfArray = tempArray.length;
            
            if (sizeOfArray == 3)
            {
                // Extract values year, month, day
                int year = Integer.valueOf(tempArray[2]);
                int month = Integer.valueOf(tempArray[1]);
                int day = Integer.valueOf(tempArray[0]);

                // Create Date object using the formatted date String
                LocalDate dateTemp = LocalDate.of(year, month, day);

                // Obtain the difference in date
                boolean differenceFromStartDate = dateTemp.isAfter(startDate);
                boolean differenceFromEndDate = dateTemp.isBefore(endDate);
                boolean equalToStartDate = dateTemp.equals(startDate);
                boolean equalToEndDate = dateTemp.equals(endDate);
                
                // Checks if the difference is within the threshold of a week
                if (differenceFromStartDate == true || equalToStartDate == true)    
                {
                    if(differenceFromEndDate == true || equalToEndDate == true)
                    {
                        String bugName = strArr[4];
                        String bugDescription = strArr[5];
                        compiledReport.add("\nBug Name: " + bugName);
                        compiledReport.add("Bug Description: " + bugDescription);
                    }
                    else {}
                }
                else {}
            }
            else                
            {    
                // That means that it is either "0" which is no date as its 
                // still being fixed, or not properly formatted date.
                // Program will ignore such lines.  
            }    
        }
        return compiledReport;
    }
    
}
