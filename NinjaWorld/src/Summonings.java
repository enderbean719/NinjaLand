import java.util.ArrayList;
import java.io.Serializable;


public class Summonings implements Serializable{

	private ArrayList<Character> members = new ArrayList<Character>();
	
	Summonings(){
		
	}

	public ArrayList<Character> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Character> members) {
		this.members = members;
	}
	
}//end Summonings
