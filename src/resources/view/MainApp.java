package resources.view;


import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private VBox inputView;
	private VBox taskDisplay;
	private HBox task;
	
	/* PROGRAM DETAILS */
	private static final double PROGRAM_HEIGHT = 600.0;
	private static final double PROGRAM_WIDTH = 550.0;
	
	private static final String PROGRAM_TITLE = "PIXEList";
	
	/**
	 * Launch PIXEList Program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	} 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	    initRoot();
		initTaskView();
		initInputView();
		initPrimaryStage(primaryStage);
	}
	
	/**
	 * Initialising GUI's Root layout
	 */
	private void initRoot() {
	    rootLayout = new RootController();
	}
	
	/**
	 * Setting up stage for GUI.
	 * @param primaryStage
	 * 			is the stage that the GUI took
	 */
	private void initPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setMinHeight(PROGRAM_HEIGHT);
		this.primaryStage.setMinWidth(PROGRAM_WIDTH);
		this.primaryStage.setTitle(PROGRAM_TITLE);
		this.primaryStage.setScene(new Scene(rootLayout));
		this.primaryStage.show();	
	}
	
	/**
	 * Initialising the taskView and update the view should there be any tasks inside.
	 */
	private void initTaskView () {
	    taskDisplay = TaskDisplayController.getInstance();
	    ((TaskDisplayController) taskDisplay).updateTaskDisplay();   
	}
	
	/**
	 * Initialising the input view which consists of the input bar and feedback label.
	 */
	private void initInputView() {
	    InputViewController.getInstance();
	}
	
	//Getters
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	}