public class Field {
	private static final int vertical_size = 7;
	private static final int orizontal_size = 6;
	private Cell[][] field;
	private boolean bool;


	public Field() {
		this.field = new Cell [orizontal_size][vertical_size];
		this.bool = false;
	}

	public int getVerticalSize() {
		return vertical_size;
	}

	public int getOrizontalSize() {
		return orizontal_size;
	}

	public Cell[][] getField() {
		return this.field;
	}

	public boolean getBool() {
		return this.bool;
	}

	//Initialization of field as a matrix of cells		
	public void initializeField() {
		this.bool = false;
		for(int i = 0; i < getOrizontalSize(); i++) {
			for(int j = 0; j < getVerticalSize(); j++) {
				field[i][j] = new Cell();
			}
		}

	}

	//Check if the cell is empty or filled, if empty it adds a pawn in the cell	
	public void check(String pawn, int input) {
		for(int i = 0; i < 6 ; i++) {
			if(field[i][input].getCheck() == false && field[i][input].getSimbol() == null) {
				field[i][input].setSimbol(pawn);
				field[i][input].setCheck();
				break;
			}
		}
	}

	//When the player select the input returns the line to take the right position	
	public int analizeLine(Player player) {
		int count = 0;
		for(int i = 0; i < 6; i++) {
			if(field[i][player.getInput()].getSimbol() != null) {
				count = i;
			}
			else {
				break;
			}
		}
		return count;
	}

	//check all the possible combinations of four identical symbols in a line(orizontal, vertical or oblique
	public void checkWinner(Player player, String symbol, int line, int input) {
		int counter = 0;

		//Vertical control 
		if(bool == false) {
			for(int i = 0; i < 6; i ++) {
				if(field[i][player.getInput()].getSimbol() == symbol && 
						field[i][player.getInput()] != null) {
					counter = counter + 1;
					if(counter == 4) {
						bool = true;
						System.out.println(player.getName()+ " won!");
					}	
				}
				else {
					counter = 0;
				}
			}
		}

		//Orizontal control
		if(bool == false) {
			counter = 0;
			for(int i = 0; i < 7; i++) {
				if(field[line][i].getSimbol() == symbol) {
					counter++;
					if(counter == 4)  {
						bool = true;
						System.out.println(player.getName()+ " Won");
					}
				}
				else {
					counter = 0;
				}
			}
		}

		//Oblique control
		if(bool == false) {
			counter = 0;
			int value = input - line;
			if(input >= line) {
				for(int i = 0; i <= 5; i++) {
					if(field[i][value].getSimbol() == symbol) {
						counter++;
						if(value < 6) {
							value++;
						}
						else {
							i = 5;
						}
						if(counter == 4) {
							bool = true;
							System.out.println(player.getName() + " Won");
						}
					}
					else {
						counter = 0;
						if(value < 6)
							value++;
					}
				}
			}
			else {
				value = line - input;
				for(int j = 0; j <= 5; j++) {
					if(field[value][j].getSimbol() == symbol) {
						counter++;
						if(value < 5) {
							value++;
						}
						else {
							j = 5;
						}
						if(counter == 4) {
							bool = true;
							System.out.println(player.getName() + " Won");
						}
					}
					else {
						counter = 0;
						if(value < 5) {
							value++;
						}

					}
				}
			}
		}

		if(bool == false) {
			counter = 0;
			int val = input + line;
			int limit = input+line;
			if(limit >= 6) {
				limit = 5;
			}
			if(val < 6) {
				for(int i = 0; i <= limit; i++) {
					if(field[i][val].getSimbol() == symbol) {
						counter++;
						val--;
						if(counter == 4) {
							bool = true;
							System.out.println(player.getName() + " Won");
						}
					}
					else {
						counter = 0;
						val--;
					}
				}
			}
			else {
				int down_limit = 6;
				int	start_line = val - down_limit;

				for(int i = start_line; i <= limit; i++ ) {
					if(field[i][down_limit].getSimbol() == symbol){
						counter++;
						down_limit--;
						if(counter == 4) {
							bool = true;
							System.out.println(player.getName() + " Won");
						}
					}
					else {
						counter = 0;
						down_limit--;
					}
				}

			}

		}

	}

	//Print the field 
	public void printField() {

		for(int i = 5; i >= 0; i--) {

			System.out.println("*---*---*---*---*---*---*---*");
			for(int j = 0; j < 7; j++) {
				if(field[i][j].getCheck()) {
					System.out.print("| "+field[i][j].getSimbol()+" ");
				}
				else {
					System.out.print("|   ");
				}
			}


			System.out.println("|");
		}
		System.out.println("*---*---*---*---*---*---*---*");
		System.out.println("  1   2   3   4   5   6   7");
	}
}
