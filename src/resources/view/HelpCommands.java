package resources.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class HelpCommands extends HBox{
    
    @FXML
    private Label commmand;
    
    @FXML
    private Label description;
    
    private static final String TASK_FILE = "HelpCommands.fxml";
    
    public HelpCommands(String command, String description) {
        loadFxml();
        initFxmlFields(command, description);
    }
    
    public void initFxmlFields(String command, String description) {
        this.commmand.setText(command);
        this.description.setText(description);
    }
    
    private void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(TASK_FILE));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
