/*package zosia.tasks;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class blueStar extends Application {


    public static void main(String[] args) {

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

        Text text = new Text("Homework is awesome!");
        text.setY(180);
        text.setFont(Font.font(15));
        text.setX(300-((text.getLayoutBounds().getWidth())/2));
        text.setFill(Color.GREENYELLOW);
        root.getChildren().add(text);

        List<Shape> frame = chainOfCaR(600,  0, 0, 50);
        showShapes(root, frame);

    }


    private List<Shape> chainOfCaR(double a, double x0, double y0, double d){
        List<Shape> list = new ArrayList<>();
        double x = x0 + d/2;
        double y = y0 + d/2;
        for (int i = 0; i < 4*(a/d-1); i++) {
            if (i % 2 == 1){
                list.add(getSquare(x, y, d));
            }else {
                list.add(getCircle(x, y, d / 2));
            }
            if (i < a/d-1){
                x += d;
            }else if(i< 2*(a/d-1)){
                y += d;
            }else if ( i< 3*(a/d-1)){
                x -= d;
            }else{
                y -=d;
            }

        }
        return list;
    }
    private void showShapes (Group g, List<Shape> tablica){
        for (Shape r: tablica){
            g.getChildren().add(r);

        }
    }
    private Circle getCircle(double x, double y, double r){
        Circle circle2 = new Circle(r);
        circle2.setCenterX(x);
        circle2.setCenterY(y);
        Random random = new Random();
        circle2.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        return circle2;
    }

    private Rectangle getSquare(double x, double y, double d) {
        Rectangle rectangle = new Rectangle(d, d);
        rectangle.setX(x-d/2);
        rectangle.setY(y-d/2);
        Random random = new Random();
        rectangle.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        return rectangle;
    }

    private static Polygon setStar (double centerX, double centerY, double radius, int sides){
        Polygon star = new Polygon();
        double radiusSmall = radius*Math.sin(Math.PI/(2*sides))/Math.cos(Math.PI/sides);
        for (int i = 0; i < sides; i++) {
            double angle = i*2*Math.PI/sides;
            star.getPoints().addAll(radius*Math.sin(angle)+centerX,
                    centerY-radius*Math.cos(angle));

            angle += Math.PI/(sides);
            star.getPoints().addAll(radiusSmall*Math.sin(angle)+centerX,
                    centerY-radiusSmall*Math.cos(angle));
        }
        star.setFill(Color.BLUE);
        return star;
    }

}*/
