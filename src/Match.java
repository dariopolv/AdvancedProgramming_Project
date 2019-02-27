import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
public class Match{

	private Field field;
	private Player player1;
	private Player player2;
	private Player genericPlayer;
	private Menu menu;
	private ArrayList<Player> listCustomPlayers;
	private ArrayList<Player> listRandomPlayers;
	private PlayerSetting playerSetting;

	public Match() {
		field = new Field();
		player1 = new Player();
		player2 = new Player();
		menu = new Menu();
		listCustomPlayers = new ArrayList<Player>();
		listRandomPlayers = new ArrayList<Player>();
		playerSetting = new PlayerSetting();
	}

	public Field getBattleground() {
		return this.field;
	}

	public void start() {

		field.initializeField();

		menu.showPlayerMenu();
		menu.selectPlayerChoise();

		switch(menu.getPlayerChoise()) {

		case 1:
			System.out.println("Setting Custom Player: ");
			genericPlayer = new Player();
			genericPlayer.setIsCustom();
			playerSetting.addPlayer(genericPlayer, listCustomPlayers,listCustomPlayers);
			System.out.println("Custom Player List: ");
			playerSetting.printPlayers(listCustomPlayers);
			start();
			break;

		case 2: System.out.println("Setting Random Player: "); 
		genericPlayer = new Player();
		playerSetting.addPlayer(genericPlayer, listRandomPlayers,listCustomPlayers);
		System.out.println("Custom Player List: ");
		playerSetting.printPlayers(listRandomPlayers);
		start();
		break;

		case 3: gameMenu();
		break;

		case 4: System.out.println("Goodbye!");
		System.exit(0);

		}
	}

	public Player playerSelection(Player player, String playerMode, ArrayList<Player> list) {	
		System.out.println("Select "+ playerMode + " Player.");
		System.out.println();
		System.out.println(playerMode + " Player List:");
		playerSetting.printNotSelectedPlayers(playerSetting.getChoise(), list);
		player = playerSetting.selectPlayer(list);
		player = playerSetting.inputChecker(player, list);
		//	playerSetting.resetChoise();
		System.out.println();
		System.out.println("Player " + player.getName() + " selected.");
		System.out.println();
		return player;
	}

	public void gameMenu() {

		menu.showGameMenu();
		menu.selectChoise();

		switch(menu.getChoise()) { 
		case 1:

			if(playerSetting.checkListSize(listCustomPlayers, menu.getChoise())) {

				player1 = playerSelection(player1, "Custom", listCustomPlayers);
				player2 = playerSelection(player2, "Custom", listCustomPlayers);
				System.out.println("Starting game");
				try {
					TimeUnit.SECONDS.sleep(2);
				}
				catch(InterruptedException e) {
					e.fillInStackTrace();
				}
				break;
			}
			else {
				start();
				break;
			}
		case 2:
			if(
					playerSetting.checkListSize(listCustomPlayers, menu.getChoise()) &&
					playerSetting.checkListSize(listRandomPlayers, menu.getChoise())) {

				player1 = playerSelection(player1, "Custom", listCustomPlayers);
				player2 = playerSelection(player2, "Random", listRandomPlayers);
				System.out.println("Starting game");
				try {
					TimeUnit.SECONDS.sleep(2);
				}
				catch(InterruptedException e) {
					e.fillInStackTrace();
				}	
				break; 	
			}
			else {
				start();
				break;
			}
		case 3:
			if(playerSetting.checkListSize(listRandomPlayers, menu.getChoise())) {	

				playerSelection(player1, "Random",listRandomPlayers);
				playerSelection(player2, "Random",listRandomPlayers);				
				System.out.println("Starting game");
				try {
					TimeUnit.SECONDS.sleep(2);
				}
				catch(InterruptedException e) {
					e.fillInStackTrace();
				}
				break;
			}
			else {
				start();
				break;
			}

		case 4: start();
		break;

		case 5:
			System.out.println("Goodbye!");
			System.exit(0);
		}
	}


	/*	public void move() {
		if(field.getBool() == false) {
			System.out.println("Player " + player1.getName()+ " moving...\nSelect position: \n");
			player1.setInput();
			field.check(player1.getPawn(), player1.getInput());
			field.printField();
			field.checkWinner(player1, player1.getPawn(), field.analizeLine(player1), player1.getInput());

			field.checkOrizontal(player1, field.analizeLine(player1), player1.getPawn());
			field.checkDiagonal(field.analizeLine(player1), player1.getInput(), player1, player1.getPawn());
			field.checkVertical(player1, player1.getPawn());

		}
		if(field.getBool() == false) {
			System.out.println("Player " + player2.getName()+ " moving...\nSelect position: \n");
			player2.setInput();
			field.check(player2.getPawn(), player2.getInput());
			field.printField();
			field.checkWinner(player2, player2.getPawn(), field.analizeLine(player2), player2.getInput());
			/*
			field.checkOrizontal(player2, field.analizeLine(player2), player2.getPawn());
			field.checkDiagonal(field.analizeLine(player2), player2.getInput(), player2, player2.getPawn());
			field.checkVertical(player2, player2.getPawn());

		}
	}
	 */
	public void play(){
		if(field.getBool() == false) {
			System.out.println("Player " + player1.getName()+ " moving...\nSelecting position: \n");
			player1.setInput();
			field.check(player1.getPawn(), player1.getInput());
			if(player1.isCustomPlayer() == false) {
				try {
					TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e) {
					e.fillInStackTrace();
				}
			}
			field.printField();
			field.checkWinner(player1, player1.getPawn(), field.analizeLine(player1), player1.getInput());

			if(field.getBool() == false) {
				System.out.println("Player " + player2.getName()+ " moving...\nSelecting position: \n");
				player2.setInput();
				field.check(player2.getPawn(), player2.getInput());
				if(player2.isCustomPlayer() == false) {
					try {
						TimeUnit.SECONDS.sleep(1);
					}
					catch(InterruptedException e) {
						e.fillInStackTrace();
					}
				}
				field.printField();
				field.checkWinner(player2, player2.getPawn(), field.analizeLine(player2), player2.getInput());
			}
		}
	}
}


