import java.util.ArrayList;
import java.util.List;

public class Map {

	private int width;
	private int height; 
	public ArrayList<Area> map = new ArrayList<Area>();
	System1 s = new System1();
	
	Map(int width, int height){
		int id = 1; 
		this.width = width;
		this.height = height;
		
		
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				map.add(  new Area(x, y, id)  );
				id++;
			}
		} 
		
		
		
	}//end Map
		
	public Area getArea(int xx, int yy) {
		Area a = new Area();
		int l = map.size();
		for(int i=0; i<l; i++) {
			a = map.get(i);
			if(a.x == xx && a.y == yy) {
				break;
			}
		}
		return a;
	}
	

	public void printMapOfIds() {
		printMapOf("ids");
	}
	
	public void printMapOfLabels() {
		printMapOf("labels");
	}
	
	public void printMapOf(String output) {

		Area a = new Area();
		
		for(int yy = 0; yy<height; yy++) {
			//one row
			for(int xx = 0; xx < this.width; xx++) {
				s.print("_____");
			}
			s.print("_");
			s.out("");
			for(int xx = 0; xx < this.width; xx++) {
				a = getArea(xx,yy);
				s.print("|");
				if(output == "ids") {
					s.print(String.format("%4d", a.id));
				}else {
					s.print(String.format("%4c", a.legendChar));
				}
			}
			s.print("|");
			s.out("");
			for(int xx = 0; xx < this.width; xx++) {
				s.print("|____");
			}
			s.print("|");
			s.out("");
			
		}
			
		
	}//printMapIds
	
	
}//end map
