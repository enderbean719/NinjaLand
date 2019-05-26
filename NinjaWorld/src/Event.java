import java.io.Serializable;

 
public class Event implements Serializable{

	Character mc;
	
	public Event(Character mc) {
		this.mc = mc;
	}
	
	public boolean mcArrivedAtChar(Character input) {
		if(mc.getPosition_().getX() == input.getPosition_().getX() && mc.getPosition_().getY() == input.getPosition_().getY()	 ) {
			return true;
		}else {
			return false;
		}
	}


	
}//end event
