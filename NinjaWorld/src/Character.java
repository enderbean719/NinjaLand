import java.util.HashMap;
//import java.util.Math;

public class Character extends Creature {

	public String clan;
	public String gender;
	public int age;	
	public Relationships rel_;
	public Map map_;
	public Squad squad_;
	public Summonings summonings_;
	public Battle battle_;
	public Commands commands_;
	public int exp;
	private int expToLevelUp;
	public boolean isAI;
	
	
	
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
		this.isAI = true;
		 
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
	
	
	
	
}//end Character
