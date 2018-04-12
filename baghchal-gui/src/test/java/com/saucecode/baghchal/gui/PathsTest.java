package com.saucecode.baghchal.gui;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * Tests class {@link Paths}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class PathsTest {

	/**
	 * Relative path to resources.
	 */
	private static String PATH_DIR = "src/main/resources/";

	/**
	 * Tests if a file exists, is no directory and is readable.
	 * 
	 * @param path
	 *            path to file
	 */
	private void testFile(String path) {
		File file = new File(PATH_DIR + path);
		assertTrue(file.exists());
		assertFalse(file.isDirectory());
		assertTrue(file.canRead());
	}

	/**
	 * Tests {@link Paths#LOGO}.
	 */
	@Test
	public void testLogo() {
		testFile(Paths.LOGO);
	}

	/**
	 * Tests {@link Paths#ICON_RULES}.
	 */
	@Test
	public void testIconRules() {
		testFile(Paths.ICON_RULES);
	}

	/**
	 * Tests {@link Paths#ICON_REDO}.
	 */
	@Test
	public void testIconRedo() {
		testFile(Paths.ICON_REDO);
	}

	/**
	 * Tests {@link Paths#ICON_UNDO}.
	 */
	@Test
	public void testIconUndo() {
		testFile(Paths.ICON_UNDO);
	}
	
	/**
	 * Tests {@link Paths#CSS}.
	 */
	@Test
	public void testCSS() {
		testFile(Paths.CSS);
	}

}
