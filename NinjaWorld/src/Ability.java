
public class Ability {

	
	private String name;
	private double range;
	private double secondsToPrepare;
	private double chakraCost;
	private double size;
	private double speed;
	private double duration;
	
	private double basicDamage;
	private double chakraDamage;
	private String damageType;  //fire , water
	
	private String specialEffect;  //genjutsu, sleep, paralysis, 
	private double seChance;  //special effect chance of succeeding
	
	private double badsb; 	// basicAtkDamageScalingBonus;
	private double cadsb;	//chakraAtkDamageScalingBonus;
	private double ssb;		//speedScalingBonus;
	private double cqsb;	//chakraQuantityScalingBonus;
	
	private boolean requireHands;
	
	
	
	public Ability() {
		name = "Basic Attack";
		range = 1.0;
		secondsToPrepare = 0.0;
		chakraCost = 0.0;
		size = 1.0;
		speed = 1.0;
		duration = 0.0;
		basicDamage = 1.0;
		chakraDamage = 0.0;
		damageType = "normal";
		specialEffect = "none";
	
	
	}
	
	public void print() {
		
	}
	
	public void show() {
	}
}
