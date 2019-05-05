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
	
	public String getBattleMovement() {
		String command = "help";
		s.out("=========================");
		s.print("Movement Command: ");
		command = s.getWord();
		return command;
	}
	
	public boolean processCommand(String c) {
		boolean commandFound = false;
		if(c.equals("map")) {
			commandFound = true;
			this.cMap();
		}else if(c.equals("stats")) {
			commandFound = true;
			this.cStats();
		}else if(c.equals("")) {
			
		}else if(c.equals("")) {
			
		}else if(c.equals("")) {
			
		}else if(c.equals("")) {
			
		}else if(c.equals("")) {
			
		}else if(c.equals("help")) {
			commandFound = true;
			//help menu 
			printGeneralCmds();
		}else {
			commandFound = processMapCommand(c);
			
		}
		
		return commandFound;
		
	}//end process
	
	
	
	public void cMap() {
		mc.map_.printMapOfLabels();
		s.out("=========================");
		s.out("Map Commands");
		processMapCommand(getCommand());
		
	}
	
	public boolean processMapCommand(String c) {
		boolean commandFound = false;
		if(c.equals("north")) {
			commandFound = true;
			mc.moveNorth();
			mc.map_.printMapOfNames();
		}else if(c.equals("south")) {
			commandFound = true;
			mc.moveSouth();
			mc.map_.printMapOfNames();
		}else if(c.equals("east")) {
			commandFound = true;
			mc.moveEast();
			mc.map_.printMapOfNames();
		}else if(c.equals("west")) {
			commandFound = true;
			mc.moveWest();
			mc.map_.printMapOfNames();
		}else if(c.equals("look")) {
			commandFound = true;
			Area current = mc.map_.getAreaMC();
			if(current.getLookCount() == 0) {
				s.out(current.getLook());
				current.setLookCount(current.getLookCount() + 1);
			}else {
				s.out(current.getLookClosely());
				current.setLookCount(current.getLookCount() + 1);
			}			
		}else if(c.equals("characters") || c.equals("names")) {
			commandFound = true;
			mc.map_.printMapOfNames();
		}else if(c.equals("land") || c.equals("labels") ) {
			commandFound = true;
			mc.map_.printMapOfLabels();
		}else if(c.equals("ids")) {
			commandFound = true;
			mc.map_.printMapOfIds();
		}else if(c.equals("positions")) {
			commandFound = true;
			mc.map_.printPositionList();
		}else if(c.equals("help")) {
			commandFound = true;
			//help menu
			printMapCmds();
		}else {
			//Process.class.getName().
			commandFound = false;
			s.out("Else - process next commmand list");
		}
		return commandFound;
	}
	
	
	
	
	public boolean processBattleMovementCommand(String c) {
		boolean commandFound = false;
		if(c.equals("north")) {
			commandFound = true;
			mc.moveNorth();
			mc.map_.printMapOfNames();
		}else if(c.equals("south")) {
			commandFound = true;
			mc.moveSouth();
			mc.map_.printMapOfNames();
		}else if(c.equals("east")) {
			commandFound = true;
			mc.moveEast();
			mc.map_.printMapOfNames();
		}else if(c.equals("west")) {
			commandFound = true;
			mc.moveWest();
			mc.map_.printMapOfNames();
		}
		return false;
	}
	
	

	public void cStats() {
		s.out("=========================");
		s.out("         STATS           ");
		s.out("=========================");
		this.mc.stats_.printStats();
		
	}
	
	public void printGeneralCmds() {
		s.out("=========================");
		s.out("     GENERAL COMMANDS    ");
		s.out("=========================");
		s.out("map");
		s.out("profile");
		s.out("stats");
		s.out("items");
		s.out("relationships");
		s.out("sqad");
		s.out("summonings");
		s.out("battles");
		s.out("quit");

	}
	
	public void printMapCmds() {
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
		s.out("positions");
	}
	
	//public void 
	
	
}//end Commands
