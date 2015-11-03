package resources.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class RootController extends BorderPane {

    @FXML
    private TextField input;

    private TaskDisplayController taskDisplayController;


    // Constructors
    public RootController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Root.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
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
        InputViewController.getInstance();
    }

}
