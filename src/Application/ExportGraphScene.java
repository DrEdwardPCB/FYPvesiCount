package Application;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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


public class ExportGraphScene extends BorderPane {
    private ToolBar toolBar=new ToolBar();
    private Button returnButton=new Button();
    private Button saveButton=new Button();
    //=======chart====
    final CategoryAxis xAxis=new CategoryAxis();
    final NumberAxis yAxis=new NumberAxis();
    final BarChart<String,Number> bc=new BarChart<>(xAxis,yAxis);
    //----------------
    public ExportGraphScene(Stage stage){
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
            fileChooser.initialFileNameProperty().setValue("graph.png");
            bc.setAnimated(false);
            WritableImage imagew=bc.snapshot(null,null);
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
        xAxis.setLabel("counter");
        yAxis.setLabel("count");
        XYChart.Series series=new XYChart.Series();
        LayerManager.getInstance().getAllLayer().forEach(lc->{
            series.getData().add(new XYChart.Data(lc.getName(),lc.getCount()));
        });
        bc.getData().add(series);

        //bc.setBarGap((bc.getWidth()-(LayerManager.getInstance().getAllLayer().size()*20))/LayerManager.getInstance().getAllLayer().size());

        bc.setCategoryGap((1835-(LayerManager.getInstance().getAllLayer().size()*40))/LayerManager.getInstance().getAllLayer().size());



        this.setCenter(bc);
    }
}
