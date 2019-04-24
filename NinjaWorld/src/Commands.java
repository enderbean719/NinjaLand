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
		if(c.equals("map")) {
			this.cMap();
		}else if(c.equals("stats")) {
			this.cStats();
		}else if(c.equals("")) {
			
		}else if(c.equals("")) {
			
		}else if(c.equals("")) {
			
		}else if(c.equals("")) {
			
		}else if(c.equals("")) {
			
		}else if(c.equals("help")) {
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
		if(c.equals("north")) {
			mc.moveNorth();
			mc.map_.printMapOfNames();
		}else if(c.equals("south")) {
			mc.moveSouth();
			mc.map_.printMapOfNames();
		}else if(c.equals("east")) {
			mc.moveEast();
			mc.map_.printMapOfNames();
		}else if(c.equals("west")) {
			mc.moveWest();
			mc.map_.printMapOfNames();
		}else if(c.equals("look")) {
			Area current = mc.map_.getAreaMC();
			if(current.lookCount == 0) {
				s.out(current.look);
			}else {
				s.out(current.lookClosely);
			}			
		}else if(c.equals("characters")) {
			mc.map_.printMapOfNames();
		}else if(c.equals("land")) {
			mc.map_.printMapOfLabels();
		}else if(c.equals("ids")) {
			mc.map_.printMapOfIds();
		}else if(c.equals("help")) {
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
			s.out("Else - process next commmand list");
		}
	}

	public void cStats() {
		s.out("=========================");
		s.out("         STATS           ");
		s.out("=========================");
		this.mc.stats_.printStats();
		
	}
	
	
	
}//end Commands
