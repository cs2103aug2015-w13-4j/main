package resources.view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import utilities.TaskTime;

//@A0124933H
public class Task extends HBox {

    // ================================================================
    // FXML FIELDS
    // ================================================================

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
    private FontAwesomeIconView taskFlag;

    @FXML
    private Label jointLabel;

    // ================================================================
    // CONSTANTS
    // ================================================================

    private static final String FILE_LOC = "Task.fxml";
    private static final String TASK_FLAG = "FLAG";

    private static final String STRING_EMPTY = "";

    private static final String CONNECTING_WORD = "to";

    private static final String TASK_COMPLETE = "finish ";
    private static final String TASK_UNCOMPLETE ="unfinish ";

    // ================================================================
    // CONSTRUCTOR
    // ================================================================

    public Task(TaskEvent task, int index) {
        loadFxml();
        checkBox.setSelected(task.isCompleted());
        initListenerAndFxmlFields(task,index);
    }

    // ================================================================
    // INITIALIZATION METHOD
    // ================================================================

    private void initListenerAndFxmlFields (TaskEvent task,int index) {
        ChangeListener<Boolean> checkboxListener = initCheckBoxListener(index);
        initFxmlFields(index, task.getTaskName(),
                task.getStartDate(),task.getStartTime(), task.getEndDate(), task.getEndTime(), task.isCompleted(),
                task.getPriority() , checkboxListener);
    }

    private ChangeListener<Boolean> initCheckBoxListener(int index) {
        ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
                InputViewController inputView = InputViewController.getInstance();
                if (newVal) {
                    inputView.passToLogic(TASK_COMPLETE + index);
                } else {
                    inputView.passToLogic(TASK_UNCOMPLETE + index);
                }
            }
        };           
        return listener;
    }

    private void initFxmlFields(int idx, String taskName, TaskDate sDate, TaskTime startTime,
            TaskDate eDate, TaskTime endTime ,  Boolean isCompleted, Command_Priority priority, ChangeListener<Boolean> checkboxListener) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.sDate.setText(sDate.toString());
        this.startTime.setText(startTime.toString());
        this.eDate.setText(eDate.toString());
        this.endTime.setText(endTime.toString());
        this.taskFlag.setVisible(priority.toString().equals(TASK_FLAG) ? true : false);
        this.checkBox.selectedProperty().addListener(checkboxListener);
        dateAndTimeConfig(sDate, startTime, eDate, endTime);
    }

    private void dateAndTimeConfig(TaskDate sDate, TaskTime startTime,
            TaskDate eDate, TaskTime endTime) {
        if(startTime.getHour() == 0) {
            this.startTime.setText(STRING_EMPTY);
        }
        if(endTime.getHour() == 0){
            this.endTime.setText(STRING_EMPTY);
        }

        if(startTime.getHour() != 0 && endTime.getHour()!= 0) {
            this.jointLabel.setText(CONNECTING_WORD);
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
