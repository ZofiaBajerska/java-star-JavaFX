package zosia.tasks;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class redStar extends Application {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        launch(args);

    }

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



    private Transition createSequentialTransition(Shape node) {
        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(createTranslateTransition1(node),
                createParallelTransition2(node),
                createParallelTransition3(node),
                createParallelTransition4(node),
                createParallelTransition5(node));
        for (Animation a : st.getChildren()) {
            a.setCycleCount(1);
        }
        st.setCycleCount(Timeline.INDEFINITE);
        return st;
    }

    private Transition createTranslateTransition1(Node node) {
        TranslateTransition st = new TranslateTransition();
        st.setDuration(Duration.millis(2000));
        st.setNode(node);
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(0);
        st.setToY(-200);
        return st;
    }

    private Transition createParallelTransition2(Shape node) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(createTranslateTransition2(node));
        pt.getChildren().add(createRotateTransition(node));
        return pt;
    }

    private Transition createTranslateTransition2(Node node) {
        TranslateTransition st2 = new TranslateTransition();
        st2.setDuration(Duration.millis(2000));
        st2.setNode(node);
        st2.setFromX(0);
        st2.setFromY(-200);
        st2.setToX(200);
        st2.setToY(0);
        return st2;
    }

    private Transition createRotateTransition(Node node) {
        RotateTransition rt = new RotateTransition();
        rt.setFromAngle(0);
        rt.setToAngle(2880);
        rt.setDuration(Duration.millis(2000));
        rt.setNode(node);
        return rt;
    }

    private Transition createParallelTransition3(Shape node) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(createTranslateTransition3(node));
        pt.getChildren().add(createFadeTransition3(node));
        return pt;
    }

    private Transition createTranslateTransition3(Node node) {
        TranslateTransition st3 = new TranslateTransition();
        st3.setDuration(Duration.millis(2000));
        st3.setNode(node);
        st3.setFromX(200);
        st3.setFromY(0);
        st3.setToX(0);
        st3.setToY(220);
        return st3;
    }

    private Transition createFadeTransition3(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(2000), node);
        ft.setFromValue(1.0);
        ft.setToValue(0.01);
        return ft;
    }

    private Transition createParallelTransition4(Shape node) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(createTranslateTransition4(node));
        pt.getChildren().add(createFadeTransition4(node));
        return pt;
    }

    private Transition createTranslateTransition4(Node node) {
        TranslateTransition st4 = new TranslateTransition();
        st4.setDuration(Duration.millis(2000));
        st4.setNode(node);
        st4.setFromX(0);
        st4.setFromY(220);
        st4.setToX(-200);
        st4.setToY(0);
        return st4;
    }

    private Transition createFadeTransition4(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(2000), node);
        ft.setFromValue(0.01);
        ft.setToValue(1);
        return ft;
    }

    private Transition createParallelTransition5(Shape node) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(createTranslateTransition5(node));
        pt.getChildren().add(createNewColour(node));
        return pt;
    }

    private Transition createTranslateTransition5(Node node) {
        TranslateTransition st5 = new TranslateTransition();
        st5.setDuration(Duration.millis(2000));
        st5.setNode(node);
        st5.setFromX(-200);
        st5.setFromY(0);
        st5.setToX(0);
        st5.setToY(0);
        return st5;
    }

    private Transition createNewColour(Shape node) {
        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(createFillTransition1(node),
                createFillTransition2(node));
        for (Animation a : st.getChildren()) {
        }
        return st;
    }
    private Transition createFillTransition1(Shape shape) {
        FillTransition ft = new FillTransition();
        ft.setFromValue(Color.RED);
        ft.setToValue(Color.BLUE);
        ft.setShape(shape);
        ft.setDuration(Duration.millis(1000));
        return ft;
    }

    private Transition createFillTransition2(Shape shape) {
        FillTransition ft = new FillTransition();
        ft.setFromValue(Color.BLUE);
        ft.setToValue(Color.RED);
        ft.setShape(shape);
        ft.setDuration(Duration.millis(1000));
        return ft;
    }
}
