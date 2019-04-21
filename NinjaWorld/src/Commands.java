
public class Commands {

	
	public String input;
	private System1 s = new System1();
	
	public Commands() {
		
	}
	
	public String getCommand() {
		String command = "help";
		s.print("Command:");
		s.getWord();
		return command;
	}
	
	public void processCommand(String command) {
		
	}
	
}//end Commands
