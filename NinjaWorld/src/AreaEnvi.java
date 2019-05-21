import java.io.Serializable;

 
public class AreaEnvi implements Serializable{
	private Area a;
	private String envi;
	
	
	public AreaEnvi(Area aa, String ee) {
		a = aa;
		envi = ee;
	}
	
	
	public void getCharactersInAreaEnvi() {
		
	}

	
	

	public Area getA() {
		return a;
	}


	public void setA(Area a) {
		this.a = a;
	}


	public String getEnvi() {
		return envi;
	}


	public void setEnvi(String envi) {
		this.envi = envi;
	}

}//AreaEnvi
