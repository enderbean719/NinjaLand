
public class Story4 implements Story {

	@Override
	public void start_(Character mc) {
		// TESTING BATTLES
		System1 s = new System1();
		s.out("Story4 begin");
		
		Character testC = new Character();
		testC.stats_.loadCreatureStats("clawed", 4);
		testC.printStatsFormal();
		
		Creature monster = new Creature();
		monster.stats_.loadCreatureStats("flying", 2);
		monster.printStatsFormal();
		
		s.out("Enter integer to quit");
		s.getInt();
		System.exit(0);
	}

}//end Story4
