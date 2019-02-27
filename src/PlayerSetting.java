import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PlayerSetting {

	private int choise;	


	public PlayerSetting() {
		this.choise = 1000;
	}

	public int getChoise() {
		return this.choise;
	}

	public boolean equals(String nameFromList, String name) {
		boolean check = false;
		if(nameFromList.length() == name.length()) {
			for(int i = 0; i < name.length(); i++) {
				if(nameFromList.charAt(i) == name.charAt(i)) {
					check = true;

				}
				else {
					check = false;

					break;
				}
			}
		}
		return check;
	}


	public void printNotSelectedPlayers(int selected, ArrayList<Player> list) {
		for(int i = 0; i < list.size(); i++) {
			if(selected == i) {
				i++;
			}
			System.out.print("["+i+"] - "+"Player: "+list.get(i).getName()+"      Pawn: ");
			System.out.println(list.get(i).getPawn());
		}
		if(list.size() == 1 && selected == 0) {
			System.out.print("["+selected+"] - "+"Player: "+list.get(selected).getName()+"      Pawn: ");
			System.out.println(list.get(selected).getPawn());
		}
	}

	public Player selectPlayer(ArrayList<Player> list) {

		BufferedReader readChoise = new BufferedReader(new InputStreamReader(System.in));

		try {
			choise = Integer.parseInt(readChoise.readLine());
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		catch(NumberFormatException ne) {
			System.out.println("The given value is not correct, please insert a numeric value");
			return null;

		}

		if(choise > list.size()-1 || choise < 0) {
			return null;
		}

		return list.get(choise);
	}

	public Player inputChecker(Player player, ArrayList<Player> list) {

		while(player == null) {
			player = selectPlayer(list);
		}
		return player;
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

	public void printPlayers(ArrayList<Player> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print("["+i+"] - "+"Player: "+list.get(i).getName()+"      Pawn: ");
			System.out.println(list.get(i).getPawn());
		}
	}

	public void addPlayer(Player p, ArrayList<Player> list, ArrayList<Player> list2) {
		p.setName();
		for(int i = 0; i < list.size(); i++) {
			while(equals(list.get(i).getName(),p.getName())) {
				i = 0;
				System.out.println("This name is already token by a Custom Player, please choose another one. ");
				System.out.println("Custom Player List:");
				printPlayers(list);
				p.setName();
			}
		}	

		for(int i = 0; i < list2.size(); i++) {
			while(equals(list2.get(i).getName(),p.getName())) {
				i = 0;
				System.out.println("This name is already token by a Random Player, please choose another one. ");
				System.out.println("Random Player List:");
				printPlayers(list2);
				p.setName();
			}
		}	

		p.setPawn();
		for(int i = 0; i < list.size(); i++) {
			while(equals(list.get(i).getPawn(),p.getPawn())) {
				i = 0;
				System.out.println("This Pawn is already token by a Custom Player, please choose another one. ");
				System.out.println("Custom Player List:");
				printPlayers(list);
				p.setPawn();
			}
		}

		for(int i = 0; i < list2.size(); i++) {
			while(equals(list2.get(i).getPawn(),p.getPawn())) {
				i = 0;
				System.out.println("This Pawn is already token by a Random Player, please choose another one. ");
				System.out.println("Random Player List:");
				printPlayers(list2);
				p.setPawn();
			}
		}

		list.add(p);
		System.out.println("Player " + p.getName() + " created.");
	}
}
