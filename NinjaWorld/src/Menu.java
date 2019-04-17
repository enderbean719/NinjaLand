import java.io.IOException;

public class Menu {

	System1 s = new System1();
	int answer = 0;
	String answerS = "";
	Character mc = new Character();
	
	public void start() {
		printTitle();
		s.out("1. New Game");
		s.out("2. Load Game");
		s.out("3. Quit Game"); 
		answer = s.getInt(); 
		s.out("you chose " + answer);
		if (answer == 1){
			newCharacter();
		}
	}

	public void printTitle() {
		s.out("title");
	}
	
	public void newCharacter() {

		s.out("What is your name, ninja?");
		answerS = s.getWord();
		mc.name_ = answerS;
		s.out("Welcome " + mc.name_);
		s.out("What do you identify as?");
		s.out("1. Male");
		s.out("2. Female");
		answer = s.getInt();
		if (answer == 1) {
			mc.gender = "male";
		}else {
			mc.gender = "female";
		} 
		s.out("Do you know what clan you are from?");
		s.out("1. Yes, of course.");
		s.out("2. I'm not sure. My parents are dead.");
		answer = s.getInt();
		if (answer == 1) {
			s.out("What clan are you from?");
			answerS = s.getWord();
			mc.clan = answerS;
		}else {
			s.out("I see. Your clan is unknown.");
			mc.clan = "unknown";
		}
		
		mc.levelUp();
				 
		
		
		
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
	
	
	
	
}//end Menu
