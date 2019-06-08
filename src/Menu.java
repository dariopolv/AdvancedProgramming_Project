
public class Menu {
	
	//TODO Match doesn't restart when a player wins

	private boolean menuChecker;
	private Input in;
	private boolean isPlayerMenu;

	public Menu() {
		this.menuChecker = false;
		this.in = new Input();
	}

	public void showPlayerMenu() {
		this.isPlayerMenu = true;
		System.out.println("Press one of the following numbers to enter the option: ");
		System.out.println("[1] - Create new Custom Player.");
		System.out.println("[2] - Create new Random Player.");
		System.out.println("[3] - Play.");
		System.out.println("[4] - Show Player Lists.");
		System.out.println("[5] - Exit.");
	}

	public void showGameMenu() {
		this.isPlayerMenu = false;
		System.out.println("Press one of the following numbers to enter the option: ");
		System.out.println("[1] - Start Game: Custom vs Custom");
		System.out.println("[2] - Start Game: Custom vs Random");
		System.out.println("[3] - Start Game: Random vs Random");
		System.out.println("[4] - Back.");
		System.out.println("[5] - Exit.");
	}

	public void selectFromMenu() {
		try {
			in.insertInt();
		}
		catch(NumberFormatException ne) {
			System.out.println("The given value is not correct, please insert a numeric value");
			if(isPlayerMenu) {
				showPlayerMenu();
				selectFromMenu();
			}
			else {
				showGameMenu();
				selectFromMenu();
			}
		}

		if(in.getInputInt() < 1 || in.getInputInt() > 5) {
			System.out.println("The given value is not correct, please select a number from 1 to 5.");
			if(isPlayerMenu) {
				showPlayerMenu();
				selectFromMenu();
			}
			else {
				showGameMenu();
				selectFromMenu();	
			}
		}
	}

	public Input getInput() {
		return this.in;
	}

	public void setMenuChecker() {
		this.menuChecker = true;
	}

	public boolean getMenuChecker() {
		return this.menuChecker;
	}
}
