import java.util.ArrayList;
import java.util.HashMap;

import sun.print.resources.serviceui;
import java.io.Serializable;

public class Area implements Serializable{
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

	public void listObjects(){

		for( Object o : this.containsObj){
			if (o instanceof  Character){
				s.out(this.containsObj.indexOf(o) + ": " + ((Character) o).getName() );

			}
		}
	}//end listObjects

	public Character getCharacterFromIndex(int index){
		for( Object o : this.containsObj){
			if (o instanceof  Character){
				if(this.containsObj.indexOf(o) == index){
					return ((Character) o);
				}

			}
		}
		return null;
	}
	
	public HashMap<Integer,Character> outputValidTargetChar(int startingId){
		Character targetChar;
		HashMap<Integer,Character> listChar = new HashMap<Integer,Character>();
		for(Object o: this.containsObj) {
			if(o instanceof Character) {
				targetChar = ((Character) o);
				listChar.put(startingId, targetChar);
				startingId++;
			} 
		}
		return listChar;
	}//outputValidTargetChar
	
	public HashMap<Integer,String> outputValidTargetEnvi(int startingId){
		HashMap<Integer,String> list = new HashMap<Integer,String>();
		if(this.isPassableByLand()) {
			list.put(startingId,"land");
			startingId++;
		}
		if(this.isPassableByWater()) {
			list.put(startingId,"water");
			startingId++;
		}
		if(this.isPassableByEarth()) {
			list.put(startingId,"earth");
			startingId++;
		}
		if(this.isPassableByTree()) {
			list.put(startingId,"tree");
			startingId++;
		}
		if(this.isPassableBySky()) {
			list.put(startingId,"sky");
			startingId++;
		}
		return list;
	}//outputValidTargetEnvi
	
	public int getId() {
		return this.id;
	}
	
	
	
	private void addObj(Object o) {
		this.containsObj.add(o);
	}
	

	public String getNames() {   //for map
		String output = "";
		String nameCr = "";
		int numCr = 0 ;
		//count characters and creatures in this area
		for(Object n : this.containsObj) {
			if(n instanceof Character) {
				numCr++;
				nameCr = ((Character) n).getName();
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
	
	
	
	

	public String getNames6() {   //for map
		String output = "";
		String nameCr = "";
		int numCr = 0 ;
		//count characters and creatures in this area
		for(Object n : this.containsObj) {
			if(n instanceof Character) {
				numCr++;
				nameCr = ((Character) n).getName();
			}
		}
		
		if(numCr == 0) {
			output = "      ";  					//empty 6 char
		}else if(numCr == 1) {
			int len = nameCr.length();
			if(len>=6) {							//(len>=6)
				output = nameCr.substring(0, 6);	
			}else {
				output = nameCr.substring(0, len);
			}			
		}else if(numCr > 1) {
			output = String.format("%5d", numCr)+"c";
//			output = numCr + "c  ";
//			if(numCr >= 10) {
//				output = "#c  ";
//			}
		}else {
			output = "xxxxxx";
			s.out(nameCr + numCr);
		}
		
		return output;
		
	}//end getNames6
	
	
	
	
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
	
	public int getSumOfHP() {
		int sum = 0;
		for(Object c: this.containsObj) {
			if(c instanceof Character) {
				sum += ((Character) c).getStats_().getCurrentHP();
			}
		}
		return sum;
	}
	
	

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


