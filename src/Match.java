import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Match{

	private Field field;
	private Player player1;
	private Player player2;
	private Player genericPlayer;
	private Menu menu;
	private ArrayList<Player> listCustomPlayers;
	private ArrayList<Player> listRandomPlayers;

	public Match() {
		field = new Field();
		player1 = new Player();
		player2 = new Player();
		menu = new Menu();
		listCustomPlayers = new ArrayList<Player>();
		listRandomPlayers = new ArrayList<Player>();
	}

	public Field getBattleground() {
		return this.field;
	}

	public void addPlayer(Player p, ArrayList<Player> list) {
		p.setName();
		p.setPawn();
		list.add(p);
		System.out.println("Player " + p.getName() + " created.");
	}

	public Player selectPlayer(ArrayList<Player> list) {
		int choise = 0;
		for(int i = 0; i < list.size(); i++) {
			System.out.print("["+i+"] - "+"Player: "+list.get(i).getName()+"      Pawn: ");
			System.out.println(list.get(i).getPawn());
		}
		BufferedReader readChoise = new BufferedReader(new InputStreamReader(System.in));	
		try {
			choise = Integer.parseInt(readChoise.readLine());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return list.get(choise);
	}

	public void printPlayers(ArrayList<Player> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print("["+i+"] - "+"Player: "+list.get(i).getName()+"      Pawn: ");
			System.out.println(list.get(i).getPawn());
		}
	}

	public boolean checkListSize(ArrayList<Player> list, int playerChoise) {
		boolean checkSize = false;
		switch(playerChoise) {
		case 1: if(list.size() < 2) {
			System.out.println("You have to create at least 2 Custom Players to play this mod.");
			break;
		}

		else {
			checkSize = true;
			break;
		}

		case 2: if(list.size() < 1) {
			System.out.println("You have to create at least 1 Custom Player and 1 Random Player to play this mod.");
			break;
		}
		else {
			checkSize = true;
			break;
		}

		case 3: 
			if(list.size() < 2) {
				System.out.println("You have to create at least 2 Random Players to play this mod.");
				break;
			}
			else {
				checkSize = true;
				break;
			}
		}
		return checkSize;
	}

	public void start() {

		field.initializeField();

		menu.showPlayerMenu();
		menu.selectPlayerChoise();

		switch(menu.getPlayerChoise()) {

		case 1:
			System.out.println("Setting Custom Player: ");
			genericPlayer = new Player(); genericPlayer.setIsCustom();
			addPlayer(genericPlayer, listCustomPlayers);
			System.out.println("Custom Player List: ");
			printPlayers(listCustomPlayers);
			start();
			break;

		case 2: System.out.println("Setting Random Player: "); 
		genericPlayer = new Player();
		addPlayer(genericPlayer, listRandomPlayers);
		System.out.println("Custom Player List: ");
		printPlayers(listRandomPlayers);
		start();
		break;

		case 3: gameMenu();
		break;

		}




	}



	public void gameMenu() {

		menu.showGameMenu();
		menu.selectChoise();
		
			switch(menu.getChoise()) { 
			case 1:
				if(checkListSize(listCustomPlayers, menu.getChoise())) {
				System.out.println("Select Custom Player.");
				System.out.println();
				player1 = selectPlayer(listCustomPlayers);
				System.out.println();
				System.out.println("Player " + player1.getName() + " selected.");
				System.out.println();
				System.out.println("Select Custom Player.");
				System.out.println();
				player2 = selectPlayer(listCustomPlayers);
				System.out.println();
				System.out.println("Player " + player2.getName() + " selected.");
				break;
				}
				else {
					start();
					break;
				}
			case 2:
				if(
				checkListSize(listCustomPlayers, menu.getChoise()) &&
				checkListSize(listRandomPlayers, menu.getChoise())) {
				System.out.println("Select Custom Player."); 
				System.out.println();
				player1 = selectPlayer(listCustomPlayers);
				System.out.println("Player " + player1.getName() + " selected.");
				System.out.println("Select Random Player.");
				player2 = selectPlayer(listRandomPlayers);
				System.out.println("Player " + player2.getName() + " selected.");
				break; 
				}
				else {
					start();
					break;
				}
			case 3:
				if(checkListSize(listRandomPlayers, menu.getChoise())) {	
				System.out.println("Select Random Player.");
				System.out.println();
				player1 = selectPlayer(listRandomPlayers);
				System.out.println("Player " + player1.getName() + " selected.");
				System.out.println();
				System.out.println("Select Random Player.");
				System.out.println();
				player2 = selectPlayer(listRandomPlayers);
				System.out.println("Player " + player2.getName() + " selected.");
				System.out.println();
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


