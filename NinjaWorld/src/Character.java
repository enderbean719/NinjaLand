import java.util.HashMap;
//import java.util.Math;

public class Character extends Creature {

	public String clan;
	public String gender;
	public int exp;
	private int expToLevelUp;
	
	public int goodDeeds;
	public int badDeeds;
	public int goodDeedsSeen;
	public int badDeedsSeen;
	public double percentGood;
	public double percentTrusted;
	public HashMap<String, Integer> goodDeedsPer;
	public HashMap<String, Integer> badDeedsPer;
	public HashMap<String, Double> relationships;
	
	public Character() {
		name_ = "Name"; 
		lvl_ = 1;
	    stats_ = new Stats();
		abilities_ = new Ability[1];
		position_ = new Position();
		status_ = new Status();
		 
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
		lvl_++;
		updateExp();
		int points = 0;
		if(lvl_ == 0) {
			points = 6;
		}else {
			points = (int) (Math.sqrt(lvl_)+2);			
		}
		this.stats_.purchaseStats(points);
	}
	
	public void updateExp() {
		exp = 0;
		double scaleOnLevel = Math.pow(lvl_, 1.3);  //x^1.3  exponent
		expToLevelUp = (int) (100.0 + (scaleOnLevel * 10.0));
		//expToLevelUp = 100 + (lvl_ * 10);
	}
}//end Character
