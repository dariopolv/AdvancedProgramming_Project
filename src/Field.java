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

	//	
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

		if(bool == false) {
			counter = 0;
			int line_pos = line;
			for(int i = input; i >= 0; i--) {
				if(field[line_pos][i].getSimbol() == symbol) {
					counter++;
					if(line_pos != 0) {
						line_pos--;
					}
					if(counter == 4) {
						bool = true;
						System.out.println(player.getName() + " Won");
					}
				}

				else {
					counter = 0;
				}
			}
			counter = 0;
			line_pos = line;
			for(int j = input; j < vertical_size; j++) {
				if(field[line_pos][j].getSimbol() == symbol) {
					counter++;
					if(line_pos != 5) {
						line_pos++;
					}
					if(counter == 4) {
						bool = true;
						System.out.println(player.getName() + " won");
					}
				}
				else {
					counter = 0;
				}
			}
			counter = 0;
			line_pos = line;
			for(int n = input; n < vertical_size; n++) {
				if(field[line_pos][n].getSimbol() == symbol) {
					counter++;
					if(line_pos != 0) {
						line_pos--;
					}
					if(counter == 4) {
						bool = true;
						System.out.println(player.getName() + " Won");
					}
				}
				else {
					counter = 0;
				}
			}
			counter = 0;
			line_pos = line; 
			for(int m = input; m >= 0; m--) {
				if(field[line_pos][m].getSimbol() == symbol) {
					counter++;
					if(line_pos != 5) {
						line_pos++;
					}
					if(counter == 4) {
						bool = true;
						System.out.println(player.getName() + " Won");
					}

				}
				else {
					counter = 0;
				}	
			}
		}
	}


	/*
	public void checkVertical(Player player, String symbol) {
		if(bool == false) {
			int counter = 0;
			for(int i = 0; i < 6; i ++) {
				if(field[i][player.getInput()].getSimbol() == symbol && field[i][player.getInput()] != null) {
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
	}

	public void checkOrizontal(Player player, int position, String symbol) {
		if(bool == false) {
			int counter = 0;
			for(int i = 0; i < 7; i++) {
				if(field[position][i].getSimbol() == symbol) {
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
	}

	public void checkDiagonal(int line, int input, Player player, String symbol) {

		if(bool == false) {
			int counter = 0;
			int line_pos = line;
			for(int i = input; i >= 0; i--) {
				if(field[line_pos][i].getSimbol() == symbol) {
					counter++;
					if(line_pos != 0) {
						line_pos--;
					}
					if(counter == 4) {
						bool = true;
						System.out.println(player.getName() + " Won");
					}
				}

				else {
					counter = 0;
				}
			}
			line_pos = line;
			int cont = 0;
			for(int j = input; j < vertical_size; j++) {
				if(field[line_pos][j].getSimbol() == symbol) {
					cont++;
					if(line_pos != 5) {
						line_pos++;
					}
					if(cont == 4) {
						bool = true;
						System.out.println(player.getName() + " won");
					}
				}
				else {
					cont = 0;
				}
			}



			int count = 0;
			line_pos = line;
			for(int n = input; n < vertical_size; n++) {
				if(field[line_pos][n].getSimbol() == symbol) {
					count++;
					if(line_pos != 0) {
						line_pos--;
					}
					if(count == 4) {
						bool = true;
						System.out.println(player.getName() + " Won");
					}
				}
				else {
					count = 0;
				}

			}

			int contatore = 0;
			line_pos = line; 
			for(int m = input; m >= 0; m--) {
				if(field[line_pos][m].getSimbol() == symbol) {
					contatore++;
					if(line_pos != 5) {
						line_pos++;
					}
					if(contatore == 4) {
						bool = true;
						System.out.println(player.getName() + " Won");
					}

				}
				else {
					contatore = 0;
				}	
			}
		}
	}
	 */

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



