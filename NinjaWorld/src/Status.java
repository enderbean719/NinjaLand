
public class Status {
	
	//unmoveable and unconscious
	private boolean isAsleep;
	private boolean inGenjutsu;
	private boolean isKO; 
	//unmoveable
	private boolean isStunned;
	private boolean isDisabled;
	
	//movable
	private boolean isPoisoned;
	private boolean isBleeding;
	private boolean isOnFire;
	private boolean isDrowning;
	private boolean isChoking;
	private boolean handsAreFree;
	
	
	public boolean isConscious()
	{
		return !(isAsleep || inGenjutsu || isKO );
	}
	
	public boolean canMove()
	{
		return !(isAsleep || inGenjutsu || isKO || isStunned || isDisabled );
	}
	
	public boolean canDoJutsu()
	{
		return canMove() && handsAreFree;
	}
	
}
