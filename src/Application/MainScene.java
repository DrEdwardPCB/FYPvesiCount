package Application;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class MainScene extends BorderPane {
    private StackCanvas countingCanvas=new StackCanvas(980,980,0);
    private Button chooseMapDirButton=new Button("Choose map directory");
    private Button resetButton=new Button("reset count");
    private VBox leftContainer=new VBox(20);
    private VBox centerContainer=new VBox(20);
    private HBox buttonBar=new HBox(10);
    private Label fileName=new Label("no File chosen");
    public VBox layerControlBar=new VBox(0);
    //private TextArea help=new TextArea(Config.HELP);
    private Button addLayerButton=new Button("+");
    private Stage stage;
    private GraphicsContext context=countingCanvas.getGraphicsContext2D();
    public StackPane layer=new StackPane();
    public static MainScene thisscene;
    public MainScene(Stage stage){
       // System.out.print("setting scene");
        this.stage=stage;
        this.getStylesheets().add(Config.CSS_STYLES);
        buttonBar.getChildren().addAll(addLayerButton,resetButton);
        leftContainer.getChildren().addAll(chooseMapDirButton,fileName,buttonBar,new Separator(),layerControlBar);
        centerContainer.getChildren().add(layer);
        leftContainer.getStyleClass().add("big-vbox");
        leftContainer.getChildren().stream().filter(Button.class::isInstance).forEach(node -> node.getStyleClass().add("big-button"));
        leftContainer.getChildren().stream().filter(TextArea.class::isInstance).forEach(node -> node.getStyleClass().add("text-area"));
        leftContainer.getStyleClass().add("side-menu");
        centerContainer.getStyleClass().add("big-vbox");
        centerContainer.setAlignment(Pos.CENTER);
        this.setLeft(leftContainer);
        this.setCenter(centerContainer);

        setCallback();
        thisscene=this;
    }
    public void setCallback(){
        chooseMapDirButton.setOnAction(e->{
            FileChooser pane=new FileChooser();
            pane.setTitle("choose image");
            pane.setInitialDirectory(new File(System.getProperty("user.dir")));
            File result=pane.showOpenDialog(stage);
            if(result!=null){
            //load in the file resize to 720 on canvas
                //System.out.println(result.toURI().toString());
                fileName.textProperty().setValue(result.getName());
                Image image = new Image(result.toURI().toString(), 980, 980, true, true);
                context.drawImage(image,0,0);
                layer.getChildren().add(countingCanvas);
            }
        });
        addLayerButton.setOnAction(e->{
            if(layer.getChildren().isEmpty()){
                JOptionPane.showMessageDialog(null,"Import image first","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name=JOptionPane.showInputDialog("enter the name of new layer");
            try {
                LayerControl newLayer=LayerManager.getInstance().addLayer(name);
                layer.getChildren().add(newLayer.getCanvas());
                layerControlBar.getChildren().add(newLayer);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Name duplication","Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        });
        resetButton.setOnAction(e->{
            fileName.textProperty().setValue("no File chosen");
            var haha=LayerManager.getInstance().getAllLayer();
            var hehe=new ArrayList<LayerControl>();
            for(var lc:haha){
                hehe.add(lc);
            }
            hehe.stream().forEach(lc->{System.out.println(lc);LayerManager.getInstance().deleteLayer(lc);});
            layer.getChildren().remove(countingCanvas);

        });
        /*countingCanvas.setOnMouseClicked(e->{
            context.setFill(Color.RED);
            context.fillRect(e.getX()-1,e.getY()-1,3,3);
            count.textProperty().setValue("Count: "+((Integer)((Integer.parseInt(count.textProperty().get().replaceFirst("Count: ","")))+1)).toString());
        });*/
        //resetButton.setOnAction(e->count.textProperty().setValue("Count: 0"));
    }
    public static MainScene getInstance(){
        return thisscene;
    }
}
