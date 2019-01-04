package Application;

import com.sun.tools.javac.Main;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.swing.*;
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
    private boolean selected=true;
    private Stack<MouseEvent> eventstack=new Stack<>();
    //UIelement
    private HBox right=new HBox(5);
    private HBox bottomRight=new HBox(0);
    private VBox left=new VBox();
    private Label nameDisplay=new Label();
    private ColorPicker colorPicker=new ColorPicker(color);
    private Label countDisplay=new Label("Count: 0");
    private Button undoButton=new Button("⎌");
    private Button deleteButton=new Button("x");

    public LayerControl(String name,int index){
        canvas=new StackCanvas(980,980,index);
        this.name=name;
        nameDisplay.textProperty().setValue(this.name);
        bottomRight.getChildren().addAll(colorPicker,countDisplay);
        left.getChildren().addAll(nameDisplay,bottomRight);
        right.getChildren().addAll(undoButton,deleteButton);
        this.setRight(right);
        this.setLeft(left);
        this.setBottom(new Separator());
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
                context.fillRect(e.getX()-1,e.getY()-1,3,3);
                eventstack.push(e);
                count++;
                countDisplay.textProperty().setValue("Count: "+count);
            }
        });
        colorPicker.setOnAction(e->{
            if(count==0)
                color=colorPicker.getValue();
            else {
                JOptionPane.showMessageDialog(null, "you can only choose color before start counting", "Error", JOptionPane.ERROR_MESSAGE);
                colorPicker.setValue(color);
            }
        });
        undoButton.setOnAction(e->{
            //System.out.println("undo executed");
            GraphicsContext context=canvas.getGraphicsContext2D();
            if(!eventstack.isEmpty()) {
                context.setFill(new Color(0,0,0,0));
                context.clearRect(eventstack.peek().getX() - 1, eventstack.peek().getY() - 1, 3, 3);
                eventstack.pop();
                count--;
                countDisplay.textProperty().setValue("Count: "+count);
            }
        });
        deleteButton.setOnAction(e->{
            MainScene.getInstance().layer.getChildren().remove(this.getCanvas());
            MainScene.getInstance().layerControlBar.getChildren().remove(this);
            LayerManager.getInstance().deleteLayer(this);
        });
    }
    public StackCanvas getCanvas(){return this.canvas;}
    public String getName(){return this.name;}
    public void setSelected(boolean select){this.selected=select;}

}
