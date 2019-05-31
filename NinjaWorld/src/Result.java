import java.util.ArrayList;
import java.io.Serializable;


public class Result implements Serializable{

	private boolean victory;

	private ArrayList<String> log = new ArrayList<String>();
	private String summary;
	private String[] team1;  //mc team
	private String[] team2;
	private String[] team3;
	private String[] leftStanding;
	private String[] victors;
	private String[] ko;
	private String[] retreated;

	private double[] damageOutput;


	
	Result(){
		
	}






	public ArrayList<String> getLog() {
		return log;
	}

	public void setLog(ArrayList<String> log) {
		this.log = log;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String[] getTeam1() {
		return team1;
	}

	public void setTeam1(String[] team1) {
		this.team1 = team1;
	}

	public String[] getTeam2() {
		return team2;
	}

	public void setTeam2(String[] team2) {
		this.team2 = team2;
	}

	public String[] getTeam3() {
		return team3;
	}

	public void setTeam3(String[] team3) {
		this.team3 = team3;
	}

	public String[] getLeftStanding() {
		return leftStanding;
	}

	public void setLeftStanding(String[] leftStanding) {
		this.leftStanding = leftStanding;
	}

	public String[] getVictors() {
		return victors;
	}

	public void setVictors(String[] victors) {
		this.victors = victors;
	}

	public String[] getKo() {
		return ko;
	}

	public void setKo(String[] ko) {
		this.ko = ko;
	}

	public String[] getRetreated() {
		return retreated;
	}

	public void setRetreated(String[] retreated) {
		this.retreated = retreated;
	}

	public double[] getDamageOutput() {
		return damageOutput;
	}

	public void setDamageOutput(double[] damageOutput) {
		this.damageOutput = damageOutput;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}


}//end result
