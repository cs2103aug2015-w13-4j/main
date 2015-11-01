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
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.Display;
import resources.view.Task;
import logic.Launch;

public class TaskDisplayController extends VBox{

    @FXML
    private ListView<HBox> listView;
    
    private static TaskDisplayController taskDisplayController;
    
    private static final String MESSAGE_TODAY = "Today";
    private static final String MESSAGE_TOMORROW = "Tomorrow";
    private static final String MESSAGE_THIS_WEEK = "This week";
    
    
    private Launch launch;
    private Display display;
    
    public static TaskDisplayController getInstance() {
        if (taskDisplayController == null) {
            taskDisplayController = new TaskDisplayController();
        }
        return taskDisplayController;
    }
    
    private TaskDisplayController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskDisplay.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public void updateTaskDisplay() {
        ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
        displayTasks = getTask();
        if (displayTasks == null ) {
        	System.out.print("ERROR in DISPLAYTASK\n");
        }
        if(listView == null){
        	System.out.print("LISTVIEW IS NOT INITIAITED\n");
        }
        listView.setItems(displayTasks);
    }
    
    public ObservableList<HBox> getTask() {
    	launch = Launch.getInstance();
    	display = launch.getDisplay();
        
        ObservableList<HBox> tasks = FXCollections.observableArrayList(); 
       ArrayList<TaskEvent> taskList = display.taskView();
        for(TaskEvent t : taskList) {
           tasks.add(new Task(t));
        } 
        //tasks.add(new Task(new TaskEvent(1, "Do CS2101", new TaskDate(2015,10,10), new TaskDate(2015,10,15), 1)));
      //  tasks.add(new Task());
        return tasks;
    } 
    
    public ObservableList<HBox> getSTask() {
        launch = Launch.getInstance();
        display = launch.getDisplay();
        
        ObservableList<HBox> tasks = FXCollections.observableArrayList(); 
        ArrayList<TaskEvent> searchTaskList = display.taskView();
         for(TaskEvent t : searchTaskList) {
            tasks.add(new Task(t));
         } 
         return tasks;
    }
    
    public void updateSearchTaskDisplay() {
        ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
        displayTasks = getSTask();
        if (displayTasks == null ) {
            System.out.print("ERROR in DISPLAYTASK\n");
        }
        if(listView == null){
            System.out.print("LISTVIEW IS NOT INITIAITED\n");
        }
        listView.setItems(displayTasks);
    }
}
