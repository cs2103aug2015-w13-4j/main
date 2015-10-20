package programGui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.Launch;

public class InputViewController {
    
    @FXML
    private TextField userInput;
    @FXML
    private Label feedBack;
    
    public void handleKeyPress(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            handleUserInput();
            userInput.setText("");
        }
    }
    
    private void handleUserInput(){
        String input = userInput.getText();
        passToLogic(input);
    }
    
    private void labelFeedBack(String input) {
        feedBack = new Label();
        feedBack.setText(input);
    }
    private void passToLogic(String input) {
        Launch launch = new Launch();
        String output = launch.command(input);
        labelFeedBack(output);
    }
}
