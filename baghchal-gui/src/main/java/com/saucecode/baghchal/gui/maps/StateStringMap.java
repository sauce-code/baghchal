package com.saucecode.baghchal.gui.maps;

import java.util.HashMap;

import com.saucecode.baghchal.logic.interfaces.BaghChalI.State;

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
	private static final long serialVersionUID = -5119151295623501069L;

	/**
	 * Creates a new State String Map.
	 */
	public StateStringMap() {
		super();
		put(State.GOAT_MOVE, "goat's turn");
		put(State.GOAT_SELECT, "goat's turn");
		put(State.GOAT_SET, "goat's turn");
		put(State.GOAT_WON, "goat won!");
		put(State.TIGER_MOVE, "tiger's turn");
		put(State.TIGER_SELECT, "tiger's turn");
		put(State.TIGER_WON, "tiger won!");
	}

}
