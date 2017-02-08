package gui;

import java.util.HashMap;

import org.controlsfx.control.StatusBar;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BaghChal;
import model.interfaces.Player;
import model.interfaces.Selection;

/**
 * A simple GUI, running an instance of {@link BaghChal}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class MainWindow extends Application {

	/**
	 * The path for the icon.
	 */
	private static final String PATH_ICON = "icon.png";

	/**
	 * The icon used for this program.
	 */
	private Image icon = new Image(PATH_ICON);

	/**
	 * The statusbar at the bottom of this scene, showing useful information.
	 */
	private StatusBar statusBar;

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
	private HashMap<Player, Color> colorsFill;

	/**
	 * Stores the colors used for strokes of the pieces.
	 */
	private HashMap<Player, Color> colorsStroke;

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
	 * Initializes the color maps.
	 */
	private void initColorMaps() {
		colorsFill = new HashMap<Player, Color>();
		colorsFill.put(Player.NONE, Color.TRANSPARENT);
		colorsFill.put(Player.GOAT, Color.WHITE);
		colorsFill.put(Player.TIGER, Color.GRAY);

		colorsStroke = new HashMap<Player, Color>();
		colorsStroke.put(Player.NONE, Color.TRANSPARENT);
		colorsStroke.put(Player.GOAT, Color.BLACK);
		colorsStroke.put(Player.TIGER, Color.BLACK);
	}

	/**
	 * Initializes the board and returns it.
	 * 
	 * @return the initialized board
	 */
	private Node initBoard() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(13.0, 0.0, 0.0, 13.0));
		grid.setAlignment(Pos.CENTER);
		grid.setGridLinesVisible(false);
		tiles = new Circle[BaghChal.DIM][BaghChal.DIM];

		for (int x = 0; x < BaghChal.DIM; x++) {
			for (int y = 0; y < BaghChal.DIM; y++) {
				final int xPos = x;
				final int yPos = y;
				Circle c = new Circle(28.0);
				c.setStrokeWidth(3.0);
				c.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (game.action(xPos, yPos)) {
							refresh();
						}
					}
				});
				grid.add(c, x, y);
				tiles[x][y] = c;
				GridPane.setMargin(c, new Insets(16.0));
			}
		}

		Group group = new Group();

		for (int x = 0; x < BaghChal.DIM; x++) {
			Line line = new Line();
			line.setStrokeWidth(4.0);
			line.startXProperty().bind(tiles[x][0].layoutXProperty());
			line.startYProperty().bind(tiles[x][0].layoutYProperty());
			line.endXProperty().bind(tiles[x][4].layoutXProperty());
			line.endYProperty().bind(tiles[x][4].layoutYProperty());
			group.getChildren().add(line);
		}

		for (int y = 0; y < BaghChal.DIM; y++) {
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
	private Node initMenuBar() {
		
		MenuItem rules = new MenuItem("_Rules");
		rules.setOnAction(e -> {
			getHostServices().showDocument("https://en.wikipedia.org/wiki/Bagh-Chal#Rules");
		});
		
		MenuItem about = new MenuItem("A_bout");

		about.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setGraphic(new ImageView(icon));
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("About");
			alert.setHeaderText(MetaInfo.TITLE + " " + MetaInfo.VERSION + " by " + MetaInfo.AUTHOR);
			alert.setContentText(
					"Email: " + MetaInfo.EMAIL + '\n' + "Repository: " + MetaInfo.REPOSITORY);
			alert.showAndWait();
		});

		Menu menuHelp = new Menu("_Help", null, rules, new SeparatorMenuItem(), about);

		// =========================================================================================
		// =========================================================================================
		// =========================================================================================

        undo = new MenuItem("_Undo");
        undo.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
        undo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (game.undo())
                {
                    refresh();
                }
            }
        });

        redo = new MenuItem("_Redo");
        redo.setAccelerator(KeyCombination.keyCombination("Ctrl+Y"));
        redo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (game.redo())
                {
                    refresh();
                }
            }
        });

        Menu menuEdit = new Menu("_Edit", null, undo, redo);

        // =========================================================================================
        // =========================================================================================
        // =========================================================================================

		MenuItem restart = new MenuItem("_New Game");
		restart.setAccelerator(KeyCombination.keyCombination("F2"));
		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				game = new BaghChal();
				refresh();
			}
		});

		MenuItem exit = new MenuItem("E_xit");
		exit.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});

		Menu menuFile = new Menu("_File", null, restart, new SeparatorMenuItem(), exit);

		// =========================================================================================
		// =========================================================================================
		// =========================================================================================

		MenuBar ret = new MenuBar(menuFile, menuEdit, menuHelp);
		ret.setUseSystemMenuBar(true);
		ret.useSystemMenuBarProperty().set(true);
		return ret;
	}

	/**
	 * Refreshes the whole GUI.
	 */
	protected void refresh() {

		Player[][] board = game.getBoard();

		// paint all tiles
		for (int x = 0; x < BaghChal.DIM; x++) {
			for (int y = 0; y < BaghChal.DIM; y++) {
				tiles[x][y].setFill(colorsFill.get(board[x][y]));
				tiles[x][y].setStroke(colorsStroke.get(board[x][y]));
			}
		}

		// color selected
		Selection selected = game.getSelection();
		if (selected != null) {
			tiles[selected.row][selected.column].setStroke(colorSelected);
		}

		// set status bar text
		statusBar.setText("goats eaten: " + game.getGoatsEaten() + " | goats left to set: "
				+ game.getGoatsLeftToSet() + " | State: " + game.getState().toString());
		
		// refresh undo and redo
		// TODO
		undo.setDisable(true);
		redo.setDisable(true);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initColorMaps();

		VBox vBox = new VBox(initBoard());
		statusBar = new StatusBar();

		BorderPane border = new BorderPane(vBox);
		border.setTop(initMenuBar());
		border.setBottom(statusBar);

		Scene scene = new Scene(border);
		primaryStage.setTitle(MetaInfo.TITLE);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(icon);
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
