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
public class InputViewController extends VBox {
    
    @FXML
    private TextField userInput;
    @FXML
    private Label feedBack;
    
    Launch launch;
    
    ArrayList<String> history;
    private int historyPointer;
    
    public static InputViewController inputViewController;
    
    public static InputViewController getInstance() {
        if (inputViewController == null) {
            inputViewController = new InputViewController();
        }
        return inputViewController;
    }
    
    public InputViewController () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InputView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            inputViewController = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        initHistoryList();
    }
    
    public void handleKeyPress(KeyEvent event) {
        TaskDisplayController taskDisplay = TaskDisplayController.getInstance();
        if(event.getCode() == KeyCode.ENTER && userInput.getText() == "") {
            taskDisplay.updateTaskDisplay();
        } else if(event.getCode() == KeyCode.ENTER) {
        	handleUserInput();
            userInput.setText("");
            taskDisplay.updateTaskDisplay();
        } else if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
            event.consume();
            handleHistoryCommands(event);
        }
        
    }
    
    private void handleHistoryCommands(KeyEvent event) { 
        String pastCmd = getHistoryCommands(event.getCode());
        userInput.setText(pastCmd);
    }
    
    private void handleUserInput(){
        String input = userInput.getText();
        updateHistoryList();
        passToLogic(input);
    }
    
    private void labelFeedBack(String input) {
        feedBack.setText(input);
    }
    private void passToLogic(String input) {
        launch = Launch.getInstance();
        String output = launch.command(input);
        labelFeedBack(output);
    }
    
    private void initHistoryList() {
        history = new ArrayList<String>();
        historyPointer = history.size() - 1;
    }
    
    private void updateHistoryList() {
        historyPointer = history.size();
        history.add(historyPointer - 1, userInput.getText());
    }
    
    private String getHistoryCommands(KeyCode code) {
        if(code == KeyCode.DOWN) { 
            return getNextCommand();
        } else if ( code == KeyCode.UP) {
            return getPrevCommand();
        } else {
            return "";
        }
    }
    
    private String getPrevCommand() {
        if(historyPointer > 0) { 
            historyPointer --;
        }
        return history.get(historyPointer);
    }
    
    private String getNextCommand() {
        if ( historyPointer < history.size() - 1) {
            historyPointer++;
        }
        return history.get(historyPointer);
        }
}
    
