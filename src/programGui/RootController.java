package programGui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class RootController extends BorderPane{

	private TextField input;

    private TaskDisplayController taskDisplayController;

	
    //Prompt Text
    private static final String PROMPT_TEXT = "Enter format: taskname ; date ; priority ; task description";
    

    //Constructors
    public RootController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/programGui/Root.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try {
            loader.load();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    
    private void initTaskDisplay() {
        this.taskDisplayController = TaskDisplayController.getInstance();
        this.setCenter(taskDisplayController);
    }
}
