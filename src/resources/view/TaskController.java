package resources.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utilities.TaskDate;
import utilities.TaskEvent;


public class TaskController extends HBox{

    @FXML
    private CheckBox checkBox;
    
    @FXML
    private Label index;
    
    @FXML
    private Label sDate;
    
    @FXML
    private Label eDate;
    
    @FXML
    private Label taskName;
    
    @FXML
    private Label priority;
    
    private static final String STRING_EMPTY = "";
    
    
    public TaskController(TaskEvent task) {
        loadFxml();
        initFxmlFields(task.getTaskID(),task.getTaskName(),"high",task.getStartDate().toString()  , task.getEndDate().toString());
    }
    
    public TaskController() {
        loadFxml();
        initFxmlFields(1,"test","high" , "10/10/2015" , "11/11/2015");
    }
    
    public void initFxmlFields (int idx, String taskName ,String priority ,  String sDate, String eDate) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.priority.setText(priority);
        this.sDate.setText(sDate);
        this.eDate.setText(eDate);
    }
    
    private void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    