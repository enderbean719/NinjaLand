import java.io.Serializable;
import java.util.HashMap;
//import java.util.Math;

public class Character   implements Serializable {
	//from CREATURE 
	private String name = "";
	private int lvl = 0 ;
	private String clan;
	private String gender;
	private int age;	
	private int money;
	private int id;
	private int exp;
	private int expToLevelUp; 
	private String[] image = {""};
	private Stats stats_ = new Stats();
	private Abilities abilities_ = new Abilities(this);
	private Position position_ = new Position();
	private Status status_ = new Status();
	private AI AI_ = new AI();
	private Relationships rel_ = new Relationships();
	private Map1 map_ = new Map1();
	private Squad squad_ = new Squad();
	private Summonings summonings_ = new Summonings();
	private Battle battle_ = new Battle();
	private Commands commands_ = new Commands(this); 
	private Items items_ = new Items();
	private Action action_ = new Action();
	
	
	
	private System1 s = new System1();
	
	public Character() {
		//from CREATURE
//		name = "Name"; 
//		lvl = 0;
//	    stats_ = new Stats();
//		abilities_ = new Abilities(this);
//		position_ = new Position();
//		status_ = new Status();
		 
	}
	
	public Character(boolean isAI, String nameInput, int lvlInput, String gender) {
		

	 	 name = nameInput;
		 lvl = lvlInput ;
		 clan = "unknown";
		 gender = "male";
		 age = 13;	
		 money = 100;
		 id = 0;
		 exp = 0;
		 expToLevelUp = 0; 
		 //image = {""};
		 stats_ = new Stats();   			//doesn't need reference to this		 
		 position_ = new Position();		//doesn't need reference to this
		 status_ = new Status();			//doesn't need reference to this		 
		 rel_ = new Relationships();		//doesn't need reference to this		 	
		 squad_ = new Squad();				//doesn't need reference to this
		 summonings_ = new Summonings();	//doesn't need reference to this
		 battle_ = new Battle();			//doesn't need reference to this		 
		 items_ = new Items();				//doesn't need reference to this
		action_ = new Action();				//doesn't need reference to this

		 abilities_ = new Abilities(this);
		 AI_ = new AI(this);
		 this.AI_.setAI(isAI);
		 map_ = new Map1(this, 1, 1);	
		 commands_ = new Commands(this);

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
	
	
	public boolean doTransitionHuman() {
		boolean tranSuccess = false;
		int choice = -1;
		choice = getTransitionNum();
		
		while(processTransitionNum(choice) == false && choice !=0){
			s.out("");
			choice = getTransitionNum();
		}
		tranSuccess = true;		
		if(choice == 0) {
			tranSuccess = false;
		}
		return tranSuccess;
	}
	
	public boolean doTransitionAI() {
		return processTransitionNum(s.getRandomIntBetween(1, 6));
	}
	
	public int getTransitionNum() {
		s.out(this.name + this.position_.getFormalEnviPosition());
		s.out("Select destination");
		s.out("0. Cancel transition");
		s.out("1. Land");
		s.out("2. Into the trees");
		s.out("3. Underwater (swimming)");
		s.out("4. Walking on water");
		s.out("5. Underground (tunneling)");
		s.out("6. Into the sky (flying)");
		//s.out(7. Onto the rocks");
		return s.getIntBetween(0, 6);
	}
	
	public boolean processTransitionNum(int t) {
		boolean success = false;
		switch(t) {
		case 1:
			success = transition("land");
			break;
		case 2:
			success = transition("tree");
			break;
		case 3:
			success = transition("inWater");
			break;
		case 4:
			success = transition("onWater");
			break;
		case 5:
			success = transition("earth");
			break;
		case 6:
			success = transition("sky");
			break;
		}
		return success;
	}
	
	public boolean transition(String t) {
		 //sky, land, inWater, onWater, tree, earth, none
		boolean tSuccess = false;
		Area a = this.map_.getArea(this.position_.getX(), this.position_.getY());
		if(t.equals("sky")) {
			if(this.status_.isCanFly() == true) {
				if(a.isPassableBySky()) {
					this.position_.setInSky(true);
					s.out(this.name + " has begun flying.");
					tSuccess = true;
				}else {
					s.out("Can't access the sky here.");
				}
			}else {
				s.out(this.name + " can't fly.");
			}
		}else if(t.equals("earth")) {
			if(this.status_.isCanGoUnderground() == true) {
				if(a.isPassableByEarth()) {
					this.position_.setUnderGround(true);
					s.out(this.name + " has gone underground.");
					tSuccess = true;
				}else {
					s.out("Can't access the earth here.");
				}
			}else {
				s.out(this.name + " can't go underground.");
			}
		}else if(t.equals("inWater")) {
			if(this.status_.isCanSwim() == true) {
				if(a.isPassableByWater()) {
					this.position_.setInWater(true);
					s.out(this.name + " has gone into the water.");
					tSuccess = true;
				}else {
					s.out("Can't access any water here.");
				}
			}else {
				s.out(this.name + " can't swim.");
			}
		}else if(t.equals("onWater")) {
			if(this.status_.isCanWalkOnWater() == true) {
				if(a.isPassableByWater()) {
					this.position_.setOnWater(true);
					s.out(this.name + " has gone onto the water.");
					tSuccess = true;
				}else {
					s.out("Can't access any water here.");
				}
			}else {
				s.out(this.name + " can't walk on water.");
			}
		}else if(t.equals("tree")) {
			if(this.status_.isCanClimbWalls() == true) {
				if(a.isPassableByTree()) {
					this.position_.setInTree(true);
					s.out(this.name + " has gone into the trees.");
					tSuccess = true;
				}else {
					s.out("Can't access any trees here.");
				}
			}else {
				s.out(this.name + " can't climb trees.");
			}
		}else if(t.equals("land")) {
			this.position_.setOnLand(true);
			s.out(this.name + " has gone onto the land.");
			tSuccess = true;
		}		
		return tSuccess;
	}//end transition
	
	public boolean move(String m) { //input cardinal direction
		boolean moveSuccess = false;
		int x, y, maxX, maxY;
		x = this.position_.getX();
		y = this.position_.getY();
		maxX = this.map_.getWidth() - 1;
		maxY = this.map_.getHeight() - 1;
		
		if(m=="north") {
			if(y > 0) {
				y--;
				moveSuccess = true;
			}else {
				s.out("Cannot go any furthur north");
			}			
		}else if(m=="south") {
			if(y < maxY) {
				y++;
				moveSuccess = true;
			}else {
				s.out("Cannot go any furthur south");
			}
		}else if(m=="east") {
			if(x < maxX) {
				x++;
				moveSuccess = true;
			}else {
				s.out("Cannot go any furthur east");
			}
		}else if(m=="west") {
			if(x > 0) {
				x--;
				moveSuccess = true;
			}else {
				s.out("Cannot go any furthur west");
			}
		}else {
			s.out( m + "?");
		}
		
		if(moveSuccess == true) {
			Area targetA = this.map_.getArea(x,y);
			String travel = this.position_.getTravelType();
			if(targetA.passableBy(travel) ) {
				this.map_.placeCreatureAndRemove(this, x, y);  //places on the new x,y removes from the old x,y in  this.position_
			}else {
				s.out("That area is not passable by " + travel);
			}
		}
		return moveSuccess;
		
		
	}//end move
	
	
	public boolean moveNorth() {
		return move("north");
	}
	public boolean moveSouth() {
		return move("south");
	}
	public boolean moveEast() {
		return move("east");
	}
	public boolean moveWest() {
		return move("west");
	}
	
	
	
	
	 public void printStatsFormal() {
		 s.out("");
		 s.out(this.name + " stats:");
		 this.stats_.printStats();
	 }
	 
	 
	public boolean canDo(Ability ab) {
		//chakra, range, status can move, hands free etc
		//s.out(mc.name + " doesn't have enough chakra for " + ab.getName);
		return true;
	}
 
 
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Map1 getMap_() {
		return map_;
	}

	public void setMap_(Map1 map_) {
		this.map_ = map_;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getClan() {
		return clan;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Stats getStats_() {
		return stats_;
	}

	public void setStats_(Stats stats_) {
		this.stats_ = stats_;
	}

	public Abilities getAbilities_() {
		return abilities_;
	}

	public void setAbilities_(Abilities abilities_) {
		this.abilities_ = abilities_;
	}

	public Position getPosition_() {
		return position_;
	}

	public void setPosition_(Position position_) {
		this.position_ = position_;
	}

	public Status getStatus_() {
		return status_;
	}

	public void setStatus_(Status status_) {
		this.status_ = status_;
	}

	public AI getAI_() {
		return AI_;
	}

	public void setAI_(AI aI_) {
		AI_ = aI_;
	}

	public Relationships getRel_() {
		return rel_;
	}

	public void setRel_(Relationships rel_) {
		this.rel_ = rel_;
	}

	public Squad getSquad_() {
		return squad_;
	}

	public void setSquad_(Squad squad_) {
		this.squad_ = squad_;
	}

	public Summonings getSummonings_() {
		return summonings_;
	}

	public void setSummonings_(Summonings summonings_) {
		this.summonings_ = summonings_;
	}

	public Battle getBattle_() {
		return battle_;
	}

	public void setBattle_(Battle battle_) {
		this.battle_ = battle_;
	}

	public Commands getCommands_() {
		return commands_;
	}

	public void setCommands_(Commands commands_) {
		this.commands_ = commands_;
	}

	public Items getItems_() {
		return items_;
	}

	public void setItems_(Items items_) {
		this.items_ = items_;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpToLevelUp() {
		return expToLevelUp;
	}

	public void setExpToLevelUp(int expToLevelUp) {
		this.expToLevelUp = expToLevelUp;
	}

	public String[] getImage() {
		return image;
	}

	public void setImage(String[] image) {
		this.image = image;
	}

	public System1 getS() {
		return s;
	}

	public void setS(System1 s) {
		this.s = s;
	}

	public Action getAction_() {
		return action_;
	}

	public void setAction_(Action action_) {
		this.action_ = action_;
	}
		 
}//end Character
