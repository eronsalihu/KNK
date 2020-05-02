import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/login.fxml"));
        Image applicationIcon = new Image(getClass().getResourceAsStream("/Resources/logoja.png"));
        primaryStage.getIcons().add(applicationIcon);

        primaryStage.setTitle("Hello World");
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.sizeToScene();
        primaryStage.setMinWidth(scene.getWidth());
        primaryStage.setMinHeight(scene.getHeight());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
