
public class Stats {
	
	System1 s = new System1();
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

	public double currentHP;
	public double currentChakra;
	
	public double maxHP;
	public double maxChakra;
	public double hpRegen;
	public double chakraRegen;	
	public double basicAtk;
	public double chakraAtk;
	public double basicDef;
	public double chakraDef;
	public double speed;
	public double brains;
	public double sensing;
	
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
			s.out("You have " + x + " points to spend.");
			s.out("What area would you like to level up?");
			answer = s.getInt();
			if (answer >= 1 && answer <= 11) {
				levelUpStat(answer);
				x--;
			}else {
				s.out("Please enter a number 1 - 11");
			}
		}
		
	}//end purchaseStats
	
	
	
	public void printStats() {
		s.out("1.  Strength:					" + basicAtk);
		s.out("2.  Durability:					" + basicDef);
		s.out("3.  Offensive Ninjutsu:				" + chakraAtk);		
		s.out("4.  Defensive Ninjutsu:				" + chakraDef);
		s.out("5.  Taijutsu/Agility:				" + speed);
		s.out("6.  Genjutsu/Intelligence:			" + brains);
		s.out("7.  Sensory Skills:				" + sensing);
		s.out("8.  Health Quantity:				" + maxHP);
		s.out("9.  Chakra Quantity:				" + maxChakra);
		s.out("10. Health regeneration:			" + hpRegen);
		s.out("11. Chakra regeneration:			" + chakraRegen);
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
		//clawed --> dog, cat, bobcat, lion, fox, bear (speed, strength, hp)
		//flying --> bird, bat, dragon  (sensing + ninjutsu + speed)
		//elemental --> (ninjutsu + durable)
		//tanky  --> bear, toad, golem, (durable and strength)
		//tricky  --> goblin, ghost, sage animal (genjutsu)
		
		
		if(type == "clawed") {
			loadClawed(level);
		}else if(type == "flying") {
			loadFlying(level);
		}else if(type == "elemental") {
			
		}else if(type =="tanky") {
			
		}else if(type =="tricky") {
			
		}else {
			
		}
		
		
	}//end loadCreatureStats
	
	
	private void loadClawed(int lvl) {
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
	

	private void loadFlying(int lvl) {
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
	
}//end stats
