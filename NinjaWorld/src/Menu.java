import java.io.Serializable;



public class Menu  implements Serializable{

	System1 s = new System1();
	int answer = 0;
	String answerS = "";
	Character mc = new Character();
	
	
	public void start() {
		mc.getAI_().setAI(false);
		printTitle();
		s.out("1. New Game");
		s.out("2. Load Game");
		s.out("3. Multiplayer Match");
		s.out("4. Quit Game");
		s.out("5. Test Battle");
		s.out("6. Test Missions");
		answer = s.getIntBetween(1,6); 
		//s.out("you chose " + answer);
		if (answer == 1){
			newCharacter();
		}else if(answer == 2) {
			s.out("Load game under construction");
		}else if(answer == 3) {
			s.out("Multiplayer match under construction");
		}else if(answer == 5) {
			Story4 st4 = new Story4();
			st4.start_(mc);
		}else if(answer == 6){
			s.out("THE STORY CONTINUES....");
			s.out("6 months later...");
			s.out("");
			s.out("Ibiki" + ": " + mc.getName() + ", its time for you to begin a mission.");
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
		}else {
			s.out("Farewell ninja!");
			System.exit(0);
		}
		
	}

	public void printTitle() {
		//print game title image
		String[] str = new String[10];
		str[0] = "ooooo      ooo  o8o                  o8o                ooooo                                    .o8  ";
		str[1] = "`888b.     `8'  `\"'                  `\"'                `888'                                   \"888 ";
		str[2] = " 8 `88b.    8  oooo  ooo. .oo.      oooo  .oooo.         888          .oooo.   ooo. .oo.    .oooo888 ";
		str[3] = " 8   `88b.  8  `888  `888P\"Y88b     `888 `P  )88b        888         `P  )88b  `888P\"Y88b  d88' `888 ";
		str[4] = " 8     `88b.8   888   888   888      888  .oP\"888        888          .oP\"888   888   888  888   888 ";
		str[5] = " 8       `888   888   888   888      888 d8(  888        888       o d8(  888   888   888  888   888 ";
		str[6] = "o8o        `8  o888o o888o o888o     888 `Y888\"\"8o      o888ooooood8 `Y888\"\"8o o888o o888o `Y8bod88P\" ";
		str[7] = "                                     888                                                             ";
		str[8] = "                                 .o. 88P                                                            ";
		str[9] = "                                 `Y888P                                                             ";
		
		for(int i = 0; i<10; i++) {
			s.out(str[i]);
		}
		
	}//end printTitle
	
	
	public void newCharacter() {
		//print image of teacher ninja
		s.out("What is your name, ninja?");
		answerS = s.getWord();
		mc.setName(answerS);
		s.out("Welcome " + mc.getName());
		s.out("What do you identify as?");
		s.out("1. Male");
		s.out("2. Female");
		answer = s.getIntBetween(1,2);
		if (answer == 1) {
			mc.setGender("male");
			mc.setImage(Images.getImage("maleNinja"));
			mc.printImage();
			s.out("Handsome guy!");
		}else {
			mc.setGender("female");
		} 
		s.out("Do you know what clan you are from?");
		s.out("1. Yes, of course.");
		s.out("2. I'm not sure. My parents are dead.");
		answer = s.getIntBetween(1,2);
		if (answer == 1) {
			s.out("What clan are you from?");
			answerS = s.getWord();
			mc.setClan(answerS);
			s.out("Oh!! The " + mc.getClan() + "!!! \nI see... We will expect great things from you.");
		}else {
			s.out("I see. Your clan is unknown.  \nIt will be interesting to watch your development.");
			mc.setClan("Unknown");
		}

		//default abilities
		mc.getAbilities_().getaList().add(new Ability() );
		mc.getAbilities_().getaList().add(new Ability() );
		mc.getAbilities_().getaList().get(0).loadChargingPunch();
		mc.getAbilities_().getaList().get(1).loadKunaiThrow();

		mc.levelUp();
		//add custom abilities based on Clan
		//add abilities based on leveling up
		
		
	//	https://naruto.fandom.com/wiki/Clans
//		s.out("1. Nara");   //shadows
//		s.out("1. Uchiha");  //sasuke
//		s.out("1. Uzumaki");  //naruto
//		s.out("1. Hyuga");   //neji
//		s.out("1. Aburame");  //bugs
//		s.out("1. Sarutobi"); //3rd kage
//		s.out("1. Akimichi ");  //choji
//		s.out("1. Inuzuka  ");  //dog boy
//		s.out("1. I'm not sure");
//		s.out("1. Akimichi ");
		//personality test to determine family?
		//randomness for clan?
		
		
	}//end newCharacter
	 

	public Character getMainCharacter() {
		return this.mc;
	}
	
}//end Menu
