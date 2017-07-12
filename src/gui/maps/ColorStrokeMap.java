package gui.maps;

import java.util.HashMap;

import javafx.scene.paint.Color;
import model.interfaces.Player;

public class ColorStrokeMap extends HashMap<Player, Color> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7418216095005484833L;

	public ColorStrokeMap() {
		super();
		put(Player.NONE, Color.TRANSPARENT);
		put(Player.GOAT, Color.BLACK);
		put(Player.TIGER, Color.BLACK);
	}
	
}
