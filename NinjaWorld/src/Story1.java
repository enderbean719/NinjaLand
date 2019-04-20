
public class Story1 implements Story {
 
	int answer = 0;
	
	@Override
	public void start_(Character mc) { 
		//SETH BEGIN HERE
		System1 s = new System1();
		s.out("Story1 begin");
		
		s.out("Welcome " + mc.name_ ); //+ player.name);
		
		
		//PLOT
		//Ninja enters village
		//Nice ninja arrives as guide to watch chuunin exams
		//mean ninja is in audience
		//dialoge - befriend mean ninja = sacrifice friendship with nice ninja
		//dialogue - maintaining current friend = piss off mean ninja
		//if mean ninja = pissed off --> attacks you after the show
		//Your sensei tells you that he needs your help
		//deliver a scroll to the hokage's assistant
		//peek inside scroll = lose trust but gain a forbidden jutsu
		
		s.out("Hey " + " My name is Rock Lee" ) ;// + p.name); 
		
		//print nice character image;
		s.out("Do you hear that load roaring?");
		s.out("1. yeah, whats all the commotion?");
		s.out("2. no, what's up?");
		s.out("3. no, are you hearing things? ");
		answer = s.getInt();
		if(answer == 1) {
			
			s.out("I was wondering when you would finally notice!");
			s.out("It's the chuunin exams!  We're late for the show!");
			s.out("Let's go find some seats in the audience.");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");
		}else if(answer == 2) {
			s.out("Man, you must be deaf or something!");
			s.out("It's the chuunin exams!  We're late for the show!");
			s.out("Let's go find some seats in the audience.");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");
		}else {
			s.out("What?? You're kidding, right?");
			s.out("It's the chuunin exams!  We're late for the show!");
			s.out("Let's go find some seats in the audience.");
			s.out("1. Sure lets go!");
			s.out("2. Nah...You go ahead.");			
		}
		answer = s.getInt();
		
		s.out("THE STORY CONTINUES....");
		s.out("6 months later...");
		s.out("");
		s.out(mc.name_ + " its time for you to begin a mission.");
		s.out("Please select a mission");
		s.out("1. Sam's mission - C rank - $500 gold");
		s.out("2. Chris's mission - C rank - $500 gold");
		s.out("3. Jake's mission - D rank - $300 gold");
		s.out("4. Josh's mission - D rank - $300 gold");
		answer = s.getInt();
		Mission1 m1 = new Mission1();
		Mission2 m2 = new Mission2();
		Mission3 m3 = new Mission3();
		Mission4 m4 = new Mission4();
		if(answer ==1) {
			m1.start_(mc);
		}else if(answer == 2) {
			m2.start_(mc);
		}else if(answer == 3) {
			m3.start_(mc);
		}else if(answer == 4) {
			m4.start_(mc);
		}else {
			s.out("Okay then.  No missions for you.");
		}
		
	}//end story1
	
	
}
