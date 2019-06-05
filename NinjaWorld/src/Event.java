import java.io.Serializable;

 
public class Event implements Serializable{

	public Character mc;
	
	public Event(Character mc) {
		this.mc = mc;
	}
	
	public boolean mcArrivedAtChar(Character input) {
		if(mc.getPosition_().getX() == input.getPosition_().getX() && mc.getPosition_().getY() == input.getPosition_().getY()	 ) {
			return true;
		}else {
			return false;
		}
	}//mcArrivedAtChar

	public boolean mcNextToChar(Character input){
		int mcX = mc.getPosition_().getX();
		int mcY = mc.getPosition_().getY();
		int iX = input.getPosition_().getX();
		int iY =  input.getPosition_().getY();

		if( Math.abs(mcX - iX) <=1 &&    Math.abs(mcY - iY) <=1 	 ) {
			return true;
		}else {
			return false;
		}
	}//mcNextToChar

	public boolean mcWithinRangeofChar(Character input, int range){
		int mcX = mc.getPosition_().getX();
		int mcY = mc.getPosition_().getY();
		int iX = input.getPosition_().getX();
		int iY =  input.getPosition_().getY();

		if( Math.abs(mcX - iX) <= range &&    Math.abs(mcY - iY) <= range 	 ) {
			return true;
		}else {
			return false;
		}
	}//mcWithinRangeofChar



}//end event
