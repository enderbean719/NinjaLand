import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.lang.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.Serializable;


public class Battle implements Serializable{

	private System1 s = new System1();
	private Character mc;
	private Squad s1;
	private Squad s2;
	private ArrayList<Squad> ss;
	private int turnCharacterId;
	private int turn;
	private ArrayList<Integer> characterIdOrder;
	private Map1 map;
	private boolean battleContinues;
	private Character currentChar;
	private Character currentDefendingChar;
	//private HashMap<Integer, ArrayList<Ability>> activeAbilitiesByChar;
	private ConcurrentHashMap<Integer, ArrayList<Ability>> activeAbilitiesByChar;

	private ArrayList<Ability> activeAbilitiesCurrentChar;
	private int numActions;
	private int currentAction;
	private int currentDefAction;
	private Character currentDefChar;
	private int currentTargetAreaID;
	private Area currentTargetArea;
	private Ability currentAbility;
	private boolean canMove;				//based on char.status_
	private boolean canTransition;			//based on char.status_
	private boolean canDefend;				//based on char.status_
	private boolean canOffense;				//based on char.status_
	private int a_id = 0;	 //auto incrementing id assigned to each ability object by creation order
	
	public Battle(){
		
	}

	public Battle(Character mc	){
		this.mc = mc;
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
	

	 
	
	public Result beginSquadAmbush() {
		Result result = new Result();

		return result;
	}


	public void setMapForAllChar(){
		for(Squad squad : this.ss) {
			for (Character c : squad.getMembers()) {
				c.setMap_(this.map);
			}
		}
	}//setMapForAllChar




	public void placeCharactersRandomly(){
		int edge = 0;
		int maxX = this.map.getWidth() - 1;
		int maxY = this.map.getHeight() - 1;
		for(Squad squad : ss){
			edge++;
			for(Character c : squad.getMembers()){
				int xx = s.getRandomIntBetween(0,maxX);
				int yy = s.getRandomIntBetween(0,maxY);
				if(edge == 1){    	 //place on left boarder
					xx = 0;
				}else if(edge == 2){ //place on right boarder
					xx = maxX;
				}else if(edge == 3){ //place on top boarder
					yy = 0;
				}else if(edge == 4){ //place on bottom boarder
					yy = maxY;
				}else{
					//leave as initialized -> 100% random placement
				}
//				c.getPosition_().setX(xx);
//				c.getPosition_().setY(yy);
				if(c.getMap_() != this.map){
					c.transitionToMap(this.map,xx,yy);
				}
				Area charExistsArea = this.map.getAreaOfChar(c);
				if( charExistsArea == null){
					if(s.isDebug()){ s.out("Char doesnt exist on map in placeCharactersRandomly");}
					this.map.getArea(xx,yy).getContainsObj().add(c);
					c.getPosition_().setX(xx);
					c.getPosition_().setY(yy);
				}else{
					if(s.isDebug()){ s.out("Char already exists on map in placeCharactersRandomly");}
					charExistsArea.getContainsObj().remove(c);
					this.map.getArea(xx,yy).getContainsObj().add(c);
					c.getPosition_().setX(xx);
					c.getPosition_().setY(yy);
				}

			}
		}
	}//placeCharactersRandomly



	//if ambush, s1 attack s2
	public Result beginSquadBattle(Character mc, Map1 map, Squad s1, Squad s2) throws Exception{
		//setup


		Result result = new Result();
		this.mc = mc;
		this.map = map;		
		this.s1 = s1;
		this.s2 = s2;
		this.s1.setSquadId(1);
		this.s2.setSquadId(2);
		this.ss = new ArrayList<Squad>();
		this.activeAbilitiesByChar = new ConcurrentHashMap<>();
		this.ss.add(s1);
		this.ss.add(s2);

		placeCharactersRandomly();
		setMapForAllChar(); //insert battle map into each character

		if(s.isDebug()) {
			s.out("battle begin");
			map.printDups();
		}


		this.turnCharacterId = 0;
		this.turn = 0;
		setCharIds();  //give each char a unique id for this battle
		setAbilityOwners(); //must be after (((setCharIds)))

		
		//printBattleIntroMessage
		s.pause();
		s.out("=========== BATTLE ===========");
		s.out("Squad " + s1.getSquadName() + " vs. Squad " + s2.getSquadName());
		s.out("=========== BEGIN! ===========");
		s.pause();
		//battle loop
		battleContinues = true;
		while(battleContinues == true) {
			this.characterIdOrder  = characterIdOrder();   //recalculate the order of attacks randomly each turn
			
			//loop characters for one round
			for(int i = characterIdOrder.size()-1; i>=0; i--) {
				//begin char turn
				setCurrentCharByIndex(i);  //find and set currentChar
				if(currentChar.getStatus_().isKO()==true){
					break;
				}
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
			    s.pause();
			    s.out("");
				s.out("===========================================================");
				s.out("**"+ currentChar.getName() + "'s turn begins. **  Chakra: " + (int)currentChar.getStats_().getCurrentChakra());
				s.out("===========================================================");
				s.pause();
				int currentCharId = currentChar.getId();
				//loop one character
				while(currentChar.getAction_().getNumActions() > 0) {
										//error check
										if( currentChar.getId()  != currentCharId ){
											s.out("ERROR - character has switched incorrectly --fixing now");
											currentChar = this.getCharById(currentCharId);
											s.pause();
										}
					//get action
					if(currentChar.getAI_().isAI() == true) {						
						currentAction = validatedRandomAction();		 //AI action				
					}else {						
						currentAction = getBattleAction(currentChar); 				//human action 1 - 4
					}
					//process action
					if(processAction(true) == true) {						//process
						currentChar.getAction_().decrementNumActions();
					}else{
						s.out("");
						s.out(this.getActionName(currentAction) + " action failed.");
					}//if processAction fails, infinite loop
				}//finish 2 actions -->
			}//loop to next character
			
			//loop on abilities that haven't had a chance to be defended
			//
			//



			//begin calculating results of first round of battling
			processAbilitiesByChar();  //processes defense as abilities trigger

			while(processDefeatedCharacters() ){}

			battleContinues = battleContinuesCheck();
			if(battleContinues == false) {
				Squad winner = winningSquad();
				if(winner != null){
					s.pause();
					s.out("==================================================");
					s.out(winner.getSquadName() + " is the winner!!!!!!!!!");
					s.out("==================================================");
					s.pause();

					processExperience(winner);
				}
			}
		}//end battle loop

		if(mc.getStats_().getCurrentHP()>0){
			result.setVictory(true);
		}else{
			result.setVictory(false);
		}

		this.mc.transitionToMap(mc.getOldMap_(),mc.getPosition_().getOldX(),mc.getPosition_().getOldY());
		return result;

	}//end beginSquadBattle




	public void processExperience(Squad winner){
		double totalExpEarned = 0.0;
		for(Squad s : 	this.ss){
			if(s.getSquadId() != winner.getSquadId()){
				for(Character enemy : s.getMembers()){
					int lvl = enemy.getLvl();
					double exp = enemy.experienceToLvlUpAtLevel(lvl);
					totalExpEarned += exp;
				}
			}
		}
		int totalLevels = 0;
		for(Character c: winner.getMembers()) {
			totalLevels += c.getLvl();
		}
		for(Character c: winner.getMembers()){
			double shareOfExp = (double) c.getLvl()/totalLevels ;
			c.addExperience( shareOfExp * totalExpEarned);
		}

	}//processExperience


	public boolean processDefeatedCharacters(){
		Character defeatedChar = charDefeated();
		if(defeatedChar != null){
			s.pause();
			this.map.printBattleMap();
			s.pause();
			s.out(defeatedChar.getName() + " is defeated");
			defeatedChar.getStatus_().setKO(true);
			this.map.removeChar(defeatedChar);
			s.pause();
			this.map.printBattleMap();
			return true;
		}else{
			return false;
		}
	}//processDefeatedCharacters


	public Character charDefeated(){
		for(Squad squad : this.ss){
			for(Character c : squad.getMembers()) {
				if(c.getStats_().getCurrentHP() <= 0 && c.getStatus_().isKO()==false){
					return c;
				}
			}
		}
		return null;
	}//charDefeated



	public boolean battleContinuesCheck() {
		boolean onlyOneTeamAlive = false;
		int teamsAlive = 0;
		for (Squad squad : ss) {
			if (squadKO(squad) == false) {
				teamsAlive++;
			}
		}
		if (teamsAlive == 1 || teamsAlive == 0) {
			onlyOneTeamAlive = true;
		} else{
			onlyOneTeamAlive = false;
		}

		if(onlyOneTeamAlive == true){
			return false;
		}else{
			return true;
		}
	}//battleContinuesCheck



	public boolean squadKO(Squad squad){
		boolean atLeastOneAlive = false;

		for(Character c : squad.getMembers()) {
			if(c.getStats_().getCurrentHP()>0){
				atLeastOneAlive = true;
			}
		}
		return  !atLeastOneAlive;
	}//squadKO



	public Squad winningSquad() {
		boolean onlyOneTeamAlive = false;
		Squad survivingTeam = null;
		int teamAlive = 0;
		for (Squad squad : ss) {
			if (squadKO(squad) == false) {
				return squad;
			}
		}
		return null;
	}//battleContinuesCheck




	public boolean processAbilitiesByChar(){
		boolean abilityNotCanceledCompletely;
		boolean correctCharFound = false;
		//loop characters
		for(int i=characterIdOrder.size()-1; i>=0; i--) {

			setCurrentCharByIndex(i);
			if(s.isDebug()){s.out("Process " + currentChar.getName() + " abilities");}
			int charId = characterIdOrder.get(i);
			try {
				ArrayList<Ability> list = activeAbilitiesByChar.get(charId);
				if(list==null || list.size() ==0){
					if(s.isDebug()){s.out("No abilities to process for " + currentChar.getName());}
				}else {
					ListIterator<Ability> listItr = list.listIterator();
					//ArrayList<Ability>  copyList = (ArrayList<Ability>)s.deepClone(list);
					//loop abilities per character
					while (listItr.hasNext()) {
						Ability a = listItr.next();
						currentAbility = a;
						abilityNotCanceledCompletely = processAbility(a);
						if (abilityNotCanceledCompletely == false || a.isMultiTurn() == false) {
							//remove ability from activeAbilitiesByChar.arraylist
							//activeAbilitiesByChar.get(i).remove(a);

							int abilityId = a.getId();
							if(s.isDebug()){s.out(a.getName() + " completed.");}
							listItr.remove();
						}//if delete required
					}//while
					//s.out("activeAbilitiesByChar size: "+ activeAbilitiesByChar.size());

					//s.out("activeAbilitiesByChar size: "+ activeAbilitiesByChar.size());
					if(s.isDebug()){s.out("Finished processing abilities for character id = " + charId + " Name: " + this.getCharById(charId).getName());}
				}
			}catch(Exception e){
				//do nothing
				e.printStackTrace();
				if(s.isDebug()){s.out("Error processing abilities to process for character id = "  + charId + " Name: " + this.getCharById(charId).getName());}
				if(s.isDebug()){s.out("on index = " + i);}
			}

		}//for
		return true;
	}//processAbilitiesByChar



	public boolean processAbility(Ability a){
		s.pause();
		s.out( a.getOwnerName() + " does " + a.getName()	);
		//look to see if ability was countered
		//if(counterSuccess == true) { return false; }

		//processes currentAbility
		for(Object o : a.getTargetObjs() ){
			if(o instanceof Character){
				processAbilityAgainstChar((Character)o, a) ;
			}else if(o instanceof AreaEnvi){
				processAbilityAgainstAreaEnvi((AreaEnvi)o, a) ;
			}else if(o instanceof Ability){
				processAbilityAgainstAbility((Ability)o, a);
			}
		}
		return true;
	}//processAbility




	public boolean processDefChoiceYN(Character tc){
		boolean chooseDefAction = false;
		if(tc.getAI_().isAI() == true){
			int random = s.getRandomIntBetween(1,10);
			if(random < 1){  //0% chance to defend with action
				//processAIdefense
				s.out(tc.getName() + " chooses to defend with an action.");
				chooseDefAction = true;
			}
		}else{
			s.out(tc.getName() + " - would you like to use an action to defend? (-1 action from next round)");
			s.out("1. Action to defend");
			s.out("2. Use durability to defend");
			int answer = s.getIntBetween(1,2);
			if(answer == 1){
				chooseDefAction = true;
			}
		}
		return chooseDefAction;
	}


	public boolean runDefenseChoices(Character tc){
		boolean abilityNotCanceledCompletely = true;
		//currentChar = tc;
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
				s.out(this.getActionName(currentDefAction) + " action succeeded.");
			}else{
				s.out("_______________________________");
				s.out("");
				s.out(this.getActionName(currentDefAction) + " action failed.");
				s.out("_______________________________");
			}//if processAction fails, infinite loop
		}//finish 2 actions -->

