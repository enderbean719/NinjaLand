import java.util.Scanner;

public class System1 {

	Scanner reader = new Scanner(System.in);
	
	//prints 1 line to the screen and ends the line
	public void out(String input) {
		System.out.println(input);
	}
	
	//prints input, but doens't end the line
	public void print(String input) {
		System.out.print(input);
	}
	
	public int getInt() {
		return reader.nextInt();
	}
	
	public double getDecimal() {
		return reader.nextDouble();
	}
	
	public String getLine() {
		return reader.nextLine();
	}
	
	public String getWord() {
		return reader.next();
	}
}
