package resources.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class initiates the root display of PIXEList's graphical user interface.
 * 
 * @author Benjamin
 *
 */
// @@author A0124933H Benjamin
public class RootController extends BorderPane {

	// ================================================================
	// FXML FIELDS
	// ================================================================
	@FXML
	private TextField input;

	// ================================================================
	// NON-FXML FIELDS
	// ================================================================
	private TaskDisplayController taskDisplayController;
	private static Logger logger;

	private static final String ROOT = "Root";

	private static final String LOGGER_INIT_UNSUCCESSFUL = "ROOT LAYOUT FAILED TO INITIALIZE";
	private static final String LOGGER_INIT_SUCCESSFUL = "Root layout initiated successfully";

	// ================================================================
	// CONSTRUCTORS
	// ================================================================
	public RootController() {
		logger = Logger.getLogger(ROOT);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Root.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			logger.log(Level.SEVERE, LOGGER_INIT_UNSUCCESSFUL, new RuntimeException(e));
		}
		initTaskDisplay();
		;
		logger.log(Level.INFO, LOGGER_INIT_SUCCESSFUL);
	}

	// ================================================================
	// INITIALIZATION METHODS
	// ================================================================
	private void initTaskDisplay() {
		this.taskDisplayController = TaskDisplayController.getInstance();
		this.setCenter(taskDisplayController);
	}
}