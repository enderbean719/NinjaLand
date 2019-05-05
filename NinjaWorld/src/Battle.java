import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.lang.*;

public class Battle {

	public Squad s1;
	public Squad s2;
	public ArrayList<Squad> ss;
	public int turnCharacterId;
	public int turn;
	public ArrayList<Integer> characterIdOrder;
	public Map1 map;
	public boolean battleContinues;
	public Character currentChar;
	public HashMap<Integer, ArrayList<Ability>> activeAbilitiesByChar; 
	private int numActions;
	
	public Battle(){
		
	}
	
	public Battle(Map1 map, Squad s1, Squad s2){
		this.map = map;
		this.s1 = s1;
		this.s2 = s2;
		this.s1.squadId = 1;
		this.s2.squadId = 2;
		this.ss = new ArrayList<Squad>();
		ss.add(s1);
		ss.add(s2);
		this.turnCharacterId = 0;
		this.turn = 0;
		
	}
	

	public Result beginSquadBattle(){		
		Result result = new Result();
		
		
		
		return result;
	}
	 
	
	public Result beginSquadAmbush() {
		Result result = new Result();
		
		
		
		return result;
	}
	 
	
	public void attack() {
		//give mc list of attack options
	}
	
	

	//if ambush, s1 attack s2
	public Result beginSquadBattle(Map1 map, Squad s1, Squad s2){
		//setup
		Result result = new Result();
		this.map = map;
		this.s1 = s1;
		this.s2 = s2;
		this.s1.squadId = 1;
		this.s2.squadId = 2;
		this.ss = new ArrayList<Squad>();
		this.activeAbilitiesByChar = new HashMap<Integer, ArrayList<Ability>>();
		ss.add(s1);
		ss.add(s2);
		this.turnCharacterId = 0;
		this.turn = 0;
		
		//printBattleIntroMessage
		
		//battle loop
		battleContinues = true;
		while(battleContinues==true) {
			this.characterIdOrder  = characterIdOrder();
			//loop characters for one round
			for(int i=0; i<characterIdOrder.size(); i++) {
			//find char
				for(Squad s : ss) {
					currentChar = s.getCharById(characterIdOrder.get(i));
					if (!(currentChar==null || currentChar.equals(null) )) {
						break;
					}
				}
			//begin char turn
				//get active abilities
				ArrayList<Ability> al = new ArrayList<Ability>();
				try {
					if(activeAbilitiesByChar.get(currentChar.id) != null) {
						al = activeAbilitiesByChar.get(currentChar.id);
					}
				}catch(Exception e) {
					
				}
				//run preeffects of prior abilities
				//()
			//run movement choices
				if(currentChar.status_.canMove() == true) {
					if(currentChar.AI_.isAI == true) {
						//AI movement
					}else {
						
					}
				}
			//get new choices
				if(currentChar.status_.isHasBeenTargeted() == true) {
					Ability defensive = currentChar.abilities_.chooseDefensive();
					al.add(defensive);					
				}
				Ability offensive = currentChar.abilities_.chooseOffensive();
				al.add(offensive);	
				activeAbilitiesByChar.put(currentChar.id, al );
				
			}//loop to next character
			
			//begin calculating results of first round of battling
			
			
		}//end battle loop
		
		
		return result;

	}//end beginSquadBattle
	
	
	
	
	public void setCharIds() {
		int i = 1;
		for(Squad s : ss) {
			for(Character c : s.members) {
				c.id = i;
				i++;
			}
		}
	}//setCharIds
	
	public double getCharSpeedScore(Character c) {
		if(c.status_.canMove() == false) {
			return 0.0;
		}
		double cSpeed = 0.0;
		double cLvl = 0.0;
		double cRandom = Math.random();
		double cRandom2 = Math.random();
		double score = 0.0;
		cSpeed = c.stats_.speed;
		cSpeed = cSpeed * 10;   //give speed a weight of 10
		cLvl = c.lvl;
		cLvl = cLvl * 2;		//give lvl a weight of 2
		cRandom = (cRandom * cSpeed/2) + cSpeed/2;  //50% speed + 50% rand
		cRandom2 =  (cRandom * cLvl/2) + cLvl/2;
		score = cRandom + cRandom2;
		return score;
	}
	
	public static HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm) 
	{ 
	        // Create a list from elements of HashMap 
	        List<Map.Entry<Integer, Double> > list = 
	               new LinkedList<Map.Entry<Integer, Double> >(hm.entrySet()); 
	  
	        // Sort the list 
	        Collections.sort(list, new Comparator<Map.Entry<Integer, Double> >() { 
	            public int compare(Map.Entry<Integer, Double> o1,  
	                               Map.Entry<Integer, Double> o2) 
	            { 
	                return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
	          
	        // put data from sorted list to hashmap  
	        HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>(); 
	        for (Map.Entry<Integer, Double> aa : list) { 
	            temp.put(aa.getKey(), aa.getValue()); 
	        } 
	        return temp; 
	} 
	 
	 
	
	public ArrayList<Integer> characterIdOrder() {
		 
		ArrayList<Integer> atkOrder = new ArrayList<Integer>();
		HashMap<Integer,Double> idAndScore = new HashMap<Integer,Double>();
		int id;
		double score;
		for(Squad s : ss) {
			for(Character c : s.members) {
				id = c.id;
				score = getCharSpeedScore(c);
				idAndScore.put(id,score);
			}
		}
		idAndScore = sortByValue(idAndScore);
		atkOrder = new ArrayList<>( idAndScore.keySet() );
		return atkOrder;
	}
	
	
	
	public boolean isAmbush(Squad s) {
		boolean allHidden = true;
		for(Character c : s.members) {
			if(c.position_.isHidden() != true) {
				allHidden =  false;
			}
		}
		return allHidden;
	}
	
	
	 
	
	 
	
	
}//end Battle
