public class Cell {

	private boolean check;
	private String simbol;


	public Cell() {

		this.check = false;

	}

	public boolean getCheck() {
		return this.check;
	}

	public void setCheck() {
		this.check = true;
	}

	public void setSimbol(String pawn) {	
		this.simbol = pawn;
	}

	public String getSimbol() {
		return this.simbol;
	}
}
