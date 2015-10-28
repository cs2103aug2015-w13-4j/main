package resources.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.ArrayList;

import logic.Display;
import logic.Launch;
import utilities.TaskEvent;
public class Controller {

	private static Controller controller;
	
	private ArrayList<TaskEvent> allTask;
	private ObservableList<TaskEvent> tasks;
	private Launch launch;
	private Display display;
	
	private boolean switchDisplayToSearch = false;
	private Stage stage;
	
	private Controller() {
		display = new Display();
		launch = Launch.getInstance();
		allTask = display.taskView();
	}
	//Singleton
	public static Controller getInstance() {
		if(controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	public void passCommand (String input) {
		launch.command(input);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	private void loadTask() {
		for(TaskEvent task : allTask) {
			tasks.add(task);
		}
	}
	
	//Getters
	public ObservableList<TaskEvent> getTasks() {
		return tasks;
	}
	
	
}
