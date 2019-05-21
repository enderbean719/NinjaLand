import java.io.Serializable;

  
public class Mission2 implements Story, Serializable{

	@Override
	public void start_(Character mc) {
		//CHRIS START HERE
		System1 s = new System1();
		s.out("Mission begin");
	}

}
