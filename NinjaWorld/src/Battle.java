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
	private ArrayList<Ability> activeAbilitiesCurrentChar;
	private int numActions;
	private int currentAction;
	private int currentDefAction;
	private Character targetedChar;
	private int currentTargetAreaID;
	private Area currentTargetArea;
	private Ability currentAbility;
	private boolean canMove;				//based on char.status_
	private boolean canTransition;			//based on char.status_
	private boolean canDefend;				//based on char.status_
	private boolean canOffense;				//based on char.status_
	
	
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
		Result result = new Result();
		this.map = map;		
		this.s1 = s1;
		this.s2 = s2;
		this.s1.setSquadId(1);
		this.s2.setSquadId(2);
		this.ss = new ArrayList<Squad>();
		this.activeAbilitiesByChar = new HashMap<Integer, ArrayList<Ability>>();
		this.ss.add(s1);
		this.ss.add(s2);
		this.turnCharacterId = 0;
		this.turn = 0;
		setCharIds();  //give each char a unique id for this battle
		setMapForAll(); //insert battle map into each character
		
		//printBattleIntroMessage
		s.out("=========== BATTLE ===========");
		s.out("Squad " + s1.getSquadName() + " vs. Squad " + s2.getSquadName());
		s.out("=========== BEGIN! ===========");
		//battle loop
		battleContinues = true;
		while(battleContinues==true) {			
			this.characterIdOrder  = characterIdOrder();   //recalculate the order of attacks randomly each turn
			
			//loop characters for one round
			for(int i=characterIdOrder.size()-1; i>=0; i--) {
				//begin char turn
				setCurrentCharByIndex(i);  //find and set currentChar
				if(currentChar.getAction_().isUsedDefensive() == true){
					this.numActions = 1;
					currentChar.getAction_().setNumActions(1);
				}else{
					this.numActions = 2;
					currentChar.getAction_().setNumActions(2);
				}
				currentChar.getAction_().refresh();				
				activeAbilitiesCurrentChar = getActiveAbilityListByCurrentChar();  //get active abilities
				//run preeffects of prior abilities
				//run movement choices
				canMove = currentChar.getStatus_().canMove();
				canTransition = currentChar.getStatus_().canMove();
				canDefend = false;
				canOffense = currentChar.getStatus_().canMove();


				//s.clear();  //no function
				//s.pause();  //excessive
			    this.map.printBattleMap();
				s.out("====================================");
				s.out("**"+ currentChar.getName() + "'s turn begins. **");
				s.out("====================================");

				while(currentChar.getAction_().getNumActions() > 0) {
					if(currentChar.getAI_().isAI() == true) {						
						currentAction = validatedRandomAction();		 //AI action				
					}else {						
						currentAction = getBattleAction(currentChar); 				//human action 1 - 4
					}
					
					if(processAction(true) == true) {						//process
						currentChar.getAction_().decrementNumActions();
					}else{
						s.out(this.getActionName(currentAction) + " action failed.");
					}//if processAction fails, infinite loop
				}//finish 2 actions -->
			}//loop to next character
			
			//loop on abilities that haven't had a chance to be defended
			
			
			//begin calculating results of first round of battling
			processAbilitiesByChar();  //processes defense as abilities trigger

		}//end battle loop


		
		return result;

	}//end beginSquadBattle
	

	public boolean processAbilitiesByChar(){
		boolean abilityNotCanceledCompletely;
		boolean correctCharFound = false;
		for(int i=characterIdOrder.size()-1; i>=0; i--) {
			setCurrentCharByIndex(i);
			try {
				//https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
//				Iterator it = activeAbilitiesByChar.entrySet().iterator();
//				while (it.hasNext() && correctCharFound==true) {
//					ArrayList<Ability> list = (ArrayList<Ability>)it.next();
//					list.indexOf(activeAbilitiesByChar);
//					for (Ability a : list ) {    //gets all abilities for 1 char and processes each one by one
//						currentAbility = a;
//						abilityNotCanceledCompletely = processAbility(a);
//						if(abilityNotCanceledCompletely == false || a.isMultiTurn()==false){
//							//remove ability from activeAbilitiesByChar
//							activeAbilitiesByChar.get(i).remove(a);
//						}
//					}
//				}

				for(ArrayList<Ability> list : activeAbilitiesByChar){
					for (Ability a : list ) {    //gets all abilities for 1 char and processes each one by one
						currentAbility = a;
						abilityNotCanceledCompletely = processAbility(a);
						if(abilityNotCanceledCompletely == false || a.isMultiTurn()==false){
							//remove ability from activeAbilitiesByChar
							activeAbilitiesByChar.get(i).remove(a);
						}
					}
				}

			}catch(Exception e){
				//do nothing
				e.printStackTrace();
			}

		}
		return true;
	}//processAbilitiesByChar



	public boolean processAbility(Ability a){
		s.pause();
		s.out( currentChar.getName() + " does " + a.getName()	);
		//look to see if ability was countered
		//if(counterSuccess == true) { return false; }

		//processes currentAbility
		for(Object o : a.getTargetObjs() ){
			if(o instanceof Character){
				processAbilityAgainstChar((Character)o) ;
			}else if(o instanceof AreaEnvi){
				processAbilityAgainstAreaEnvi((AreaEnvi)o) ;
			}else if(o instanceof Ability){
				processAbilityAgainstAbility((Ability)a);
			}
		}
		return true;
	}//processAbility




	public boolean processDefChoiceYN(Character tc){
		boolean chooseDef = false;
		if(tc.getAI_().isAI() == true){
			int random = s.getRandomIntBetween(1,10);
			if(random >= 4){  //60% chance to defend
				//processAIdefense
				s.out(tc.getName() + " chooses to defend with an action.");
				chooseDef = true;
			}
		}else{
			s.out(tc.getName() + " - would you like to use an action to defend? (-1 action from next round)");
			s.out("1. Action to defend");
			s.out("2. Use durability to defend");
			int answer = s.getIntBetween(1,2);
			if(answer == 1){
				chooseDef = true;
			}
		}
		return chooseDef;
	}


	public boolean runDefenseChoices(Character tc){
		boolean abilityNotCanceledCompletely = true;
		int numActions = 1;
		tc.getAction_().setNumActions(1);
		while(numActions > 0) {
			if(tc.getAI_().isAI() == true) {
				currentDefAction = validatedRandomAction();		 //AI action
			}else {
				currentDefAction = getBattleAction(tc); 				//human action 1 - 4
			}

			if(processAction(false) == true) {						//processAction success
				numActions--;
			}else{
				s.out(this.getActionName(currentDefAction) + " action failed.");
			}//if processAction fails, infinite loop
		}//finish 2 actions -->

		return abilityNotCanceledCompletely;
	}

	public boolean processOneCharactersDefense(Character tc){
		boolean abilityNotCanceledCompletely = true;
		tc.getStatus_().setHasBeenTargeted(true);
		canDefend = tc.getStatus_().canMove();
		targetedChar = tc;
		s.out(tc.getName() + " has been targeted by " + currentAbility.getName());
		if(tc.getStatus_().canMove() && tc.getAction_().isUsedDefensive() == false ){  //if can defend
			boolean yesDefend = processDefChoiceYN(tc);
			if(yesDefend){
				abilityNotCanceledCompletely = runDefenseChoices(tc);
			}


		}
		return abilityNotCanceledCompletely;
		//if atk/defense successful, return true;
		// if defense successful and ability destroyed, return false

	}//processOneCharactersDefense



	public boolean processAbilityAgainstChar(Character tc) {
		//defense option
		boolean abilityNotCanceledCompletely = processOneCharactersDefense(tc);

		if (abilityNotCanceledCompletely) {

			//Stats cs = currentChar.getStats_();
			double cbDamage = currentAbility.getBasicDamage();
			double ccDamage = currentAbility.getChakraDamage();

			s.out("Base damage        = " + (cbDamage + ccDamage) + " (physical dmg = " + cbDamage + " / chakra dmg = " + ccDamage + ")");

			scaleAbility();
			double cbDamageScaled = currentAbility.getBasicDamage();
			double ccDamageScaled = currentAbility.getChakraDamage();
			double totalBonus = (cbDamageScaled + ccDamageScaled) - (cbDamage + ccDamage);
			s.out("Bonus Damage       = " + totalBonus);
			//s.out("_______________________________________");
			s.pause();
			s.out(currentChar.getName() + "'s " + currentAbility.getName() + " strikes for " + (cbDamageScaled + ccDamageScaled));

			//cancel damage with defense
			Stats ts = tc.getStats_();

			double tbDef = ts.getBasicDef();
			double tcDef = ts.getChakraDef();
			tbDef = (double) s.getRandomIntBetween(0, (int) tbDef);
			tcDef = (double) s.getRandomIntBetween(0, (int) tcDef);
			s.out(tc.getName() + " blocks " + tbDef + " physical dmg and " + tcDef + " chakra dmg");
			//s.out("_______________________________________");
			s.pause();
			cbDamageScaled = (cbDamageScaled - tbDef < 0) ? 0 : (cbDamageScaled - tbDef);
			ccDamageScaled = (ccDamageScaled - tcDef < 0) ? 0 : (ccDamageScaled - tcDef);
			double total = cbDamageScaled + ccDamageScaled;
			s.out("Total Damage = " + total);
			double tchp = ts.getCurrentHP();
			ts.setCurrentHP(tchp - total);
			s.pause();
			return true;
		} else{
			s.out(currentAbility.getName() + " was negated.");
			return false;
		}
	}//processAbilityAgainstChar

	public boolean processAbilityAgainstAreaEnvi(AreaEnvi tae){

		return true;

	}

	public boolean processAbilityAgainstAbility(Ability ta){

		return true;

	}

	public void scaleAbility(){

		String[] t = {"maxHP","maxChakra","hpRegen","chakraRegen","basicAtk","chakraAtk","basicDef","chakraDef","speed","brains","sensing"};
		for(String tt : t){
			scaleAbilityOnTrait(tt);
		}
	}//scaleAbility



	public void scaleAbilityOnTrait(String trait){
		Double scale = currentAbility.getBdScalingBonus().get(trait);

		if( scale != null){
			double t = currentChar.getStats_().getStatFromName(trait);
			double bonus = (double)s.getRandomIntBetween((int)t/2, (int)t*2);
			bonus = bonus * scale;
			s.out("+ " + bonus + " physical damage, boosted by "  + trait + " @ " + (int)(scale*100) + "%" );
			double bd = currentAbility.getBasicDamage();
			currentAbility.setBasicDamage(bd + bonus);
		}

		Double scale2 = currentAbility.getCdScalingBonus().get(trait);

		if( scale2 != null){
			double t = currentChar.getStats_().getStatFromName(trait);
			double bonus = (double)s.getRandomIntBetween((int)t/2, (int)t*2);
			bonus = bonus * scale2;
			s.out("+ " + bonus + " chakra damage, boosted by "  + trait + " @ " + (int)(scale2*100) + "%" );
			double cd = currentAbility.getChakraDamage();
			currentAbility.setBasicDamage(cd + bonus);
		}
	}//scaleAbilityOnTrait

	public boolean processAction(boolean isOffense) {
		//prevalidated
		if(isOffense){
			return processValidatedAction();
		}else{
			return processValidatedDefAction(targetedChar);
		}


	}//processAction

	public boolean processValidatedAction() {
		boolean actionSuccess = false;
		//rest
		if(currentAction == 0) {
			//rest
			//gain chakra and HP
			s.out(currentChar.getName() + " rests.");
			processRest();
			actionSuccess = true;
		}
		//transition
		if(currentAction == 1) {
			//change this part ---> make movements a request , not an immediate action
			//create a moveNorth ability, if ability name = north, movement ==true, run movement
			if(currentChar.getAI_().isAI() ) {
				actionSuccess = currentChar.doTransitionAI();   //change later
			}else {
				actionSuccess = currentChar.doTransitionHuman();
			}
			if(actionSuccess){
				currentChar.getAction_().setUsedEnvi(true);
			}
		}
		//move
		if(currentAction == 2) {			 
			String com = currentChar.getCommands_().getCommand();
			//change this part ---> make movements a request , not an immediate action
			//create a moveNorth ability, if ability name = north, movement ==true, run movement
			actionSuccess = currentChar.getCommands_().processBattleMapCommand(com);
			if(actionSuccess) {
				this.getMap().printBattleMap();
				s.out("Moved " + com);
				s.pause();
				currentChar.getAction_().setUsedMove(true);
			}			
		}
		//offensive
		if(currentAction == 3) {
			Ability offensive = currentChar.getAbilities_().chooseOffensive();
			Ability offensiveCopy = (Ability)s.deepClone(offensive) ;
			if(offensive != null) {
				if(currentChar.canDo(offensive) ){
					currentAbility = offensiveCopy;
					actionSuccess = prepareAbility();
					if(actionSuccess){
						activeAbilitiesCurrentChar.add(offensiveCopy);
						activeAbilitiesByChar.put(currentChar.getId(), activeAbilitiesCurrentChar );
						currentChar.getAction_().setUsedOffensive(true);
					}
				}
			}
		}
		//defensive
//		if(currentAction == 4) {
//			Ability defensive = currentChar.getAbilities_().chooseDefensive();
//			Ability defensiveCopy = (Ability)s.deepClone(defensive) ;
//
//			if(defensive != null) {
//				if(currentChar.canDo(defensive) ){
//					currentAbility = defensiveCopy;
//					actionSuccess = prepareAbility();
//					if(actionSuccess){
//						activeAbilitiesCurrentChar.add(defensiveCopy);
//						activeAbilitiesByChar.put(currentChar.getId(), activeAbilitiesCurrentChar );
//						currentChar.getAction_().setUsedDefensive(true);
//					}
//				}
//			}
//		}
		if(currentAction == 5) {
			s.out("Sorry, " + currentChar.getName() + "has no items.");
		}
		return actionSuccess;
	}//processValidatedAction



