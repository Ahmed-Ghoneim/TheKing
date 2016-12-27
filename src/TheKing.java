
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TheKing extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane stackPane = new StackPane();
        King king = new King();
        stackPane.getChildren().add(king);
        stackPane.setStyle("-fx-background-image: url('png/g.jpg'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;"
                + "-fx-background-size: cover;");
        Scene scene = new Scene(stackPane, 5000, 6000);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player Vector");
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }
}
