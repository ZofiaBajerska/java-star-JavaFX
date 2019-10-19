package zosia.tasks;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World");

        launch(args);

    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.YELLOW);
        stage.setScene(scene);
        stage.show();
    }

}
