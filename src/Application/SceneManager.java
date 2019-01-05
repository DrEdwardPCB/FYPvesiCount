package Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager sm;
    private Scene ms;
    private Scene hs;
    private Stage stage;
    private SceneManager(){
        stage=CountingApplication.stage;
        ms=new Scene(new MainScene(stage),1200,1000);
        ms=new Scene(new HelpScene(stage),1200,1000);
    }
    public static SceneManager getInstance(){
        if(sm==null){
            sm=new SceneManager();
        }
        return sm;
    }
    public void setScene(SCENELIST scene){
        switch(scene){
            case MainScene:{
                stage.setScene(ms);
                break;
            }
            case HelpScene:{
                stage.setScene(hs);
                break;
            }
        }
    }
}
