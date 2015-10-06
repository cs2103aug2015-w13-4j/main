package programGui;

import utilities.TaskEvent;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

    //Titles
    private static final String TITLE_WARNING = "Warning ";
    
    //Warning Message
    private static final String WARNING_EXIT = "Are you sure you want to exit?";
    private static final String WARNING_DELETE = "Are you sure you want to delete the task?";
    
    //Confirmation Message
    private static final String MSG_CONFIRM = "Yes";
    private static final String MSG_DENY = "No";
    
    //Button Names
    private static final String BUTTON_EXIT = "exit";
    private static final String BUTTON_OPEN = "open";
    
    Stage window;
    Button button;
    Button confirmBtn;
    TableView<TaskEvent> eventTable;
    
    public static void main(String[] args) {
        launch(args);
    }
   
    @Override
    public void start(Stage primaryStage) throws Exception {
            window = primaryStage;
            
            Label label = new Label("All task view");
            BorderPane root = new BorderPane();

            Scene scene = new Scene(root,450,600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
             
            window.setOnCloseRequest(e->  {
            e.consume();    
            exitProgram(); 
            });

            window.setTitle("PIXEList");
            window.setScene(scene);
            window.show();
            button = new Button();
            button.setText("Exit");
            button.setOnAction(e -> AlertBox.display(TITLE_WARNING, WARNING_EXIT));
            confirmBtn = new Button("Exit");
            confirmBtn.setOnAction(e-> exitProgram() );
            
            TextField instrInput = new TextField();
            instrInput.setPromptText("instr ; taskname ; date ; priority");
            instrInput.getText();
            //Pass to parser.
               
            
            //Table
            TableColumn<TaskEvent,>
            root.setTop(confirmBtn);
            root.setBottom(instrInput);
            


        } 
 
    private void exitProgram() {
        // Save File //
        
        Boolean ans = ConfirmationBox.display(TITLE_WARNING, WARNING_EXIT);
        if(ans) {
        window.close();
        }
    }
    
    protected VBox createVBox () {
        VBox leftMenu = new VBox();
        Button button1 = new Button("File");
        Button button2 = new Button("Edit");
        Button button3 = new Button("View");
        leftMenu.getChildren().addAll(button1,button2,button3);
        
        return leftMenu;
    }
    
    public ObservableList<TaskEvent> getProduct() {
        ObservableList<TaskEvent> tasks = FXCollections.observableArrayList();
        tasks.get();
        
        return tasks;
    }
    }
}
