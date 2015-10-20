package programGui;

import javafx.fxml.FXML;
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
        initFxmlFields(task.getTaskID(),task.getTaskName(),task.getDate().toString());
    }
    
    public void initFxmlFields (int index, String taskName , String date ) {
        this.index.setText(index + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.date.setText(date);
 
    }
}
