package gui.maps;

import java.util.HashMap;

import javafx.scene.paint.Color;
import model.interfaces.Player;

/**
 * Stores the colors used for filling pieces.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class ColorFillMap extends HashMap<Player, Color> {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 8483911894826353187L;

	/**
	 * Creates a new Color Fill Map.
	 */
	public ColorFillMap() {
		super();
		put(Player.NONE, Color.TRANSPARENT);
		put(Player.GOAT, Color.WHITE);
		put(Player.TIGER, Color.GRAY);
	}

}
