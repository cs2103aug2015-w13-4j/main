package programGui;

import java.util.ArrayList;
import utilities.TaskDate;
import utilities.TaskEvent;
import logic.Display;
import logic.Launch;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    private static final String PROMPT_TEXT = "Enter format: taskname ; date ; priority ; task description";
   
    //Program Title
    private static final String PROGRAM_TITLE = "PIXEList";
    
    //Table Components Name
    private static final String COLUMN_TASK_ID = "ID";
    private static final String COLUMN_TASK_NAME = "Task Name";
    private static final String COLUMN_TASK_DATE = "Task Date";
    private static final String COLUMN_TASK_PRIORITY = "Priority";
    private static final String COLUMN_TASK_DESC = "Task Description";
    
    //Task Data Name
    private static final String TASK_ID = "taskID";
    private static final String TASK_NAME = "taskName";
    private static final String TASK_DATE = "date";
    private static final String TASK_PRIORITY = "priority";
    private static final String TASK_DESC = "description";
    
    Stage window;
    Button button;
    Button confirmBtn;
    TableView<TaskEvent> eventTable;
    Launch launch;
    Label feedbackLabel;
    String input;
    
    /*public static void main(String[] args) {
        launch(args);
    } */
   
    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) throws Exception {
            window = primaryStage;
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,850,600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            launch = Launch.getInstance();
        
            window.setOnCloseRequest(e->  {
            e.consume();    
            exitProgram(); 
            });

            window.setTitle(PROGRAM_TITLE);
            window.setScene(scene);
            window.show();
            
            confirmBtn = new Button(BUTTON_EXIT);
            confirmBtn.setOnAction(e-> exitProgram() );
            
            TextField instrInput = new TextField();
            instrInput.setPromptText(PROMPT_TEXT);
            instrInput.setOnKeyPressed(e-> { 
                if(e.getCode().equals(KeyCode.ENTER)) {
                    input = instrInput.getText();
                    instrInput.setText(CLEAR_FIELD);
                    passToLogic(input);
                    eventTable.setItems(getTasks());
                }
            });
            
            feedbackLabel = new Label();
            feedbackLabel.setOpacity(20);
            feedbackLabel.setText("");
            
            
            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(feedbackLabel,instrInput);

               
            //Id Column
            TableColumn<TaskEvent,Integer> idColumn = new TableColumn<>(COLUMN_TASK_ID);
            idColumn.setMinWidth(5);
            idColumn.setCellValueFactory(new PropertyValueFactory<>(TASK_ID));
             
            //Task Name Column
            TableColumn<TaskEvent,String> nameColumn = new TableColumn<>(COLUMN_TASK_NAME);
            nameColumn.setMinWidth(150);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>(TASK_NAME));
            /*
            //Task Description Column
            TableColumn<TaskEvent,String> descColumn = new TableColumn<>(COLUMN_TASK_DESC);
            descColumn.setMinWidth(250);
            descColumn.setCellValueFactory(new PropertyValueFactory<>(TASK_DESC));
            */
            //Priority Column
            TableColumn<TaskEvent,Integer> priorityColumn = new TableColumn<>(COLUMN_TASK_PRIORITY);
            priorityColumn.setMinWidth(20);
            priorityColumn.setCellValueFactory(new PropertyValueFactory<>(TASK_PRIORITY));
            
            //Date Column
            TableColumn<TaskEvent, TaskDate> dateColumn = new TableColumn<>(COLUMN_TASK_DATE);
            dateColumn.setMinWidth(100);
            dateColumn.setCellValueFactory(new PropertyValueFactory<>(TASK_DATE));
            
            //Setting up of task view     
            eventTable = new TableView<>();
            eventTable.setItems(getTasks());
            eventTable.getColumns().addAll(idColumn,nameColumn,priorityColumn,dateColumn);
            eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            //Setting up of Border Pane
            root.setCenter(eventTable);
            //root.setTop(confirmBtn);
            root.setBottom(vbox);
            
        } 
 
    private void exitProgram() {
        
        Boolean ans = ConfirmationBox.display(TITLE_WARNING, WARNING_EXIT);
        
        if(ans) {
        	window.close();
        }
    }
    
    public ObservableList<TaskEvent> getTasks() {
    	Display display = Launch.getDisplay();
        ObservableList<TaskEvent> tasks = FXCollections.observableArrayList();
        ArrayList<TaskEvent> taskList = display.taskView();
        System.out.print("Hi\n");
        System.out.print(taskList.size());
        for(TaskEvent t : taskList) {
        	System.out.print(taskList.size());
        	tasks.add(t);
        } 
       /* tasks.add(new TaskEvent(1, "Do CS2101", new TaskDate(2015,10,10),new TaskDate(2015,10,12) ,1));
        tasks.add(new TaskEvent(2, "Do CS2010", new TaskDate(2015,10,19),new TaskDate(2015,10,20) , 1));
        tasks.add(new TaskEvent(2, "Do CS2103", new TaskDate(2015,10,12),new TaskDate(2015,10,15), 1));
        */return tasks;
    }
    
    /**
     * Passes what the user has entered to logic component.
     * @param input
     * 			is what the user has entered in the text field.
     */  
    public void passToLogic(String input) {      
        String output = launch.command(input);
        feedbackLabel.setText(launch.command(input));
        //AlertBox.display(TITLE_ALERT, output);
        }    
}