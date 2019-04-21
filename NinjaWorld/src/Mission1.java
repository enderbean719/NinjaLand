
public class Mission1 implements Story {

	@Override
	public void start_(Character mc) {
		// SAM START HERE
		System1 s = new System1();
		s.out("Mission begin");

		int answer = 0;
		int bagOfGold = 0;
		int bagOfMetal = 0;
		boolean gambleMetal = false;
		boolean gambleGold = false;
		boolean accepted = false;
		
		//Dialogue
		//s.out("");
		
		//s.out("can find him at the ramen shop.");
		//s.out("1. Accept Mission");
		//s.out("2. Reject Mission");
		//answer = s.getIntBetween(1,2);
		//if(answer == 1) {
			
			
		//}else if(answer == 2) {
		//}
		//Action: Explain Mission (brief) Accept or reject?
		//Accept is to move forward
		
		
		s.out("Danzo Assistant: Three shinobi artifcats have been stolen by a small band of shinobi robbers.");
		s.out("We've tracked their location to a near by fort outside of the village.");
		s.out("They are attempting to find a buyer for the stolen shinobi artifacts.");
		s.out("Your mission is to act like a potential buyer with this bag of gold.");
		s.out("and bring back the artifacts.");
			
		s.out("Do you accept this mission?");
		s.out("1. Accept Mission");
		s.out("2. Reject Mission");
		answer = s.getIntBetween(1,2);
		if(answer == 1) {
			accepted = true;
		}else if(answer == 2) {
			s.out("Danzo Assistant: Good luck finding an open mission cooler than this one.");
		}
		if(accepted == true) {
		
			s.out("Danzo Assistant: We're glad you accepted, here is the small bag of gold.");
			bagOfGold = 1;
			s.out("Danzo Assistant: Get on going before someone buys the artifacts before you do!");
			s.out("1. Ask for more money to be used on the mission");
			s.out("2. Accept amount given and begin mission");
			answer = s.getIntBetween(1,2);
			if(answer == 1) {
				s.out(mc.name +": You think they'll take me seriously with this small bag of gold?");
				s.out("Danzo Assistant: You're not buying the artifact you noodle head!");
				s.out("The gold is suppose to get you close to the robbers and get them to lower their guard.");
				s.out("But since you asked, here are two small bags of metal nuts and bolts. Act like it's gold.");
				s.out("There, now they'll take you more seriously.");
				bagOfMetal=2;
			}else if(answer == 2) {
			}
			s.out("Good luck on the mission.");
			s.out(mc.name +": Leaves the secret AMBU Black Ops building.");
			
			s.out("As you make your way to the city gate you see some men gambling in an alley.");
			s.out("Do you want to gamble with the mission money?");
			s.out("1. Continue to the city gate");
			s.out("2. Gamble with mission money");
			answer = s.getIntBetween(1,2);
			if(answer == 1) {
				s.out(mc.name +" leaves the city");
			}else if(answer == 2) {
				s.out(mc.name +" approaches the men gambling. You guys got room for one more?");
				s.out("Gambling Man: Sure, take seat.  We're playing Donkey Dice. Place you wager on the table.");
				s.out(mc.name+": I have some bags of gold.");
				s.out("1. Wager one bag of metal nuts and bolts [deceive]");
				s.out("2. Wager one bag of gold");
				double random = Math.random();
				double enemyRandom = Math.random();
				random = random * mc.stats_.brains;
				enemyRandom = enemyRandom * 3;
				if(answer == 1) {
					
				}
				
			}
			
			
		
		
		} // End of mission
			
			
			
			
			
	}// End Start (do not pass)

}// End Mission