import java.util.ArrayList;
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
	private boolean boosted;
	private double boostChakraCost;
	private HashMap<String, Double> boostCategory = new HashMap<String,Double>();
	
	private double visibility; 	//0 - 1
	private double sensibility;	//0 - 1
	
	private double basicDamage;
	private double chakraDamage;
	private String damageType;  //fire , water
	private HashMap<String,Double> bdScalingBonus = new HashMap<String,Double>();
	private HashMap<String,Double> cdScalingBonus = new HashMap<String,Double>();  
	
	private String specialEffect;  			//genjutsu, sleep, paralysis, 
	private double seChance;  				//special effect chance of succeeding  (0.0 - 1.0)
	private double seDurationAfterStrike;	//seconds
	private double seDamage;				
	private double seVisibility;		//0 - 1		
	private double seSensibility;		//0 - 1
	private HashMap<String,Double> seScalingBonus = new HashMap<String,Double>();
	
	
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

	//private int targetAreaID;
	//private String targetString;
	private ArrayList<Object> targetObjs = new ArrayList<Object>();;  //Character, Area, ability
	//private boolean atCharacter;	//battle processing
	//private boolean atArea;			//battle processing
	//private boolean atAbility;		//battle processing
	//private ArrayList<Character> targetCharacter; //can be null
	//private ArrayList<Area> targetArea;
	//private ArrayList<Ability> targetAbility; //can be null	
	private boolean targetLand;
	private boolean targetTrees;
	private boolean targetSky;
	private boolean targetWater;
	
	private boolean moveSelf;
	private Area moveSelfArea;
	private boolean moveTarget;
	private Area moveTargetToArea;
	
	


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
		boosted				= false;
		boostChakraCost 	= 0.0;
		boostCategory 		= null;

		visibility			= 0.8;
		sensibility			= 0.8;

		basicDamage			= 2.0;
		chakraDamage		= 0.0;
		damageType			= "physical";
		bdScalingBonus		=  new HashMap<String,Double>();
		bdScalingBonus.put("basicAtk", 0.5);
		//cdScalingBonus		=  new HashMap<String,Double>();
		bdScalingBonus.put("brains", 0.1);  //critical hits on intelligence
		 
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
			c1.getStats_().loadCreatureStats("clawed", i); 				
			Character c2 = new Character(); 
			c2.getStats_().loadCreatureStats("flying", i);
			double cc1=0;
			double cc2=0;
			
			switch(attribute) {
			case "maxHP":
				cc1 = c1.getStats_().getMaxHP();
				cc2 = c2.getStats_().getMaxHP();
				break;
			case "maxChakra":
				cc1 = c1.getStats_().getMaxChakra();
				cc2 = c2.getStats_().getMaxChakra();
				break;
			case "hpRegen":
				cc1 = c1.getStats_().getHpRegen();
				cc2 = c2.getStats_().getHpRegen();
				break;
			case "chakraRegen":
				cc1 = c1.getStats_().getChakraRegen();
				cc2 = c2.getStats_().getChakraRegen();
				break;
			case "basicAtk" : 
				cc1 = c1.getStats_().getBasicAtk();
				cc2 = c2.getStats_().getBasicAtk();
				break;
			case "chakraAtk":
				cc1 = c1.getStats_().getChakraAtk();
				cc2 = c2.getStats_().getChakraAtk();
				break;
			case "basicDef":
				cc1 = c1.getStats_().getChakraAtk();
				cc2 = c2.getStats_().getChakraAtk();
				break;
			case "chakraDef":
				cc1 = c1.getStats_().getChakraAtk();
				cc2 = c2.getStats_().getChakraAtk();
				break;
			case "speed":
				cc1 = c1.getStats_().getChakraAtk();
				cc2 = c2.getStats_().getChakraAtk();
				break;
			case "brains":
				cc1 = c1.getStats_().getChakraAtk();
				cc2 = c2.getStats_().getChakraAtk();
				break;
			case "sensing":
				cc1 = c1.getStats_().getChakraAtk();
				cc2 = c2.getStats_().getChakraAtk();
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
			testC.setName("Naruto");
			testC.getStats_().loadCreatureStats("clawed", i);
			//testC.printStatsFormal();
			double cc1 = testC.getStats_().getMaxHP();
			
			Character monster = new Character();
			monster.setName("Evil Crane");
			monster.getStats_().loadCreatureStats("flying", i);
			//monster.printStatsFormal();
			double cc2 = monster.getStats_().getMaxHP();
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
	
	
	
//	public void setTargetArea(Area a) {
//		this.targetArea.add(a);
//	}
	
	public void setTargetEnvi(String e) {
		switch (e){
		case "land":
			this.targetLand = true;
			break;
		case "water":
			this.targetWater = true;
			break;
		case "tree":
		case "trees":
			this.targetTrees = true;
			break;
		case "sky":
			this.targetSky = true;
			break;
		}
	}//setTargetEnvi
	
	public String getName() {
		return name;
	}
	
	
	
	
	
	public boolean isRequireTwoHands() {
		return requireTwoHands;
	}


	public void setRequireTwoHands(boolean requireTwoHands) {
		this.requireTwoHands = requireTwoHands;
	}


	public boolean isRequireOneHand() {
		return requireOneHand;
	}


	public void setRequireOneHand(boolean requireOneHand) {
		this.requireOneHand = requireOneHand;
	}


	public boolean isDefensive() {
		return defensive;
	}


	public void setDefensive(boolean defensive) {
		this.defensive = defensive;
	}


	public boolean isOffensive() {
		return offensive;
	}


	public void setOffensive(boolean offensive) {
		this.offensive = offensive;
	}


	public double getChakraCost() {
		return chakraCost;
	}


	public void setChakraCost(double chakraCost) {
		this.chakraCost = chakraCost;
	}


	public double getRange() {
		return range;
	}


	public void setRange(double range) {
		this.range = range;
	}


	public double getSize() {
		return size;
	}


	public void setSize(double size) {
		this.size = size;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public double getSecondsToPrepare() {
		return secondsToPrepare;
	}


	public void setSecondsToPrepare(double secondsToPrepare) {
		this.secondsToPrepare = secondsToPrepare;
	}


	public double getDurationAfterStrike() {
		return durationAfterStrike;
	}


	public void setDurationAfterStrike(double durationAfterStrike) {
		this.durationAfterStrike = durationAfterStrike;
	}


	public boolean isBoostable() {
		return boostable;
	}


	public void setBoostable(boolean boostable) {
		this.boostable = boostable;
	}


	public double getBoostChakraCost() {
		return boostChakraCost;
	}


	public void setBoostChakraCost(double boostChakraCost) {
		this.boostChakraCost = boostChakraCost;
	}


	public HashMap<String, Double> getBoostCategory() {
		return boostCategory;
	}


	public void setBoostCategory(HashMap<String, Double> boostCategory) {
		this.boostCategory = boostCategory;
	}


	public double getVisibility() {
		return visibility;
	}


	public void setVisibility(double visibility) {
		this.visibility = visibility;
	}


	public double getSensibility() {
		return sensibility;
	}


	public void setSensibility(double sensibility) {
		this.sensibility = sensibility;
	}


	public double getBasicDamage() {
		return basicDamage;
	}


	public void setBasicDamage(double basicDamage) {
		this.basicDamage = basicDamage;
	}


	public double getChakraDamage() {
		return chakraDamage;
	}


	public void setChakraDamage(double chakraDamage) {
		this.chakraDamage = chakraDamage;
	}


	public String getDamageType() {
		return damageType;
	}


	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}


	public HashMap<String, Double> getBdScalingBonus() {
		return bdScalingBonus;
	}


	public void setBdScalingBonus(HashMap<String, Double> bdScalingBonus) {
		this.bdScalingBonus = bdScalingBonus;
	}


	public HashMap<String, Double> getCdScalingBonus() {
		return cdScalingBonus;
	}


	public void setCdScalingBonus(HashMap<String, Double> cdScalingBonus) {
		this.cdScalingBonus = cdScalingBonus;
	}


	public String getSpecialEffect() {
		return specialEffect;
	}


	public void setSpecialEffect(String specialEffect) {
		this.specialEffect = specialEffect;
	}


	public double getSeChance() {
		return seChance;
	}


	public void setSeChance(double seChance) {
		this.seChance = seChance;
	}


	public double getSeDurationAfterStrike() {
		return seDurationAfterStrike;
	}


	public void setSeDurationAfterStrike(double seDurationAfterStrike) {
		this.seDurationAfterStrike = seDurationAfterStrike;
	}


	public double getSeDamage() {
		return seDamage;
	}


	public void setSeDamage(double seDamage) {
		this.seDamage = seDamage;
	}


	public double getSeVisibility() {
		return seVisibility;
	}


	public void setSeVisibility(double seVisibility) {
		this.seVisibility = seVisibility;
	}


	public double getSeSensibility() {
		return seSensibility;
	}


	public void setSeSensibility(double seSensibility) {
		this.seSensibility = seSensibility;
	}


	public HashMap<String, Double> getSeScalingBonus() {
		return seScalingBonus;
	}


	public void setSeScalingBonus(HashMap<String, Double> seScalingBonus) {
		this.seScalingBonus = seScalingBonus;
	}


	public boolean isProjectile() {
		return projectile;
	}


	public void setProjectile(boolean projectile) {
		this.projectile = projectile;
	}


	public boolean isFollowing() {
		return following;
	}


	public void setFollowing(boolean following) {
		this.following = following;
	}


	public double getFollowSpeed() {
		return followSpeed;
	}


	public void setFollowSpeed(double followSpeed) {
		this.followSpeed = followSpeed;
	}


	public double getNumProjectiles() {
		return numProjectiles;
	}


	public void setNumProjectiles(double numProjectiles) {
		this.numProjectiles = numProjectiles;
	}


	public double getNumTargets() {
		return numTargets;
	}


	public void setNumTargets(double numTargets) {
		this.numTargets = numTargets;
	}


	public boolean isCanStrikeBeforeDestination() {
		return canStrikeBeforeDestination;
	}


	public void setCanStrikeBeforeDestination(boolean canStrikeBeforeDestination) {
		this.canStrikeBeforeDestination = canStrikeBeforeDestination;
	}


	public boolean isCanGoThroughTarget() {
		return canGoThroughTarget;
	}


	public void setCanGoThroughTarget(boolean canGoThroughTarget) {
		this.canGoThroughTarget = canGoThroughTarget;
	}


	public boolean isCanBeBlockedByJutsu() {
		return canBeBlockedByJutsu;
	}


	public void setCanBeBlockedByJutsu(boolean canBeBlockedByJutsu) {
		this.canBeBlockedByJutsu = canBeBlockedByJutsu;
	}


	public boolean isAoeTrees() {
		return aoeTrees;
	}


	public void setAoeTrees(boolean aoeTrees) {
		this.aoeTrees = aoeTrees;
	}


	public boolean isAoeWater() {
		return aoeWater;
	}


	public void setAoeWater(boolean aoeWater) {
		this.aoeWater = aoeWater;
	}


	public boolean isAoeSky() {
		return aoeSky;
	}


	public void setAoeSky(boolean aoeSky) {
		this.aoeSky = aoeSky;
	}


	public double getAoeRange() {
		return aoeRange;
	}


	public void setAoeRange(double aoeRange) {
		this.aoeRange = aoeRange;
	}


	public String getAoeShape() {
		return aoeShape;
	}


	public void setAoeShape(String aoeShape) {
		this.aoeShape = aoeShape;
	}
 
//	public ArrayList<Area> getTargetArea() {
//		return targetArea;
//	}
//
//
//	public void setTargetArea(ArrayList<Area> targetArea) {
//		this.targetArea = targetArea;
//	}


	public boolean isTargetLand() {
		return targetLand;
	}


	public void setTargetLand(boolean targetLand) {
		this.targetLand = targetLand;
	}


	public boolean isTargetTrees() {
		return targetTrees;
	}


	public void setTargetTrees(boolean targetTrees) {
		this.targetTrees = targetTrees;
	}


	public boolean isTargetSky() {
		return targetSky;
	}


	public void setTargetSky(boolean targetSky) {
		this.targetSky = targetSky;
	}


	public boolean isTargetWater() {
		return targetWater;
	}


	public void setTargetWater(boolean targetWater) {
		this.targetWater = targetWater;
	}
 

	public String getLaunchMessage() {
		return launchMessage;
	}


	public void setLaunchMessage(String launchMessage) {
		this.launchMessage = launchMessage;
	}


	public String getLandMessage() {
		return landMessage;
	}


	public void setLandMessage(String landMessage) {
		this.landMessage = landMessage;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isMoveSelf() {
		return moveSelf;
	}


	public void setMoveSelf(boolean moveSelf) {
		this.moveSelf = moveSelf;
	}
	
	public Area getMoveSelfArea() {
		return moveSelfArea;
	}


	public void setMoveSelfArea(Area moveSelfArea) {
		this.moveSelfArea = moveSelfArea;
	}


	public boolean isMoveTarget() {
		return moveTarget;
	}


	public void setMoveTarget(boolean moveTarget) {
		this.moveTarget = moveTarget;
	}


	public Area getMoveTargetToArea() {
		return moveTargetToArea;
	}


	public void setMoveTargetToArea(Area moveTargetToArea) {
		this.moveTargetToArea = moveTargetToArea;
	}


//	public void setTargetCharacter(ArrayList<Character> targetCharacter) {
//		this.targetCharacter = targetCharacter;
//	}
//
//
//	public void setTargetAbility(ArrayList<Ability> targetAbility) {
//		this.targetAbility = targetAbility;
//	}


	public boolean isBoosted() {
		return boosted;
	}


	public void setBoosted(boolean boosted) {
		this.boosted = boosted;
	}


//	public ArrayList<Character> getTargetCharacter() {
//		return targetCharacter;
//	}
//
//
//	public ArrayList<Ability> getTargetAbility() {
//		return targetAbility;
//	}
//
//
//	public boolean isAtCharacter() {
//		return atCharacter;
//	}
//
//
//	public void setAtCharacter(boolean atCharacter) {
//		this.atCharacter = atCharacter;
//	}
//
//
//	public boolean isAtArea() {
//		return atArea;
//	}
//
//
//	public void setAtArea(boolean atArea) {
//		this.atArea = atArea;
//	}

//
//	public int getTargetAreaID() {
//		return targetAreaID;
//	}
//
//
//	public void setTargetAreaID(int targetAreaID) {
//		this.targetAreaID = targetAreaID;
//	}


//	public String getTargetString() {
//		return targetString;
//	}
//
//
//	public void setTargetString(String targetString) {
//		this.targetString = targetString;
//	}
//
//
//	public boolean isAtAbility() {
//		return atAbility;
//	}
//
//
//	public void setAtAbility(boolean atAbility) {
//		this.atAbility = atAbility;
//	}


	public ArrayList<Object> getTargetObjs() {
		return targetObjs;
	}


	public void setTargetObjs(ArrayList<Object> targetObjs) {
		this.targetObjs = targetObjs;
	}
}//end ability

