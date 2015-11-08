package resources.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

//@@A0124933H Benjamin
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

    // ================================================================
    // CONSTRUCTORS
    // ================================================================

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

    // ================================================================
    // PRIVATE METHODS
    // ================================================================

    private void initTaskDisplay() {
        this.taskDisplayController = TaskDisplayController.getInstance();
        this.setCenter(taskDisplayController);
    }

    private void initInputView() {
        InputViewController.getInstance();
    }
}
