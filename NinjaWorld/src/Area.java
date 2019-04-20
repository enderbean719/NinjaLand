import java.util.ArrayList;

public class Area {
	int id;
	int x;
	int y;
	public boolean passable = true;
	
	public String look;			//1st look description
	public String lookClosely;	//2nd look description
	
	char legendChar = ' ';  //used for showing what it contains on the map
	ArrayList<Object> containsObj;
	
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
	
}//end Area