//DEFENSE

//DEFENSE

	public boolean processValidatedDefAction(Character tc) {
		boolean actionSuccess = false;
		//rest
		if(currentDefAction == 0) {
			//rest
			//gain chakra and HP
			s.out(tc.getName() + " rests.");
			processRest();
			actionSuccess = true;
		}
		//transition
		if(currentDefAction == 1) {
			//change this part ---> make movements a request , not an immediate action
			//create a moveNorth ability, if ability name = north, movement ==true, run movement
			if(tc.getAI_().isAI() ) {
				actionSuccess = tc.doTransitionAI();   //change later
			}else {
				actionSuccess = tc.doTransitionHuman();
			}
			if(actionSuccess){
				tc.getAction_().setUsedEnvi(true);
			}
		}
		//move
		if(currentDefAction == 2) {
			String com = tc.getCommands_().getCommand();
			//change this part ---> make movements a request , not an immediate action
			//create a moveNorth ability, if ability name = north, movement ==true, run movement
			actionSuccess = tc.getCommands_().processBattleMapCommand(com);
			if(actionSuccess) {
				this.getMap().printBattleMap();
				s.out("Moved " + com);
				s.pause();
				tc.getAction_().setUsedMove(true);
			}
		}
		//offensive
//		if(currentDefAction == 3) {
//			Ability offensive = tc.getAbilities_().chooseOffensive();
//			Ability offensiveCopy = (Ability)s.deepClone(offensive) ;
//			if(offensive != null) {
//				if(tc.canDo(offensive) ){
//					currentAbility = offensiveCopy;
//					actionSuccess = prepareAbility();
//					if(actionSuccess){
//						activeAbilitiesCurrentChar.add(offensiveCopy);
//						activeAbilitiesByChar.put(tc.getId(), activeAbilitiesCurrentChar );
//						tc.getAction_().setUsedOffensive(true);
//					}
//				}
//			}
//		}
		//defensive
		if(currentAction == 4) {
			Ability defensive = tc.getAbilities_().chooseDefensive();
			Ability defensiveCopy = (Ability)s.deepClone(defensive) ;

			if(defensive != null) {
				if(tc.canDo(defensive) ){
					currentAbility = defensiveCopy;
					actionSuccess = prepareAbility();
					if(actionSuccess){
						activeAbilitiesCurrentChar.add(defensiveCopy);
						activeAbilitiesByChar.put(tc.getId(), activeAbilitiesCurrentChar );
						tc.getAction_().setUsedDefensive(true);
					}
				}
			}
		}
		if(currentAction == 5) {
			s.out("Sorry, " + tc.getName() + " has no items.");
		}
		return actionSuccess;
	}//processValidatedDefAction







	public boolean prepareAbility() {
		boolean abilityReady = false;
		s.out("Activating ability \"" + currentAbility.getName() + "\"");
		//calc cost + boost cost
		double cost = currentAbility.getChakraCost();
		if(currentAbility.isBoostable()) {
			s.out("Would you like to boost this ability for " + currentAbility.getBoostChakraCost() + " chakra?");
			s.out("1. Yes");
			s.out("2. No");
			int a = s.getIntBetween(1, 2);
			if(a == 1) {
				currentAbility.setBoosted(true);
				cost += currentAbility.getBoostChakraCost();
			}else {
				currentAbility.setBoosted(false);
				cost = currentAbility.getChakraCost();
			}
		}//boost
		
		//set targets
		double nt = currentAbility.getNumTargets();
		if( nt > 0){
			int input = 0;
			int max = this.getMap().getMaxAreaID();
			while (nt > 0) {
				this.map.printBattleMap();
			    s.out("");
			    s.out(currentAbility.getName() + " has " + nt + " target(s)");
				s.out("");
				s.out("  Target Area ID (or 0: Cancel)");
				s.out("=====================");
				s.print(":");
				input = s.getIntBetween(0, max);				
				if(input == 0) {
					return false;
				}
				
				currentTargetAreaID = input;
				currentTargetArea = this.map.getAreaFromID(input);
				
				//validate range of ability
				if(targetAreaWithinRange() == false) {
					s.out("Sorry, Area " + input + " is out of range.");
					s.out(currentAbility.getName() + " range : " + (int)currentAbility.getRange() );
					s.out("");
					return false;
				}
				
				abilityReady = getAndSetTargetForCurrentTargetArea();
				if(abilityReady == true) {
					nt--;
				}
			}
		}
		//abilityReady == success --->subtract chakra
		double cc = currentChar.getStats_().getCurrentChakra();
		currentChar.getStats_().setCurrentChakra(cc - cost);
		
		return abilityReady;
		//if(ab.includesDash){processDash}
	}//prepareAbility
	
	
	public boolean getAndSetTargetForCurrentTargetArea() {
		//Area targetA = this.map.getAreaFromID(id);
		int startingId = 1;
		HashMap<Integer,Character> potentialChar = currentTargetArea.outputValidTargetChar(startingId);
		startingId = potentialChar.size() + 1;
		HashMap<Integer,String> potentialEnvi = currentTargetArea.outputValidTargetEnvi(startingId);
		HashMap<Integer,String> list = new HashMap<Integer,String>();
		
		Iterator it = potentialChar.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        list.put( ((int)pair.getKey()), ((Character)pair.getValue()).getName() ) ;
	     //   it.remove(); // avoids a ConcurrentModificationException
	    }
	    list.putAll(potentialEnvi);
	    String t = "";
	    s.out("  Targets on Area " + currentTargetArea.getId());
	    s.out("=======================");
	    s.out("0. Cancel");
	    for(int i = 1; i<= list.size(); i++) {
	    	t = list.get(i);
	    	s.out(i + ": " + t);
	    }	    
	    s.out("=======================");
		s.print(":");
		int choice = -1;
		choice = s.getIntBetween(0,list.size());
		if(choice == 0) {
			return false;
		}else if( choice <= potentialChar.size() ) {
			currentAbility.getTargetObjs().add(potentialChar.get(choice)  );
		}else {
			String envi = potentialEnvi.get(choice);
			AreaEnvi ae = new AreaEnvi(currentTargetArea, envi);
			currentAbility.getTargetObjs().add(ae);
		} 
		return true;
	}//getAndSetTargetForCurrentTargetArea
	
	
	public boolean targetAreaWithinRange() {
		boolean valid = false;
		double r = currentAbility.getRange();
		Area charA = currentChar.getMap_().getAreaMC();
		double cx = charA.getX();
		double cy = charA.getY();
		double tx = currentTargetArea.getX();
		double ty = currentTargetArea.getY();
		if( (Math.abs(tx - cx) <= r) && (Math.abs(ty - cy) <= r)  ){
			valid = true;
		}
		return valid;
	}
	
	
	public void setMapForAll() {
		for(Squad s : this.ss) {
			for(Character c: s.getMembers()) {
				c.setMap_(this.map);
			}
		}
		
	}//setMapForAll
	
	
	public int validatedRandomAction() { 
		int v = -1;
		while(validateAction(v) == false) {
			v = s.getRandomIntBetween(0, 4); 
		}
		return v;
	}
	
	
	
	public boolean validateAction(int v) {
		switch(v) {		
		case 0:
			return true;
		case 1:
			if(canTransition && currentChar.getAction_().isUsedEnvi() == false) {
				return true;
			}
			break;
		case 2:
			if(canMove  && currentChar.getAction_().isUsedMove() == false) {
				return true;
			}
			break;
		case 3:
			if(canOffense && currentChar.getAction_().isUsedOffensive() == false) {
				return true;
			}
			break;
		case 4:
			if(canDefend && currentChar.getAction_().isUsedDefensive() == false) {
				return true;
			}
			break;
		case 5:
			if(currentChar.getStatus_().isOneHandFree() && currentChar.getAction_().isUsedItem() == false){
				return true;
			}
			break;
		}//END SWITCH

		return false;
	}//end validateAction
	
	
	
	public int getBattleAction(Character c) {
		int a = -1;
		Action a_ = c.getAction_();
		while(validateAction(a) == false) {											//validates choice after entry
			s.out("Choose an action. (" + c.getAction_().getNumActions() + " actions)" );
			s.out("0. Rest");
			if(canTransition && a_.isUsedEnvi() == false){
				s.out("1. Utilize environment");
			}
			if(canMove && a_.isUsedMove() == false) {
				s.out("2. Move to a new area");
			}
			if(canOffense && a_.isUsedOffensive() == false) {
				s.out("3. Offensive ability");
			}
			if(canDefend && a_.isUsedDefensive() == false){
				s.out("4. Defensive ability");
			}
			if(currentChar.getStatus_().isOneHandFree()  && a_.isUsedItem() == false){
				s.out("5. Use item");
			}
			a = s.getIntBetween(0, 5);
		} 
		return a;
	}//end getBattleAction
	
	
	
	public ArrayList<Ability> getActiveAbilityListByCurrentChar(){
		ArrayList<Ability> al = new ArrayList<Ability>();
		try {
			if(activeAbilitiesByChar.get(currentChar.getId()) != null) {
				al = activeAbilitiesByChar.get(currentChar.getId());
			}
		}catch(Exception e) {
			
		}
		return al;
	}



	//public void setCurrentCharByIndex(int i) throws Exception {

	public void setCurrentCharByIndex(int i) {
		currentChar = null;
		for(Squad s : ss) {
			currentChar = s.getCharById(characterIdOrder.get(i));
			if (!(currentChar==null || currentChar.equals(null) )) {
				break;
			}
		}
		if(currentChar == null) {
			//throw new Exception("No character found for id = " + i);
			s.out("error, character not found");
		}
	}//end setCurrentCharByIndex
	



	public void setCharIds() {
		int i = 1;
		for(Squad squad : ss) {
			for(Character c : squad.getMembers()) {
				c.setId(i);
				i++;
			}
		}
	}//setCharIds
	
	public double getCharSpeedScore(Character c) {
		if(c.getStatus_().canMove() == false) {
			return 0.0;
		}
		double cSpeed = 0.0;
		double cLvl = 0.0;
		double cRandom = Math.random();
		double cRandom2 = Math.random();
		double score = 0.0;
		cSpeed = c.getStats_().getSpeed();
		cSpeed = cSpeed * 10;   //give speed a weight of 10
		cLvl = c.getLvl();
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
			//s.out("check squad");
			for(Character c : sq.getMembers()) {
				//s.out("check character");
				id = c.getId();
				score = getCharSpeedScore(c);
				idAndScore.put(id,score);
			}
		}
		//s.out("speed scores");
		//s.out(idAndScore.toString());
		idAndScore = sortByValue(idAndScore);
		//s.out("speed scores ordered");
		//s.out(idAndScore.toString());
		atkOrder = new ArrayList<>( idAndScore.keySet() );
		//s.out(atkOrder.toString());
		return atkOrder;
	}
	
	
	
