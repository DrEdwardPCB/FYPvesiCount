package Application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CountingApplication extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("counting app");
        primaryStage.setScene(new Scene(new MainScene(primaryStage),1200,1000));
        primaryStage.show();
    }
}
