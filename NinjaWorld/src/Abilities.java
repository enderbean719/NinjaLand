import java.util.ArrayList;

public class Abilities {

	public ArrayList<Ability> aList = new ArrayList<Ability>();
	private System1 s = new System1();
	public Character owner;
	
	public Abilities(Character c) {
		this.owner = c;
	}
	

	
	public Ability loadAbility(String name) {
		Ability a = new Ability();
		return a;
		
	}
	
	
	public Ability choose() {
		Ability a = new Ability();
		int answer = 0;
		s.out("+++++ " + owner.getName() + " +++++");
		s.out(" Choose an ability ");
		s.out("___________________");
		showList();
		s.print("Ability: ");
		answer = s.getIntBetween(1, aList.size());
		a = aList.get(answer-1);
		return a;
	}
	
	public Ability chooseOffensive() {
		Ability a = new Ability();
		int answer = 0;
		s.out("+++++ " + owner.getName() + " +++++");
		s.out(" Choose an offensive ability ");
		s.out("_____________________________");
		showListOffensive();
		s.print("Ability: ");
		answer = s.getIntBetween(1, aList.size());
		a = aList.get(answer-1);
		while(a.isOffensive() == false) {
			s.out("Sorry, please re-enter a valid number");
			s.print("Ability: ");
			answer = s.getIntBetween(1, aList.size());
			a = aList.get(answer-1);
		}
		return a;
	}
	
	public Ability chooseDefensive() {
		Ability a = new Ability();
		int answer = 0;
		s.out("+++++ " + owner.getName() + " +++++");
		s.out(" Choose a defensive ability ");
		s.out("____________________________");
		showListOffensive();
		s.print("Ability: ");
		answer = s.getIntBetween(1, aList.size());
		a = aList.get(answer-1);
		while(a.isDefensive() == false) {
			s.out("Sorry, please re-enter a valid number");
			s.print("Ability: ");
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
		for(int i=0; i< aList.size(); i++) {
			if(aList.get(i).isOffensive()	) {
				s.out(i+": " + aList.get(i).getName() );
			}			
		}
	}
	
	public void showListDefensive() {
		for(int i=0; i< aList.size(); i++) {
			if(aList.get(i).isDefensive()	) {
				s.out(i+": " + aList.get(i).getName() );
			}			
		}
	}
	
	
}//end Abilities
