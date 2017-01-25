package model;

import model.interfaces.BaghChalI;
import model.interfaces.Player;

/**
 * Implementation of {@link model.interfaces.BaghChalI}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class BaghChal implements BaghChalI {

	/**
	 * Creates a new instance of {@link BaghChal}.
	 */
	public BaghChal() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean set(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean select(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean move(int targetRow, int targetColumn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player[][] getBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getCurrentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGoatsEaten() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGoatsLeftToSet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

}
