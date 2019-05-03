
public class Story4 implements Story {

	@Override
	public void start_(Character mc) {
		// TESTING BATTLES
		System1 s = new System1();
		s.out("Story4 begin");
		
		
		Character testC = new Character();
		testC.name = "Naruto";
		testC.stats_.loadCreatureStats("clawed", 4);
		testC.abilities_.aList.add(new Ability() );
		testC.abilities_.aList.add(new Ability() );
		testC.abilities_.aList.get(0).loadChargingPunch();
		testC.abilities_.aList.get(1).loadKunaiThrow();
		testC.printStatsFormal();
		
		s.getInt();
		
		Creature monster = new Creature();
		monster.name = "Evil Crane";
		monster.stats_.loadCreatureStats("flying", 2);
		monster.abilities_.aList.add(new Ability());
		monster.abilities_.aList.add(new Ability());
		monster.abilities_.aList.get(0).loadChargingPunch();
		monster.abilities_.aList.get(1).loadKunaiThrow();
		monster.printStatsFormal();
		
		

		s.getInt();
		testC.abilities_.showList();
		monster.abilities_.showList();
		s.getInt();
		
		Map mm = new Map(testC,5,5);
		//mm.getArea(1, 1);
		mm.placeCreatureAndRemove(monster, 4, 4);
		mm.printMapOfIds();
		s.out("");
		mm.printMapOfLabels();
		s.out("");
		mm.printMapOfNames();
		s.out("");
 

		s.getInt();
		
		//s.out("Enter integer to quit");
		//s.getInt();
		System.exit(0);
	}
	

}//end Story4
