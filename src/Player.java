import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Player {

//TODO Create a Player interface, then classes HumanPlayer and RandomPlayer	
	
	private String name;
	private String pawn;
	private int input;
	private boolean isCustom;
	
	public Player() {
		
		this.isCustom = false;
	}
	 
	public void setName() {
		System.out.println("Choose your name");
		BufferedReader setname = new BufferedReader(new InputStreamReader(System.in));
		try {

			this.name = setname.readLine();	

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return this.name;
	}

	public void printName() {
		System.out.println(this.name);
	}

	public void setPawn() {	
		System.out.println("Choose your pawn");
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in)); 
		try {
			this.pawn = read.readLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String getPawn() {
		return this.pawn;
	}
	
	public void setIsCustom() {
		this.isCustom = true;
	}
	
	public boolean isCustomPlayer() {
		return this.isCustom;
	}

	public void setInput() {
	    if(isCustom) {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = Integer.parseInt(read.readLine()) - 1;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	    }
	    else {
	    	Random in = new Random();
	    	input = in.nextInt(7);
	    }
	}
	
	public int getInput() {
		return this.input;
	}

	
	

	//TODO TODO TODO  aggiungere tempo partita con lista migliori giocatori  ©Protected by Copyright -- Author Dario Polverini

}
