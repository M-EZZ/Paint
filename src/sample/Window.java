package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Muhammed Ezz on 5/10/2016.
 */
public class Window {

    static int choice =0 ;

    public static int display ( ){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Choose an option");
        window.setMinWidth(280);
        window.setMinHeight(320);
        Button resize = new Button("Resize Shape");
        Button move = new Button("Move Shape");
        Button copy = new Button("Copy Shape");
        Button delete = new Button("Delete Shape");
        Button color = new Button("Color shape");
        Button rotate = new Button("Rotate");
        TextField angle  = new TextField();
        angle.setPromptText("Enter Angle");
        VBox v = new VBox(20);
        v.setAlignment(Pos.CENTER);
        HBox h = new HBox(5);
        h.getChildren().addAll(angle, rotate);
        h.setPadding(new Insets(0,5,0,5));
        v.setPadding(new Insets(20,20,20,20));
        v.getChildren().addAll(resize,move,copy,delete,color,h);
        resize.setOnAction(e -> {
            choice = 1;
            window.close();
        });
        move.setOnAction(e-> {
            choice = 2 ;
            window.close();

        });
        copy.setOnAction(e-> {
            choice = 3;
            window.close();
        });
        delete.setOnAction(e-> {
            choice = 4;
            window.close();
        });
        color.setOnAction(e-> {
            choice = 5 ;
            window.close();
        });
        rotate.setOnAction(e->{
            choice = Integer.parseInt(angle.getText());
            window.close();
        });

        Scene scene = new Scene(v );
        window.setScene(scene);
        window.showAndWait();

        return choice;
    }
}
