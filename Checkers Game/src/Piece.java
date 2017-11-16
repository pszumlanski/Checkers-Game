// SINGLE PIECE CLASS

public class Piece {

	
	Board board = new Board(); // new instance of Board
	
	public void movePiece(int x, int y, int xn, int yn, int turn) { // method for moving pieces
		Board.board[xn][yn] = Board.board[x][y]; // move the piece from the initial spot to the given destination
		Board.board[x][y] = null; // reset the initial spot
		addKing(xn, yn, turn);
		board.showBoard(); // display the board with changes
	}
	
	public void addPiece(int x, int y, String r) { // adding piece to the board

	    Board.board[x][y] = r; // set the spot to the given string
	}
	
	
	
	public void addKing(int xn, int yn, int turn) { // 
		if(turn == 1) {
			if(xn == 0) {
				Board.board[xn][yn] = "W";
			}
		} else {
			if(xn == 7) {
				Board.board[xn][yn] = "B";
			}
		}
	}
	
}
