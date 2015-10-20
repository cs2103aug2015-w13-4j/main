package programGui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utilities.TaskDate;
import utilities.TaskEvent;


public class Task extends HBox{

    @FXML
    private CheckBox checkBox;
    
    @FXML
    private Label index;
    
    @FXML
    private Label date;
    
    @FXML
    private Label taskName;
    
    private static final String STRING_EMPTY = "";
    
    
    public Task(TaskEvent task) {
        loadFxml();
        initFxmlFields(1,task.getTaskName(),task.getStartDate().toString());
    }
    
    public Task() {
        loadFxml();
        initFxmlFields(1,"h","l");
    }
    
    public void initFxmlFields (int idx, String taskName , String date ) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.date.setText(date);
    }
    
    private void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/programGui/Task.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    