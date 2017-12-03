import java.util.*;

public class Main {
    private static int currentUserId;
    private static int currentMemberId;
    private static boolean isAdmin;
    private static SqlProc sqlProc;
    private static DisplaySqlProc displaySqlProc;
    private static Scanner scanner;
    private static final String wipe = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n";

    public static void main(String[] argv) {
	sqlProc = new SqlProc();
	displaySqlProc = new DisplaySqlProc();
	scanner = new Scanner(System.in);
	while (true) {
	    System.out.println("Welcome to GameGo!");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("[1] Create new user account");
	    System.out.println("[2] Login existing user");
	    System.out.println("[3] Login existing admin");
	    System.out.println("[Q] Quit");

	    String choice = scanner.nextLine();
	    System.out.println(wipe);

	    switch (choice.toLowerCase()) {
	    case "1":
		createUserMenu();
		break;
	    case "2":
		loginUserMenu();
		break;
	    case "3":
		loginAdminMenu();
		break;
	    case "q":
		System.out.println("Goodbyes from GameGo!");
		scanner.close();
		System.exit(0);
		break;
	    default:
		System.out.println("Invalid input, try again.");
	    }
	}
    }

    public static void createUserMenu() {
	while (true) {
	    System.out.print("Creating basic GameGo account.");
	    System.out.print("\r\nName: ");
	    String name = scanner.nextLine();
	    System.out.print("\r\nAge: ");
	    String ageString = scanner.nextLine();
	    System.out.print("\r\nEmail: ");
	    String email = scanner.nextLine();
	    System.out.print("\r\nPassword: ");
	    String password = scanner.nextLine();
	    System.out.println(wipe);

	    int ageInt = Integer.parseInt(ageString);
	    int response = sqlProc.createUser(name, ageInt, email, password);

	    if (response > -1) {
		currentUserId = response;
		userMenu();
		return;
	    } else {
		System.out.println("Oops, user exists already.");
	    }

	}
    }

    public static void loginUserMenu() {
	while (true) {
	    System.out.print("Logging into GameGo account.");
	    System.out.print("\r\nEmail: ");
	    String email = scanner.nextLine();
	    System.out.print("\r\nPassword: ");
	    String password = scanner.nextLine();
	    System.out.println(wipe);

	    int response = sqlProc.loginUser(email, password);

	    if (response > -1) {
		currentUserId = response;
		isAdmin = false;
		userMenu();
		return;
	    } else {
		System.out.println("Oops, user exists already.");
	    }
	}
    }
    
    public static void loginAdminMenu() {
	while (true) {
	    System.out.print("Logging into Admin account.");
	    System.out.print("\r\nEmail: ");
	    String email = scanner.nextLine();
	    System.out.print("\r\nPassword: ");
	    String password = scanner.nextLine();
	    System.out.println(wipe);

	    int response = sqlProc.loginAdmin(email, password);

	    if (response > -1) {
		currentUserId = response;
		isAdmin = true;
		admin_memberMenu();
		return;
	    } else {
		System.out.println("Oops, user exists already.");
	    }
	}
    }

    public static void userMenu() {
	currentMemberId = sqlProc.getMemberId(currentUserId);
	while (true) {
	    System.out.println("Welcome user!");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("[1] Browse games");
	    System.out.println("[2] Browse consoles");
	    if (currentMemberId == -1) {
		 System.out.println("[3] Sign up for GameGo membership");
	    } else {
		 System.out.println("[3] Membership Exclusives");
	    }
	    System.out.println("[4] View purchase history");
	    System.out.println("[Q] Log out");

	    String choice = scanner.nextLine();
	    System.out.println(wipe);

	    switch (choice.toLowerCase()) {
	    case "1":
		browseGamesMenu();
		break;
	    case "2":
		browseConsolesMenu();
		break;
	    case "3":
		if (currentMemberId == -1) {
		    createMemberMenu();
		} else {
		    memberMenu();
		}
		break;
	    case "4":
		viewMyTransactions();
		break;
	    case "q":
		System.out.println("You've recently logged out.");
		currentUserId = 0;
		isAdmin = false;
		return;
	    default:
		System.out.println("Invalid input, try again.");
	    }
	}
    }

    public static void createMemberMenu() {
	while (true) {
	    System.out.println("Joining GameGo Membership, please confirm your credentials.");
	    System.out.print("\r\nEmail: ");
	    String email = scanner.nextLine();
	    System.out.print("\r\nPassword: ");
	    String password = scanner.nextLine();
	    System.out.println(wipe);

	    int response = sqlProc.createMember(email, password);

	    if (response > -1) {
		System.out.println("You've successfully joined GameGo membership.");
		return;
	    } else {
		System.out.println("Oops, incorrect credentials when trying to become member.");
		return;
	    }

	}
    }
    
