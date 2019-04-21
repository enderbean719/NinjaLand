
public class Battle {

	Battle(){
		
	}
	
	public Result begin1vCreature(Character mc, Creature monster){
		Result result = new Result();
		
		while(continueFight1vCreature(mc, monster) == true) {
			
		}
		
		
		return result;
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
