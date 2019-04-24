
public class Status {

	// unmoveable and unconscious
	private boolean isAsleep;
	private boolean inGenjutsu;
	private boolean isKO;
	// unmoveable
	private boolean isStunned;
	private boolean isDisabled;
	private boolean gatheringChakra;

	// movable
	private boolean isPoisoned;
	private boolean beingTortured;
	private boolean isBleeding;
	private boolean isOnFire;
	private boolean isDrowning;
	private boolean isChoking;
	private boolean handsAreFree;

	// battle intent
	// battle
	public boolean retreatAttempt;
	public boolean retreatSuccess;
	public boolean chasing;
	public boolean haveMercy;
	public boolean pauseFighting;

	public boolean isConscious() {
		return !(isAsleep || inGenjutsu || isKO);
	}

	public boolean canMove() {
		return !(isAsleep || inGenjutsu || isKO || isStunned || isDisabled || gatheringChakra);
	}

	public boolean canDoJutsu() {
		return canMove() && handsAreFree;
	}

}
