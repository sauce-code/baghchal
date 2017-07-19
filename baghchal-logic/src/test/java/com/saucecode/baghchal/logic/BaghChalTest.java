package com.saucecode.baghchal.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.saucecode.baghchal.logic.interfaces.Player;
import com.saucecode.baghchal.logic.interfaces.BaghChalI.State;

/**
 * Testclass for {@link BaghChal}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class BaghChalTest {

	/**
	 * Test method for {@link com.saucecode.baghchal.logic.BaghChal#BaghChal()}.
	 */
	@Test
	public void testBaghChal() {
		BaghChal game = new BaghChal();

		assertNotNull(game);
		assertEquals(State.GOAT_SET, game.getState());
		assertEquals(0, game.getGoatsEaten());
		assertEquals(20, game.getGoatsLeftToSet());
		assertEquals(false, game.isAnyUndoLeft());
		assertEquals(false, game.isAnyRedoLeft());

		assertEquals(Player.TIGER, game.getBoard()[0][0]);
		assertEquals(Player.NONE, game.getBoard()[1][0]);
		assertEquals(Player.NONE, game.getBoard()[2][0]);
		assertEquals(Player.NONE, game.getBoard()[3][0]);
		assertEquals(Player.TIGER, game.getBoard()[4][0]);

		assertEquals(Player.NONE, game.getBoard()[0][1]);
		assertEquals(Player.NONE, game.getBoard()[1][1]);
		assertEquals(Player.NONE, game.getBoard()[2][1]);
		assertEquals(Player.NONE, game.getBoard()[3][1]);
		assertEquals(Player.NONE, game.getBoard()[4][1]);

		assertEquals(Player.NONE, game.getBoard()[0][2]);
		assertEquals(Player.NONE, game.getBoard()[1][2]);
		assertEquals(Player.NONE, game.getBoard()[2][2]);
		assertEquals(Player.NONE, game.getBoard()[3][2]);
		assertEquals(Player.NONE, game.getBoard()[4][2]);

		assertEquals(Player.NONE, game.getBoard()[0][3]);
		assertEquals(Player.NONE, game.getBoard()[1][3]);
		assertEquals(Player.NONE, game.getBoard()[2][3]);
		assertEquals(Player.NONE, game.getBoard()[3][3]);
		assertEquals(Player.NONE, game.getBoard()[4][3]);

		assertEquals(Player.TIGER, game.getBoard()[0][4]);
		assertEquals(Player.NONE, game.getBoard()[1][4]);
		assertEquals(Player.NONE, game.getBoard()[2][4]);
		assertEquals(Player.NONE, game.getBoard()[3][4]);
		assertEquals(Player.TIGER, game.getBoard()[4][4]);
	}

	/**
	 * Test method for
	 * {@link com.saucecode.baghchal.logic.BaghChal#action(int, int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAction1() {
		BaghChal game = new BaghChal();
		game.action(-1, 2);
	}

	/**
	 * Test method for
	 * {@link com.saucecode.baghchal.logic.BaghChal#action(int, int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAction2() {
		BaghChal game = new BaghChal();
		game.action(2, 20);
	}

	/**
	 * Test method for
	 * {@link com.saucecode.baghchal.logic.BaghChal#action(int, int)}.
	 */
	@Test
	public void testAction3() {
		BaghChal game = new BaghChal();
		game.action(2, 2);

		assertNotNull(game);
		assertEquals(State.TIGER_SELECT, game.getState());
		assertEquals(0, game.getGoatsEaten());
		assertEquals(19, game.getGoatsLeftToSet());
		assertEquals(true, game.isAnyUndoLeft());
		assertEquals(false, game.isAnyRedoLeft());

		assertEquals(Player.TIGER, game.getBoard()[0][0]);
		assertEquals(Player.NONE, game.getBoard()[1][0]);
		assertEquals(Player.NONE, game.getBoard()[2][0]);
		assertEquals(Player.NONE, game.getBoard()[3][0]);
		assertEquals(Player.TIGER, game.getBoard()[4][0]);

		assertEquals(Player.NONE, game.getBoard()[0][1]);
		assertEquals(Player.NONE, game.getBoard()[1][1]);
		assertEquals(Player.NONE, game.getBoard()[2][1]);
		assertEquals(Player.NONE, game.getBoard()[3][1]);
		assertEquals(Player.NONE, game.getBoard()[4][1]);

		assertEquals(Player.NONE, game.getBoard()[0][2]);
		assertEquals(Player.NONE, game.getBoard()[1][2]);
		assertEquals(Player.GOAT, game.getBoard()[2][2]);
		assertEquals(Player.NONE, game.getBoard()[3][2]);
		assertEquals(Player.NONE, game.getBoard()[4][2]);

		assertEquals(Player.NONE, game.getBoard()[0][3]);
		assertEquals(Player.NONE, game.getBoard()[1][3]);
		assertEquals(Player.NONE, game.getBoard()[2][3]);
		assertEquals(Player.NONE, game.getBoard()[3][3]);
		assertEquals(Player.NONE, game.getBoard()[4][3]);

		assertEquals(Player.TIGER, game.getBoard()[0][4]);
		assertEquals(Player.NONE, game.getBoard()[1][4]);
		assertEquals(Player.NONE, game.getBoard()[2][4]);
		assertEquals(Player.NONE, game.getBoard()[3][4]);
		assertEquals(Player.TIGER, game.getBoard()[4][4]);
	}

	/**
	 * Test method for {@link com.saucecode.baghchal.logic.BaghChal#undo()}.
	 */
	@Test
	public void testUndo() {
		BaghChal game = new BaghChal();
		BaghChal clone = game.clone();
		game.action(2, 3);
		game.undo();
		assertEquals(clone, game);
	}

	/**
	 * Test method for {@link com.saucecode.baghchal.logic.BaghChal#redo()}.
	 */
	@Test
	public void testRedo() {
		BaghChal game = new BaghChal();
		game.action(2, 3);
		BaghChal clone = game.clone();
		game.undo();
		game.redo();
		assertEquals(clone, game);
	}

	/**
	 * Test method for {@link com.saucecode.baghchal.logic.BaghChal#clone()}.
	 */
	@Test
	public void testClone1() {
		BaghChal game = new BaghChal();
		BaghChal clone = game.clone();
		assertEquals(game, clone);
	}

	/**
	 * Test method for {@link com.saucecode.baghchal.logic.BaghChal#clone()}.
	 */
	@Test
	public void testClone2() {
		BaghChal game = new BaghChal();
		BaghChal clone = game.clone();
		game.action(2, 2);
		assertNotEquals(game, clone);
	}

	/**
	 * Test method for {@link com.saucecode.baghchal.logic.BaghChal#clone()}.
	 */
	@Test
	public void testClone3() {
		BaghChal game = new BaghChal();
		BaghChal clone = game.clone();
		game.action(2, 2);
		clone.action(2, 2);
		assertEquals(game, clone);
	}

}