    public static void memberMenu() {
	while (true) {
	    System.out.println("Membership Menu");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("[1] Check Points");
	    System.out.println("[2] Buy Prize");
	    System.out.println("[3] Check Rentals");
	    System.out.println("[4] Return Rentals");
	    System.out.println("[5] End Membership");
	    System.out.println("[Q] Go back");
	    
	    String choice = scanner.nextLine();
	    System.out.println(wipe);

	    switch (choice.toLowerCase()) {
	    case "1":
		System.out.println(displaySqlProc.viewMemberPoints(currentMemberId));
		break;
	    case "2":
		System.out.println("Awaiting Implementation");
		break;
	    case "3":
		System.out.println("Awaiting Implementation");
		break;
	    case "4":
		System.out.println("Awaiting Implementation");
		break;
	    case "5":
		System.out.println("Awaiting Implementation");
		break;
	    case "q":
		return;
	    default:
		System.out.println("Invalid input, try again.");
	    }

	}
    }
    
    public static void browseGamesMenu() {
	while (true) {
	    System.out.println("Browse Games Menu");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("[1] View game listing");
	    System.out.println("[2] Search for game");
	    System.out.println("[3] View games on sale");
	    System.out.println("[Q] Go back");

	    String choice = scanner.nextLine();
	    System.out.println(wipe);

	    switch (choice.toLowerCase()) {
	    case "1":
		viewGamesMenu();
		break;
	    case "2":
		searchGamesMenu();
		break;
	    case "3":
		ArrayList<String> gameList = displaySqlProc.viewGamesOnSale();
		printItemList(gameList);
		break;
	    case "q":
		return;
	    default:
		System.out.println("Invalid input, try again.");
	    }
	}
    }

    public static void viewGamesMenu() {
	while (true) {
	    System.out.println("View Games Menu");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("View games sorted by..");
	    System.out.println("[1]title, [2]author, [3]genre, [4]console, [5]rating, [6]price");
	    if (isAdmin == false) {
		System.out.println("[B] Buy game");
		System.out.println("[R] Rent game (GameGo Members Only)");
	    }
	    System.out.println("[Q] Go back");

	    String choice = scanner.nextLine();
	    System.out.println(wipe);
	    ArrayList<String> gameList = new ArrayList<String>();

	    switch (choice.toLowerCase()) {
	    case "1":
		gameList = displaySqlProc.viewGames("title");
		printItemList(gameList);
		break;
	    case "2":
		gameList = displaySqlProc.viewGames("author");
		printItemList(gameList);
		break;
	    case "3":
		gameList = displaySqlProc.viewGames("genre");
		printItemList(gameList);
		break;
	    case "4":
		gameList = displaySqlProc.viewGames("console");
		printItemList(gameList);
		break;
	    case "5":
		gameList = displaySqlProc.viewGames("rating");
		printItemList(gameList);
		break;
	    case "6":
		gameList = displaySqlProc.viewGames("price");
		printItemList(gameList);
		break;
	    case "b":
		if (isAdmin == true) {
		    System.out.println("Invalid input, try again.");
		} else {
		    gameList = displaySqlProc.viewGames("title");
		    printItemList(gameList);
		    System.out.println("Enter the game's ID to purchase the game.");
		    System.out.println("Or, press [Q] to go back.");
		    String gidString = scanner.nextLine();
		    System.out.println(wipe);
		    if (gidString.toLowerCase().equals("q")) {
			break;
		    } else {
			int gidInt = Integer.parseInt(gidString);
			String result = sqlProc.buyGame(currentUserId, gidInt);
			System.out.println(result);
		    }
		}
		break;
	    case "r":
		if (isAdmin == true) {
		    System.out.println("Invalid input, try again.");
		} else {
		    if (currentMemberId == -1) {
			System.out.println("Error: renting is excluse to GameGo members");
		    } else {
			gameList = displaySqlProc.viewGames("title");
			printItemList(gameList);
			System.out.println("Enter the game's ID to rent the game.");
			System.out.println("Or, press [Q] to go back.");
			String gidRentString = scanner.nextLine();
			System.out.println(wipe);
			if (gidRentString.toLowerCase().equals("q")) {
			    break;
			} else {
		   	    int gidInt = Integer.parseInt(gidRentString);
		   	    String result = sqlProc.rentGame(currentUserId, gidInt);
		   	    System.out.println(result);
			}
		    }
		}
		
		break;
	    case "q":
		return;
	    default:
		System.out.println("Invalid input, try again.");
	    }
	}
    }
    
