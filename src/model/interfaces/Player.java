package model.interfaces;

/**
 * Represents the players of a Bagh Chal game.
 * 
 * @since v0.1
 * 
 * @author Torben Kr&uuml;ger
 * 
 * @see BaghChalI
 * 
 */
public enum Player {

	/**
	 * The goat player.
	 * 
	 * @since v0.1
	 */
	GOAT,

	/**
	 * The tiger player.
	 * 
	 * @since v0.1
	 */
	TIGER,

	/**
	 * No player.
	 * 
	 * @since v0.1
	 */
	NONE;

	/**
	 * Returns the opponent.
	 * 
	 * @return
	 * 		<ul>
	 *         <li>{@link #TIGER}, if {@code this} is {@link #GOAT}</li>
	 *         <li>{@link #GOAT}, if {@code this} is {@link #TIGER}</li>
	 *         <li>{@link #NONE}, if {@code this} is {@link #NONE}</li>
	 *         </ul>
	 * 
	 * @since v1.0
	 */
	public Player getOpponent() {
		switch (this) {
		case GOAT:
			return TIGER;
		case TIGER:
			return GOAT;
		case NONE:
			return NONE;
		default:
			throw new IllegalArgumentException("no such enum");
		}
	}

}
