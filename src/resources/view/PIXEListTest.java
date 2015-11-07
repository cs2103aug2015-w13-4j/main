package resources.view;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class PIXEListTest extends GuiTest{
    
    BorderPane sceneRoot;
    TaskDisplayController taskDisplay;
    InputViewController inputView;
    Stage primaryStage;
    TextField userInput;
    Label feedBack;

    @Test
    public void testHelp() {
        userInput.setText("help");
        push(KeyCode.ENTER);
        verifyLabel("help activated");
        sleep(2,TimeUnit.SECONDS);
        push(KeyCode.ESCAPE);
    }
    
    @Test
    public void testInitDatas() {
        push(KeyCode.F1);
        assertEquals(inputView.getPresetSize(), 20);
    }
    
    @Test
    public void testAdd() {
        userInput.setText("add \"CS2010\"");
        push(KeyCode.ENTER);
        verifyLabel("CS2010 has been added successfully");
    }
    
    public void verifyLabel(String result) {
        assertEquals(result, feedBack.getText());
    }
    
    @Test
    public void testUndo() {
        userInput.setText("undo");
        push(KeyCode.ENTER);
    }
    
    @Test
    public void testRedo() {
        userInput.setText("redo");
        push(KeyCode.ENTER);
    }

    @Override
    protected Parent getRootNode() {
        return null;
    }

    @Override
    public void setupStage() throws Throwable {
        File f = new File("tasks.txt");
        f.delete();
        new Thread(() -> Application.launch(MainApp.class)).start();
        sleep(4,TimeUnit.SECONDS);
        userInput = (TextField)find("#userInput");
        feedBack = (Label)find("#feedBack");
        inputView = InputViewController.getInstance();
        taskDisplay = TaskDisplayController.getInstance();
    }
}
