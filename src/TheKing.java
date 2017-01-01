
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TheKing extends Application {

    @Override
    public void start(Stage stage) {
        VBox v = new VBox(25);

        v.setStyle("-fx-background-image: url('png/g.png'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: cover;");
        Button start = new Button("Start Playing");
        TextField name = new TextField();
        Label label = new Label("Please, Enter you name:");
        name.setMaxWidth(200);
        v.getChildren().addAll(label, name, start);
        v.setAlignment(Pos.CENTER);
        Scene scene = new Scene(new King());
        start.setOnAction(e -> {
            if (!name.getText().isEmpty()) {
                scene.setRoot(new King());
            }
        });

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.setTitle("الشايب");
        //stage.setFullScreen(true);
        stage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }
}