    public static void browseConsolesMenu() {
	while (true) {
	    System.out.println("Browse Consoles Menu");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("[1] View all consoles by title");
	    System.out.println("[2] View all consoles by price");
	    if (isAdmin == false) {
		System.out.println("[B] Buy");
	    }
	    System.out.println("[Q] Go back");

	    String choice = scanner.nextLine();
	    System.out.println(wipe);
	    
	    ArrayList<String> consoleList = new ArrayList<String>();
	    
	    switch (choice.toLowerCase()) {
	    case "1":
		consoleList = displaySqlProc.viewConsoles("name");
		printItemList(consoleList);
		break;
	    case "2":
		consoleList = displaySqlProc.viewConsoles("price");
		printItemList(consoleList);
		break;
	    case "b":
		if (isAdmin == true) {
		    System.out.println("Invalid input, try again.");
		} else {
		    consoleList = displaySqlProc.viewConsoles("name");
		    printItemList(consoleList);
		    System.out.println("Enter the console's ID to purchase the game.");
		    System.out.println("Or, press [Q] to go back.");
		    String cidString = scanner.nextLine();
		    System.out.println(wipe);
		    if (cidString.toLowerCase().equals("q")) {
			break;
		    } else {
			int cidInt = Integer.parseInt(cidString);
			String result = sqlProc.buyConsole(currentUserId, cidInt);
			System.out.println(result);
		    }
		}
		break;
	    case "q":
		return;
	    default:
		System.out.println("Invalid input, try again.");
	    }
	}
    }

    public static void searchGamesMenu() {
	while (true) {
	    System.out.println("View Games Menu");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("Search by..");
	    System.out.println("[1]title, [2]author, [3]genre, [4]console");
	    System.out.println("[5]rating (Less Than), [6]rating (Greater Than)");
	    System.out.println("[7]price (Less Than), [8]price (Greater Than)");
	    if (isAdmin == false) {
		System.out.println("[B] Buy game");
		System.out.println("[R] Rent game (GameGo Members Only)");
	    }
	    System.out.println("[Q] Go back");

	    System.out.println("Categorical search:");
	    String choice = scanner.nextLine();
	    System.out.println(wipe);
	    ArrayList<String> gameList = new ArrayList<String>();
	    
	    switch (choice.toLowerCase()) {
	    case "1":
		searchGamesByTitleMenu();
		break;
	    case "2":
		searchGamesByAuthorMenu();
		break;
	    case "3":
		searchGamesByGenreMenu();
		break;
	    case "4":
		searchGamesByConsoleMenu();
		break;
	    case "5":
		searchGamesByRatingMenu("LessThan");
		break;
	    case "6":
		searchGamesByRatingMenu("GreaterThan");
		break;
	    case "7":
		searchGamesByPriceMenu("LessThan");
		break;
	    case "8":
		searchGamesByPriceMenu("GreaterThan");
		break;
	    case "b":
		if (isAdmin == true) {
		    System.out.println("Invalid input, try again.");
		} else {
		    gameList = displaySqlProc.viewGames("title");
		    printItemList(gameList);
		    System.out.println("Enter the game's ID to purchase the game.");
		    System.out.println("Or, press [Q] to go back.");
		    String gidString = scanner.nextLine();
		    System.out.println(wipe);
		    if (gidString.toLowerCase().equals("q")) {
			break;
		    } else {
			int gidInt = Integer.parseInt(gidString);
			String result = sqlProc.buyGame(currentUserId, gidInt);
			System.out.println(result);
		    }
		}
		break;
	    case "r":
		if (isAdmin == true) {
		    System.out.println("Invalid input, try again.");
		} else {
		    if (currentMemberId == -1) {
			System.out.println("Error: renting is excluse to GameGo members");
		    } else {
			gameList = displaySqlProc.viewGames("title");
			printItemList(gameList);
			System.out.println("Enter the game's ID to rent the game.");
			System.out.println("Or, press [Q] to go back.");
			String gidRentString = scanner.nextLine();
			System.out.println(wipe);
			if (gidRentString.toLowerCase().equals("q")) {
			    break;
			} else {
		   	    int gidInt = Integer.parseInt(gidRentString);
		   	    String result = sqlProc.rentGame(currentUserId, gidInt);
		   	    System.out.println(result);
			}
		    }
		}
		
		break;
	    case "q":
		return;
	    default:
		System.out.println("Invalid input, try again.");
	    }
	}
    }
    
    public static void searchGamesByTitleMenu() {
	while (true) {
	    System.out.println("Enter the game's title or type !q to go back:");
	    String input = scanner.nextLine();
	    System.out.println(wipe);
	    
	    switch (input.toLowerCase()) {
	    case "!q":
		return;
	    default: 
		ArrayList<String> gameList = displaySqlProc.searchGamesString("title", input);
		printItemList(gameList);
	    }
	}
    }
    
