import java.util.ArrayList;

public class Abilities {

	public ArrayList<Ability> aList = new ArrayList<Ability>();
	private System1 s = new System1();
	
	public Abilities() {
		
	}
	
	public void showList() {
		for(int i=0; i< aList.size(); i++) {
			s.out(i+": " + aList.get(i).getName() );
			
		}
	}
	
	public Ability loadAbility(String name) {
		Ability a = new Ability();
		return a;
		
	}
	
}
