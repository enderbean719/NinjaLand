import java.util.HashMap;


public class Character extends Creature {

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
}
