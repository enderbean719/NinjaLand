import java.io.Serializable;


public class Stats implements Serializable {
	
	private System1 s = new System1();
//base stats
//	maxHP
//	maxChakra
//	hpRegen
//	chakraRegen	
//	basicAtk
//	chakraAtk
//	basicDef
//	chakraDef
//	speed
//	brains
//	sensing

	private double currentHP;
	private double currentChakra;
	
	private double maxHP;
	private double maxChakra;
	private double hpRegen;
	private double chakraRegen;	
	private double basicAtk;
	private double chakraAtk;
	private double basicDef;
	private double chakraDef;
	private double speed;
	private double brains;
	private double sensing;
	
	//special item influenced stats
	private double armor;
	private double weight;
	

	public Stats() {

		currentHP = 40;
		currentChakra = 40;
		maxHP = 40;  		
		maxChakra = 40;		
		hpRegen = 1;		
		chakraRegen = 1;	
		basicAtk = 1;		
		chakraAtk = 1;		
		basicDef = 1;		
		chakraDef = 1;		
		speed = 1;			
		brains = 1;				
		sensing = 1;		
			
		armor = 1;
		weight = 1;

	}
	
	public void purchaseStats(int x){
		s.out("These are your current stats.");
		int answer = 0;
		
		while (x > 0){
			printStats();
			s.pause();
			s.out("You have " + x + " points to spend.");
			s.out("What area would you like to level up?");
			s.print(":");
			answer = s.getInt();
			if (answer >= 1 && answer <= 11) {
				levelUpStat(answer);
				x--;
			}else {
				s.out("Please enter a number 1 - 11");
			}
		}
		s.out("");
		s.out("");
		s.out("These are your new stats!")	;
		printStats();
		s.pause();
	}//end purchaseStats
	
	
	
	public void printStats() {
		//System.out.printf();

		System.out.printf("%-30s %5.0f %n", "1.  Strength:", basicAtk);
		System.out.printf("%-30s %5.0f %n", "2.  Durability:", basicDef);
		System.out.printf("%-30s %5.0f %n", "3.  Offensive Ninjutsu:", chakraAtk);
		System.out.printf("%-30s %5.0f %n", "4.  Defensive Ninjutsu:", chakraDef);
		System.out.printf("%-30s %5.0f %n", "5.  Taijutsu/Agility:", speed);
		System.out.printf("%-30s %5.0f %n", "6.  Genjutsu/Intelligence:", brains);
		System.out.printf("%-30s %5.0f %n", "7.  Sensory Skills:", sensing);
		System.out.printf("%-30s %5.0f %n", "8.  Health Quantity:", maxHP);
		System.out.printf("%-30s %5.0f %n", "9.  Chakra Quantity:", maxChakra);
		System.out.printf("%-30s %5.0f %n", "10. Health regeneration:", hpRegen);
		System.out.printf("%-30s %5.0f %n", "11. Chakra regeneration:", chakraRegen);

//
//		s.out("");
//		s.out("1.  Strength:					" + basicAtk);
//		s.out("2.  Durability:					" + basicDef);
//		s.out("3.  Offensive Ninjutsu:				" + chakraAtk);
//		s.out("4.  Defensive Ninjutsu:				" + chakraDef);
//		s.out("5.  Taijutsu/Agility:				" + speed);
//		s.out("6.  Genjutsu/Intelligence:			" + brains);
//		s.out("7.  Sensory Skills:				" + sensing);
//		s.out("8.  Health Quantity:				" + maxHP);
//		s.out("9.  Chakra Quantity:				" + maxChakra);
//		s.out("10. Health regeneration:			" + hpRegen);
//		s.out("11. Chakra regeneration:			" + chakraRegen);
	}
	
