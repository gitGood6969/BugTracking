Before compiling, ensure that the following lines are commented off at the top of the file:
//Package
//package BugTracking;

If you're using the command line to compile, the following are the commands to use:
javac *.java
java BugTracking
//==============================================
How to read the database files...
BugList.txt:
reporterRole :reporterID :assignedTo :status   :bugName   :bugDescription :dateReported :bugFixedDate
1            :2          :4          :fixed    :testing 1 :bug report 1   :7/11/2020    :0
1            :2          :4          :fixing   :testing 2 :bug report 2   :2/11/2020    :0
1            :3          :4          :fixed    :testing 3 :bug report 3   :28/10/2020   :10/11/2020
1            :3          :0          :notfixed :testing 4 :bug report 4   :10/10/2020   :0

database.txt
username :password :role :ID
tom      :111      :1    :2
bob      :222      :2    :4
alice    :333      :3    :6
john     :444      :4    :8

ID are unique [no duplicates allowed]
Role:
1 -> Reporter
2 -> Developer
3 -> Reviewer
4 -> Triager

//==============================================
Overview of all classes...[sort by type]
Driver Class:
BugTracking

Boundary Class:
BugReportPageUI                 [#48]
DeveloperBugListUI              [#53]
DeveloperUpdateStatusUI         [#49]
ReporterBugListUI               [#52]
ReviewerBugListUI               [#54]
ReviewerUpdateStatusUI          [#50]
SearchByAssigneeUI              [#81]
SearchByKeywordUI               [#79]
SearchByTitleUI                 [#80]
TriagerAssignmentUI             [#51]  
TriagerBugListUI                [#55]
UserLoginUI                     [#40 & others]         

Controller Class:
BugReportPageController         [#48]
DeveloperBugListController      [#53]
DeveloperUpdateStatusController [#49]
ReporterBugListController       [#52]
ReviewerBugListController       [#54]
ReviewerUpdateStatusController  [#50]
SearchByAssigneeController      [#81]
SearchByKeywordController       [#79]
SearchByTitleController         [#80]
TriagerAssignmentController     [#51]
TriagerBugListController        [#55]
UserLoginController             [#40]

Entity Class:
BugList [Many]
User    [#40]
//==============================================
Overview of all classes...[sort by User Story]
User Stories:
#40 As a User, I want to login to the system so that I can access the system according to my roles.
UserLoginUI                     [Boundary]
UserLoginController             [Controller]
User                            [Entity]

#48 As a Reporter, I want to report a bug so that the issue will be solved
BugReportPageUI                 [Boundary]
BugReportPageController         [Controller]
BugList                         [Entity]

#49 As a Developer, I want to be able to update the status of the bug so that Reviewers can check if it has been fixed with the patches provided.
DeveloperUpdateStatusUI         [Boundary]
DeveloperUpdateStatusController [Controller]
BugList                         [Entity]

#52 As a Reporter, I want to view the list of bugs I have reported so that I know if it has been fixed.
ReporterBugListUI               [Boundary]
ReporterBugListController       [Controller]
BugList                         [Entity]

#53 As a Developer, I want to view the list of bugs that is assigned to me by triager so that I can start working on it
DeveloperBugListUI              [Boundary]
DeveloperBugListController      [Controller]
BugList                         [Entity]

#54 As a Reviewer, I want to view the list of bugs to check that if it is being fixed or not
ReviewerBugListUI               [Boundary]
ReviwewerBugListController      [Controller]
BugList                         [Entity]

#55 As a Triager, I want to view the list of bugs so that I will know whether it has been assigned to any developers
TriagerBugListUI                [Boundary]
TriagerBugListController        [Controller]
BugList                         [Entity]

#79 As a User, I want to be able to search for bugs through keywords so that I can find the bug based on the description that I entered.
SearchByKeywordUI               [Boundary]
SearchByKeyworkController       [Controller]
BugList                         [Entity]

#80 As a User, I want to be able to search for bugs through titles so that I can find the exact bug that I have encountered.
SearchByTitleUI                 [Boundary]
SearchByTitleController         [Controller]
BugList                         [Entity]

#81 As a User, I want to be able to search for bugs through assignee so that I can specify the exact assignee that the bug was assigned to.
SearchByAssigneeUI              [Boundary]
SearchByAssigneeController      [Controller]
BugList                         [Entity]


//==============================================
// NEW 10/11/2020  
//==============================================
#50 As a Reviewer, I want to be able to update the status of the bug so that I can record it as closed after checking if it has been fixed.
ReviewerUpdateStatusUI          [Boundary]
ReviewerUpdateStatusController  [Controller]
BugList                         [Entity]

#51 As a Triager, I want to be able to record the assignment of the bug so that i know that it is being handled by someone
TriagerAssignmentUI             [Boundary]
TriagerAssignmentController     [Controller]
BugList                         [Entity]
//==============================================




