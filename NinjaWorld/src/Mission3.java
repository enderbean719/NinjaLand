public class Mission3 implements Story {

	@Override
	public void start_(Character mc) {
		//JAKE START HERE
		
		int answer = 0;
		System1 s = new System1();
		boolean accepted = false;
		boolean reject = false;
				
		
		s.out("ALfin: All right team 3! We've been called for our first D rank mission outside the village.");
		s.out("Lets meet up in 10 minutes at the Hokoges waiting room. Geeno, "+mc.getName()+", Mintly, are you ready? ");
		
		s.out("1. Accept Mission");
		s.out("2. Reject Mission");
		answer = s.getIntBetween(1,2);
		if(answer == 1) {
			accepted = true;
			
		}else if(answer == 2) {
			s.out("Alfin: We will have to have a discussion about this later. Everyone else, lets go.");
			reject = true;
			
		}


		if(accepted == true) {
			s.out("Alfin: Alright, let's bring it in before we break!");
			s.out("Alfin springs forward shoving his hand infront of you and the others.");
			s.out("Geeno, hard back steps Alfins thrusted hand while Mintly looks unsure from the tention in the air.");
			
			s.out("1. reciprocate Alfin's enthusiasm and pile your hand on top of his.");
	
			s.out("1. walk with Alfin to the meeting spot.");
			s.out("2. Race with Geno to the meeting spot.");
			answer = s.getIntBetween(1,2);
			if(answer == 1) {
				s.out(mc.getName()+" sat next to Thaku.");
				
			}
		
		}//end  accepted == true
			
			
			
		if(reject == true) {
				
			s.out("You know, I have a side job you can do.  It might be easier.");
			
			
				
				

		
			
		}//end reject == true



	}//end start


}//end story 