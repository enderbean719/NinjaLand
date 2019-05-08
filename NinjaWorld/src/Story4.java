
public class Story4 implements Story {

	private System1 s = new System1();
	
	@Override
	public void start_(Character mc) {
		// TESTING BATTLES
		System1 s = new System1();
		s.out("Story4 begin");
		
	
		Character testC = new Character(false);
		testC.name = "Naruto";
		testC.stats_.loadCreatureStats("clawed", 4);
		testC.abilities_.aList.add(new Ability() );
		testC.abilities_.aList.add(new Ability() );
		testC.abilities_.aList.get(0).loadChargingPunch();
		testC.abilities_.aList.get(1).loadKunaiThrow();
		//testC.printStatsFormal();
		
		//s.getInt();
		
		Character monster = new Character(false);
		monster.name = "Evil Crane";
		monster.stats_.loadCreatureStats("flying", 2);
		monster.abilities_.aList.add(new Ability());
		monster.abilities_.aList.add(new Ability());
		monster.abilities_.aList.get(0).loadChargingPunch();
		monster.abilities_.aList.get(1).loadKunaiThrow();
		//monster.printStatsFormal();
		
		

		//s.getInt();
		testC.abilities_.showList();
		monster.abilities_.showList();
		//s.getInt();
		
		Map1 mm = new Map1(testC,5,5);
		//mm.getArea(1, 1);
		mm.placeCreatureAndRemove(monster, 4, 4);
		mm.printMapOfIds();
		s.out("");
		mm.printMapOfLabels();
		s.out("");
		mm.printMapOfNames();
		s.out("");
 
		Squad s1 = new Squad(testC);
		Squad s2 = new Squad(monster);
		try {
			testC.battle_.beginSquadBattle(mm, s1, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		s.getInt();
		
		//s.out("Enter integer to quit");
		//s.getInt();
		System.exit(0);
	}
	

}//end Story4
