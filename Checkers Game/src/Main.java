import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static Board board = new Board(); // new instance of Board
	static Piece piece = new Piece(); // new instance of Piece
	static Rules rules = new Rules(); // new instance of Rules
	private static class Point { // making a Point, an (x, y) coordinate pair
	    final int x;
	    final int y;
	    Point(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}
	static Point from; // new Point for piece's initial coordinate
	static Point to; // new Point for piece's destination coordinate
	static HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	
	
	@SuppressWarnings("resource") // Could not close the reader without errors
	static void moveReader(int turn) { // read the move from user input and save to Points "from" and "to"
		
		String input; // string for adding user's input
		
		if(turn == 1) { // if Player 1's turn
			System.out.println("\nPlayer 1 turn: ");
		} else { // if Player 2's turn
			System.out.println("\nPlayer 2 turn: ");
		}
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		input = reader.nextLine(); // Read the whole input
		Scanner reader2 = new Scanner(input); // Reading from input
		String fromMove = reader2.next().toLowerCase(); // Get "from" move
		@SuppressWarnings("unused")
		String midword = reader2.next(); // middle word, can be anything  !!UNUSED!!
		String toMove = reader2.next().toLowerCase(); // get "to" move
		reader2.close();
		
		char a = fromMove.charAt(0); // get the letter from "to" move
		char b = toMove.charAt(0); // get the letter from "from" move
		int fromy = a - 'a'; // turn letter into y coordinate
		int fromx = Character.getNumericValue(fromMove.charAt(1)) -1; // turn number into x coordinate
		int toy = b - 'a'; // turn letter into y coordinate
		int tox = Character.getNumericValue(toMove.charAt(1)) -1; // turn number into x coordinate
		from = new Point(fromx, fromy);
		to = new Point(tox, toy);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Checkers! Choose the game mode: ");
		System.out.println("1 - Human vs. Human");
		System.out.println("2 - Human vs. AI");
		@SuppressWarnings("resource")
		Scanner menu = new Scanner(System.in); // read the choice from the input
		int choice = menu.nextInt(); // read the choice as int
		int turn = 1; // set the turn to Player 1
		
		if(choice == 1) { // if Human vs. Human chosen
			String error;
			System.out.println("\nWelcome to your game of checkers.\nThe syntax for this game is simple.\n"
					+ "Piece you want to move + to + spot you want your piece to go to.\n"
					+ "For example:\n\nb6 to c5\n\nHave fun and good luck!\n\n");
			board.initBoard(); // initiate the board
			while(rules.gameOver() == false) { // while the game is not over
				if(turn == 1) { // if Player 1's turn
					moveReader(turn); // read the move from user input
					if(rules.rulesMet(from.x, from.y, to.x, to.y, turn) == true) { // if all rules are met
						piece.movePiece(from.x, from.y, to.x, to.y, turn); // move the piece
						if(rules.multiCaptMessage == true) { // if capturing multiple pieces is possible
							System.out.println("\nYou can capture another piece!\n");
							board.showBoard();
						} else {
							turn = 2; // change the turn flag to Player 2's turn
						}
					} else {
						error = rules.errorMessages(from.x, from.y, to.x, to.y, turn); // display error message
						System.out.println(error);
						board.showBoard(); // show the board
					}
				} else { // if Player 2's turn
					moveReader(turn); // read the move from user input
					if(rules.rulesMet(from.x, from.y, to.x, to.y, turn) == true) { // if all rules are met
						piece.movePiece(from.x, from.y, to.x, to.y, turn); // move the piece
						if(rules.multiCaptMessage == true) { // if capturing multiple pieces is possible
							System.out.println("\nYou can capture another piece!\n");
							board.showBoard();
						} else {
							turn = 1; // change the turn flag to Player 1's turn
						}
					} else {
						error = rules.errorMessages(from.x, from.y, to.x, to.y, turn); // display error message
						System.out.println(error);
						board.showBoard(); // show the board
					}
				}
			}
			if(rules.gameOver() == true) {
				if(rules.p1count > rules.p2count) {
					System.out.println("Game Over. Player 1 Wins.");
				} else {
					System.out.println("Game Over. Player 2 Wins.");
				}
			}
		}
	}
}
