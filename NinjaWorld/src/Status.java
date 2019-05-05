
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
	private boolean oneHandFree;
	private boolean twoHandsFree; 
	
	//sensing
	private boolean isAware;  //aware of enemy nearby
	private boolean canHear;
	private boolean canSmell;
	private boolean canSee;
	private boolean canSense;
	
	// battle intent
	// battle
	
	private boolean retreatAttempt;
	private boolean retreatSuccess;
	private boolean chasing;
	private boolean haveMercy;
	private boolean pauseFighting;
	private boolean hasBeenTargeted;//?

	
	
	public Status() {  //defaults
		isAsleep 		= false;
		inGenjutsu 		= false;
		isKO			= false;
		isStunned 		= false;
		isDisabled		= false;
		gatheringChakra = false;
		isPoisoned 		= false;
		beingTortured 	= false;
		isBleeding 		= false;
		isOnFire 		= false;
		isDrowning 		= false;
		isChoking 		= false;
		oneHandFree 	= true;
		twoHandsFree 	= true;
		isAware 		= false;
		canHear 		= true;
		canSmell 		= true;
		canSee 			= true;
		canSense 		= true;
		retreatAttempt  = false;
		retreatSuccess  = false;
		chasing 		= false;
		haveMercy 		= false;
		pauseFighting 	= false;
	}
	
	
	public boolean isConscious() {
		return !(isAsleep || inGenjutsu || isKO);
	}

	public boolean canMove() {
		return !(isAsleep || inGenjutsu || isKO || isStunned || isDisabled || gatheringChakra);
	}

	public boolean canDoJutsu() {
		return canMove() && twoHandsFree;
	}
	
	
	
	
	
	
	public boolean isAsleep() {
		return isAsleep;
	}


	public void setAsleep(boolean isAsleep) {
		this.isAsleep = isAsleep;
	}


	public boolean isInGenjutsu() {
		return inGenjutsu;
	}


	public void setInGenjutsu(boolean inGenjutsu) {
		this.inGenjutsu = inGenjutsu;
	}


	public boolean isKO() {
		return isKO;
	}


	public void setKO(boolean isKO) {
		this.isKO = isKO;
	}


	public boolean isStunned() {
		return isStunned;
	}


	public void setStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}


	public boolean isDisabled() {
		return isDisabled;
	}


	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}


	public boolean isGatheringChakra() {
		return gatheringChakra;
	}


	public void setGatheringChakra(boolean gatheringChakra) {
		this.gatheringChakra = gatheringChakra;
	}


	public boolean isPoisoned() {
		return isPoisoned;
	}


	public void setPoisoned(boolean isPoisoned) {
		this.isPoisoned = isPoisoned;
	}


	public boolean isBeingTortured() {
		return beingTortured;
	}


	public void setBeingTortured(boolean beingTortured) {
		this.beingTortured = beingTortured;
	}


	public boolean isBleeding() {
		return isBleeding;
	}


	public void setBleeding(boolean isBleeding) {
		this.isBleeding = isBleeding;
	}


	public boolean isOnFire() {
		return isOnFire;
	}


	public void setOnFire(boolean isOnFire) {
		this.isOnFire = isOnFire;
	}


	public boolean isDrowning() {
		return isDrowning;
	}


	public void setDrowning(boolean isDrowning) {
		this.isDrowning = isDrowning;
	}


	public boolean isChoking() {
		return isChoking;
	}


	public void setChoking(boolean isChoking) {
		this.isChoking = isChoking;
	}


	public boolean isOneHandFree() {
		return oneHandFree;
	}


	public void setOneHandFree(boolean oneHandFree) {
		this.oneHandFree = oneHandFree;
	}


	public boolean isTwoHandsFree() {
		return twoHandsFree;
	}


	public void setTwoHandsFree(boolean twoHandsFree) {
		this.twoHandsFree = twoHandsFree;
	}


	public boolean isAware() {
		return isAware;
	}


	public void setAware(boolean isAware) {
		this.isAware = isAware;
	}


	public boolean isCanHear() {
		return canHear;
	}


	public void setCanHear(boolean canHear) {
		this.canHear = canHear;
	}


	public boolean isCanSmell() {
		return canSmell;
	}


	public void setCanSmell(boolean canSmell) {
		this.canSmell = canSmell;
	}


	public boolean isCanSee() {
		return canSee;
	}


	public void setCanSee(boolean canSee) {
		this.canSee = canSee;
	}


	public boolean isCanSense() {
		return canSense;
	}


	public void setCanSense(boolean canSense) {
		this.canSense = canSense;
	}


	public boolean isRetreatAttempt() {
		return retreatAttempt;
	}


	public void setRetreatAttempt(boolean retreatAttempt) {
		this.retreatAttempt = retreatAttempt;
	}


	public boolean isRetreatSuccess() {
		return retreatSuccess;
	}


	public void setRetreatSuccess(boolean retreatSuccess) {
		this.retreatSuccess = retreatSuccess;
	}


	public boolean isChasing() {
		return chasing;
	}


	public void setChasing(boolean chasing) {
		this.chasing = chasing;
	}


	public boolean isHaveMercy() {
		return haveMercy;
	}


	public void setHaveMercy(boolean haveMercy) {
		this.haveMercy = haveMercy;
	}


	public boolean isPauseFighting() {
		return pauseFighting;
	}


	public void setPauseFighting(boolean pauseFighting) {
		this.pauseFighting = pauseFighting;
	}


	public boolean isHasBeenTargeted() {
		return hasBeenTargeted;
	}


	public void setHasBeenTargeted(boolean hasBeenTargeted) {
		this.hasBeenTargeted = hasBeenTargeted;
	}
	
	

}//end Status
