package resources.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the main class of PIXEList where all the initialization of the
 * program's graphical user interface and the backend of the program.
 * 
 * @author Benjamin
 *
 */

// @@author A0124933H Benjamin
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	// ================================================================
	// PROGRAM FIELDS
	// ================================================================
	private static final double PROGRAM_HEIGHT = 660.0;
	private static final double PROGRAM_WIDTH = 880.0;

	private static final String PROGRAM_TITLE = "PIXEList";
	private static final String IMAGE_ICON = "resources/imgs/icon.png";

	private static Logger logger;

	// ================================================================
	// LOGGER CONSTANTS
	// ================================================================
	private static final String MAIN_APP = "MainApp";
	private static final String LOGGER_MAIN = "Graphical user interface initiated successfully.";
	private static final String LOGGER_STAGE = "Stage set up successfully.";

	// ================================================================
	// PROGRAM INITIALIZATION METHODS
	// ================================================================
	/**
	 * Launch PIXEList Program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger = Logger.getLogger(MAIN_APP);
		initRoot();
		initTaskView();
		initInputView();
		initPrimaryStage(primaryStage);
		logger.log(Level.INFO, LOGGER_MAIN);
	}

	/**
	 * Initialising GUI's Root layout
	 */
	private void initRoot() {
		rootLayout = new RootController();
	}

	/**
	 * Setting up stage for GUI.
	 * 
	 * @param primaryStage
	 *            is the stage that the GUI took
	 */
	private void initPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.getIcons().add(new Image(IMAGE_ICON));
		this.primaryStage.setMinHeight(PROGRAM_HEIGHT);
		this.primaryStage.setMinWidth(PROGRAM_WIDTH);
		this.primaryStage.setTitle(PROGRAM_TITLE);
		assert rootLayout != null;
		this.primaryStage.setScene(new Scene(rootLayout));
		this.primaryStage.show();
		logger.log(Level.INFO, LOGGER_STAGE);
	}

	/**
	 * Initialising the taskView and update the view should there be any tasks
	 * inside.
	 */
	private void initTaskView() {
		TaskDisplayController.getInstance();
	}

	/**
	 * Initialising the input view which consists of the input bar and feedback
	 * label.
	 */
	private void initInputView() {
		InputViewController.getInstance();
	}
}