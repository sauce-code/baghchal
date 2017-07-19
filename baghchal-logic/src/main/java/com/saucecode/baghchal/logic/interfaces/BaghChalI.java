package com.saucecode.baghchal.logic.interfaces;

/**
 * Interface for a Bagh Chal game model. See <a href=
 * "https://en.wikipedia.org/wiki/Bagh-Chal">https://en.wikipedia.org/wiki/Bagh-Chal</a>
 * for more information.
 * 
 * @since v1.0
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public interface BaghChalI {

	/**
	 * Dimension (width <b>and</b> height) of a Bagh Chal board.
	 * 
	 * @since v1.0
	 */
	public static final int DIM = 5;

	/**
	 * Number of goats being set during a game.
	 * 
	 * @since v1.0
	 */
	public static final int GOATS_START_COUNT = 20;

	/**
	 * Numbers of goats needed to be eaten by the tiger player in order to win.
	 * 
	 * @since v1.0
	 */
	public static final int TIGER_WIN_CONDITION = 5;

	/**
	 * Returns a <b>copy</b> of the current state of the board.
	 * 
	 * @return <b>copy</b> of the current state of the board
	 * 
	 * @since v1.0
	 */
	public Player[][] getBoard();

	/**
	 * Returns the number of goats eaten.
	 * 
	 * @return number of goats eaten
	 * 
	 * @since v1.0
	 */
	public int getGoatsEaten();

	/**
	 * Returns the number of goats left to set.
	 * 
	 * @return number of goats left to set
	 * 
	 * @since v1.0
	 */
	public int getGoatsLeftToSet();

	/**
	 * Performs an action. This method's behavior depends on its current state,
	 * see {@link #getState()}.
	 * <ul>
	 * <li>{@link State#GOAT_SET}: sets a goat on tile {@code row} /
	 * {@code column}</li>
	 * <li>{@link State#GOAT_SELECT}: selects tile {@code row} /
	 * {@code column}</li>
	 * <li>{@link State#GOAT_MOVE}: moves the selected goat to {@code row} /
	 * {@code column}</li>
	 * <li>{@link State#TIGER_SELECT}: selects tile {@code row} /
	 * {@code column}</li>
	 * <li>{@link State#TIGER_MOVE}: moves the selected tiger to {@code row} /
	 * {@code column}</li>
	 * <li>any other {@link State}: no action will take place</li>
	 * </ul>
	 * 
	 * @param row
	 *            row used for this action
	 * @param column
	 *            column used for this action
	 * @return {@code true}, if this action has been successful
	 * @throws IllegalArgumentException
	 *             if {@code row} or {@code column} are smaller than {@code 0}
	 *             or greater equals than {@link BaghChalI#DIM}
	 * 
	 * @since v1.0
	 */
	public boolean action(int row, int column);

	/**
	 * Returns the current selected tile.
	 * 
	 * @return
	 *         <ul>
	 *         <li>current selected tile</li>
	 *         <li>{@code null}, if there is currently no selected tile</li>
	 *         </ul>
	 * 
	 * @since v1.0
	 */
	public Selection getSelection();

	/**
	 * Returns the current {@link State}. This method will never return
	 * {@code null}.
	 * 
	 * @return current {@link State}
	 * 
	 * @since v1.0
	 */
	public State getState();

	/**
	 * Undos the last action. Ignores selections, so the resulting {@link State}
	 * will always be {@link State#GOAT_SET}, {@link State#GOAT_SELECT} or
	 * {@link State#TIGER_SELECT}.
	 * 
	 * @return {@code true}, if undo has been successful
	 * 
	 * @since v1.2
	 */
	public default boolean undo() {
		return false;
	}

	/**
	 * Redos the last undone action.
	 * 
	 * @return {@code true}, if redo has been successful
	 * 
	 * @since v1.2
	 */
	public default boolean redo() {
		return false;
	}

	/**
	 * Tells wether there is any undo left.
	 * 
	 * @return {@code true}, if there is any undo left
	 * 
	 * @since v1.2
	 */
	public default boolean isAnyUndoLeft() {
		return false;
	}

	/**
	 * Tells wether there is any redo left.
	 * 
	 * @return {@code true}, if there is any redo left
	 * 
	 * @since v1.2
	 */
	public default boolean isAnyRedoLeft() {
		return false;
	}

	/**
	 * States used by any Bagh Chal implementation.
	 * 
	 * @author Torben Kr&uuml;ger
	 * 
	 * @see BaghChalI
	 */
	public enum State {

		/**
		 * It's goat player's turn. There are goats left to set, see
		 * {@link BaghChalI#getGoatsLeftToSet()}. His task is to place a goat on
		 * a free field.
		 */
		GOAT_SET,

		/**
		 * It's tiger player's turn. His task is to select a tiger to move.
		 */
		TIGER_SELECT,

		/**
		 * It's tiger player's turn. His task is to move the selected tiger. He
		 * may also select another tiger or unselect the selected tiger.
		 */
		TIGER_MOVE,

		/**
		 * It's goat player's turn. There are no goats left to set, see
		 * {@link BaghChalI#getGoatsLeftToSet()}. His task is to select a goat
		 * to move.
		 */
		GOAT_SELECT,

		/**
		 * It's goat player's turn. His task is to move the selected goat. He
		 * may also select another goat or unselect the selected goat.
		 */
		GOAT_MOVE,

		/**
		 * The game is over. Goat player won.
		 */
		GOAT_WON,

		/**
		 * The game is over. Tiger player won.
		 */
		TIGER_WON;

	}

	/**
	 * Represents a selection of a certain tile.
	 * 
	 * @author Torben Kr&uuml;ger
	 *
	 */
	public class Selection {

		/**
		 * Row of the selection.
		 */
		public final int row;

		/**
		 * Column of the selection.
		 */
		public final int column;

		/**
		 * Creates a new selection.
		 * 
		 * @param row
		 *            row
		 * @param column
		 *            column
		 */
		public Selection(int row, int column) {
			this.row = row;
			this.column = column;
		}

		@Override
		public String toString() {
			return "[row=" + row + ", column=" + column + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + column;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Selection other = (Selection) obj;
			if (column != other.column)
				return false;
			if (row != other.row)
				return false;
			return true;
		}

	}

}
