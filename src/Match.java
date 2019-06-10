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
	private Input in;

	public Match() {
		field = new Field();
		player1 = new Player();
		player2 = new Player();
		menu = new Menu();
		listCustomPlayers = new ArrayList<Player>();
		listRandomPlayers = new ArrayList<Player>();
		playerSetting = new PlayerSetting();
		in = menu.getInput();
	}

	//It runs the game from the beginning
	//This function is called only if the game is restarted
	public void runGame() {
		start();
		while(getBattleground().getBool() == false) {	 
			play();
		}
	}

	public Field getBattleground() {
		return this.field;
	}

	//Setting player name, pawn and add it in the right list	
	public void setPlayer(String playerMode) {
		System.out.println("Setting " + playerMode + " Player: ");
		genericPlayer = new Player();
		if(playerMode == "Custom") {
			genericPlayer.setIsCustom();
		}
		playerSetting.addPlayer(genericPlayer, listCustomPlayers,listRandomPlayers);
		System.out.println(playerMode + " Player List: ");
		if(playerMode == "Custom") {
			playerSetting.printPlayers(listCustomPlayers);
			System.out.println();
		}
		else {
			playerSetting.printPlayers(listRandomPlayers);
			System.out.println();
		}
		start();
	}

	//Initialization of the field and choice from the menu. Each choice will set the player as Custom or as Random
	public void start() {
		field.initializeField();
		menu.showPlayerMenu();
		menu.selectFromMenu();

		switch(in.getInputInt()) {

		case 1:
			setPlayer("Custom");
			break;

		case 2:
			setPlayer("Random");
			break;

		case 3: gameMenu();
		break;

		case 4:
			System.out.println("Custom Player List: ");
			playerSetting.printPlayers(listCustomPlayers);
			System.out.println("Random Player List:");
			playerSetting.printPlayers(listRandomPlayers);
			start();
			break;

		case 5: System.out.println("Goodbye!");
		System.exit(0);

		}
	}

	//TODO: Sort doesn't work
	//Player selection from the right list
	public Player playerSelection(Player player, String playerMode, ArrayList<Player> list) {
		System.out.println("Select "+ playerMode + " Player.");
		System.out.println();
		System.out.println(playerMode + " Player List:");
		playerSetting.printNotSelectedPlayer(list);
		player = playerSetting.selectPlayer(list);
		player = playerSetting.inputChecker(player, list);
		playerSetting.sortSelectedPlayer(list, player);
		System.out.println();
		System.out.println("Player " + player.getName() + " selected.");
		System.out.println();
		return player;
	}

	//Prepare the game to start, last checks for playing in one of the mod in the right way
	public void gameMenu() {

		menu.showGameMenu();
		menu.selectFromMenu();

		switch(in.getInputInt()) {
		case 1:
			if(playerSetting.checkListSize(listCustomPlayers, in.getInputInt())) {

				player1 = playerSelection(player1, "Custom", listCustomPlayers);
				player2 = playerSelection(player2, "Custom", listCustomPlayers);
				System.out.println("Starting game");
				try {
					TimeUnit.SECONDS.sleep(2);
					field.printField();
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
					playerSetting.checkListSize(listCustomPlayers, in.getInputInt()) &&
					playerSetting.checkListSize(listRandomPlayers, in.getInputInt())) {

				player1 = playerSelection(player1, "Custom", listCustomPlayers);
				player2 = playerSelection(player2, "Random", listRandomPlayers);
				System.out.println("Starting game");
				try {
					TimeUnit.SECONDS.sleep(2);
					field.printField();
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
			if(playerSetting.checkListSize(listRandomPlayers, in.getInputInt())) {	

				player1 = playerSelection(player1, "Random",listRandomPlayers);
				player2 = playerSelection(player2, "Random",listRandomPlayers);				
				System.out.println("Starting game");
				try {
					TimeUnit.SECONDS.sleep(2);
					field.printField();
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

	//The field variable is set as false until there will be a winner.
	//The method check if the move is allowed and it perform it.
	public void move(Player player) {
		if(field.getBool() == false) {
			System.out.println("Player " + player.getName()+ " moving...\nSelecting position: \n");
			player.setInput();
			while(field.analizeLine(player) == 5 && 
				  field.getField()[field.analizeLine(player)][player.getInput()] != null) {
				System.out.println("The current column is full, choose another.");
				System.out.println("Player " + player.getName()+ " moving...\nSelecting position: \n");
				player.setInput();
			}
			field.check(player.getPawn(), player.getInput());
			if(player.isCustomPlayer() == false) {
				try {
					TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e) {
					e.fillInStackTrace();
				}
			}
			field.printField();
			field.checkWinner(player, player.getPawn(), field.analizeLine(player), player.getInput());
			player.deselectPlayer();
			endGame();
		}
	}

	//Starts the game:
	public void play(){

		move(player1);
		move(player2);
	}

	public void endGame() {
		if(field.getBool()) {
			menu.showEndMenu();
			menu.selectFromMenu();
			switch(in.getInputInt()) {
			case 1: 
				menu.deselectEndMenu();
				start();
				while(getBattleground().getBool() == false) {	 
					play();
				}
				break;
			case 2: System.out.println("Goodbye!");
			System.exit(0);
			}
		}
	}
}