	public void levelUpStat(int choice) {
		
		switch (choice){
		case 1:
			basicAtk++;
			break;
		case 2:
			basicDef++;
			break;
		case 3:
			chakraAtk++;
			break;
		case 4:
			chakraDef++;
			break;
		case 5: 
			speed++;
			break;
		case 6:
			brains++;
			break;
		case 7:
			sensing++;
			break;
		case 8:
			maxHP = maxHP*1.1 + 15;
			break;
		case 9:
			maxChakra = maxChakra*1.1 + 15;
			break;
		case 10:
			hpRegen++;
			break;
		case 11:
			chakraRegen++;
			break;
		default:
			break;
			
		}
		
	}//end levelUpStats
	 
	//level math
	//points = (int) (Math.sqrt(lvl_)+2);
	//level 
	//1 = 6 --> 6
	//2 = 3 --> 9
	//3 = 3 --> 12
	//4 = 4 --> 16
	//5 = 4 --> 20
	//6 = 4 --> 24
	//7 = 5 --> 29
	//8 = 5 --> 34
	//9 = 5 --> 39
	//10 = 5 --> 44
	//11 = 5 --> 49
	//12 = 5 --> 54
	//13 = 6 --> 60
	public int calcPoints(int lvl) {
		int points = 6;  //points for lvl 1
		//add points for each level after 1
		for(int i = 2; i<=lvl; i++) {
			points += (int) (Math.sqrt(lvl)+2);
		}
		return points;
	}
	
	
	public void loadCreatureStats(String type, int level) {
		//clawed --> dog, cat, bobcat, lion, fox, bear
		// 			(speed, strength, hp)
		//flying --> bird, bat, dragon
		// 			(sensing + ninjutsu + speed)
		//elemental -->
		// 				(ninjutsu + durable)
		//tanky  --> bear, toad, golem,
		// 				(durable and strength)
		//tricky  --> goblin, ghost, sage animal
		// 				(genjutsu)
		
		
		if(type == "clawed") {
			loadClawed(level);
		}else if(type == "flying") {
			loadFlying(level);
		}else if(type == "elemental") {
			loadElemental(level);
		}else if(type =="tanky") {
			loadTanky(level);
		}else if(type =="tricky") {
			loadTricky(level);
		}else if(type == "weaponSummoner"){
			loadWeaponSummoner(level);
		}else {
			loadEqualStats(level);
		}
		
		
	}//end loadCreatureStats
	
	
	public void loadClawed(int lvl) {   //taijutsu
		int points = calcPoints(lvl);
		currentHP = 1;
		currentChakra = 1;		
		
		maxHP = 40;  		//10%
		maxChakra = 40;		//5%
		hpRegen = 1;		//5%
		chakraRegen = 1;	//5%
		basicAtk = 1;		//20%
		chakraAtk = 1;		//5%
		basicDef = 1;		//5%
		chakraDef = 1;		//5%
		speed = 1;			//30%
		brains = 1;			//5%		
		sensing = 1;		//5%
		//11 stats total
		
		armor = 1;
		weight = 1;
		
		applyPoints(points, .10, .05, .05, .05, .20, .05, .05, .05, .30, .05, .05);
		currentHP = maxHP;
		currentChakra = maxChakra;	
	}


	public void loadFlying(int lvl) {   //hyuuga
		int points = calcPoints(lvl);
		currentHP = 1;
		currentChakra = 1;		
		
		maxHP = 40;  		//5%
		maxChakra = 40;		//5%
		hpRegen = 1;		//5%
		chakraRegen = 1;	//5%
		basicAtk = 1;		//5%
		chakraAtk = 1;		//20%
		basicDef = 1;		//5%
		chakraDef = 1;		//5%
		speed = 1;			//10%
		brains = 1;			//5%		
		sensing = 1;		//30%
		//11 stats total
		
		armor = 1;
		weight = 1;
		
		applyPoints(points, .05, .05, .05, .05, .05, .20, .05, .05, .10, .05, .30);
		currentHP = maxHP;
		currentChakra = maxChakra;	
	}



