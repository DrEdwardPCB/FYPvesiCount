package Application;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainScene extends BorderPane {
    private Canvas countingCanvas=new Canvas(980,980);
    private Button chooseMapDirButton=new Button("Choose map directory");
    private Button resetButton=new Button("reset count");
    private VBox leftContainer=new VBox(20);
    private VBox centerContainer=new VBox(20);
    private Label count=new Label("Count: 0");
    private Label fileName=new Label("no File chosen");
    private TextArea help=new TextArea(Config.HELP);
    private Stage stage;
    private GraphicsContext context=countingCanvas.getGraphicsContext2D();

    public MainScene(Stage stage){
       // System.out.print("setting scene");
        this.stage=stage;
        this.getStylesheets().add(Config.CSS_STYLES);
        leftContainer.getChildren().addAll(chooseMapDirButton,fileName,count,resetButton,help);
        centerContainer.getChildren().add(countingCanvas);
        leftContainer.getStyleClass().add("big-vbox");
        leftContainer.getChildren().stream().filter(Button.class::isInstance).forEach(node -> node.getStyleClass().add("big-button"));
        leftContainer.getChildren().stream().filter(TextArea.class::isInstance).forEach(node -> node.getStyleClass().add("text-area"));
        leftContainer.getStyleClass().add("side-menu");
        centerContainer.getStyleClass().add("big-vbox");
        centerContainer.setAlignment(Pos.CENTER);
        this.setLeft(leftContainer);
        this.setCenter(centerContainer);
        setCallback();
    }
    public void setCallback(){
        chooseMapDirButton.setOnAction(e->{
            var pane=new FileChooser();
            pane.setTitle("choose image");
            pane.setInitialDirectory(new File(System.getProperty("user.dir")));
            var result=pane.showOpenDialog(stage);
            if(result!=null){
            //load in the file resize to 720 on canvas
                //System.out.println(result.toURI().toString());
                fileName.textProperty().setValue(result.getName());
                Image image = new Image(result.toURI().toString(), 980, 980, true, true);
                context.drawImage(image,0,0);
            }
        });
        countingCanvas.setOnMouseClicked(e->{
            context.setFill(Color.RED);
            context.fillRect(e.getX()-1,e.getY()-1,3,3);
            count.textProperty().setValue("Count: "+((Integer)((Integer.parseInt(count.textProperty().get().replaceFirst("Count: ","")))+1)).toString());
        });
        resetButton.setOnAction(e->count.textProperty().setValue("Count: 0"));
    }
}
