import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;


public class Story1 implements Story, Serializable{
 
	private int answer = 0;
	private System1 s = new System1();
	private ArrayList<Character> snakeList = new ArrayList<>();
	private int mizukiApproachCount = 0;
	private boolean mizukiDefeated = false;



	//public ListIterator<Character> snakeListIter = new ListIterator<Character>();




	@Override
	public void start_(Character mc) { 
		//SETH BEGIN HERE

		Event e = new Event(mc);
		
		
		//PLOT
		//Ninja enters village
		//Nice ninja arrives as guide to watch chuunin exams
		//mean ninja is in audience
		//dialoge - befriend mean ninja = sacrifice friendship with nice ninja
		//dialogue - maintaining current friend = piss off mean ninja
		//if mean ninja = pissed off --> attacks you after the show
		//Your sensei tells you that he needs your help
		//deliver a scroll to the hokage's assistant
		//peek inside scroll = lose trust but gain a forbidden jutsu

		//boolean isAI, String nameInput, int lvlInput, String gender
		Character saisu = new Character(true, "Saisu Kamano", 1, "male", "weaponSummoner");
		saisu.getStats_().loadClawed(saisu.getLvl());
		mc.getRel_().newRelationship75(saisu.getName());
		//https://naruto.fandom.com/wiki/Saisu_Kamano
		//someone build loader function inside Character Class file for Saisu's stats
		//print nice character image;
		s.out(saisu.getName() + ": Hey " + mc.getName() ) ;
		s.pause();
		s.out(saisu.getName() + ": Do you hear that loud roaring?");
		s.pause();
		s.out("Choose a response");
		s.out("1. yeah, whats all the commotion?");
		s.out("2. no, what's up?");
		s.out("3. no, are you hearing things? ");
		answer = s.getIntBetween(1,3);
		if(answer == 1) {
			mc.getRel_().addGoodDeedAgainst(saisu.getName());
			s.out("<" + saisu.getName() + " noticed your positive attitude.>");
			s.pause();
			s.out(saisu.getName() + ": I was wondering when you would finally notice!");
			s.pause();
			s.out(saisu.getName() + ": It's the chunin exams!  We're late for the show!");
			s.pause();
			s.out(saisu.getName() + ": Let's go find some seats in the audience.");
			s.pause();
			s.out("Choose a response");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");
		}else if(answer == 2) {
			//no good deed, no bad deed, neutral response
			s.out(saisu.getName() + ": Man, you must be deaf or something!");
			s.pause();
			s.out(saisu.getName() + ": It's the chunin exams!  We're late for the show!");
			s.pause();
			s.out(saisu.getName() + ": Let's go find some seats in the audience.");
			s.pause();
			s.out("Choose a response");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");
		}else {
			s.out("<" + saisu.getName() + " noticed your negative attitude.>");
			mc.getRel_().addBadDeedAgainst(saisu.getName());
			s.pause();
			s.out(saisu.getName() + ": What?? You're kidding, right?");
			s.pause();
			s.out(saisu.getName() + ": It's the chunin exams!  We're late for the show!");
			s.pause();
			s.out(saisu.getName() + ": Let's go find some seats in the audience.");
			s.pause();
			s.out("Choose a response");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");			
		}
		answer = s.getIntBetween(1,2);
		mainmap = new Map1(mc,10,5);  //max x = 9, max y = 4
		mc.setMap_(mainmap);


		//arena
		Character arenaBuilding = new Character();
		arenaBuilding.setName("Arena");
		mainmap.placeCreatureAndRemove(arenaBuilding,9,4);
		//mizuki
		Character mizuki = new Character(true,"Mizuki",2,"male","tricky");
		mc.getRel_().newRelationship50(mizuki.getName());
		mizuki.getAbilities_().getaList().add(new Ability() );
		mizuki.getAbilities_().getaList().add(new Ability() );
		mizuki.getAbilities_().getaList().get(0).loadChargingPunch();
		mizuki.getAbilities_().getaList().get(1).loadSwordSlash();
		mainmap.placeCreatureAndRemove(mizuki,0,4);
		//arena
		Character sakane = new Character(true, "Sakane Tsuchigumo", 7, "male", "elemental");
		mc.getRel_().newRelationship50(sakane.getName());
		sakane.setName("Izumi Uchiha");
		mainmap.placeCreatureAndRemove(sakane,5,2);
		//snakes
		for(int i=0; i<5; i++){
			placeRandomSnake(mainmap);
		}

		if(true) {  //answer == 1
			//mainmap.printMapOfNames();
			mainmap.printBattleMap();
			while(e.mcArrivedAtChar(arenaBuilding)==false) {


				//MIZUKI
				if (e.mcNextToChar(mizuki) == true && mizukiDefeated == false) {
					if( mizukiApproachCount == 0){
						mizukiDefeated = mizukiDialogue(mc, mizuki);
					}else{
						mizukiDefeated = mizukiDialogue2(mc, mizuki);
					}
				}//end Mizuki

				if(mizukiDefeated){
					mainmap.removeChar(mizuki);
				}

				//SNAKE event
				//move snakes
				ListIterator<Character> snakeListItr = snakeList.listIterator();
				while (snakeListItr.hasNext()) {
					Character snake = snakeListItr.next();
					if (e.mcNextToChar(snake) == true) {
						s.pause();
						s.out("WARNING! Snake is coming for an attack!");
						s.pause();
						//Area mcArea = mainmap.getAreaOfChar(mc);
						//mainmap.removeChar(mc);

						Map1 snakeBattleMap = new Map1(3, 3);

											//BEFORE TEST
											if(s.isDebug()) {
												s.out("BEFORE TEST");
												mainmap.printDups();
												snakeBattleMap.printDups();
											}

						mc.transitionToMap(snakeBattleMap,0,0);
						snake.transitionToMap(snakeBattleMap, 2,2);

										//AFTER TRANSITION TEST
										if(s.isDebug()) {
											s.out("AFTER TRANSITION TEST");
											mainmap.printDups();
											snakeBattleMap.printDups();
										}

						//snakeBattleMap.placeCreatureAndRemove(snake, 2, 2);
						Squad s1 = new Squad(mc);
						Squad s2 = new Squad(snake);

						Result result = new Result();
						try {

							result = mc.getBattle_().beginSquadBattle(mc, snakeBattleMap, s1, s2);

							//AFTER BATTLE TEST
							if(s.isDebug()) {
								s.out("AFTER BATTLE TEST");
								mainmap.printDups();
								snakeBattleMap.printDups();
							}

							//mc.setMap_(mainmap);
							//mainmap.placeCreatureAndRemove(mc, mcArea.getX(), mcArea.getY());
						} catch (Exception ee) {
							// TODO Auto-generated catch block
							ee.printStackTrace();
						}
						if(result.isVictory()){
							//map.remove snake
							mainmap.printBattleMap();
							s.pause();
							s.out("Snake has been destroyed!");
							s.pause();
							mainmap.removeChar(snake);
							snakeListItr.remove();
							mainmap.printBattleMap();

							//AFTER REMOVAL TEST
							if(s.isDebug()) {
								s.out("AFTER REMOVAL TEST");
								mainmap.printDups();
								snakeBattleMap.printDups();
							}



						}
					}//end snake battle
				}//loop nearby snakes

				mc.getCommands_().runCommands();

			}//while loop

		}
		
		
		
		
		
		
		
		//Kakashi is jonin at this time - maybe a sensei
		//boolean isAI, String nameInput, int lvlInput, String gender
		Character ibiki = new Character(true,"Ibiki Morino", 25, "male", "weaponSummoner");
		ibiki.getStats_().loadWeaponSummoner(ibiki.getLvl());
		mc.getRel_().newRelationship75(ibiki.getName());

		s.out("THE STORY CONTINUES....");
		s.out("6 months later...");
		s.out("");
		s.out(ibiki.getName() + ": " + mc.getName() + ", its time for you to begin a mission.");
		s.out("Please select a mission");
		s.out("0. Cancel");
		s.out("1. Sam's mission    - D rank -  $300 gold");
		s.out("2. Chris's mission  - D rank -  $300 gold");
		s.out("3. Jake's mission   - C rank -  $600 gold");
		s.out("4. Josh's mission   - C rank -  $600 gold");
		s.out("5. Elias's mission  - B rank - $1200 gold");
		s.out("6. Seth's mission   - B rank - $1200 gold");
		answer = s.getIntBetween(0,6);
		Mission1 m1 = new Mission1();
		Mission2 m2 = new Mission2();
		Mission3 m3 = new Mission3();
		Mission4 m4 = new Mission4();
		Mission5 m5 = new Mission5();
		Mission6 m6 = new Mission6();

		if(answer ==1) {
			m1.start_(mc);
		}else if(answer == 2) {
			m2.start_(mc);
		}else if(answer == 3) {
			m3.start_(mc);
		}else if(answer == 4) {
			m4.start_(mc);
		}else if(answer == 5) {
			m5.start_(mc);
		}else if(answer == 6) {
			m6.start_(mc);
		}else {
			s.out("Okay then.  No missions for you.");
		}
		
	}//end story1
	