	public void loadElemental(int lvl) {
		int points = calcPoints(lvl);
		currentHP = 1;
		currentChakra = 1;

		maxHP = 40;  		//5%
		maxChakra = 40;		//20%
		hpRegen = 1;		//5%
		chakraRegen = 1;	//5%
		basicAtk = 1;		//5%
		chakraAtk = 1;		//30%
		basicDef = 1;		//5%
		chakraDef = 1;		//10%
		speed = 1;			//5%
		brains = 1;			//5%
		sensing = 1;		//5%
		//11 stats total

		armor = 1;
		weight = 1;

		applyPoints(points, .05, .20, .05, .05, .05, .30, .05, .10, .10, .05, .05);
		currentHP = maxHP;
		currentChakra = maxChakra;
	}


	public void loadTanky(int lvl) {
		int points = calcPoints(lvl);
		currentHP = 1;
		currentChakra = 1;

		maxHP = 40;  		//20%
		maxChakra = 40;		//5%
		hpRegen = 1;		//15%
		chakraRegen = 1;	//5%
		basicAtk = 1;		//5%
		chakraAtk = 1;		//5%
		basicDef = 1;		//20%
		chakraDef = 1;		//10%
		speed = 1;			//5%
		brains = 1;			//5%
		sensing = 1;		//5%
		//11 stats total

		armor = 1;
		weight = 1;

		applyPoints(points, .20, .05, .15, .05, .05, .05, .20, .10, .05, .05, .05);
		currentHP = maxHP;
		currentChakra = maxChakra;
	}


	public void loadTricky(int lvl) {
		int points = calcPoints(lvl);
		currentHP = 1;
		currentChakra = 1;

		maxHP = 40;  		//5%
		maxChakra = 40;		//5%
		hpRegen = 1;		//5%
		chakraRegen = 1;	//10%
		basicAtk = 1;		//5%
		chakraAtk = 1;		//10%
		basicDef = 1;		//5%
		chakraDef = 1;		//10%
		speed = 1;			//10%
		brains = 1;			//30%
		sensing = 1;		//5%
		//11 stats total

		armor = 1;
		weight = 1;

		applyPoints(points, .05, .05, .05, .10, .05, .10, .05, .10, .10, .30, .05);
		currentHP = maxHP;
		currentChakra = maxChakra;
	}

	public void loadWeaponSummoner(int lvl) {
		int points = calcPoints(lvl);
		currentHP = 1;
		currentChakra = 1;

		maxHP = 40;  		//5%
		maxChakra = 40;		//20%
		hpRegen = 1;		//5%
		chakraRegen = 1;	//5%
		basicAtk = 1;		//15%
		chakraAtk = 1;		//5%
		basicDef = 1;		//15%
		chakraDef = 1;		//5%
		speed = 1;			//10%
		brains = 1;			//10%
		sensing = 1;		//5%
		//11 stats total

		armor = 1;
		weight = 1;

		applyPoints(points, .05, .20, .05, .05, .15, .05, .15, .05, .10, .10, .05);
		currentHP = maxHP;
		currentChakra = maxChakra;
	}



	public void loadEqualStats(int lvl) {
		int points = calcPoints(lvl);
		currentHP = 1;
		currentChakra = 1;

		maxHP = 40;  		//10%
		maxChakra = 40;		//9%
		hpRegen = 1;		//9%
		chakraRegen = 1;	//9%
		basicAtk = 1;		//9%
		chakraAtk = 1;		//9%
		basicDef = 1;		//9%
		chakraDef = 1;		//9%
		speed = 1;			//9%
		brains = 1;			//9%
		sensing = 1;		//9%
		//11 stats total

		armor = 1;
		weight = 1;

		applyPoints(points, .10, .09, .09, .09, .09, .09, .09, .09, .09, .09, .09);
		currentHP = maxHP;
		currentChakra = maxChakra;
	}


