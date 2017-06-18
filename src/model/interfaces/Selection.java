package model.interfaces;

/**
 * Represents a selection of a certain tile.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Selection {

	/**
	 * Row of the selection.
	 */
	public final int row;

	/**
	 * Column of the selection.
	 */
	public final int column;

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

	@Override
	public String toString() {
		return "[row=" + row + ", column=" + column + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Selection other = (Selection) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
