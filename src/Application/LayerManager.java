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
    public void deleteLayer(LayerControl lc){
        layerlist.remove(lc);
        MainScene.getInstance().layer.getChildren().remove(lc.getCanvas());
        MainScene.getInstance().layerControlBar.getChildren().remove(lc);
        if(layerlist.isEmpty()){
            this.selectedLayer=null;
        }else{
            changeSelected(layerlist.get(0));
        }

    }
    public LayerControl getSelectedLayer(){
        return this.selectedLayer;
    }
    public ArrayList<LayerControl> getAllLayer(){
        return this.layerlist;
    }
    public void changeSelected(LayerControl lc){
        this.selectedLayer.setSelected(false);
        lc.setSelected(true);
        lc.getCanvas().toFront();

        this.selectedLayer=lc;

    }

}
