package resources.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;


public class Task extends HBox{

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
    
    private static final String TASK_FILE = "Task.fxml";    
    
    ChangeListener<Boolean> checkBoxListener;
    
    private static final String STRING_EMPTY = "";
    
    

   public Task(TaskEvent task) {
        loadFxml();
        initListener();
        initFxmlFields(task.getTaskID(),task.getTaskName(),task.getStartDate()  , task.getEndDate() , task.isCompleted() , task.getPriority() );
    } 
    
    public Task() {
        loadFxml();
        initListener();
        initFxmlFields(1,"test","high" , new TaskDate(11,11,2015) , false);
        //initFxmlFields(2,"hi","high" , new TaskDate(11,12,2015) , new TaskDate(12,12,2015));
    }
    
    public void initFxmlFields (int idx, String taskName , TaskDate sDate, TaskDate eDate , Boolean isCompleted , Command_Priority priority) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.sDate.setText(sDate.toString());
        this.eDate.setText(eDate.toString());
        this.priority.setText(priority.toString().equals("FLAG") ? "FLAG" : "");
        if(isCompleted) {
            checkBox.setSelected(true);
        }
        if(sDate.getDay() == 0 && eDate.getDay() == 0 ) {
            this.sDate.setText(STRING_EMPTY);
            this.eDate.setText(STRING_EMPTY);
        }
        else {
        if(sDate.getDay() == 0 ){
        	this.sDate.setText(STRING_EMPTY);
            this.eDate.setText(eDate.toString());
        }
        if(eDate.getDay() == 0 ) { 
            this.eDate.setText(STRING_EMPTY);
            this.sDate.setText(sDate.toString());
        }
        }
    }
    
    public void initFxmlFields (int idx, String taskName ,String priority , TaskDate eDate,Boolean isCompleted) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.priority.setText(priority);
        if(isCompleted) { 
            checkBox.setSelected(true);
        }
        this.sDate.setText(STRING_EMPTY);
        if(eDate.getDay() == 0) {
            this.eDate.setText(STRING_EMPTY);
        } else {
        this.eDate.setText(eDate.toString());
        }
    }
    
    public void initListener() {
        checkBoxListener = new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
                System.out.print("Hi");
            }
        };
        
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
    