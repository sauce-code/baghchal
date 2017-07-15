package com.saucecode.baghchal.tui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.saucecode.baghchal.logic.BaghChal;
import com.saucecode.baghchal.logic.interfaces.BaghChalI;
import com.saucecode.baghchal.logic.interfaces.BaghChalI.State;

/**
 * Simple Main class.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Main {

	/**
	 * The console.
	 */
	private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads an int from the console.
	 * 
	 * @return
	 * 		<ul>
	 *         <li>int from the console</li>
	 *         <li>{@code -1}, if <b>any</b> error occurs</li>
	 *         </ul>
	 */
	private static int readInt() {
		try {
			return Integer.parseInt(console.readLine());
		} catch (NumberFormatException e) {
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Reads a row from the console
	 * 
	 * @return row read from the console
	 */
	private static int readRow() {
		System.out.println("row:");
		int row = readInt();
		while (row < 0 || row >= BaghChalI.DIM) {
			System.out.println("invalid input");
			row = readInt();
		}
		return row;
	}

	/**
	 * Reads a column from the console
	 * 
	 * @return column read from the console
	 */
	private static int readColumn() {
		System.out.println("column:");
		int row = readInt();
		while (row < 0 || row >= BaghChalI.DIM) {
			System.out.println("invalid input");
			row = readInt();
		}
		return row;
	}

	/**
	 * Simple main method, runs an instance of {@link BaghChal}.
	 * 
	 * @param args
	 *            arguments, unused
	 */
	public static void main(String[] args) {
		BaghChalI game = new BaghChal();
		while ((game.getState() != State.GOAT_WON) && game.getState() != State.TIGER_WON) {
			System.out.println(game);
			int row = readRow();
			int column = readColumn();
			if (!game.action(row, column)) {
				System.out.println("invalid action");
			}
		}
		System.out.println(game);
	}

}
