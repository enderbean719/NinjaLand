import java.util.ArrayList;
import java.util.List;

public class Map1 {
	
	private Character mc;
	private int width;
	private int height; 
	private ArrayList<Area> areas = new ArrayList<Area>();
	private System1 s = new System1();
	
	
	
	
	
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
		mc.setMap_(this);
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
			if(a.getX() == xx && a.getY() == yy) {
				break;
			}
		}
		return a;
	}
	
	
	public void placeMC(int x, int y) {
		getArea(mc.getPosition_().getX(),mc.getPosition_().getY()).getContainsObj().remove(mc);
		mc.getPosition_().setX(x);
		mc.getPosition_().setY(y);
		getArea(x,y).getContainsObj().add(mc);
	}
	
	public void placeCreatureAndRemove(Character c, int x, int y) {
		getArea(c.getPosition_().getX(), c.getPosition_().getY()).getContainsObj().remove(c);
		c.getPosition_().setX(x);
		c.getPosition_().setY(y);
		getArea(x,y).getContainsObj().add(c);
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
	

	public void printBattleMap() {

		Area a = new Area();
		for(int xx = 0; xx < this.width; xx++) {
			s.print("_______");										//roof of entire map
		}
		s.print("_");												//finish roof
		s.out("");
		for(int yy = 0; yy<height; yy++) {
			//one row			
			for(int xx = 0; xx < this.width; xx++) {
				a = getArea(xx,yy);
				s.print("|"); 										// beginning of cell wall left side 
				s.print(String.format("%6d", a.getId()));			//1st row data	
			}			
			s.print("|");									//end row
			s.out("");										//next row
			for(int xx = 0; xx < this.width; xx++) {
				a = getArea(xx,yy);
				s.print("|"); 										// beginning of cell wall left side 
				s.print(String.format("%6s", a.getNames6())); 		//2nd row data
			}		
			s.print("|");									//end row
			s.out("");										//next row
			for(int xx = 0; xx < this.width; xx++) {
				a = getArea(xx,yy);
				s.print("|"); 										// beginning of cell wall left side 
				if(a.getSumOfHP()==0) {
					s.print(String.format("%6s", "") );				//3rd row empty OR
				}else {
					s.print(String.format("%6d", a.getSumOfHP() ));	//3rd row data
				}
				
			}	
			s.print("|");									//end row
			s.out("");										//next row
			for(int xx = 0; xx < this.width; xx++) {
				a = getArea(xx,yy);
				s.print("|"); 										// beginning of cell wall left side 
				s.print(String.format("%6s", a.getLegendChar())); 	//4th row data
			}	
			s.print("|");									//end row
			s.out("");										//next row			 
			for(int xx = 0; xx < this.width; xx++) {
				s.print("|______");									//5th row closed			
			}
			s.print("|");
			s.out("");
			
		}//end for loop on y axis				
	}//printBattleMap
	
	
	
	
	
	
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
					s.print(String.format("%5d", a.getId()));
				}else if(output == "labels"){
					s.print(String.format("%5s", a.getLegendChar()));
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
			x = this.mc.getPosition_().getX();
			y = this.mc.getPosition_().getY();
			return getArea(x,y);
	}
	
	
	public void printPositionList() {
		String row = "";
		for(Area a : this.areas ) {
			for(Object o : a.getContainsObj()) {
				if(o instanceof Character) {
					row = ((Character) o).getName();
					row = row + " : (" + ((Character) o).getPosition_().getX() + "," + ((Character) o).getPosition_().getY();
					s.out(row);
				}
			}
		}
	}//end printPositionList
	
}//end map
