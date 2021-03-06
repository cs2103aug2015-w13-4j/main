package resources.view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import logic.Display;
import logic.Launch;
import resources.view.Task;
import utilities.TaskEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class performs all the necessary actions to update task information and
 * refreshes the views to keep the view up to date for the user. It also
 * controls the behaviour of the appearance of the help and result overlays.
 * 
 * @author Benjamin
 *
 */

// @@author A0124933H Benjamin
public class TaskDisplayController extends StackPane {

	// ================================================================
	// FXML FIELDS
	// ================================================================
	@FXML
	private ListView<HBox> generalView;

	@FXML
	private ListView<HBox> flagView;

	@FXML
	private ListView<HBox> floatingView;

	@FXML
	private ListView<HBox> resultContent;

	@FXML
	private ListView<HBox> helpContent;

	@FXML
	private BorderPane viewBorderPane;

	@FXML
	private Label flagTaskLabel;

	@FXML
	private Label generalTaskLabel;

	@FXML
	private Label floatingTaskLabel;

	@FXML
	private VBox helpView;

	@FXML
	private VBox resultView;

	@FXML
	private BorderPane borderPane;

	// ================================================================
	// CONSTANTS
	// ================================================================
	private static final String HELP_DESC_ADD = "Add a task?";
	private static final String HELP_DESC_EDIT = "Edit a task?";
	private static final String HELP_DESC_DELETE = "Delete a task?";
	private static final String HELP_DESC_UNDO = "To undo";
	private static final String HELP_DESC_REDO = "To redo";
	private static final String HELP_DESC_SEARCH = "To search a task ";
	private static final String HELP_DESC_FINISH = "Mark a task as finished";
	private static final String HELP_DESC_FLAG = "Flag a task as important";
	private static final String HELP_DESC_UNFLAG = "Unflag a task";
	private static final String HELP_DESC_VIEW_COMPLETED = "To view completed tasks";
	private static final String HELP_DESC_CHANGE_DIR = "To change the file directory";
	private static final String HELP_DESC_FIELD = "Fields available";
	private static final String HELP_DESC_EXIT = "To Exit PIXEList";

	private static final String HELP_COMMAND_ADD = "add \"Task Name\" <StartDate> <EndDate> <Flag?>";
	private static final String HELP_COMMAND_EDIT = "edit <id> <field> \"Name Changes here.\"<Date>";
	private static final String HELP_COMMAND_DELETE = "delete <id>";
	private static final String HELP_COMMAND_UNDO = "undo";
	private static final String HELP_COMMAND_REDO = "redo";
	private static final String HELP_COMMAND_SEARCH = "search \"Search keyword\"";
	private static final String HELP_COMMAND_FINISH = "finish <id>";
	private static final String HELP_COMMAND_FLAG = "flag <id>";
	private static final String HELP_COMMAND_UNFLAG = "unflag <id>";
	private static final String HELP_COMMAND_VIEW_COMPLETED = "view completed";
	private static final String HELP_COMMAND_CHANGE_DIR = "cd \"foldername/filename\"";
	private static final String HELP_COMMAND_EXIT = "exit";
	private static final String HELP_COMMAND_FIELD = "startDate , endDate , name";

	private static final String LABEL_TASK_DISPLAYED = " tasks displayed";
	private static final String LABEL_GENERAL = "General Tasks - ";
	private static final String LABEL_FLAGGED = "Flagged Tasks - ";
	private static final String LABEL_FLOATING = "Floating Tasks - ";

	private static final String TASK_FLAG = "FLAG";

	private static final String FILE_LOC = "TaskDisplay.fxml";
	private static final String TASK_DISPLAY_CONTROLLER = "TaskDisplayController";

	private static final String LOGGER_INIT_UNSUCCESSFUL = "TASK DISPLAY FAILED TO INITIALIZE";
	private static final String LOGGER_INIT_SUCCESSFUL = "Task Display initiated successfully";

	private static final int TASK_NUMBERING_OFFSET = 1;

	private static final double OVERLAY_VISIBLE_OPACITY = 1.0;
	private static final double OVERLAY_FADE_OPACITY = 0.35;
	private static final double OVERLAY_INVISIBLE_OPACITY = 0.0;

