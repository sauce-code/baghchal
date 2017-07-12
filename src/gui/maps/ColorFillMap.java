package gui.maps;

import java.util.HashMap;

import javafx.scene.paint.Color;
import model.interfaces.Player;

public class ColorFillMap extends HashMap<Player, Color> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8483911894826353187L;

	public ColorFillMap() {
		super();
		put(Player.NONE, Color.TRANSPARENT);
		put(Player.GOAT, Color.WHITE);
		put(Player.TIGER, Color.GRAY);
	}

}
