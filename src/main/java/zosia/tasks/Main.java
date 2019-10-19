package zosia.tasks;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println("Hello World");

        launch(args);

    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();

        Polygon star = setStar(300,300, 100, 5);
        root.getChildren().add(star);


        Scene scene = new Scene(root, 600, 600, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    private static Polygon setStar (double centerX, double centerY, double radius, int sides){
        Polygon star = new Polygon();
        double radiusSmall = radius*Math.sin(Math.PI/(2*sides))/Math.cos(Math.PI/sides);
        System.out.println(radiusSmall);
        for (int i = 0; i < sides; i++) {
            double angle = i*2*Math.PI/sides;
            star.getPoints().addAll(radius*Math.sin(angle)+centerX,
                    centerY-radius*Math.cos(angle));
            System.out.println(radius*Math.sin(angle)+centerX);
            System.out.println(centerY-radius*Math.cos(angle));

            angle += Math.PI/(sides);
            star.getPoints().addAll(radiusSmall*Math.sin(angle)+centerX,
                    centerY-radiusSmall*Math.cos(angle));



        }
        star.setFill(Color.BLUE);
        return star;
    }

}
