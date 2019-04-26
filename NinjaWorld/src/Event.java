
public class Event {

	Character mc;
	
	public Event(Character mc) {
		this.mc = mc;
	}
	
	public boolean arrivedAtCreature(Creature input) {
		if(mc.position_.x == input.position_.x && mc.position_.y == input.position_.y	 ) {
			return true;
		}else {
			return false;
		}
	}
	
}//end event
