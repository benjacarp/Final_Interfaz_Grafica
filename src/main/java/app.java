import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ui.MainFrame;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class app extends Application {

    private Button button;

    public static void main(String[] args) {
        System.out.println("Interfaz");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Menu principal");
//        button = new Button();
//        button.setText("Click me!");
//        button.setOnAction(e -> this.buttonClick());
//
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//
//        Scene scene = new Scene(layout,300,250);

        primaryStage = new MainFrame();
        primaryStage.show();

    }

    private void buttonClick() {
        System.out.println("Oooh do that again");
    }

}
