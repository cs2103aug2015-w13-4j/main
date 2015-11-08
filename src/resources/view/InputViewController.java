package resources.view;

import java.io.IOException;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import logic.Launch;
import resources.view.TaskDisplayController;

/**
 * This class handles the TextField that users can input commands and also the
 * feedback label which shows the feedback when the command is executed
 * 
 * @author A0124933H
 *
 */
//@@A0124933H Benjamin
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
    
    public static InputViewController inputViewController;
    
    // ================================================================
    // CONSTANTS
    // ================================================================
    private static final String FILE_LOC = "InputView.fxml";
    private static final String INIT_TEST_DATA = "Test data initialized";
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                FILE_LOC));
        loader.setRoot(this);
        loader.setController(this);
        try {
            inputViewController = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initPresetArrayList();
        initHistoryList();
        initCommandList();
    }

    /**
     * Handles key presses for user input bar.
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
        } else if (event.getCode() == KeyCode.UP
                || event.getCode() == KeyCode.DOWN) {
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
    		i = i% commands.size();
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
