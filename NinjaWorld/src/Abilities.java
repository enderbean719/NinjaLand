import java.util.ArrayList;

public class Abilities {

	private ArrayList<Ability> aList = new ArrayList<Ability>();
	private System1 s = new System1();
	private Character mc;
	
	public Abilities(Character c) {
		this.mc = c;
	}
	

	
	public Ability loadAbility(String name) {
		Ability a = new Ability();
		return a;
		
	}
	
	
	public Ability choose() {
		Ability a = new Ability();
		int answer = 0;
		s.out("+++++ " + mc.getName() + " +++++");
		s.out(" Choose an ability ");
		s.out("___________________");
		showList();
		s.out("___________________");
		s.print(":");
		answer = s.getIntBetween(0, aList.size());
		if(answer==0) {
			a = null;  				 	//0 = cancel
		}else {
			a = aList.get(answer-1);   //adjust answer for +1 offset
		}
		return a;
	}
	
	public Ability chooseOffensive() {
		Ability a = new Ability();
		int answer = 0;
		s.out("+++++ " + mc.getName() + " +++++");
		s.out(" Choose an offensive ability ");
		s.out("_____________________________");
		showListOffensive();
		s.out("_____________________________");
		s.print(":");
		answer = s.getIntBetween(0, aList.size());
		if(answer==0) {
			a = null;  				 	//0 = cancel
		}else {
			a = aList.get(answer-1);   //adjust answer for +1 offset
		}
		while(a != null && a.isOffensive() == false) {
			s.out("Sorry, please re-enter a valid number");
			s.out("_____________________________");
			s.print(":");
			answer = s.getIntBetween(1, aList.size());
			a = aList.get(answer-1);
		}
		return a;
	}
	
	public Ability chooseDefensive() {
		Ability a = new Ability();
		int answer = 0;
		s.out("+++++ " + mc.getName() + " +++++");
		s.out(" Choose a defensive ability ");
		s.out("____________________________");
		showListOffensive();
		s.out("_____________________________");
		s.print(":");
		answer = s.getIntBetween(0, aList.size());
		if(answer==0) {
			a = null;   				//0 = cancel
		}else {
			a = aList.get(answer-1);   //adjust answer for +1 offset
		}		
		while(a != null && a.isDefensive() == false) {
			s.out("Sorry, please re-enter a valid number");
			s.out("_____________________________");
			s.print(":");
			answer = s.getIntBetween(1, aList.size());
			a = aList.get(answer-1);
		}
		return a;
	}
	
	public void showList() {
		for(int i=0; i< aList.size(); i++) {
			s.out(i+": " + aList.get(i).getName() );			
		}
	}
	
	public void showListOffensive() {
		s.out("0. Cancel");
		for(int i=0; i< aList.size(); i++) {
			if(aList.get(i).isOffensive()	) {
				s.out(   (i+1) + ": " + aList.get(i).getName() );
			}			
		}
	}
	
	public void showListDefensive() {
		s.out("0. Cancel");
		for(int i=0; i< aList.size(); i++) {
			if(aList.get(i).isDefensive()	) {
				s.out(  (i+1) + ": " + aList.get(i).getName() );
			}			
		}
	}



	public ArrayList<Ability> getaList() {
		return aList;
	}



	public void setaList(ArrayList<Ability> aList) {
		this.aList = aList;
	}



	public System1 getS() {
		return s;
	}



	public void setS(System1 s) {
		this.s = s;
	}



	public Character getMc() {
		return mc;
	}



	public void setMc(Character mc) {
		this.mc = mc;
	}
	
	
}//end Abilities
