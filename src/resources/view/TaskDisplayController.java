package resources.view;

import java.io.IOException;
import java.util.ArrayList;






import utilities.Command_Priority;
import utilities.TaskDate;
import utilities.TaskEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.Display;
import resources.view.Task;
import logic.Launch;

public class TaskDisplayController extends StackPane{

    @FXML
    private ListView<HBox> generalView;
    
    @FXML
    private ListView<HBox> flagView;
    
    @FXML
    private ListView<HBox> floatingView;
    
    @FXML
    private ListView<HBox> resultContent;
    
    @FXML
    private ListView<HBox> helpContent;
    
    @FXML
    private BorderPane viewBorderPane;
    
    @FXML
    private Label flagTaskLabel;
    
    @FXML
    private Label generalTaskLabel;
    
    @FXML
    private Label floatingTaskLabel;
    
    @FXML
    private VBox helpView;
    
    @FXML
    private VBox resultView;
    
    @FXML
    private BorderPane borderPane;
    
    private static TaskDisplayController taskDisplayController;
    
    private static final String MESSAGE_TODAY = "Today";
    private static final String MESSAGE_TOMORROW = "Tomorrow";
    private static final String MESSAGE_THIS_WEEK = "This week";
    
    private static final String HELP_DESC_ADD  = "Add a task?";
    private static final String HELP_DESC_EDIT = "Edit a task?";
    private static final String HELP_DESC_DELETE = "Delete a task?";
    private static final String HELP_DESC_UNDO = "To undo";
    private static final String HELP_DESC_REDO = "To redo";
    private static final String HELP_DESC_SEARCH = "To search a task ";
    private static final String HELP_DESC_FINISH = "Mark a task as finished";
    private static final String HELP_DESC_FLAG = "Flag a task as important";
    private static final String HELP_DESC_UNFLAG = "Unflag a task";
    private static final String HELP_DESC_VIEW_COMPLETED = "To view completed tasks";
    private static final String HELP_DESC_CHANGE_DIR = "To change the file directory";
    private static final String HELP_DESC_FIELD = "Fields available";
    private static final String HELP_DESC_EXIT = "To Exit PIXEList";
    
    private static final String HELP_COMMAND_ADD = "add \"Task Name\" <StartDate> <EndDate> <Flag?>";
    private static final String HELP_COMMAND_EDIT = "edit <id> <field> \"Name Changes here.\"<Date>";
    private static final String HELP_COMMAND_DELETE = "delete <id>";
    private static final String HELP_COMMAND_UNDO = "undo";
    private static final String HELP_COMMAND_REDO = "redo";
    private static final String HELP_COMMAND_SEARCH = "search \"Search keyword\"";
    private static final String HELP_COMMAND_FINISH = "finish <id>";
    private static final String HELP_COMMAND_FLAG = "flag <id>";
    private static final String HELP_COMMAND_UNFLAG = "unflag <id>";
    private static final String HELP_COMMAND_VIEW_COMPLETED = "view completed";
    private static final String HELP_COMMAND_CHANGE_DIR = "cd";
    private static final String HELP_COMMAND_EXIT = "exit";
    private static final String HELP_COMMAND_FIELD = "startDate , endDate , name";
    
    
    private Launch launch;
    private Display display;
    
    private boolean enableHelpView;
    private boolean enableResultView;
    
    public static TaskDisplayController getInstance() {
        if (taskDisplayController == null) {
            taskDisplayController = new TaskDisplayController();
        }
        return taskDisplayController;
    }
    