	public Character newSnake(){
		Character snake = new Character(true,"snake",1, "male", "clawed");
		snake.getStats_().loadClawed(snake.getLvl());
		snake.getAbilities_().getaList().add(new Ability() );
		snake.getAbilities_().getaList().get(0).loadScratch();
		snake.getAbilities_().getaList().get(0).setName("Tail Whip");
		snake.getStats_().setCurrentHP(5);
		snake.getStats_().setHpRegen(0);
		return  snake;
	}

	public void placeRandomSnake(Map1 m){
		Area a = m.getRandomEmptyArea();
		Character snake = newSnake();
		this.snakeList.add(snake);
		m.placeCreatureAndRemove(snake,a.getX(),a.getY());
	}



	public boolean mizukiDialogue(Character mc, Character mizuki){
		boolean winFight = false;
		s.pause();
		boolean whySnakes = false;
		boolean suspicious = false;
		s.out("Approach " + mizuki.getName() + "?"	);
		s.out("1. Yes") ;
		s.out("2. No")	;
		s.print(":");
		answer = s.getIntBetween(1,2);
		if(answer == 1){  //approach yes
			mizukiApproachCount++;
			String[] approach = new String[4];
			approach[1] = " 'Hey man, what's up?' ";
			approach[2] = " 'What's with all the snakes around here?' ";
			approach[3] = " 'You look awfully suspicious, what are you up to?' ";
			s.out("Choose an approach");
			for(int i=1; i<=3; i++){
				s.out( i + ": " + approach[i]);
			}
			s.print(":");
			answer = s.getIntBetween(1,3);
			if(answer == 1 ){ //approach 1, friendly
				//approach[1] = " 'Hey man, what's up?' ";
				s.out("--------------------------------------------");
				s.out(mc.getName() + ": " + approach[1]);
				s.pause();
				s.out(mizuki.getName() +": 'Oh, you know, just enjoying the weather!'");
				if(s.getRandomIntBetween(0, (int)mc.getStats_().getSensing() ) >= 1	 ){
					s.out(mc.getName() + " sensing skill: " + "<notices a sly smile>");
				}else{
					s.out("<failed to sense something>");
				}
				s.pause();
				s.out("Respond to " + mizuki.getName() + "?"	);
				s.out("1. Yes")	;
				s.out("2. No")	;
				s.print(":");
				answer = s.getIntBetween(1,2);
				if(answer == 2){  //no response
					//do nothing continue about your day
					s.out("");
					s.out("--------------------------------------------");
					s.out(mc.getName() + " walks away");
					s.pause();
					answer = 0;
				}else if( answer == 1){  //choose response
					//move on to next bracket
					String[] response = new String[5];
					response[1] = " 'It is nice out! Mind if I join you?' ";
					response[2] = " 'How can you enjoy the weather with all these snakes about??' ";
					response[3] = " 'Yes, I agree.  I particularly favor the cumulus clouds. How about you?' ";
					response[4] = " 'Seems like something someone would say to hide what they are really doing...' ";
					s.out("");
					s.out("Choose a response");
					int resp = 3;
					for(int i=1; i<=3; i++){
						s.out( i + ": " + response[i]);
					}
					if(s.getRandomIntBetween(0, (int)mc.getStats_().getBrains() ) >= 1	 ){
						s.out( 4 + ": " + response[4] + "(" +mc.getName() + " intelligence skill)");
						resp = 4;
					}else{
						s.out("<failed to deduce something>");
					}
					s.print(":");
					answer = s.getIntBetween(1,resp);

					//MC RESPONSE
					s.out("--------------------------------------------");
					s.out(mc.getName() + ": " + response[answer]);
					s.pause();
					//MIZUKI RESPONSE
					if(answer==1){  //" 'It is nice out! Mind if I join you?' ";

						s.out(mizuki.getName() + ": 'Well...' <looks around awkwardly> 'Sure why not.'");
						s.pause();
						s.out("<" + mizuki.getName() + " notices your friendly attitude>");
						mc.getRel_().addGoodDeedAgainst(mizuki.getName());
						s.pause();
						if(s.getRandomIntBetween(0, (int)mc.getStats_().getSensing() ) >= 2	 ){
							s.out(mc.getName() + " sensing skill: " + "<noticed " + mizuki.getName() +
									" look towards a person in the east "); //+ sakane.getName() + " >");
						}else{
							s.out("<failed to sense something>");
						}
						s.pause();
						s.out("Continue to chat?");
						s.out("1: Yes");
						s.out("2: No");
						s.print(":");
						answer = s.getIntBetween(1,2);
						if(answer == 1){
							teamUpMizuki(mc,mizuki);
						}else{
							//break
						}
					}else if(answer == 2){  //'How can you enjoy the weather with all these snakes about??'

						//ACTIVATE WHY SNAKE BRANCH
						whySnakes = true;

					}else if(answer == 3){  //'Yes, I agree.  I particularly favor the cumulus clouds. How about you?'
						if(mc.getGender()=="female"){
							s.out(mizuki.getName() + ": 'Haha - you're so cute.  I prefer the thunderstorms though.' ");
							mc.getRel_().addGoodDeedAgainst(mizuki.getName());
							s.pause();
							s.out("<" + mizuki.getName() + " is starting to like you>");
						}else{
							s.out(mizuki.getName() + ": 'You're such a nerd.' <rolls eyes>");
							mc.getRel_().addBadDeedAgainst(mizuki.getName());
							s.pause();
							s.out("<" + mizuki.getName() + " opinion of you drops>");
						}
						s.pause();
						s.out("Continue to chat?");
						s.out("1: Yes");
						s.out("2: No");
						s.print(":");
						answer = s.getIntBetween(1,2);
						if(answer == 1){
							teamUpMizuki(mc,mizuki);
						}else{
							//break
						}
					}else if(answer == 4){  //'Seems like something someone would say to hide what they are really doing...'
						suspicious = true;
						// ACTIVATE SUSPICIONS BRANCH
					}

				}//done with conversation
				//finish approach 1
			}else if(answer == 2){ //approach 2, questioning
				//approach[2] = " 'What's with all the snakes around here?' ";

				s.out("--------------------------------------------");
				s.out(mc.getName() + ": " + approach[2]);
				s.pause();
				whySnakes = true;
				//ACTIVATE WHY SNAKES BRANCH

			}else if(answer == 3){ // approach 3, suspicious
				//approach[3] = " 'You look awfully suspicious, what are you up to?' ";
				s.out("--------------------------------------------");
				s.out(mc.getName() + ": " + approach[3]);
				s.pause();
				suspicious = true;
				//ACTIVATE SUSPICIOUS BRANCH
			}
		}else{
			//continue about your day
			answer = 0;
		}


		if(whySnakes == true){

			s.out(mizuki.getName() + ": 'Snakes? What snakes? Oh, those are mere babies!");
			s.pause();
			s.out(mizuki.getName() + ": 'You've never had the pleasure of seeing a fully grown snake before have you?!");
			s.pause();
			if(mc.getGender().equals("female")){
				s.out(mizuki.getName() + ": 'Don't worry I will protect you. <looks into your eyes and smiles>");
				mc.getRel_().addGoodDeedAgainst(mizuki.getName());
				s.pause();
				s.out("<" + mizuki.getName() + " is starting to like you>");
			}else{
				s.out(mizuki.getName() + ": 'Don't be such a wimp. <rolls eyes>'");
				mc.getRel_().addBadDeedAgainst(mizuki.getName());
				s.pause();
				s.out("<" + mizuki.getName() + " opinion of you drops>");
			}
			s.pause();

			//RESPONSE
			s.out("Respond to " + mizuki.getName() + "?"	);
			s.out("1. Yes")	;
			s.out("2. No")	;
			s.print(":");
			answer = s.getIntBetween(1,2);
			if(answer == 2){  //no response
				//do nothing continue about your day
				s.out("");
				s.out("--------------------------------------------");
				s.out(mc.getName() + " walks away");
				s.pause();
				answer = 0;
			}else if( answer == 1) {  //choose response
				//move on to next bracket
				String[] response = new String[4];
				response[1] = " 'What do you mean?? There are larger snakes than those??' ";  //curious
				response[2] = " 'Are you calling me a coward???' ";                                //challenging
				response[3] = " 'There are snake scales on your clothes... You summoned them, didn't you?!' ";  //evidence
				s.out("");
				s.out("Choose a response");
				//list reponses
				int resp = 2;
				for (int i = 1; i <= 2; i++) {
					s.out(i + ": " + response[i]);
				}
				if (s.getRandomIntBetween(0, (int) mc.getStats_().getSensing()) >= 2) {
					s.out(3 + ": " + response[3] + "(" + mc.getName() + " sensing skill)");
					resp = 3;
				}else{
					s.out("<failed to sense something>");
				}
				//get response
				s.print(":");
				answer = s.getIntBetween(1, resp);
				s.out("--------------------------------------------");
				s.out(mc.getName() + ": " + response[answer]);
				s.pause();
				if(answer == 1){ 		 //curious
//					response[1] = " 'What do you mean?? There are larger snakes than those??' ";  //curious
					s.out(mizuki.getName() + ": 'Of course there are!  They can grow up to the size of walls of the village!!");
					s.pause();
					if(mc.getRel_().getRelationship(mizuki.getName()) > .5	 ){
						s.out(mizuki.getName() + "'But don't worry.  As long as I'm around, they can't touch you.'");
						s.pause();
						s.out("<relationship is going well>");
						s.pause();
					}else{
						s.out(mizuki.getName() + "'I guess you haven't had very many rigorous missions yet...'");
						s.pause();
						s.out("<relationship is not going very well>");
						s.pause();
					}
				}else if(answer == 2){  //challenging

//					response[2] = " 'Are you calling me a coward???' ";     //challenging

					mc.getRel_().addBadDeedAgainst(mizuki.getName());
					s.out(mizuki.getName() + ": ' Hmmm...I don't know - ARE YOU?' ");
					s.pause();
					s.out("<" + mizuki.getName() + "places his hand on a sword>");
					s.pause();
					s.out("Battle?");
					s.out("1. Yes");
					s.out("2. No");
					s.print(":");
					answer = s.getIntBetween(1,2);
					if(answer == 1){
						s.out("--------------------------------------------");
						s.out(mc.getName() + ": Bring it on JERK!  I'm not afraid of you!");
						s.pause();
						s.out(mizuki.getName() + ": 'Well, well, well. You got guts...But no brains.'");
						s.pause();
						while(mc.getRel_().getRelationship(mizuki.getName()) > .4){
							mc.getRel_().addBadDeedAgainst(mizuki.getName());
						}

						//BATTLE
						winFight = mizukiBattle(mc,mizuki);
						//END BATTLE



					}else {
						s.out("--------------------------------------------");
						s.out(mc.getName() + ":  'I'm not here to fight, okay?'");
						s.pause();
						s.out(mizuki.getName() + ": 'I thought so...' <smirk>");
						s.pause();
					}


				}else if(answer == 3){  //evidence

//					response[3] = " 'There are snake scales on your clothes. You summoned them, didn't you?!' ";  //evidence
					s.out(mizuki.getName() + ": 'Well, well, well....You caught me...'");
					mc.getRel_().addBadDeedAgainst(mizuki.getName());
					mc.getRel_().addBadDeedAgainst(mizuki.getName());
					s.pause();
					s.out("<" + mizuki.getName() + " opinion of you drops>");
					s.pause();
					s.out("<" + mizuki.getName() + "places his hand on a sword>");
					s.pause();
					s.out(mizuki.getName() + ": 'Looks like I'm going to have to END you...'");
					s.pause();

					//BATTLE
					winFight = mizukiBattle(mc,mizuki);
					//END BATTLE
				}
			}//end snake responses

			whySnakes = false; //turn off branch
		}

		if(suspicious == true){

			winFight = mizukiDialogue2(mc,mizuki);

			suspicious = false; // turn off branch
		}

		return winFight;

	}//end Mizuki Dialogue



