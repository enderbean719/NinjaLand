
public class Abilities {

	public Ability[] list_ = new Ability[6];
	
	public void showList() {
		for(int i=0; i<6; i++) {
			list_[i].print();
		}
	}
	
	public Ability loadAbility(String name) {
		Ability a = new Ability();
		return a;
		
	}
	
}
