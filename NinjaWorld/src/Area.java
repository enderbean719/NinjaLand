import java.util.ArrayList;

import sun.print.resources.serviceui;

public class Area {
	int id;
	int x;
	int y;
	int lookCount;
	private System1 s = new System1();
	public boolean passableByLand = true;		//walkable true
	public boolean passableByAir = true;		//flyable true
	public boolean enterable = false;  //doorway false
	
	public String look = "hmm...nothing interesting here";			//1st look description
	public String lookClosely = "hmm...nothing special...I think I should look somewhere else.";	//2nd look description
	
	char legendChar = ' ';  //used for showing what it contains on the map
	ArrayList<Object> containsObj = new ArrayList<Object>();
	
	Area(int xx, int yy){
		this.x = xx;
		this.y = yy;
	}
	
	Area(int xx, int yy, int id){
		this.x = xx;
		this.y = yy;
		this.id = id;
	}
	
	Area(){
		
	}
	
	public int getId() {
		return this.id;
	}
	
	
	
	private void addObj(Object o) {
		this.containsObj.add(o);
	}
	

	public String getNames() {
		String output = "";
		String nameCr = "";
		int numCr = 0 ;
		//count characters and creatures in this area
		for(Object n : this.containsObj) {
			if(n instanceof Creature) {
				numCr++;
				nameCr = ((Creature) n).name;
			}
		}
		
		if(numCr == 0) {
			output = " __ ";
		}else if(numCr == 1) {
			int len = nameCr.length();
			if(len>=5) {
				output = nameCr.substring(0, 5);
			}else {
				output = nameCr.substring(0, len);
			}			
		}else if(numCr > 1) {
			output = String.format("%3d", numCr)+"c";
//			output = numCr + "c  ";
//			if(numCr >= 10) {
//				output = "#c  ";
//			}
		}else {
			output = "xxxx";
			s.out(nameCr + numCr);
		}
		
		return output;
		
	}//end getNames
	
	
	
	
//
//	public String getNames() {
//		String output = "";
//		String nameCh = "";
//		String nameCr = "";
//		int numCh = 0 ;
//		int numCr = 0 ;
//		//count characters and creatures in this area
//		for(Object n : this.containsObj) {
//			if(n instanceof Character) {
//				numCh++;
//				nameCh = ((Character) n).name;
//			}
//			if(n instanceof Creature) {
//				numCr++;
//				nameCr = ((Creature) n).name;
//			}
//		}
//		
//		if(numCh == 0 && numCr == 0) {
//			output = "none";
//		}else if(numCh == 1 && numCr == 0) {
//			output = nameCh.substring(0, 3);
//		}else if(numCh == 0 && numCr == 1) {
//			output = nameCr.substring(0, 3);
//		}else if(numCh > 1 && numCr == 0) {
//			output = numCh + "n  ";
//			if(numCh >= 10) {
//				output = "#n  ";
//			}
//		}else if(numCh == 0 && numCr > 1) {
//			output = numCr + "c  ";
//			if(numCr >= 10) {
//				output = "#c  ";
//			}
//		}else if(numCh >= 1 && numCr >= 1) { 
//			if(numCh >= 10) {
//				nameCh = "#n";
//			}else {
//				nameCh = numCh + "n";
//			}
//			if(numCh >= 10) {
//				nameCr = "#c";
//			}else {
//				nameCr = numCr + "c";
//			}
//			output = nameCh + nameCr;
//		}else {
//			output = "xxxx";
//			s.out(nameCh + numCh);
//			s.out(nameCr + numCr);
//		}
//		
//		return output;
//		
//	}//end getNames
	
}//end Area


