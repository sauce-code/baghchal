package com.saucecode.baghchal.gui;

import com.saucecode.baghchal.gui.maps.ColorFillMap;
import com.saucecode.baghchal.gui.maps.ColorStrokeMap;
import com.saucecode.baghchal.gui.maps.StateStringMap;
import com.saucecode.baghchal.logic.BaghChal;
import com.saucecode.baghchal.logic.interfaces.BaghChalI;
import com.saucecode.baghchal.logic.interfaces.BaghChalI.Selection;
import com.saucecode.baghchal.logic.interfaces.Player;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * A simple GUI, running an instance of {@link BaghChal}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class MainWindow extends Application {

	/**
	 * The icon used for this program.
	 */
	private final Image logo = new Image(Paths.LOGO);

	/**
	 * The rules icon.
	 */
	private final Image iconRules = new Image(Paths.ICON_RULES);

	/**
	 * The undo icon.
	 */
	private final Image iconUndo = new Image(Paths.ICON_UNDO);

	/**
	 * The redo icon.
	 */
	private final Image iconRedo = new Image(Paths.ICON_REDO);

	/**
	 * The instance of the Bagh Chal game.
	 */
	private BaghChal game = new BaghChal();

	/**
	 * all pieces on the board.
	 */
	private Circle[][] tiles;

	/**
	 * Stores the colors used for filling pieces.
	 */
	private final ColorFillMap colorsFill = new ColorFillMap();

	/**
	 * Stores the colors used for strokes of the pieces.
	 */
	private final ColorStrokeMap colorsStroke = new ColorStrokeMap();

	/**
	 * Stores the corresponding Strings for each {@link State}.
	 */
	private final StateStringMap stateStrings = new StateStringMap();

	/**
	 * The color being used for strokes of selected pieces.
	 */
	private Color colorSelected = Color.CYAN;

	/**
	 * Menuitem for calling {@link BaghChal#undo()} on {@link #game}.
	 */
	private MenuItem undo;

	/**
	 * Menuitem for calling {@link BaghChal#redo()} on {@link #game}.
	 */
	private MenuItem redo;

	/**
	 * Displays the text for current number of
	 * {@link BaghChal#getGoatsLeftToSet()} on {@link #game}.
	 */
	private Text goatsLeftToSet;

	/**
	 * Displays the text for current number of {@link BaghChal#getGoatsEaten()}
	 * on {@link #game}.
	 */
	private Text goatsEaten;

	/**
	 * Displays the text for current {@link BaghChal#getState()} on
	 * {@link #game}.
	 */
	private Text state;

	/**
	 * Initializes the board and returns it.
	 * 
	 * @return the initialized board
	 */
	private Group initBoard() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(4.0, 0.0, 0.0, 12.0));
		grid.setAlignment(Pos.CENTER);
		grid.setGridLinesVisible(false);
		tiles = new Circle[BaghChalI.DIM][BaghChalI.DIM];

		for (int x = 0; x < BaghChalI.DIM; x++) {
			for (int y = 0; y < BaghChalI.DIM; y++) {
				final int xPos = x;
				final int yPos = y;
				Circle c = new Circle(28.0);
				c.setStrokeWidth(3.0);
				c.setOnMouseClicked(e -> {
					if (game.action(xPos, yPos)) {
						refresh();
					}
				});
				grid.add(c, x, y);
				tiles[x][y] = c;
				GridPane.setMargin(c, new Insets(16.0));
			}
		}

		Group group = new Group();

		for (int x = 0; x < BaghChalI.DIM; x++) {
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[x][0].layoutXProperty());
			line.startYProperty().bind(tiles[x][0].layoutYProperty());
			line.endXProperty().bind(tiles[x][4].layoutXProperty());
			line.endYProperty().bind(tiles[x][4].layoutYProperty());
			group.getChildren().add(line);
		}

		for (int y = 0; y < BaghChalI.DIM; y++) {
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[0][y].layoutXProperty());
			line.startYProperty().bind(tiles[0][y].layoutYProperty());
			line.endXProperty().bind(tiles[4][y].layoutXProperty());
			line.endYProperty().bind(tiles[4][y].layoutYProperty());
			group.getChildren().add(line);
		}

		{
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[0][2].layoutXProperty());
			line.startYProperty().bind(tiles[0][2].layoutYProperty());
			line.endXProperty().bind(tiles[2][4].layoutXProperty());
			line.endYProperty().bind(tiles[2][4].layoutYProperty());
			group.getChildren().add(line);
		}

		{
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[0][0].layoutXProperty());
			line.startYProperty().bind(tiles[0][0].layoutYProperty());
			line.endXProperty().bind(tiles[4][4].layoutXProperty());
			line.endYProperty().bind(tiles[4][4].layoutYProperty());
			group.getChildren().add(line);
		}

		{
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[2][0].layoutXProperty());
			line.startYProperty().bind(tiles[2][0].layoutYProperty());
			line.endXProperty().bind(tiles[4][2].layoutXProperty());
			line.endYProperty().bind(tiles[4][2].layoutYProperty());
			group.getChildren().add(line);
		}

		{
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[2][0].layoutXProperty());
			line.startYProperty().bind(tiles[2][0].layoutYProperty());
			line.endXProperty().bind(tiles[0][2].layoutXProperty());
			line.endYProperty().bind(tiles[0][2].layoutYProperty());
			group.getChildren().add(line);
		}

		{
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[4][0].layoutXProperty());
			line.startYProperty().bind(tiles[4][0].layoutYProperty());
			line.endXProperty().bind(tiles[0][4].layoutXProperty());
			line.endYProperty().bind(tiles[0][4].layoutYProperty());
			group.getChildren().add(line);
		}

		{
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[4][2].layoutXProperty());
			line.startYProperty().bind(tiles[4][2].layoutYProperty());
			line.endXProperty().bind(tiles[2][4].layoutXProperty());
			line.endYProperty().bind(tiles[2][4].layoutYProperty());
			group.getChildren().add(line);
		}

		group.getChildren().add(grid);

		return group;
	}

	/**
	 * Initializes the menu bar and returns it.
	 * 
	 * @return the initialized menubar
	 */
	private MenuBar initMenuBar() {

		// =========================================================================================
		// ==== HELP MENU
		// =========================================================================================

		MenuItem rules = new MenuItem("_Rules", new ImageView(iconRules));
		rules.setOnAction(e -> getHostServices().showDocument("https://en.wikipedia.org/wiki/Bagh-Chal#Rules"));

		MenuItem about = new MenuItem("A_bout");
		about.setOnAction(e -> new AboutAlert(logo).showAndWait());

		Menu menuHelp = new Menu("_Help", null, rules, new SeparatorMenuItem(), about);

		// =========================================================================================
		// ====== EDIT MENU
		// =========================================================================================

		undo = new MenuItem("_Undo", new ImageView(iconUndo));
		undo.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
		undo.setOnAction(e -> {
			if (game.undo()) {
				refresh();
			}
		});

		redo = new MenuItem("_Redo", new ImageView(iconRedo));
		redo.setAccelerator(KeyCombination.keyCombination("Ctrl+Y"));
		redo.setOnAction(e -> {
			if (game.redo()) {
				refresh();
			}
		});

		Menu menuEdit = new Menu("_Edit", null, undo, redo);

		// =========================================================================================
		// ======= FILE MENU
		// =========================================================================================

		MenuItem restart = new MenuItem("_New Game");
		restart.setAccelerator(KeyCombination.keyCombination("F2"));
		restart.setOnAction(e -> {
			game = new BaghChal();
			refresh();
		});

		MenuItem exit = new MenuItem("E_xit");
		exit.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
		exit.setOnAction(e -> Platform.exit());

		Menu menuFile = new Menu("_File", null, restart, new SeparatorMenuItem(), exit);

		// =========================================================================================
		// ======= BUILDING MENUBAR
		// =========================================================================================

		MenuBar ret = new MenuBar(menuFile, menuEdit, menuHelp);
		ret.setUseSystemMenuBar(true);
		ret.useSystemMenuBarProperty().set(true);
		return ret;
	}

	/**
	 * Initializes the scoreboard and returns it.
	 * 
	 * @return initialzied scoreboard
	 */
	private VBox initScoreboard() {

		goatsLeftToSet = new Text();
		goatsLeftToSet.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0));
		goatsLeftToSet.setTextAlignment(TextAlignment.CENTER);
		BorderPane box1 = new BorderPane(goatsLeftToSet);

		goatsEaten = new Text();
		goatsEaten.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0));
		BorderPane box2 = new BorderPane(goatsEaten);

		HBox box0 = new HBox(box1, box2);
		box0.setPadding(new Insets(6.0, 0.0, 0.0, 0.0));
		HBox.setHgrow(box1, Priority.ALWAYS);
		HBox.setHgrow(box2, Priority.ALWAYS);

		state = new Text(stateStrings.get(game.getState()));
		state.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0));
		BorderPane box3 = new BorderPane(state);
		box3.setPadding(new Insets(2.0, 0.0, 0.0, 0.0));

		return new VBox(new VBox(box0, box3));
	}

	/**
	 * Refreshes the whole GUI.
	 */
	protected void refresh() {

		Player[][] board = game.getBoard();

		// paint all tiles
		for (int x = 0; x < BaghChalI.DIM; x++) {
			for (int y = 0; y < BaghChalI.DIM; y++) {
				tiles[x][y].setFill(colorsFill.get(board[x][y]));
				tiles[x][y].setStroke(colorsStroke.get(board[x][y]));
			}
		}

		// color selected
		Selection selected = game.getSelection();
		if (selected != null) {
			tiles[selected.row][selected.column].setStroke(colorSelected);
		}

		// set texts
		goatsLeftToSet.setText("goats left to set: " + game.getGoatsLeftToSet());
		goatsEaten.setText("goats eaten: " + game.getGoatsEaten());
		state.setText(stateStrings.get(game.getState()));

		// refresh undo and redo
		undo.setDisable(!game.isAnyUndoLeft());
		redo.setDisable(!game.isAnyRedoLeft());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox vBox = new VBox(initScoreboard(), initBoard());

		BorderPane border = new BorderPane(vBox);
		border.setTop(initMenuBar());

		Scene scene = new Scene(border);
		primaryStage.setTitle(MetaInfo.TITLE);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(logo);

		refresh();

		primaryStage.show();
	}

	/**
	 * Runs the application.
	 *
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