	public boolean mizukiDialogue2(Character mc, Character mizuki){
		boolean winFight = false;
		if(mc.getRel_().getRelationship(mizuki.getName()) > .5	 ){
			s.out(mizuki.getName() + ": 'Mind your own business, please'");
		}else{
			s.out(mizuki.getName() + ": 'Mind your own business, twerp'");
		}
		String[] response = new String[5];
		response[1] = " 'Fine. Have it your way. I'm out.' ";  //done
		response[2] = " 'I'm just so curious.  You seem like you always have something interesting going on.' ";   //team
		response[3] = " 'Tell me what you're hiding, dammit!' ";  //aggressive
		response[4] = " 'There is a snake trail leading up to you!! You totally summoned them!' ";  //sensing stat
		s.out("");
		s.out("Choose a response");
		//list responses
		int resp = 3;
		for (int i = 1; i <= 3; i++) {
			s.out(i + ": " + response[i]);
		}
		if (s.getRandomIntBetween(0, (int) mc.getStats_().getSensing()) >= 2) {
			s.out(4 + ": " + response[4] + " (" + mc.getName() + " sensing skill)");
			resp = 4;
		}else{
			s.out("<failed to sense something>");
		}
		//get response
		s.print(":");
		answer = s.getIntBetween(1,resp);

		if(answer == 1){
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[1]);
			s.pause();
			s.out(mizuki.getName() + ": 'Trust me, it's better like this.'");
			s.pause();
		}else if(answer == 2){   //'I'm just so curious.  You seem like you always have something interesting going on.'
			//team
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[2]);
			s.pause();

