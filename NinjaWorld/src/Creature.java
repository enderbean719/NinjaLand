
public class Creature {

	 public String name = "";
	 public int lvl = 0 ;
	 public Stats stats_ = new Stats();
	 public Abilities abilities_ = new Abilities();
	 public Position position_ = new Position();
	 public Status status_ = new Status();
	 public AI AI_ = new AI();
	 
	 System1 s = new System1();
	 
	 public Creature() {
			name = "Name"; 
			lvl = 0;
		    stats_ = new Stats();
			abilities_ = new Abilities();
			position_ = new Position();
			status_ = new Status();			 
	 }
		
	 public void printStatsFormal() {
		 s.out("");
		 s.out(this.name + " stats:");
		 this.stats_.printStats();
	 }
	
	 public boolean isDead() {return false;}
	 
	 
	 public void show() {}
	 
}
