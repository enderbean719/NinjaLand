
public class Stats {
	
	System1 s = new System1();
	//base stats
	
	public double currentHP;
	public double currentChakra;
	
	public double maxHP;
	public double maxChakra;
	
	public double basicAtk;
	public double chakraAtk;
	public double basicDef;
	public double chakraDef;
	public double speed;
	public double brains;
	public double sensing;
	
	public double hpRegen;
	public double chakraRegen;
	//special item influenced stats
	private double armor;
	private double weight;
	
	public Stats() {

		currentHP = 1;
		currentChakra = 1;
		maxHP = 40;
		basicAtk = 1;
		chakraAtk = 1;
		basicDef = 1;
		chakraDef = 1;
		speed = 1;
		brains = 1;
		maxChakra = 40;
		sensing = 1;
		hpRegen = 1;
		chakraRegen = 1;		
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
			maxHP *= 1.3;
			break;
		case 9:
			maxChakra *= 1.3;
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
	 
	
	
}//end stats
