import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.io.Serializable;


public class System1 implements Serializable{


	private boolean debug = false;


	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void clear() {
//		System.out.println("\033[H\033[2J");  
//	    System.out.flush(); 
	}

	public void pause() {
		Scanner reader = new Scanner(System.in);
		this.out("");
		this.out("....................................................<press ENTER to continue>");
		this.out("");
		try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
	}
	
	
	public static Object deepClone(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}//deepClone
	
	//prints 1 line to the screen and ends the line
	public void out(String input)  {		
		System.out.println(input);
		int count = 0;
		
		
	}//end out
	
	//prints input, but doens't end the line
	public void print(String input) {
		System.out.print(input);
	}
	
	public int getInt(){
		Scanner reader = new Scanner(System.in);
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
		Scanner reader = new Scanner(System.in);
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
		Scanner reader = new Scanner(System.in);
		return reader.nextLine();
	}
	
	public String getWord() {
		Scanner reader = new Scanner(System.in);
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
	
	public int getRandomIntBetween(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("max must be greater or equal to  min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;		
	}//end random

	 
	
	
}//end System1
