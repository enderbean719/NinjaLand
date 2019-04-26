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
			s.out("=========================");
			s.out("     GENERAL COMMANDS    ");
			s.out("=========================");
			s.out("1. map");
			s.out("2. stats");
			s.out("3. ");
			s.out("4. ");
		}else {
			commandFound = processMapCommand(c);
			
		}
		
		return commandFound;
		
	}//end process
	
	
	
	public void cMap() {
		mc.map_.printMapOfLabels();
		s.out("=========================");
		s.print("Map Command:");
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
			if(current.lookCount == 0) {
				s.out(current.look);
			}else {
				s.out(current.lookClosely);
			}			
		}else if(c.equals("characters")) {
			commandFound = true;
			mc.map_.printMapOfNames();
		}else if(c.equals("land")) {
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
			s.out("back = leave the map");
		}else {
			//Process.class.getName().
			commandFound = false;
			s.out("Else - process next commmand list");
		}
		return commandFound;
	}

	public void cStats() {
		s.out("=========================");
		s.out("         STATS           ");
		s.out("=========================");
		this.mc.stats_.printStats();
		
	}
	
	
	
}//end Commands
