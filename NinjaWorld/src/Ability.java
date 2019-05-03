import java.util.HashMap;

public class Ability {

	//private Character mc;
	private String name;
	
	private boolean requireTwoHands;
	private boolean requireOneHand;
	private boolean defensive;
	private boolean offensive;
	
	private double chakraCost;
	private double range;	
	private double size;
	private double speed;	
	private double secondsToPrepare;
	private double durationAfterStrike;
	
	private boolean boostable;
	private double boostChakraCost;
	private HashMap<String, Double> boostCategory;
	
	private double visibility; 	//0 - 1
	private double sensibility;	//0 - 1
	
	private double basicDamage;
	private double chakraDamage;
	private String damageType;  //fire , water
	private HashMap<String,Double> bdScalingBonus;
	private HashMap<String,Double> cdScalingBonus;  
	
	private String specialEffect;  			//genjutsu, sleep, paralysis, 
	private double seChance;  				//special effect chance of succeeding  (0.0 - 1.0)
	private double seDurationAfterStrike;	//seconds
	private double seDamage;				
	private double seVisibility;		//0 - 1		
	private double seSensibility;		//0 - 1
	private HashMap<String,Double> seScalingBonus;
	

	private boolean projectile;   //false if attached to user's body
	private boolean following;
	private double followSpeed;
	private double numProjectiles;
	private double numTargets;
	private boolean canStrikeBeforeDestination;
	private boolean canGoThroughTarget;
	private boolean canBeBlockedByJutsu;
	private boolean aoeTrees;
	private boolean aoeWater;
	private boolean aoeSky;
	private double aoeRange; //0 = only target, 1 = entire square of characters, 2 = target square + 1 range of squares
	private String aoeShape;  //circle = expands outwards evenly, 1 square, 9 squares, 25 squares //triangle = 1 sq, 4 sq, 9 sq fanning outwards

	private String launchMessage;   //?
	private String landMessage;		//?
	
	
	public Ability() {   //default ability		

	
	}
	
	
	public void loadChargingPunch() {

		name 				= "Charging punch";

		requireTwoHands 	= false;
		requireOneHand 		= true;
		defensive 			= true;
		offensive 			= true;

		chakraCost 			= 0.0;
		range 				= 0.0;
		size				= 1.0;
		speed				= 4.0;
		secondsToPrepare	= 0.0;
		durationAfterStrike = 0.0;

		boostable 			= false;
		boostChakraCost 	= 0.0;
		boostCategory 		= null;

		visibility			= 0.8;
		sensibility			= 0.8;

		basicDamage			= 1.0;
		chakraDamage		= 0.0;
		damageType			= "physical";
		bdScalingBonus		=  new HashMap<String,Double>();
		bdScalingBonus.put("basicAtk", 0.5);
		cdScalingBonus		=  new HashMap<String,Double>();
		cdScalingBonus.put("chakraAtk", 0.1);
		 
		specialEffect		= "stun";
		seChance			= 0.1;
		seDurationAfterStrike = 1.0;
		seDamage			= 2.0;
		seVisibility		= 0.3;
		seSensibility		= 0.3;
		seScalingBonus		= new HashMap<String,Double>();
		seScalingBonus.put("basicDef", 0.5);

		projectile			= false;
		following			= false;
		followSpeed			= 0.0;
		numProjectiles		= 0.0;
		numTargets			= 1.0;
		canStrikeBeforeDestination = false;
		canGoThroughTarget	= false;
		canBeBlockedByJutsu = false;
		aoeTrees			= false;
		aoeWater			= false;
		aoeSky				= false;
		aoeRange			= 0.0;
		aoeShape			= "";

		
		launchMessage		= "";
		landMessage			= "";
	}

	public void loadKunaiThrow() {
			
		name 				= "Kunai Throw";

		requireTwoHands 	= false;
		requireOneHand 		= true;
		defensive 			= false;
		offensive 			= true;

		chakraCost 			= 0.0;
		range 				= 5.0;  //self square + 4
		size				= 1.0;  
		speed				= 4.0;
		secondsToPrepare	= 1.0;
		durationAfterStrike = 0.0;

		boostable 			= false;
		boostChakraCost 	= 0.0;
		boostCategory 		= null;  // = new HashMap<String,Double>();
		//boostCategory.put("speed", 2.0)  //multiply's ability's speed by 2 

		visibility			= 0.7;  
		sensibility			= 0.8;

		basicDamage			= 1.0;
		chakraDamage		= 0.0;
		damageType			= "weapon";
		bdScalingBonus		=  new HashMap<String,Double>();
		bdScalingBonus.put("basicAtk", 0.1);
		cdScalingBonus		=  new HashMap<String,Double>();
		//cdScalingBonus.put("chakraAtk", 0.0);
		 
		specialEffect		= "";
		seChance			= 0.0;
		seDurationAfterStrike = 0.0;
		seDamage			= 0.0;
		seVisibility		= 0.0;
		seSensibility		= 0.0;
		seScalingBonus		= new HashMap<String,Double>();
		//seScalingBonus.put("basicDef", 0.5);

		projectile			= true;
		following			= false;
		followSpeed			= 0.0;
		numProjectiles		= 1.0;
		numTargets			= 1.0;
		canStrikeBeforeDestination = true;
		canGoThroughTarget	= false;
		canBeBlockedByJutsu = true;
		aoeTrees			= false;
		aoeWater			= false;
		aoeSky				= false;
		aoeRange			= 0.0;
		aoeShape			= "";

		
		launchMessage		= "";
		landMessage			= "";

	
	}
	
