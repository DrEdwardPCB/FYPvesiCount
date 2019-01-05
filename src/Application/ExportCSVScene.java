package Application;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ExportCSVScene extends BorderPane {
    private ToolBar toolBar=new ToolBar();
    private Button returnButton=new Button();
    private Button saveButton=new Button();
    private TextArea ta=new TextArea();
    public ExportCSVScene(Stage stage){
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
            fileChooser.initialFileNameProperty().setValue("data.csv");
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                PrintWriter pw=null;
                try {
                    pw=new PrintWriter(file);
                    pw.print(ta.getText());
                    pw.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }finally{
                    pw.close();
                }
            }
        });
    }
    private void generate(){
        ta.setEditable(true);
        ta.setPrefSize(500,500);
        ta.setText("counter,count\n");
        LayerManager.getInstance().getAllLayer().forEach(lc->{
            ta.setText(ta.getText()+lc.getName()+","+lc.getCount()+"\n");
        });
        this.setCenter(ta);
    }
}
