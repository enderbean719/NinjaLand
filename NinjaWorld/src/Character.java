import java.util.HashMap;
//import java.util.Math;

public class Character extends Creature {

	public String clan;
	public String gender;
	public int age;	
	public Relationships rel_ = new Relationships();
	public Map map_ = new Map();
	public Squad squad_ = new Squad();
	public Summonings summonings_ = new Summonings();
	public Battle battle_ = new Battle();
	public Commands commands_ = new Commands(this);
	public int exp;
	private int expToLevelUp;
	public boolean isAI = false;
	public String[] image = {""};
	
	
	private System1 s = new System1();
	
	public Character() {
		name = "Name"; 
		lvl = 0;
	    stats_ = new Stats();
		abilities_ = new Ability[1];
		position_ = new Position();
		status_ = new Status();
		 
	}
	
	public Character(String isAI) {
		
		name = "Name"; 
		lvl = 0;
	    stats_ = new Stats();
		abilities_ = new Ability[1];
		position_ = new Position();
		status_ = new Status();
		if(isAI == "AI" || isAI == "Ai" || isAI == "ai") {
			this.isAI = true;
		}
		 
	}
	
	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	public Character buildC(String name) {
		
		return new Character();
		
	}
	
	public void levelUp() {
		lvl++;
		s.out("LEVEL UP TO "+ lvl + "!!!"); 		
		updateExp();
		int points = 0;
		if(lvl == 1) {
			points = 6;
		}else {
			points = (int) (Math.sqrt(lvl)+2);			
		}
		this.stats_.purchaseStats(points);
	}
	
	private void updateExp() {
		exp = 0;
		double scaleOnLevel = Math.pow(lvl, 1.3);  //x^1.3  exponent
		expToLevelUp = (int) (100.0 + (scaleOnLevel * 10.0));
		//expToLevelUp = 100 + (lvl_ * 10);
	}
	
	
	
	public void printNumber(int x) {
		//http://patorjk.com/software/taag/#p=display&f=Star%20Wars&t=234567
		//star wars ascii font
		
		//x mod 10 loop (find number of digits)   123  --> 3 digits
		//print top of 1, top of 2, top of 3
		//print mid of 1, mid of 2, mid of 3 etc
	}
	
	public void printImage() {
		for(int i = 0; i<image.length; i++) {
			s.out(image[i]);
		}
	}
	
	public void move(String m) { //input cardinal direction
		int x, y, maxX, maxY;
		x = this.position_.x;
		y = this.position_.y;
		maxX = this.map_.getWidth() - 1;
		maxY = this.map_.getHeight() - 1;
		if(m=="north") {
			if(y > 0) {
				y--;
			}else {
				s.out("Cannot go any furthur north");
			}			
		}else if(m=="south") {
			if(y < maxY) {
				y++;
			}else {
				s.out("Cannot go any furthur south");
			}
		}else if(m=="east") {
			if(x < maxX) {
				x++;
			}else {
				s.out("Cannot go any furthur east");
			}
		}else if(m=="west") {
			if(x > 0) {
				x--;
			}else {
				s.out("Cannot go any furthur west");
			}
		}else {
			s.out( m + "?");
		}
		this.map_.placeCreatureAndRemove(this, x, y);  //places on the new x,y removes from the old x,y in  this.position_
	}//end move
	
	public void moveNorth() {
		move("north");
	}
	public void moveSouth() {
		move("south");
	}
	public void moveEast() {
		move("east");
	}
	public void moveWest() {
		move("west");
	}
	
}//end Character