	public void print() {
		System1 s = new System1();
		s.out(name);
	}
	
	public void show() {
		
	}
	
	
	
	
	
	
	
	

	public double getAvgStats(String rank, String attribute) {
		 
		double genin, chuunin, jounin, anbu, sannin, kage;
		genin = 0;
		chuunin = 0;
		jounin = 0;
		anbu = 0;
		sannin = 0;
		kage = 0;
		
		for(int i=1;i<=60;i++) {					
			Character c1 = new Character(); 
			c1.stats_.loadCreatureStats("clawed", i); 				
			Creature c2 = new Creature(); 
			c2.stats_.loadCreatureStats("flying", i);
			double cc1=0;
			double cc2=0;
			
			switch(attribute) {
			case "maxHP":
				cc1 = c1.stats_.maxHP;
				cc2 = c2.stats_.maxHP;
				break;
			case "maxChakra":
				cc1 = c1.stats_.maxChakra;
				cc2 = c2.stats_.maxChakra;
				break;
			case "hpRegen":
				cc1 = c1.stats_.hpRegen;
				cc2 = c2.stats_.hpRegen;
				break;
			case "chakraRegen":
				cc1 = c1.stats_.chakraRegen;
				cc2 = c2.stats_.chakraRegen;
				break;
			case "basicAtk" : 
				cc1 = c1.stats_.basicAtk;
				cc2 = c2.stats_.basicAtk;
				break;
			case "chakraAtk":
				cc1 = c1.stats_.chakraAtk;
				cc2 = c2.stats_.chakraAtk;
				break;
			case "basicDef":
				cc1 = c1.stats_.chakraAtk;
				cc2 = c2.stats_.chakraAtk;
				break;
			case "chakraDef":
				cc1 = c1.stats_.chakraAtk;
				cc2 = c2.stats_.chakraAtk;
				break;
			case "speed":
				cc1 = c1.stats_.chakraAtk;
				cc2 = c2.stats_.chakraAtk;
				break;
			case "brains":
				cc1 = c1.stats_.chakraAtk;
				cc2 = c2.stats_.chakraAtk;
				break;
			case "sensing":
				cc1 = c1.stats_.chakraAtk;
				cc2 = c2.stats_.chakraAtk;
				break;
			default: 
				break;
			}  
			
			double average = (cc1 + cc2)/2;
			if(i<10) {
				genin += average;
			}
			if(i>=10 && i<20) {
				chuunin += average;
			}
			if(i>=20 && i<30) {
				jounin += average;
			}
			if(i>=30 && i<40) {
				anbu += average;
			}
			if(i>=40 && i<50) {
				sannin += average;
			}
			if(i>=50 && i<60) {
				kage += average;
			}
			 
		} 
		switch (rank) {
		case "gennin":
		case "genin": 
			return genin; 
		case "chuunin":
		case "chunin":
			return chuunin;
		case "jounin":
		case "jonin":
			return jounin;
		case "anbu:":
			return anbu;
		case "kage":
			return kage;		
		default:
				return 0;
		}
		
	}//end getAvgStats
	
	
	public void printAvgStats(String rank, String attribute) {
		System1 s = new System1();
		double genin, chuunin, jonin, anbu, sannin, kage;
		genin = 0;
		chuunin = 0;
		jonin = 0;
		anbu = 0;
		sannin = 0;
		kage = 0;
		
		for(int i=1;i<=60;i++) {
			
			
			Character testC = new Character();
			testC.name = "Naruto";
			testC.stats_.loadCreatureStats("clawed", i);
			//testC.printStatsFormal();
			double cc1 = testC.stats_.maxHP;
			
			Creature monster = new Creature();
			monster.name = "Evil Crane";
			monster.stats_.loadCreatureStats("flying", i);
			//monster.printStatsFormal();
			double cc2 = monster.stats_.maxHP;
			double average = (cc1 + cc2)/2;
			if(i<10) {
				genin += average;
			}
			if(i>=10 && i<20) {
				chuunin += average;
			}
			if(i>=20 && i<30) {
				jonin += average;
			}
			if(i>=30 && i<40) {
				anbu += average;
			}
			if(i>=40 && i<50) {
				sannin += average;
			}
			if(i>=50 && i<60) {
				kage += average;
			}
			
			s.out("lvl " + i + " chakra = " + average);
		}
		s.out("genin avg chakra = " + genin/9);
		s.out("chuunin avg chakra = " + chuunin/10);
		s.out("jonin avg chakra = " + jonin/10);
		s.out("anbu avg chakra = " + anbu/10);
		s.out("sannin avg chakra = " + sannin/10);
		s.out("kage avg chakra = " + kage/10);
		s.out("");
	}
	
	
	public String getName() {
		return name;
	}
	
}//end ability

