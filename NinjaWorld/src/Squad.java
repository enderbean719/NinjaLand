import java.util.ArrayList;

public class Squad {
	
	public String squadName;
	public int squadId = 0;
	public ArrayList<Character> members = new ArrayList<Character>();
	
	public Squad(){
		
	}
	
	public Squad(Character c1){
		squadName = c1.name;
		members.add(c1);
	}
	
	public Squad(String name, Character cLeader, Character c1, Character c2, Character c3){
		squadName = name;
		members.add(cLeader);
		members.add(c1);
		members.add(c2);
		members.add(c3);
	}
	
	public Character getCharById(int id) {
		for(Character c : members) {
			if (c.id == id) {
				return c;
			}
		}
		return null;
	}
	
}//end squad
