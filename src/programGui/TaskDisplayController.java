package programGui;

import java.util.ArrayList;

import utilities.TaskEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import logic.Display;

public class TaskDisplayController {

    @FXML
    private ListView<HBox> listView;
    
    public void updateTaskDisplay(ObservableList<TaskEvent> tasks) {
        ObservableList<HBox> displayTasks = FXCollections.observableArrayList();
        displayTasks = getTask();
        listView.setItems(displayTasks);
    }
    
    public ObservableList<HBox> getTask() {
        Display display = new Display();
        ObservableList<HBox> tasks = FXCollections.observableArrayList();
        ArrayList<TaskEvent> taskList = display.taskView();
        for(TaskEvent t : taskList) {
           tasks.add(new Task(t));
        }
        return tasks;
    } 
}
