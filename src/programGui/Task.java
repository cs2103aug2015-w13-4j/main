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
    
    @FXML
    private Label priority;
    
    private static final String STRING_EMPTY = "";
    
    
    public Task(TaskEvent task) {
        loadFxml();
        initFxmlFields(task.getTaskID(),task.getTaskName(),task.getEndDate().toString()  , "High");
    }
    
    public Task() {
        loadFxml();
        initFxmlFields(1,"test","10/10/2015" , "High");
    }
    
    public void initFxmlFields (int idx, String taskName , String date, String priority) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.date.setText(date);
        this.priority.setText(priority);
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
    