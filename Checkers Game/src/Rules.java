// RULES FOR THE GAME

public class Rules {

	Board board = new Board(); // new instance of Board
	boolean isCapture = false; // flag to check if Capture move is available
	private boolean multiCapt = false; // flag to check if multiple pieces can be captured in one move
	public boolean multiCaptMessage = false; // public flag that lets the main class know if multiple captures are available
	private boolean isKing = false;
	public int p1count = 0; // counter for Player 1 pieces
	public int p2count = 0; // counter for Player 2 pieces
	
	private boolean emptyField(int x, int y) { // for checking if chosen spot has a piece in it
		if(Board.board[x][y] == null) { // if empty field chosen
			return false;
		} else {
			return true;
		}
	}
	private boolean occupiedField(int xn, int yn) { // for checking if move's destination is an empty spot
		if(Board.board[xn][yn] != null) { // if destination spot is not empty
			return false;
		} else {
			return true;
		}
	}
	public boolean gameOver() { // to check if any pieces from either side are present
		p1count = 0;
		p2count = 0;
		for(int row = 0; row < Board.board.length; row++) { // Iterate through all rows
			for(int col = 0; col < Board.board[row].length; col++) { // Iterate through all columns
				if(Board.board[row][col] == "w" || Board.board[row][col] == "W") { // if spot on the board has Player 1 piece
					p1count++; // Increment the counter for Player 1 pieces by 1
				} else if(Board.board[row][col] == "b" || Board.board[row][col] == "B") { // if spot on the board has Player 2 piece
					p2count++; // Increment the counter for Player 2 pieces by 1
				}
			}
		}
		if(p1count > 0 && p2count > 0) { // if both players have more than 0 pieces
			return false;
		} else {
			return true;
		}
	}
	private boolean rightPiece(int x, int y, int turn) { // checks if the right player chose the right piece
		if(turn == 1) { // if it's player 1's turn
			if(Board.board[x][y] == "w" || Board.board[x][y] == "W") { // if the chosen spot is Player 1 piece
				return true;
			} else {
				return false;
			}
		} else { // if it's player 2's turn
			if(Board.board[x][y] == "b" || Board.board[x][y] == "B") { // if the chosen spot is Player 2 piece
				return true;
			} else {
				return false;
			}
		}
	}
	private boolean makeMove(int x, int y, int xn, int yn, int turn) { // makes a move
		if(turn == 1) { // if it's player 1's turn
			if((x - xn) == 1 && Math.abs(y - yn) == 1) { // checks if the move is forward and diagonal
				captureAvailable(turn); // runs the method to check if any captures are available
				if(isCapture == true) { // if there are capture moves available
					return false;
				} else {
					return true;
				}
			} else { // if the move is not forward and diagonal
				if(Math.abs(x - xn) == 2 && Math.abs(y - yn) == 2) { // checks if the move is a capture move
					if(xn == x - 2) { // if the move is a forward capturing move
						if((yn == y + 2 && Board.board[x-1][y+1] == "b") || (yn == y + 2 && Board.board[x-1][y+1] == "B")) { // if there is an opponent's piece in between
							Board.board[x-1][y+1] = null; // remove the opponent's piece
							multiCapture(xn, yn, turn); // check if multiple pieces can be captured in one move
							if(multiCapt == true) { // if so
								multiCaptMessage = true; // set the flag for multi capture
							}
							return true; // 
						}else if((yn == y - 2 && Board.board[x-1][y-1] == "b") || (yn == y - 2 && Board.board[x-1][y-1] == "B")) { // if there is an opponent's piece in between
							Board.board[x-1][y-1] = null; // remove the opponent's piece
							multiCapture(xn, yn, turn); // check if multiple pieces can be captured in one move
							if(multiCapt == true) { // if so
								multiCaptMessage = true; // set the flag for multi capture
							}
							return true;
						} else {
							return false;
						}
					} else if(xn == x + 2) {
						if(Board.board[x][y] == "W") {
							isKing = true;
							if((yn == y + 2 && Board.board[x+1][y+1] == "b") || (yn == y + 2 && Board.board[x+1][y+1] == "B")) { // check if there is an opponent's piece in between
								Board.board[x+1][y+1] = null; // remove the opponent's piece
								multiCapture(xn, yn, turn); // check if multiple pieces can be captured in one move
								if(multiCapt == true) { // if so
									multiCaptMessage = true; // set the flag for multi capture
								}
								return true;
							}else if((yn == y - 2 && Board.board[x+1][y-1] == "b") || (yn == y - 2 && Board.board[x+1][y-1] == "B")) { // check if there is an opponent's piece in between
								Board.board[x+1][y-1] = null; // remove the opponent's piece
								multiCapture(xn, yn, turn); // check if multiple pieces can be captured in one move
								if(multiCapt == true) { // if so
									multiCaptMessage = true; // set the flag for multi capture
								}
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if(Math.abs(x - xn) == 1 && Math.abs(y - yn) == 1) {
					if(Board.board[x][y] == "W") {
						isKing = true;
						captureAvailable(turn); // runs the method to check if any captures are available
						if(isCapture == true) { // if there are capture moves available
							return false;
						} else {
							return true;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		} else { // if it's player 2's turn
			if((xn - x) == 1 && Math.abs(y - yn) == 1) { // checks if the move is forward and diagonal
				captureAvailable(turn); // runs the method to check if any captures are available
				if(isCapture == true) { // if there are capture moves available
					return false;
				} else {
					return true;
				}
			} else { // if the move is not forward and diagonal
				if(Math.abs(x - xn) == 2 && Math.abs(y - yn) == 2) { // checks if the move is a capture move
					if(xn == x + 2) { // if the move is a forward capturing move
						if((yn == y + 2 && Board.board[x+1][y+1] == "w") || (yn == y + 2 && Board.board[x+1][y+1] == "W")) { // check if there is an opponent's piece in between
							Board.board[x+1][y+1] = null; // remove the opponent's piece
							multiCapture(xn, yn, turn); // check if multiple pieces can be captured in one move
							if(multiCapt == true) { // if so
								multiCaptMessage = true; // set the flag for multi capture
							}
							return true;
						}else if((yn == y - 2 && Board.board[x+1][y-1] == "w") || (yn == y - 2 && Board.board[x+1][y-1] == "W")) { // check if there is an opponent's piece in between
							Board.board[x+1][y-1] = null; // remove the opponent's piece
							multiCapture(xn, yn, turn); // check if multiple pieces can be captured in one move
							if(multiCapt == true) { // if so
								multiCaptMessage = true; // set the flag for multi capture
							}
							return true;
						} else {
							return false;
						}
					} else if(xn == x - 2){
						if(Board.board[x][y] == "B") {
							isKing = true;
							if((yn == y + 2 && Board.board[x-1][y+1] == "w") || (yn == y + 2 && Board.board[x-1][y+1] == "W")) { // if there is an opponent's piece in between
								Board.board[x-1][y+1] = null; // remove the opponent's piece
								multiCapture(xn, yn, turn); // check if multiple pieces can be captured in one move
								if(multiCapt == true) { // if so
									multiCaptMessage = true; // set the flag for multi capture
								}
								return true;
							}else if((yn == y - 2 && Board.board[x-1][y-1] == "w") || (yn == y - 2 && Board.board[x-1][y-1] == "W")) { // if there is an opponent's piece in between
								Board.board[x-1][y-1] = null; // remove the opponent's piece
								multiCapture(xn, yn, turn); // check if multiple pieces can be captured in one move
								if(multiCapt == true) { // if so
									multiCaptMessage = true; // set the flag for multi capture
								}
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if(Math.abs(x - xn) == 1 && Math.abs(y - yn) == 1) {
					if(Board.board[x][y] == "B") {
						isKing = true;
						captureAvailable(turn); // runs the method to check if any captures are available
						if(isCapture == true) { // if there are capture moves available
							return false;
						} else {
							return true;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
	}
	private void captureAvailable(int turn) { // checks if any capturing moves are available
		isCapture = false; // resets the Capture move availability flag
		if(turn == 1) { // if it's player 1's turn
			for(int row = 0; row < Board.board.length; row++) { // Iterate through all rows
				for(int col = 0; col < Board.board[row].length; col++) { // Iterate through all columns
					if(Board.board[row][col] == "w") { // if the spot is Player 1 piece
						try { // check for out of bounds error
							if((Board.board[row - 1][col - 1] == "b"|| Board.board[row - 1][col - 1] == "B") && Board.board[row-2][col-2] == null) { // checks if there's an opponent's piece in capturing spot
								isCapture = true; // set the flag off
							}
						} catch(ArrayIndexOutOfBoundsException exception) {}
						try { // check for out of bounds error
							 if((Board.board[row - 1][col + 1] == "b" || Board.board[row - 1][col + 1] == "B") && Board.board[row-2][col+2] == null) { // checks if there's an opponent's piece in capturing spot
								 isCapture = true; // set the flag off
							 }
						} catch(ArrayIndexOutOfBoundsException exception) {}	
					} 
					if(Board.board[row][col] == "W") {
						try { // check for out of bounds error
							if((Board.board[row + 1][col - 1] == "b"|| Board.board[row + 1][col - 1] == "B") && Board.board[row-2][col-2] == null) { // checks if there's an opponent's piece in capturing spot
								isCapture = true; // set the flag off
							}
						} catch(ArrayIndexOutOfBoundsException exception) {}
						try { // check for out of bounds error
							 if((Board.board[row + 1][col + 1] == "b" || Board.board[row + 1][col + 1] == "B") && Board.board[row-2][col+2] == null) { // checks if there's an opponent's piece in capturing spot
								 isCapture = true; // set the flag off
							 }
						} catch(ArrayIndexOutOfBoundsException exception) {}
					}
				}
			}
		} else { // if it's player 2's turn
			for(int row = 0; row < Board.board.length; row++) { // Iterate through all rows
				for(int col = 0; col < Board.board[row].length; col++) { // Iterate through all columns
					if(Board.board[row][col] == "b") { // if the spot is Player 2 piece
						try { // check for out of bounds error
							if((Board.board[row + 1][col - 1] == "w" || Board.board[row + 1][col - 1] == "W") && Board.board[row+2][col-2] == null) { // checks if there's an opponent's piece in capturing spot
								isCapture = true; // set the flag off
							}
						} catch(ArrayIndexOutOfBoundsException exception) {}
						try { // check for out of bounds error
							 if((Board.board[row + 1][col + 1] == "w" || Board.board[row + 1][col + 1] == "W") && Board.board[row+2][col+2] == null) { // checks if there's an opponent's piece in capturing spot
								 isCapture = true; // set the flag off
							 }
						} catch(ArrayIndexOutOfBoundsException exception) {}
					}
					if(Board.board[row][col] == "B") {
						try { // check for out of bounds error
							if((Board.board[row - 1][col - 1] == "w" || Board.board[row - 1][col - 1] == "W") && Board.board[row+2][col-2] == null) { // checks if there's an opponent's piece in capturing spot
								isCapture = true; // set the flag off
							}
						} catch(ArrayIndexOutOfBoundsException exception) {}
						try { // check for out of bounds error
							 if((Board.board[row - 1][col + 1] == "w" || Board.board[row - 1][col + 1] == "W") && Board.board[row+2][col+2] == null) { // checks if there's an opponent's piece in capturing spot
								 isCapture = true; // set the flag off
							 }
						} catch(ArrayIndexOutOfBoundsException exception) {}
					}
				}
			}
		}
	}
	public void multiCapture(int xn, int yn, int turn) { // if multiple pieces can be captured
		multiCapt = false;
		multiCaptMessage = false;
		if(turn == 1) { // if Player 1's turn
			try { // check for out of bounds error
				if((Board.board[xn - 1][yn - 1] == "b" || Board.board[xn - 1][yn - 1] == "B") && Board.board[xn-2][yn-2] == null) { // checks if there's an opponent's piece in capturing spot
					multiCapt = true; // set the flag off
				}
			} catch(ArrayIndexOutOfBoundsException exception) {}
			try { // check for out of bounds error
				 if((Board.board[xn - 1][yn + 1] == "b" || Board.board[xn - 1][yn + 1] == "B") && Board.board[xn-2][yn+2] == null) { // checks if there's an opponent's piece in capturing spot
					 multiCapt = true; // set the flag off
				 }
			} catch(ArrayIndexOutOfBoundsException exception) {}
			if(isKing == true) {
				try { // check for out of bounds error
					if((Board.board[xn + 1][yn - 1] == "w" || Board.board[xn + 1][yn - 1] == "W") && Board.board[xn+2][yn-2] == null) { // checks if there's an opponent's piece in capturing spot
						multiCapt = true; // set the flag off
						isKing = false;
					}
				} catch(ArrayIndexOutOfBoundsException exception) {}
				try { // check for out of bounds error
					 if((Board.board[xn + 1][yn + 1] == "w" || Board.board[xn + 1][yn + 1] == "W") && Board.board[xn+2][yn+2] == null) { // checks if there's an opponent's piece in capturing spot
						 multiCapt = true; // set the flag off
						 isKing = false;
					 }
				} catch(ArrayIndexOutOfBoundsException exception) {}
			}
		} else { // if Player 2's turn
			try { // check for out of bounds error
				if((Board.board[xn + 1][yn - 1] == "w" || Board.board[xn + 1][yn - 1] == "W") && Board.board[xn+2][yn-2] == null) { // checks if there's an opponent's piece in capturing spot
					multiCapt = true; // set the flag off
				}
			} catch(ArrayIndexOutOfBoundsException exception) {}
			try { // check for out of bounds error
				 if((Board.board[xn + 1][yn + 1] == "w" || Board.board[xn + 1][yn + 1] == "W") && Board.board[xn+2][yn+2] == null) { // checks if there's an opponent's piece in capturing spot
					 multiCapt = true; // set the flag off
				 }
			} catch(ArrayIndexOutOfBoundsException exception) {}
			if(isKing == true) {
				try { // check for out of bounds error
					if((Board.board[xn + 1][yn - 1] == "w" || Board.board[xn + 1][yn - 1] == "W") && Board.board[xn+2][yn-2] == null) { // checks if there's an opponent's piece in capturing spot
						multiCapt = true; // set the flag off
						isKing = false;
					}
				} catch(ArrayIndexOutOfBoundsException exception) {}
				try { // check for out of bounds error
					 if((Board.board[xn + 1][yn + 1] == "w" || Board.board[xn + 1][yn + 1] == "W") && Board.board[xn+2][yn+2] == null) { // checks if there's an opponent's piece in capturing spot
						 multiCapt = true; // set the flag off
						 isKing = false;
					 }
				} catch(ArrayIndexOutOfBoundsException exception) {}
			}
		}
	}
	
	public boolean rulesMet(int x, int y, int xn, int yn, int turn) { // checks if all the rules are met
		if(emptyField(x, y) == true && occupiedField(xn, yn) == true && rightPiece(x, y, turn) == true
				&& makeMove(x, y, xn, yn, turn) == true) {
			return true;
		} else {
			return false;
		}
	}
	public String errorMessages(int x, int y, int xn, int yn, int turn) { // error messages if different rules are broken
		if(emptyField(x, y) == false) {
			return "\n!!! No piece in that field !!!\n";
		}
		else if(occupiedField(xn, yn) == false) {
			return "\n!!! This field is occupied !!!\n";
		}
		else if(rightPiece(x, y, turn) == false) {
			return "\n!!! You can only move your pieces !!!\n";
		}
		else if(makeMove(x, y, xn, yn, turn) == false && isCapture == true) {
			return "\n!!! You have to capture your opponent's piece !!!\n";
		}
		else if(makeMove(x, y, xn, yn, turn) == false) {
			return "\n!!! Only forward diagonal moves are allowed !!!\n";
		} else {
			return null;
		}
	}
}



/*

else if(xn == x + 2) {
						if(yn == y + 2 && Board.board[x+1][y+1] == "#") {
							Board.board[x+1][y+1] = null;
							return true;
						} else if(yn == y - 2 && Board.board[x+1][y-1] == "#") {
							Board.board[x+1][y-1] = null;
							return true;
						} else {
							return false;
						}
					} 

else if(xn == x + 2) {
						if(yn == y + 2 && Board.board[x+1][y+1] == "O") {
							Board.board[x+1][y+1] = null;
							return true;
						} else if(yn == y - 2 && Board.board[x+1][y-1] == "O") {
							Board.board[x+1][y-1] = null;
							return true;
						} else {
							return false;
						}
					} 

*/
