package resources.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Help Class creates a a help HBOX object to display the available commands and
 * how to use them.
 * 
 * @author Benjamin
 *
 */

//@@author A0124933H Benjamin
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