//	public boolean isAmbush(Squad sq) {
//		boolean allHidden = true;
//		for(Character c : sq.getMembers()) {
//			if(c.getPosition_().isHidden() != true) {
//				allHidden =  false;
//			}
//		}
//		return allHidden;
//	}
	
	public void processRest() {
		double c = currentChar.getStats_().getChakraRegen();
		double h = currentChar.getStats_().getHpRegen();
		//apply random boost to c & h? 
		double currentC = currentChar.getStats_().getCurrentChakra();
		double currentH = currentChar.getStats_().getCurrentHP();
		double maxC = currentChar.getStats_().getMaxChakra();
		double maxH = currentChar.getStats_().getMaxHP();
		//chakra regen
		if(currentC + c > maxC) {
			currentChar.getStats_().setCurrentChakra(maxC);
			s.out("+" + (int)(maxC-currentC) + " chakra   (at max chakra)");
		}else {
			currentChar.getStats_().setCurrentChakra(currentC + c);
			s.out("+" + (int)(c) + " chakra");
		}
		//HP regen
		if(currentH + h > maxH) {
			currentChar.getStats_().setCurrentHP(maxH);
			s.out("+" + (int)(maxH-currentH) + " HP   (at max HP)");
		}else {
			currentChar.getStats_().setCurrentHP(currentH + h);
			s.out("+" + (int)(h) + " HP");
		}
		s.out("");
		s.pause();

	}//processRest








	public String getActionName(int num){
		String s = "";
		switch (num){
			case 0:
				return "rest";
			case 1:
				return "transition";
			case 2:
				return "move";
			case 3:
				return "offensive";
			case 4:
				return "defensive";
			case 5:
				return "item";
		}
		return s;
	}





	//=============================================================================
	//=============================================================================
//=============================================================================


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
	
	public ArrayList<Ability> getActiveAbilitiesCurrentChar() {
		return activeAbilitiesCurrentChar;
	}

	public void setActiveAbilitiesCurrentChar(ArrayList<Ability> activeAbilitiesCurrentChar) {
		this.activeAbilitiesCurrentChar = activeAbilitiesCurrentChar;
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}
}//end Battle
