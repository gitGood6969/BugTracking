//package BugTracking;

public class TriagerBugListController {

	public String[] ViewListOfBugs(String role, String num)
	{
		BugList bugList = new BugList();
		return bugList.getListOfBugs(role, num);
	}
}
