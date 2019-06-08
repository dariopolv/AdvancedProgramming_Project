import java.util.Random;

public class Player {

	private String name;
	private String pawn;
	private int input;
	private boolean isCustom;
	private Input in;

	public Player() {

		this.isCustom = false;
		this.in = new Input();
	}

	public void setName() {
		System.out.println("Choose your name");
		in.insertString();
		this.name = in.getInputString();

		if(name.length() == 0) {
			System.out.println("The name field can't be void");
			setName();
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
		in.insertString();
		this.pawn = in.getInputString();

		if(pawn.length() != 1) {
			System.out.println("The pawn length must be of one character only.");
			setPawn();
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
			try {
				in.insertInt();
			}
			catch(NumberFormatException ne) {
				System.out.println("The given value is not correct, please insert a numeric value.\nSelecting position:");
				setInput();
			}
			input = in.getInputInt() - 1;
			while(input < 0 || input > 6) {
				System.out.println("The given value is out of range, please insert a number from 1 to 7.\nSelecting position:");
				setInput();
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
