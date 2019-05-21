import java.util.ArrayList;
import java.io.Serializable;


public class Result implements Serializable{

	public ArrayList<String> log = new ArrayList<String>();
	public String summary;
	public String[] team1;  //mc team
	public String[] team2;
	public String[] team3;
	public String[] leftStanding;
	public String[] victors;
	public String[] ko;
	public String[] retreated;
	
	public double[] damageOutput;
	
	public boolean victory;
	
	Result(){
		
	}
	
	
}//end result
