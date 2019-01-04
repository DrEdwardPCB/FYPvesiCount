package Application;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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
    private StackCanvas canvas;
    private int count=0;
    private String name;
    private Color color=Color.hsb((Math.random()*360),1.0,1.0);
    private Stack<StackCanvas> stack;
    private boolean selected=false;

    //UIelement
    private HBox right=new HBox(5);
    private HBox bottomRight=new HBox(0);
    private VBox left=new VBox();
    private Label nameDisplay=new Label();
    private Label colorDisplay=new Label();
    private Label countDisplay=new Label("Count: ");
    private Button undoButton=new Button("⎌");
    private Button deleteButton=new Button("x");

    public LayerControl(String name,int index){
        canvas=new StackCanvas(980,980,index);
        this.name=name;
        nameDisplay.textProperty().setValue(this.name);
        colorDisplay.textProperty().set("   ");
        colorDisplay.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        bottomRight.getChildren().addAll(colorDisplay,countDisplay);
        left.getChildren().addAll(nameDisplay,bottomRight);
        right.getChildren().addAll(undoButton,deleteButton);
        this.setRight(right);
        this.setLeft(left);
        setStyle();
        setCallback();
    }
    public void setStyle(){

    }
    public void setCallback(){
        this.setOnMouseClicked(e->{
            LayerManager.getInstance().changeSelected(this);
            System.out.println("selected");
        });
        canvas.setOnMouseClicked(e->{
            if(selected==true){
                GraphicsContext context=canvas.getGraphicsContext2D();
                context.setFill(color);
                context.save();
                context.fillRect(e.getX()-1,e.getY()-1,3,3);
                count++;
                countDisplay.textProperty().setValue("Count: "+count);
            }
        });

    }
    public StackCanvas getCanvas(){return this.canvas;}
    public String getName(){return this.name;}
    public void setSelected(boolean select){this.selected=select;}

}