	private void applyPoints(int p, double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k) {
		//calculate ratios
		int[] pp = new int[11];
		pp[0]  = (int) (p * a);  
		pp[1]  = (int) (p * b);
		pp[2]  = (int) (p * c);
		pp[3]  = (int) (p * d);
		pp[4]  = (int) (p * e);
		pp[5]  = (int) (p * f);
		pp[6]  = (int) (p * g);
		pp[7]  = (int) (p * h);
		pp[8]  = (int) (p * i);
		pp[9]  = (int) (p * j);
		pp[10] = (int) (p * k);
		//11 stats total
		
		//fix rounding errors
		int index = 0;
		while(sumIntArray(pp) > p) {
			pp[index]--;
			index++;
			if(index > 10) {
				index = 0;
			}
		}
		
		index = 0;
		while(sumIntArray(pp) < p) {
			pp[index]++;
			index++;
			if(index > 10) {
				index = 0;
			}
		}
		
		//apply pp[index] to stats
		//maxHP 			*= Math.pow(1.3, (double) pp[0]);  //multiply HP by 1.3 ^ #points
		//maxChakra 		*= Math.pow(1.3, (double) pp[1]);  //multiply HP by 1.3 ^ #points
		for(int di = 0; di<pp[0];di++) {
			maxHP = maxHP*1.1 + 15;
		}
		for(int di = 0; di<pp[1];di++) {
			maxChakra = maxChakra*1.1 + 15;
		}
		hpRegen			+= pp[2];
		chakraRegen 	+= pp[3];
		basicAtk 		+= pp[4];
		chakraAtk 		+= pp[5];
		basicDef 		+= pp[6];
		chakraDef		+= pp[7];
		speed			+= pp[8];
		
		brains			+= pp[9];
		sensing			+= pp[10];		
		 
		
	}// end applyPoints

	
	private int sumIntArray(int[] array) {
		int sum = 0;
		for(int a : array){
			sum += a;
		}
		return sum;
	}
	
	
	
	
	public double getStatFromName(String name){

		switch (name){
			case "maxHP":
				return this.maxHP;
			case "maxChakra":
				return this.maxChakra;
			case "hpRegen":
				return this.hpRegen;
			case "chakraRegen":
				return this.chakraRegen;
			case "basicAtk":
				return this.basicAtk;
			case "chakraAtk":
				return this.chakraAtk;
			case "basicDef":
				return this.basicDef;
			case "chakraDef":
				return this.chakraDef;
			case "speed":
				return this.speed;
			case "brains":
				return this.brains;
			case "sensing":
				return this.sensing;
			default:
				return 0.0;
		}
	}//getStatFromName
	
	
	

	public System1 getS() {
		return s;
	}

	public void setS(System1 s) {
		this.s = s;
	}

	public double getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(double currentHP) {
		this.currentHP = currentHP;
	}

	public double getCurrentChakra() {
		return currentChakra;
	}

	public void setCurrentChakra(double currentChakra) {
		this.currentChakra = currentChakra;
	}

	public double getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(double maxHP) {
		this.maxHP = maxHP;
	}

	public double getMaxChakra() {
		return maxChakra;
	}

	public void setMaxChakra(double maxChakra) {
		this.maxChakra = maxChakra;
	}

	public double getHpRegen() {
		return hpRegen;
	}

	public void setHpRegen(double hpRegen) {
		this.hpRegen = hpRegen;
	}

	public double getChakraRegen() {
		return chakraRegen;
	}

	public void setChakraRegen(double chakraRegen) {
		this.chakraRegen = chakraRegen;
	}

	public double getBasicAtk() {
		return basicAtk;
	}

	public void setBasicAtk(double basicAtk) {
		this.basicAtk = basicAtk;
	}

	public double getChakraAtk() {
		return chakraAtk;
	}

	public void setChakraAtk(double chakraAtk) {
		this.chakraAtk = chakraAtk;
	}

	public double getBasicDef() {
		return basicDef;
	}

	public void setBasicDef(double basicDef) {
		this.basicDef = basicDef;
	}

	public double getChakraDef() {
		return chakraDef;
	}

	public void setChakraDef(double chakraDef) {
		this.chakraDef = chakraDef;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getBrains() {
		return brains;
	}

	public void setBrains(double brains) {
		this.brains = brains;
	}

	public double getSensing() {
		return sensing;
	}

	public void setSensing(double sensing) {
		this.sensing = sensing;
	}

	public double getArmor() {
		return armor;
	}

	public void setArmor(double armor) {
		this.armor = armor;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}//end stats
