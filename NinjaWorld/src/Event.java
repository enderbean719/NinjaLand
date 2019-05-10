
public class Event {

	Character mc;
	
	public Event(Character mc) {
		this.mc = mc;
	}
	
	public boolean arrivedAtChar(Character input) {
		if(mc.getPosition_().getX() == input.getPosition_().getX() && mc.getPosition_().getY() == input.getPosition_().getY()	 ) {
			return true;
		}else {
			return false;
		}
	}
	
}//end event
