import com.sun.org.apache.xalan.internal.xslt.Process;

public class Commands {

	
	public String input;
	private System1 s = new System1();
	private Character mc;
	
	public Commands(Character mc) {
		this.mc = mc;
	}
	
	public void runCommands() {
		String command = getCommand();
		processCommand(command);
	}
	
	public String getCommand() {
		String command = "help";
		s.out("=========================");
		s.print("Command: ");
		command = s.getWord();
		return command;
	}
	
	public void processCommand(String c) {
		if(c == "map") {
			this.cMap();
		}else if(c == "stats") {
			this.cStats();
		}else if(c == "") {
			
		}else if(c == "") {
			
		}else if(c == "") {
			
		}else if(c == "") {
			
		}else if(c == "") {
			
		}else if(c == "help") {
			//help menu
			s.out("I'm sorry that was not a valid command.");
			s.out("=========================");
			s.out("     GENERAL COMMANDS    ");
			s.out("=========================");
			s.out("1. map");
			s.out("2. stats");
			s.out("3. ");
			s.out("4. ");
		}else {
			processMapCommand(c);
			
		}
		
		
		
	}//end process
	
	
	
	public void cMap() {
		mc.map_.printMapOfLabels();
		s.out("=========================");
		s.print("Map Command:");
		processMapCommand(getCommand());
		
	}
	
	public void processMapCommand(String c) {
		if(c == "north") {
			mc.moveNorth();
			mc.map_.printMapOfNames();
		}else if(c == "south") {
			mc.moveSouth();
			mc.map_.printMapOfNames();
		}else if(c == "east") {
			mc.moveEast();
			mc.map_.printMapOfNames();
		}else if(c == "west") {
			mc.moveWest();
			mc.map_.printMapOfNames();
		}else if(c == "look") {
			
		}else if(c == "characters") {
			
		}else if(c == "land") {
			
		}else if(c == "ids") {
			
		}else if(c == "help") {
			//help menu
			s.out("=========================");
			s.out("     MAP COMMANDS    ");
			s.out("=========================");
			s.out("north = go 1 sqare up");
			s.out("south = go 1 sqare down");
			s.out("east = go 1 to the left");
			s.out("west = go 1 to the right");
			s.out("look = observe area (use twice in a row to get a more detailed description)");
			s.out("approach = interact with objects");
			s.out("characters");
			s.out("land");
			s.out("ids");
			s.out("back = leave the map");
		}else {
			//Process.class.getName().
		}
	}

	public void cStats() {
		s.out("=========================");
		s.out("         STATS           ");
		s.out("=========================");
		this.mc.stats_.printStats();
		
	}
	
	
	
}//end Commands
