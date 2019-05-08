import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.lang.*;

public class Battle {

	private System1 s = new System1();
	private Squad s1;
	private Squad s2;
	private ArrayList<Squad> ss;
	private int turnCharacterId;
	private int turn;
	private ArrayList<Integer> characterIdOrder;
	private Map1 map;
	private boolean battleContinues;
	private Character currentChar;
	private HashMap<Integer, ArrayList<Ability>> activeAbilitiesByChar; 
	private int numActions;
	private int currentAction;
	private boolean canMove;
	private boolean canTransition;
	private boolean canDefend;
	private boolean canOffense;
	
	
	public Battle(){
		
	}
	
	public Battle(Map1 map, Squad s1, Squad s2){
		this.map = map;
		this.s1 = s1;
		this.s2 = s2;
		this.s1.setSquadId(1);
		this.s2.setSquadId(2);
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
	public Result beginSquadBattle(Map1 map, Squad s1, Squad s2) throws Exception{
		//setup
		boolean debug = false;
		Result result = new Result();
		this.map = map;
		this.s1 = s1;
		this.s2 = s2;
		this.s1.setSquadId(1);
		this.s2.setSquadId(2);
		this.ss = new ArrayList<Squad>();
		this.activeAbilitiesByChar = new HashMap<Integer, ArrayList<Ability>>();
		ss.add(s1);
		ss.add(s2);
		this.turnCharacterId = 0;
		this.turn = 0;
		setCharIds();  //give each char a unique id for this battle
		
		//printBattleIntroMessage
		s.out("===== BATTLE =====");
		s.out("Squad " + s1.getSquadName() + " vs. Squad " + s2.getSquadName());
		s.out("===== BEGIN! =====");
		//battle loop
		battleContinues = true;
		while(battleContinues==true) {
			if(debug) s.out("Next round of battle begins!");
			//recalculate the order of attacks randomly each turn
			this.characterIdOrder  = characterIdOrder();
			if(debug) s.out("characterIdOrder = "  + characterIdOrder);
			
			//loop characters for one round
			for(int i=characterIdOrder.size()-1; i>=0; i--) {
			//begin char turn
				//find char
				if(debug) s.out("i = " + i);
				setCurrentCharByIndex(i);				
				//get active abilities
				ArrayList<Ability> al = getActiveAbilityListByCurrentChar();
				//run preeffects of prior abilities
				//()
				//run movement choices
				canMove = currentChar.status_.canMove();
				canTransition = currentChar.status_.canMove();
				canDefend = currentChar.status_.isHasBeenTargeted();
				canOffense = true;
				if(debug) s.out(currentChar.name + " canMove = " + canMove + " canTransition = " + canTransition + " canDef= " + canDefend + " canOffense = " + canOffense);
				
				numActions = 2;
				if(debug) s.out("numActions = " + numActions);
				
				s.getInt();
				s.clear();//no function
			    map.printBattleMap();
			    
				while(numActions > 0) {
					if(debug) s.out("numActions = " +numActions);
					if(currentChar.AI_.isAI == true) {
						//AI action
						currentAction = validatedRandomAction();						
					}else {
						//human action
						currentAction = getBattleAction();
					}
					numActions--;
				}
				
				 
			//get new choices
				if(currentAction == 0) {
					Ability defensive = currentChar.abilities_.chooseDefensive();
					al.add(defensive);	
					activeAbilitiesByChar.put(currentChar.id, al );
				}
				if(currentAction == 3) {
					Ability offensive = currentChar.abilities_.chooseOffensive();
					al.add(offensive);
					activeAbilitiesByChar.put(currentChar.id, al );
				} 
				if(currentAction == 1) {
					s.out("lets transition");
				}
				if(currentAction == 2) {
					s.out("lets move");
				}
				
			}//loop to next character
			
			//begin calculating results of first round of battling
			
			
		}//end battle loop
		
		
		return result;

	}//end beginSquadBattle
	
	
	public int validatedRandomAction() { 
		int v = -1;
		while(validateAction(v) == false) {
			v = s.getRandomIntBetween(1, 4); 
		}
		return v;
	}
	
	public boolean validateAction(int v) {
		switch(v) {		
		case 1:
			if(canTransition) {
				return true;
			}
			break;
		case 2:
			if(canMove) {
				return true;
			}
			break;
		case 3:
			if(canOffense) {
				return true;
			}
			break;
		case 4:
			if(canDefend) {
				return true;
			}
			break;
		}
		return false;
	}//end validateAction
	
	
	
	public int getBattleAction() {
		int a = -1;
		while(validateAction(a) == false) {
			s.out("Choose an action. (" + numActions + " actions)" );
			
			if(canTransition){
				s.out("1. Utilize environment");
			}
			if(canMove) {
				s.out("2. Move to a new area");
			}
			if(canOffense) {
				s.out("3. Offensive ability");
			}
			if(canDefend){
				s.out("4. Defensive ability");
			}
			a = s.getIntBetween(1, 4);
		} 
		return a;
	}//end getBattleAction
	
	
	
	public ArrayList<Ability> getActiveAbilityListByCurrentChar(){
		ArrayList<Ability> al = new ArrayList<Ability>();
		try {
			if(activeAbilitiesByChar.get(currentChar.id) != null) {
				al = activeAbilitiesByChar.get(currentChar.id);
			}
		}catch(Exception e) {
			
		}
		return al;
	}
	
	
	
	
	public void setCurrentCharByIndex(int i) throws Exception {
		currentChar = null;
		for(Squad s : ss) {
			currentChar = s.getCharById(characterIdOrder.get(i));
			if (!(currentChar==null || currentChar.equals(null) )) {
				break;
			}
		}
		if(currentChar == null) {
			throw new Exception("No character found for id = " + i);
		}
	}//end setCurrentCharByIndex
	
	
	public void setCharIds() {
		int i = 1;
		for(Squad squad : ss) {
			for(Character c : squad.getMembers()) {
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
		cSpeed = c.stats_.getSpeed();
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
		for(Squad sq : ss) {
			s.out("check squad");
			for(Character c : sq.getMembers()) {
				s.out("check character");
				id = c.id;
				score = getCharSpeedScore(c);
				idAndScore.put(id,score);
			}
		}
		s.out("speed scores");
		s.out(idAndScore.toString());
		idAndScore = sortByValue(idAndScore);
		s.out("speed scores ordered");
		s.out(idAndScore.toString());
		atkOrder = new ArrayList<>( idAndScore.keySet() );
		s.out(atkOrder.toString());
		return atkOrder;
	}
	
	
	
	public boolean isAmbush(Squad sq) {
		boolean allHidden = true;
		for(Character c : sq.getMembers()) {
			if(c.position_.isHidden() != true) {
				allHidden =  false;
			}
		}
		return allHidden;
	}
	
	
	 
	

	public Squad getS1() {
		return s1;
	}

	public void setS1(Squad s1) {
		this.s1 = s1;
	}

	public Squad getS2() {
		return s2;
	}

	public void setS2(Squad s2) {
		this.s2 = s2;
	}

	public ArrayList<Squad> getSs() {
		return ss;
	}

	public void setSs(ArrayList<Squad> ss) {
		this.ss = ss;
	}

	public int getTurnCharacterId() {
		return turnCharacterId;
	}

	public void setTurnCharacterId(int turnCharacterId) {
		this.turnCharacterId = turnCharacterId;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public ArrayList<Integer> getCharacterIdOrder() {
		return characterIdOrder;
	}

	public void setCharacterIdOrder(ArrayList<Integer> characterIdOrder) {
		this.characterIdOrder = characterIdOrder;
	}

	public Map1 getMap() {
		return map;
	}

	public void setMap(Map1 map) {
		this.map = map;
	}

	public boolean isBattleContinues() {
		return battleContinues;
	}

	public void setBattleContinues(boolean battleContinues) {
		this.battleContinues = battleContinues;
	}

	public Character getCurrentChar() {
		return currentChar;
	}

	public void setCurrentChar(Character currentChar) {
		this.currentChar = currentChar;
	}

	public HashMap<Integer, ArrayList<Ability>> getActiveAbilitiesByChar() {
		return activeAbilitiesByChar;
	}

	public void setActiveAbilitiesByChar(HashMap<Integer, ArrayList<Ability>> activeAbilitiesByChar) {
		this.activeAbilitiesByChar = activeAbilitiesByChar;
	}

	public int getNumActions() {
		return numActions;
	}

	public void setNumActions(int numActions) {
		this.numActions = numActions;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public boolean isCanTransition() {
		return canTransition;
	}

	public void setCanTransition(boolean canTransition) {
		this.canTransition = canTransition;
	}

	public boolean isCanDefend() {
		return canDefend;
	}

	public void setCanDefend(boolean canDefend) {
		this.canDefend = canDefend;
	}

	public boolean isCanOffense() {
		return canOffense;
	}

	public void setCanOffense(boolean canOffense) {
		this.canOffense = canOffense;
	}
	
	
}//end Battle
