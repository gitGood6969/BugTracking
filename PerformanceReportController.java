// Controller Class
// [User Stories: #77]

// Package
//package BugTracking;

// Import Libraries
import java.util.ArrayList; // To use the ArrayList variable container
import java.util.Arrays;    // To use Array containers.
import java.util.HashMap;   // To use HashMap to hold the initial value
import java.util.Map;       // To use Map to hold the initial value
import java.util.Iterator;  // To use Iterator to loop through the HashMap
//import java.util.TreeMap;   // To use TreeMap to arrange in values as its a Binary Search Tree

public class PerformanceReportController 
{
    // Private Variables
    private ArrayList<String []> rawBugData = new ArrayList<String []>();
    private ArrayList<String []> rawUserData = new ArrayList<String []>();

    // Default Constructor
    public PerformanceReportController() 
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

    //public ArrayList<String> compileBugReport
    public ArrayList<String> compilePerformanceReport(String user)
    {
        // Used to hold the final report generated
        ArrayList<String> performanceReport = new ArrayList<String>();

        if (user.equals("Reporter"))
        {// Do report for reporter
            performanceReport = reporterPerformance();
        }
        else if (user.equals("Developer"))
        {// Do report for Developer
            performanceReport = developerPerformance(); 
        }
        else // Just in case the user supplies an illegal user type...
        {// Returns an error message UwU
            String a = "Illegal user type!\nPlease try again!";   
            performanceReport.add(a);
        }
        return performanceReport;
    }

    // Method which generates the best performing reporter 
    public ArrayList<String> reporterPerformance()
    {
        // Used to hold the reporter's performance report
        ArrayList<String> reporterPerformance = new ArrayList<String>();
        
        // Create a new hashMap to hold data extracted from the list of bugs 
        HashMap<Integer, Integer> bugMap = new HashMap<>();
             
        // Loop through the list of bug data and fill up a hash map (bugmap)
        // with the reporter's ID and how many bug reports they have created
        for (int i = 0; i < rawBugData.size(); i++)
        {
            // Extract the Reporter ID
            Integer id = Integer.parseInt(rawBugData.get(i)[1]);

            // Checks to see if there is a reporter ID with a counter in the Map  
            if(bugMap.get(id) == null)
            { // If it does not, we will add a new item to the map and increase counter to 1
                bugMap.put(id, 1);
            }
            else
            { // If it does match, we will increment existing counter
                int tempCounter = bugMap.get(id);
                tempCounter++; 
                bugMap.put(id, tempCounter); 
            }
        }

        // Create a new hashMap to hold data extracted from the list of users
        HashMap<Integer, String> userMap = new HashMap<>();

        // Loop through the list of user data and fill up a hash map (userMap)
        // with the reporter's ID and Name // e.g. 2, tom 
        for (int i = 0; i < rawUserData.size(); i++)
        {
            // Extract data from list of users
            Integer role = Integer.parseInt(rawUserData.get(i)[2]); // Role
              Integer id = Integer.parseInt(rawUserData.get(i)[3]); // ID
             String name = rawUserData.get(i)[0];                   // Name

            // Checks to see if the user's role is Reporter AKA "1"
            if (role == 1)
            {// If it is, it will procees to try and add it to the hash map
                // Checks to see if there is a name with an ID
                if(userMap.get(id) == null)
                { // If it does not, we will add an ID and its corresponding name to the map
                    userMap.put(id,name);
                }
                else {}// If the HashMap already has the user, it will do nothing 
            }
            else {}// If the user is not a reporter, it will do nothing 
        }

        // Create a new hashMap to merge the data from both HashMaps 
        // Data is Name and the number of bugs reported
        HashMap<String, Integer> mergeMap = new HashMap<>();
        // userMap <Integer id, String name>
        // bugMap  <Integer id, Integer counter>
        //          Key       , Value

        // Using an iterator to loop through the hashMap 
        Iterator userMapIterator = userMap.entrySet().iterator();
        while (userMapIterator.hasNext()) 
        {
            // Get pointer to the current value in hashMap
            Map.Entry mapData = (Map.Entry)userMapIterator.next(); 

            // ger user ID and Static Cast it as Map object has no type specified
            int id = (int)mapData.getKey();           // From userMap
            String name = (String)mapData.getValue(); // From userMap

            // For merging both HashMaps 
            if (bugMap.get(id) == null) 
            {// Occurs when reporter is a new user and has not created any reports
                //For reporters whom are new and don't have not made any reports,
                // they will be assigned "0" 
                mergeMap.put(name, 0); 
            }
            else
            {// Occurs when reporter made reports previously
                mergeMap.put(name,bugMap.get(id)); 
            }
        }
        
        // Get best performing reporter  
        Map.Entry<String, Integer> bestReporter = null; // Is a pointer to a record in the HashMap
        for (Map.Entry<String, Integer> record : mergeMap.entrySet()) // Loop through mergeMap
        {
            if (bestReporter == null || record.getValue().compareTo(bestReporter.getValue()) > 0)
            {// Compare values
                bestReporter = record;
            }
        }

        // Add Best performing reporter to compile report
        String appendText = "Best performing Reporter: " + bestReporter.getKey();
        reporterPerformance.add(appendText);
        appendText = "Total Number of Bugs Reported: " + bestReporter.getValue();
        reporterPerformance.add(appendText);

        // Get developer whom are also TIED with the best developer    
        //Map.Entry<String, Integer> bestDeveloper = null; // Is a pointer to a record in the HashMap
        for (Map.Entry<String, Integer> record : mergeMap.entrySet()) // Loop through mergeMap
        {
            if (record.getKey().equals(bestReporter.getKey()) == false)
            {
                if (record.getValue().compareTo(bestReporter.getValue()) == 0)
                {// Compare values
                    String appendText2 = "\nBest performing developer: " + record.getKey();
                    reporterPerformance.add(appendText2);
                    appendText2 = "Total Number of Bugs Reported: " + record.getValue();
                    reporterPerformance.add(appendText2);
                }
                else {} // Do nothing if it value is smaller than best developer
            }
            else {} // Do nothing if name is the same as bestDeveloper
        }

        // Adding header
        appendText = "\nName : Number of Bugs Reported";
        reporterPerformance.add(appendText);

        // Loop through the mergeMap and format data. 
        // Add data to report.
        for (Map.Entry<String, Integer> record : mergeMap.entrySet()) // Loop through mergeMap
        {
            reporterPerformance.add(record.getKey() + ":" +  record.getValue());
        }
        return reporterPerformance;
    }