		return abilityNotCanceledCompletely;
	}

	public boolean processOneCharactersDefense(Character tc, Ability a){
		boolean abilityNotCanceledCompletely = true;
		tc.getStatus_().setHasBeenTargeted(true);
		canDefend = tc.getStatus_().canMove();
		currentDefChar = tc;
		s.out(tc.getName() + " has been targeted by " + a.getOwnerName() + "'s " + a.getName());
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



	public boolean processAbilityAgainstChar(Character tc, Ability a) {
		//defense option
		boolean abilityNotCanceledCompletely = processOneCharactersDefense(tc, a);

		if (abilityNotCanceledCompletely) {

			//Stats cs = currentChar.getStats_();
			double cbDamage = a.getBasicDamage();
			double ccDamage = a.getChakraDamage();

			s.out( (int)(cbDamage + ccDamage) + " Base damage  -> " + " (physical dmg = " + (int)cbDamage + " / chakra dmg = " + (int)ccDamage + ")");

			scaleAbility(a);
			double cbDamageScaled = a.getBasicDamage();
			double ccDamageScaled = a.getChakraDamage();
			double totalBonus = (cbDamageScaled + ccDamageScaled) - (cbDamage + ccDamage);
			s.out((int)totalBonus + " Bonus Damage      " );
			//s.out("_______________________________________");
			s.pause();
			s.out(a.getOwnerName() + "'s " + a.getName() + " strikes " + tc.getName() + " for " + (int)(cbDamageScaled + ccDamageScaled));

			//cancel damage with defense
			Stats ts = tc.getStats_();

			double tbDef = ts.getBasicDef();
			double tcDef = ts.getChakraDef();
			tbDef = (double) s.getRandomIntBetween(0, (int) tbDef);
			tcDef = (double) s.getRandomIntBetween(0, (int) tcDef);
			s.out(tc.getName() + " blocks " + (int)tbDef + " physical dmg and " + (int)tcDef + " chakra dmg");
			//s.out("_______________________________________");
			s.pause();
			cbDamageScaled = (cbDamageScaled - tbDef < 0) ? 0 : (cbDamageScaled - tbDef);
			ccDamageScaled = (ccDamageScaled - tcDef < 0) ? 0 : (ccDamageScaled - tcDef);
			double total = cbDamageScaled + ccDamageScaled;
			s.out("Total Damage = " + (int)total);
			double tchp = ts.getCurrentHP();
			ts.setCurrentHP(tchp - total);
			s.pause();
			return true;
		} else{
			s.out(a.getName() + " was negated.");
			return false;
		}
	}//processAbilityAgainstChar

	public boolean processAbilityAgainstAreaEnvi(AreaEnvi tae, Ability a){

		return true;

	}

	public boolean processAbilityAgainstAbility(Ability ta, Ability a){

		return true;

	}

	public void scaleAbility(Ability a){

		String[] t = {"maxHP","maxChakra","hpRegen","chakraRegen","basicAtk","chakraAtk","basicDef","chakraDef","speed","brains","sensing"};
		for(String tt : t){
			scaleAbilityOnTrait(a , tt);
		}
	}//scaleAbility



	public void scaleAbilityOnTrait(Ability a, String trait){
		Double scale = a.getBdScalingBonus().get(trait);
		Character owner = this.getCharById(a.getOwnerId()) ;
		if( scale != null){
			double t = owner.getStats_().getStatFromName(trait);
			double randomOnTrait = (double)s.getRandomIntBetween((int)t/2, (int)t*2);
			double randomBonus = randomOnTrait * scale;
			double scalingBonus = randomBonus * scale * scale * a.getBasicDamage();
			double bonus = scalingBonus + randomBonus;
			s.out("+ " + (int)bonus + " bonus physical damage, boosted by "  + trait + " @ " + (int)(scale*100) + "%" );
			double bd = a.getBasicDamage();
			a.setBasicDamage(bd + bonus);
		}

		Double scale2 = a.getCdScalingBonus().get(trait);

		if( scale2 != null){
			double t = owner.getStats_().getStatFromName(trait);
			double randomOnTrait = (double)s.getRandomIntBetween((int)t/2, (int)t*2);
			double randomBonus = randomOnTrait * scale2;
			double scalingBonus = randomBonus * scale2 * scale2 * a.getBasicDamage();
			double bonus = scalingBonus + randomBonus;
			s.out("+ " + (int)bonus + " bonus chakra damage, boosted by "  + trait + " @ " + (int)(scale2*100) + "%" );
			double cd = a.getChakraDamage();
			a.setBasicDamage(cd + bonus);
		}
	}//scaleAbilityOnTrait



	public boolean processAction(boolean isOffense) {
		//prevalidated
		if(isOffense){
			return processValidatedAction();
		}else{
			return processValidatedDefAction(currentDefChar);
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
			//human
			if(currentChar.getAI_().isAI() == false) {
				String direction = "";
				//while(actionSuccess==false && direction != "back" && direction != "cancel"){
					direction = currentChar.getCommands_().getCommand();
					actionSuccess = currentChar.getCommands_().processBattleMapCommand(direction, currentChar.getAI_().isAI() );
				//}
				if (actionSuccess) {
					this.getMap().printBattleMap();
					s.out("");
					s.pause();
					s.out(currentChar.getName() + " moved " + direction);
					s.pause();
					currentChar.getAction_().setUsedMove(true);
				}
			//AI
			}else{ //is Ai = true
				String direction = currentChar.getAI_().getRandomBattleMoveDirection();
				actionSuccess = currentChar.getCommands_().processBattleMapCommand(direction, currentChar.getAI_().isAI() );
				while(actionSuccess==false && direction != "back" && direction != "cancel"){
					direction = currentChar.getAI_().getRandomBattleMoveDirection();
					actionSuccess = currentChar.getCommands_().processBattleMapCommand(direction, currentChar.getAI_().isAI() );
				}

				if(actionSuccess){
					this.getMap().printBattleMap();
					s.out("");
					s.pause();
					if(s.isDebug()){ s.out("AI");}
					s.out(currentChar.getName() + " moved " + direction);
					currentChar.getAction_().setUsedMove(true);
					s.pause();
				}else{
					s.out(currentChar.getName() + " failed at moving " + direction);
				}
			}
		}
		//offensive
		if(currentAction == 3) {
			//HUMAN
			if(currentChar.getAI_().isAI() == false) {
				Ability offensive = currentChar.getAbilities_().chooseOffensive();
				Ability offensiveCopy = (Ability) s.deepClone(offensive);

				if (offensive != null) {
					if (currentChar.canDo(offensive)) {
						currentAbility = offensiveCopy;
						actionSuccess = prepareAbility(true);
						if (actionSuccess) {
							offensiveCopy.setId(this.a_id);
							this.a_id++;
							activeAbilitiesCurrentChar.add(offensiveCopy);
							activeAbilitiesByChar.put(currentChar.getId(), activeAbilitiesCurrentChar);
							currentChar.getAction_().setUsedOffensive(true);
						} else {
							if (s.isDebug()) {
								s.out(currentChar.getName() + " prepareAbility failed");
							}
						}
					}else{
							if (s.isDebug()) {	s.out(currentChar.getName() + " can't do an offensive maneuver at this time");}
					}
				}else {
						if (s.isDebug()) {
							s.out(currentChar.getName() + " null ability");
						}
				}

			//AI
			}else{
				Ability offensive = currentChar.getAbilities_().chooseOffensiveAI();
				Ability offensiveCopy = (Ability) s.deepClone(offensive);

				if (offensive != null) {
					if (currentChar.canDo(offensive)) {
						currentAbility = offensiveCopy;
						actionSuccess = prepareAbility(true);
						if (actionSuccess) {
							offensiveCopy.setId(this.a_id);
							offensiveCopy.setOwnerName(currentChar.getName())	;
							offensiveCopy.setOwnerId(currentChar.getId());
							this.a_id++;
							activeAbilitiesCurrentChar.add(offensiveCopy);
							activeAbilitiesByChar.put(currentChar.getId(), activeAbilitiesCurrentChar);
							currentChar.getAction_().setUsedOffensive(true);
						}else {
							if(s.isDebug()){s.out(currentChar.getName() + " prepareAbility failed"); }
						}
					}else{
						if(s.isDebug()){s.out(currentChar.getName() + " can't do an offensive maneuver at this time"); }
					}
				}else{
					if(s.isDebug()){s.out(currentChar.getName() + " null ability"); }
				}
			}//end AI

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
			s.out("Sorry, " + currentChar.getName() + " has no items.");
		}
		if(currentAction == 6){
			this.map.printBattleMap();
			int id = this.selectMapId();
			Area aa = this.map.getAreaFromID(id);
			aa.listObjects();
			int ii = s.getInt();
			Character cc = aa.getCharacterFromIndex(ii);
			if(cc!=null){
				cc.printBattleStatus();
			}else{
				s.out("Nothing here");
			}

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
			actionSuccess = tc.getCommands_().processBattleMapCommand(com, tc.getAI_().isAI());
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
		if(currentDefAction == 4) {
			if(tc.getAI_().isAI() == false) {
				Ability defensive = tc.getAbilities_().chooseDefensiveAI();
				Ability defensiveCopy = (Ability) s.deepClone(defensive);

				if (defensive != null) {
					if (tc.canDo(defensive)) {
						currentAbility = defensiveCopy;
						actionSuccess = prepareAbility(false);
						if (actionSuccess) {
							defensiveCopy.setId(this.a_id);
							this.a_id++;
							//activeAbilitiesCurrentChar.add(defensiveCopy);
							activeAbilitiesByChar.get(tc.getId()).add(defensiveCopy);
							//activeAbilitiesByChar.put(tc.getId(), activeAbilitiesCurrentChar);
							if(s.isDebug()  ){s.out(defensiveCopy.getName()  + " activated for " + tc.getName()   );}
							tc.getAction_().setUsedDefensive(true);
						} else {
							s.out(defensiveCopy.getName() + " cancelled.");
						}
					} else {
						s.out("Sorry, " + tc.getName() + " can't use defensive moves right now.");
					}
				} else {
					s.out("Sorry, " + tc.getName() + " has no defensive abilities.");
				}
			//AI
			}else{
				Ability defensive = tc.getAbilities_().chooseDefensiveAI();
				Ability defensiveCopy = (Ability)s.deepClone(defensive) ;

				if(defensive != null) {
					if(tc.canDo(defensive) ){
						currentAbility = defensiveCopy;
						actionSuccess = prepareAbility(false);
						if(actionSuccess){
							defensiveCopy.setId(this.a_id);
							this.a_id++;
							//activeAbilitiesCurrentChar.add(defensiveCopy);
							activeAbilitiesByChar.get(tc.getId()).add(defensiveCopy);
							//activeAbilitiesByChar.put(tc.getId(), activeAbilitiesCurrentChar );
							if(s.isDebug()  ){s.out(defensiveCopy.getName()  + " activated for " + tc.getName()   );}
							tc.getAction_().setUsedDefensive(true);
						}else{
							s.out(defensiveCopy.getName() + " cancelled.");
						}
					}else{
						s.out("Sorry, " + tc.getName() + " can't use defensive moves right now.");
					}
				}else{
					s.out("Sorry, " + tc.getName() + " has no defensive abilities.");
				}
			}

		}
		if(currentDefAction == 5) {
			s.out("Sorry, " + tc.getName() + " has no items.");
		}
		if(currentDefAction == 6){
			this.map.printBattleMap();
			int id = this.selectMapId();
			Area aa = this.map.getAreaFromID(id);
			aa.listObjects();
			int ii = s.getInt();
			Character cc = aa.getCharacterFromIndex(ii);
			if(cc!=null){
				cc.printBattleStatus();
			}else{
				s.out("Nothing here");
			}
		}
		return actionSuccess;
	}//processValidatedDefAction







	public boolean prepareAbility(boolean isOffensive) {
		Ability prepAbility;
		prepAbility = currentAbility;
		//prepAbility = currendDefAbility;
		Character charUsingAbility;
		if(isOffensive){
			charUsingAbility = currentChar;
		}else{
			charUsingAbility = currentDefChar;
		}

		boolean abilityReady = false;

		s.out("Activating ability \"" + currentAbility.getName() + "\"");
		//calc cost + boost cost
		double cost = currentAbility.getChakraCost();
		if(currentAbility.isBoostable()) {
			if(charUsingAbility.getAI_().isAI()){
				int aa = s.getRandomIntBetween(1, 2);
				if(aa == 1) {
					currentAbility.setBoosted(true);
					cost += currentAbility.getBoostChakraCost();
				}else {
					currentAbility.setBoosted(false);
					cost = currentAbility.getChakraCost();
				}
			}else{
				s.out("Would you like to boost this ability for " + currentAbility.getBoostChakraCost() + " chakra?");
				s.out("1. Yes");
				s.out("2. No");
				int aa = s.getIntBetween(1, 2);
				if(aa == 1) {
					currentAbility.setBoosted(true);
					cost += currentAbility.getBoostChakraCost();
				}else {
					currentAbility.setBoosted(false);
					cost = currentAbility.getChakraCost();
				}
			}

		}//boost
		
		//set targets
		double nt = currentAbility.getNumTargets();
		if( nt > 0){
			int input = 0;
			int max = this.getMap().getMaxAreaID();
			while (nt > 0) {
				s.pause();
				this.map.printBattleMap();
				s.pause();
			    s.out("");
			    s.out(currentAbility.getName() + " has a range of " + currentAbility.getRange()  );
				s.out("");
				s.out(currentAbility.getName() + " has " + nt + " target(s)");
				s.out("");

				//AI
				//AI
				boolean foundEnemyWithinRange = false;
				if(charUsingAbility.getAI_().isAI()){
					for(int i = 1; i<= max; i++){
						//input = s.getRandomIntBetween(0, max);

						Area randA = this.map.getAreaFromID(i);  //Map Area Ids start at 1 and go to Max
						if(randA.getContainsObj()!=null && randA.getContainsObj().size()>0){
							for(Object o : randA.getContainsObj()){
								if(o instanceof Character){
									if(this.isEnemy(charUsingAbility,(Character)o  ) && targetAreaWithinRange(randA) ){
										currentTargetArea = randA;
										currentTargetAreaID = i;
										foundEnemyWithinRange = true;
										break;
									}
								}
								if(foundEnemyWithinRange ==true){
									break;
								}
							}
							if(foundEnemyWithinRange ==true){
								break;
							}
						}

					}
					if(foundEnemyWithinRange ==false){
						s.out(charUsingAbility.getName() + " could not find an enemy within range of " + currentAbility.getName());
						//if(s.isDebug()){s.out(charUsingAbility.getName() + " could not find an enemy within range of " + currentAbility.getName());}
						currentTargetArea = this.map.getAreaFromID(0);
						currentTargetAreaID = 0;
						return false;
					}
				//HUMAN
				}else{
					s.out("  Target Area ID (or 0: Cancel)");
					s.out("=====================");
					s.print(":");
					input = s.getIntBetween(0, max);
					if(input == 0) {
						return false;
					}
					currentTargetAreaID = input;
					currentTargetArea = this.map.getAreaFromID(input);
				}

				if(currentTargetArea == null){
					if(s.isDebug()){s.out("target area is null");}
					return false;
				}

				//validate range of ability
				if(targetAreaWithinRange() == false) {
					s.out("___________________________________");
					s.out("");
					s.out("Sorry, Area " + input + " is out of range.");
					s.out("");
					s.out("___________________________________");
					s.out("");
					s.out(currentAbility.getName() + " range : " + (int)currentAbility.getRange() );
					s.out("");
					s.out("___________________________________");
					s.out("");
					return false;
				}
				
				abilityReady = getAndSetTargetForCurrentTargetArea(isOffensive);
				if(abilityReady == true) {
					nt--;
				}
			}
		}
		//abilityReady == success --->subtract chakra
		if(abilityReady){
			double cc = charUsingAbility.getStats_().getCurrentChakra();
			charUsingAbility.getStats_().setCurrentChakra(cc - cost);
			s.out(currentChar.getName() + " spends " + cost + " chakra");
		}

		return abilityReady;
		//if(ab.includesDash){processDash}
	}//prepareAbility
	
	
	public boolean getAndSetTargetForCurrentTargetArea(boolean isOffense) {

		Character charUsingAbility;
		if(isOffense){
			charUsingAbility = currentChar;
		}else{
			charUsingAbility = currentDefChar;
		}

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
	    //AI
	    if(charUsingAbility.getAI_().isAI()){
			int choice = s.getRandomIntBetween(0,list.size());
			if(s.getRandomIntBetween(0,100)>0){
				choice = 1;  //default to character choice at top of list (99%)
			}
			if(choice == 0) {
				return false;
			}else if( choice <= potentialChar.size() ) {
				currentAbility.getTargetObjs().add(potentialChar.get(choice)  );
			}else {
				String envi = potentialEnvi.get(choice);
				AreaEnvi ae = new AreaEnvi(currentTargetArea, envi);
				currentAbility.getTargetObjs().add(ae);
			}
		//HUMAN
		}else { //not AI --> human
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
		}
		return true;
	}//getAndSetTargetForCurrentTargetArea
	
	
	public boolean targetAreaWithinRange() {
		boolean valid = false;
		double r = currentAbility.getRange();
		//Area charA = currentChar.getMap_().getAreaMC();
		Area charA = currentChar.getMap_().getAreaOfChar(currentChar);
		double cx = charA.getX();
		double cy = charA.getY();
		double tx = currentTargetArea.getX();
		double ty = currentTargetArea.getY();
		if( (Math.abs(tx - cx) <= r) && (Math.abs(ty - cy) <= r)  ){
			valid = true;
		}
		return valid;
	}


	public boolean targetAreaWithinRange(Area targetA) {
		boolean valid = false;
		double r = currentAbility.getRange();
		//Area charA = currentChar.getMap_().getAreaMC();
		Area charA = currentChar.getMap_().getAreaOfChar(currentChar);
		double cx = charA.getX();
		double cy = charA.getY();
		double tx = targetA.getX();
		double ty = targetA.getY();
		if( (Math.abs(tx - cx) <= r) && (Math.abs(ty - cy) <= r)  ){
			valid = true;
		}
		return valid;
	}

	public Squad getEnemySquad(Character activeChar){   //random enemy squad if multiple
		for(Squad s: this.ss){
			if(!s.getMembers().contains(activeChar)){
				return s;
			}
		}
		return null;
	}

	public Squad getAllySquad(Character activeChar){
		for(Squad s: this.ss){
			if(s.getMembers().contains(activeChar)){
				return s;
			}
		}
		return null;
	}

	public boolean isEnemy(Character a, Character b){
		Squad allies = getAllySquad(a);
		if(allies.getMembers().contains(b)){
			return false;
		}else{
			return true;
		}
	}



	
	public int validatedRandomAction() { 
		int v = -1;
		while(validateAction(v) == false || v == 1 ) {   //dont allow transitions for AI until tested
			int priority = s.getRandomIntBetween(1, 10);
			if(priority >=1 && priority <= 2	){
				v = 0;  //20% rest
			}else if(priority >2 && priority <=4){
				v = 2;	//20% move
			}else {
				v = 3;  //60% attack
			}
			//v = s.getRandomIntBetween(0, 5);
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
		case 6:
			return true;
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
			s.out("6. Get info on area");
			a = s.getIntBetween(0, 6);
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
			if (!(currentChar == null || currentChar.equals(null) )) {
				break;
			}
		}
		if(currentChar == null) {
			//throw new Exception("No character found for id = " + i);
			s.out("Error, character not found");
		}
	}//end setCurrentCharByIndex


	public Character getCharByIndex(int i) {
		Character c = null;
		for(Squad s : ss) {
			c = s.getCharById(characterIdOrder.get(i));
			if (!(c==null || c.equals(null) )) {
				break;
			}
		}
		if(c == null) {
			//throw new Exception("No character found for id = " + i);
			s.out("Error, character not found");
		}
		return c;
	}//end setCurrentCharByIndex


	public Character getCharById(int id) {
		Character c = null;
		for(Squad s : ss) {
			c = s.getCharById(id);
			if (!(c==null || c.equals(null) )) {
				break;
			}
		}
		if(c == null) {
			//throw new Exception("No character found for id = " + i);
			s.out("Error, character not found");
		}
		return c;
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




	public int selectMapId(){

		int input = 0;
		int max = this.getMap().getMaxAreaID();
		s.out("=========================");
		s.out(" Area ID (or 0: Cancel)");
		s.out("=========================");
		s.print(":");
		input = s.getIntBetween(0, max);
		if(input == 0) {
			return -1;
		}else {
			return input;
		}
	}//selectMapId


	public void setAbilityOwners(){
		for(Squad sq : this.ss){
			for(Character c : sq.getMembers() ){
				for(Ability a : c.getAbilities_().getaList()){
					a.setOwnerName(c.getName());
					a.setOwnerId(c.getId());
				}
			}
		}

	}//selectMapId



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

	public ConcurrentHashMap<Integer, ArrayList<Ability>> getActiveAbilitiesByChar() {
		return activeAbilitiesByChar;
	}

	public void setActiveAbilitiesByChar(ConcurrentHashMap<Integer, ArrayList<Ability>> activeAbilitiesByChar) {
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

	public System1 getS() {
		return s;
	}

	public void setS(System1 s) {
		this.s = s;
	}

	public int getCurrentDefAction() {
		return currentDefAction;
	}

	public void setCurrentDefAction(int currentDefAction) {
		this.currentDefAction = currentDefAction;
	}

	public Character getCurrentDefChar() {
		return currentDefChar;
	}

	public void setCurrentDefChar(Character currentDefChar) {
		this.currentDefChar = currentDefChar;
	}

	public int getCurrentTargetAreaID() {
		return currentTargetAreaID;
	}

	public void setCurrentTargetAreaID(int currentTargetAreaID) {
		this.currentTargetAreaID = currentTargetAreaID;
	}

	public Area getCurrentTargetArea() {
		return currentTargetArea;
	}

	public void setCurrentTargetArea(Area currentTargetArea) {
		this.currentTargetArea = currentTargetArea;
	}

	public Ability getCurrentAbility() {
		return currentAbility;
	}

	public void setCurrentAbility(Ability currentAbility) {
		this.currentAbility = currentAbility;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}




	public Character getMc() {
		return mc;
	}

	public void setMc(Character mc) {
		this.mc = mc;
	}


}//end Battle
