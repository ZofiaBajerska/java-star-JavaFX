package zosia.tasks.models;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Application main class for java FX.
 *
 * @Author Zofia B.
 */
public class redStar extends Application {

    /**
     * Main method of application.
     *
     * @param args application command line arguments.
     */
    public static void main(String[] args) {

        launch(args);

    }

    /**
     * Method call by lunch witch prepare scene, star polygon, all the button etc.
     *
     * @param stage The param of stage on which all the magic happened.
     */
    @Override
    public void start(Stage stage) {

        Polygon star = setStar(300,300, 100, 5);

        Transition starTransition = createSequentialTransition(star);

        Pane p = new Pane();
        p.getChildren().add(star);
        p.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));//, CornerRadii.EMPTY, Insets.EMPTY)));

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(p);


        VBox toolBox = new VBox();
        toolBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        {
            ToggleButton button = new ToggleButton("Start!");
            button.setUserData(starTransition);

            button.setOnAction(event -> {
                ToggleButton tb = (ToggleButton) event.getSource();
                Transition t = (Transition) tb.getUserData();
                if (tb.isSelected()) {
                    t.play();
                    tb.setText("Stop!");
                } else {
                    t.pause();
                    tb.setText("Start!");
                }

            });
            toolBox.getChildren().add(button);
            button.setMaxWidth(Double.POSITIVE_INFINITY);
        }
        rootPane.setLeft(toolBox);

        Scene scene = new Scene(rootPane, 650, 600, Color.BLACK);
        stage.setScene(scene);
        stage.show();
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
     * @return Star as a Polygon.
     */
    private static Polygon setStar (double centerX, double centerY, double radius, int sides){
        Polygon star = new Polygon();
        double radiusSmall = radius*Math.sin(Math.PI / ( 2 * sides)) / Math.cos (Math.PI / sides);
        for (int i = 0; i < sides; i++) {
            double angle = i * 2 * Math.PI / sides;
            star.getPoints().addAll(radius*Math.sin(angle)+centerX,
                    centerY-radius*Math.cos(angle));

            angle += Math.PI/(sides);
            star.getPoints().addAll(radiusSmall*Math.sin(angle)+centerX,
                    centerY-radiusSmall*Math.cos(angle));
        }
        star.setFill(Color.RED);
        return star;
    }

    /**
     * Method to define changes applied to the star,
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Sequential transition - definition of the changes.
     */

    private Transition createSequentialTransition(Shape node) {
        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(createTranslateTransitionMovingStarUp(node),
                createParallelTransitionRotateAndMove(node),
                createParallelTransitionDisappearAndMove(node),
                createParallelTransitionAppearAndMove(node),
                createParallelTransitionColorAndMove(node));
        for (Animation a : st.getChildren()) {
            a.setCycleCount(1);
        }
        st.setCycleCount(Timeline.INDEFINITE);
        return st;
    }

    /**
     * Method to define transition moving the shape.
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Created transition.
     */

    private Transition createTranslateTransitionMovingStarUp(Node node) {
        TranslateTransition st = new TranslateTransition();
        st.setDuration(Duration.millis(2000));
        st.setNode(node);
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(0);
        st.setToY(-200);
        return st;
    }
    /**
     * Method to define parallel transition moving the shape and it rotating.
     *
     * @param node Graphical object to be moved, changed
     *
     * @return Created transition.
     */
    private Transition createParallelTransitionRotateAndMove(Shape node) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(createTranslateTransitionMovingStarToMiddleRight(node));
        pt.getChildren().add(createRotateTransition(node));
        return pt;
    }

    /**
     * Method to define transition moving the shape.
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Created transition.
     */
    private Transition createTranslateTransitionMovingStarToMiddleRight(Node node) {
        TranslateTransition st2 = new TranslateTransition();
        st2.setDuration(Duration.millis(2000));
        st2.setNode(node);
        st2.setFromX(0);
        st2.setFromY(-200);
        st2.setToX(200);
        st2.setToY(0);
        return st2;
    }

    /**
     * Method to define transition to rotate the shape.
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Created transition.
     */
    private Transition createRotateTransition(Node node) {
        RotateTransition rt = new RotateTransition();
        rt.setFromAngle(0);
        rt.setToAngle(2880);
        rt.setDuration(Duration.millis(2000));
        rt.setNode(node);
        return rt;
    }

    /**
     * Method to define parallel transition moving the shape and it disappear.
     *
     * @param node Graphical object to be moved, changed
     *
     * @return Created transition.
     */
    private Transition createParallelTransitionDisappearAndMove(Shape node) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(createTranslateTransitionToTheBottom(node));
        pt.getChildren().add(createFadeTransitionDisappear(node));
        return pt;
    }

    /**
     * Method to define transition moving the shape.
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Created transition.
     */
    private Transition createTranslateTransitionToTheBottom(Node node) {
        TranslateTransition st3 = new TranslateTransition();
        st3.setDuration(Duration.millis(2000));
        st3.setNode(node);
        st3.setFromX(200);
        st3.setFromY(0);
        st3.setToX(0);
        st3.setToY(220);
        return st3;
    }

    /**
     * Method to define transition to disappear the shape.
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Created transition.
     */
    private Transition createFadeTransitionDisappear(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(2000), node);
        ft.setFromValue(1.0);
        ft.setToValue(0.01);
        return ft;
    }

    /**
     * Method to define parallel transition moving the shape and it appear.
     *
     * @param node Graphical object to be moved, changed
     *
     * @return Created transition.
     */
    private Transition createParallelTransitionAppearAndMove(Shape node) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(createTranslateTransitionToMiddleLeft(node));
        pt.getChildren().add(createFadeTransitionAppear(node));
        return pt;
    }

    /**
     * Method to define transition moving the shape.
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Created transition.
     */
    private Transition createTranslateTransitionToMiddleLeft(Node node) {
        TranslateTransition st4 = new TranslateTransition();
        st4.setDuration(Duration.millis(2000));
        st4.setNode(node);
        st4.setFromX(0);
        st4.setFromY(220);
        st4.setToX(-200);
        st4.setToY(0);
        return st4;
    }

    /**
     * Method to define transition to appear the shape.
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Created transition.
     */
    private Transition createFadeTransitionAppear(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(2000), node);
        ft.setFromValue(0.01);
        ft.setToValue(1);
        return ft;
    }

    /**
     * Method to define parallel transition moving the shape and color changing.
     *
     * @param node Graphical object to be moved, changed
     *
     * @return Created transition.
     */
    private Transition createParallelTransitionColorAndMove(Shape node) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(createTranslateTransitionToTheCenter(node));
        pt.getChildren().add(createChangingOfStarColours(node));
        return pt;
    }
    /**
     * Method to define transition moving the shape.
     *
     * @param node Graphical object to be moved, changed.
     *
     * @return Created transition.
     */
    private Transition createTranslateTransitionToTheCenter(Node node) {
        TranslateTransition st5 = new TranslateTransition();
        st5.setDuration(Duration.millis(2000));
        st5.setNode(node);
        st5.setFromX(-200);
        st5.setFromY(0);
        st5.setToX(0);
        st5.setToY(0);
        return st5;
    }

    /**
     * Method to define transition that change colour from red to blue to red from two transitions.
     *
     * @param node Graphical object to be moved, changed
     *
     * @return Created transition.
     */
    private Transition createChangingOfStarColours(Shape node) {
        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(createFillTransitionColourFromRedToBlue(node),
                createFillTransitionColourFromBlueToRed(node));
        for (Animation a : st.getChildren()) {
        }
        return st;
    }

    /**
     * Method to define transition that change colour from red to blue.
     *
     * @param shape Graphical object to be moved, changed.
     *
     * @return reated transition.
     */
    private Transition createFillTransitionColourFromRedToBlue(Shape shape) {
        FillTransition ft = new FillTransition();
        ft.setFromValue(Color.RED);
        ft.setToValue(Color.BLUE);
        ft.setShape(shape);
        ft.setDuration(Duration.millis(1000));
        return ft;
    }

    /**
     * Method to define transition that change colour from blue to red.
     *
     * @param shape Graphical object to be moved, changed.
     *
     * @return reated transition.
     */
    private Transition createFillTransitionColourFromBlueToRed(Shape shape) {
        FillTransition ft = new FillTransition();
        ft.setFromValue(Color.BLUE);
        ft.setToValue(Color.RED);
        ft.setShape(shape);
        ft.setDuration(Duration.millis(1000));
        return ft;
    }
}
