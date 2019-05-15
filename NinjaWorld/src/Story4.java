
public class Story4 implements Story {

	private System1 s = new System1();
	
	@Override
	public void start_(Character mc) {
		// TESTING BATTLES
		System1 s = new System1();
 	
		s.out("hi");
		s.pause();
		s.out("done");
		
		
		Character naruto = new Character(false,"Naruto",2,"male");
		naruto.getStats_().loadCreatureStats("clawed", 4);
		naruto.getAbilities_().getaList().add(new Ability() );
		naruto.getAbilities_().getaList().add(new Ability() );
		naruto.getAbilities_().getaList().get(0).loadChargingPunch();
		naruto.getAbilities_().getaList().get(1).loadKunaiThrow();

		s.out("naruto AI = " + naruto.getAI_().isAI());
		
		Character sasuke = new Character(false,"Sasuke",2,"male");
		sasuke.getStats_().loadCreatureStats("flying", 2);
		sasuke.getAbilities_().getaList().add(new Ability());
		sasuke.getAbilities_().getaList().add(new Ability());
		sasuke.getAbilities_().getaList().get(0).loadChargingPunch();
		sasuke.getAbilities_().getaList().get(1).loadKunaiThrow();
		
		s.out("sasuke AI = " + sasuke.getAI_().isAI());
		 
		
		Map1 mm = new Map1(naruto,5,5); 
		mm.placeCreatureAndRemove(sasuke, 4, 4); 
		s.out(""); 
		s.out(""); 
		s.out("");
 
		Squad s1 = new Squad(naruto);
		Squad s2 = new Squad(sasuke);
		try {
			naruto.getBattle_().beginSquadBattle(mm, s1, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		s.out("end game");
		s.getInt();
		
		//s.out("Enter integer to quit");
		//s.getInt();
		System.exit(0);
	}
	

}//end Story4
