import java.io.Serializable;

  
public class Mission3 implements Story, Serializable{

	@Override
	public void start_(Character mc) {
		int answer = 0;
		System1 s = new System1();
		boolean acceptMission = false;
		boolean rejectMission = false;
		Character Alfin = new Character(true, "Alfin Sensei", 30, "Male");
		mc.getRel_().newRelationship75(Alfin.getName());		
		Character Mintly = new Character(true, "Mintly Kun", 5, "Female");
		mc.getRel_().newRelationship50(Mintly.getName());
		Character Geeno = new Character(true, "Geeno Kun", 5, "Male");
		mc.getRel_().newRelationship50(Geeno.getName());
		
		s.out("Alfin: All right team 3! We've been called for our first D rank mission outside the village.");
		s.out("Lets meet up in 10 minutes at the Hokoges waiting room. Geeno, "+mc.getName()+", Mintly, are you ready? ");
		
		s.out("1. Accept Mission");
		s.out("2. Reject Mission");
		answer = s.getIntBetween(1,2);
		if(answer == 1) {
			acceptMission = true;
			
		}else if(answer == 2) {
			s.out("Alfin: We will have to have a discussion about this later. Everyone else, lets go.");
			mc.getRel_().addBadDeedAgainst(Alfin.getName());
			mc.getRel_().addBadDeedAgainst(Mintly.getName());
			mc.getRel_().addBadDeedAgainst(Geeno.getName());
			s.out("<the team grows less fond of you>");
			rejectMission = true;
		// this + the other are stacked if you choose to reject mission. have it return person to the 4 mission options instead.	
		}


		if(acceptMission == true) {
			s.out("Alfin: Alright, let's bring it in before we break!");
			s.out("Alfin springs forward shoving his hand infront of you and the others.");
			s.out("Geeno, rejects Alfins out-stretched hand, while Mintly looks unsure from the tention in the air.");
			
			s.out("1. <" +mc.getName()+ " reciprocate Alfin's enthusiasm and pile your hand on top of his.> All right!");
			s.out("2. <" +mc.getName()+ " awkwardly look off in the direction of the meeting site> let's just get going.");
			s.out("3. <" +mc.getName()+ " smiles at Mintly, and calmly adds his hand on top of Alfin's> We got this.");
			s.out("4. <" +mc.getName()+ " plops his hand on the pile> ok, I guess");
			answer = s.getIntBetween(1,4);
			if(answer == 1) {
				mc.getRel_().addGoodDeedAgainst(Alfin.getName());
				mc.getRel_().addGoodDeedAgainst(Alfin.getName());
				mc.getRel_().addBadDeedAgainst(Geeno.getName());
				mc.getRel_().addBadDeedAgainst(Geeno.getName());
				s.out("Alfin: alright! " +mc.getName()+ " glad to hear it.");
				s.out("Alfin looks at the other two and asks, anyone else??!");
				s.out("Mintly adds her hand next, then Genno a second after.");
				s.out("alright, team, lets move out.");
				s.out("<Alfin grows more fond of you x 2 >");
				s.out("<Geeno grows less fond of you x 2 >");
			} else if(answer == 2) {
				mc.getRel_().addGoodDeedAgainst(Geeno.getName());
				mc.getRel_().addGoodDeedAgainst(Geeno.getName());
				mc.getRel_().addBadDeedAgainst(Alfin.getName());
				mc.getRel_().addBadDeedAgainst(Mintly.getName());
				s.out("<Mintly feels bad and adds her hand to Alfin's sheepishly while avoiding eye contact with " +mc.getName()+ " and Geeno.>");
				s.out("Alfin: ohh... alright then. Well, I guess I'll see you all there then.");
				s.out("<Alfin grows less fond of you>");
				s.out("<Mintly grows less fond of you>");
				s.out("<Geeno grows more fond of you x 2>");
			} else if(answer == 3) {
				mc.getRel_().addGoodDeedAgainst(Mintly.getName());
				mc.getRel_().addGoodDeedAgainst(Alfin.getName());
				s.out("Alfin: Good to hear, " +mc.getName()+ ". Let's keep up that possitivity and head out.");
				s.out("<Mintly grows more fond of you>");
				s.out("<Alfin grows more fond of you>");
			} else if(answer == 4) {
				s.out("Alfin looks at the other two and asks, anyone else??!");
				s.out("Mintly adds her hand next, then Genno growns and adds his a second after.");
			}
			s.out("1. Walk with Alfin to the meeting spot.");
			s.out("2. Offer to race against Geeno to the meeting spot.");
			s.out("3. chat with Mintly on your way to the meeting spot"); 
			s.out("4. Take your own route to the meeting spot");
			answer = s.getIntBetween(1,4);
			if(answer == 1) {
				if(mc.getRel_().getRelationship(Alfin.getName())>.82) {
					s.out("Alfin: You remind me of myself when I was young.");
					s.out("The best guys in my age group always used a special food pill when in tight situations."); 
					s.out("I still have some till this day, if I ever do end up in combat.");
					s.out("you can never be too prepared you know.");
					s.out("Alfin: Here take this " +mc.getName()+ ". Just in case the time comes when you could really use an extra boost of power.");
					mc.getRel_().addGoodDeedAgainst(Alfin.getName());
					mc.getRel_().addGoodDeedAgainst(Alfin.getName());
					s.out("<Alfin grows more fond of you>");
				}
				if(mc.getRel_().getRelationship(Alfin.getName())<.82) {
					s.out(mc.getName()+" hears all about alfins back story and how he had it so hard back in the old days on his way to the Hokage's.");
					mc.getRel_().addGoodDeedAgainst(Alfin.getName());
					s.out("<Alfin grows more fond of you>");
				}
			}
			if(answer == 2) {
				if(mc.getRel_().getRelationship(Geeno.getName())>.65) {
					s.out("f");
				}
				if(mc.getRel_().getRelationship(Geeno.getName())<.65) {
					s.out("f");
				}
			}
			if(answer == 3) {
				if(mc.getRel_().getRelationship(Geeno.getName())>.65) {
					s.out("f");
				}
				if(mc.getRel_().getRelationship(Geeno.getName())<.65) {
					s.out("f");	
				}
			}
			if(answer == 4) {
				if(mc.getRel_().getRelationship(Geeno.getName())>.65) {
					s.out("f");
				}
				if(mc.getRel_().getRelationship(Geeno.getName())<.65) {
					s.out("f");	
				}
			}
				
				
					
	}	//end  accepted == true
			
				
				// add a food pill in Items that is connected to some stat boosts and nerfs
			
			
		
				
				
				
	

			
			
			
		if(rejectMission == true) {
			s.out("You know, I have a side job you can do.  It might be easier.");
			// this + the other are stacked if you choose to reject mission. have it return person to 4 mission options instead.
	
		}//end reject == true

	}//end start

}//end story 