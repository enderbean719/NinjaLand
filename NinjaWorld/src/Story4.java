import java.io.Serializable;

  
public class Story4 implements Story, Serializable{

	private System1 s = new System1();
	
	@Override
	public void start_(Character mc) {
		// TESTING BATTLES
		System1 s = new System1();

		
		
		Character naruto = new Character(false,"Naruto",5,"male", "clawed");
		naruto.getAbilities_().getaList().add(new Ability() );
		naruto.getAbilities_().getaList().add(new Ability() );
		naruto.getAbilities_().getaList().add(new Ability() );
		naruto.getAbilities_().getaList().add(new Ability() );
		naruto.getAbilities_().getaList().add(new Ability() );
		naruto.getAbilities_().getaList().add(new Ability() );
		naruto.getAbilities_().getaList().get(0).loadChargingPunch();
		naruto.getAbilities_().getaList().get(1).loadKunaiThrow();
		naruto.getAbilities_().getaList().get(2).loadShadowParalysis();
		naruto.getAbilities_().getaList().get(3).loadFireSpray();
		naruto.getAbilities_().getaList().get(4).loadWaterBlast();
		naruto.getAbilities_().getaList().get(5).loadRasengan();

		s.out("naruto AI = " + naruto.getAI_().isAI());
		
		Character sasuke = new Character(true,"Sasuke",2,"male","elemental");
		sasuke.getAbilities_().getaList().add(new Ability() );
		sasuke.getAbilities_().getaList().add(new Ability() );
		sasuke.getAbilities_().getaList().add(new Ability() );
		sasuke.getAbilities_().getaList().add(new Ability() );
		sasuke.getAbilities_().getaList().add(new Ability() );
		sasuke.getAbilities_().getaList().add(new Ability() );
		sasuke.getAbilities_().getaList().get(0).loadChargingPunch();
		sasuke.getAbilities_().getaList().get(1).loadKunaiThrow();
		sasuke.getAbilities_().getaList().get(2).loadShadowParalysis();
		sasuke.getAbilities_().getaList().get(3).loadFireSpray();
		sasuke.getAbilities_().getaList().get(4).loadWaterBlast();
		sasuke.getAbilities_().getaList().get(5).loadRasengan();
		
		s.out("sasuke AI = " + sasuke.getAI_().isAI());
		 
		
		Map1 mm = new Map1(naruto,5,5); 
		mm.placeCreatureAndRemove(sasuke, 4, 4); 
		s.out(""); 
		s.out(""); 
		s.out("");
 
		Squad s1 = new Squad(naruto);
		Squad s2 = new Squad(sasuke);
		try {
			naruto.getBattle_().beginSquadBattle(naruto, mm, s1, s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		s.out("end game");
		s.pause();
		
		//s.out("Enter integer to quit");
		//s.getInt();
		System.exit(0);
	}
	

}//end Story4
