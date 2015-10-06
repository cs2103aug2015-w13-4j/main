package programGui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationBox {
        
    
    private static boolean ans;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        
        yesBtn.setOnAction(e-> {
            ans = true;
            window.close();
        } );
        noBtn.setOnAction(e-> {
            ans = false;
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesBtn , noBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return ans;
    }
    
}
