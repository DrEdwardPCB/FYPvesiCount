package Application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CountingApplication extends Application {
    public static Stage stage;
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        this.stage=primaryStage;
        primaryStage.setTitle("FYPvesiCount");
        primaryStage.setScene(new Scene(new MainScene(primaryStage),1200,1000));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

}