    public static void searchGamesByAuthorMenu() {
	while (true) {
	    System.out.println("Enter the game's author or type !q to go back:");
	    String input = scanner.nextLine();
	    System.out.println(wipe);
	    
	    switch (input.toLowerCase()) {
	    case "!q":
		return;
	    default: 
		ArrayList<String> gameList = displaySqlProc.searchGamesString("author", input);
		printItemList(gameList);
	    }
	}
    }

    public static void searchGamesByGenreMenu() {
	while (true) {
	    System.out.println("Enter the game's genre or type !q to go back:");
	    String input = scanner.nextLine();
	    System.out.println(wipe);
	    
	    switch (input.toLowerCase()) {
	    case "!q":
		return;
	    default: 
		ArrayList<String> gameList = displaySqlProc.searchGamesString("genre", input);
		printItemList(gameList);
	    }
	}
    }
    public static void searchGamesByConsoleMenu() {
	while (true) {
	    System.out.println("Enter the game's console or type !q to go back:");
	    String input = scanner.nextLine();
	    System.out.println(wipe);
	    
	    switch (input.toLowerCase()) {
	    case "!q":
		return;
	    default: 
		ArrayList<String> gameList = displaySqlProc.searchGamesString("console", input);
		printItemList(gameList);
	    }
	}
    }
    public static void searchGamesByRatingMenu(String lessOrGreat) {
	while (true) {
	    System.out.println("Enter a rating of 1-5 or type !q to go back:");
	    String input = scanner.nextLine();
	    System.out.println(wipe);
	    
	    switch (input.toLowerCase()) {
	    case "!q":
		return;
	    default: 
		int inputInt = Integer.parseInt(input);
		ArrayList<String> gameList = displaySqlProc.searchGamesByRating(lessOrGreat, inputInt);
		printItemList(gameList);
	    }
	}
    }
    public static void searchGamesByPriceMenu(String lessOrGreat) {
	while (true) {
	    System.out.println("Enter a price or type !q to go back:");
	    String input = scanner.nextLine();
	    System.out.println(wipe);
	    
	    switch (input.toLowerCase()) {
	    case "!q":
		return;
	    default: 
		double inputDouble = Double.parseDouble(input);
		ArrayList<String> gameList = displaySqlProc.searchGamesByPrice(lessOrGreat, inputDouble);
		printItemList(gameList);
	    }
	}
    }
    
    public static void printItemList(ArrayList<String> itemList) {
	for (String listing : itemList) {
	    System.out.println(listing);
	}
    }
    
    public static void viewMyTransactions() {
	displaySqlProc.viewTransactionsById(currentUserId);
    }
    
    /****** ADMIN SPECIFIC MENUES **********************************************************************************/
    
    public static void admin_memberMenu() {
	while (true) {
	    System.out.println("ADMIN Membership Menu");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("[1] Browse games, [2] Browse consoles");
	    System.out.println("View [3]Memberships, [4]Rentals, [5]Sales, [6]Transactions, [7]Statistics");
	    System.out.println("[8] Promote user to admin");
	    System.out.println("[Q] Log out");
	    
	    String choice = scanner.nextLine();
	    System.out.println(wipe);

	    switch (choice.toLowerCase()) {
	    case "1":
		browseGamesMenu();
		break;
	    case "2":
		browseConsolesMenu();
		break;
	    case "3":
		System.out.println("Awaiting Implementation");
		break;
	    case "4":
		System.out.println("Awaiting Implementation");
		break;
	    case "5":
		System.out.println("Awaiting Implementation");
		break;
	    case "6":
		System.out.println("Awaiting Implementation");
		break;
	    case "7":
		System.out.println("Awaiting Implementation");
		break;
	    case "8":
		System.out.println("Awaiting Implementation");
		break;
	    case "q":
		return;
	    default:
		System.out.println("Invalid input, try again.");
	    }

	}
    }
    
    public static void admin_transactionsMenu() {
	while (true) {
	    System.out.println("ADMIN Transactions Menu");
	    System.out.println("Enter a key value to proceed:");
	    System.out.println("[1] List all Transactions");
	    System.out.println("[2] Search transactions by user");
	    System.out.println("[3] Search transactions by game");
	    System.out.println("[4] Search transactions by console");
	    System.out.println("[5] Net Gross of Transactions");
	    System.out.println("[Q] Go back");
	    
	    String choice = scanner.nextLine();
	    System.out.println(wipe);

	    switch (choice.toLowerCase()) {
	    case "1":
		System.out.println("Awaiting Implementation");
		break;
	    case "2":
		System.out.println("Awaiting Implementation");
		break;
	    case "3":
		System.out.println("Awaiting Implementation");
		break;
	    case "4":
		System.out.println("Awaiting Implementation");
		break;
	    case "5":
		System.out.println("Awaiting Implementation");
		break;
	    case "q":
		return;
	    default:
		System.out.println("Invalid input, try again.");
	    }

	}
    }
}