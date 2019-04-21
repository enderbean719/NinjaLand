
public class Mission4 implements Story {

	@Override
	public void start_(Character mc) {
		//JOSH START HERE
		
				int answer = 0;
				System1 s = new System1();
				boolean accepted = false;
				boolean continue1 = false;
						
				
				s.out("Rock Lee: So this mission should be pretty simple. There is a member of the sand village here in town who needs some help. The leaf");
				s.out("village has an alliance with the sand village and this mission could help strengthen relations. The sand ninja's name is Thaku, you");
				s.out("can find him at the ramen shop.");
				s.out("1. Accept Mission");
				s.out("2. Reject Mission");
				answer = s.getIntBetween(1,2);
				if(answer == 1) {
					accepted = true;
					
				}else if(answer == 2) {
					
					
					
				}
	
	
				if(accepted == true) {
					s.out("A few minutes later "+mc.name+" arrived at the ramen shop. "+mc.name+" opened to door to the shop and was greeted by the pleasant smell");
					s.out("of beef flavored ramen. "+mc.name+" noticed 3 ninja, one was quite tall with short dark hair with leaf village ninja attire, another was");
					s.out("average height with black clothes and blonde hair, and the last was obviously Thaku, the sand ninja. Thaku was tall and massively built.");
					s.out("He was wearing dark blue and brown, loosely-fitted sand village clothes and had a stern expression on his face. ");
					s.out("1. Approach Thaku");
					s.out("2. Buy ramen");
					answer = s.getIntBetween(1,2);
					if(answer == 1) {
						s.out(mc.name+" sat next to Thaku.");
						s.out(mc.name+": \"Are you Thaku?\"");
						s.out("Thaku: \"You're the ninja the leaf village sent me?\" Thaku said incredulously.");
						s.out(mc.name+": Yes, I'm more powerful than I appear.");
						s.out("Thaku: \"Well, let's hope you're right.\"");
						
						
						
					}else if(answer == 2) {
						s.out(mc.name+" started walking up to the store clerk and passed the sand ninja. The ninja quickly stood up and blocked "+mc.name+"'s path.");
						s.out("Thaku: \"My name is Thaku. I can tell by that look on your face that you are the ninja the leaf village sent to help me.\"");
					}
					
					
					
					s.out("Thaku ordered two ramens and continued. \n\"There is a certain beast I've been tracking for several days. It's called a Sunakoi.");
					s.out("Do you know what a Sunakoi is?\"");
					s.out("1. Yes");
					s.out("2. No");
					answer = s.getIntBetween(1,2);
					if(answer == 1) {
						s.out("Thaku: \"Then you'll know how dangerous they are.");
					}
				}else if(answer == 2) {
					s.out("These creatures are originally from extreme desert areas. They are brown, four-legged creatures with scales that form ");
					s.out("backward-facing spikes. They're claws and teeth are razor sharp and can cut you in half in an instant! A typical adult weighs more than 2 men");
					s.out("and they have chakra resistant armor and can spit poison.\"");
					
					s.out("Thaku cleared his voice and took a sip of water. His face was sweating and his voice was gruff.");
					s.out("1. Why are we searching for this Sunakoi?");
					s.out("2. How do we defeat the Sunakoi?");
					answer = s.getIntBetween(1,2);
					if(answer == 1) {
						
					}else if(answer == 2) {
						
					}
					
				}//end mission (accept = true)
	
	
	
	
	}//end start
	

}//end story 
