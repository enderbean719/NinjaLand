
public class Story4 implements Story {

	@Override
	public void start_(Character mc) {
		// TESTING BATTLES
		System1 s = new System1();
		s.out("Story4 begin");
		

		Character testC = new Character();
		testC.name = "Naruto";
		testC.stats_.loadCreatureStats("clawed", 4);
		testC.printStatsFormal();
		
		Creature monster = new Creature();
		monster.name = "Evil Crane";
		monster.stats_.loadCreatureStats("flying", 2);
		monster.printStatsFormal();
		
		
		Map mm = new Map(testC,5,5);
		//mm.getArea(1, 1);
		mm.placeCreatureAndRemove(monster, 4, 4);
		mm.printMapOfIds();
		s.out("");
		mm.printMapOfLabels();
		s.out("");
		mm.printMapOfNames();
		s.out("");

		
		
		
		//s.out("Enter integer to quit");
		//s.getInt();
		System.exit(0);
	}
	

}//end Story4