    private TaskDisplayController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskDisplayNew.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewBorderPane.setBottom(InputViewController.getInstance());
        initHelpTaskDisplay();
        updateViews();
    }
    
    
    
    public void updateViews() {
        refreshAllViews();
        launch.getInstance();
        display = launch.getDisplay();
        updateGeneralTaskDisplay();
        updateFlaggedTaskDisplay();
        updateFloatingTaskDisplay();
        updateResultTaskDisplay();
    }
    
    private void updateGeneralTaskDisplay() {
        ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
        displayTasks = getTask();
        generalView.setItems(displayTasks);
        generalTaskLabel.setText("General Tasks - " + displayTasks.size() + " tasks displayed");
    }

    private void updateFlaggedTaskDisplay() {
        ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
        displayTasks = getFlaggedTask();
        flagView.setItems(displayTasks);
        flagTaskLabel.setText("Flagged Tasks - " + displayTasks.size() + " tasks displayed");
    }

    private void updateFloatingTaskDisplay() {
        ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
        displayTasks = getFloatingTask();
        floatingView.setItems(displayTasks);
        floatingTaskLabel.setText("Floating Tasks - " + displayTasks.size() + " tasks displayed");
    }

    public void updateResultTaskDisplay() {
        ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
        displayTasks = getResultedTask();
        resultContent.setItems(displayTasks);
    }
    
    private void initHelpTaskDisplay() {
        ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
        displayTasks = initHelpCommands();
        helpContent.setItems(displayTasks);
    }
    private ObservableList<HBox> getTask() {
        
        ObservableList<HBox> tasks = FXCollections.observableArrayList(); 
       ArrayList<TaskEvent> taskList = display.taskView();
        for(TaskEvent t : taskList) {
            if(t.getEndDate().getDay() != 0 || t.getStartDate().getDay() != 0) {
           tasks.add(new Task(t));
            }
        } 
        //tasks.add(new Task(new TaskEvent(1, "Do CS2101", new TaskDate(2015,10,10), new TaskDate(2015,10,15), 1)));
      //  tasks.add(new Task());
        return tasks;
    } 
    
    private ObservableList<HBox> getFloatingTask() {
        
        ObservableList<HBox> tasks = FXCollections.observableArrayList(); 
        ArrayList<TaskEvent> floatingTaskList = display.taskView();
         for(TaskEvent t : floatingTaskList) {
             if(t.getEndDate().getDay() == 0 && t.getStartDate().getDay() == 0) {
            tasks.add(new Task(t));
             }
         } 
         return tasks;
    }
    
    private ObservableList<HBox> getFlaggedTask() {
        
        ObservableList<HBox> tasks = FXCollections.observableArrayList(); 
        ArrayList<TaskEvent> flaggedTaskList = display.taskView();
         for(TaskEvent t : flaggedTaskList) {
             if((t.getEndDate().getDay() != 0 || t.getStartDate().getDay() != 0 )&& (t.getPriority()).toString().equals("FLAG")) {
            tasks.add(new Task(t));
             }
         } 
         return tasks;
    }
    
    private ObservableList<HBox> getResultedTask() { 
        
        ObservableList<HBox> tasks = FXCollections.observableArrayList();
        ArrayList<TaskEvent> searchedTaskList = display.resultView();
        for(TaskEvent t : searchedTaskList) {
            tasks.add(new Task(t));
        }
        return tasks;
    }
    
    private ObservableList<HBox> initHelpCommands() {
        ObservableList<HBox> tasks = FXCollections.observableArrayList();
        tasks.add(new HelpCommand(HELP_COMMAND_ADD, HELP_DESC_ADD));
        tasks.add(new HelpCommand(HELP_COMMAND_EDIT, HELP_DESC_EDIT));
        tasks.add(new HelpCommand(HELP_COMMAND_DELETE, HELP_DESC_DELETE));
        tasks.add(new HelpCommand(HELP_COMMAND_UNDO, HELP_DESC_UNDO));
        tasks.add(new HelpCommand(HELP_COMMAND_REDO, HELP_DESC_REDO));
        tasks.add(new HelpCommand(HELP_COMMAND_SEARCH, HELP_DESC_SEARCH));
        tasks.add(new HelpCommand(HELP_COMMAND_FINISH, HELP_DESC_FINISH));
        tasks.add(new HelpCommand(HELP_COMMAND_FLAG, HELP_DESC_FLAG));
        tasks.add(new HelpCommand(HELP_COMMAND_UNFLAG, HELP_DESC_UNFLAG));
        tasks.add(new HelpCommand(HELP_COMMAND_VIEW_COMPLETED, HELP_DESC_VIEW_COMPLETED));
        tasks.add(new HelpCommand(HELP_COMMAND_CHANGE_DIR, HELP_DESC_CHANGE_DIR));
        tasks.add(new HelpCommand(HELP_COMMAND_FIELD, HELP_DESC_FIELD));
        tasks.add(new HelpCommand(HELP_COMMAND_EXIT, HELP_DESC_EXIT));
        
        return tasks;
    }
    public void triggerHelpView() {
        this.enableHelpView = true;
    }
    
    public void triggerResultView() {
        this.enableResultView = true;
    }
    public void showHelpView() {
        helpView.toFront();
        helpView.setOpacity(1);
        borderPane.setOpacity(0.35);
    }
    public void showResultView() {
        resultView.toFront();
        resultView.setOpacity(1);
        borderPane.setOpacity(0.35);
    }
    
    private void hideHelpView() {
        helpView.toBack();
        helpView.setOpacity(0);
        this.enableHelpView = false;
    }
    
    private void hideResultView() {
        helpView.toBack();
        resultView.setOpacity(0);
        this.enableResultView = false;
    }
    
    public void hideViews(){
        if ( enableHelpView == false && enableResultView == false) {
            hideAllOverlays();
        }
    }
    public void hideAllOverlays() {
        borderPane.toFront();
        borderPane.setOpacity(1);
        hideHelpView();
        hideResultView();
    }
    public void initOverlaySettings() {
        enableHelpView = false;
        enableResultView = false;
    }
    
    public void refreshHelpOverlay() {
        if ( enableHelpView == true ){
            showHelpView();
        } else {
            hideHelpView();
        }
    }

    public void refreshResultOverlay() {
        if(enableResultView == true) {
            showResultView();
        } else {
            hideResultView();
        }
    }
    
    public void refreshAllViews() {
        refreshResultOverlay();
        refreshHelpOverlay();
        hideViews();
    }
}
