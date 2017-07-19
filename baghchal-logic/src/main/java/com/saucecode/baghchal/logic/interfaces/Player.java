package com.saucecode.baghchal.logic.interfaces;

/**
 * Represents the players of a Bagh Chal game.
 * 
 * @author Torben Kr&uuml;ger
 * 
 */
public enum Player {

	/**
	 * The goat player.
	 */
	GOAT,

	/**
	 * The tiger player.
	 */
	TIGER,

	/**
	 * No player.
	 */
	NONE;

	/**
	 * Returns the opponent.
	 * 
	 * @return
	 *         <ul>
	 *         <li>{@link #TIGER}, if {@code this} is {@link #GOAT}</li>
	 *         <li>{@link #GOAT}, if {@code this} is {@link #TIGER}</li>
	 *         <li>{@link #NONE}, if {@code this} is {@link #NONE}</li>
	 *         </ul>
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

	/**
	 * Returns the enum as a char.
	 * 
	 * @return enum as a char
	 */
	public char toChar() {
		switch (this) {
		case GOAT:
			return 'G';
		case TIGER:
			return 'T';
		case NONE:
			return ' ';
		default:
			throw new IllegalArgumentException("no such enum");
		}
	}

}
