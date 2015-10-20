package programGui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private static final String PROGRAM_TITLE = "PIXEList";
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private VBox inputView;
	private VBox taskView;
	private FXMLLoader loader;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		loader = new FXMLLoader();
		this.primaryStage = primaryStage;
		initRoot();
		//initTaskView();
		initInputView();
	}
	
	private void initRoot() {
		try {
				loader.setLocation(MainApp.class.getResource("Root.fxml"));
				//Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));						
				rootLayout = loader.load();
				primaryStage.setScene(new Scene(rootLayout,850,600));
				primaryStage.setTitle("PIXEList");
				primaryStage.show();
		} catch(IOException E) {
			System.out.print("Error initialising root gui");
		}
	}
	
	/*private void initTaskView() throws IOException {
		try { 
			loader.setLocation(MainApp.class.getResource("TaskView.fxml"));
			taskView = (VBox)loader.load();
			rootLayout.setCenter(taskView);
			
		} catch (IOException E) {
			System.out.print("Error initialising task view");
		} 
	} */
	
	private void initInputView() {
		try {
			FXMLLoader load = new FXMLLoader();
			load.setLocation(MainApp.class.getResource("InputView.fxml"));
			inputView = (VBox) load.load();
			rootLayout.setBottom(inputView);
		} catch (IOException E) {
			System.out.print("Error initialising input view");
		}
	}
	
	//Getters
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	
	}
