import java.util.HashMap;
//import java.util.Math;

public class Character  {
	//from CREATURE 
	 public String name = "";
	 public int lvl = 0 ;
	 public Stats stats_ = new Stats();
	 public Abilities abilities_ = new Abilities(this);
	 public Position position_ = new Position();
	 public Status status_ = new Status();
	 public AI AI_ = new AI();
	public String clan;
	public String gender;
	public int age;	
	public int money;
	public int id;
	public Relationships rel_ = new Relationships();
	public Map1 map_ = new Map1();
	public Squad squad_ = new Squad();
	public Summonings summonings_ = new Summonings();
	public Battle battle_ = new Battle();
	public Commands commands_ = new Commands(this); 
	public Items items_ = new Items();
	public int exp;
	private int expToLevelUp; 
	public String[] image = {""};
	
	
	private System1 s = new System1();
	
	public Character() {
		//from CREATURE
		name = "Name"; 
		lvl = 0;
	    stats_ = new Stats();
		abilities_ = new Abilities(this);
		position_ = new Position();
		status_ = new Status();
		 
	}
	
	 
	
	 
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

 
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
	
	
	public void transition(String t) {
		 //sky, land, water, tree, earth, none
		Area a = this.map_.getArea(this.position_.getX(), this.position_.getY());
		if(t.equals("sky")) {
			if(this.status_.isCanFly() == true) {
				this.position_.setInSky(true);
				s.out(this.name + " has begun flying.");
			}else {
				s.out(this.name + " can't fly.");
			}
		}else if(t.equals("earth")) {
			if(this.status_.isCanGoUnderground() == true) {
				this.position_.setUnderGround(true);
				s.out(this.name + " has gone underground.");
			}else {
				s.out(this.name + " can't go underground.");
			}
		}else if(t.equals("inWater")) {
			if(this.status_.isCanSwim() == true) {
				this.position_.setInWater(true);
				s.out(this.name + " has gone into the water.");
			}else {
				s.out(this.name + " can't swim.");
			}
		}else if(t.equals("onWater")) {
			if(this.status_.isCanWalkOnWater() == true) {
				this.position_.setOnWater(true);
				s.out(this.name + " has gone onto the water.");
			}else {
				s.out(this.name + " can't walk on water.");
			}
		}else if(t.equals("tree")) {
			if(this.status_.isCanClimbWalls() == true) {
				this.position_.setInTree(true);
				s.out(this.name + " has gone into the trees.");
			}else {
				s.out(this.name + " can't climb trees.");
			}
		}else if(t.equals("land")) {
			this.position_.setOnLand(true);
			s.out(this.name + " has gone onto the land.");
		}
	}//end transition
	
	public void move(String m) { //input cardinal direction
		int x, y, maxX, maxY;
		x = this.position_.getX();
		y = this.position_.getY();
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
		Area targetA = this.map_.getArea(x,y);
		String travel = this.position_.getTravelType();
		if(targetA.passableBy(travel) ) {
			this.map_.placeCreatureAndRemove(this, x, y);  //places on the new x,y removes from the old x,y in  this.position_
		}else {
			s.out("That area is not passable by " + travel);
		}
		
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
	
	
	
	
	 public void printStatsFormal() {
		 s.out("");
		 s.out(this.name + " stats:");
		 this.stats_.printStats();
	 }
	 
	 
	
}//end Character
