package resources.view;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class RootController extends BorderPane{

    @FXML
	private TextField input;

    private TaskDisplayController taskDisplayController;
    private InputViewController inputViewController;

	
    //Prompt Text
    private static final String PROMPT_TEXT = "Enter format: taskname ; date ; priority ; task description";
    

    //Constructors
    public RootController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Root.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        
        initTaskDisplay();
        initInputView();
    }
    
    private void initTaskDisplay() {
        this.taskDisplayController = TaskDisplayController.getInstance();
        this.setCenter(taskDisplayController);
    }
    
    private void initInputView() {
        this.inputViewController = InputViewController.getInstance();
        this.setBottom(inputViewController);
    }
    
    
}
