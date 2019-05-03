
public class AI {
	
	public boolean isAI = true;
	public Creature c;
	public boolean canFight;
	public int aggressive;  //will initiate  1 - 10
	public int cowardly;  //will retreat  1 - 10
	
	
	public AI() {
		
	}
	
	public AI(Creature c) {
		this.c = c;
	}
	
}//end AI
