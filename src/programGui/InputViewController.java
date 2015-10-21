package programGui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import logic.Launch;
import programGui.TaskDisplayController;
public class InputViewController extends VBox {
    
    @FXML
    private TextField userInput;
    @FXML
    private Label feedBack;
    
    Launch launch;
    
    public static InputViewController inputViewController;
    
    public static InputViewController getInstance() {
        if (inputViewController == null) {
            inputViewController = new InputViewController();
        }
        return inputViewController;
    }
    
    public InputViewController () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/programGui/InputView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            inputViewController = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void handleKeyPress(KeyEvent event) {
        TaskDisplayController taskDisplay = TaskDisplayController.getInstance();
        if(event.getCode() == KeyCode.ENTER) {
        	handleUserInput();
            userInput.setText("");
            taskDisplay.updateTaskDisplay();
        }
    }
    
    private void handleUserInput(){
        String input = userInput.getText();
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
}
