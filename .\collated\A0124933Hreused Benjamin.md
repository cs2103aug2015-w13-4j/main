# A0124933Hreused Benjamin
###### ./src/resources/view/Task.java
``` java
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
    private static final String TASK_UNCOMPLETE = "unfinish ";

    // ================================================================
    // CONSTRUCTOR
    // ================================================================

    public Task(TaskEvent task, int index) {
        loadFxml();
        checkBox.setSelected(task.isCompleted());
        initListenerAndFxmlFields(task, index);
    }

    // ================================================================
    // INITIALIZATION METHOD
    // ================================================================

    /**
     * Intialize listeners and fxml label fields.
     * 
     * @param task
     * @param index
     */
    private void initListenerAndFxmlFields(TaskEvent task, int index) {
        ChangeListener<Boolean> checkboxListener = initCheckBoxListener(index);
        initFxmlFields(index, task.getTaskName(), task.getStartDate(),
                task.getStartTime(), task.getEndDate(), task.getEndTime(),
                task.isCompleted(), task.getPriority(), checkboxListener);
    }

    /**
     * Initialize listeners for the task to record and carry out an action when
     * a task is ticked or unticked.
     * 
     * @param index
     * @return
     */
    private ChangeListener<Boolean> initCheckBoxListener(int index) {
        ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean oldVal, Boolean newVal) {
                InputViewController inputView = InputViewController
                        .getInstance();
                if (newVal) {
                    inputView.passToLogic(TASK_COMPLETE + index);
                } else {
                    inputView.passToLogic(TASK_UNCOMPLETE + index);
                }
            }
        };
        return listener;
    }

    /**
     * Initialize fields for fxml label to display.
     * 
     * @param idx
     *            is the id of the task.
     * @param taskName
     *            is the name of the task.
     * @param sDate
     *            is the starting date of the task.
     * @param startTime
     *            is the starting time of the task.
     * @param eDate
     *            is the ending date of the task.
     * @param endTime
     *            is the ending time of the task.
     * @param isCompleted
     *            is whether the task is completed or not.
     * @param priority
     *            is whether the task is flagged or not.
     * @param checkboxListener
     *            is the listener to check whether the checkbox is ticked or
     *            not.
     */
    private void initFxmlFields(int idx, String taskName, TaskDate sDate,
            TaskTime startTime, TaskDate eDate, TaskTime endTime,
            Boolean isCompleted, Command_Priority priority,
            ChangeListener<Boolean> checkboxListener) {
        this.index.setText(idx + STRING_EMPTY);
        this.taskName.setText(taskName);
        this.sDate.setText(sDate.toString());
        this.startTime.setText(startTime.toString());
        this.eDate.setText(eDate.toString());
        this.endTime.setText(endTime.toString());
        this.taskFlag.setVisible(priority.toString().equals(TASK_FLAG) ? true
                : false);
        this.checkBox.selectedProperty().addListener(checkboxListener);
        dateAndTimeConfig(sDate, startTime, eDate, endTime);
    }

    /**
     * Checking of the time and date for proper display.
     * 
     * @param sDate
     *            is the starting date of the task.
     * @param startTime
     *            is the starting time of the task.
     * @param eDate
     *            is the ending date of the task.
     * @param endTime
     *            is the ending time of the task.
     */
    private void dateAndTimeConfig(TaskDate sDate, TaskTime startTime,
            TaskDate eDate, TaskTime endTime) {
        if (startTime.getHour() == 0) {
            this.startTime.setText(STRING_EMPTY);
        }
        if (endTime.getHour() == 0) {
            this.endTime.setText(STRING_EMPTY);
        }

        if (startTime.getHour() != 0 && endTime.getHour() != 0) {
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
            if (eDate.getDay() != 0 && sDate.getDay() != 0) {
                this.jointLabel.setText(CONNECTING_WORD);
            }
        }
    }

    private void loadFxml() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FILE_LOC));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
