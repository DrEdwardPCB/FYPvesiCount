package Application;

import java.util.ArrayList;

//manage which layer is selected
public class LayerManager {
    private ArrayList<LayerControl> layerlist;
    private LayerControl selectedLayer;
    //function to add layer and delete layer
    private static LayerManager layerManger;

    private LayerManager(){ }

    public static LayerManager getInstance( ) {
        if (layerManger == null)
            layerManger=new LayerManager();
        return layerManger;
    }
    public void addLayer(){

    }
    public void deleteLayer(){

    }
    public void changeSelected(LayerControl lc){

    }

}
