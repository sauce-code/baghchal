package model;

import model.interfaces.BaghChalI;
import model.interfaces.Player;

/**
 * Implementation of {@link model.interfaces.BaghChalI}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class BaghChal implements BaghChalI {

	/**
	 * A matrix for all possible moves.
	 */
	private static final int[][] MOVES = {
			{ 0, 1, 2, 0, 0, 1, 1, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 2, 1, 0, 1, 2, 0, 1, 1, 1, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 1, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 0, 0, 1, 0, 1, 2, 0, 1, 1, 1, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 0, 2, 1, 0, 1, 0, 0, 1, 1, 1, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 },
			{ 2, 0, 2, 0, 0, 1, 1, 0, 0, 0, 0, 1, 2, 0, 0, 1, 1, 0, 0, 0, 2, 0, 2, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0 },
			{ 2, 0, 2, 0, 2, 0, 1, 1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1, 1, 0, 2, 0, 2, 0, 2 },
			{ 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0 },
			{ 0, 0, 2, 0, 2, 0, 0, 0, 1, 1, 0, 0, 2, 1, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 2 },
			{ 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 1, 1, 1, 0, 0, 1, 0, 1, 2, 0, 1, 1, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 1, 1, 1, 0, 2, 1, 0, 1, 0, 0, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 1, 1, 0, 0, 0, 0, 1, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 2, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 1, 1, 1, 0, 2, 1, 0, 1, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 2, 1, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 1, 1, 0, 0, 2, 1, 0 } };

	/**
	 * The board being used.
	 */
	private Player[][] board;

	/**
	 * The number of goats left to set.
	 */
	private int goatsLeftToSet;

	/**
	 * The number of goats eaten.
	 */
	private int goatsEaten;

	/**
	 * The current player.
	 */
	private Player currentPlayer;

	/**
	 * The winner.
	 */
	private Player winner;

	/**
	 * The current selection.
	 */
	private Selection selection;

	/**
	 * Creates a new instance of {@link BaghChal}.
	 */
	public BaghChal() {
		board = new Player[HEIGHT][WIDTH];
		for (int row = 0; row < HEIGHT; row++) {
			for (int column = 0; column < WIDTH; column++) {
				board[row][column] = Player.NONE;
			}
		}
		board[0][0] = Player.TIGER;
		board[0][WIDTH] = Player.TIGER;
		board[HEIGHT][WIDTH] = Player.TIGER;
		board[HEIGHT][0] = Player.TIGER;
		goatsLeftToSet = GOATS_START_COUNT;
		goatsEaten = 0;
		currentPlayer = Player.GOAT;
		winner = Player.NONE;
		selection = null;
	}

	@Override
	public boolean set(int row, int column) {
		checkInput(row);
		checkInput(column);
		if (!isGameOver() && (goatsLeftToSet > 0) && (board[row][column] == Player.NONE)) {
			board[row][column] = Player.GOAT;
			return true;
		}
		return false;
	}

	@Override
	public boolean select(int row, int column) {
		checkInput(row);
		checkInput(column);
		if (!isGameOver() && (board[row][column] == currentPlayer)
				&& (currentPlayer != Player.GOAT || goatsLeftToSet == 0)) {
			selection = new Selection(row, column);
			return true;
		}
		return false;
	}

	@Override
	public boolean move(int targetRow, int targetColumn) {
		checkInput(targetRow);
		checkInput(targetColumn);
		if ((selection == null) || (board[targetRow][targetColumn] != Player.NONE)) {
			return false;
		}
		switch (getMovePossibility(selection.row, selection.column, targetRow, targetColumn)) {
		case 0:
			return false;
		case 1:
			board[selection.row][selection.column] = null;
			board[targetRow][targetColumn] = currentPlayer;
			break;
		case 2:
			int betweenRow = (selection.row + targetRow) / 2;
			int betweenColumn = (selection.column + targetColumn) / 2;
			if ((currentPlayer != Player.TIGER)
					|| (board[betweenRow][betweenColumn] != Player.GOAT)) {
				return false;
			}
			board[selection.row][selection.column] = Player.NONE;
			board[betweenRow][betweenColumn] = Player.NONE;
			goatsEaten++;
			board[targetRow][targetColumn] = Player.TIGER;
			break;
		default:
			throw new InternalError();
		}
		selection = null;
		currentPlayer = currentPlayer.getOpponent();
		checkGameOver();
		return true;
	}

	private void checkGameOver() {
		if (goatsEaten == TIGER_WIN_CONDITION) {
			winner = Player.TIGER;
			currentPlayer = Player.NONE;
		}
		if (!anyTigerCanMove()) {
			winner = Player.GOAT;
			currentPlayer = Player.NONE;
		}
	}

	/**
	 * Returns wether at least 1 tiger is able to move.
	 * 
	 * @return {@code true}, if at least 1 tiger is able to move
	 */
	private boolean anyTigerCanMove() {
		boolean canMove = false;
		for (int row = 0; row < DIM; row++) {
			for (int column = 0; column < DIM; column++) {
				if ((board[row][column] == Player.TIGER) && tigerCanMove(row, column)) {
					canMove = true;
				}
			}
		}
		return canMove;
	}

	/**
	 * Returns wether a specific tiger is able to move. This method expects that the given
	 * {@code row} and {@code column} point on a tiger.
	 * 
	 * @param row
	 *            row of the specific tiger
	 * @param column
	 *            column of the specific tiger
	 * @return {@code true}, if the specific tiger is able to move
	 */
	private boolean tigerCanMove(int row, int column) {
		boolean canMove = false;
		for (int targetRow = 0; targetRow < DIM; targetRow++) {
			for (int targetColumn = 0; targetColumn < DIM; targetColumn++) {
				if (getMovePossibility(row, column, targetRow, targetColumn) > 0) {
					canMove = true;
				}
			}
		}
		return canMove;
	}

	/**
	 * Returns a possiblity of wether a move is possible or not.
	 * 
	 * @param row
	 *            row
	 * @param column
	 *            column
	 * @param targetRow
	 *            target row
	 * @param targetColumn
	 *            target column
	 * @return
	 * 		<ul>
	 *         <li>{@code 0}, if there is no possible move</li>
	 *         <li>{@code 1}, if a direct move without jump would be possible</li>
	 *         <li>{@code 2}, if a jump would be possible</li>
	 *         </ul>
	 */
	private int getMovePossibility(int row, int column, int targetRow, int targetColumn) {
		return MOVES[row * 5 + column][targetRow * 5 + targetColumn];
	}

	@Override
	public Player[][] getBoard() {
		return board.clone();
	}

	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public int getGoatsEaten() {
		return goatsEaten;
	}

	@Override
	public int getGoatsLeftToSet() {
		return goatsLeftToSet;
	}

	@Override
	public boolean isGameOver() {
		return (winner != Player.NONE);
	}

	@Override
	public Player getWinner() {
		return winner;
	}

	private class Selection {

		private Selection(int row, int column) {
			this.row = row;
			this.column = column;
		}

		private int row;

		private int column;

	}

	/**
	 * Throws an exception, if the value is not in range {@code [ 0 ; } {@link BaghChalI#DIM}
	 * {@code )}.
	 * 
	 * @param value
	 *            value to be checked
	 * 
	 * @throws IllegalArgumentException
	 *             if the value is not in range {@code [ 0 ; } {@link BaghChalI#DIM} {@code )}
	 */
	private void checkInput(int value) {
		if (value < 0 || value >= DIM) {
			throw new IllegalArgumentException(
					"has to be in range [ 0 ; " + DIM + " ), but was " + value);
		}
	}

}
