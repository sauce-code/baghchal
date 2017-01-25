package model.interfaces;

/**
 * Interface for a Bagh Chal game model. See
 * <a href="https://en.wikipedia.org/wiki/Bagh-Chal">https://en.wikipedia.org/wiki/Bagh-Chal</a> for
 * more information.
 * 
 * @since v0.1
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
	 * Width of a Bagh Chal board.
	 * 
	 * @since v0.1
	 */
	public static final int WIDTH = 5;

	/**
	 * Height of a Bagh Chal board.
	 * 
	 * @since v0.1
	 */
	public static final int HEIGHT = 5;

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
	 * Sets a goat on a desired tile. If the desired tile is not empty, there are no goats left to
	 * set or the game is already over, nothing happens.
	 * 
	 * @param row
	 *            row of the desired tile
	 * @param column
	 *            column of the desired tile
	 * @return {@code true}, if the desired tile is free and there is at least 1 goat left to set
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code row} or {@code column} negative or greater equal {@link #DIM}
	 * 
	 * @since v0.1
	 */
	public boolean set(int row, int column);

	/**
	 * Selects a desired tile in order to perform a move. Therefore, the desired tile must contain a
	 * figure of the current player and the game mustn't be over yet. Also, if the current player is
	 * goat, there mustn't be any goats left to set.<br>
	 * If the selection hasn't been successful, nothing happens.<br>
	 * If there is already a tile selected, it will be overwritten by any new successful select.
	 * 
	 * @param row
	 *            row of the desired tile
	 * @param column
	 *            column of the desired tile
	 * @return {@code true}, if the selection has been successful
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code row} or {@code column} negative or greater equal {@link #DIM}
	 * 
	 * @since v0.1
	 */
	public boolean select(int row, int column);

	/**
	 * Moves the previously selected figure by {@link #select(int, int)} to a desired location. If
	 * no figure has been selected previously or the desired location is invalid, nothing happens.
	 * If the move is successful, the selected figure will be unselected.
	 * 
	 * @param targetRow
	 *            row of the desired target tile
	 * @param targetColumn
	 *            column of the desired target tile
	 * @return {@code true}, if the move has been successful
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code targetRow} or {@code targetColumn} negative or greater equal
	 *             {@link #DIM}
	 * 
	 * @since v0.1
	 */
	public boolean move(int targetRow, int targetColumn);

	/**
	 * Returns a <b>copy</b> of the current state of the board.
	 * 
	 * @return <b>copy</b> of the current state of the board
	 * 
	 * @since v0.1
	 */
	public Player[][] getBoard();

	/**
	 * Returns the current player, who will do the next move.
	 * 
	 * @return
	 * 		<ul>
	 *         <li>{@link Player#GOAT}, if it's goat player's turn</li>
	 *         <li>{@link Player#TIGER}, if it's tiger player's turn</li>
	 *         <li>{@link Player#NONE}, if the game is over</li>
	 *         </ul>
	 * 
	 * @since v0.1
	 */
	public Player getCurrentPlayer();

	/**
	 * Returns the number of goats eaten.
	 * 
	 * @return number of goats eaten
	 * 
	 * @since v0.1
	 */
	public int getGoatsEaten();

	/**
	 * Returns the number of goats left to set.
	 * 
	 * @return number of goats left to set
	 * 
	 * @since v0.1
	 */
	public int getGoatsLeftToSet();

	/**
	 * Tells wether the game is over or not.
	 * 
	 * @return {@code true}, if the tigers capture five goats, or the goats have blocked the tigers
	 *         from being able to move
	 * 
	 * @since v0.1
	 */
	public boolean isGameOver();

	/**
	 * Returns the winner of this game.
	 * 
	 * @return
	 * 		<ul>
	 *         <li>{@link Player#GOAT}, if goat player won</li>
	 *         <li>{@link Player#TIGER}, if tiger player won</li>
	 *         <li>{@link Player#NONE}, if the game is not over yet</li>
	 *         </ul>
	 * 
	 * @since v0.1
	 */
	public Player getWinner();

}
