package resources.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//@author A0124933H
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    // ================================================================
    // PROGRAM FIELDS
    // ================================================================

    private static final double PROGRAM_HEIGHT = 660.0;
    private static final double PROGRAM_WIDTH = 880.0;

    private static final String PROGRAM_TITLE = "PIXEList";
    private static final String IMAGE_ICON = "resources/imgs/icon.png";

    // ================================================================
    // METHODS
    // ================================================================
    
    /**
     * Launch PIXEList Program
     * 
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
     * 
     * @param primaryStage
     *            is the stage that the GUI took
     */
    private void initPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image(IMAGE_ICON));
        this.primaryStage.setMinHeight(PROGRAM_HEIGHT);
        this.primaryStage.setMinWidth(PROGRAM_WIDTH);
        this.primaryStage.setTitle(PROGRAM_TITLE);
        assert rootLayout != null;
        this.primaryStage.setScene(new Scene(rootLayout));
        this.primaryStage.show();
    }

    /**
     * Initialising the taskView and update the view should there be any tasks
     * inside.
     */
    private void initTaskView() {
        TaskDisplayController.getInstance();
    }

    /**
     * Initialising the input view which consists of the input bar and feedback
     * label.
     */
    private void initInputView() {
        InputViewController.getInstance();
    }

    // ================================================================
    // GETTERS
    // ================================================================
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
}