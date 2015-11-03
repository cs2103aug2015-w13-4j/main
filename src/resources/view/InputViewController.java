package resources.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    TaskDisplayController taskDisplay;
    
    ArrayList<String> history;
    ArrayList<String> preset;
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
        initPresetArrayList();
        initHistoryList();
    }
    
    public void handleKeyPress(KeyEvent event) {
        taskDisplay = TaskDisplayController.getInstance();
        if(event.getCode() == KeyCode.ENTER) {
            taskDisplay.hideAllOverlays();
            handleUserInput();
            userInput.setText("");
            taskDisplay.updateViews();
        } else if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
            event.consume();
            handleHistoryCommands(event);
        } else if( event.getCode() == KeyCode.F1 ) {
            taskDisplay.hideAllOverlays();
            event.consume();
            initTesting();
            taskDisplay.updateViews();
        } else if ( event.getCode() == KeyCode.ESCAPE) {
            taskDisplay.hideAllOverlays();
        }
    }
    
    private void handleHistoryCommands(KeyEvent event) { 
        String pastCmd = getHistoryCommands(event.getCode());
        userInput.setText(pastCmd);
    }
    
    private void handleUserInput(){
        String input = userInput.getText();
        if ( input.equals("exit")){
            System.exit(0);
        }
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
    private void initTesting() {
        launch = Launch.getInstance();
        for(String in : preset){
            launch.command(in);
        }
        labelFeedBack("Initialized test datas");
    }
    
    private void initHistoryList() {
        history = new ArrayList<String>();
        history.add("");
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
    
    private void initPresetArrayList() {
        preset = new ArrayList<String>();
        preset.add("add \"Oral Presentation for CS2101\" 2/11/2015 FLAG");
        preset.add("add \"Meeting about budget\" 5/11/2015 FLAG");
        preset.add("add \"CS2101 Report writing\" 10/11/2015");
        preset.add("add \"Workshop about Creative Design\" 4/11/2015 6/11/2015");
        preset.add("add \"CS2103 Milestone 4\" 4/11/2015 "); 
        preset.add("add \"Meeting with HR\" 9/11/2015 FLAG");
        
    }
}
    
