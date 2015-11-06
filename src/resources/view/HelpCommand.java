package resources.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HelpCommand extends HBox {

    @FXML
    private Label command;

    @FXML
    private Label description;

    private static final String FILE_LOC = "HelpCommand.fxml";

    public HelpCommand(String command, String description) {
        loadFxml();
        initFxmlFields(command, description);
    }

    public void initFxmlFields(String command, String description) {
        this.command.setText(command);
        this.description.setText(description);
    }

    private void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(FILE_LOC));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
