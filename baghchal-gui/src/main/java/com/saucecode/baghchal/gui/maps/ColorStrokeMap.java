package com.saucecode.baghchal.gui.maps;

import java.util.HashMap;

import com.saucecode.baghchal.logic.interfaces.Player;

import javafx.scene.paint.Color;

/**
 * Stores the colors used for strokes of the pieces.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class ColorStrokeMap extends HashMap<Player, Color> {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -7418216095005484833L;

	/**
	 * Creates a new Color Stroke Map.
	 */
	public ColorStrokeMap() {
		super();
		put(Player.NONE, Color.TRANSPARENT);
		put(Player.GOAT, Color.BLACK);
		put(Player.TIGER, Color.BLACK);
	}
	
}
