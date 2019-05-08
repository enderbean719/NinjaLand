
public class Position {
	private int x;
	private int y;
	private boolean hidden;
	private boolean invisible;
	private boolean silent;
	private boolean oderless;
	//public boolean 
	
	
	
	//only one in this list
	private boolean inWater; 	//travel
	private boolean onWater;  //water
	private boolean inSky;		//travel
	private boolean inTree;	 //travel
	private boolean onLand;  //travel
	private boolean underGround;	//travel	
	
	
	//hiding status
	private boolean inBush;  //land 
	private boolean behindObject; //land
	
	

	public void setPositionStatus(String input) {
		setAllFalse();		
		switch (input) {
		case "inWater":
			inWater=true;
			break;
		case "inSky":
			inSky = true;
			break;
		case "inTree":
			inTree = true;
			break;
		case "inBush":
			inBush = true;
			break;
		case "onLand":
			onLand = true;
			break;
		case "onWater":
			onWater = true;
			break; 
		case "underGround":
			underGround = true;
			break;
		default:
			break;
		}
		
	}//end setPositionStatus
	
	public boolean at(int x, int y) {
		if(this.x == x && this.y == y) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setAllFalse() {
		inWater = false;
		inSky = false;
		inTree = false;
		inBush = false;
		onLand = false;
		onWater = false; 
		underGround = false;

	}//end setAllFalse
	
	public String getTravelType() {
		 //sky, land, water, tree, earth, none
		if(this.inSky == true) {
			return "sky";
		}else if(this.onLand == true) {
			return "land";
		}else if(this.inWater == true || this.onWater == true) {
			return "water";
		}else if(this.inTree == true) {
			return "tree";
		}else if(this.underGround == true) {
			return "earth";
		}else {
			return "none";
		} 
	}
	
	
	public String getEnviPosition() {
		 //sky, land, water, tree, earth, none
		if(this.inSky == true) {
			return "inSky";
		}else if(this.onLand == true) {
			return "onLand";
		}else if(this.inWater == true ) {
			return "inWater";
		}else if(this.onWater == true) {
			return "onWater";
		}else if(this.inTree == true) {
			return "inTree";
		}else if(this.underGround == true) {
			return "underGround";
		}else {
			return "none";
		} 
	}
	
	public String getFormalEnviPosition() {
		 //sky, land, water, tree, earth, none
		if(this.inSky == true) {
			return " is in the sky";
		}else if(this.onLand == true) {
			return " is on land";
		}else if(this.inWater == true ) {
			return " is in the water";
		}else if(this.onWater == true) {
			return " is on the water";
		}else if(this.inTree == true) {
			return " is in a tree";
		}else if(this.underGround == true) {
			return " is underground";
		}else {
			return "none";
		} 
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isInvisible() {
		return invisible;
	}

	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}

	public boolean isSilent() {
		return silent;
	}

	public void setSilent(boolean silent) {
		this.silent = silent;
	}

	public boolean isOderless() {
		return oderless;
	}

	public void setOderless(boolean oderless) {
		this.oderless = oderless;
	}

	public boolean isInWater() {
		return inWater;
	}

	public void setInWater(boolean inWater) {
		if(inWater == true) {
			setPositionStatus("inWater");
		}else{
			this.inWater = inWater;
		}		
	}

	public boolean isInSky() {
		return inSky;
	}

	public void setInSky(boolean inSky) {
		if(inSky == true) {
			setPositionStatus("inSky");
		}else{
			this.inSky = inSky;
		}		
	}

	public boolean isInTree() {
		return inTree;
	}

	public void setInTree(boolean inTree) {
		if(inTree == true) {
			setPositionStatus("inTree");
		}else{
			this.inTree = inTree;
		}	
	}

	public boolean isOnLand() {
		return onLand;
	}

	public void setOnLand(boolean onLand) {
		if(onLand == true) {
			setPositionStatus("onLand");
		}else{
			this.onLand = onLand;
		}	
	}

	public boolean isInBush() {
		return inBush;
	}

	public void setInBush(boolean inBush) {
		if(inBush == true) {
			setPositionStatus("inBush");
		}else{
			this.inBush = inBush;
		}	
	}
 

	public boolean isOnWater() {
		return onWater;
	}

	public void setOnWater(boolean onWater) {
		if(onWater == true) {
			setPositionStatus("onWater");
		}else{
			this.onWater = onWater;
		}	
	}

	public boolean isBehindObject() {
		return behindObject;
	}

	public void setBehindObject(boolean behindObject) {
		this.behindObject = behindObject;
	}

	public boolean isUnderGround() {
		return underGround;
	}

	public void setUnderGround(boolean underGround) {
		if(underGround == true) {
			setPositionStatus("underGround");
		}else{
			this.underGround = underGround;
		}	
	}
	
}//end class
