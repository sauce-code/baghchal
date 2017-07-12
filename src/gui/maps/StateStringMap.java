package gui.maps;

import java.util.HashMap;

import model.interfaces.BaghChalI.State;

/**
 * Stores the corresponding Strings for each {@link State}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class StateStringMap extends HashMap<State, String> {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 6356717555055526293L;
	
	/**
	 * Creates a new State String Map.
	 */
	public StateStringMap() {
		super();
		put(State.GOAT_MOVE, "goat's turn");
		put(State.GOAT_SELECT, "goat's turn");
		put(State.GOAT_SET, "goat's turn");
		put(State.GOAT_WON, "goats won!");
		put(State.TIGER_MOVE, "tiger's turn");
		put(State.TIGER_SELECT, "tiger's turn");
		put(State.TIGER_WON, "tigers won!");
	}

}
