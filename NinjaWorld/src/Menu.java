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
		
	}
	
}
