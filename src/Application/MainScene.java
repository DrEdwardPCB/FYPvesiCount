package Application;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    //menu
    private VBox upper=new VBox();
    private ToolBar toolBar=new ToolBar();
    private MenuBar menuBar=new MenuBar();
    /*menu
    file->import image, export image, export csv export graph

    tool
    import image button
    reset workspace
    Help
    */
    private Menu fileMenu=new Menu("File");
    private MenuItem importImageItem=new MenuItem("Import");
    private MenuItem exportImageItem=new MenuItem("Export to Image");
    private MenuItem exportCsvItem=new MenuItem("Export to csv");
    private MenuItem exportGraphItem=new MenuItem("Export to graph");

    Button importButtonTool=new Button();
    Button resetButtonTool=new Button();
    Button helpButtonTool=new Button();

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
        fileMenu.getItems().addAll(importImageItem, new SeparatorMenuItem(),exportImageItem,exportCsvItem,exportGraphItem);
        menuBar.getMenus().add(fileMenu);
        importButtonTool.setGraphic(new ImageView(getClass().getResource("../assest/add.png").toExternalForm()));
        resetButtonTool.setGraphic(new ImageView(getClass().getResource("../assest/reset.png").toExternalForm()));
        helpButtonTool.setGraphic(new ImageView(getClass().getResource("../assest/undo.png").toExternalForm()));
        toolBar.getItems().addAll(importButtonTool,resetButtonTool,new Separator(),helpButtonTool);
        upper.getChildren().addAll(menuBar,toolBar);
        this.setTop(upper);
        setCallback();
        thisscene=this;
        resetButtonTool.setDisable(true);
    }
    public void setCallback(){
        importButtonTool.setOnAction(e->{
            FileChooser pane=new FileChooser();
            pane.setTitle("choose image");
            pane.setInitialDirectory(new File(System.getProperty("user.dir")));
            File result=pane.showOpenDialog(stage);
            if(result!=null){
                fileName.textProperty().setValue(result.getName());
                Image image = new Image(result.toURI().toString(), 980, 980, true, true);
                context.drawImage(image,0,0);
                layer.getChildren().add(countingCanvas);
                chooseMapDirButton.setDisable(true);
                importImageItem.setDisable(true);
                importButtonTool.setDisable(true);
                resetButtonTool.setDisable(false);
            }
        });
        importImageItem.setOnAction(e->{
            FileChooser pane=new FileChooser();
            pane.setTitle("choose image");
            pane.setInitialDirectory(new File(System.getProperty("user.dir")));
            File result=pane.showOpenDialog(stage);
            if(result!=null){
                fileName.textProperty().setValue(result.getName());
                Image image = new Image(result.toURI().toString(), 980, 980, true, true);
                context.drawImage(image,0,0);
                layer.getChildren().add(countingCanvas);
                chooseMapDirButton.setDisable(true);
                importImageItem.setDisable(true);
                importButtonTool.setDisable(true);
                resetButtonTool.setDisable(false);
            }
        });
        chooseMapDirButton.setOnAction(e->{
            FileChooser pane=new FileChooser();
            pane.setTitle("choose image");
            pane.setInitialDirectory(new File(System.getProperty("user.dir")));
            File result=pane.showOpenDialog(stage);
            if(result!=null){
                fileName.textProperty().setValue(result.getName());
                Image image = new Image(result.toURI().toString(), 980, 980, true, true);
                context.drawImage(image,0,0);
                layer.getChildren().add(countingCanvas);
                chooseMapDirButton.setDisable(true);
                importImageItem.setDisable(true);
                importButtonTool.setDisable(true);
                resetButtonTool.setDisable(false);
            }
        });
        addLayerButton.setOnAction(e->{
            if(layer.getChildren().isEmpty()){
                JOptionPane.showMessageDialog(null,"Import image first","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name=JOptionPane.showInputDialog("enter the name of new layer");
            if(name!=null) {
                try {
                    LayerControl newLayer = LayerManager.getInstance().addLayer(name);
                    layer.getChildren().add(newLayer.getCanvas());
                    layerControlBar.getChildren().add(newLayer);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Name duplication", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        resetButtonTool.setOnAction(e->{
            fileName.textProperty().setValue("no File chosen");
            var haha=LayerManager.getInstance().getAllLayer();
            var hehe=new ArrayList<LayerControl>();
            for(var lc:haha){
                hehe.add(lc);
            }
            hehe.stream().forEach(lc->{System.out.println(lc);LayerManager.getInstance().deleteLayer(lc);});
            layer.getChildren().remove(countingCanvas);
            chooseMapDirButton.setDisable(false);
            importImageItem.setDisable(false);
            importButtonTool.setDisable(false);

        });
        resetButton.setOnAction(e->{
            LayerManager.getInstance().getAllLayer().stream().forEach(layer->layer.reset());
        });
        helpButtonTool.setOnAction(e-> SceneManager.getInstance().setScene(SCENELIST.HelpScene));
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