    //========================================================================
    
    // Add best performing developr to compiled report
    public ArrayList<String> developerPerformance()
    {
        // Used to hold the developer's performance report
        ArrayList<String> developerPerformance = new ArrayList<String>();

         // Create a new hashMap to hold data extracted from the list of bugs 
        HashMap<Integer, Integer> bugMap = new HashMap<>();
             
        // Loop through the list of bug data and fill up a hash map (bugmap)
        // with the developer's ID and how many bug reports have been fixed/closed by them
        for (int i = 0; i < rawBugData.size(); i++)
        { 
            // Extract the Status of the bug
            String status = rawBugData.get(i)[3];

            //check for  status of bug whether it is closed of fixed
            if (status.equals("fixed")|| status.equals("closed"))
            {// If matches means the bug is fixed OR closed then we perform operations on it
      
                // Extract the Developer ID
                Integer id = Integer.parseInt(rawBugData.get(i)[2]);

                // Checks to see if there is a developer ID with a counter in the Map  
                if(bugMap.get(id) == null)
                { // If it does not, we will add a new item to the map and increase counter to 1
                    bugMap.put(id, 1);
                }
                else
                { // If it does match, we will increment existing counter
                    int tempCounter = bugMap.get(id);
                    tempCounter++; 
                    bugMap.put(id, tempCounter); 
                }
            }
            else {} // If status does not match the program will ignore
        }

        // Create a new hashMap to hold data extracted from the list of users
        HashMap<Integer, String> userMap = new HashMap<>();

        // Loop through the list of user data and fill up a hash map (userMap)
        // with the developer's ID and Name // e.g. 4, bob 
        for (int i = 0; i < rawUserData.size(); i++)
        {
            // Extract data from list of users
            Integer role = Integer.parseInt(rawUserData.get(i)[2]); // Role
              Integer id = Integer.parseInt(rawUserData.get(i)[3]); // ID
             String name = rawUserData.get(i)[0];                   // Name

            // Checks to see if the user's role is Developer AKA "2"
            if (role == 2)
            {// If it is, it will procees to try and add it to the hash map
                // Checks to see if there is a name with an ID
                if(userMap.get(id) == null)
                { // If it does not, we will add an ID and its corresponding name to the map
                    userMap.put(id,name);
                }
                else {}// If the HashMap already has the user, it will do nothing 
            }
            else {}// If the user is not a reporter, it will do nothing 
        }

        // Create a new hashMap to merge the data from both HashMaps 
        // Data is Name and the number of bugs reported
        HashMap<String, Integer> mergeMap = new HashMap<>();
        // userMap <Integer id, String name>
        // bugMap  <Integer id, Integer counter>
        //          Key       , Value

        // Using an iterator to loop through the hashMap 
        Iterator userMapIterator = userMap.entrySet().iterator();
        while (userMapIterator.hasNext()) 
        {
            // Get pointer to the current value in hashMap
            Map.Entry mapData = (Map.Entry)userMapIterator.next(); 

            // ger user ID and Static Cast it as Map object has no type specified
            int id = (int)mapData.getKey();           // From userMap
            String name = (String)mapData.getValue(); // From userMap

            // For merging both HashMaps 
            if (bugMap.get(id) == null) 
            {// Occurs when reporter is a new user and has not created any reports
                //For reporters whom are new and don't have not made any reports,
                // they will be assigned "0" 
                mergeMap.put(name, 0); 
            }
            else
            {// Occurs when reporter made reports previously
                mergeMap.put(name,bugMap.get(id)); 
            }
        }

        // Get best performing developer  
        Map.Entry<String, Integer> bestDeveloper = null; // Is a pointer to a record in the HashMap
        for (Map.Entry<String, Integer> record : mergeMap.entrySet()) // Loop through mergeMap
        {
            if (bestDeveloper == null || record.getValue().compareTo(bestDeveloper.getValue()) > 0)
            {// Compare values
                bestDeveloper = record;
            }
            else {} // Do nothing if it value is smaller than best developer
        }

        // Add Best performing developer to compile report
        String appendText = "Best performing developer: " + bestDeveloper.getKey();
        developerPerformance.add(appendText);
        appendText = "Total Number of Bugs Reported: " + bestDeveloper.getValue();
        developerPerformance.add(appendText);

        // Get developer whom are also TIED with the best developer    
        //Map.Entry<String, Integer> bestDeveloper = null; // Is a pointer to a record in the HashMap
        for (Map.Entry<String, Integer> record : mergeMap.entrySet()) // Loop through mergeMap
        {
            if (record.getKey().equals(bestDeveloper.getKey()) == false)
            {
                if (record.getValue().compareTo(bestDeveloper.getValue()) == 0)
                {// Compare values
                    String appendText2 = "\nBest performing developer: " + record.getKey();
                    developerPerformance.add(appendText2);
                    appendText2 = "Total Number of Bugs Reported: " + record.getValue();
                    developerPerformance.add(appendText2);
                }
                else {} // Do nothing if it value is smaller than best developer
            }
            else {} // Do nothing if name is the same as bestDeveloper
        }

        // Adding header
        appendText = "\nName : Number of Bugs Reported";
        developerPerformance.add(appendText);

        // Loop through the mergeMap and format data. 
        // Add data to report.
        for (Map.Entry<String, Integer> record : mergeMap.entrySet()) // Loop through mergeMap
        {
            developerPerformance.add(record.getKey() + ":" +  record.getValue());
        }
        return developerPerformance;
    }
}
/*
#77 As a Triager, I want to generate a report for the best performing reporter or 
developer so that I can rely on them more heavily in the future due to their 
experience on identifying and fixing bugs.
https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples
https://stackoverflow.com/questions/36967655/count-same-lines-in-a-file-java
https://stackoverflow.com/questions/31288274/java-hashmap-word-count-from-a-text-file
https://www.baeldung.com/java-treemap-vs-hashmap
https://www.javatpoint.com/java-map
https://www.w3schools.com/java/java_hashmap.asp
*/