	// ================================================================
	// NON-FXML FIELDS
	// ================================================================
	private Display display;

	private static TaskDisplayController taskDisplayController;

	private boolean enableHelpView;
	private boolean enableResultView;

	private ArrayList<TaskEvent> taskList;
	private static Logger logger;

	// ================================================================
	// CONSTRUCTORS
	// ================================================================
	public static TaskDisplayController getInstance() {
		if (taskDisplayController == null) {
			taskDisplayController = new TaskDisplayController();
		}
		return taskDisplayController;
	}

	private TaskDisplayController() {
		logger = Logger.getLogger(TASK_DISPLAY_CONTROLLER);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FILE_LOC));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			logger.log(Level.SEVERE, LOGGER_INIT_UNSUCCESSFUL, new RuntimeException(e));
		}
		viewBorderPane.setBottom(InputViewController.getInstance());
		initHelpTaskDisplay();
		updateViews();
		logger.log(Level.INFO, LOGGER_INIT_SUCCESSFUL);
	}

	/**
	 * Method to help with TestFX JUnit Testing
	 * 
	 * @return size of taskList
	 */
	public int getTaskListSize() {
		return taskList.size();
	}

	/**
	 * Refreshes and updates all display views.
	 */
	public void updateViews() {
		Launch.getInstance();
		display = Launch.getDisplay();
		refreshAllViews();
		updateAllTaskDisplays();
	}

	// ================================================================
	// PRIVATE METHODS
	// ================================================================
	/**
	 * Updates all tasks with the latest information
	 */
	private void updateAllTaskDisplays() {
		getAllTask();
		updateGeneralTaskDisplay();
		updateFlaggedTaskDisplay();
		updateFloatingTaskDisplay();
		updateResultTaskDisplay();
	}

	private void updateGeneralTaskDisplay() {
		ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
		displayTasks = getGeneralTask();
		generalView.setItems(displayTasks);
		generalTaskLabel.setText(LABEL_GENERAL + displayTasks.size() + LABEL_TASK_DISPLAYED);
	}

	private void updateFlaggedTaskDisplay() {
		ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
		displayTasks = getFlaggedTask();
		flagView.setItems(displayTasks);
		flagTaskLabel.setText(LABEL_FLAGGED + displayTasks.size() + LABEL_TASK_DISPLAYED);
	}

	private void updateFloatingTaskDisplay() {
		ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
		displayTasks = getFloatingTask();
		floatingView.setItems(displayTasks);
		floatingTaskLabel.setText(LABEL_FLOATING + displayTasks.size() + LABEL_TASK_DISPLAYED);
	}

	private void updateResultTaskDisplay() {
		ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
		displayTasks = getResultedTask();
		resultContent.setItems(displayTasks);
	}

	private void initHelpTaskDisplay() {
		ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
		displayTasks = getHelpCommands();
		helpContent.setItems(displayTasks);
	}

	private void getAllTask() {
		taskList = display.taskView();
		getGeneralTask();
		getFloatingTask();
		getFlaggedTask();
	}

	private ObservableList<HBox> getGeneralTask() {

		ObservableList<HBox> tasks = FXCollections.observableArrayList();
		for (TaskEvent t : taskList) {
			if ((t.getEndDate().getDay() != 0 || t.getStartDate().getDay() != 0)
					&& !t.getPriority().toString().equals(TASK_FLAG)) {
				tasks.add(new Task(t, taskList.indexOf(t) + TASK_NUMBERING_OFFSET));
			}
		}
		return tasks;
	}

	private ObservableList<HBox> getFloatingTask() {

		ObservableList<HBox> tasks = FXCollections.observableArrayList();
		for (TaskEvent t : taskList) {
			if (t.getEndDate().getDay() == 0 && t.getStartDate().getDay() == 0) {
				tasks.add(new Task(t, taskList.indexOf(t) + TASK_NUMBERING_OFFSET));
			}
		}
		return tasks;
	}

	private ObservableList<HBox> getFlaggedTask() {

		ObservableList<HBox> tasks = FXCollections.observableArrayList();
		for (TaskEvent t : taskList) {
			if ((t.getEndDate().getDay() != 0 || t.getStartDate().getDay() != 0)
					&& (t.getPriority()).toString().equals(TASK_FLAG)) {
				tasks.add(new Task(t, taskList.indexOf(t) + TASK_NUMBERING_OFFSET));
			}
		}
		return tasks;
	}

	private ObservableList<HBox> getResultedTask() {

		ObservableList<HBox> tasks = FXCollections.observableArrayList();
		ArrayList<TaskEvent> searchedTaskList = display.resultView();
		for (TaskEvent t : searchedTaskList) {
			tasks.add(new Task(t, searchedTaskList.indexOf(t) + TASK_NUMBERING_OFFSET));
		}
		return tasks;
	}

	/**
	 * Sets up the help information for PIXEList.
	 * 
	 * @return tasks which contains all the help information.
	 */
	private ObservableList<HBox> getHelpCommands() {
		ObservableList<HBox> tasks = FXCollections.observableArrayList();
		tasks.add(new HelpCommand(HELP_COMMAND_ADD, HELP_DESC_ADD));
		tasks.add(new HelpCommand(HELP_COMMAND_EDIT, HELP_DESC_EDIT));
		tasks.add(new HelpCommand(HELP_COMMAND_DELETE, HELP_DESC_DELETE));
		tasks.add(new HelpCommand(HELP_COMMAND_UNDO, HELP_DESC_UNDO));
		tasks.add(new HelpCommand(HELP_COMMAND_REDO, HELP_DESC_REDO));
		tasks.add(new HelpCommand(HELP_COMMAND_SEARCH, HELP_DESC_SEARCH));
		tasks.add(new HelpCommand(HELP_COMMAND_FINISH, HELP_DESC_FINISH));
		tasks.add(new HelpCommand(HELP_COMMAND_FLAG, HELP_DESC_FLAG));
		tasks.add(new HelpCommand(HELP_COMMAND_UNFLAG, HELP_DESC_UNFLAG));
		tasks.add(new HelpCommand(HELP_COMMAND_VIEW_COMPLETED, HELP_DESC_VIEW_COMPLETED));
		tasks.add(new HelpCommand(HELP_COMMAND_CHANGE_DIR, HELP_DESC_CHANGE_DIR));
		tasks.add(new HelpCommand(HELP_COMMAND_FIELD, HELP_DESC_FIELD));
		tasks.add(new HelpCommand(HELP_COMMAND_EXIT, HELP_DESC_EXIT));

		return tasks;
	}

	// ================================================================
	// GUI OVERLAY METHODS
	// ================================================================
	public boolean isResultViewEnabled() {
		return enableResultView;
	}

	public void triggerHelpView() {
		this.enableHelpView = true;
	}

	public void triggerResultView() {
		this.enableResultView = true;
	}

	private void showHelpView() {
		helpView.toFront();
		helpView.setOpacity(OVERLAY_VISIBLE_OPACITY);
		borderPane.setOpacity(OVERLAY_FADE_OPACITY);
	}

	private void showResultView() {
		resultView.toFront();
		resultView.setOpacity(OVERLAY_VISIBLE_OPACITY);
		borderPane.setOpacity(OVERLAY_FADE_OPACITY);
	}

	private void hideHelpView() {
		helpView.toBack();
		helpView.setOpacity(OVERLAY_INVISIBLE_OPACITY);
		this.enableHelpView = false;
	}

	private void hideResultView() {
		helpView.toBack();
		resultView.setOpacity(OVERLAY_INVISIBLE_OPACITY);
		this.enableResultView = false;
	}

	private void hideViews() {
		if (enableHelpView == false && enableResultView == false) {
			hideAllOverlays();
		}
	}

	public void hideAllOverlays() {
		borderPane.toFront();
		borderPane.setOpacity(OVERLAY_VISIBLE_OPACITY);
		hideHelpView();
		hideResultView();
	}

	private void refreshHelpOverlay() {
		if (enableHelpView == true) {
			showHelpView();
		} else {
			hideHelpView();
		}
	}

	private void refreshResultOverlay() {
		if (enableResultView == true) {
			showResultView();
		} else {
			hideResultView();
		}
	}

	private void refreshAllViews() {
		refreshResultOverlay();
		refreshHelpOverlay();
		hideViews();
	}
}
