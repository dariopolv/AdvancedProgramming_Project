import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Menu {

	private int choise;
	private int choisePlayerMenu;
	private boolean menuChecker;
	
	public Menu() {
		this.menuChecker = false;
	}

	public void showGameMenu() {
		System.out.println("Press one of the following numbers to enter the option: ");
		System.out.println("[1] - Start Game: Custom vs Custom");
		System.out.println("[2] - Start Game: Custom vs Random");
		System.out.println("[3] - Start Game: Random vs Random");
		System.out.println("[4] - Back.");
		System.out.println("[5] - Exit.");
	}
	
	public void showPlayerMenu() {
		System.out.println("Press one of the following numbers to enter the option: ");
		System.out.println("[1] - Create new Custom Player.");
		System.out.println("[2] - Create new Random Player.");
	    System.out.println("[3] - Play.");
	    System.out.println("[4] - Exit.");
	}

	public void selectChoise() {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			choise = Integer.parseInt(read.readLine());
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		catch(NumberFormatException ne) {
			System.out.println("The given value is not correct, please insert a numeric value");
		    showGameMenu();
		    selectChoise();
		}
	}
	
	public void selectPlayerChoise() {
		BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
		try {
			choisePlayerMenu = Integer.parseInt(readin.readLine());
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
		catch(NumberFormatException ne) {
			System.out.println("The given value is not correct, please insert a numeric value");
		    showPlayerMenu();
			selectPlayerChoise();
		}
		
		
	}
	
	public int getChoise() {
		return this.choise;
	}
	
	public int getPlayerChoise() {
		return this.choisePlayerMenu;
	}
	
	public void setMenuChecker() {
		this.menuChecker = true;
	}
	 
	public boolean getMenuChecker() {
		return this.menuChecker;
	}
}
