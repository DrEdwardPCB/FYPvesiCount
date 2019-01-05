package Application;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelpScene extends BorderPane {
    private TextArea description=new TextArea();
    private ToolBar toolBar=new ToolBar();
    private Button returnButton=new Button();
    public HelpScene(Stage stage){
        returnButton.setGraphic(new ImageView(getClass().getResource("../assest/undo.png").toExternalForm()));
        toolBar.getItems().add(returnButton);
        description.setWrapText(true);
        description.setEditable(false);
        description.setPrefSize(980,980);
        description.setText("FYPvesiCount\n" +
                "Description:\n" +
                "This little programme is created and maintained by Wong Yuk Ming Edward by the mean of calculating the SNR of the epifluorscent image of the FYP project\n" +
                "\n" +
                "How to use:\n" +
                "1. you have to import a image by clicking choose image file, File->import or import button on the tool bar\n" +
                "2. To start counting, click the \"+\" button to add a new layer of count\n" +
                "  2a. you have to give a name for the layer, make sure that each layer is having a unique name\n" +
                "3. before you count make sure to choose the desire color, once start count, you have to reset or undo all count of that layer to change the counting color\n" +
                "4. after counting, you can click File->Export<desire things> to export to the desire file type\n" +
                "By part menu:\n" +
                "\tmenuBar:\n" +
                "\t\tthe menu bar contains the \"File\" button that you can import or export picture\n" +
                "\tToolBar:\n" +
                "\t\tthe tool bar contains 3 buttons namely import, clear workspace and help from left to right\n" +
                "\t\timport button: simply import image to the canvas\n" +
                "\t\tclear workspace button: will enable after image imported, on clicked it will remove the image from the workspace together with the counter\n" +
                "\t\thelp button: if you see this passage already, i dun want to spend words on telling what this button can do\n" +
                "\tsideBar:\n" +
                "\t\tthis side bar is for user to know what image file you are accessing and display counter information\n" +
                "\t\tname of file: display the name of image file\n" +
                "\t\tadd button: add new counter\n" +
                "\t\treset button: reset all counter back to 0 and also clear all canvas marks\n" +
                "\tcounter:\n" +
                "\t\teach counter consist of name, color, count, undobutton and delete button\n" +
                "\t\tname:name of the counter, important as it will affect the name of the graph, to change name of the counter, just delete the old one and create a new one with desire name BEFORE START COUNTING\n" +
                "\t\tcolor:this color is randomly generated, user can change color BEFORE START COUNTING by clicking on it, this will change the color of the marker being mark on canvas\n" +
                "\t\tcount:this display the number of count you have made\n" +
                "\t\tundo button:in case user mark wrongly, user can press this button to remove the previos count and mark on the canvas\n" +
                "\t\tdelete button: this button will delete all the mark of this layer and delete this counter\n" +
                "\t\tbefore counting, you can click the area of the counter to select the counter that you want to count\n" +
                "\tcanvas\n" +
                "\t\tuser can count by clicking on the canvas area, a mark with color of selected counter will be marked on the canvas to indicate that you have counted that point, dragging will not cause count, count will only be made when you release the mouse click on canvas area\n");
        this.setTop(toolBar);
        this.setCenter(description);
        returnButton.setOnAction(e->SceneManager.getInstance().setScene(SCENELIST.MainScene));
    }
}
