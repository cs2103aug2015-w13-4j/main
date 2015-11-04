package resources.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;
import utilities.TaskTime;

public class Task extends HBox {

    @FXML
    private CheckBox checkBox;

    @FXML
    private Label index;

    @FXML
    private Label sDate;

    @FXML
    private Label eDate;
    
    @FXML
    private Label startTime;
    
    @FXML
    private Label endTime;

    @FXML
    private Label taskName;

    @FXML
    private Label priority;
    
    @FXML
    private Label jointLabel;

    private static final String TASK_FILE = "Task.fxml";
    private static final String TASK_FLAG = "FLAG";

    ChangeListener<Boolean> checkBoxListener;

    private static final String STRING_EMPTY = "";
    private static final String CONNECTING_WORD = "to";

    public Task(TaskEvent task) {
        loadFxml();
        initFxmlFields(task.getTaskID(), task.getTaskName(),
                task.getStartDate(),task.getStartTime(), task.getEndDate(), task.getEndTime(), task.isCompleted(),
                task.getPriority());
    }

    public Task() {
        loadFxml();
        initFxmlFields(1, "test", "high", new TaskDate(11, 11, 2015), false);
        // initFxmlFields(2,"hi","high" , new TaskDate(11,12,2015) , new
        // TaskDate(12,12,2015));
    }

    public void initFxmlFields(int idx, String taskName, TaskDate sDate, TaskTime startTime,
            TaskDate eDate, TaskTime endTime ,  Boolean isCompleted, Command_Priority priority) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.sDate.setText(sDate.toString());
        this.startTime.setText(startTime.toString());
        this.eDate.setText(eDate.toString());
        this.endTime.setText(endTime.toString());
        this.priority.setText(priority.toString().equals(TASK_FLAG) ? TASK_FLAG : STRING_EMPTY);
        if (isCompleted) {
            checkBox.setSelected(true);
        }
        if(startTime.getHour() == 0) {
        	this.startTime.setText(STRING_EMPTY);
        }
        if(endTime.getHour() == 0){
        	this.endTime.setText(STRING_EMPTY);
        }
        
        if (sDate.getDay() == 0 && eDate.getDay() == 0) {
            this.sDate.setText(STRING_EMPTY);
            this.eDate.setText(STRING_EMPTY);
        } else {
            if (sDate.getDay() == 0) {
                this.sDate.setText(STRING_EMPTY);
                this.eDate.setText(eDate.toString());
            }
            if (eDate.getDay() == 0) {
                this.eDate.setText(STRING_EMPTY);
                this.sDate.setText(sDate.toString());
            }
            if(eDate.getDay() != 0 && sDate.getDay() != 0) {
            	this.jointLabel.setText(CONNECTING_WORD);
            }
        }
    }

    public void initFxmlFields(int idx, String taskName, String priority,
            TaskDate eDate, Boolean isCompleted) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.priority.setText(priority);
        if (isCompleted) {
            checkBox.setSelected(true);
        }
        this.sDate.setText(STRING_EMPTY);
        if (eDate.getDay() == 0) {
            this.eDate.setText(STRING_EMPTY);
        } else {
            this.eDate.setText(eDate.toString());
        }
    }

    private void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource(TASK_FILE));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
