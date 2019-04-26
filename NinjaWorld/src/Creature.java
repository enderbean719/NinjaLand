
public class Creature {

	 public String name;
	 public int lvl ;
	 public Stats stats_ ;
	 public Ability[] abilities_ ;
	 public Position position_ ;
	 public Status status_ ;
	 public AI AI_;
	 
	 System1 s = new System1();
	 
	 public Creature() {
			name = "Name"; 
			lvl = 0;
		    stats_ = new Stats();
			abilities_ = new Ability[1];
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
