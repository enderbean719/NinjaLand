
public class Event {

	Character mc;
	
	public Event(Character mc) {
		this.mc = mc;
	}
	
	public boolean arrivedAtChar(Character input) {
		if(mc.position_.getX() == input.position_.getX() && mc.position_.getY() == input.position_.getY()	 ) {
			return true;
		}else {
			return false;
		}
	}
	
}//end event