			teamUpMizuki(mc, mizuki);


		}else if(answer == 3){
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[3]);
			s.pause();

			s.out(mizuki.getName() + ": 'Why don't you mind your own business before I MAKE you?'");
			s.out("<" + mizuki.getName() + "places his hand on a sword>");
			s.pause();
			s.out("Battle?");
			s.out("1. Yes");
			s.out("2. No");
			answer = s.getIntBetween(1,2);
			if(answer == 1){
				s.out("--------------------------------------------");
				s.out(mc.getName() + ": Bring it on JERK!  I'm not afraid of you!");
				s.pause();
				s.out(mizuki.getName() + ": 'Well, well, well. You got guts...But no brains.'");
				s.pause();
				while(mc.getRel_().getRelationship(mizuki.getName()) > .4){
					mc.getRel_().addBadDeedAgainst(mizuki.getName());
				}
				//BATTLE
				winFight = mizukiBattle(mc,mizuki);
				//END BATTLE
			}else {
				s.out("--------------------------------------------");
				s.out(mc.getName() + ":  'I'm not here to fight, okay?'");
				s.pause();
				s.out(mizuki.getName() + ": 'I thought so...' <smirk>");
				s.pause();
			}
		}else{  //answer == 4
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[4]);
			s.pause();

			s.out(mizuki.getName() + ": 'Well, well, well....You caught me...'");
			mc.getRel_().addBadDeedAgainst(mizuki.getName());
			mc.getRel_().addBadDeedAgainst(mizuki.getName());
			s.out("<" + mizuki.getName() + " opinion of you drops>");
			s.pause();
			s.out("<" + mizuki.getName() + "places his hand on a sword>");
			s.pause();
			s.out(mizuki.getName() + ": 'Looks like I'm going to have to END you...'");
			s.pause();
			//BATTLE
			winFight = mizukiBattle(mc,mizuki);
			//END BATTLE
		}
		return winFight;
	}//mizukiDialogue2



	public boolean mizukiBattle(Character mc, Character mizuki){
		boolean winFight = false;
		Result fight = new Result();
		Squad s1 = new Squad(mc);
		Squad s2 = new Squad(mizuki);
		Map1 mm = new Map1(mc,4,4);
		try {
			fight = mc.getBattle_().beginSquadBattle(mc, mm, s1, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		winFight = fight.isVictory();
		if(winFight){
			s.out("");
			s.out("");
			s.out("");
			s.out(mizuki.getName() + ": 'cough...cough...'");
			s.pause();
			s.out(mizuki.getName() + ": 'Ok, I admit it. I summoned the snakes...'");
			s.pause();
			s.out(mizuki.getName() + ": 'I needed to create a distraction for my friend.'");
			s.pause();
			s.out(mizuki.getName() + ": 'Just don't bother my friend over there....You'll regret it.'");
			s.pause();
			s.out("<" + mizuki.getName() + " disappears with a substitution jutsu puff of smoke >");
		}else {
			s.out(mizuki.getName() + ": 'Pathetic...'");
		}
		mc.transitionToMap(mainmap,mizuki.getPosition_().getX(),mizuki.getPosition_().getY());

		return winFight;
	}//mizukiBattle


	public boolean teamUpMizuki(Character mc, Character mizuki){
		boolean acceptAlliance = false;
		s.out("");
		s.out(mizuki.getName() + ": 'Hmm.....What types of things do you find interesting?'");
		s.pause();
		mc.getRel_().addGoodDeedAgainst(mizuki.getName());
		s.out("<" + mizuki.getName() + " opinion of you goes up>");
		s.pause();
		s.out("Choose a response");
		String[] response = new String[5];
		response[1] = " 'Learning new jutsu is very interesting' ";  //
		response[2] = " 'I like training and getting stronger no matter what' ";   //
		response[3] = " 'I like helping others and making friends' ";  //
		response[4] = " 'Pain is the only thing that is truly interesting' ";  //
		response[5] = " 'I'm fascinated by wild creatures and their abilities' ";  //
		response[6] = " 'As long as it is within the rules, I like adventure' ";  //
		for (int i = 1; i <= 6; i++) {
			s.out(i + ": " + response[i]);
		}
		//get response
		s.print(":");
		answer = s.getIntBetween(1,6);
		if(answer == 1){
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[1]);
			s.pause();
			s.out(mizuki.getName() + ": 'What type of jutsu do you like?'");
			s.pause();
			s.out("Choose a response");
			s.out("1. lethal jutsu");
			s.out("2. useful jutsu");
			s.print(":");
			answer = s.getIntBetween(1,2);
			if(answer == 1){
				s.out(mizuki.getName() + ": 'I think we can be friends.'");
				s.pause();
				s.out("<" + mizuki.getName() + " opinion of you goes up>");
				mc.getRel_().increaseRelTo(mizuki.getName(),.6);
				mc.getRel_().addGoodDeedAgainst(mizuki.getName());
				acceptAlliance = true;
			}else{
				s.out(mizuki.getName() + ": 'I don't think we can be friends.'");
			}

		}else if(answer ==2){
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[2]);
			s.pause();
			s.out(mizuki.getName() + ": 'Why do you want to be strong?'");
			s.pause();
			s.out("Choose a response");
			s.out("1. get revenge");
			s.out("2. protect others");
			s.print(":");
			answer = s.getIntBetween(1,2);
			if(answer == 1){
				s.out(mizuki.getName() + ": 'I think we can be friends.'");
				s.pause();
				s.out("<" + mizuki.getName() + " opinion of you goes up>");
				mc.getRel_().increaseRelTo(mizuki.getName(),.6);
				mc.getRel_().addGoodDeedAgainst(mizuki.getName());
				acceptAlliance = true;
			}else{
				s.out(mizuki.getName() + ": 'I don't think we can be friends.'");
			}

		}else if(answer ==3){
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[3]);
			s.pause();
			s.out(mizuki.getName() + ": 'I don't think we can be friends.'");
		}else if(answer ==4){
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[4]);
			s.pause();
			s.out(mizuki.getName() + ": 'Pain? Who do you want to hurt?'");
			s.pause();
			s.out("Choose a response");
			s.out("1. people in this village...");
			s.out("2. enemies of our nation!");
			s.print(":");
			answer = s.getIntBetween(1,2);
			if(answer == 1){
				s.out(mizuki.getName() + ": 'I think we can be friends.'");
				s.pause();
				s.out("<" + mizuki.getName() + " opinion of you goes up>");
				mc.getRel_().increaseRelTo(mizuki.getName(),.6);
				mc.getRel_().addGoodDeedAgainst(mizuki.getName());
				acceptAlliance = true;
			}else{
				s.out(mizuki.getName() + ": 'I don't think we can be friends.'");
			}

		}else if(answer ==5){
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[5]);
			s.pause();
			s.out(mizuki.getName() + ": 'How do you feel about snakes?'");
			s.pause();
			s.out("Choose a response");
			s.out("1. I admire their ability to kill");
			s.out("2. Not my favorite creature to be honest...");
			s.print(":");
			answer = s.getIntBetween(1,2);
			if(answer == 1){
				s.out(mizuki.getName() + ": 'I think we can be friends.'");
				s.pause();
				s.out("<" + mizuki.getName() + " opinion of you goes up>");
				mc.getRel_().increaseRelTo(mizuki.getName(),.6);
				mc.getRel_().addGoodDeedAgainst(mizuki.getName());
				acceptAlliance = true;
			}else{
				s.out(mizuki.getName() + ": 'I don't think we can be friends.'");
			}

		}else if(answer ==6){
			s.out("--------------------------------------------");
			s.out(mc.getName() + ": " + response[6]);
			s.pause();
			s.out(mizuki.getName() + ": 'I don't think we can be friends.'");
		}

		if(acceptAlliance==false){
			mc.getRel_().addBadDeedAgainst(mizuki.getName());
		}

		return acceptAlliance;
	}


	public void izumiDialogue(Character mc, Character sakane){




		sakane.setName("Sakane Tsuchigumo");
	}

	public void sakaneDialogue(Character mc, Character sakane){




	}



	public System1 getS() {
		return s;
	}

	public void setS(System1 s) {
		this.s = s;
	}

	public ArrayList<Character> getSnakeList() {
		return snakeList;
	}

	public void setSnakeList(ArrayList<Character> snakeList) {
		this.snakeList = snakeList;
	}



	public int getMizukiApproachCount() {
		return mizukiApproachCount;
	}

	public void setMizukiApproachCount(int mizukiApproachCount) {
		this.mizukiApproachCount = mizukiApproachCount;
	}

	public boolean isMizukiDefeated() {
		return mizukiDefeated;
	}

	public void setMizukiDefeated(boolean mizukiDefeated) {
		this.mizukiDefeated = mizukiDefeated;
	}


	private Map1 mainmap;

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}


	public Map1 getMainmap() {
		return mainmap;
	}

	public void setMainmap(Map1 mainmap) {
		this.mainmap = mainmap;
	}



}//end story1
