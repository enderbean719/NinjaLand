import com.sun.org.apache.xalan.internal.xslt.Process;
import java.io.Serializable;


public class Commands implements Serializable{

	private Character mc;
	private String input;
	private System1 s = new System1();
	private AI ai_ = new AI();
	
	
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
		s.out("Command: ");
		s.out("=========================");
		s.print(":");
		command = s.getWord();
		return command;
	}
	
	public String getBattleMovement() {
		String command = "help";
		s.out("=========================");
		s.out("Movement Command: ");
		s.out("=========================");
		s.print(":");
		command = s.getWord();
		return command;
	}
	
	
//	public String getBattleTarget() {
//		String command = "help";
//		s.out("=========================");
//		s.print("Target Command: (square id + target) Ex. 11 ninja, 11 tree, 11 water, 11 land");
//		s.out("=========================");
//		s.print(":");
//		command = s.getWord();
//		return command;
//	}
	
	
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
		mc.getMap_().printMapOfLabels();
		s.out("=========================");
		s.out("Map Commands");
		s.out("=========================");
		s.print(":");
		processMapCommand(getCommand());
		
	}
	
	public boolean processMapCommand(String c) {
		boolean commandFound = false;
		if(c.equals("north") || c.equals("n")) {
			commandFound = true;
			mc.moveNorth();
			mc.getMap_().printBattleMap();
		}else if(c.equals("south")|| c.equals("s")) {
			commandFound = true;
			mc.moveSouth();
			mc.getMap_().printBattleMap();
		}else if(c.equals("east")|| c.equals("e")) {
			commandFound = true;
			mc.moveEast();
			mc.getMap_().printBattleMap();
		}else if(c.equals("west")|| c.equals("w")) {
			commandFound = true;
			mc.moveWest();
			mc.getMap_().printBattleMap();
		}else if(c.equals("southwest")|| c.equals("sw")) {
			commandFound = true;
			mc.moveSouthWest();
			mc.getMap_().printBattleMap();
		}else if(c.equals("southeast")|| c.equals("se")) {
			commandFound = true;
			mc.moveSouthEast();
			mc.getMap_().printBattleMap();
		}else if(c.equals("northwest")|| c.equals("nw")) {
			commandFound = true;
			mc.moveNorthWest();
			mc.getMap_().printBattleMap();
		}else if(c.equals("northeast")|| c.equals("ne")) {
			commandFound = true;
			mc.moveNorthEast();
			mc.getMap_().printBattleMap();
		}else if(c.equals("look")) {
			commandFound = true;
			Area current = mc.getMap_().getAreaMC();
			if(current.getLookCount() == 0) {
				s.out(current.getLook());
				current.setLookCount(current.getLookCount() + 1);
			}else {
				s.out(current.getLookClosely());
				current.setLookCount(current.getLookCount() + 1);
			}			
		}else if(c.equals("characters") || c.equals("names")) {
			commandFound = true;
			mc.getMap_().printMapOfNames();
		}else if(c.equals("land") || c.equals("labels") ) {
			commandFound = true;
			mc.getMap_().printMapOfLabels();
		}else if(c.equals("ids")) {
			commandFound = true;
			mc.getMap_().printMapOfIds();
		}else if(c.equals("positions")) {
			commandFound = true;
			mc.getMap_().printPositionList();
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
	
	
	

	
	

	public boolean processBattleMapCommand(String c, boolean isAi) {
		boolean moveSuccess = false;
		while(   !(c.equals("back") || c.equals("cancel") )   &&    moveSuccess == false ){
			if(c.equals("north") || c.equals("n")) {
				moveSuccess = mc.moveNorth();
			}else if(c.equals("south")|| c.equals("s")) {
				moveSuccess = mc.moveSouth();
			}else if(c.equals("east")|| c.equals("e")) {
				moveSuccess = mc.moveEast();
			}else if(c.equals("west")|| c.equals("w")) {
				moveSuccess = mc.moveWest();
			}else if(c.equals("southwest")|| c.equals("sw")) {
				moveSuccess = mc.moveSouthWest();
			}else if(c.equals("southeast")|| c.equals("se")) {
				moveSuccess = mc.moveSouthEast();
			}else if(c.equals("northwest")|| c.equals("nw")) {
				moveSuccess = mc.moveNorthWest();
			}else if(c.equals("northeast")|| c.equals("ne")) {
				moveSuccess = mc.moveNorthEast();
			}else if(c.equals("help")) {
				//help menu
				this.printBattleMapCmds();
			}else {
				this.printBattleMapCmds();
			}
			if(moveSuccess == false) {
				if(isAi){
					break;   //loop at Parent level
					//c = ai_.getRandomBattleMoveDirection();
				}else{
					s.out("Enter a different direction or cancel");
					c = this.getCommand();
				}

			}
			
		}//while
		return moveSuccess;
	}//processBattleMapCommand
	
	
	
	

	public void cStats() {
		s.out("=========================");
		s.out("         STATS           ");
		s.out("=========================");
		this.mc.getStats_().printStats();
		s.out("=========================");
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
		s.out("=========================");
	}
	
	public void printMapCmds() {
		s.out("=========================");
		s.out("     MAP COMMANDS    ");
		s.out("=========================");
		s.out("north 		(n) = go 1 square up");
		s.out("south 		(s) = go 1 square down");
		s.out("east  		(e) = go 1 to the left");
		s.out("west  		(w) = go 1 to the right");
		s.out("southeast (se) = go 1 square diagonally southeast");
		s.out("southwest (sw) = go 1 square diagonally southwest");
		s.out("northeast (ne) = go 1 square diagonally northeast");
		s.out("northwest (nw) = go 1 square diagonally northwest");
		s.out("look = observe area (use twice in a row to get a more detailed description)");
		s.out("approach = interact with objects");
		s.out("characters");
		s.out("land");
		s.out("ids");
		s.out("positions");
		s.out("=========================");
	}
	
	public void printBattleMapCmds() {
		s.out("=========================");
		s.out("     MAP COMMANDS    ");
		s.out("=========================");
		s.out("north 		(n) = go 1 square up");
		s.out("south 		(s) = go 1 square down");
		s.out("east  		(e) = go 1 to the left");
		s.out("west  		(w) = go 1 to the right");
		s.out("southeast (se) = go 1 square diagonally southeast");
		s.out("southwest (sw) = go 1 square diagonally southwest");
		s.out("northeast (ne) = go 1 square diagonally northeast");
		s.out("northwest (nw) = go 1 square diagonally northwest");
		s.out("back = cancel move action");
		s.out("=========================");
	}
	//public void 

	public Character getMc() {
		return mc;
	}

	public void setMc(Character mc) {
		this.mc = mc;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public System1 getS() {
		return s;
	}

	public void setS(System1 s) {
		this.s = s;
	}
	
	
}//end Commands
