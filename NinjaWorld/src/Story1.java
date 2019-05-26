import java.io.Serializable;

  
public class Story1 implements Story, Serializable{
 
	private int answer = 0;
	
	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	@Override
	public void start_(Character mc) { 
		//SETH BEGIN HERE
		System1 s = new System1(); 
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
		Character saisu = new Character(true, "Saisu Kamano", 1, "male");
		saisu.getStats_().loadClawed(saisu.getLvl());
		mc.getRel_().newRelationship75(saisu.getName());
		//https://naruto.fandom.com/wiki/Saisu_Kamano
		//someone build loader function inside Character Class file for Saisu's stats
		//print nice character image;
		s.out(saisu.getName() + ": Hey " + mc.getName() ) ;		
		s.out("Do you hear that loud roaring?");
		s.out("1. yeah, whats all the commotion?");
		s.out("2. no, what's up?");
		s.out("3. no, are you hearing things? ");
		answer = s.getIntBetween(1,3);
		if(answer == 1) {
			mc.getRel_().addGoodDeedAgainst(saisu.getName());
			s.out("<" + saisu.getName() + " noticed your positive attitude.>");
			s.out("I was wondering when you would finally notice!");
			s.out("It's the chunin exams!  We're late for the show!");
			s.out("Let's go find some seats in the audience.");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");
		}else if(answer == 2) {
			//no good deed, no bad deed, neutral response
			s.out("Man, you must be deaf or something!");
			s.out("It's the chunin exams!  We're late for the show!");
			s.out("Let's go find some seats in the audience.");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");
		}else {
			s.out("<" + saisu.getName() + " noticed your negative attitude.>");
			mc.getRel_().addBadDeedAgainst(saisu.getName());
			s.out("What?? You're kidding, right?");
			s.out("It's the chunin exams!  We're late for the show!");
			s.out("Let's go find some seats in the audience.");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");			
		}
		answer = s.getIntBetween(1,2);
		Map1 mainmap = new Map1(mc,10,5);  //max x = 9, max y = 4
		mc.setMap_(mainmap);

		//snake
		Character rat = new Character(true,"snake",1, "male");
		rat.getStats_().loadClawed(rat.getLvl());
		rat.getAbilities_().getaList().add(new Ability() );
		rat.getAbilities_().getaList().get(0).loadScratch();
		mainmap.placeCreatureAndRemove(rat,1,2);

		//arena
		Character arenaBuilding = new Character();
		arenaBuilding.setName("Arena");
		mainmap.placeCreatureAndRemove(arenaBuilding,5,2);
		if(true) {  //answer == 1
			//mainmap.printMapOfNames();
			mainmap.printBattleMap();
			while(e.mcArrivedAtChar(arenaBuilding)==false) {

				if(e.mcArrivedAtChar(rat)==true){
					Map1 ratBattleMap = new Map1(mc,3,3);
					ratBattleMap.placeCreatureAndRemove(rat, 2, 2);
					Squad s1 = new Squad(mc);
					Squad s2 = new Squad(rat);
					try {
						mc.getBattle_().beginSquadBattle(ratBattleMap, s1, s2);
					} catch (Exception ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
				}//end rat battle

				mc.getCommands_().runCommands();

			}//while loop

		}
		
		//Yuki Minazuki = Itachi sensei
		//Kakashi is jonin at this time - maybe a sensei
		//boolean isAI, String nameInput, int lvlInput, String gender
		Character ibiki = new Character(true,"Ibiki Morino", 25, "male");
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
	
	
}
