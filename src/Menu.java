
public class Menu {
	
	//TODO Match doesn't restart when a player wins

	private boolean menuChecker;
	private Input in;
	private boolean isPlayerMenu;
	private boolean isEndMenu;

	public Menu() {
		this.menuChecker = false;
		this.in = new Input();
		this.isEndMenu = false;
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
	
	public void showEndMenu() {
		this.isEndMenu = true;
		System.out.println("Do you want to go to the Main Menu?\n"
				+ "[1] Yes\n"
				+ "[2] No");
	}

	public void selectFromMenu() {
		try {
			in.insertInt();
		}
		catch(NumberFormatException ne) {
			System.out.println("The given value is not correct, please insert a numeric value");
		if(!isEndMenu) {
			if(isPlayerMenu) {
				showPlayerMenu();
				selectFromMenu();
			}
			else {
				showGameMenu();
				selectFromMenu();
			}
		}
		else {
			showEndMenu();
		}
	}
if(!isEndMenu) {
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
else {
	if(in.getInputInt() < 1 || in.getInputInt() > 2) {
		System.out.println("The given value is not correct, please select a number from 1 to 5.");
	     showEndMenu();
	}
}
	}

	public Input getInput() {
		return this.in;
	}

	public void setMenuChecker() {
		this.menuChecker = true;
	}
	
	public void deselectEndMenu() {
		this.isEndMenu = false;
	}

	public boolean getMenuChecker() {
		return this.menuChecker;
	}
}
