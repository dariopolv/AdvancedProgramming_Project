import java.util.ArrayList;

public class PlayerSetting {

	private Input in;

	public PlayerSetting() {
		this.in = new Input();
	}

	//Override of equals method to check if a player name from a list is equal to a name of a player that we are creating	
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

	//Ask input to select a player from a list and get the player from that list	
	public Player selectPlayer(ArrayList<Player> list) {

		try {
			in.insertInt();
		}
		catch(NumberFormatException ne) {
			System.out.println("The given value is not correct, please insert a numeric value");
			printPlayers(list);
			return null;
		}

		if(in.getInputInt() > list.size()-1 || in.getInputInt() < 0) {
			System.out.println("The given input is out of range");
			printPlayers(list);
			return null;
		}

		return list.get(in.getInputInt());

	}

	//Remove a selected player from the list	
	public void removeSelectedPlayer(ArrayList<Player> list) {
		list.remove(in.getInputInt());
	}

	//Check the selection input, if it's not correct it restarts the selection	
	public Player inputChecker(Player player, ArrayList<Player> list) {
		while(player == null) {
			player = selectPlayer(list);
		}
		return player;
	}

	//Check the list size. Each mod of games requires players in the list:
	//Mod Custom vs Custom: requires to create and add at least 2 Custom players in the list
	//Mod Custom vs Random: requires to create and add at least 1 Custom player and 1 Random Player in the lists
	//Mod Random vs Random: requires to create and add at least 2 Random players in the list
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

	//Print the list of players	
	public void printPlayers(ArrayList<Player> list) {
		if(list.size() == 0) {
			System.out.println("No Players found");
		}
		for(int i = 0; i < list.size(); i++) {
			System.out.print("["+i+"] - "+"Player: "+list.get(i).getName()+"      Pawn: ");
			System.out.println(list.get(i).getPawn());
		}
	}

	//Set player name and pawn and check if match with other names or pawns in the lists. If there isn't a match it adds the player in the right list	
	public void addPlayer(Player p, ArrayList<Player> list, ArrayList<Player> list2) {
		p.setName();
		for(int i = 0; i < list.size(); i++ ) {
			while(equals(list.get(i).getName(), p.getName())) {
				System.out.println("This name is already token by a Custom Player, please choose another one. ");
				System.out.println("Custom Player List:");
				printPlayers(list);
				i = 0;
				p.setName();
			}

		}
		for(int i = 0; i < list2.size(); i++) {  		
			while(equals(list2.get(i).getName(), p.getName())) {
				i = 0;
				System.out.println("This name is already token by a Random Player, please choose another one. ");
				System.out.println("Random Player List:");
				printPlayers(list2);
				p.setName();
			}
		}

		p.setPawn();
		for(int i = 0; i < list.size(); i++ ) {
			while(equals(list.get(i).getPawn(), p.getPawn())) {
				System.out.println("This Pawn is already token by a Custom Player, please choose another one. ");
				System.out.println("Custom Player List:");
				printPlayers(list);
				i = 0;
				p.setPawn();
			} 		
		}

		for(int i = 0; i < list2.size(); i++) {  		
			while(equals(list2.get(i).getPawn(), p.getPawn())) {
				i = 0;
				System.out.println("This name is already token by a Random Player, please choose another one. ");
				System.out.println("Random Player List:");
				printPlayers(list2);
				p.setPawn();
			}
		}
		if(p.isCustomPlayer()) {
			list.add(p);
		}
		else {
			list2.add(p);
		}
		System.out.println("Player " + p.getName() + " created.");
	}	
}
