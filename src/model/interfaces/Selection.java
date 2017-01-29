package model.interfaces;

/**
 * Represents a selection of a certain tile.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Selection {

	/**
	 * Creates a new selection.
	 * 
	 * @param row
	 *            row
	 * @param column
	 *            column
	 */
	public Selection(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Row of the selection.
	 */
	public final int row;

	/**
	 * Column of the selection.
	 */
	public final int column;

	@Override
	public String toString() {
		return "[row=" + row + ", column=" + column + "]";
	}

}
