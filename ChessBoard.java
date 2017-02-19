package chessAttacks;

import java.util.*;
import java.io.*;

class ChessBoard {
	int boardSize;
	LinkedList pieces = new LinkedList();

	ChessBoard(int boardSize) {
		this.boardSize = boardSize;
	}

	// Adds chess piece to linked list
	void addPiece(ChessPiece piece) {
		pieces.add(piece);
	}

	ChessPiece getPieceAt(int col, int row) {
		ChessPiece piece = null;
		for (int i = 0; i < pieces.size(); i++) {
			piece = pieces.get(i);
			if (piece.col == col && piece.row == row) {
				return piece;
			}
		}
		return piece;
	}

	boolean moveTo(ChessPiece movePiece, int x, int y) {
		boolean okToMove = true;
		boolean capturedPiece = false;
		int capturedPieceIndex = 0;
		if (movePiece.canMove(x, y)) {
			for (int i = 0; i < pieces.size; i++) {
				ChessPiece piece = pieces.get(i);
				if (movePiece.col == piece.col && movePiece.row == piece.row)
					continue;
				
				// if no piece at square, check if attack path is clear
				if (movePiece.isBlocking(piece, x, y)) {
					okToMove = false;
					break;
				}
				// check if there is a piece at square for capture
				else if (piece.col == x && piece.row == y) {
					// check if pieces are same color
					if (movePiece.color == piece.color) {
						okToMove = false;
						break;
					} else {
						// Capture the piece
						capturedPiece = true;
						capturedPieceIndex = i;
					}
				}
			}
		}
		// Check if we have a capture piece, we need to remove it
		if (capturedPiece) {
			pieces.delete(capturedPieceIndex);
		}

		if (okToMove) {
			movePiece.col = x;
			movePiece.row = y;
		}

		return okToMove;
	}

	// boolean canMove (ChessPiece movePiece, int x, int y) {
	// boolean check = true;
	// for (int i = 0; i < pieces.size; i++) {
	// ChessPiece piece = pieces.get(i);
	// //if no piece at square, check if attack path is clear
	// if (movePiece.isBlocking (piece, x, y)) {
	// check = false;
	// }
	// //check if there is a piece at square for capture
	// else if (piece.col == x && piece.row == y) {
	// //check if pieces are same color
	// if (movePiece.color == piece.color) {
	// check = false;
	// }
	// else {
	// check = true;
	// }
	//
	// }
	// }
	// return check;
	// }

	// //Makes sure that no pieces are placed in the same square
	// boolean validatePieces () {
	// //Loops through and compares every single piece with each other
	// for (int i = 0; i < pieces.size(); i++) {
	// ChessPiece currentPiece = pieces.get(i);
	// for (int j = 0; j < pieces.size(); j++) {
	// //Makes sure that you aren't comparing the same pieces
	// if (j != i) {
	// ChessPiece nextPiece = pieces.get(j);
	// if (currentPiece.col == nextPiece.col && currentPiece.row ==
	// nextPiece.row) {
	// return false;
	// }
	// }
	// }
	// }
	// return true;
	// }
	//
	// // if there is not exactly one black and one white king, return false
	// boolean validateKings () {
	// //checks to see if a king of each type is found
	// boolean whiteKing = false;
	// boolean blackKing = false;
	// //keeps count of how many kings of each type are found
	// int white = 0;
	// int black = 0;
	// for (int i = 0; i < pieces.size(); i ++ ) {
	// ChessPiece c = pieces.get(i);
	// if (c.piece == 'k') {
	// whiteKing = true;
	// white++;
	// }
	// if (c.piece == 'K') {
	// blackKing = true;
	// black++;
	// }
	// }
	// if (whiteKing && blackKing) {
	// //makes sure there is exactly ONE of each type of king
	// if (white == 1 && black == 1) {
	// return true;
	// }
	// }
	// return false;
	// }

	// Returns a string of the type of chess piece located at query square
	// Returns "-" if no piece is found at query square
	// String querySquare(int col, int row) {
	// for (int i = 0; i < pieces.size(); i++) {
	// ChessPiece piece = pieces.get(i);
	// if (piece.col == col && piece.row == row) {
	// return Character.toString(piece.piece);
	// }
	// }
	// return "-";
	// }

	// //Returns string of attacking pieces' type, col, row to analysis.txt
	// String determineAttackPieces() {
	// //Loops through and compares every single piece with each other to check
	// if they are attacking each other
	// for (int i = 0; i < pieces.size(); i++) {
	// ChessPiece currentPiece = pieces.get(i);
	// for (int j = 0; j < pieces.size(); j++) {
	// //Makes sure that you aren't comparing the same pieces
	// if (j != i) {
	// ChessPiece nextPiece = pieces.get(j);
	// if (currentPiece.isAttacking(nextPiece)) {
	// return currentPiece.piece + " " +currentPiece.col + " " +
	// currentPiece.row + " " + nextPiece.piece + " " +nextPiece.col + " " +
	// nextPiece.row;
	// }
	// }
	// }
	// }
	// return "-";
	// }
}
