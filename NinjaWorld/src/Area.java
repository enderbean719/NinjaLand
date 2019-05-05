import java.util.ArrayList;

import sun.print.resources.serviceui;

public class Area {
	private int id;
	private int x;
	private int y;
	private int lookCount;
	private System1 s = new System1();
	private boolean passableByLand = true;		//walkable true
	private boolean passableBySky = true;		//flyable true
	private boolean passableByWater = false;		//swimmable false
	private boolean passableByTree = false;
	private boolean passableByEarth = true;
	private boolean enterable = false;  //doorway false
	
	private String look = "hmm...nothing interesting here";			//1st look description
	private String lookClosely = "hmm...nothing special...I think I should look somewhere else.";	//2nd look description
	
	private String legendChar = "";  //used for showing what it contains on the map
	private ArrayList<Object> containsObj = new ArrayList<Object>();
	

	public Area(int xx, int yy){
		this.x = xx;
		this.y = yy;
	}
	
	public Area(int xx, int yy, int id){
		this.x = xx;
		this.y = yy;
		this.id = id;
	}
	
	public Area(){
		
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
			if(n instanceof Character) {
				numCr++;
				nameCr = ((Character) n).name;
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
	
	
	public boolean passableBy(String travelType) {
		//sky, land, water, tree, earth, none
		if(travelType.equals("sky") && this.passableBySky == true) {
			return true;
		}
		if(travelType.equals("land") && this.passableByLand == true) {
			return true;
		}
		if(travelType.equals("water") && this.passableByWater == true) {
			return true;
		}
		if(travelType.equals("tree") && this.passableByTree == true) {
			return true;
		}
		if(travelType.equals("earth") && this.passableByEarth == true) {
			return true;
		}
		return false;
	}//end passableBy
	
	
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
	
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLookCount() {
		return lookCount;
	}

	public void setLookCount(int lookCount) {
		this.lookCount = lookCount;
	}

	public System1 getS() {
		return s;
	}

	public void setS(System1 s) {
		this.s = s;
	}

	public boolean isPassableByLand() {
		return passableByLand;
	}

	public void setPassableByLand(boolean passableByLand) {
		this.passableByLand = passableByLand;
	}

	public boolean isPassableBySky() {
		return passableBySky;
	}

	public void setPassableBySky(boolean passableBySky) {
		this.passableBySky = passableBySky;
	}

	public boolean isPassableByWater() {
		return passableByWater;
	}

	public void setPassableByWater(boolean passableByWater) {
		this.passableByWater = passableByWater;
	}

	public boolean isPassableByTree() {
		return passableByTree;
	}

	public void setPassableByTree(boolean passableByTree) {
		this.passableByTree = passableByTree;
	}

	public boolean isPassableByEarth() {
		return passableByEarth;
	}

	public void setPassableByEarth(boolean passableByEarth) {
		this.passableByEarth = passableByEarth;
	}

	public boolean isEnterable() {
		return enterable;
	}

	public void setEnterable(boolean enterable) {
		this.enterable = enterable;
	}

	public String getLook() {
		return look;
	}

	public void setLook(String look) {
		this.look = look;
	}

	public String getLookClosely() {
		return lookClosely;
	}

	public void setLookClosely(String lookClosely) {
		this.lookClosely = lookClosely;
	}

	public String getLegendChar() {
		return legendChar;
	}

	public void setLegendChar(String legendChar) {
		this.legendChar = legendChar;
	}

	public ArrayList<Object> getContainsObj() {
		return containsObj;
	}

	public void setContainsObj(ArrayList<Object> containsObj) {
		this.containsObj = containsObj;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}//end Area


