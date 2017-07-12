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
import javafx.stage.StageStyle;
import model.BaghChal;
import model.interfaces.BaghChalI;
import model.interfaces.BaghChalI.State;
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
	 * Stores the corresponding Strings for each {@link State}.
	 */
	private HashMap<State, String> stateStrings;

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
	 * Displays the text for current number of {@link BaghChal#getGoatsLeftToSet() on {@link #game}.
	 */
	private Text goatsLeftToSet;

	/**
	 * Displays the text for current number of {@link BaghChal#getGoatsEaten()} on {@link #game}.
	 */
	private Text goatsEaten;

	/**
	 * Displays the text for current {@link BaghChal#getState()() on {@link #game}.
	 */
	private Text state;

	/**
	 * Initializes the maps.
	 */
	private void initMaps() {
		colorsFill = new HashMap<Player, Color>();
		colorsFill.put(Player.NONE, Color.TRANSPARENT);
		colorsFill.put(Player.GOAT, Color.WHITE);
		colorsFill.put(Player.TIGER, Color.GRAY);

		colorsStroke = new HashMap<Player, Color>();
		colorsStroke.put(Player.NONE, Color.TRANSPARENT);
		colorsStroke.put(Player.GOAT, Color.BLACK);
		colorsStroke.put(Player.TIGER, Color.BLACK);

		stateStrings = new HashMap<State, String>();
		stateStrings.put(State.GOAT_MOVE, "goat's turn");
		stateStrings.put(State.GOAT_SELECT, "goat's turn");
		stateStrings.put(State.GOAT_SET, "goat's turn");
		stateStrings.put(State.GOAT_WON, "goats won!");
		stateStrings.put(State.TIGER_MOVE, "tiger's turn");
		stateStrings.put(State.TIGER_SELECT, "tiger's turn");
		stateStrings.put(State.TIGER_WON, "tigers won!");
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
		tiles = new Circle[BaghChalI.DIM][BaghChalI.DIM];

		for (int x = 0; x < BaghChalI.DIM; x++) {
			for (int y = 0; y < BaghChalI.DIM; y++) {
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
	private Node initMenuBar() {

		MenuItem rules = new MenuItem("_Rules");
		rules.setOnAction(e -> {
			getHostServices().showDocument("https://en.wikipedia.org/wiki/Bagh-Chal#Rules");
		});

		MenuItem about = new MenuItem("A_bout");

		about.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			ImageView image = new ImageView(icon);
			image.setFitWidth(100.0);
			image.setFitHeight(100.0);
			alert.setGraphic(image);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("About");
			alert.setHeaderText(MetaInfo.TITLE + " " + MetaInfo.VERSION);
			alert.setContentText("Written by " + MetaInfo.AUTHOR + '\n' + "Logo by "
					+ MetaInfo.LOGO_ARTIST + '\n' + "Email: " + MetaInfo.EMAIL + '\n'
					+ "Repository: " + MetaInfo.REPOSITORY);
			alert.showAndWait();
		});

		Menu menuHelp = new Menu("_Help", null, rules, new SeparatorMenuItem(), about);

		// =========================================================================================
		// =========================================================================================
		// =========================================================================================

		undo = new MenuItem("_Undo");
		undo.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (game.undo()) {
					refresh();
				}
			}
		});

		redo = new MenuItem("_Redo");
		redo.setAccelerator(KeyCombination.keyCombination("Ctrl+Y"));
		redo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (game.redo()) {
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

	private VBox initScoreboard() {
		// scoreBoard.setPrefWidth(200);

		goatsLeftToSet = new Text("Goats left to set: " + game.getGoatsLeftToSet());
		goatsLeftToSet.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0));
		goatsLeftToSet.setTextAlignment(TextAlignment.CENTER);
		BorderPane box1 = new BorderPane(goatsLeftToSet);
		// box1.setPrefSize(400.0, 50.0);
		// box1.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
//		box1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		goatsEaten = new Text("Goats eaten: " + game.getGoatsEaten());
		goatsEaten.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20.0));
		BorderPane box2 = new BorderPane(goatsEaten);
		// box2.setPrefSize(400.0, 50.0);
		// box2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
//		box2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		HBox box0 = new HBox(box1, box2);
		box0.setPadding(new Insets(6.0, 0.0, 0.0, 0.0));
		HBox.setHgrow(box1, Priority.ALWAYS);
		HBox.setHgrow(box2, Priority.ALWAYS);
//		box0.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		state = new Text(stateStrings.get(game.getState()));
		state.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 25.0));
		BorderPane box3 = new BorderPane(state);
		box3.setPadding(new Insets(2.0, 0.0, 0.0, 0.0));
		// box3.setPrefSize(400.0, 50.0);
		// box3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
//		box3.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		VBox scoreboard = new VBox(box0, box3);
//		scoreboard.setBorder(new Border(new BorderStroke(null, null, Color.BLACK, null, null, null, BorderStrokeStyle.SOLID, null, CornerRadii.EMPTY, new BorderWidths(4.0), null)));
		return new VBox(scoreboard);
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
		goatsLeftToSet.setText("gaots left to set: " + game.getGoatsLeftToSet());
		goatsEaten.setText("Goats eaten: " + game.getGoatsEaten());
		state.setText(stateStrings.get(game.getState()));

		// set status bar text
		statusBar.setText("goats eaten: " + game.getGoatsEaten() + "    goats left to set: "
				+ game.getGoatsLeftToSet() + "    State: " + game.getState().toString());

		// refresh undo and redo
		undo.setDisable(!game.isAnyUndoLeft());
		redo.setDisable(!game.isAnyRedoLeft());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initMaps();

		VBox vBox = new VBox(initScoreboard(), initBoard());
		statusBar = new StatusBar();

		BorderPane border = new BorderPane(vBox);
		border.setTop(initMenuBar());

//		border.setBottom(statusBar);

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
