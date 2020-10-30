Before compiling, ensure that the following lines are commented off at the top of the file:
//Package
//package BugTracking;

If you're using the command line to compile, the following are the commands to use:
javac *.java
java BugTracking
//==============================================
How to read the database files...
BugList.txt:
reporterRole :reporterID :assignedTo :bugName   :bugDescription
1            :2          :4          :testing 1 :bug report 1
2            :2          :4          :testing 1 :bug report 1
1            :2          :3          :testing 2 :bug report 2

userlist.txt
username :password :role :ID
tom      :111      :1    :2
bob      :222      :2    :4
jane     :221      :2    :3
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
BugReportPageUI
DeveloperBugListUI
ReporterBugListUI
UserLoginUI
DeveloperBugListUI

Controller Class:
BugReportPageController
ReporterBugListController
UserLoginController
DeveloperBugListController

Entity Class:
BugList
User
//==============================================
Overview of all classes...[sort by User Story]
User Stories:
#40 As a User, I want to login to the system so that I can access the system according to my roles.
UserLoginUI                [Boundary]
UserLoginController        [Controller]
User                       [Entity]

#48 As a Reporter, I want to report a bug so that the issue will be solved
BugReportPageUI            [Boundary]
BugReportPageController    [Controller]
BugList                    [Entity]

#52 As a Reporter, I want to view the list of bugs I have reported so that I know if it has been fixed.
ReporterBugListUI          [Boundary]
ReporterBugListController  [Controller]
BugList                    [Entity]

#53 As a Developer, I want to view the list of bugs that is assigned to me by triager so that I can start working on it
DeveloperBugListUI         [Boundary]
DeveloperBugListController [Controller]
BugList                    [Entity]
//==============================================




