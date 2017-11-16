// CLASS FOR CREATING THE BOARD

public class Board {

	static Piece piece = new Piece(); // new instance of Piece
	static String[][] board = new String[8][8]; // create an array of arrays containing strings
	static int row = 0;
	static int col = 0;
	
	public void showBoard() { // display the board
	    for (row = 0; row < board.length; row++) { // limit number of rows to the size of board array
	    	if(row > 0) { // after the first row
	    	System.out.println(""); // make a new line
	    	} else { //before the first row
	    		System.out.println("   A  B  C  D  E  F  G  H"); // add letters
	    	}
	    	
	        for(col = 0; col < board[row].length; col++) { // limit number to columns to the number of rows
	        	if(col == 0) { // before the first column
	        		System.out.print((row + 1) + " "); // add numbers
	        	}
	        	
	        	if(board[row][col] != null) { // if the spot is not empty
	        		System.out.print("[" + board[row][col] + "]"); // add the string between two brackets
	        	}
	        	else { // if spot is empty
		            System.out.print("[ ]"); // add empty brackets
	        	}
	        	if(col == 7) { // after the last column
	        		System.out.print(" " + (row + 1)); // add numbers
	        	}
	        }
	    }
	    System.out.println("\n   A  B  C  D  E  F  G  H"); // add letters after the last row
	}
	
	void initBoard() { // initiate board and add pieces in their starting spots
		
		for(int i = 0; i < 8; i++) {
			for(int a = 0; a < 8; a++) {
				if(i % 2 == 0) {
					if(i < 4 && a % 2 == 0) {
						piece.addPiece(i, a, "b"); // add Player 2 piece
					}
					if(i > 4 && a % 2 == 0) {
						piece.addPiece(i, a, "w"); // add Player 1 piece
					}
				} else {
					if(i < 3 && a % 2 != 0) {
						piece.addPiece(i, a, "b"); // add Player 2 piece
					}
					if(i > 4 && a % 2 != 0) {
						piece.addPiece(i, a, "w"); // add Player 1 piece
					}
				}
			}
		}
		showBoard(); // display the board
	}
}
