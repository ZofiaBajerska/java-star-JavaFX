package zosia.tasks.models;

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

/**
 * Application main class for java FX.
 *
 * @Author Zofia B.
 */
public class blueStar extends Application {

    /**
     * Main method of application.
     *
     * @param args application command line arguments.
     */

    public static void main(String[] args) {

        launch(args);
    }

    /**
     * Method call by lunch witch prepare scene, star polygon, text etc.
     *
     * @param stage The param of stage on witch we can see whole homework.
     */
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

        List<Shape> frame = chainOfCirclesAndSquares(600,  0, 0, 50);
        showShapes(root, frame);

    }

    /**
     * Method to get the list of frame of shapes(circles and squares).
     *
     * @param a Size of the stage,
     *
     * @param x0 Coordinate x for center.
     *
     * @param y0 Coordinate y for center.
     *
     * @param d Diameter of the shapes(circle and squares)
     *
     * @return
     */
    private List<Shape> chainOfCirclesAndSquares(double a, double x0, double y0, double d){
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

    /**
     * Private method which add the frame to the scene.
     *
     * @param g Root parameter.
     *
     * @param tablica Collection of frame shapes.
     */
    private void showShapes (Group g, List<Shape> tablica){
        for (Shape r: tablica){
            g.getChildren().add(r);

        }
    }


    /**
     * Method to get the circle with random color for the frame.
     *
     * @param x Coordinate x for center.
     *
     * @param y Coordinate y for center.
     *
     * @param r Radius.
     *
     * @return Circle with random colour.
     */
    private Circle getCircle(double x, double y, double r){
        Circle circle2 = new Circle(r);
        circle2.setCenterX(x);
        circle2.setCenterY(y);
        Random random = new Random();
        circle2.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        return circle2;
    }

    /**
     * Method to get the square with random color for the frame.
     *
     * @param x Coordinate x for center.
     *
     * @param y Coordinate y for center.
     *
     * @param d Diameter length.
     *
     * @return One square with random colour.
     */
    private Rectangle getSquare(double x, double y, double d) {
        Rectangle rectangle = new Rectangle(d, d);
        rectangle.setX(x-d/2);
        rectangle.setY(y-d/2);
        Random random = new Random();
        rectangle.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        return rectangle;
    }

    /**
     * Method to create star with the given number of sides and given radius.
     *
     * @param centerX Coordinates x of the center of the star.
     *
     * @param centerY Coordinates y of the center of the star.
     *
     * @param radius Radius of the star.
     *
     * @param sides Number of sides of the star.
     *
     * @return Star as a blue Polygon.
     */
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

}
