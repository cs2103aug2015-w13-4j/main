package programGui;

import java.util.ArrayList;
import java.util.List;

import utilities.TaskDate;
import utilities.TaskEvent;
import logic.Display;
import logic.Operation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

    //Titles
    private static final String TITLE_WARNING = "Warning ";
    private static final String TITLE_ALERT = "Alert";
    
    //Warning Message
    private static final String WARNING_EXIT = "Are you sure you want to exit?";
    private static final String WARNING_DELETE = "Are you sure you want to delete the task?";
    
    //Confirmation Message
    private static final String MSG_CONFIRM = "Yes";
    private static final String MSG_DENY = "No";
    
    //Button Names
    private static final String BUTTON_EXIT = "exit";
    private static final String BUTTON_OPEN = "open";
    
    //Clearing TextField
    private static final String CLEAR_FIELD = "";
    
    //Prompt Text
    private static final String PROMPT_TEXT = "instr ; taskname ; date ; priority ; task description";
   
    Stage window;
    Button button;
    Button confirmBtn;
    TableView<TaskEvent> eventTable;
    
    public static void main(String[] args) {
        launch(args);
    }
   
    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) throws Exception {
            window = primaryStage;
            
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,850,600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            window.setOnCloseRequest(e->  {
            e.consume();    
            exitProgram(); 
            });

            window.setTitle("PIXEList");
            window.setScene(scene);
            window.show();
            
            confirmBtn = new Button("Exit");
            confirmBtn.setOnAction(e-> exitProgram() );
            
            TextField instrInput = new TextField();
            instrInput.setPromptText(PROMPT_TEXT);
            instrInput.setOnKeyPressed(e-> { 
                if(e.getCode().equals(KeyCode.ENTER)) {
                    String input = instrInput.getText();
                    instrInput.setText(CLEAR_FIELD);
                    //instrInput.setPromptText(PROMPT_TEXT);
                    passToLogic(input);
                }
            });

               
            //Id Column
            TableColumn<TaskEvent,Integer> idColumn = new TableColumn<>("Task ID");
            idColumn.setMinWidth(50);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("taskID"));
            
            //Task Name Column
            TableColumn<TaskEvent,String> nameColumn = new TableColumn<>("Task Name");
            nameColumn.setMinWidth(200);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
            
            //Task Description Column
            TableColumn<TaskEvent,String> descColumn = new TableColumn<>("Task Desc");
            descColumn.setMinWidth(250);
            descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            //Priority Column
            TableColumn<TaskEvent,Integer> priorityColumn = new TableColumn<>("Task Priority");
            priorityColumn.setMinWidth(50);
            priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
            
            //Date Column
            TableColumn<TaskEvent, TaskDate> dateColumn = new TableColumn<>("Task Date");
            dateColumn.setMinWidth(100);
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            
            //Setting up of task view     
            eventTable = new TableView<>();
            eventTable.setItems(getTasks());
            eventTable.getColumns().addAll(idColumn,nameColumn,descColumn,priorityColumn,dateColumn);
            eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            //Setting up of Border Pane
            root.setCenter(eventTable);
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
    
    public ObservableList<TaskEvent> getTasks() {
        Display display = new Display();
        ObservableList<TaskEvent> tasks = FXCollections.observableArrayList();
       /* ArrayList<TaskEvent> taskList = display.defaultView();
        for(TaskEvent t : taskList) {
        tasks.add(t);
        } */
        tasks.add(new TaskEvent(1,"test", new TaskDate() , 2 , "test" ));
        return tasks;
    }
    
    public void passToLogic(String input) {
        Operation op = new Operation();
        
        String output = op.processOperation(input); 
        AlertBox.display(TITLE_ALERT, output);
        
        }    
}