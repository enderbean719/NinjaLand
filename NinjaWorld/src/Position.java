
public class Position {
	public int x;
	public int y;
	public boolean hidden;
	public boolean invisible;
	public boolean silent;
	public boolean oderless;
	//public boolean 
	
	//only one in this list
	private boolean inWater;
	private boolean inAir;
	private boolean inTree;
	private boolean inBush;
	private boolean onGround;
	private boolean onWater;
	private boolean behindObject;
	private boolean underGround;
	
	void setPositionStatus(String input) {
		setAllFalse();		
		switch (input) {
		case "inWater":
			inWater=true;
			break;
		case "inAir":
			inAir = true;
			break;
		case "inTree":
			inTree = true;
			break;
		case "inBush":
			inBush = true;
			break;
		case "onGround":
			onGround = true;
			break;
		case "onWater":
			onWater = true;
			break;
		case "behindObject":
			behindObject = true;
			break;
		case "underGround":
			underGround = true;
			break;
		default:
			break;
		}
		
	}//end setPositionStatus
	
	void setAllFalse() {
		inWater = false;
		inAir = false;
		inTree = false;
		inBush = false;
		onGround = false;
		onWater = false;
		behindObject = false;

	}//end setAllFalse
	
}//end class
