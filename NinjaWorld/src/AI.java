import java.io.Serializable;

public class AI  implements Serializable{
	private Character c;
	private boolean isAI = true;	
	private boolean canFight = true;
	private int aggressive;  //will initiate  1 - 10
	private int cowardly;  //will retreat  1 - 10
	

	public AI() {
		
	}
	
	public AI(Character c) {
		this.c = c;
	}
	
	
	
	public String getRandomBattleMoveDirection(){
		System1 s = new System1();
		int r = s.getRandomIntBetween(1,8);
		switch (r){
			case 1:
				return "n";
			case 2:
				return "s";
			case 3:
				return "e";
			case 4:
				return "w";
			case 5:
				return "nw";
			case 6:
				return "ne";
			case 7:
				return "sw";
			case 8:
				return "se";
		}
		return "";
	}

	
	public boolean isAI() {
		return isAI;
	}

	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}

	public Character getC() {
		return c;
	}

	public void setC(Character c) {
		this.c = c;
	}

	public boolean isCanFight() {
		return canFight;
	}

	public void setCanFight(boolean canFight) {
		this.canFight = canFight;
	}

	public int getAggressive() {
		return aggressive;
	}

	public void setAggressive(int aggressive) {
		this.aggressive = aggressive;
	}

	public int getCowardly() {
		return cowardly;
	}

	public void setCowardly(int cowardly) {
		this.cowardly = cowardly;
	}
	
}//end AI
