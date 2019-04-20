
public class NinjaLand {

	
	
	public static void main(String[] args) {
		// BEGIN GAME
		System1 s = new System1();
		Menu mainMenu = new Menu();
		mainMenu.start(); 
		Character mc = new Character();
		mc = mainMenu.getMainCharacter();
		
		Story1 st1 = new Story1();
		st1.start_(mc);
		
//		Story2 st2 = new Story2();
//		st2.start_(mc);
//		
//		Story3 st3 = new Story3();
//		st3.start_(mc);
		
		//Story4 st4 = new Story4();
		//st4.start_(mc);
		
	}
	
	

}
