
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
		s.print("Command:");
		s.getWord();
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
			
		}else if(c == "") {
			
		}else {
			//help menu
			s.out("I'm sorry that was not a valid command.");
			s.out("=========================");
			s.out("     GENERAL COMMANDS    ");
			s.out("=========================");
			s.out("1. map");
			s.out("2. stats");
			s.out("3. ");
			s.out("4. ");
			
		}
		
		
		
	}//end process
	
	
	
	public void cMap() {
		mc.map_.printMapOfLabels();
		s.out("Map Command:");
		processMapCommand(getCommand());
		
	}
	
	public void processMapCommand(String c) {
		if(c == "north") {
			this.cMap();
		}else if(c == "south") {
			this.cStats();
		}else if(c == "east") {
			
		}else if(c == "west") {
			
		}else if(c == "look") {
			
		}else if(c == "characters") {
			
		}else if(c == "land") {
			
		}else if(c == "ids") {
			
		}else {
			//help menu
			if(c!="help") {s.out("I'm sorry that was not a valid command.");}
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
		}
	}

	public void cStats() {
		
	}
	
	
	
}//end Commands
