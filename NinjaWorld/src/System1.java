import java.util.InputMismatchException;
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
	
	public int getInt(){
		int i = 0;
		try {
			i = reader.nextInt();
		}catch(InputMismatchException  e){
			System.out.println("Input error - please enter an integer.");
	        reader.nextLine();
			i = getInt();
		}
		return i;
	}
	
	public double getDecimal() {
		double d = 0;
		try {
			d = reader.nextDouble();
		}catch(InputMismatchException  e){
			System.out.println("Input error - please enter an decimal number.");
			reader.nextLine();
			d = getDecimal();
		}
		return d;			
	}
	
	public String getLine() {
		return reader.nextLine();
	}
	
	public String getWord() {
		return reader.next();
	}
	
	public int getIntBetween(int min, int max) {
		int i = 0;		
		i = getInt();
		while(i<min || i>max) {
			System.out.println("Please enter a number between " + min + " and " + max);
			i = getInt();
		}
		return i;
	}
	
	
}//end System1
