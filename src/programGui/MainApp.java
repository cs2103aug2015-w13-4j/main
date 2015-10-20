package programGui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private static final String PROGRAM_TITLE = "PIXEList";
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private VBox inputView;
	private VBox taskDisplay;
	private HBox task;
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		initRoot();
		initTaskView();
		initInputView();
	}
	
	private void initRoot() {
		try { 
		        FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("Root.fxml"));
				//Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));						
				rootLayout = (BorderPane) loader.load();
				primaryStage.setScene(new Scene(rootLayout,850,600));
				primaryStage.setTitle(PROGRAM_TITLE);
				primaryStage.show();
		} catch(IOException E) {
			System.out.print("Error initialising root gui");
		}
	}
	
	private void initTaskView() throws IOException {
		try { 
		    FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("TaskDisplay.fxml"));
			taskDisplay = (VBox) loader.load();
			rootLayout.setCenter(taskDisplay);
			
		} catch (IOException E) {
			System.out.print("Error initialising task display");
		} 
	} 
	
	private void initInputView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("InputView.fxml"));
			inputView = (VBox) loader.load();
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
