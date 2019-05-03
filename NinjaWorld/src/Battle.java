
public class Battle {

	Squad s1;
	Squad s2;
	
	public Battle(){
		
	}
	
	public Battle(Squad s1, Squad s2){
		this.s1 = s1;
		this.s2 = s2;
	}
	
	public Result begin1vCreature(Character mc, Creature monster){
		Result result = new Result();
		Map basicMap = new Map(3,3);
		mc.map_ = basicMap;
		mc.map_.printMapOfNames();
		boolean mcFirst = mcGoesFirst( mc,  monster);
		if(mcFirst) {
			 mc.battle_.attack();
		}else {
			
		}
			 
		while(continueFight1vCreature(mc, monster) == true) {
			//see who goes first
			 
		}
		
		
		return result;
	}
	
	public void attack() {
		//give mc list of attack options
	}
	
	public boolean mcGoesFirst(Character mc, Creature monster) {
		double mcSpeed = mc.stats_.speed;
		double cSpeed = monster.stats_.speed;
		double mcRandom = Math.random();
		double cRandom = Math.random();
		mcRandom = (mcRandom * mcSpeed) + mcSpeed/2;
		cRandom = (cRandom * cSpeed) + cSpeed/2;
		return (mcRandom > cRandom);
	}
	
	
	public Result begin1v1(Character mc, Character enemy){
		Result result = new Result();
		
		
		
		return result;
	}
	
	public Result beginSquadBattle(){		
		Result result = new Result();
		
		
		
		return result;
	}
	
	
	private boolean continueFight1vCreature(Character mc, Creature monster) {
		boolean cont = true;
		if(mc.stats_.currentHP <= 0 || monster.stats_.currentHP <= 0) {
			cont = false;
		} 
		if(mc.status_.pauseFighting == true ||  monster.status_.pauseFighting == true) {
			cont = false;
		}
		if(mc.status_.retreatSuccess == true ||  monster.status_.retreatSuccess == true) {
			cont = false;
		}
		
		return cont;
	}//end continueFight1vCreature
	
	
}//end Battle
