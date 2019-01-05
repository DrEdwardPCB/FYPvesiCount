package Application;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ExportGraphScene extends BorderPane {
    private ToolBar toolBar=new ToolBar();
    private Button returnButton=new Button();
    private Button saveButton=new Button("Save");
    public ExportGraphScene(Stage stage){
        returnButton.setGraphic(new ImageView(getClass().getResource("../assest/undo.png").toExternalForm()));
        toolBar.getItems().addAll(returnButton,saveButton);
        generate();
        returnButton.setOnAction(e->SceneManager.getInstance().setScene(SCENELIST.MainScene));
        saveButton.setOnAction(e->{
            System.out.println("");
        });
    }
    private void generate(){}
}
