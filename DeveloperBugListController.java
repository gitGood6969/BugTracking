//package BugTracking;

public class DeveloperBugListController {
	
	public String[] ViewListOfBugs(String role, String num)
	{
		BugList bugList = new BugList();
		return bugList.getListOfBugs(role, num);
	}

}
