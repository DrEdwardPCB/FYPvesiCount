package Application;

import java.util.ArrayList;
import java.util.stream.Collectors;

//manage which layer is selected
public class LayerManager {
    private ArrayList<LayerControl> layerlist=new ArrayList<>();
    private LayerControl selectedLayer;
    //function to add layer and delete layer
    private static LayerManager layerManger;

    private LayerManager(){ }

    public static LayerManager getInstance( ) {
        if (layerManger == null)
            layerManger=new LayerManager();
        return layerManger;
    }
    public LayerControl addLayer(String name)throws Exception{
            boolean error=false;
            ArrayList<LayerControl> tempLayerList = (ArrayList<LayerControl>) layerlist.stream().filter(layerControl -> layerControl.getName().equals(name)).collect(Collectors.toList());
            if(!tempLayerList.isEmpty()){
               throw new Exception("name duplication");
            }
            LayerControl newLayer = new LayerControl(name,layerlist.size());
            layerlist.add(newLayer);
            if(selectedLayer!=null){
                selectedLayer.setSelected(false);
            }
            selectedLayer = newLayer;

            return selectedLayer;
    }
    public void deleteLayer(){

    }
    public LayerControl getSelectedLayer(){
        return this.selectedLayer;
    }
    public void changeSelected(LayerControl lc){
        this.selectedLayer.setSelected(false);
        lc.setSelected(true);
        var canvas = lc.getCanvas();
        var parent = lc.getCanvas().getParent();

        this.selectedLayer=lc;

    }

}
