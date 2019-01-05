package Application;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ExportImageScene extends BorderPane {
    private ToolBar toolBar=new ToolBar();
    private Button returnButton=new Button();
    private Button saveButton=new Button();
    private Canvas canvas=new Canvas(980,980);
    public ExportImageScene(Stage stage){
        returnButton.setGraphic(new ImageView(getClass().getResource("../assest/undo.png").toExternalForm()));
        saveButton.setGraphic(new ImageView(getClass().getResource("../assest/save.png").toExternalForm()));
        toolBar.getItems().addAll(returnButton,saveButton);
        this.setTop(toolBar);
        generate();
        returnButton.setOnAction(e->SceneManager.getInstance().setScene(SCENELIST.MainScene));
        saveButton.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            //System.out.println(pic.getId());
            fileChooser.initialFileNameProperty().setValue("image.png");
            WritableImage imagew=canvas.snapshot(null,null);
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imagew,
                            null), "png", file);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
    private void generate(){
        this.canvas.getGraphicsContext2D().drawImage(MainScene.getInstance().layer.getChildren().get(0).snapshot(null,null),0,0);
        LayerManager.getInstance().getAllLayer().forEach(lc->{
            this.canvas.getGraphicsContext2D().setFill(lc.getColor());
            lc.getStack().forEach(node->{
                this.canvas.getGraphicsContext2D().fillRect(node.getX()-1,node.getY()-1,3,3);
            });
        });
        this.setCenter(this.canvas);
    }
}
