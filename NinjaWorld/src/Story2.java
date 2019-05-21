import java.io.Serializable;

  
public class Story2 implements Story, Serializable{

	@Override
	public void start_(Character mc) {
		// SAM BEGIN HERE
		System1 s = new System1();
		s.out("Story2 begin");
	}

	 
}
