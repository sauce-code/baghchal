package com.saucecode.baghchal.logic;

import java.util.Arrays;

import com.saucecode.baghchal.logic.interfaces.BaghChalI;
import com.saucecode.baghchal.logic.interfaces.Player;

/**
 * Implementation of {@link com.saucecode.baghchal.logic.interfaces.BaghChalI}.
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
	 * The number of goats eaten. This value <b>always</b> has to be in range [ 0 ;
	 * {@link BaghChalI#TIGER_WIN_CONDITION} ].
	 */
	private int goatsEaten;

	/**
	 * The current selection.
	 */
	private Selection selected;

	/**
	 * The current state.
	 */
	private State state;

	/**
	 * The previous game. Used for {@link #undo()}.
	 */
	private BaghChal previous;

	/**
	 * The following game. used for {@link #redo()}.
	 */
	private BaghChal next;

	/**
	 * Creates a new instance of {@link BaghChal}.
	 */
	public BaghChal() {
		board = new Player[DIM][DIM];
		for (int row = 0; row < DIM; row++) {
			for (int column = 0; column < DIM; column++) {
				board[row][column] = Player.NONE;
			}
		}
		board[0][0] = Player.TIGER;
		board[0][DIM - 1] = Player.TIGER;
		board[DIM - 1][DIM - 1] = Player.TIGER;
		board[DIM - 1][0] = Player.TIGER;
		goatsLeftToSet = GOATS_START_COUNT;
		goatsEaten = 0;
		selected = null;
		state = State.GOAT_SET;
		previous = null;
		next = null;
	}

	/**
	 * Creates a clone of an existing BaghChal game.
	 * 
	 * @param orig
	 *            original game, wwich shall be cloned
	 */
	private BaghChal(BaghChal orig) {
		board = new Player[DIM][DIM];
		for (int row = 0; row < DIM; row++) {
			board[row] = orig.board[row].clone();
		}
		goatsLeftToSet = orig.goatsLeftToSet;
		goatsEaten = orig.goatsEaten;
		selected = null;
		state = orig.state;
		if (state == State.GOAT_MOVE) {
			state = State.GOAT_SELECT;
		}
		if (state == State.TIGER_MOVE) {
			state = State.TIGER_SELECT;
		}
		previous = orig.previous;
		next = orig.next;
	}

	@Override
	public Player[][] getBoard() {
		return board.clone();
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
	public Selection getSelection() {
		return selected;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public boolean action(int row, int column) {

		checkInput(row);
		checkInput(column);

		switch (state) {

		case TIGER_WON:
			return false;

		case GOAT_WON:
			return false;

		case GOAT_SET:
			if (board[row][column] == Player.NONE) {
				previous = clone();
				previous.next = this;
				board[row][column] = Player.GOAT;
				goatsLeftToSet--;
				switch (getWinner()) {
				case GOAT:
					state = State.GOAT_WON;
					return true;
				case TIGER:
					state = State.TIGER_WON;
					return true;
				case NONE:
					state = State.TIGER_SELECT;
					return true;
				default:
					throw new InternalError("no such enum");
				}
			}
			return false;

		case GOAT_MOVE:
			switch (board[row][column]) {
			case GOAT:
				if ((selected.row == row) && (selected.column == column)) {
					selected = null;
					state = State.GOAT_SELECT;
					return true;
				} else {
					selected = new Selection(row, column);
					return true;
				}
			case TIGER:
				return false;
			case NONE:
				if ((getMovePossibility(selected.row, selected.column, row, column)) == 1) {
					previous = clone();
					board[selected.row][selected.column] = Player.NONE;
					board[row][column] = Player.GOAT;
					selected = null;
					switch (getWinner()) {
					case GOAT:
						state = State.GOAT_WON;
						return true;
					case TIGER:
						state = State.TIGER_WON;
						return true;
					case NONE:
						state = State.TIGER_SELECT;
						return true;
					default:
						throw new InternalError("no such enum");
					}
				}
				return false;
			default:
				throw new InternalError("no such enum");
			}

		case GOAT_SELECT:
			if (board[row][column] == Player.GOAT) {
				selected = new Selection(row, column);
				state = State.GOAT_MOVE;
				return true;
			}
			return false;

		case TIGER_MOVE:
			switch (board[row][column]) {
			case GOAT:
				return false;
			case TIGER:
				if ((selected.row == row) && (selected.column == column)) {
					selected = null;
					state = State.TIGER_SELECT;
					return true;
				} else {
					selected = new Selection(row, column);
					return true;
				}
			case NONE:
				switch (getMovePossibility(selected.row, selected.column, row, column)) {
				case 0:
					return false;
				case 1:
					previous = clone();
					board[selected.row][selected.column] = Player.NONE;
					board[row][column] = Player.TIGER;
					selected = null;
					state = (goatsLeftToSet > 0) ? State.GOAT_SET : State.GOAT_SELECT;
					return true;
				case 2:
					int betweenRow = (selected.row + row) / 2;
					int betweenColumn = (selected.column + column) / 2;
					if (board[betweenRow][betweenColumn] != Player.GOAT) {
						return false;
					}
					previous = clone();
					board[selected.row][selected.column] = Player.NONE;
					board[betweenRow][betweenColumn] = Player.NONE;
					goatsEaten++;
					board[row][column] = Player.TIGER;
					selected = null;
					switch (getWinner()) {
					case GOAT:
						state = State.GOAT_WON;
						return true;
					case TIGER:
						state = State.TIGER_WON;
						return true;
					case NONE:
						state = (goatsLeftToSet > 0) ? State.GOAT_SET : State.GOAT_SELECT;
						return true;
					default:
						throw new InternalError("no such enum");
					}
				default:
					throw new InternalError("no such enum");
				}
			default:
				throw new InternalError("no such enum");
			}

		case TIGER_SELECT:
			if (board[row][column] == Player.TIGER) {
				selected = new Selection(row, column);
				state = State.TIGER_MOVE;
				return true;
			}
			return false;

		default:
			throw new InternalError("no such enum");
		}

	}

	@Override
	public boolean undo() {
		if (isAnyUndoLeft()) {
			BaghChal clone = clone();
			board = previous.board;
			goatsLeftToSet = previous.goatsLeftToSet;
			goatsEaten = previous.goatsEaten;
			selected = previous.selected;
			state = previous.state;
			previous = previous.previous;
			next = clone;
			return true;
		}
		return false;
	}

	@Override
	public boolean redo() {
		if (isAnyRedoLeft()) {
			BaghChal clone = clone();
			if (previous != null) {
				previous.next = clone();
			}
			board = next.board;
			goatsLeftToSet = next.goatsLeftToSet;
			goatsEaten = next.goatsEaten;
			selected = next.selected;
			state = next.state;
			previous = clone;
			clone.next = this;
			next = next.next;
			return true;
		}
		return false;
	}

	@Override
	public boolean isAnyUndoLeft() {
		return previous != null;
	}

	@Override
	public boolean isAnyRedoLeft() {
		return next != null;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (int row = 0; row < DIM; row++) {
			for (int column = 0; column < DIM; column++) {
				sb.append(board[row][column].toChar());
				sb.append("---");
			}
			sb.delete(sb.length() - 3, sb.length());
			sb.append('\n');
			if (row % 2 == 0) {
				sb.append("| \\ | / | \\ | / |\n");
			} else {
				sb.append("| / | \\ | / | \\ |\n");
			}
		}
		sb.delete(sb.length() - 18, sb.length());

		sb.append("goats left to set: ");
		sb.append(goatsLeftToSet);
		sb.append('\n');

		sb.append("goats eaten: ");
		sb.append(goatsEaten);
		sb.append('\n');

		sb.append("selected: ");
		sb.append(selected);
		sb.append('\n');

		sb.append("state: ");
		sb.append(state);
		sb.append('\n');

		return sb.toString();
	}

	@Override
	public BaghChal clone() {
		return new BaghChal(this);
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

	/**
	 * Returns wether at least 1 tiger is able to move.
	 * 
	 * @return {@code true}, if at least 1 tiger is able to move
	 */
	private boolean anyTigerCanMove() {
		for (int row = 0; row < DIM; row++) {
			for (int column = 0; column < DIM; column++) {
				if ((board[row][column] == Player.TIGER) && tigerCanMove(row, column)) {
					return true;
				}
			}
		}
		return false;
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
		for (int targetRow = 0; targetRow < DIM; targetRow++) {
			for (int targetColumn = 0; targetColumn < DIM; targetColumn++) {
				int move = getMovePossibility(row, column, targetRow, targetColumn);
				if (move == 2 && board[targetRow][targetColumn] == Player.NONE
						&& board[(row + targetRow) / 2][(column + targetColumn)
								/ 2] == Player.GOAT) {
					return true;
				}
				if (move == 1 && board[targetRow][targetColumn] == Player.NONE) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns the winner of this match.
	 * 
	 * @return
	 * 		<ul>
	 *         <li>{@link Player#GOAT}, if no tiger can move anymore</li>
	 *         <li>{@link Player#TIGER}, if {@link BaghChalI#TIGER_WIN_CONDITION} goats are eaten
	 *         </li>
	 *         <li>{@link Player#NONE}, else</li>
	 *         </ul>
	 */
	private Player getWinner() {
		if (goatsEaten == TIGER_WIN_CONDITION) {
			return Player.TIGER;
		}
		if (!anyTigerCanMove()) {
			return Player.GOAT;
		}
		return Player.NONE;
	}

	/**
	 * Throws an exception, if the value is not in range {@code [ 0 ; } {@link BaghChalI#DIM}
	 * {@code )}.
	 * 
	 * @param value
	 *            value to be checked
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code value} is not in range {@code [ 0 ; } {@link BaghChalI#DIM} {@code )}
	 */
	private void checkInput(int value) {
		if (value < 0 || value >= DIM) {
			throw new IllegalArgumentException(
					"value has to be in range [ 0 ; " + DIM + " ), but was " + value);
		}
	}

	@Override
	/*
	 * this method IGNORES fields previous and next
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		result = prime * result + goatsEaten;
		result = prime * result + goatsLeftToSet;
		result = prime * result + ((selected == null) ? 0 : selected.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	/*
	 * this method IGNORES fields previous and next
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaghChal other = (BaghChal) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		if (goatsEaten != other.goatsEaten)
			return false;
		if (goatsLeftToSet != other.goatsLeftToSet)
			return false;
		if (selected == null) {
			if (other.selected != null)
				return false;
		} else if (!selected.equals(other.selected))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

}
