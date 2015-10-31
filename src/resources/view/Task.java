package resources.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Label sDate;
    
    @FXML
    private Label eDate;
    
    @FXML
    private Label taskName;
    
    @FXML
    private Label priority;
    
    
    ChangeListener<Boolean> checkBoxListener;
    
    private static final String STRING_EMPTY = "";
    
    

   public Task(TaskEvent task) {
        loadFxml();
        initListener();
        initFxmlFields(task.getTaskID(),task.getTaskName(),"high",task.getStartDate()  , task.getEndDate());
    } 
    
    public Task() {
        loadFxml();
        initFxmlFields(1,"test","high" , new TaskDate(11,11,2015));
        initFxmlFields(2,"hi","high" , new TaskDate(11,12,2015) , new TaskDate(12,12,2015));
    }
    
    public void initFxmlFields (int idx, String taskName ,String priority , TaskDate sDate, TaskDate eDate) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.priority.setText(priority);
        this.checkBox.selectedProperty().addListener(checkBoxListener);
        if(sDate.getDay() == 0 || eDate.getDay() == 0) {
        	this.sDate.setText("");
        } else {
        this.sDate.setText(sDate.toString());
        this.eDate.setText(eDate.toString());
        }
    }
    
    public void initFxmlFields (int idx, String taskName ,String priority , TaskDate eDate) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.priority.setText(priority);
        this.checkBox.selectedProperty().addListener(checkBoxListener);
        if(eDate.getDay() == 0) {
            this.eDate.setText("");
        } else {
        this.eDate.setText(eDate.toString());
        }
    }
    
    public void initListener() {
        checkBoxListener = new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
                
            }
        };
        
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
    