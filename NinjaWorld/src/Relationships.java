import java.util.HashMap;

public class Relationships {


	public int goodDeeds;
	public int badDeeds;
	public int goodDeedsSeen;
	public int badDeedsSeen;
	public double percentGood;  	// = good deeds		 / (good deeds + bad deeds)
	public double percentTrusted;	// = good deeds seen / (good deeds seen + bad deeds seen)
	private HashMap<String, Integer> goodDeedsPer = new HashMap<String,Integer>();
	private HashMap<String, Integer> badDeedsPer = new HashMap<String,Integer>();
	private HashMap<String, Double> relationships = new HashMap<String,Double>();
	
	public Relationships() {
		
	}
	
	public void newRelationship50(String name) {
		addGoodDeedAgainst(name);
		addBadDeedAgainst(name);
	}
	
	public void newRelationship75(String name) {
		addGoodDeedAgainst(name);
		addGoodDeedAgainst(name);
		addGoodDeedAgainst(name);
		addBadDeedAgainst(name);
	}

	public void newRelationship25(String name) {
		addGoodDeedAgainst(name);
		addBadDeedAgainst(name);
		addBadDeedAgainst(name);
		addBadDeedAgainst(name);
	}
	
	
	public void addGoodDeedAgainst(String name) {
		Integer b = goodDeedsPer.get(name);
		if (b == null){
			goodDeedsPer.put(name, 1);
		}else {
			goodDeedsPer.put(name, b + 1);
		}
		updateRelationships(name);
	}
	
	public void addBadDeedAgainst(String name) {
		Integer b = badDeedsPer.get(name);
		if (b == null){
			badDeedsPer.put(name, 1);
		}else {
			badDeedsPer.put(name, b + 1);
		}
		updateRelationships(name);
	}
	
	
	
	private void updateRelationships(String name) {
		relationships.put(name, calcRelationship(name));
	}
	
	private double calcRelationship(String name) {
		Integer g, b;
		double r, gd, bd;
		g = goodDeedsPer.get(name);
		b = badDeedsPer.get(name);
		if(g==null) {
			g = 0;
		}
		if(b==null) {
			b = 0;
		}
		if(g==0 && b==0) {
			g = 1;
			b = 1;
		}
		gd = (double) g;
		bd = (double) b;
		r = gd / (gd + bd);
		return r;
	}
	
	public double getRelationship(String name) {
		return relationships.get(name);		
	}
	
}//end relationship
