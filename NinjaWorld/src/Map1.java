import java.util.ArrayList;
import java.util.List;

public class Map1 {

	private int width;
	private int height; 
	public ArrayList<Area> areas = new ArrayList<Area>();
	System1 s = new System1();
	Character mc;
	
	
	
	
	public Map1(int width, int height){
		int id = 1; 
		this.width = width;
		this.height = height;		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				areas.add(  new Area(x, y, id)  );
				id++;
			}
		} 		
	}//end Map
	
	public Map1(){
		new Map1(1,1);
	}
	
	public Map1(Character mc, int width, int height){
		int id = 1; 
		this.mc = mc;
		mc.map_ = this;
		this.width = width;
		this.height = height;		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				areas.add(  new Area(x, y, id)  );
				id++;
			}
		} 		
		placeMC(0,0);
	}//end Map
		
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	public Area getArea(int xx, int yy) {
		Area a = new Area();
		int l = areas.size();
		for(int i=0; i<l; i++) {
			a = areas.get(i);
			if(a.x == xx && a.y == yy) {
				break;
			}
		}
		return a;
	}
	
	
	public void placeMC(int x, int y) {
		getArea(mc.position_.x,mc.position_.y).containsObj.remove(mc);
		mc.position_.x = x;
		mc.position_.y = y;
		getArea(x,y).containsObj.add(mc);
	}
	
	public void placeCreatureAndRemove(Character c, int x, int y) {
		getArea(c.position_.x, c.position_.y).containsObj.remove(c);
		c.position_.x = x;
		c.position_.y = y;
		getArea(x,y).containsObj.add(c);
	}
	
	 
	public void printMapOfIds() {
		printMapOf("ids");
	}
	
	public void printMapOfLabels() {
		printMapOf("labels");
	}
	

	public void printMapOfNames() {
		printMapOf("names");
	}
	
	private void printMapOf(String output) {

		Area a = new Area();
		
		for(int yy = 0; yy<height; yy++) {
			//one row
			for(int xx = 0; xx < this.width; xx++) {
				s.print("______");
			}
			s.print("_");
			s.out("");
			for(int xx = 0; xx < this.width; xx++) {
				a = getArea(xx,yy);
				s.print("|"); // beginning of cell wall left side
				
				//output data into 4 characters of space before next cell wall begins
				if(output == "ids") {
					s.print(String.format("%5d", a.id));
				}else if(output == "labels"){
					s.print(String.format("%5c", a.legendChar));
				}else if(output == "names") {
					s.print(String.format("%5s", a.getNames()));
					//s.print(a.getNames() );
				}else {
					s.print("     ");
				}
			}
			s.print("|");
			s.out("");
			for(int xx = 0; xx < this.width; xx++) {
				s.print("|_____");
			}
			s.print("|");
			s.out("");
			
		}//end printMapOf				
	}//printMapIds
	
	public Area getAreaMC() { 
			int x, y;
			x = this.mc.position_.x;
			y = this.mc.position_.y;
			return getArea(x,y);
	}
	
	
	public void printPositionList() {
		String row = "";
		for(Area a : this.areas ) {
			for(Object o : a.containsObj) {
				if(o instanceof Character) {
					row = ((Character) o).name;
					row = row + " : (" + ((Character) o).position_.x + "," + ((Character) o).position_.y;
					s.out(row);
				}
			}
		}
	}//end printPositionList
	
}//end map