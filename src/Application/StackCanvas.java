package Application;

import javafx.scene.canvas.Canvas;

public class StackCanvas extends Canvas {
    private int id;
    public StackCanvas(double height, double width, int id){
        super(height,width);
        this.id=id;
    }
    //if id=0=base image
    public int getID(){
        return this.id;
    }

}
