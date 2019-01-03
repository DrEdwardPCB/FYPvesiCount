package Application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.Stack;

public class LayerControl extends BorderPane {
    //UI element
    //count
    //name
    //color
    //reset---set to 0
    //undo---count-1, remove previous dot(undo at most 5 store as canvas)
    //delete


    //variable
    private Canvas canvas;
    private int count=0;
    private String name;
    private Color color=new Color(((int)Math.random()*255),((int)Math.random()*255),((int)Math.random()*255),1.0);
    private Stack<Canvas> stack;
    private boolean selected=false;

    //UIelement
    private HBox right=new HBox();
    private VBox bottomRight=new VBox();
    private VBox left=new VBox();
    private Label nameDisplay=new Label();
    private Label colorDisplay=new Label();
    private Label countDisplay=new Label("Count: ");
    private Button undoButton=new Button("⎌");
    private Button deleteButton=new Button("x");

    public LayerControl(){
        canvas=new StackCanvas(980,980,1);
        bottomRight.getChildren().addAll(colorDisplay,countDisplay);
        right.getChildren().addAll(nameDisplay,bottomRight);
        left.getChildren().addAll(undoButton,deleteButton);
        this.setRight(right);
        this.setLeft(left);
        setStyle();
        setCallback();
    }
    public void setStyle(){

    }
    public void setCallback(){
        this.setOnMouseClicked(e->LayerManager.getInstance().changeSelected(this));
        canvas.setOnMouseClicked(e->{
            if(selected=true){
            GraphicsContext context=canvas.getGraphicsContext2D();
            context.setFill(Color.RED);
            context.fillRect(e.getX()-1,e.getY()-1,3,3);
            count++;
            countDisplay.textProperty().setValue("Count: "+count);
            }
        });
    }
    public Canvas getCanvas(){return this.canvas;}


}
