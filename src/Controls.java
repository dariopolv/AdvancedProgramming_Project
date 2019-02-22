
public class Controls {
	private Match match;
	private Field field;
	private boolean gameWon;
	
	public Controls() {
		this.gameWon = false;
	}
	
	public boolean getGameWon() {
		return this.gameWon;

	}
	
	public void print() {
	 System.out.println(field.getField()[0][0].getSimbol());
	}
	
	public void checkWinner(Player player, String symbol, int line, int input) {
		int counter = 0;
		if(gameWon == false) {
			for(int i = 0; i < 6; i ++) {
				if(match.getBattleground().getField()[i][player.getInput()].getSimbol() == symbol && 
				   match.getBattleground().getField()[i][player.getInput()] != null) {
					counter = counter + 1;
					if(counter == 4) {
						gameWon = true;
						System.out.println(player.getName()+ " won!");
					}	
				}
				else {
					counter = 0;
				}
			}
		}
		if(gameWon == false) {
			counter = 0;
			for(int i = 0; i < 7; i++) {
				if(match.getBattleground().getField()[line][i].getSimbol() == symbol) {
					counter++;
					if(counter == 4)  {
						gameWon = true;
						System.out.println(player.getName()+ " Won");
					}
				}
				else {
					counter = 0;
				}
			}
		}
		
		if(gameWon == false) {
			counter = 0;
			int line_pos = line;
			for(int i = input; i >= 0; i--) {
				if(match.getBattleground().getField()[line_pos][i].getSimbol() == symbol) {
					counter++;
					if(line_pos != 0) {
						line_pos--;
					}
					if(counter == 4) {
						gameWon = true;
						System.out.println(player.getName() + " Won");
					}
				}

				else {
					counter = 0;
				}
			}
			counter = 0;
			line_pos = line;
			for(int j = input; j < match.getBattleground().getVerticalSize(); j++) {
				if(match.getBattleground().getField()[line_pos][j].getSimbol() == symbol) {
					counter++;
					if(line_pos != 5) {
						line_pos++;
					}
					if(counter == 4) {
						gameWon = true;
						System.out.println(player.getName() + " won");
					}
				}
				else {
					counter = 0;
				}
			}
			counter = 0;
			line_pos = line;
			for(int n = input; n < match.getBattleground().getVerticalSize(); n++) {
				if(match.getBattleground().getField()[line_pos][n].getSimbol() == symbol) {
					counter++;
					if(line_pos != 0) {
						line_pos--;
					}
					if(counter == 4) {
						gameWon = true;
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
				if(match.getBattleground().getField()[line_pos][m].getSimbol() == symbol) {
					counter++;
					if(line_pos != 5) {
						line_pos++;
					}
					if(counter == 4) {
						gameWon = true;
						System.out.println(player.getName() + " Won");
					}

				}
				else {
					counter = 0;
				}	
			}
		}
	}
}
