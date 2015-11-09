# A0124933H Benjamin
###### ./src/resources/view/application.css
``` css
/*
 * ================================================================
 * Input View
 * ================================================================
 */

.inputView #feedBack {
	-fx-font-family: "Arial", cursive, sans-serif;
	-fx-font-size: 13px;
	-fx-text-fill: white;
	font-weight: 600;
	-fx-background-color: #202020;
	-fx-border-color: #268BD2;
	-fx-border-style: solid;
	-fx-border-width: 2px 2px 0px 2px;
} 

.inputView #userInput {
	-fx-font-family: "Consolas", cursive, sans-serif;
	-fx-font-size: 14px;
	-fx-text-fill: white;
	-fx-focus-color: blue;
	-fx-background-color:#202020;
	-fx-accent: blue;
	-fx-border-color: #268BD2;
	-fx-border-style: solid;
	-fx-border-width: 0px 2px 2px 2px;
}


/*
 * ================================================================
 * Task Styles
 * ================================================================
 */

.task {
	-fx-padding: 2 0 2 5;
	-fx-background-radius: 5;
	-fx-font-family: "Arial", sans-serif;
	-fx-font-size: 13px;
}

.task #index { 
	-fx-padding: 0 5 0 5;
	-fx-text-fill: #FF8000;
	-fx-font-size: 11px;
}

.task #taskName {
	-fx-padding: 0 0 0 6;
	-fx-text-fill: white;
}

.task #taskFlag {
	-fx-padding: 12;
	-fx-fill: #F02E4F;
}

.task #sDate {
	-fx-padding: 1 0 0 5;
	-fx-text-fill: #ADADAD;
	-fx-font-size:11px;	
}

.task #eDate { 
	-fx-padding: 1 0 0 5;
	-fx-text-fill: #C0C0C0;
	-fx-font-size:11px;
}
.task #startTime {
	-fx-padding : 1 0 0 5;
	-fx-text-fill: #FF8000;
	-fx-font-size:11px;
}

.task #endTime {
	-fx-padding: 1 0 0 5;
	-fx-text-fill: #FF8000;
	-fx-font-size:11px;
	
}

.task #checkBox {
	-fx-font-size: 8px;
}

.task #jointLabel {
	-fx-font-size: 10px;
	-fx-padding: 1 0 0 5;
	-fx-text-fill: #FDC325;
}

/*
 * ================================================================
 * Help Style
 * ================================================================
 */

.helpCommand {
	-fx-padding: 2;
	-fx-font-size:13px;
	-fx-background-color: transparent;
}

.helpCommand #command {
	-fx-font-weight: 700;
}

/*
 * ================================================================
 * Root
 * ================================================================
 */
.root{
	-fx-background-color: transparent;
}
/*
 * ================================================================
 * Task Display
 * ================================================================
 */
 
.display #borderPane {
	-fx-background-color: #1B1C1D; 
}
 
.display #resultContent {
 	-fx-background-color: #586E75;
 	-fx-padding: 13;
 	-fx-background-radius: 20;
}
 
.display #resultContent .list-cell {
 	-fx-background-color: #586E75;
}

.display #resultContent .listView {
	-fx-background-color: #586E75;
}

.display #helpContent {
	-fx-background-color: #3399FF;
	-fx-padding: 13;
	-fx-background-radius: 20;
}

.display #helpContent .list-cell{
	-fx-background-color: transparent;
}
.display #generalTaskLabel {
	-fx-font-family: "Arial", cursive, sans-serif;
	-fx-font-size: 14px;
	-fx-font-weight: bold;
	-fx-text-fill: #2AA198;
	font-weight: 700;
	-fx-background-color: #1B1C1D;
	-fx-padding: 2 24 2 2;
	-fx-border-color: #1B1C1D;
	-fx-border-style: solid;
	-fx-border-width: 2px 2px 0px 0px;
}

.display #generalView {
	-fx-background-color: #1B1C1D;
	-fx-border-color:#1B1C1D;
	-fx-border-style:solid;
	-fx-border-width:2px 2px 0px 0px;
}

.display #generalView .list-cell {
	-fx-background-color: #1B1C1D;
}

.display #generalView .listView {
	-fx-background-color: #1B1C1D;
}
.display #flagTaskLabel {
	-fx-font-family: "Arial", cursive, sans-serif;
	-fx-font-size: 14px;
	-fx-font-weight: bold;
	-fx-text-fill: #DC322F;
	font-weight : 700;
	-fx-background-color: #1B1C1D;
	-fx-border-color: #1B1C1D;
	-fx-border-style: solid;
	-fx-border-width: 2px 2px 0px 0px;
	-fx-padding: 2 24 2 2;
}

.display #flagView {
	-fx-background-color: #1B1C1D;
	-fx-border-color:#1B1C1D;
	-fx-border-style:solid;
	-fx-border-width:2px 2px 2px 0px;
}

.display #flagView .list-cell {
	-fx-background-color: #1B1C1D;
}

.display #flagView .listView {
	-fx-background-color: #1B1C1D;
}

.display #floatingTaskLabel {
	-fx-font-family: "Arial", cursive, sans-serif;
	-fx-font-size: 14px;
	-fx-font-weight: bold;
	-fx-text-fill: #B58900;
	font-weight: 700;
	-fx-background-color: #1B1C1D;
	-fx-padding: 2 17 2 2;
	-fx-border-color:#1B1C1D;
	-fx-border-style:solid;
	-fx-border-width:2px 2px 0px 0px;
}

.display #floatingView {
	-fx-background-color: #1B1C1D;
	-fx-border-color: #1B1C1D;
	-fx-border-style: solid;
	-fx-border-width: 2px 2px 2px 2px;
}

.display #floatingView .list-cell {
	-fx-background-color: #1B1C1D;
}

.display #floatingView .listView {
	-fx-background-color: #1B1C1D;
}

.display .list-view .scroll-bar:horizontal {
	-fx-opacity: 0;
	-fx-padding: -10;
}

.display .list-view .scroll-bar:vertical {
	-fx-opacity: 0;
	-fx-padding: -10;
}

.display .list-cell {
	-fx-padding: 0 0 3 0;
	-fx-background-color: #1B1C1D;
}

```
###### ./src/resources/view/HelpCommand.fxml
``` fxml

<?import javafx.geometry.*?>
<?import javafx.scene.control.*?>
<?import java.lang.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.BorderPane?>

<fx:root fx:id="helpCmd" alignment="CENTER_LEFT" fillHeight="false" prefHeight="20.0" prefWidth="400.0" styleClass="helpCommand" type="HBox" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <Label fx:id="description" focusTraversable="false" text="Label" HBox.hgrow="ALWAYS">
         <HBox.margin>
            <Insets />
         </HBox.margin></Label>
      <HBox alignment="CENTER_RIGHT" nodeOrientation="LEFT_TO_RIGHT" HBox.hgrow="ALWAYS">
         <children>
            <Label fx:id="command" focusTraversable="false" nodeOrientation="LEFT_TO_RIGHT" text="Label" HBox.hgrow="ALWAYS">
               <HBox.margin>
                  <Insets />
               </HBox.margin>
            </Label>
         </children>
      </HBox>
   </children>
</fx:root>
```
###### ./src/resources/view/HelpCommand.java
``` java
public class HelpCommand extends HBox {

	// ================================================================
	// FXML FIELDS
	// ================================================================
	@FXML
	private Label command;

	@FXML
	private Label description;

	// ================================================================
	// CONSTANTS
	// ================================================================
	private static final String FILE_LOC = "HelpCommand.fxml";

	// ================================================================
	// CONSTRUCTOR
	// ================================================================
	public HelpCommand(String command, String description) {
		loadFxml();
		initFxmlFields(command, description);
	}

	// ================================================================
	// INITIALIZATION METHOD
	// ================================================================
	private void initFxmlFields(String command, String description) {
		this.command.setText(command);
		this.description.setText(description);
	}

	private void loadFxml() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(FILE_LOC));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```
