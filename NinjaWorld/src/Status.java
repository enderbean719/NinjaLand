
public class Status {

	// unmoveable and unconscious
	private boolean isAsleep			=false;
	private boolean inGenjutsu			=false;
	private boolean isKO				=false;
	// unmoveable
	private boolean isStunned			=false;
	private boolean isDisabled			=false;
	private boolean gatheringChakra		=false;

	// movable bad
	private boolean isPoisoned			=false;
	private boolean beingTortured		=false;
	private boolean isBleeding			=false;
	private boolean isOnFire			=false;
	private boolean isDrowning			=false;
	private boolean isChoking			=false;
	//movable good
	private boolean oneHandFree				=true;
	private boolean twoHandsFree			=true; 
	private boolean canFly				=false;
	private boolean canGoUnderground	=false;
	private boolean canSwim					=true;
	private boolean canWalkOnWater		=false;
	private boolean canClimbWalls		=false;
	

	//sensing
	private boolean isAware				=false;  //aware of enemy nearby
	private boolean canHear					=true;
	private boolean canSmell				=true;
	private boolean canSee					=true;
	private boolean canSense				=true;
	
	// battle intent
	// battle
	
	private boolean retreatAttempt		=false;
	private boolean retreatSuccess		=false;
	private boolean chasing				=false;
	private boolean haveMercy			=false;
	private boolean pauseFighting		=false;
	private boolean hasBeenTargeted		=false;

	
	
	public Status() {  //defaults
//		isAsleep 		= false;
//		inGenjutsu 		= false;
//		isKO			= false;
//		isStunned 		= false;
//		isDisabled		= false;
//		gatheringChakra = false;
//		isPoisoned 		= false;
//		beingTortured 	= false;
//		isBleeding 		= false;
//		isOnFire 		= false;
//		isDrowning 		= false;
//		isChoking 		= false;
//		oneHandFree 	= true; //
//		twoHandsFree 	= true;//
//		canFly 			= false;
//		isAware 		= false;
//		canHear 		= true;//
//		canSmell 		= true;//
//		canSee 			= true;//
//		canSense 		= true;//
//		retreatAttempt  = false;
//		retreatSuccess  = false;
//		chasing 		= false;
//		haveMercy 		= false;
//		pauseFighting 	= false;
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

	public boolean isCanFly() {
		return canFly;
	}


	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}



	public boolean isCanGoUnderground() {
		return canGoUnderground;
	}


	public void setCanGoUnderground(boolean canGoUnderground) {
		this.canGoUnderground = canGoUnderground;
	}


	public boolean isCanSwim() {
		return canSwim;
	}


	public void setCanSwim(boolean canSwim) {
		this.canSwim = canSwim;
	}


	public boolean isCanWalkOnWater() {
		return canWalkOnWater;
	}


	public void setCanWalkOnWater(boolean canWalkOnWater) {
		this.canWalkOnWater = canWalkOnWater;
	}


	public boolean isCanClimbWalls() {
		return canClimbWalls;
	}


	public void setCanClimbWalls(boolean canClimbWalls) {
		this.canClimbWalls = canClimbWalls;
	}


}//end Status