###### ./src/resources/view/InputView.fxml
``` fxml

<?import javafx.geometry.*?>
<?import java.lang.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.VBox?>

<fx:root fx:id="inputVBox" alignment="TOP_CENTER" minHeight="40.0" prefHeight="55.0" prefWidth="820.0" styleClass="inputView" type="VBox" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <Label fx:id="feedBack" alignment="CENTER" contentDisplay="CENTER" focusTraversable="false" minHeight="20.0" prefHeight="25.0" prefWidth="800.0" text="Feedback" VBox.vgrow="ALWAYS">
         <VBox.margin>
            <Insets />
         </VBox.margin>
      </Label>
      <TextField fx:id="userInput" minHeight="25.0" onKeyPressed="#handleKeyPress" prefHeight="30.0" promptText="Welcome to PIXEList" VBox.vgrow="ALWAYS" />
   </children>
</fx:root>
```
###### ./src/resources/view/InputViewController.java
``` java
public class InputViewController extends VBox {

	// ================================================================
	// FXML FIELDS
	// ================================================================
	@FXML
	private TextField userInput;

	@FXML
	private Label feedBack;

	// ================================================================
	// NON-FXML FIELDS
	// ================================================================
	Launch launch;
	TaskDisplayController taskDisplay;

	private ArrayList<String> history;
	private ArrayList<String> commands;
	private ArrayList<String> presetTaskTestsList;
	private int historyPointer;
	private int commandsPointer;
	private static Logger logger;

	public static InputViewController inputViewController;

	// ================================================================
	// CONSTANTS
	// ================================================================
	private static final String FILE_LOC = "InputView.fxml";
	private static final String INIT_TEST_DATA = "Test data initialized";
	private static final String INPUT_VIEW = "InputView";
	private static final String EMPTY_STRING = "";

	private static final String COMMAND_ADD = "add ";
	private static final String COMMAND_DELETE = "delete ";
	private static final String COMMAND_EDIT = "edit ";
	private static final String COMMAND_SEARCH = "search ";
	private static final String COMMAND_VIEW_COMPLETED = "view completed";
	private static final String COMMAND_FLAG = "flag ";
	private static final String COMMAND_UNFLAG = "unflag ";
	private static final String COMMAND_REDO = "redo";
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_DIRECTORY = "directory";
	private static final String COMMAND_HELP = "help";
	private static final String COMMAND_MARK_FINISH = "finish";
	private static final String COMMAND_EXIT = "exit";

	private static final String TEST_TASK_ONE = "add \"Oral Presentation for CS2101\" 2/11/2015 FLAG";
	private static final String TEST_TASK_TWO = "add \"Meeting about budget\" 5/11/2015 FLAG";
	private static final String TEST_TASK_THREE = "add \"CS2101 Report writing\" 10/11/2015";
	private static final String TEST_TASK_FOUR = "add \"Workshop about Creative Design\" 4/11/2015 6/11/2015";
	private static final String TEST_TASK_FIVE = "add \"CS2103 Milestone 4\" 4/11/2015 ";
	private static final String TEST_TASK_SIX = "add \"Meeting with HR\" 9/11/2015 FLAG";
	private static final String TEST_TASK_SEVEN = "add \"Watch LOTR\"";
	private static final String TEST_TASK_EIGHT = "add \"Buy Apples\"";
	private static final String TEST_TASK_NINE = "add \"Attend roadshow\" 5/11/2015 5pm to 8pm";
	private static final String TEST_TASK_TEN = "add \"Pay the Bills\" 8/11/2015";
	private static final String TEST_TASK_ELEVEN = "add \"Collect suit from Tailor \" 10/11/2015 4pm";
	private static final String TEST_TASK_TWELVE = "add \"Send Laundry for washing \" 11/11/2015 12am to 4pm";
	private static final String TEST_TASK_THIRTEEN = "add \"Clean the house\"";
	private static final String TEST_TASK_FOURTEEN = "add \"Buy Groceries\"";
	private static final String TEST_TASK_FIFTEEN = "add \"Submit powerpoint slides\" 12/11/2015 10am";
	private static final String TEST_TASK_SIXTEEN = "add \" Buy Stationaries\" ";
	private static final String TEST_TASK_SEVENTEEN = "add \"Meeting with colleagues about expenditure \" 15/11/2015 9.15am";
	private static final String TEST_TASK_EIGHTEEN = "add \"Buy Coffee powder\"";
	private static final String TEST_TASK_NINETEEN = "add \" Inform boss about progress report\" 18/11/2015 11.30am";
	private static final String TEST_TASK_TWENTY = "add \"Wash the car\"";

	private static final String LOGGER_INIT_UNSUCCESSFUL = "INPUT VIEW FAILED TO INITIALIZE";
	private static final String LOGGER_INIT_SUCCESSFUL = "InputView initiated successfully";

	// ================================================================
	// CONSTRUCTORS
	// ================================================================
	public static InputViewController getInstance() {
		if (inputViewController == null) {
			inputViewController = new InputViewController();
		}
		return inputViewController;
	}

	public InputViewController() {
		logger = Logger.getLogger(INPUT_VIEW);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FILE_LOC));
		loader.setRoot(this);
		loader.setController(this);
		try {
			inputViewController = loader.load();
		} catch (IOException e) {
			logger.log(Level.SEVERE, LOGGER_INIT_UNSUCCESSFUL, new RuntimeException(e));
		}
		initPresetArrayList();
		initHistoryList();
		initCommandList();
		logger.log(Level.INFO, LOGGER_INIT_SUCCESSFUL);
	}

	/**
	 * Handles key presses for user input bar.
	 * 
	 * @param event
	 */
	public void handleKeyPress(KeyEvent event) {
		taskDisplay = TaskDisplayController.getInstance();
		if (event.getCode() == KeyCode.ENTER) {
			taskDisplay.hideAllOverlays();
			handleUserInput();
			userInput.setText(EMPTY_STRING);
			taskDisplay.updateViews();
			resetCommandPointer();
		} else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
			event.consume();
			handleHistoryCommands(event);
		} else if (event.getCode() == KeyCode.F1) {
			taskDisplay.hideAllOverlays();
			event.consume();
			initTesting();
			taskDisplay.updateViews();
		} else if (event.getCode() == KeyCode.ESCAPE) {
			taskDisplay.hideAllOverlays();
		} else if (event.getCode() == KeyCode.TAB) {
			event.consume();
			handleCommandList(commandsPointer++);
		}
	}

	public void passToLogic(String input) {
		taskDisplay = TaskDisplayController.getInstance();
		launch = Launch.getInstance();
		String output = launch.command(input);
		labelFeedBack(output);
		taskDisplay.updateViews();
	}

	// ================================================================
	// PRIVATE METHODS
	// ================================================================
	private void handleHistoryCommands(KeyEvent event) {
		String pastCmd = getHistoryCommands(event.getCode());
		userInput.setText(pastCmd);
		userInput.positionCaret(pastCmd.length());
	}

	private void handleCommandList(int i) {
		String command = getCommands(i);
		userInput.setText(command);
		userInput.positionCaret(command.length());
	}

	private void handleUserInput() {
		String input = userInput.getText();
		if (input.equals(COMMAND_EXIT)) {
			System.exit(0);
		}
		updateHistoryList();
		passToLogic(input);
	}

	private void labelFeedBack(String input) {
		feedBack.setText(input);
	}

	private void initTesting() {
		launch = Launch.getInstance();
		for (String in : presetTaskTestsList) {
			launch.command(in);
		}
		labelFeedBack(INIT_TEST_DATA);
	}

	private void initCommandList() {
		commandsPointer = 0;
		commands = new ArrayList<String>();
		commands.add(COMMAND_ADD);
		commands.add(COMMAND_EDIT);
		commands.add(COMMAND_DELETE);
		commands.add(COMMAND_SEARCH);
		commands.add(COMMAND_UNDO);
		commands.add(COMMAND_REDO);
		commands.add(COMMAND_VIEW_COMPLETED);
		commands.add(COMMAND_FLAG);
		commands.add(COMMAND_UNFLAG);
		commands.add(COMMAND_HELP);
		commands.add(COMMAND_EXIT);
		commands.add(COMMAND_DIRECTORY);
		commands.add(COMMAND_MARK_FINISH);

	}

	private String getCommands(int i) {
		i = i % commands.size();
		return commands.get(i);
	}

	private void resetCommandPointer() {
		commandsPointer = 0;
	}

	private void initHistoryList() {
		history = new ArrayList<String>();
		history.add(EMPTY_STRING);
		historyPointer = history.size() - 1;
	}

	private void updateHistoryList() {
		historyPointer = history.size();
		history.add(historyPointer - 1, userInput.getText());
	}

	private String getHistoryCommands(KeyCode code) {
		if (code == KeyCode.DOWN) {
			return getNextCommand();
		} else if (code == KeyCode.UP) {
			return getPrevCommand();
		} else {
			return EMPTY_STRING;
		}
	}

	private String getPrevCommand() {
		if (historyPointer > 0) {
			historyPointer--;
		}
		return history.get(historyPointer);
	}

	private String getNextCommand() {
		if (historyPointer < history.size() - 1) {
			historyPointer++;
		}
		return history.get(historyPointer);
	}

	private void initPresetArrayList() {
		presetTaskTestsList = new ArrayList<String>();
		presetTaskTestsList.add(TEST_TASK_ONE);
		presetTaskTestsList.add(TEST_TASK_TWO);
		presetTaskTestsList.add(TEST_TASK_THREE);
		presetTaskTestsList.add(TEST_TASK_FOUR);
		presetTaskTestsList.add(TEST_TASK_FIVE);
		presetTaskTestsList.add(TEST_TASK_SIX);
		presetTaskTestsList.add(TEST_TASK_SEVEN);
		presetTaskTestsList.add(TEST_TASK_EIGHT);
		presetTaskTestsList.add(TEST_TASK_NINE);
		presetTaskTestsList.add(TEST_TASK_TEN);
		presetTaskTestsList.add(TEST_TASK_ELEVEN);
		presetTaskTestsList.add(TEST_TASK_TWELVE);
		presetTaskTestsList.add(TEST_TASK_THIRTEEN);
		presetTaskTestsList.add(TEST_TASK_FOURTEEN);
		presetTaskTestsList.add(TEST_TASK_FIFTEEN);
		presetTaskTestsList.add(TEST_TASK_SIXTEEN);
		presetTaskTestsList.add(TEST_TASK_SEVENTEEN);
		presetTaskTestsList.add(TEST_TASK_EIGHTEEN);
		presetTaskTestsList.add(TEST_TASK_NINETEEN);
		presetTaskTestsList.add(TEST_TASK_TWENTY);
	}

	// ================================================================
	// GETTERS
	// ================================================================
	public int getPresetSize() {
		return presetTaskTestsList.size();
	}

	public int getCommandsSize() {
		return commands.size();
	}
}
```
###### ./src/resources/view/MainApp.java
``` java
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
```
###### ./src/resources/view/PIXEListTest.java
``` java
public class PIXEListTest extends GuiTest {

	BorderPane sceneRoot;
	TaskDisplayController taskDisplay;
	InputViewController inputView;
	Stage primaryStage;
	TextField userInput;
	Label feedBack;

	@Test
	public void testHelp() {
		userInput.setText("help");
		push(KeyCode.ENTER);
		verifyLabel("help activated");
		sleep(2, TimeUnit.SECONDS);
		push(KeyCode.ESCAPE);
	}

	@Test
	public void testInitDatas() {
		push(KeyCode.F1);
		assertEquals(inputView.getPresetSize(), 20);
	}

	@Test
	public void testAdd() {
		userInput.setText("add \"CS2010\"");
		push(KeyCode.ENTER);
		verifyLabel("CS2010 has been added successfully");
	}

	public void verifyLabel(String result) {
		assertEquals(result, feedBack.getText());
	}

	@Test
	public void testUndo() {
		userInput.setText("undo");
		push(KeyCode.ENTER);
	}

	@Test
	public void testRedo() {
		userInput.setText("redo");
		push(KeyCode.ENTER);
	}

	@Override
	protected Parent getRootNode() {
		return null;
	}

	@Override
	public void setupStage() throws Throwable {
		File f = new File("tasks.txt");
		f.delete();
		new Thread(() -> Application.launch(MainApp.class)).start();
		sleep(4, TimeUnit.SECONDS);
		userInput = (TextField) find("#userInput");
		feedBack = (Label) find("#feedBack");
		inputView = InputViewController.getInstance();
		taskDisplay = TaskDisplayController.getInstance();
	}
}
```
###### ./src/resources/view/Root.fxml
``` fxml

<?import java.lang.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.BorderPane?>


<fx:root prefHeight="600" prefWidth="800" stylesheets="@application.css" type="BorderPane" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
</fx:root>
```
###### ./src/resources/view/RootController.java
``` java
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
	private static final String FILE_LOC = "Root.fxml";

	private static final String LOGGER_INIT_UNSUCCESSFUL = "ROOT LAYOUT FAILED TO INITIALIZE";
	private static final String LOGGER_INIT_SUCCESSFUL = "Root layout initiated successfully";

	// ================================================================
	// CONSTRUCTORS
	// ================================================================
	public RootController() {
		logger = Logger.getLogger(ROOT);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FILE_LOC));
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
```
###### ./src/resources/view/Task.fxml
``` fxml

<?import de.jensd.fx.glyphs.fontawesome.*?>
<?import javafx.geometry.*?>
<?import java.lang.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.HBox?>

<fx:root alignment="CENTER_LEFT" nodeOrientation="LEFT_TO_RIGHT" prefHeight="20.0" styleClass="task" type="HBox" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <CheckBox fx:id="checkBox" focusTraversable="false" mnemonicParsing="false" />
      <Label fx:id="index" focusTraversable="false" text="Index " />
      <Label fx:id="taskName" focusTraversable="false" text="Task Name " />
      <FontAwesomeIconView fx:id="taskFlag" glyphName="FLAG" HBox.hgrow="ALWAYS" />
      <Label fx:id="sDate" focusTraversable="false" text="sDate" />
      <Label fx:id="startTime" focusTraversable="false" />
      <Label fx:id="jointLabel" focusTraversable="false" />
      <Label fx:id="eDate" focusTraversable="false" text="eDate" />
      <Label fx:id="endTime" focusTraversable="false" />
   </children>
</fx:root>
```
###### ./src/resources/view/TaskDisplay.fxml
``` fxml

<?import javafx.geometry.*?>
<?import javafx.scene.media.*?>
<?import java.lang.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.BorderPane?>

<fx:root fx:id="stackPane" prefHeight="750.0" prefWidth="420.0" styleClass="display" type="StackPane" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <BorderPane fx:id="borderPane">
         <center>
            <BorderPane fx:id="viewBorderPane" prefHeight="780.0" prefWidth="200.0" BorderPane.alignment="CENTER_LEFT">
               <top>
                  <VBox fx:id="flagVBox" maxHeight="200.0" prefHeight="200.0" prefWidth="200.0" BorderPane.alignment="TOP_CENTER">
                     <children>
                        <Label fx:id="flagTaskLabel" focusTraversable="false" prefHeight="20.0" text="Flagged tasks" VBox.vgrow="ALWAYS" />
                        <ListView fx:id="flagView" focusTraversable="false" VBox.vgrow="ALWAYS" />
                     </children>
                  </VBox>
               </top>
               <center>
                  <VBox fx:id="genVBox" prefHeight="580.0" prefWidth="160.0" BorderPane.alignment="TOP_CENTER">
                     <children>
                        <Label fx:id="generalTaskLabel" focusTraversable="false" maxHeight="15.0" prefHeight="10.0" text="General Tasks" VBox.vgrow="ALWAYS" />
                        <ListView fx:id="generalView" focusTraversable="false" prefHeight="555.0" VBox.vgrow="ALWAYS" />
                     </children>
                  </VBox>
               </center>
            </BorderPane>
         </center>
         <right>
            <VBox fx:id="floatVBox" alignment="CENTER_LEFT" prefWidth="280.0" BorderPane.alignment="TOP_LEFT">
               <children>
                  <Label fx:id="floatingTaskLabel" focusTraversable="false" text="Floating Tasks" VBox.vgrow="ALWAYS" />
                  <ListView fx:id="floatingView" focusTraversable="false" VBox.vgrow="ALWAYS" />
               </children>
            </VBox>
         </right>
      </BorderPane>
      <VBox fx:id="helpView" alignment="CENTER" fillWidth="false" prefHeight="400.0" prefWidth="450.0">
         <children>
            <Label alignment="CENTER" contentDisplay="CENTER" focusTraversable="false" prefWidth="300.0" text="Need Help?" VBox.vgrow="ALWAYS">
               <VBox.margin>
                  <Insets bottom="1.0" left="1.0" right="1.0" top="1.0" />
               </VBox.margin>
               <padding>
                  <Insets bottom="2.0" left="2.0" right="2.0" top="2.0" />
               </padding></Label>
            <ListView fx:id="helpContent" focusTraversable="false" maxHeight="400.0" maxWidth="450.0" prefHeight="400.0" prefWidth="450.0" />
         </children></VBox>
      <VBox fx:id="resultView" alignment="CENTER" fillWidth="false" prefHeight="400.0">
         <children>
            <Label fx:id="resultLabel" alignment="CENTER" contentDisplay="CENTER" focusTraversable="false" prefWidth="300.0" text="Results" />
            <ListView fx:id="resultContent" focusTraversable="false" maxHeight="400.0" prefHeight="400.0" prefWidth="500.0" />
         </children>
      </VBox>
   </children>
</fx:root>
```
###### ./src/resources/view/TaskDisplayController.java
``` java
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
```
###### ./target/classes/resources/view/application.css
``` css
/*
 * ================================================================
 * Input View
 * ================================================================
 */

.inputView #feedBack {
	-fx-font-family: "Arial", cursive, sans-serif;
	-fx-font-size: 13px;
	-fx-text-fill: white;
	font-weight: 600;
	-fx-background-color: #202020;
	-fx-border-color: #268BD2;
	-fx-border-style: solid;
	-fx-border-width: 2px 2px 0px 2px;
} 

.inputView #userInput {
	-fx-font-family: "Consolas", cursive, sans-serif;
	-fx-font-size: 14px;
	-fx-text-fill: white;
	-fx-focus-color: blue;
	-fx-background-color:#202020;
	-fx-accent: blue;
	-fx-border-color: #268BD2;
	-fx-border-style: solid;
	-fx-border-width: 0px 2px 2px 2px;
}


/*
 * ================================================================
 * Task Styles
 * ================================================================
 */

.task {
	-fx-padding: 2 0 2 5;
	-fx-background-radius: 5;
	-fx-font-family: "Arial", sans-serif;
	-fx-font-size: 13px;
}

.task #index { 
	-fx-padding: 0 5 0 5;
	-fx-text-fill: #FF8000;
	-fx-font-size: 11px;
}

.task #taskName {
	-fx-padding: 0 0 0 6;
	-fx-text-fill: white;
}

.task #taskFlag {
	-fx-padding: 12;
	-fx-fill: #F02E4F;
}

.task #sDate {
	-fx-padding: 1 0 0 5;
	-fx-text-fill: #ADADAD;
	-fx-font-size:11px;	
}

.task #eDate { 
	-fx-padding: 1 0 0 5;
	-fx-text-fill: #C0C0C0;
	-fx-font-size:11px;
}
.task #startTime {
	-fx-padding : 1 0 0 5;
	-fx-text-fill: #FF8000;
	-fx-font-size:11px;
}

.task #endTime {
	-fx-padding: 1 0 0 5;
	-fx-text-fill: #FF8000;
	-fx-font-size:11px;
	
}

.task #checkBox {
	-fx-font-size: 8px;
}

.task #jointLabel {
	-fx-font-size: 10px;
	-fx-padding: 1 0 0 5;
	-fx-text-fill: #FDC325;
}

/*
 * ================================================================
 * Help Style
 * ================================================================
 */

.helpCommand {
	-fx-padding: 2;
	-fx-font-size:13px;
	-fx-background-color: transparent;
}

.helpCommand #command {
	-fx-font-weight: 700;
}

/*
 * ================================================================
 * Root
 * ================================================================
 */
.root{
	-fx-background-color: transparent;
}
/*
 * ================================================================
 * Task Display
 * ================================================================
 */
 
.display #borderPane {
	-fx-background-color: #1B1C1D; 
}
 
.display #resultContent {
 	-fx-background-color: #586E75;
 	-fx-padding: 13;
 	-fx-background-radius: 20;
}
 
.display #resultContent .list-cell {
 	-fx-background-color: #586E75;
}

.display #resultContent .listView {
	-fx-background-color: #586E75;
}

.display #helpContent {
	-fx-background-color: #3399FF;
	-fx-padding: 13;
	-fx-background-radius: 20;
}

.display #helpContent .list-cell{
	-fx-background-color: transparent;
}
.display #generalTaskLabel {
	-fx-font-family: "Arial", cursive, sans-serif;
	-fx-font-size: 14px;
	-fx-font-weight: bold;
	-fx-text-fill: #2AA198;
	font-weight: 700;
	-fx-background-color: #1B1C1D;
	-fx-padding: 2 24 2 2;
	-fx-border-color: #1B1C1D;
	-fx-border-style: solid;
	-fx-border-width: 2px 2px 0px 0px;
}

.display #generalView {
	-fx-background-color: #1B1C1D;
	-fx-border-color:#1B1C1D;
	-fx-border-style:solid;
	-fx-border-width:2px 2px 0px 0px;
}

.display #generalView .list-cell {
	-fx-background-color: #1B1C1D;
}

.display #generalView .listView {
	-fx-background-color: #1B1C1D;
}
.display #flagTaskLabel {
	-fx-font-family: "Arial", cursive, sans-serif;
	-fx-font-size: 14px;
	-fx-font-weight: bold;
	-fx-text-fill: #DC322F;
	font-weight : 700;
	-fx-background-color: #1B1C1D;
	-fx-border-color: #1B1C1D;
	-fx-border-style: solid;
	-fx-border-width: 2px 2px 0px 0px;
	-fx-padding: 2 24 2 2;
}

.display #flagView {
	-fx-background-color: #1B1C1D;
	-fx-border-color:#1B1C1D;
	-fx-border-style:solid;
	-fx-border-width:2px 2px 2px 0px;
}

.display #flagView .list-cell {
	-fx-background-color: #1B1C1D;
}

.display #flagView .listView {
	-fx-background-color: #1B1C1D;
}

.display #floatingTaskLabel {
	-fx-font-family: "Arial", cursive, sans-serif;
	-fx-font-size: 14px;
	-fx-font-weight: bold;
	-fx-text-fill: #B58900;
	font-weight: 700;
	-fx-background-color: #1B1C1D;
	-fx-padding: 2 17 2 2;
	-fx-border-color:#1B1C1D;
	-fx-border-style:solid;
	-fx-border-width:2px 2px 0px 0px;
}

.display #floatingView {
	-fx-background-color: #1B1C1D;
	-fx-border-color: #1B1C1D;
	-fx-border-style: solid;
	-fx-border-width: 2px 2px 2px 2px;
}

.display #floatingView .list-cell {
	-fx-background-color: #1B1C1D;
}

.display #floatingView .listView {
	-fx-background-color: #1B1C1D;
}

.display .list-view .scroll-bar:horizontal {
	-fx-opacity: 0;
	-fx-padding: -10;
}

.display .list-view .scroll-bar:vertical {
	-fx-opacity: 0;
	-fx-padding: -10;
}

.display .list-cell {
	-fx-padding: 0 0 3 0;
	-fx-background-color: #1B1C1D;
}

```
###### ./target/classes/resources/view/HelpCommand.fxml
``` fxml

<?import javafx.geometry.*?>
<?import javafx.scene.control.*?>
<?import java.lang.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.BorderPane?>

<fx:root fx:id="helpCmd" alignment="CENTER_LEFT" fillHeight="false" prefHeight="20.0" prefWidth="400.0" styleClass="helpCommand" type="HBox" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <Label fx:id="description" focusTraversable="false" text="Label" HBox.hgrow="ALWAYS">
         <HBox.margin>
            <Insets />
         </HBox.margin></Label>
      <HBox alignment="CENTER_RIGHT" nodeOrientation="LEFT_TO_RIGHT" HBox.hgrow="ALWAYS">
         <children>
            <Label fx:id="command" focusTraversable="false" nodeOrientation="LEFT_TO_RIGHT" text="Label" HBox.hgrow="ALWAYS">
               <HBox.margin>
                  <Insets />
               </HBox.margin>
            </Label>
         </children>
      </HBox>
   </children>
</fx:root>
```
###### ./target/classes/resources/view/InputView.fxml
``` fxml

<?import javafx.geometry.*?>
<?import java.lang.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.VBox?>

<fx:root fx:id="inputVBox" alignment="TOP_CENTER" minHeight="40.0" prefHeight="55.0" prefWidth="820.0" styleClass="inputView" type="VBox" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <Label fx:id="feedBack" alignment="CENTER" contentDisplay="CENTER" focusTraversable="false" minHeight="20.0" prefHeight="25.0" prefWidth="800.0" text="Feedback" VBox.vgrow="ALWAYS">
         <VBox.margin>
            <Insets />
         </VBox.margin>
      </Label>
      <TextField fx:id="userInput" minHeight="25.0" onKeyPressed="#handleKeyPress" prefHeight="30.0" promptText="Welcome to PIXEList" VBox.vgrow="ALWAYS" />
   </children>
</fx:root>
```
###### ./target/classes/resources/view/Root.fxml
``` fxml

<?import java.lang.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.BorderPane?>


<fx:root prefHeight="600" prefWidth="800" stylesheets="@application.css" type="BorderPane" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
</fx:root>
```
###### ./target/classes/resources/view/Task.fxml
``` fxml

<?import de.jensd.fx.glyphs.fontawesome.*?>
<?import javafx.geometry.*?>
<?import java.lang.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.HBox?>

<fx:root alignment="CENTER_LEFT" nodeOrientation="LEFT_TO_RIGHT" prefHeight="20.0" styleClass="task" type="HBox" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <CheckBox fx:id="checkBox" focusTraversable="false" mnemonicParsing="false" />
      <Label fx:id="index" focusTraversable="false" text="Index " />
      <Label fx:id="taskName" focusTraversable="false" text="Task Name " />
      <FontAwesomeIconView fx:id="taskFlag" glyphName="FLAG" HBox.hgrow="ALWAYS" />
      <Label fx:id="sDate" focusTraversable="false" text="sDate" />
      <Label fx:id="startTime" focusTraversable="false" />
      <Label fx:id="jointLabel" focusTraversable="false" />
      <Label fx:id="eDate" focusTraversable="false" text="eDate" />
      <Label fx:id="endTime" focusTraversable="false" />
   </children>
</fx:root>
```
###### ./target/classes/resources/view/TaskDisplay.fxml
``` fxml

<?import javafx.geometry.*?>
<?import javafx.scene.media.*?>
<?import java.lang.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.BorderPane?>

<fx:root fx:id="stackPane" prefHeight="750.0" prefWidth="420.0" styleClass="display" type="StackPane" xmlns="http://javafx.com/javafx/8.0.40" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <BorderPane fx:id="borderPane">
         <center>
            <BorderPane fx:id="viewBorderPane" prefHeight="780.0" prefWidth="200.0" BorderPane.alignment="CENTER_LEFT">
               <top>
                  <VBox fx:id="flagVBox" maxHeight="200.0" prefHeight="200.0" prefWidth="200.0" BorderPane.alignment="TOP_CENTER">
                     <children>
                        <Label fx:id="flagTaskLabel" focusTraversable="false" prefHeight="20.0" text="Flagged tasks" VBox.vgrow="ALWAYS" />
                        <ListView fx:id="flagView" focusTraversable="false" VBox.vgrow="ALWAYS" />
                     </children>
                  </VBox>
               </top>
               <center>
                  <VBox fx:id="genVBox" prefHeight="580.0" prefWidth="160.0" BorderPane.alignment="TOP_CENTER">
                     <children>
                        <Label fx:id="generalTaskLabel" focusTraversable="false" maxHeight="15.0" prefHeight="10.0" text="General Tasks" VBox.vgrow="ALWAYS" />
                        <ListView fx:id="generalView" focusTraversable="false" prefHeight="555.0" VBox.vgrow="ALWAYS" />
                     </children>
                  </VBox>
               </center>
            </BorderPane>
         </center>
         <right>
            <VBox fx:id="floatVBox" alignment="CENTER_LEFT" prefWidth="280.0" BorderPane.alignment="TOP_LEFT">
               <children>
                  <Label fx:id="floatingTaskLabel" focusTraversable="false" text="Floating Tasks" VBox.vgrow="ALWAYS" />
                  <ListView fx:id="floatingView" focusTraversable="false" VBox.vgrow="ALWAYS" />
               </children>
            </VBox>
         </right>
      </BorderPane>
      <VBox fx:id="helpView" alignment="CENTER" fillWidth="false" prefHeight="400.0" prefWidth="450.0">
         <children>
            <Label alignment="CENTER" contentDisplay="CENTER" focusTraversable="false" prefWidth="300.0" text="Need Help?" VBox.vgrow="ALWAYS">
               <VBox.margin>
                  <Insets bottom="1.0" left="1.0" right="1.0" top="1.0" />
               </VBox.margin>
               <padding>
                  <Insets bottom="2.0" left="2.0" right="2.0" top="2.0" />
               </padding></Label>
            <ListView fx:id="helpContent" focusTraversable="false" maxHeight="400.0" maxWidth="450.0" prefHeight="400.0" prefWidth="450.0" />
         </children></VBox>
      <VBox fx:id="resultView" alignment="CENTER" fillWidth="false" prefHeight="400.0">
         <children>
            <Label fx:id="resultLabel" alignment="CENTER" contentDisplay="CENTER" focusTraversable="false" prefWidth="300.0" text="Results" />
            <ListView fx:id="resultContent" focusTraversable="false" maxHeight="400.0" prefHeight="400.0" prefWidth="500.0" />
         </children>
      </VBox>
   </children>
</fx:root>
```
